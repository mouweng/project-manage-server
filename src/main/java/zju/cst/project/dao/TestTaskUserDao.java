package zju.cst.project.dao;

import org.mapstruct.Mapper;
import zju.cst.project.entity.ProTestTaskUser;

/**
 * @author: wengyifan
 * @description:
 * @date: 2021/3/5 4:45 下午
 */
@Mapper
public interface TestTaskUserDao {
    int insert(ProTestTaskUser proTestTaskUser);
}
