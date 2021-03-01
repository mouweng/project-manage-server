package zju.cst.project.service;

import zju.cst.project.entity.ProProject;
import zju.cst.project.entity.vo.ProjectVo;

import java.util.List;
import java.util.Map;

/**
 * @author: wengyifan
 * @description: ProjectService 接口
 * @date: 2021/2/27 9:29 下午
 */
public interface ProjectService {
    /**
     * @param
     * @return {@link ProProject}
     * @throws
     * @author: wengyifan
     * @description: 查询所有项目字段
     * @date: 2021/2/27 9:30 下午
     */
    List<ProProject> queryAll();

    /**
     * @param uid
     * @return {@link Map< Integer, List< ProProject>>}
     * @throws
     * @author: wengyifan
     * @description: 通过uid查询其项目信息
     * @date: 2021/2/28 9:57 上午
     */
    Map<Integer, List<ProProject>> queryByUid(Integer uid);

    /**
     * @param id
     * @return {@link ProProject}
     * @throws
     * @author: wengyifan
     * @description: 通过项目id查询项目信息
     * @date: 2021/2/28 9:57 上午
     */
    ProProject queryById(Integer id);

    /**
     * @param uid
     * @param pid
     * @return {@link boolean}
     * @throws
     * @author: wengyifan
     * @description: 根据uid和pid查询是否存在关系
     * @date: 2021/2/28 11:05 上午
     */
    boolean queryProjectUserByUidPid(Integer uid, Integer pid);

    /**
     * @param uid
     * @param pid
     * @return {@link boolean}
     * @throws
     * @author: wengyifan
     * @description: 根据uid和pid查询管理员是否存在关系
     * @date: 2021/2/28 11:05 上午
     */
    boolean queryProjectManagerUserByUidPid(Integer uid, Integer pid);

    /**
     * @param projectVo
     * @return {@link}
     * @throws
     * @author: wengyifan
     * @description: 修改项目信息
     * @date: 2021/2/28 11:06 上午
     */
    void modifyProject(ProjectVo projectVo);

    /**
     * @param pid
     * @param uid
     * @return {@link}
     * @throws
     * @author: wengyifan
     * @description: 项目添加用户
     * @date: 2021/2/28 2:13 下午
     */
    void addUser(Integer pid, Integer uid);

    /**
     * @param pid
     * @param uid
     * @return {@link}
     * @throws
     * @author: wengyifan
     * @description: 项目添加管理员用户
     * @date: 2021/2/28 2:13 下午
     */
    void addManagerUser(int pid, Integer uid);

    /**
     * @param pid
     * @param uid
     * @return {@link boolean}
     * @throws
     * @author: wengyifan
     * @description: 删除关联表中的用户信息
     * @date: 2021/2/28 3:05 下午
     */
    boolean deleteUser(Integer pid, Integer uid);

    int createProject(ProjectVo projectVo);

    void deleteProject(Integer id);
}