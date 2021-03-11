package zju.cst.project.service.impl;

import org.springframework.stereotype.Service;
import zju.cst.project.dao.FileDao;
import zju.cst.project.entity.ProFile;
import zju.cst.project.service.FileService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: wengyifan
 * @description: FileService实现类
 * @date: 2021/3/1 6:54 下午
 */
@Service
public class FileServiceImpl implements FileService {
    @Resource
    FileDao fileDao;

    @Override
    public int getFileNum() {
        return fileDao.getFileNum();
    }

    @Override
    public List<ProFile> getAllFiles(){
        return fileDao.getAllFiles();
    }

    @Override
    public Integer getTotalDownloadTimes(){
        return fileDao.getTotalDownloadTimes();
    }

    @Override
    public int addFile(String fileName, String filePath, Integer pid, Integer uid, Long size, String suffix) {
        ProFile proFile = new ProFile();
        proFile.setFileName(fileName);
        proFile.setFilePath(filePath);
        proFile.setPid(pid);
        proFile.setUid(uid);
        proFile.setSize(size);
        proFile.setSuffix(suffix);
        proFile.setGmtCreate(new Date());
        proFile.setGmtUpdate(new Date());
        fileDao.insert(proFile);
        return proFile.getId();
    }

    @Override
    public boolean repeatUpload(String fileName, Integer pid) {
        if(fileDao.queryByFileNameAndPid(fileName, pid) > 0) return true;
        else return false;
    }

    @Override
    public ProFile queryByIdAndPid(Integer id, Integer pid) {
        return fileDao.queryByIdAndPid(id, pid);
    }

    @Override
    public void downloadTimeAdd(ProFile proFile) {
        proFile.setDownloadTimes(proFile.getDownloadTimes() + 1);
        proFile.setGmtUpdate(new Date());
        fileDao.update(proFile);
    }

    @Override
    public void deleteFile(Integer id) {
        fileDao.deleteById(id);
    }

    @Override
    public List<ProFile> getFilesByPid(Integer pid) {
        return fileDao.queryByPid(pid);
    }
}
