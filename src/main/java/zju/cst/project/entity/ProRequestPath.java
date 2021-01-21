package zju.cst.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: wengyifan
 * @Description: ProRequestPath JavaBean
 * @Date Create in 2021/1/21 7:30 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProRequestPath implements Serializable {
    //主键id
    private Integer id;
    //请求路径
    private String url;
    //路径描述
    private String description;
}
