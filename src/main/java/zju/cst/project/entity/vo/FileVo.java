package zju.cst.project.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import zju.cst.project.entity.ProFile;
import zju.cst.project.entity.ProUser;

import java.io.Serializable;

/**
 * @author: wengyifan
 * @description:
 * @date: 2021/3/11 10:23 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileVo extends ProFile implements Serializable {
    private ProUser user;
    public FileVo(ProFile proFile, ProUser user) {
        super.setId(proFile.getId());
        super.setPid(proFile.getPid());
        super.setUid(proFile.getUid());
        super.setFilePath(proFile.getFilePath());
        super.setFileName(proFile.getFileName());
        super.setSize(proFile.getSize());
        super.setSuffix(proFile.getSuffix());
        super.setDownloadTimes(proFile.getDownloadTimes());
        super.setGmtCreate(proFile.getGmtCreate());
        super.setGmtUpdate(proFile.getGmtUpdate());
        setUser(user);
    }
}
