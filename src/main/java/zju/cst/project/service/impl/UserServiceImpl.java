package zju.cst.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import zju.cst.project.dao.UserDao;
import zju.cst.project.dao.UserRoleDao;
import zju.cst.project.entity.ProUser;
import zju.cst.project.entity.ProUserRole;
import zju.cst.project.entity.vo.UserVo;
import zju.cst.project.service.UserService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wengyifan
 * @Description: UserServiceImpl 实现类
 * @Date Create in 2021/1/21 7:30 下午
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Resource
    private UserRoleDao userRoleDao;


    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * @return int
     * @throws
     * @author xushifeng
     * @description: 获得用户总数
     * @date 2021/3/4 2:17 下午
     */
    @Override
    public int getUserNum(){
        return this.userDao.getUserNum();
    }

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
    public ProUser update(ProUser proUser, UserVo userVo) {

        // 除了Account意外，其他字段可以更改更新字段
        if (userVo.getUserName() != null && !userVo.getUserName().equals(""))
            proUser.setUserName(userVo.getUserName());
        if (userVo.getPassword() != null && !userVo.getPassword().equals(""))
            proUser.setPassword(passwordEncoder.encode(userVo.getPassword()));
        if (userVo.getDepartment() != null && !userVo.getDepartment().equals(""))
            proUser.setDepartment(userVo.getDepartment());
        if (userVo.getTelephone() != null && !userVo.getTelephone().equals(""))
            proUser.setTelephone(userVo.getTelephone());
        if (userVo.getPosition() != null && !userVo.getPosition().equals(""))
            proUser.setPosition(userVo.getPosition());

        this.userDao.update(proUser);
        return this.queryById(proUser.getId());
    }

    @Override
    public ProUser update(ProUser proUser) {
        this.userDao.update(proUser);
        return this.queryById(proUser.getId());
    }

    @Override
    public boolean createCommonUser(UserVo userVo, ProUser createUser) {
        // 创建用户
        ProUser user = new ProUser();
        user.setAccount(userVo.getAccount());
        user.setUserName(userVo.getUserName());
        user.setPassword(passwordEncoder.encode(userVo.getPassword()));
        if (userVo.getDepartment() != null && !userVo.getDepartment().equals(""))
            user.setDepartment(userVo.getDepartment());
        if (userVo.getTelephone() != null && !userVo.getTelephone().equals(""))
            user.setTelephone(userVo.getTelephone());
        if (userVo.getPosition() != null && !userVo.getPosition().equals(""))
            user.setPosition(userVo.getPosition());
        user.setLastLoginTime(new Date());
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setPhotoFile("mouweng.website:8888/group1/M00/00/00/rBW7KmBDf2iAF9TPAABQvsLNIEs480.jpg");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setCreateUser(createUser.getId());
        user.setUpdateUser(createUser.getId());
        userDao.insert(user);

        // 赋予角色
        int id = userDao.selectByName(user.getAccount()).getId();
        ProUserRole userRole = new ProUserRole();
        userRole.setUserId(id);
        userRole.setRoleId(userVo.getRole());
        userRoleDao.insert(userRole);

        return true;
    }

    /**
     * @param mUser
     * @param user
     * @return {@link boolean}
     * @throws
     * @author: wengyifan
     * @description: 比较两个User的权限大小
     * @date: 2021/1/30 9:30 上午
     */
    @Override
    public boolean compareRole(ProUser mUser, ProUser user) {
        ProUserRole ur1 = userRoleDao.selectByUid(mUser.getId());
        ProUserRole ur2 = userRoleDao.selectByUid(user.getId());
        if (ur1.getRoleId() < ur2.getRoleId()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param mUser
     * @param role
     * @return {@link boolean}
     * @throws
     * @author: wengyifan
     * @description: 比较mUser和权限role的大小
     * @date: 2021/1/30 9:30 上午
     */
    @Override
    public boolean compareRole(ProUser mUser, Integer role) {
        ProUserRole ur = userRoleDao.selectByUid(mUser.getId());
        if (ur.getRoleId() < role) return true;
        else return false;
    }

    @Override
    public boolean deleteUser(Integer id) {
        userDao.deleteByUid(id);
        userRoleDao.deleteByUid(id);
        return true;
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

    @Override
    public Map<Integer, List<ProUser>> queryUserByPid(Integer id) {
        Map<Integer, List<ProUser>> map = new HashMap<>();
        map.put(1, userDao.queryUserByPidAndType(id, 1));
        map.put(2, userDao.queryUserByPidAndType(id, 2));
        map.put(3, userDao.queryUserByPidAndType(id, 3));
        return map;
    }

    @Override
    public Integer queryUserRole(Integer id) {
        ProUserRole proUserRole = userRoleDao.selectByUid(id);
        return proUserRole.getRoleId();
    }
}
