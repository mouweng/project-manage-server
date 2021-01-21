package zju.cst.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: wengyifan
 * @Description: ProRequestPathPermission JavaBean
 * @Date Create in 2021/1/21 7:30 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProRequestPathPermission implements Serializable {
    //主键id
    private Integer id;
    //请求路径id
    private Integer urlId;
    //权限id
    private Integer permissionId;
}
