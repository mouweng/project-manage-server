package zju.cst.project.service;

import zju.cst.project.entity.ProBugTask;
import zju.cst.project.entity.vo.CreateBugTaskVo;

import java.util.List;

public interface BugTaskService {
    int getBugTaskNum();

    List<ProBugTask> getTaskFinishedInAWeek();

    List<ProBugTask> getTaskCreatedInAWeek();

    ProBugTask createBugTask(CreateBugTaskVo createBugTaskVo);

    boolean createBugTaskUser(int DevTid, Integer devUid, Integer testUid);

    boolean deleteBugTask(Integer bugTid);

    boolean deleteBugTaskUser(Integer bugTid);

    ProBugTask queryBugTaskByBugTid(Integer bugTid);

    List<ProBugTask> queryBugTaskByDevTid(Integer devTid);

    List<ProBugTask> queryBugTaskByDevUid(Integer devUid);

    List<ProBugTask> queryBugTaskByTestUid(Integer devUid);

    List<ProBugTask> queryBugTaskByPid(Integer pid);

    boolean updateBugTaskStatus(Integer bugTid, Integer status);

//    List<ProBugTask> queryBugTaskByUidAndStatus(Integer uid, Integer status);

    List<ProBugTask> queryBugTaskByPidAndStatus(Integer pid, Integer status);

    boolean updateBugTask(CreateBugTaskVo createBugTaskVo);
}
