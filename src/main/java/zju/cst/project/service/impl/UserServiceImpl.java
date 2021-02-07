package zju.cst.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zju.cst.project.dao.UserDao;
import zju.cst.project.entity.ProUser;
import zju.cst.project.service.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: wengyifan
 * @Description: UserServiceImpl 实现类
 * @Date Create in 2021/1/21 7:30 下午
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    /**
     * @param id
     * @return {@link ProUser}
     * @throws
     * @author: wengyifan
     * @description: 通过ID查询单条数据
     * @date: 2021/1/21 8:03 下午
     */
    @Override
    public ProUser queryById(Integer id) {
        return this.userDao.queryById(id);
    }

    /**
     * @param username
     * @return {@link ProUser}
     * @throws
     * @author: wengyifan
     * @description: 根据用户名查询用户
     * @date: 2021/1/21 8:04 下午
     */
    @Override
    public ProUser selectByName(String username) {
        return userDao.selectByName(username);
    }

    /**
     * @param proUser
     * @return {@link ProUser}
     * @throws
     * @author: wengyifan
     * @description: 修改数据
     * @date: 2021/1/21 8:04 下午
     */
    @Override
    public ProUser update(ProUser proUser) {
        this.userDao.update(proUser);
        return this.queryById(proUser.getId());
    }

    /**
     * @return {@link List <ProUser>}
     * @description: 获取数据库中所有的用户
     * @author: Huachang Yu
     */
    @Override
    public List<ProUser> getAllUsers() {
        return this.userDao.getAllUsers();
    }
}
