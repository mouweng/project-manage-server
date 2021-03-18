package zju.cst.project.service;

import zju.cst.project.entity.ProEvent;

import java.util.List;

/**
 * @author xushifeng
 * @description
 * @date 2021/3/16 5:30 下午
 */
public interface EventService{
    boolean createEvent(ProEvent proEvent);

    List<ProEvent> queryEventsByProjectId(int projectId);

    List<ProEvent> queryEventsByUserId(int userId);
}
