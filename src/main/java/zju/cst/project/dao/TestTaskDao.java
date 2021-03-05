package zju.cst.project.dao;

import org.mapstruct.Mapper;
import zju.cst.project.entity.ProTestTask;

/**
 * @author: wengyifan
 * @description:
 * @date: 2021/3/5 4:44 下午
 */
@Mapper
public interface TestTaskDao {

    int insert(ProTestTask proTestTask);

    int deleteById(Integer testTid);
}
