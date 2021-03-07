package zju.cst.project.dao;

import org.mapstruct.Mapper;
import zju.cst.project.entity.ProBugTask;

import java.util.List;

@Mapper
public interface BugTaskDao {

    int getBugTaskNum();

    List<ProBugTask> getTaskCreatedInAWeek();

    List<ProBugTask> getTaskFinishedInAWeek();

    ProBugTask queryById(Integer id);

    List<ProBugTask> queryByDevTid(Integer devTid);

    List<ProBugTask> queryByPid(Integer pid);

    List<ProBugTask> queryByDevUid(Integer devUid);

    List<ProBugTask> queryByTestUid(Integer testUid);

    int update(ProBugTask proBugTask);

    int insert(ProBugTask proBugTask);

    int deleteById(Integer id);

    List<ProBugTask> queryBugTaskByUidAndStatus(Integer uid, Integer status);

    List<ProBugTask> queryBugTaskByPidAndStatus(Integer pid, Integer status);



}
