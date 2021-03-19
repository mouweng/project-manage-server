package zju.cst.project.service.impl;

import org.springframework.stereotype.Service;
import zju.cst.project.common.enums.EventType;
import zju.cst.project.dao.*;
import zju.cst.project.entity.ProBugTask;
import zju.cst.project.entity.ProDevTask;
import zju.cst.project.entity.ProEvent;
import zju.cst.project.service.EventService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author xushifeng
 * @description
 * @date 2021/3/16 5:35 下午
 */
@Service
public class EventServicelmpl implements EventService {
    @Resource
    EventDao eventDao;
    @Resource
    UserDao userDao;
    @Resource
    ProjectDao projectDao;
    @Resource
    DevTaskDao devTaskDao;
    @Resource
    DevTaskUserDao devTaskUserDao;
    @Resource
    BugTaskDao bugTaskDao;
    @Resource
    BugTaskUserDao bugTaskUserDao;
    @Resource
    TestTaskDao testTaskDao;
    @Resource
    TestTaskUserDao testTaskUserDao;

    @Override
    public boolean createEvent(ProEvent proEvent){
        int uid = proEvent.getUserId();
        String userName = userDao.queryById(uid).getUserName();
        proEvent.setUserName(userName);
        int pid = proEvent.getProjectId();
        String projectName = projectDao.queryById(pid).getName();
        proEvent.setProjectName(projectName);
        int taskType = proEvent.getTaskType();
        String taskTypeStr;
        if(taskType == 0)
            taskTypeStr = "开发任务";
        else if(taskType == 1)
            taskTypeStr = "测试任务";
        else
            taskTypeStr = "Bug任务";
        String content = String.format("用户 %s 在项目 %s 中创建了 %s %s", userName, projectName, taskTypeStr, proEvent.getContent());
        proEvent.setContent(content);
        return eventDao.insert(proEvent) > 0;
    }

    @Override
    public boolean createEvent(int taskId, int taskType, int eventType){
        ProEvent proEvent = new ProEvent();
        String action = EventType.getMessageByCode(eventType);
        if(taskType == 0) {
            ProDevTask proDevTask = devTaskDao.queryById(taskId);
            int pid = proDevTask.getProjectId();
            String projectName = projectDao.queryById(pid).getName();
            int uid = devTaskUserDao.queryById(taskId).getUid();
            String userName = userDao.queryById(uid).getUserName();
            String taskName = proDevTask.getName();
            String content = String.format("用户 %s 在项目 %s 中%s了开发任务 %s", userName, projectName, action, taskName);
            proEvent.setProjectId(pid);
            proEvent.setProjectName(projectName);
            proEvent.setUserName(userName);
            proEvent.setUserId(uid);
            proEvent.setTaskId(proDevTask.getId());
            proEvent.setProjectId(proDevTask.getProjectId());
            proEvent.setContent(content);
        }
        else if(taskType == 1) ;
        else{
            ProBugTask proBugTask = bugTaskDao.queryById(taskId);
            int pid = proBugTask.getProjectId();
            String projectName = projectDao.queryById(pid).getName();
            int uid = bugTaskUserDao.queryById(taskId).getTestUid();
            String userName = userDao.queryById(uid).getUserName();
            String taskName = proBugTask.getContent();
            String content = String.format("用户 %s 在项目 %s 中%s了Bug任务 %s", userName, projectName, action, taskName);
            proEvent.setId(pid);
            proEvent.setProjectName(projectName);
            proEvent.setUserName(userName);
            proEvent.setUserId(uid);
            proEvent.setTaskId(proBugTask.getId());
            proEvent.setProjectId(proBugTask.getProjectId());
            proEvent.setContent(content);
        }
            proEvent.setTaskType(taskType);
            proEvent.setTime(new Date());
            proEvent.setEventType(eventType);
        return eventDao.insert(proEvent) > 0;
    }

    @Override
    public List<ProEvent> queryEventsByProjectId(int projectId){
        return eventDao.queryEventsByProjectId(projectId);
    }

    @Override
    public List<ProEvent> queryEventsByUserId(int userId){
        return eventDao.queryEventsByUserId(userId);
    }

}
