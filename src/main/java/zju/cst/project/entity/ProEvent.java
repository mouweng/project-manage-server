package zju.cst.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProEvent implements Serializable {
    // 自增id
    public Integer id;
    // 项目id
    public Integer projectId;
    // 项目名称
    public String projectName;
    // 用户id
    public String userId;
    // 用户名称
    public String userName;
    // 任务id
    public Integer taskId;
    // 任务类型
    public int taskType;
    // 事件类型
    public int eventType;
    // 事件内容
    public String content;
    // 事件发生时间
    public Date time;

}
