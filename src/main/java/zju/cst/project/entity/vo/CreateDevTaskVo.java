package zju.cst.project.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: wengyifan
 * @description:
 * @date: 2021/3/2 8:18 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDevTaskVo implements Serializable {
    // 任务自增id
    private Integer devTid;
    // uid
    private Integer uid;
    // pid
    private Integer pid;
    // 任务内容
    private String content;
    // 任务名字
    private String name;
    // 状态
    private Integer status;
}
