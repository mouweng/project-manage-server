package zju.cst.project.service.impl;

import org.springframework.stereotype.Service;
import zju.cst.project.dao.EventDao;
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

    @Override
    public List<ProEvent> queryEventsByProjectId(int projectId){
        return eventDao.queryEventsByProjectId(projectId);
    }

    @Override
    public List<ProEvent> queryEventsByUserId(int userId){
        return eventDao.queryEventsByUserId(userId);
    }

}
