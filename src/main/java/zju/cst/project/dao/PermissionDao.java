package zju.cst.project.dao;

import org.mapstruct.Mapper;
import org.springframework.cache.annotation.Cacheable;
import zju.cst.project.entity.ProPermission;

import java.util.List;

/**
 * @Author: wengyifan
 * @Description: Permission数据接口层
 * @Date Create in 2021/1/21 7:30 下午
 */
@Mapper
public interface PermissionDao {

    /**
     * @param id
     * @return {@link List< ProPermission>}
     * @throws
     * @author: wengyifan
     * @description: 查询用户的权限
     * @date: 2021/1/21 7:57 下午
     */
    @Cacheable(value = "permission", key = "'userList' + #id")
    List<ProPermission> selectListByUser(Integer id);


    /**
     * @param path
     * @return {@link List< ProPermission>}
     * @throws
     * @author: wengyifan
     * @description: 查询具体某个接口的权限
     * @date: 2021/1/21 7:57 下午
     */
    @Cacheable(value = "permission", key = "'path' + #path")
    List<ProPermission> selectListByPath(String path);
}
