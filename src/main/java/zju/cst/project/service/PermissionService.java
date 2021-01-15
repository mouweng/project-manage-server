package zju.cst.project.service;

import zju.cst.project.entity.ProPermission;

import java.util.List;

public interface PermissionService {

    /**
     * 查询用户的权限列表
     *
     * @param id
     * @return
     */
    List<ProPermission> selectListByUser(Integer id);

    /**
     * 查询具体某个接口的权限
     *
     * @param path
     * @return
     */
    List<ProPermission> selectListByPath(String path);
}
