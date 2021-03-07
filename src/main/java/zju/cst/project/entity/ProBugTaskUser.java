package zju.cst.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProBugTaskUser implements Serializable {
    // 自增id
    private Integer id;
    // bug任务id
    private Integer bugTid;
    // 用户id
    private Integer devUid;
    private Integer testUid;
}

