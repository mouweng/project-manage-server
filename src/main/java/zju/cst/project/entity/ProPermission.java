package zju.cst.project.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: wengyifan
 * @Description: ProPermission JavaBean
 * @Date Create in 2021/1/21 7:30 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProPermission implements Serializable {
    //主键id
    private Integer id;
    //权限code
    private String permissionCode;
    //权限名
    private String permissionName;
}
