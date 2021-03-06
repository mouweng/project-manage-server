package zju.cst.project.service;

import zju.cst.project.entity.ProFile;
import zju.cst.project.entity.ProUser;

import java.util.List;
import java.util.Map;

/**
 * @author: wengyifan
 * @description: FileService接口
 * @date: 2021/3/1 6:53 下午
 */
public interface FileService {
    int getFileNum();

    List<ProFile> getAllFiles();

    Integer getTotalDownloadTimes();

    int addFile(String fileName, String filePath, Integer pid, Integer uid, Long size, String suffix);

    boolean repeatUpload(String fileName, Integer pid);

    ProFile queryByIdAndPid(Integer id, Integer pid);

    void downloadTimeAdd(ProFile proFile);

    void deleteFile(Integer id);

    List<ProFile> getFilesByPid(Integer pid);
}
