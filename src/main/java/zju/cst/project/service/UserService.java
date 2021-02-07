package zju.cst.project.service;

import zju.cst.project.entity.ProUser;

import java.util.List;

/**
 * @Author: wengyifan
 * @Description: UserService 接口
 * @Date Create in 2021/1/21 7:30 下午
 */
public interface UserService {
    /**
     * @param id
     * @return {@link ProUser}
     * @throws
     * @author: wengyifan
     * @description: 通过ID查询单条数据
     * @date: 2021/1/21 8:03 下午
     */
    ProUser queryById(Integer id);

    /**
     * @param username
     * @return {@link ProUser}
     * @throws
     * @author: wengyifan
     * @description: 根据用户名查询用户
     * @date: 2021/1/21 8:04 下午
     */
    ProUser selectByName(String username);

    /**
     * @param proUser
     * @return {@link ProUser}
     * @throws
     * @author: wengyifan
     * @description: 修改数据
     * @date: 2021/1/21 8:04 下午
     */
    ProUser update(ProUser proUser);

    /**
     * @return {@link List <ProUser>}
     * @description: 获取数据库中所有的用户
     * @author: Huachang Yu
     */
    List<ProUser> getAllUsers();
}
