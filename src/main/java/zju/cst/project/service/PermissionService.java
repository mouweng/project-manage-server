package zju.cst.project.service;

import zju.cst.project.entity.ProPermission;

import java.util.List;

/**
 * @Author: wengyifan
 * @Description: PermissionService 接口
 * @Date Create in 2021/1/21 7:30 下午
 */
public interface PermissionService {

    /**
     * @param id
     * @return {@link List< ProPermission>}
     * @throws
     * @author: wengyifan
     * @description: 查询用户的权限列表
     * @date: 2021/1/21 8:03 下午
     */
    List<ProPermission> selectListByUser(Integer id);

    /**
     * @param path
     * @return {@link List< ProPermission>}
     * @throws
     * @author: wengyifan
     * @description: 查询具体某个接口的权限
     * @date: 2021/1/21 8:03 下午
     */
    List<ProPermission> selectListByPath(String path);
}
