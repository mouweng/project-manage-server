package zju.cst.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zju.cst.project.common.entity.JsonResult;
import zju.cst.project.common.enums.ResultCode;
import zju.cst.project.common.utils.ResultTool;
import zju.cst.project.entity.vo.CreateDevTaskVo;
import zju.cst.project.service.DevTaskService;

/**
 * @author: wengyifan
 * @description: 任务视图层
 * @date: 2021/3/1 10:59 下午
 */
@RestController
public class TaskController {

    @Autowired
    DevTaskService devTaskService;

    // 添加任务
    // 传递参数：uid（用户id）, pid（项目id）, content（任务内容）, selfTest（自测内容）
    @PostMapping("/devTask/createDevTask")
    public JsonResult createDevTask(CreateDevTaskVo createDevTaskVo) {
        int devTid = devTaskService.createDevTask(createDevTaskVo);
        devTaskService.createDevTaskUser(devTid, createDevTaskVo.getUid());
        return ResultTool.success("任务添加成功");
    }


    // 删除任务
    // 传递参数：devTid（任务id）
    @GetMapping("/devTask/deleteDevTask/{devTid}")
    public JsonResult deleteDevTask(@PathVariable("devTid") Integer devTid) {
        boolean res1 = devTaskService.deleteDevTask(devTid);
        boolean res2 = devTaskService.deleteDevTaskUser(devTid);
        if (res1 && res2) return ResultTool.success("任务删除成功");
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

    // todo:根据uid和完成情况查询任务

    // todo:根据pid和完成情况查询任务

    // todo:更改任务信息


    // 更改任务状态（待处理/进行中/已完成）
    // 传递参数：devTid（任务id），status（任务状态，1：待处理/2：进行中/3：已完成）
    @GetMapping("/devTask/updateDevTaskStatus/{devTid}/{status}")
    public JsonResult updateDevTaskStatus(@PathVariable("devTid") Integer devTid, @PathVariable("status") Integer status) {
        boolean res = devTaskService.updateDevTaskStatus(devTid, status);
        if(res) return ResultTool.success("状态修改成功");
        else return ResultTool.fail(ResultCode.COMMON_FAIL);
    }

}
