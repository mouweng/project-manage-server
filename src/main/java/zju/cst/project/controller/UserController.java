package zju.cst.project.controller;

//import org.omg.CORBA.NO_PERMISSION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import zju.cst.project.common.entity.JsonResult;
import zju.cst.project.common.enums.ResultCode;
import zju.cst.project.common.utils.ResultTool;
import zju.cst.project.entity.ProUser;
import zju.cst.project.entity.vo.UserVo;
import zju.cst.project.service.UserService;

import java.security.Principal;
import java.util.Date;
import java.util.List;

/**
 * @Author: wengyifan
 * @Description: 用户视图层
 * @Date Create in 2021/1/21 7:30 下午
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;

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

    /**
     * @param userVo
     * @param principal
     * @return {@link JsonResult}
     * @throws
     * @author: wengyifan
     * @description: 修改用户信息，可修改字段:userName、password、department、telephone、position
     * @date: 2021/1/21 7:54 下午
     */
    @PostMapping("/user/modifyUser")
    public JsonResult modifyUser(UserVo userVo, Principal principal) {
        // 如果查询不到user，则返回账号不存在
        ProUser user = userService.selectByName(userVo.getAccount());
        if (user == null) return ResultTool.fail(ResultCode.USER_ACCOUNT_NOT_EXIST);

        // 获取主动修改的用户信息
        ProUser mUser = userService.selectByName(principal.getName());

        // 判断mUser的权限是否大于user的权限
        boolean canModify = userService.compareRole(mUser, user);

        // 判断查询的用户id和已登录的用户id是否一致
        if (user.getAccount().equals(principal.getName()) || canModify) {
            // 执行update
            user.setUpdateUser(mUser.getId());
            user.setUpdateTime(new Date());
            userService.update(user, userVo);
            return ResultTool.success();
        } else {
            return ResultTool.fail(ResultCode.NO_PERMISSION);
        }
    }

    /**
     * account, user_name, password, department, telephone, position, role
     * @param 
     * @return {@link JsonResult}
     * @throws
     * @author: wengyifan
     * @description: 
     * @date: 2021/1/29 9:58 上午 
     */
    @PostMapping("/user/createUser")
    public JsonResult createUser(UserVo userVo, Principal principal) {
        if (principal == null) return ResultTool.fail(ResultCode.USER_NOT_LOGIN);
        // 获取创建者信息
        String createUserName = principal.getName();
        ProUser createUser = userService.selectByName(createUserName);


        // 账号、密码、昵称、权限不能为空，为空则返回参数缺失
        if (userVo.getUserName() == null || userVo.getUserName().equals("")) {
            return ResultTool.fail(ResultCode.PARAM_NOT_COMPLETE);
        }else if (userVo.getAccount() == null || userVo.getAccount().equals("")) {
            return ResultTool.fail(ResultCode.PARAM_NOT_COMPLETE);
        }else if (userVo.getPassword() == null || userVo.getPassword().equals("")) {
            return ResultTool.fail(ResultCode.PARAM_NOT_COMPLETE);
        }else if (userVo.getRole() == null) {
            return ResultTool.fail(ResultCode.PARAM_NOT_COMPLETE);
        }else if (userVo.getRole() != 2 &&  userVo.getRole() != 3) {
            // 如果role不为2或3（2代表项目经理，3代表普通用户），则返回参数无效
            return ResultTool.fail(ResultCode.PARAM_NOT_VALID);
        }

        // 查看是否能够创建
        boolean canCreate = userService.compareRole(createUser, userVo.getRole());
        if (!canCreate) {
            return ResultTool.fail(ResultCode.NO_PERMISSION);
        }

        // 验证账号是否存在，若存在，则返回失败
        if (userService.selectByName(userVo.getAccount()) != null) {
            return ResultTool.fail(ResultCode.USER_ACCOUNT_ALREADY_EXIST);
        }

        // 调用service层创建账户
        boolean flag = userService.createCommonUser(userVo, createUser);
        if (flag) return ResultTool.success();
        else return ResultTool.fail();
    }

    /**
     * @param id
     * @return {@link JsonResult}
     * @throws
     * @author: wengyifan
     * @description: 
     * @date: 2021/1/29 9:58 上午 
     */
    @GetMapping("/user/deleteUser/{id}")
    public JsonResult deleteUser(@PathVariable("id") Integer id, Principal principal) {
        if (principal == null) return ResultTool.fail(ResultCode.USER_NOT_LOGIN);
        // 判断账号是否存在
        ProUser user = userService.queryById(id);
        if (user == null) return ResultTool.fail(ResultCode.USER_ACCOUNT_NOT_EXIST);

        ProUser dUser = userService.selectByName(principal.getName());
        if (userService.compareRole(dUser, user)) {
            userService.deleteUser(id);
            return ResultTool.success();
        } else {
            return ResultTool.fail(ResultCode.NO_PERMISSION);
        }
    }

    /**
     * @return {@link List <ProUser>}
     * @description: 获取数据库中所有的用户
     * @author: Huachang Yu
     */
    @Cacheable(value = "user", key = "'alluser'")
    @GetMapping( value = "/user/all")
    public JsonResult<List<ProUser>> getAllUser(Principal principal) {
        if (principal == null) return ResultTool.fail(ResultCode.USER_NOT_LOGIN);
        return ResultTool.success(this.userService.getAllUsers());
    }

    @GetMapping(value = "/user/queryUserByDevTid/{devTid}")
    public JsonResult queryUserByDevTid(@PathVariable("devTid") Integer devTid) {
        return ResultTool.success(userService.queryUserByDevTid(devTid));
    }
}
