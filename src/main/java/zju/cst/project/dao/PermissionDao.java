package zju.cst.project.dao;

import org.mapstruct.Mapper;
import zju.cst.project.entity.ProPermission;

import java.util.List;

@Mapper
public interface PermissionDao {
    /**
     * 查询用户的权限
     *
     * @param id
     * @return
     */
    List<ProPermission> selectListByUser(Integer id);

    /**
     * 查询具体某个接口的权限
     *
     * @param path 接口路径
     * @return
     */
    List<ProPermission> selectListByPath(String path);
}
