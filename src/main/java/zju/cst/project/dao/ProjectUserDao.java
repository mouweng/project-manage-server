package zju.cst.project.dao;

import org.mapstruct.Mapper;
import zju.cst.project.entity.ProProjectUser;

/**
 * @author: wengyifan
 * @description:
 * @date: 2021/2/28 11:17 上午
 */
@Mapper
public interface ProjectUserDao {
    /**
     * @param uid
     * @param pid
     * @return {@link Integer}
     * @throws
     * @author: wengyifan
     * @description: 根据pid和uid判断字段是否存在，返回字段长度
     * @date: 2021/2/28 2:19 下午
     */
    Integer queryProjectUserByUidPid(Integer uid, Integer pid);

    /**
     * @param proProjectUser
     * @return {@link}
     * @throws
     * @author: wengyifan
     * @description: 插入字段
     * @date: 2021/2/28 2:19 下午
     */
    void insert(ProProjectUser proProjectUser);

    Integer queryProjectManagerUserByUidPid(Integer uid, Integer pid);

    int delete(Integer pid, Integer uid);

    void deleteByPid(Integer pid);
}
