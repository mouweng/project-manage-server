package zju.cst.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import zju.cst.project.common.entity.JsonResult;
import zju.cst.project.common.utils.ResultTool;
import zju.cst.project.entity.ProUser;
import zju.cst.project.service.UserService;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user/getUser/{id}")
    public JsonResult getUser(@PathVariable("id") Integer id) {
        ProUser user = userService.queryById(id);
        return ResultTool.success(user);
    }

}
