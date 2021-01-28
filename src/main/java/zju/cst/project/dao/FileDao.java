package zju.cst.project.dao;

import org.mapstruct.Mapper;
import zju.cst.project.entity.ProFile;

import java.util.List;

/**
 * @author: wengyifan
 * @description: File数据接口层
 * @date: 2021/1/28 2:43 下午
 */
@Mapper
public interface FileDao {
    /**
     * @param pid
     * @return {@link List< ProFile>}
     * @throws
     * @author: wengyifan
     * @description: 通过项目id查询文件
     * @date: 2021/1/28 4:13 下午
     */
    List<ProFile> queryByPid(Integer pid);

    /**
     * @param id
     * @return {@link ProFile}
     * @throws
     * @author: wengyifan
     * @description: 通过文件id查询文件
     * @date: 2021/1/28 4:13 下午
     */
    ProFile queryById(Integer id);

    /**
     * @param proFile
     * @return {@link int}
     * @throws
     * @author: wengyifan
     * @description: 更新文件信息
     * @date: 2021/1/28 4:13 下午
     */
    int update(ProFile proFile);

    /**
     * @param proFile
     * @return {@link int}
     * @throws
     * @author: wengyifan
     * @description:
     * @date: 2021/1/28 4:13 下午
     */
    int insert(ProFile proFile);

    /**
     * @param id
     * @return {@link int}
     * @throws
     * @author: wengyifan
     * @description: 根据id删除文件
     * @date: 2021/1/28 4:13 下午
     */
    int deleteById(Integer id);

    /**
     * @param pid
     * @return {@link int}
     * @throws
     * @author: wengyifan
     * @description: 根据项目id删除文件
     * @date: 2021/1/28 4:13 下午
     */
    int deleteByPid(Integer pid);
}
