package zju.cst.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import zju.cst.project.common.entity.JsonResult;
import zju.cst.project.common.enums.ResultCode;
import zju.cst.project.common.utils.ResultTool;
import zju.cst.project.entity.ProDevTask;
import zju.cst.project.entity.ProEvent;
import zju.cst.project.entity.vo.CreateDevTaskVo;
import zju.cst.project.service.DevTaskService;
import zju.cst.project.service.EventService;
import zju.cst.project.service.ProjectService;
import zju.cst.project.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author: wengyifan
 * @description: 任务视图层
 * @date: 2021/3/1 10:59 下午
 */
@RestController
public class DevTaskController {

    @Autowired
    DevTaskService devTaskService;
    @Autowired
    EventService eventService;
    @Autowired
    UserService userService;
    @Autowired
    ProjectService projectService;

    // 添加任务
    // 传递参数：uid（用户id）, pid（项目id）, content（任务内容）, name（任务名字）
    @PostMapping("/devTask/createDevTask")
    public JsonResult createDevTask(CreateDevTaskVo createDevTaskVo) {
        int devTid = devTaskService.createDevTask(createDevTaskVo);
        boolean res1 = devTaskService.createDevTaskUser(devTid, createDevTaskVo.getUid());
        ProDevTask proDevTask = devTaskService.queryDevTaskByDevTid(devTid);
        boolean res2 = eventService.createEvent(devTid, 0, 0);
//        // 添加用户，如果已经存在，则不用重复添加
//        int uid = createDevTaskVo.getUid();
//        int pid = createDevTaskVo.getPid();
//        if(!projectService.queryProjectUserByUidPid(uid, pid)) {
//            projectService.addUser(pid, uid);
//        }
        if(res1 && res2)
        {
            return ResultTool.success(proDevTask);
        }
        else{
            return ResultTool.fail(ResultCode.COMMON_FAIL);
        }
    }


    // 删除任务
    // 传递参数：devTid（任务id）
    @GetMapping("/devTask/deleteDevTask/{devTid}")
    public JsonResult deleteDevTask(@PathVariable("devTid") Integer devTid) {
        boolean res1 = eventService.createEvent(devTid, 0, 2);
        boolean res2 = devTaskService.deleteDevTask(devTid);
        boolean res3 = devTaskService.deleteDevTaskUser(devTid);

        if (res1 && res2 && res3) return ResultTool.success("任务删除成功");
        else return ResultTool.fail(ResultCode.COMMON_FAIL);
    }

    // 将任务添加给用户
    // 传递参数：devTid（任务id）, uid（用户id）
    @GetMapping("/devTask/addDevTaskUser/{devTid}/{uid}")
    public JsonResult addDevTaskUser(@PathVariable("devTid") Integer devTid, @PathVariable("uid") Integer uid) {
        boolean res = devTaskService.createDevTaskUser(devTid, uid);
        if(res) return ResultTool.success("成功将任务添加给用户");
        else return ResultTool.fail(ResultCode.COMMON_FAIL);
    }

    // 根据devTid查询任务
    // 传递参数：devTid（任务id）
    @GetMapping("/devTask/queryDevTaskByDevTid/{devTid}")
    public JsonResult queryDevTaskByDevTid(@PathVariable("devTid") Integer devTid) {
        return ResultTool.success(devTaskService.queryDevTaskByDevTid(devTid));
    }

    // 根据pid查询任务
    // 传递参数：pid（项目id）
    @GetMapping("/devTask/queryDevTaskByPid/{pid}")
    public JsonResult queryDevTaskByPid(@PathVariable("pid") Integer pid) {
        return ResultTool.success(devTaskService.queryDevTaskByPid(pid));
    }

    // 根据uid查询任务
    // 传递参数：uid（用户id）
    @GetMapping("/devTask/queryDevTaskByUid/{uid}")
    public JsonResult queryDevTaskByUid(@PathVariable("uid") Integer uid) {
        return ResultTool.success(devTaskService.queryDevTaskByUid(uid));
    }

