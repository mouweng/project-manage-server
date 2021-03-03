package zju.cst.project.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: wengyifan
 * @description:ProFile JavaBean
 * @date: 2021/1/28 2:37 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProFile implements Serializable {
    // 自增id
    private Integer id;
    // 项目id
    private Integer pid;
    // 上传者uid
    private Integer uid;
    // 文件路径
    private String filePath;
    // 文件名
    private String fileName;
    // 文件后缀
    private String suffix;
    // 下载次数
    private Integer downloadTimes;
    // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;
    // 修改时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtUpdate;
}
