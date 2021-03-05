package zju.cst.project.service;

import zju.cst.project.entity.ProUser;
import zju.cst.project.entity.vo.UserVo;

import java.util.List;
import java.util.Map;

/**
 * @Author: wengyifan
 * @Description: UserService 接口
 * @Date Create in 2021/1/21 7:30 下午
 */
public interface UserService {
    /**
     * @return int
     * @throws
     * @author xushifeng
     * @description: 获得用户总数
     * @date 2021/3/4 2:15 下午
     */
    int getUserNum();

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
     * @param userVo
     * @return {@link ProUser}
     * @throws
     * @author: wengyifan
     * @description: 修改数据
     * @date: 2021/1/21 8:04 下午
     */
    ProUser update(ProUser proUser, UserVo userVo);

    /**
     * @param proUser
     * @return {@link ProUser}
     * @throws
     * @author: wengyifan
     * @description: 修改数据
     * @date: 2021/1/29 11:11 下午
     */
    ProUser update(ProUser proUser);


    /**
     * @param userVo
     * @param createUser
     * @return {@link boolean}
     * @throws
     * @author: wengyifan
     * @description: 创建普通用户
     * @date: 2021/1/29 9:44 下午
     */
    boolean createCommonUser(UserVo userVo, ProUser createUser);
    /**
     * @param mUser
     * @param user
     * @return {@link boolean}
     * @throws
     * @author: wengyifan
     * @description: 判断权限
     * @date: 2021/1/30 10:15 上午
     */
    boolean compareRole(ProUser mUser, ProUser user);

    /**
     * @param mUser
     * @param role
     * @return {@link boolean}
     * @throws
     * @author: wengyifan
     * @description: 判断权限2
     * @date: 2021/1/30 10:16 上午
     */
    boolean compareRole(ProUser mUser, Integer role);

    /**
     * @param id
     * @return {@link boolean}
     * @throws
     * @author: wengyifan
     * @description: 删除用户
     * @date: 2021/1/30 10:37 上午
     */
    boolean deleteUser(Integer id);

    /**
     * @return {@link List <ProUser>}
     * @description: 获取数据库中所有的用户
     * @author: Huachang Yu
     */
    List<ProUser> getAllUsers();


    Map<Integer, List<ProUser>> queryUserByPid(Integer id);
}