    // 根据uid和完成情况查询任务
    // 传递参数：uid（用户id）, devTid（任务id）
    @GetMapping("/devTask/queryDevTaskByUidAndStatus/{uid}/{status}")
    public JsonResult queryDevTaskByUidAndStatus(@PathVariable("uid") Integer uid, @PathVariable("status") Integer status) {
        return ResultTool.success(devTaskService.queryDevTaskByUidAndStatus(uid, status));
    }


    // 根据pid和完成情况查询任务
    // 传递参数：pid（项目id）, devTid（任务id）
    @GetMapping("/devTask/queryDevTaskByPidAndStatus/{pid}/{status}")
    public JsonResult queryDevTaskByPidAndStatus(@PathVariable("pid") Integer pid, @PathVariable("status") Integer status) {
        return ResultTool.success(devTaskService.queryDevTaskByPidAndStatus(pid, status));
    }

    // 更改任务信息
    // 传递参数：devTid（任务id）, content（任务内容）, name（任务名字）
    @PostMapping("/devTask/updateDevTask")
    public JsonResult updateDevTask(CreateDevTaskVo createDevTaskVo) {
        boolean res = devTaskService.updateDevTask(createDevTaskVo);
        if(res) return ResultTool.success("任务修改成功");
        else return ResultTool.fail(ResultCode.COMMON_FAIL);
    }


    // 更改任务状态（待处理/进行中/已完成）
    // 传递参数：devTid（任务id），status（任务状态，1：待处理/2：进行中/3：已完成）
    @GetMapping("/devTask/updateDevTaskStatus/{devTid}/{status}")
    public JsonResult updateDevTaskStatus(@PathVariable("devTid") Integer devTid, @PathVariable("status") Integer status) {
        boolean res = devTaskService.updateDevTaskStatus(devTid, status);
        if(status.equals(2)) {
            eventService.createEvent(devTid, 0, 1);
        }
        if(res) return ResultTool.success("状态修改成功");
        else return ResultTool.fail(ResultCode.COMMON_FAIL);
    }

    @GetMapping("/devTask/updateDevTaskFinished/{devTid}/{finished}")
    public JsonResult updateDevTaskFinished(@PathVariable("devTid") Integer devTid, @PathVariable("finished") Integer finished) {
        boolean res = devTaskService.updateDevTaskFinished(devTid, finished);
        if (finished.equals(0)) {
            eventService.createEvent(devTid, 0, 3);
        } else if (finished.equals(1)){
            eventService.createEvent(devTid, 0, 1);
        }
        if(res) return ResultTool.success("状态修改成功");
        else return ResultTool.fail(ResultCode.COMMON_FAIL);
    }

    @Transactional
    @GetMapping(value = "/devTask/setDevTaskUsers/{devTid}")
    public JsonResult setDevUsers(@PathVariable("devTid") Integer devTid, @RequestParam("userIds") Integer[] useIds) {
        return ResultTool.success(devTaskService.setDevTaskUsers(devTid, Arrays.asList(useIds)));
    }

    @GetMapping(value = "/devTask/getDevTaskByProjectIDandUserID/{pid}/{uid}")
    public JsonResult getDevTaskByProjectIDandUserID(@PathVariable("pid") Integer pid, @PathVariable("uid") Integer uid) {
        // 获取User权限
        Integer roleId = userService.queryUserRole(uid);
        List<ProDevTask> devTasks;
        if (roleId == 2 || roleId == 1) {
            // 管理员得到自己所在项目的Task
            devTasks = devTaskService.queryDevTaskByPid(pid);
        } else {
            // 非管理员得到自己的Task
            devTasks = devTaskService.queryDevTaskByPidAndUid(pid,uid);
        }
        return ResultTool.success(devTasks);
    }


}
