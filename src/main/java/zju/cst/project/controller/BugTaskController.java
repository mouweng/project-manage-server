package zju.cst.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import zju.cst.project.common.entity.JsonResult;
import zju.cst.project.common.enums.ResultCode;
import zju.cst.project.common.utils.ResultTool;
import zju.cst.project.entity.ProBugTask;
import zju.cst.project.entity.ProEvent;
import zju.cst.project.entity.vo.CreateBugTaskVo;
import zju.cst.project.entity.vo.CreateDevTaskVo;
import zju.cst.project.service.BugTaskService;
import zju.cst.project.service.DevTaskService;
import zju.cst.project.service.EventService;

import java.util.Date;

/**
 * @author: wengyifan
 * @description:
 * @date: 2021/3/3 8:54 上午
 */
@RestController
public class BugTaskController {
    @Autowired
    BugTaskService bugTaskService;
    @Autowired
    EventService eventService;
    //添加任务
    @PostMapping("/bugTask/createBugTask")
    public JsonResult createBugTask(CreateBugTaskVo createBugTaskVo) {
        ProBugTask proBugTask = bugTaskService.createBugTask(createBugTaskVo);
        bugTaskService.createBugTaskUser(proBugTask.getId(), createBugTaskVo.getDevUid(), createBugTaskVo.getTestUid());
        ProEvent proEvent = new ProEvent();
        proEvent.setProjectId(proBugTask.getProjectId());
        proEvent.setUserId(createBugTaskVo.getTestUid());
        proEvent.setContent(createBugTaskVo.getContent());
        proEvent.setTaskId(proBugTask.getId());
        // 0: dev, 1: test, 2:bug
        proEvent.setTaskType(2);
        proEvent.setTime(new Date());
        // 0: create ,1: finish
        proEvent.setEventType(0);
        eventService.createEvent(proEvent);
        return ResultTool.success("任务添加成功");
    }

    //删除任务
    @GetMapping("/bugTask/deleteBugTask/{bugTid}")
    public JsonResult deleteDevTask(@PathVariable("bugTid") Integer bugTid) {
        boolean res1 = bugTaskService.deleteBugTask(bugTid);
        boolean res2 = bugTaskService.deleteBugTaskUser(bugTid);
        if (res1 && res2) return ResultTool.success("任务删除成功");
        else return ResultTool.fail(ResultCode.COMMON_FAIL);
    }

    //将任务添加给用户
    @GetMapping("/bugTask/addBugTaskUser/{bugTid}/{devUid}/{testUid}")
    public JsonResult addDevTaskUser(@PathVariable("bugTid") Integer bugTid, @PathVariable("devUid") Integer devUid, @PathVariable("testUid") Integer testUid) {
        boolean res = bugTaskService.createBugTaskUser(bugTid, devUid, testUid);
        if(res) return ResultTool.success("成功将任务添加给用户");
        else return ResultTool.fail(ResultCode.COMMON_FAIL);
    }

    //根据bugTid查询任务
    @GetMapping("/bugTask/queryBugTaskByBugTid/{bugTid}")
    public JsonResult queryDevTaskByBugTid(@PathVariable("bugTid") Integer bugTid) {
        return ResultTool.success(bugTaskService.queryBugTaskByBugTid(bugTid));
    }

    //根据devTid查询任务
    @GetMapping("/bugTask/queryBugTaskByDevTid/{devTid}")
    public JsonResult queryDevTaskByDevTid(@PathVariable("devTid") Integer devTid) {
        return ResultTool.success(bugTaskService.queryBugTaskByDevTid(devTid));
    }

    //根据pid查询任务
    @GetMapping("/bugTask/queryBugTaskByPid/{pid}")
    public JsonResult queryBugTaskByPid(@PathVariable("pid") Integer pid) {
        return ResultTool.success(bugTaskService.queryBugTaskByPid(pid));
    }

    //根据devUid查询任务
    @GetMapping("/bugTask/queryBugTaskByDevUid/{devUid}")
    public JsonResult queryBugTaskByDevUid(@PathVariable("devUid") Integer devUid){
        return ResultTool.success(bugTaskService.queryBugTaskByDevUid(devUid));
    }

    //根据testUid查询任务
    @GetMapping("/bugTask/queryBugTaskByTestUid/{testUid}")
    public JsonResult queryBugTaskByTestUid(@PathVariable("testUid") Integer testUid){
        return ResultTool.success(bugTaskService.queryBugTaskByTestUid(testUid));
    }

    // todo: 根据uid和完成情况查询任务

    //根据pid和完成情况查询任务
    @GetMapping("/bugTask/queryBugTaskByPidAndStatus/{pid}/{status}")
    public JsonResult queryBugTaskByPidAndStatus(@PathVariable("pid") Integer pid, @PathVariable("status") Integer status) {
        return ResultTool.success(bugTaskService.queryBugTaskByPidAndStatus(pid, status));
    }

    //更改任务信息
    @PostMapping("/bugTask/updateBugTask")
    public JsonResult updateBugTask(CreateBugTaskVo createBugTaskVo) {
        boolean res = bugTaskService.updateBugTask(createBugTaskVo);
        if(res) return ResultTool.success("任务修改成功");
        else return ResultTool.fail(ResultCode.COMMON_FAIL);
    }

    //更改任务状态（待处理/进行中/已完成）
    @GetMapping("/bugTask/updateBugTaskStatus/{bugTid}/{status}")
    public JsonResult updateDevTaskStatus(@PathVariable("bugTid") Integer bugTid, @PathVariable("status") Integer status) {
        boolean res = bugTaskService.updateBugTaskStatus(bugTid, status);
        if(res) return ResultTool.success("状态修改成功");
        else return ResultTool.fail(ResultCode.COMMON_FAIL);
    }
}
