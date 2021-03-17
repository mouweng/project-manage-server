package zju.cst.project.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import zju.cst.project.entity.ProDevTask;
import zju.cst.project.entity.ProProject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: wengyifan
 * @description:
 * @date: 2021/3/17 9:35 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnProjectVo extends ProProject implements Serializable {
    List<ProDevTask> devTasks;
    public ReturnProjectVo(ProProject project, ProDevTask d1, ProDevTask d2, ProDevTask d3) {
        super.setId(project.getId());
        super.setName(project.getName());
        super.setDescription(project.getDescription());
        super.setGmtCreate(project.getGmtCreate());
        super.setGmtUpdate(project.getGmtUpdate());
        devTasks = new ArrayList<>();
        devTasks.add(d1);
        devTasks.add(d2);
        devTasks.add(d3);
    }
}
