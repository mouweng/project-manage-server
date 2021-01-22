package zju.cst.project.controller;

import org.omg.CORBA.NO_PERMISSION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import zju.cst.project.common.entity.JsonResult;
import zju.cst.project.common.enums.ResultCode;
import zju.cst.project.common.utils.ResultTool;
import zju.cst.project.entity.ProUser;
import zju.cst.project.entity.vo.ModifyUserVo;
import zju.cst.project.service.UserService;

import java.security.Principal;

/**
 * @Author: wengyifan
 * @Description: 用户视图层
 * @Date Create in 2021/1/21 7:30 下午
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * @param id
     * @param principal
     * @return {@link JsonResult}
     * @throws
     * @author: wengyifan
     * @description: 根据id获取用户信息
     * @date: 2021/1/21 7:53 下午
     */
    @GetMapping("/user/getUser/{id}")
    public JsonResult getUser(@PathVariable("id") Integer id, Principal principal) {
        ProUser user = userService.queryById(id);

        // 如果查询不到user，则返回账号不存在
        if (user == null) return ResultTool.fail(ResultCode.USER_ACCOUNT_NOT_EXIST);

        // 判断查询的用户id和已登录的用户id是否一致
        if (user.getAccount().equals(principal.getName())) {
            return ResultTool.success(user);
        } else {
            return ResultTool.fail(ResultCode.NO_PERMISSION);
        }
    }

    /**
     * @param modifyUserVo
     * @param principal
     * @return {@link JsonResult}
     * @throws
     * @author: wengyifan
     * @description: 修改用户信息，可修改字段:userName、password、department、telephone、position
     * @date: 2021/1/21 7:54 下午
     */
    @PostMapping("/user/modifyUser")
    public JsonResult modifyUser(ModifyUserVo modifyUserVo, Principal principal) {
        ProUser user = userService.selectByName(modifyUserVo.getAccount());

        // 如果查询不到user，则返回账号不存在
        if (user == null) return ResultTool.fail(ResultCode.USER_ACCOUNT_NOT_EXIST);

        // 判断查询的用户id和已登录的用户id是否一致
        if (user.getAccount().equals(principal.getName())) {
            // 更新字段
            if (modifyUserVo.getUserName() != null && !modifyUserVo.getUserName().equals(""))
                user.setUserName(modifyUserVo.getUserName());
            if (modifyUserVo.getPassword() != null && !modifyUserVo.getPassword().equals(""))
                user.setPassword(passwordEncoder.encode(modifyUserVo.getPassword()));
            if (modifyUserVo.getDepartment() != null && !modifyUserVo.getDepartment().equals(""))
                user.setDepartment(modifyUserVo.getDepartment());
            if (modifyUserVo.getTelephone() != null && !modifyUserVo.getTelephone().equals(""))
                user.setTelephone(modifyUserVo.getTelephone());
            if (modifyUserVo.getPosition() != null && !modifyUserVo.getPosition().equals(""))
                user.setPosition(modifyUserVo.getPosition());

            // 执行update
            userService.update(user);
            return ResultTool.success();
        } else {
            return ResultTool.fail(ResultCode.NO_PERMISSION);
        }
    }


    /**
     * @param principal
     * @return {@link String}
     * @throws
     * @author: wengyifan
     * @description: 获取用户名
     * @date: 2021/1/21 7:55 下午
     */
    @GetMapping(value = "/user/username")
    public String currentUserName(Principal principal) {
        if (principal != null) return principal.getName();
        return "";
    }

    @GetMapping(value = "/user/test")
    public String testUser() {
        return "this is test";
    }

}