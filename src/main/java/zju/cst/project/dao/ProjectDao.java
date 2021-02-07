package zju.cst.project.dao;

import org.mapstruct.Mapper;
import zju.cst.project.entity.ProProject;

import java.util.List;

/**
 * @author: wengyifan
 * @description: Project数据接口层
 * @date: 2021/1/28 10:59 上午
 */
@Mapper
public interface ProjectDao {
    /**
     * @param
     * @return {@link List<ProProject>}
     * @throws
     * @author: wengyifan
     * @description: 查询所有项目
     * @date: 2021/1/28 11:04 上午
     */
    List<ProProject> queryAll();

    /**
     * @param id
     * @return {@link ProProject}
     * @throws
     * @author: wengyifan
     * @description: 根据id查询项目
     * @date: 2021/1/28 11:05 上午
     */
    ProProject queryById(Integer id);

    /**
     * @param uid
     * @return {@link List<ProProject>}
     * @throws
     * @author: wengyifan
     * @description: 根据项目经理id查询项目
     * @date: 2021/1/28 11:06 上午
     */
    List<ProProject> queryByManager(Integer uid);

    /***
     * @param uid
     * @return {@link List<ProProject>}
     * @throws
     * @author: wengyifan
     * @description: 根据测试组长id查询项目
     * @date: 2021/1/28 11:07 上午
     */
    List<ProProject> queryByTestLeader(Integer uid);


    /**
     * @param project
     * @return {@link int}
     * @throws
     * @author: wengyifan
     * @description: 更新项目信息
     * @date: 2021/1/28 11:08 上午
     */
    int update(ProProject project);

    /**
     * @param project
     * @return {@link int}
     * @throws
     * @author: wengyifan
     * @description: 创建项目
     * @date: 2021/1/28 11:08 上午
     */
    int insert(ProProject project);

    /**
     * @param id
     * @return {@link int}
     * @throws
     * @author: wengyifan
     * @description: 根据用户id删除项目
     * @date: 2021/1/28 11:08 上午
     */
    int deleteById(Integer id);
}
