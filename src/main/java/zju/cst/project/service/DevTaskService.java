package zju.cst.project.service;

import zju.cst.project.entity.ProDevTask;
import zju.cst.project.entity.ProUser;
import zju.cst.project.entity.vo.CreateDevTaskVo;

import java.util.List;
import java.util.Map;

/**
 * @author: wengyifan
 * @description:
 * @date: 2021/3/2 8:23 下午
 */
public interface DevTaskService {

    int getDevTaskNum();

    List<ProDevTask> getTaskFinishedInAWeek();

    List<ProDevTask> getTaskCreatedInAWeek();

    int createDevTask(CreateDevTaskVo createDevTaskVo);

    boolean createDevTaskUser(int devTid, Integer uid);

    boolean deleteDevTask(Integer devTid);

    boolean deleteDevTaskUser(Integer devTid);

    ProDevTask queryDevTaskByDevTid(Integer devTid);

    List<ProDevTask> queryDevTaskByUid(Integer uid);

    List<ProDevTask> queryDevTaskByPid(Integer pid);

    boolean updateDevTaskStatus(Integer devTid, Integer status);

    List<ProDevTask> queryDevTaskByUidAndStatus(Integer uid, Integer status);

    List<ProDevTask> queryDevTaskByPidAndStatus(Integer pid, Integer status);

    boolean updateDevTask(CreateDevTaskVo createDevTaskVo);

    boolean updateDevTaskFinished(Integer devTid, Integer finished);

    List<ProUser> setDevTaskUsers(Integer pid, List<Integer> userIds);

    Map<String, Integer> getUserDevTaskNum();
}
