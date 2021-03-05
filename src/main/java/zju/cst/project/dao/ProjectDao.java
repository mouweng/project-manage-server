package zju.cst.project.dao;

import org.mapstruct.Mapper;
import zju.cst.project.entity.ProProject;

import java.util.Collection;
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

    /**
     * @param uid
     * @param type
     * @return {@link List< ProProject>}
     * @throws
     * @author: wengyifan
     * @description: 根据uid和类型查询用户参与的项目
     * @date: 2021/2/28 9:50 上午
     */
    List<ProProject> queryProjectByUidAndType(Integer uid, Integer type);

    /**
     *
     * @return {@link int}
     * @throws
     * @author: xushifeng
     * @description: 查询项目数量
     * @date: 2021/3/3 8:14 下午
     */
    int getProjectNum();
}
