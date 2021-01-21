package zju.cst.project.service.impl;

import org.springframework.stereotype.Service;
import zju.cst.project.dao.PermissionDao;
import zju.cst.project.entity.ProPermission;
import zju.cst.project.service.PermissionService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: wengyifan
 * @Description: PermissionServiceImpl 实现类
 * @Date Create in 2021/1/21 7:30 下午
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private PermissionDao permissionDao;

    /**
     * @param id
     * @return {@link List< ProPermission>}
     * @throws
     * @author: wengyifan
     * @description: 查询用户的权限列表
     * @date: 2021/1/21 8:03 下午
     */
    @Override
    public List<ProPermission> selectListByUser(Integer id) {
        return permissionDao.selectListByUser(id);
    }

    /**
     * @param path
     * @return {@link List< ProPermission>}
     * @throws
     * @author: wengyifan
     * @description: 查询具体某个接口的权限
     * @date: 2021/1/21 8:03 下午
     */
    @Override
    public List<ProPermission> selectListByPath(String path) {
        return permissionDao.selectListByPath(path);
    }
}
