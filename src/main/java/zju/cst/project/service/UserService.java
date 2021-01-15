package zju.cst.project.service;

import zju.cst.project.entity.ProUser;

public interface UserService {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ProUser queryById(Integer id);

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    ProUser selectByName(String username);

    /**
     * 修改数据
     *
     * @param proUser 实例对象
     * @return 实例对象
     */
    ProUser update(ProUser proUser);
}
