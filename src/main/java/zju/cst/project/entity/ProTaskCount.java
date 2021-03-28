package zju.cst.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author xushifeng
 * @description
 * @date 2021/3/27 2:04 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProTaskCount implements Serializable {
    //项目名称
    private String name;
    //任务数量
    private Integer taskCount;
}
