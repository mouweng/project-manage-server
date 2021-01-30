package zju.cst.project.dao;

import org.mapstruct.Mapper;
import zju.cst.project.entity.ProUserRole;

/**
 * @author: wengyifan
 * @description: 用户-角色表 数据接口层
 * @date: 2021/1/29 10:31 下午
 */
@Mapper
public interface UserRoleDao {

    /**
     * @param uid
     * @return {@link ProUserRole}
     * @throws
     * @author: wengyifan
     * @description: 通过uid查询用户角色表信息
     * @date: 2021/1/29 10:37 下午
     */
    ProUserRole selectByUid(Integer uid);

    /**
     * @param proUserRole
     * @return {@link int}
     * @throws
     * @author: wengyifan
     * @description: 更新数据
     * @date: 2021/1/29 10:37 下午
     */
    int update(ProUserRole proUserRole);

    /**
     * @param proUserRole
     * @return {@link int}
     * @throws
     * @author: wengyifan
     * @description: 插入数据
     * @date: 2021/1/29 10:38 下午
     */
    int insert(ProUserRole proUserRole);

    /**
     * @param uid
     * @return {@link int}
     * @throws
     * @author: wengyifan
     * @description: 通过uid删除数据
     * @date: 2021/1/29 10:38 下午
     */
    int deleteByUid(Integer uid);
}
