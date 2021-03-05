package zju.cst.project.dao;

import org.mapstruct.Mapper;
import zju.cst.project.entity.ProFile;

import java.util.List;
import java.util.Map;

/**
 * @author: wengyifan
 * @description: File数据接口层
 * @date: 2021/1/28 2:43 下午
 */
@Mapper
public interface FileDao {
    /**
     * @return int
     * @throws
     * @author xushifeng
     * @description: 获得文件数量
     * @date 2021/3/4 1:25 下午
     */
    int getFileNum();

    /**
     * @return java.util.List<zju.cst.project.entity.ProFile>
     * @throws
     * @author xushifeng
     * @description: 获得所有的文件信息
     * @date 2021/3/4 3:22 下午
     */
    List<ProFile> getAllFiles();

    /**
     * @return int
     * @throws
     * @author xushifeng
     * @description
     * @date 2021/3/4 6:45 下午
     */
    Integer getTotalDownloadTimes();

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

    int queryByFileNameAndPid(String fileName, Integer pid);

    ProFile queryByIdAndPid(Integer id, Integer pid);
}
