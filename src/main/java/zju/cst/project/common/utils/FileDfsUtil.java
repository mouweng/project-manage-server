package zju.cst.project.common.utils;

import com.github.tobato.fastdfs.domain.fdfs.StorageNode;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.DefaultFastFileStorageClient;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * @author: wengyifan
 * @description:
 * @date: 2021/3/4 5:02 下午
 */
@Component
public class FileDfsUtil {
    @Resource
    private FastFileStorageClient storageClient;
//    private static Method getStorageNodeMethod;
//
//    static {
//        try {
//            Class<?> defaultFastFileStorageClientClass = DefaultFastFileStorageClient.class;
//            getStorageNodeMethod = defaultFastFileStorageClientClass.getDeclaredMethod("getStorageNode", String.class);
//            getStorageNodeMethod.setAccessible(true);
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
//    }

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
//        StorageNode storageNode = (StorageNode) getStorageNodeMethod.invoke(this.storageClient, storePath.getGroup());
//        String nodeIp = storageNode.getIp();
//        System.out.println(nodeIp);
//        return nodeIp + ":8888/" + storePath.getFullPath() ;
        return storePath.getFullPath();
    }

    /**
     * 删除文件
     */
    public boolean deleteFile(String fileUrl) {
        if (StringUtils.isEmpty(fileUrl)) {
            // System.out.println("fileUrl == >>文件路径为空...");
            return false;
        }
        StorePath storePath = StorePath.parseFromUrl(fileUrl);
        storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
        return true;
    }
}
