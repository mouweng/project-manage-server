package zju.cst.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zju.cst.project.dao.UserDao;
import zju.cst.project.entity.ProUser;
import zju.cst.project.service.UserService;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public ProUser queryById(Integer id) {
        return this.userDao.queryById(id);
    }

    @Override
    public ProUser selectByName(String username) {
        return userDao.selectByName(username);
    }

    @Override
    public ProUser update(ProUser proUser) {
        this.userDao.update(proUser);
        return this.queryById(proUser.getId());
    }
}
