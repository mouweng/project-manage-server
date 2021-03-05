package zju.cst.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskStatistics implements Serializable {
    //任务总数
    private Integer totalTaskNum;
    //开发任务数量
    private Integer devTaskNum;
    //测试任务数量
    private Integer testTaskNum;
    //bug任务数量
    private Integer bugTaskNum;
}
