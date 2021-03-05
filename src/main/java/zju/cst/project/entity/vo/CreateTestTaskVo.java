package zju.cst.project.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: wengyifan
 * @description:
 * @date: 2021/3/5 4:23 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTestTaskVo implements Serializable {
    private Integer testTid;
    // 任务自增id
    private Integer devTid;
    // uid
    private Integer uid;
    // pid
    private Integer pid;
    // 测试用例
    private String testCase;
    // 测试结果
    private String testResults;
}
