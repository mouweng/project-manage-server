package zju.cst.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import zju.cst.project.common.entity.JsonResult;
import zju.cst.project.common.enums.ResultCode;
import zju.cst.project.common.utils.ResultTool;
import zju.cst.project.entity.vo.CreateDevTaskVo;
import zju.cst.project.entity.vo.CreateTestTaskVo;
import zju.cst.project.service.TestTaskService;

/**
 * @author: wengyifan
 * @description:
 * @date: 2021/3/3 8:53 上午
 */
@RestController
public class TestTaskController {

    @Autowired
    TestTaskService testTaskService;

    // todo: 添加任务
    // 传递参数：devTid(开发任务id), uid（用户id）, pid（项目id）, testCase（测试用例）, testResults（测试结果）
    @PostMapping("/testTask/createTestTask")
    public JsonResult createTestTask(CreateTestTaskVo createTestTaskVo) {
        int testTid = testTaskService.createTestTask(createTestTaskVo);
        testTaskService.createTestTaskUser(testTid, createTestTaskVo.getUid());
        return ResultTool.success("任务添加成功");
    }

    // 删除任务
    // 传递参数：testTid（任务id）
    @GetMapping("/testTask/deleteTestTask/{testTid}")
    public JsonResult deleteTestTask(@PathVariable("testTid") Integer testTid) {
        boolean res1 = testTaskService.deleteTestTask(testTid);
        boolean res2 = testTaskService.deleteTestTaskUser(testTid);
        if (res1 && res2) return ResultTool.success("任务删除成功");
        else return ResultTool.fail(ResultCode.COMMON_FAIL);
    }

    // 将任务添加给用户
    // 传递参数：testTid（任务id）, uid（用户id）
    @GetMapping("/testTask/addTestTaskUser/{testTid}/{uid}")
    public JsonResult addTestTaskUser(@PathVariable("testTid") Integer testTid, @PathVariable("uid") Integer uid) {
        boolean res = testTaskService.createTestTaskUser(testTid, uid);
        if(res) return ResultTool.success("成功将任务添加给用户");
        else return ResultTool.fail(ResultCode.COMMON_FAIL);
    }

    // todo: 根据testTid查询任务

    // todo: 根据devTid查询任务

    // todo: 根据pid查询任务

    // todo: 根据uid查询任务

    // todo: 根据uid和完成情况查询任务

    // todo: 根据pid和完成情况查询任务


    // todo: 更改任务信息

    // todo: 更改任务状态（待处理/进行中/已完成）

}
