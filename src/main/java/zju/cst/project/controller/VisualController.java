package zju.cst.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import zju.cst.project.common.entity.JsonResult;
import zju.cst.project.common.utils.ResultTool;
import zju.cst.project.service.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: wengyifan
 * @description:
 * @date: 2021/3/3 8:55 上午
 */
@RestController
public class VisualController {
    @Autowired
    ProjectService projectService;
    @Autowired
    FileService fileService;
    @Autowired
    UserService userService;
    @Autowired
    DevTaskService devTaskService;
    @Autowired
    BugTaskService bugTaskService;
    // 统计项目个数
    /**
     * @return int
     * @throws
     * @author xushifeng
     * @description
     * @date 2021/3/4 1:06 下午
     */
    @GetMapping("/visual/getProjectNum")
    public JsonResult getProjectNum(){
        return ResultTool.success(projectService.getProjectNum());
    }

    // 统计文件个数
    @GetMapping("/visual/getFileNum")
    public JsonResult getFileNum(){
        return ResultTool.success(fileService.getFileNum());
    }

    // 统计任务个数
    @GetMapping("/visual/getTaskNum")
    public JsonResult getTaskNum(){
        int devTaskNum = devTaskService.getDevTaskNum();
        int testTaskNum = 0;
        int bugTaskNum = bugTaskService.getBugTaskNum();
        int totalTaskNum = devTaskNum + testTaskNum + bugTaskNum;
        Map map = new HashMap();
        map.put("devTaskNum", devTaskNum);
        map.put("testTaskNum", testTaskNum);
        map.put("bugTaskNum", bugTaskNum);
        map.put("totalTaskNum", totalTaskNum);
        return ResultTool.success(map);
    }

    // 统计用户个数
    @GetMapping("/visual/getUserNum")
    public JsonResult getUserNum(){
        return ResultTool.success(userService.getUserNum());
    }

    // 统计文件下载次数
    @GetMapping("/visual/getTotalDownloadTimes")
    public JsonResult getTotalDownloadTimes(){
        return ResultTool.success(fileService.getTotalDownloadTimes());
    }

    // 统计近7天任务创建情况
    @GetMapping("/visual/getTaskCreatedInAWeek")
    public JsonResult getTaskCreatedInAWeek(){
        Map map = new HashMap();
        map.put("devTask", devTaskService.getTaskCreatedInAWeek());
        map.put("bugTask", bugTaskService.getTaskCreatedInAWeek());
        return ResultTool.success(map);
    }

    // 统计近7天任务完成情况
    @GetMapping("/visual/getTaskFinishedInAWeek")
    public JsonResult getTaskFinishedInAWeek(){
        Map map = new HashMap();
        map.put("devTask", devTaskService.getTaskFinishedInAWeek());
        map.put("bugTask", bugTaskService.getTaskFinishedInAWeek());
        return ResultTool.success(map);
    }
}
