package zju.cst.project.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: wengyifan
 * @description: ProProject JavaBean
 * @date: 2021/1/28 10:53 上午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProProject implements Serializable {
    // 自增id
    private Integer id;
    // 项目名称
    private String name;
    // 项目描述
    private String description;
    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;
    //修改时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtUpdate;

}
