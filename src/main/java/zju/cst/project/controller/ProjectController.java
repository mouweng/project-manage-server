package zju.cst.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import zju.cst.project.common.entity.JsonResult;
import zju.cst.project.common.enums.ResultCode;
import zju.cst.project.common.utils.ResultTool;
import zju.cst.project.entity.ProUser;
import zju.cst.project.entity.vo.ProjectVo;
import zju.cst.project.service.ProjectService;
import zju.cst.project.service.UserService;

import java.security.Principal;

/**
 * @author: wengyifan
 * @description: 项目视图层
 * @date: 2021/2/27 7:17 下午
 */
@RestController
public class ProjectController {

    @Autowired
    ProjectService projectService;
    @Autowired
    UserService userService;


    /**
     * @param
     * @return {@link JsonResult}
     * @throws
     * @author: wengyifan
     * @description: 显示所有项目
     * @date: 2021/2/27 9:25 下午
     */
    @GetMapping("/project/queryAll")
    public JsonResult queryAll() {
        return ResultTool.success(projectService.queryAll());
    }

    /**
     * @param id
     * @param principal
     * @return {@link JsonResult}
     * @throws
     * @author: wengyifan
     * @description: 查询当前用户的项目,排好序返回当前用户的项目（按照type排序、创建时间）
     * @date: 2021/2/27 9:41 下午
     */
    @GetMapping("/project/queryByUid/{id}")
    public JsonResult queryByUId(@PathVariable("id") Integer id, Principal principal) {
        if (principal == null) return ResultTool.fail(ResultCode.USER_NOT_LOGIN);
        ProUser user = userService.queryById(id);
        // 如果查询不到user，则返回账号不存在
        if (user == null) return ResultTool.fail(ResultCode.USER_ACCOUNT_NOT_EXIST);

        // 判断查询的用户id和已登录的用户id是否一致
        if (user.getAccount().equals(principal.getName())) {
            return ResultTool.success(projectService.queryByUid(id));
        } else {
            return ResultTool.fail(ResultCode.NO_PERMISSION);
        }
    }

    /**
     * @param id
     * @return {@link JsonResult}
     * @throws
     * @author: wengyifan
     * @description: 根据项目id获取项目信息
     * @date: 2021/2/28 9:15 上午
     */
    @GetMapping("/project/queryById/{id}")
    public JsonResult queryById(@PathVariable("id") Integer id) {
        return ResultTool.success(projectService.queryById(id));
    }

    /**
     * @param id
     * @return {@link JsonResult}
     * @throws
     * @author: wengyifan
     * @description: 根据项目ID查询参与项目的人员
     * @date: 2021/2/28 9:15 上午
     */
    @GetMapping("/project/queryUserByPid/{id}")
    public JsonResult queryUserByPid(@PathVariable("id") Integer id) {
        return ResultTool.success(userService.queryUserByPid(id));
    }

    /**
     * @param projectVo
     * @param principal
     * @return {@link JsonResult}
     * @throws
     * @author: wengyifan
     * @description: 修改项目信息（项目经理权限）
     * @date: 2021/2/28 2:03 下午
     */
    @PostMapping("/project/modifyProject")
    public JsonResult modifyProject(ProjectVo projectVo, Principal principal) {
        // 获取当前登录用户
        if (principal == null) return ResultTool.fail(ResultCode.USER_NOT_LOGIN);
        String createUserName = principal.getName();
        ProUser createUser = userService.selectByName(createUserName);
        // 判断当前用户是否管理此项目
        Integer uid = createUser.getId();
        Integer pid = projectVo.getId();
        if (!projectService.queryProjectManagerUserByUidPid(uid, pid)) {
            return ResultTool.fail(ResultCode.NO_PERMISSION);
        }
        // 修改项目信息
        projectService.modifyProject(projectVo);
        return ResultTool.success("修改项目信息成功");
    }


