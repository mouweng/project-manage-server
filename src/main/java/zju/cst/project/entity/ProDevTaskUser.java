package zju.cst.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: wengyifan
 * @description: DevTaskUser DevTask和User的关联表JavaBean
 * @date: 2021/1/28 8:17 下午
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProDevTaskUser implements Serializable {
    // 自增id
    private Integer id;
    // 开发任务id
    private Integer devTid;
    // 用户id
    private Integer uid;
}
