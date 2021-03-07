package zju.cst.project.service.impl;

import org.springframework.stereotype.Service;
import zju.cst.project.dao.BugTaskDao;
import zju.cst.project.dao.BugTaskUserDao;
import zju.cst.project.entity.ProBugTask;
import zju.cst.project.entity.ProBugTaskUser;
import zju.cst.project.entity.vo.CreateBugTaskVo;
import zju.cst.project.service.BugTaskService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class BugTaskServiceImpl implements BugTaskService {
    @Resource
    BugTaskDao bugTaskDao;
    @Resource
    BugTaskUserDao bugTaskUserDao;

    @Override
    public int getBugTaskNum(){
        return bugTaskDao.getBugTaskNum();
    }

    @Override
    public List<ProBugTask> getTaskFinishedInAWeek(){
        return bugTaskDao.getTaskFinishedInAWeek();
    }

    public List<ProBugTask> getTaskCreatedInAWeek(){
        return bugTaskDao.getTaskCreatedInAWeek();
    }

    public int createBugTask(CreateBugTaskVo createBugTaskVo){
        ProBugTask proBugTask = new ProBugTask();
        proBugTask.setDevTid(createBugTaskVo.getDevTid());
        proBugTask.setContent(createBugTaskVo.getContent());
        proBugTask.setProjectId(createBugTaskVo.getPid());
        proBugTask.setStatus(1);
        proBugTask.setFinished(0);// 暂时无用
        proBugTask.setGmtCreate(new Date());
        proBugTask.setGmtUpdate(new Date());
        bugTaskDao.insert(proBugTask);
        return proBugTask.getId();
    }

    @Override
    public boolean createBugTaskUser(int bugTid, Integer devUid, Integer testUid){
        ProBugTaskUser proBugTaskUser = new ProBugTaskUser();
        proBugTaskUser.setBugTid(bugTid);
        proBugTaskUser.setDevUid(devUid);
        proBugTaskUser.setTestUid(testUid);
        return bugTaskUserDao.insert(proBugTaskUser) > 0;
    }

    @Override
    public boolean deleteBugTask(Integer bugTid){
        return bugTaskDao.deleteById(bugTid) > 0;
    }

    @Override
    public boolean deleteBugTaskUser(Integer bugTid){
        return bugTaskUserDao.deleteByBugTid(bugTid) > 0;
    }

    @Override
    public ProBugTask queryBugTaskByBugTid(Integer bugTid) {
        return bugTaskDao.queryById(bugTid);
    }

    @Override
    public List<ProBugTask> queryBugTaskByDevTid(Integer devTid){
        return bugTaskDao.queryByDevTid(devTid);
    }

    @Override
    public List<ProBugTask> queryBugTaskByPid(Integer pid){
        return bugTaskDao.queryByPid(pid);
    }

    @Override
    public List<ProBugTask> queryBugTaskByDevUid(Integer devUid){
        return bugTaskDao.queryByDevUid(devUid);
    }

    @Override
    public List<ProBugTask> queryBugTaskByTestUid(Integer testUid){
        return bugTaskDao.queryByTestUid(testUid);
    }

    @Override
    public List<ProBugTask> queryBugTaskByPidAndStatus(Integer pid, Integer status){
        return bugTaskDao.queryBugTaskByPidAndStatus(pid, status);
    }

    @Override
    public boolean updateBugTask(CreateBugTaskVo createBugTaskVo){
        ProBugTask proBugTask = new ProBugTask();
        proBugTask.setId(createBugTaskVo.getBugTid());
        proBugTask.setDevTid(createBugTaskVo.getDevTid());
        proBugTask.setContent(createBugTaskVo.getContent());
        proBugTask.setGmtUpdate(new Date());
        return bugTaskDao.update(proBugTask) > 0;
    }

    @Override
    public boolean updateBugTaskStatus(Integer bugTid, Integer status){
        ProBugTask proBugTask = new ProBugTask();
        proBugTask.setId(bugTid);
        if (status <= 3 && status >= 1) proBugTask.setStatus(status);
        else return false;
        proBugTask.setGmtUpdate(new Date());
        return bugTaskDao.update(proBugTask) > 0;
    }
}
