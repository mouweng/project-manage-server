package zju.cst.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import zju.cst.project.common.entity.JsonResult;
import zju.cst.project.common.enums.ResultCode;
import zju.cst.project.common.utils.ResultTool;
import zju.cst.project.entity.vo.CreateBugTaskVo;
import zju.cst.project.service.BugTaskService;
import zju.cst.project.service.DevTaskService;

/**
 * @author: wengyifan
 * @description:
 * @date: 2021/3/3 8:54 上午
 */
@RestController
public class BugTaskController {
    @Autowired
    BugTaskService bugTaskService;
    //添加任务
    @PostMapping("/bugTask/createBugTask")
    public JsonResult createBugTask(CreateBugTaskVo createBugTaskVo) {
        int bugTid = bugTaskService.createBugTask(createBugTaskVo);
        bugTaskService.createBugTaskUser(bugTid, createBugTaskVo.getDevUid(), createBugTaskVo.getTestUid());
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
    @GetMapping("/bugTask/addBugTaskUser/{bugTid}/{uid}")
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

    // todo: 根据devTid查询任务
    @GetMapping("/bugTask/queryBugTaskByDevTid/{devTid}")
    public JsonResult queryDevTaskByDevTid(@PathVariable("devTid") Integer devTid) {
        return ResultTool.success(bugTaskService.queryBugTaskByBugTid(devTid));
    }

    // todo: 根据pid查询任务

    // todo: 根据uid查询任务

    // todo: 根据uid和完成情况查询任务

    // todo: 根据pid和完成情况查询任务

    // todo: 更改任务信息

    // todo: 更改任务状态（待处理/进行中/已完成）

}
