package zju.cst.project.dao;


import org.mapstruct.Mapper;
import zju.cst.project.entity.ProUser;

import java.util.List;

/**
 * @Author: wengyifan
 * @Description: User数据接口层
 * @Date Create in 2021/1/21 7:30 下午
 */
@Mapper
public interface UserDao {
    /**
     * @param id
     * @return {@link ProUser}
     * @throws
     * @author: wengyifan
     * @description: 通过ID查询单条数据
     * @date: 2021/1/21 7:58 下午
     */
    ProUser queryById(Integer id);

    /**
     * @param username
     * @return {@link ProUser}
     * @throws
     * @author: wengyifan
     * @description: 根据用户名查询用户
     * @date: 2021/1/21 7:58 下午
     */
    ProUser selectByName(String username);

    /**
     * @param proUser
     * @return {@link int}
     * @throws
     * @author: wengyifan
     * @description: 更新数据
     * @date: 2021/1/21 7:58 下午
     */
    int update(ProUser proUser);

    /**
     * @param proUser
     * @return {@link int}
     * @throws
     * @author: wengyifan
     * @description: 创建用户
     * @date: 2021/1/29 9:57 下午
     */
    int insert(ProUser proUser);

    /**
     * @param uid
     * @return {@link int}
     * @throws
     * @author: wengyifan
     * @description: 根据uid删除用户
     * @date: 2021/1/30 10:38 上午
     */
    int deleteByUid(Integer uid);

     /**
      * @return {@link List<ProUser>}
      * @description: 获取数据库中所有的用户
      * @author: Huachang Yu
     */
    List<ProUser> getAllUsers();

    /**
     * @param id
     * @return {@link List< ProUser>}
     * @throws
     * @author: wengyifan
     * @description: 根据项目id和type类型（人员在项目里的位置）查询项目中的成员
     * @date: 2021/2/28 9:23 上午
     */
    List<ProUser> queryUserByPidAndType(Integer id, Integer type);
}
