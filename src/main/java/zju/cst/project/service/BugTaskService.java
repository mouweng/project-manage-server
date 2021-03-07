package zju.cst.project.service;

import zju.cst.project.entity.ProBugTask;
import zju.cst.project.entity.vo.CreateBugTaskVo;

import java.util.List;

public interface BugTaskService {
    int getBugTaskNum();

    List<ProBugTask> getTaskFinishedInAWeek();

    List<ProBugTask> getTaskCreatedInAWeek();

    int createBugTask(CreateBugTaskVo createBugTaskVo);

    boolean createBugTaskUser(int bugTid, Integer devUid, Integer testUid);

    boolean deleteBugTask(Integer bugTid);

    boolean deleteBugTaskUser(Integer bugTid);

    ProBugTask queryBugTaskByBugTid(Integer BugTid);

    List<ProBugTask> queryBugTaskByDevTid(Integer DevTid);

//    List<ProBugTask> queryBugTaskByUid(Integer uid);

//    List<ProBugTask> queryBugTaskByPid(Integer pid);
//
//    boolean updateBugTaskStatus(Integer BugTid, Integer status);
//
//    List<ProBugTask> queryBugTaskByUidAndStatus(Integer uid, Integer status);
//
//    List<ProBugTask> queryBugTaskByPidAndStatus(Integer pid, Integer status);
//
//    boolean updateBugTask(CreateBugTaskVo createBugTaskVo);
}
