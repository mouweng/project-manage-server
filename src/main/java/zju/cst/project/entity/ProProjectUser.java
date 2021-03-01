package zju.cst.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: wengyifan
 * @description: ProProjectUser JavaBean
 * @date: 2021/2/28 2:15 下午
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProProjectUser implements Serializable {
    // 自增id
    private Integer id;
    // 项目id
    private Integer pid;
    // 用户id
    private Integer uid;
    // 类型
    private Integer type;
}