    /**
     * @param pid
     * @param username
     * @param principal
     * @return {@link JsonResult}
     * @throws
     * @author: wengyifan
     * @description: 增加项目成员（项目经理权限）
     * @date: 2021/2/28 2:43 下午
     */
    @GetMapping("/project/addUser/{pid}/{username}")
    public JsonResult AddUser(@PathVariable("pid") Integer pid, @PathVariable("username") String username, Principal principal) {
        // 获取当前登录用户
        if (principal == null) return ResultTool.fail(ResultCode.USER_NOT_LOGIN);
        String createUserName = principal.getName();
        ProUser createUser = userService.selectByName(createUserName);
        // 判断当前用户是否管理此项目
        Integer uid = createUser.getId();
        if (!projectService.queryProjectManagerUserByUidPid(uid, pid)) {
            return ResultTool.fail(ResultCode.NO_PERMISSION);
        }
        // 判断要添加的用户是否存在
        ProUser user = userService.selectByName(username);
        if (user == null) {
            return ResultTool.fail(ResultCode.USER_NOT_EXIST);
        }

        // 判断是否已经在项目组中
        if(projectService.queryProjectUserByUidPid(user.getId(), pid)) {
            return ResultTool.fail(ResultCode.USER_EXIST_IN_PROJECT);
        }

        // 添加用户
        projectService.addUser(pid, user.getId());
        return ResultTool.success("添加成功");
    }

    /**
     * @param pid
     * @param uid
     * @param principal
     * @return {@link JsonResult}
     * @throws
     * @author: wengyifan
     * @description: 删除项目成员（项目经理权限）
     * @date: 2021/3/1 1:18 下午
     */
    @GetMapping("/project/deleteUser/{pid}/{uid}")
    public JsonResult AddUser(@PathVariable("pid") Integer pid, @PathVariable("uid") Integer uid, Principal principal) {
        if (principal == null) return ResultTool.fail(ResultCode.USER_NOT_LOGIN);
        // 获取当前登录用户
        String principalUserName = principal.getName();
        ProUser principalUser = userService.selectByName(principalUserName);

        // 判断当前用户是否管理此项目
        Integer principalUid = principalUser.getId();
        if (!projectService.queryProjectManagerUserByUidPid(principalUid, pid)) {
            return ResultTool.fail(ResultCode.NO_PERMISSION);
        }
        if(projectService.deleteUser(pid, uid)) {
            return ResultTool.success("删除成功");
        } else {
            return ResultTool.fail(ResultCode.DELETE_ERROR);
        }
    }

    /**
     * @param projectVo
     * @param principal
     * @return {@link JsonResult}
     * @throws
     * @author: wengyifan
     * @description: 创建项目（项目经理权限）
     * @date: 2021/3/1 1:19 下午
     */
    @PostMapping("/project/createProject")
    public JsonResult createProject(ProjectVo projectVo, Principal principal) {
        if (principal == null) return ResultTool.fail(ResultCode.USER_NOT_LOGIN);
        // 获取当前登录用户
        String principalUserName = principal.getName();
        ProUser principalUser = userService.selectByName(principalUserName);

        // 创建项目
        int pid = projectService.createProject(projectVo);

        // 关联项目和管理员
        projectService.addManagerUser(pid, principalUser.getId());
        return ResultTool.success("创建项目成功");
    }

    // todo:设置测试组长
    // todo:撤销测试组长


    /**
     * @param id
     * @param principal
     * @return {@link JsonResult}
     * @throws
     * @author: wengyifan
     * @description: 删除项目及其相关的数据(未来还要删除)
     * @date: 2021/3/1 1:19 下午
     */
    // todo:未来还要删除和项目相关的任务
    @GetMapping("/project/deleteProject/{id}")
    public JsonResult createProject(@PathVariable("id") Integer id, Principal principal) {
        if (principal == null) return ResultTool.fail(ResultCode.USER_NOT_LOGIN);
        // 获取当前登录用户
        String principalUserName = principal.getName();
        ProUser principalUser = userService.selectByName(principalUserName);

        // 判断当前用户是否管理此项目
        Integer principalUid = principalUser.getId();
        if (!projectService.queryProjectManagerUserByUidPid(principalUid, id)) {
            return ResultTool.fail(ResultCode.NO_PERMISSION);
        }
        projectService.deleteProject(id);
        // todo: 删除和项目相关的任务
        return ResultTool.success("删除项目成功");
    }
}
