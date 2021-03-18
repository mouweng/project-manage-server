package zju.cst.project.service.impl;

import org.springframework.stereotype.Service;
import zju.cst.project.dao.BugTaskUserDao;
import zju.cst.project.dao.EventDao;
import zju.cst.project.dao.ProjectDao;
import zju.cst.project.dao.UserDao;
import zju.cst.project.entity.ProEvent;
import zju.cst.project.service.EventService;

import javax.annotation.Resource;
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
    public List<ProEvent> queryEventsByProjectId(int projectId){
        return eventDao.queryEventsByProjectId(projectId);
    }

    @Override
    public List<ProEvent> queryEventsByUserId(int userId){
        return eventDao.queryEventsByUserId(userId);
    }

}
