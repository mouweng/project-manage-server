package zju.cst.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: wengyifan
 * @description:
 * @date: 2021/3/5 4:55 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProTestTaskUser implements Serializable{
    // 自增id
    private Integer id;
    // 测试任务id
    private Integer testTid;
    // 用户id
    private Integer uid;
}
