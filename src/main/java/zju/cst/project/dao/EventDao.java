package zju.cst.project.dao;

import org.mapstruct.Mapper;
import zju.cst.project.entity.ProEvent;

import java.util.List;

/**
 * @author xushifeng
 * @description
 * @date 2021/3/15 8:33 下午
 */
@Mapper
public interface EventDao {
    int insert(ProEvent proEvent);
    List<ProEvent> queryEventsByProjectId(int projectId);
    List<ProEvent> queryEventsByUserId(int userId);
}
