package zju.cst.project.dao;


import org.mapstruct.Mapper;
import zju.cst.project.entity.ProUser;

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
}
