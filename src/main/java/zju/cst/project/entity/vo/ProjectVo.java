package zju.cst.project.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: wengyifan
 * @description: ProjectVo
 * @date: 2021/2/28 10:34 上午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectVo implements Serializable {
    // 自增id
    private Integer id;
    // 项目名称
    private String name;
    // 项目描述
    private String description;
}
