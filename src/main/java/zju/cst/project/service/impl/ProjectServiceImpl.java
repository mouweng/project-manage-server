package zju.cst.project.service.impl;

import org.springframework.stereotype.Service;
import zju.cst.project.dao.PermissionDao;
import zju.cst.project.dao.ProjectDao;
import zju.cst.project.dao.ProjectUserDao;
import zju.cst.project.entity.ProProject;
import zju.cst.project.entity.ProProjectUser;
import zju.cst.project.entity.vo.ProjectVo;
import zju.cst.project.service.ProjectService;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author: wengyifan
 * @description:
 * @date: 2021/2/27 9:31 下午
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Resource
    private ProjectDao projectDao;

    @Resource
    private ProjectUserDao projectUserDao;


    /**
     * @param
     * @return {@link ProProject}
     * @throws
     * @author: wengyifan
     * @description: 查询所有方法实现
     * @date: 2021/2/27 9:32 下午
     */
    @Override
    public List<ProProject> queryAll() {
        return projectDao.queryAll();
    }

    /**
     * @param uid
     * @return {@link Map< Integer, List< ProProject>>}
     * @throws
     * @author: wengyifan
     * @description: 根据用户uid查询其所有的项目
     * @date: 2021/2/28 11:10 上午
     */
    @Override
    public Map<Integer, List<ProProject>> queryByUid(Integer uid) {
        Map<Integer, List<ProProject>> map = new HashMap<>();
        map.put(1, projectDao.queryProjectByUidAndType(uid, 1));
        map.put(2, projectDao.queryProjectByUidAndType(uid, 2));
        map.put(3, projectDao.queryProjectByUidAndType(uid, 3));
        return map;
    }

    @Override
    public List<ProProject> queryByUidAll(Integer uid) {
        List<ProProject> proProjects = new ArrayList<>();
        proProjects.addAll(projectDao.queryProjectByUidAndType(uid, 1));
        proProjects.addAll(projectDao.queryProjectByUidAndType(uid, 2));
        proProjects.addAll(projectDao.queryProjectByUidAndType(uid, 3));
        return proProjects;
    }

    /**
     * @param id
     * @return {@link ProProject}
     * @throws
     * @author: wengyifan
     * @description: 根据id查询项目信息
     * @date: 2021/2/28 11:10 上午
     */
    @Override
    public ProProject queryById(Integer id) {
        return projectDao.queryById(id);
    }

    /**
     * @param uid
     * @param pid
     * @return {@link boolean}
     * @throws
     * @author: wengyifan
     * @description: 根据uid和pid查询是否存在关系
     * @date: 2021/2/28 11:07 上午
     */
    @Override
    public boolean queryProjectUserByUidPid(Integer uid, Integer pid) {
        if (projectUserDao.queryProjectUserByUidPid(uid, pid) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean queryProjectManagerUserByUidPid(Integer uid, Integer pid) {
        if (projectUserDao.queryProjectManagerUserByUidPid(uid, pid) > 0) {
            return true;
        }
        return false;
    }

    /**
     * @param projectVo
     * @return {@link}
     * @throws
     * @author: wengyifan
     * @description: 修改项目信息
     * @date: 2021/2/28 11:08 上午
     */
    @Override
    public void modifyProject(ProjectVo projectVo) {
        ProProject proProject = new ProProject();
        proProject.setId(projectVo.getId());
        proProject.setName(projectVo.getName());
        proProject.setDescription(projectVo.getDescription());
        proProject.setGmtUpdate(new Date());
        projectDao.update(proProject);
    }

    /**
     * @param pid
     * @param uid
     * @return {@link}
     * @throws
     * @author: wengyifan
     * @description: 项目添加用户
     * @date: 2021/2/28 2:49 下午
     */
    @Override
    public void addUser(Integer pid, Integer uid) {
        ProProjectUser proProjectUser = new ProProjectUser();
        proProjectUser.setPid(pid);
        proProjectUser.setUid(uid);
        proProjectUser.setType(3);
        projectUserDao.insert(proProjectUser);
    }

    @Override
    public void addManagerUser(int pid, Integer uid) {
        ProProjectUser proProjectUser = new ProProjectUser();
        proProjectUser.setPid(pid);
        proProjectUser.setUid(uid);
        proProjectUser.setType(1);
        projectUserDao.insert(proProjectUser);
    }

    @Override
    public boolean deleteUser(Integer pid, Integer uid) {
        if(projectUserDao.delete(pid, uid) > 0) return true;
        else return false;
    }

    @Override
    public int createProject(ProjectVo projectVo) {
        ProProject proProject = new ProProject();
        proProject.setName(projectVo.getName());
        proProject.setDescription(projectVo.getDescription());
        proProject.setGmtCreate(new Date());
        proProject.setGmtUpdate(new Date());
        projectDao.insert(proProject);
        // 返回自增id
        return proProject.getId();
    }

    @Override
    public void deleteProject(Integer pid) {
        projectDao.deleteById(pid);
        projectUserDao.deleteByPid(pid);
    }
}


