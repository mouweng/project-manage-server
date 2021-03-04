package zju.cst.project.common.utils;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author: wengyifan
 * @description:
 * @date: 2021/3/4 5:02 下午
 */
@Component
public class FileDfsUtil {
    @Resource
    private FastFileStorageClient storageClient ;

    /**
     * 上传文件
     */
    public String upload(MultipartFile multipartFile) throws Exception{
        String originalFilename = multipartFile.getOriginalFilename().
                substring(multipartFile.getOriginalFilename().
                        lastIndexOf(".") + 1);
        // 如果上传的文件没有后缀，则将后缀设为none
        if (originalFilename.equals(multipartFile.getOriginalFilename())) originalFilename = "none";
        StorePath storePath = this.storageClient.uploadFile(multipartFile.getInputStream(), multipartFile.getSize(),originalFilename , null);
        return storePath.getFullPath() ;
    }

    /**
     * 删除文件
     */
    public void deleteFile(String fileUrl) {
        if (StringUtils.isEmpty(fileUrl)) {
            System.out.println("fileUrl == >>文件路径为空...");
            return;
        }
        try {
            StorePath storePath = StorePath.parseFromUrl(fileUrl);
            storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
