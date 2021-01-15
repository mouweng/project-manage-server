package zju.cst.project.service.impl;

import org.springframework.stereotype.Service;
import zju.cst.project.dao.PermissionDao;
import zju.cst.project.entity.ProPermission;
import zju.cst.project.service.PermissionService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private PermissionDao permissionDao;

    @Override
    public List<ProPermission> selectListByUser(Integer id) {
        return permissionDao.selectListByUser(id);
    }

    @Override
    public List<ProPermission> selectListByPath(String path) {
        return permissionDao.selectListByPath(path);
    }
}
