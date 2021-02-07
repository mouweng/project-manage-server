package zju.cst.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: wengyifan
 * @description: 测试任务TestTask JavaBean
 * @date: 2021/1/28 4:25 下午
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProTestTask implements Serializable {
    // 自增id
    private Integer id;
    // 开发任务id（外键）
    private String devTid;
    // 任务所属项目
    private Integer projectId;
    // 测试案例
    private String testCase;
    // 测试结果
    private String testResults;
    // 任务状态(待处理/进行中/已完成)
    private Integer status;
    // 是否完成(1代表是/0代表否)
    private Integer finished;

    // 预计开始时间
    private Date estimatedStartTime;
    // 预计结束时间
    private Date estimatedEndTime;
    // 实际开始时间
    private Date actualStartTime;
    // 实际结束时间
    private Date actualEndTime;

    // 创建时间
    private Date gmtCreate;
    // 修改时间
    private Date gmtUpdate;
}
