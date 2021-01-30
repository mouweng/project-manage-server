package zju.cst.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: wengyifan
 * @description:
 * @date: 2021/1/29 10:28 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProUserRole implements Serializable {
    // 自增id
    private Integer id;
    // 用户id
    private Integer userId;
    // 角色id
    private Integer roleId;
}
