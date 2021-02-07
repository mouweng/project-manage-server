package zju.cst.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: wengyifan
 * @description: 开发任务DevTask JavaBean
 * @date: 2021/1/28 4:17 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProDevTask implements Serializable {
    // 自增id
    private Integer id;
    // 任务所属项目
    private Integer projectId;
    // 任务内容
    private String content;
    // 自测内容
    private String selfTest;
    // 任务状态(待处理/进行中/已完成)
    private Integer status;
    // 是否完成(1代表是/0代表否)
    private Integer finished;
    // 通过测试(1代表通过/0代表不通过)
    private Integer testPass;

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
