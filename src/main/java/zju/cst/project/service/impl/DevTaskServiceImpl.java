package zju.cst.project.service.impl;

import org.springframework.stereotype.Service;
import zju.cst.project.dao.DevTaskDao;
import zju.cst.project.dao.DevTaskUserDao;
import zju.cst.project.entity.ProDevTask;
import zju.cst.project.entity.ProDevTaskUser;
import zju.cst.project.entity.vo.CreateDevTaskVo;
import zju.cst.project.service.DevTaskService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author: wengyifan
 * @description:
 * @date: 2021/3/2 8:23 下午
 */
@Service
public class DevTaskServiceImpl implements DevTaskService {

    @Resource
    DevTaskDao devTaskDao;
    @Resource
    DevTaskUserDao devTaskUserDao;

    @Override
    public int getDevTaskNum(){
        return devTaskDao.getDevTaskNum();
    }

    @Override
    public List<ProDevTask> getTaskCreatedInAWeek(){
        return devTaskDao.getTaskCreatedInAWeek();
    }

    @Override
    public List<ProDevTask> getTaskFinishedInAWeek(){
        return devTaskDao.getTaskFinishedInAWeek();
    }

    @Override
    public int createDevTask(CreateDevTaskVo createDevTaskVo) {
        ProDevTask proDevTask = new ProDevTask();
        proDevTask.setContent(createDevTaskVo.getContent());
        proDevTask.setName(createDevTaskVo.getName());
        proDevTask.setProjectId(createDevTaskVo.getPid());
        if (createDevTaskVo.getStatus() == null || createDevTaskVo.getStatus() <= 0 || createDevTaskVo.getStatus() > 3)
            proDevTask.setStatus(1);
        else proDevTask.setStatus(createDevTaskVo.getStatus());
        proDevTask.setFinished(0);// 暂时无用
        proDevTask.setTestPass(0);// 暂时无用
        proDevTask.setGmtCreate(new Date());
        proDevTask.setGmtUpdate(new Date());
        devTaskDao.insert(proDevTask);
        return proDevTask.getId();
    }

    @Override
    public boolean createDevTaskUser(int devTid, Integer uid) {
        ProDevTaskUser proDevTaskUser = new ProDevTaskUser();
        proDevTaskUser.setDevTid(devTid);
        proDevTaskUser.setUid(uid);
        return devTaskUserDao.insert(proDevTaskUser) > 0 ? true : false;
    }

    @Override
    public boolean deleteDevTask(Integer devTid) {
        return devTaskDao.deleteById(devTid) > 0 ? true : false;
    }

    @Override
    public boolean deleteDevTaskUser(Integer devTid) {
        return devTaskUserDao.deleteByDevTid(devTid) > 0 ? true : false;
    }

    @Override
    public ProDevTask queryDevTaskByDevTid(Integer devTid) {
        return devTaskDao.queryById(devTid);
    }

    @Override
    public List<ProDevTask> queryDevTaskByUid(Integer uid) {
        return devTaskDao.queryByUid(uid);
    }

    @Override
    public List<ProDevTask> queryDevTaskByPid(Integer pid) {
        return devTaskDao.queryByPid(pid);
    }

    @Override
    public boolean updateDevTaskStatus(Integer devTid, Integer status) {
        ProDevTask proDevTask = new ProDevTask();
        proDevTask.setId(devTid);
        if (status <= 3 && status >= 1) proDevTask.setStatus(status);
        else return false;
        proDevTask.setGmtUpdate(new Date());
        return devTaskDao.update(proDevTask) > 0 ? true : false;
    }

    @Override
    public List<ProDevTask> queryDevTaskByUidAndStatus(Integer uid, Integer status) {
        return devTaskDao.queryDevTaskByUidAndStatus(uid, status);
    }

    @Override
    public List<ProDevTask> queryDevTaskByPidAndStatus(Integer pid, Integer status) {
        return devTaskDao.queryDevTaskByPidAndStatus(pid, status);
    }

    @Override
    public boolean updateDevTask(CreateDevTaskVo createDevTaskVo) {
        ProDevTask proDevTask = new ProDevTask();
        proDevTask.setId(createDevTaskVo.getDevTid());
        proDevTask.setContent(createDevTaskVo.getContent());
        proDevTask.setName(createDevTaskVo.getName());
        proDevTask.setGmtUpdate(new Date());
        return devTaskDao.update(proDevTask) > 0 ? true : false;
    }
}
