package zju.cst.project.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBugTaskVo implements Serializable {
    // 任务自增id
    private Integer bugTid;
    //
    private Integer devTid;
    // uid
    private Integer devUid;
    private Integer testUid;
    // pid
    private Integer pid;
    // 任务内容
    private String content;
}
