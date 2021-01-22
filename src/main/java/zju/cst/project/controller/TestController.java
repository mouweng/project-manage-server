package zju.cst.project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zju.cst.project.common.entity.JsonResult;
import zju.cst.project.common.utils.ResultTool;
import zju.cst.project.entity.ProPermission;
import zju.cst.project.entity.ProUser;
import zju.cst.project.service.PermissionService;
import zju.cst.project.service.UserService;

import java.util.List;

/**
 * @Author: wengyifan
 * @Description: 测试Controller入口
 * @Date Create in 2021/1/21 7:30 下午
 */
@RestController
public class TestController {
    @Autowired
    PermissionService permissionService;

    @Autowired
    UserService userService;

    @GetMapping("/test")
    public String test() {
        return "This is test!";
    }

    @GetMapping("/getPermission/{id}")
    public JsonResult getUser(@PathVariable("id") Integer id) {
        List<ProPermission> permissions = permissionService.selectListByUser(id);
        return ResultTool.success(permissions);
    }

    @GetMapping("/getPath")
    public JsonResult getPath() {
        List<ProPermission> permissions = permissionService.selectListByPath("/user/getUser");
        return ResultTool.success(permissions);
    }

    @GetMapping("/update")
    public JsonResult getUser() {
        ProUser user = userService.queryById(3);
        user.setTelephone("1234567890");
        userService.update(user);
        return ResultTool.success();
    }


}
