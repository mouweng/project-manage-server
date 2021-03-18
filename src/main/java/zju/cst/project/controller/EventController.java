package zju.cst.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import zju.cst.project.common.entity.JsonResult;
import zju.cst.project.common.utils.ResultTool;
import zju.cst.project.entity.ProEvent;
import zju.cst.project.service.EventService;

import java.util.List;

/**
 *
 * @author xushifeng
 * @description
 * @date 2021/3/16 5:42 下午
 */
@RestController
public class EventController {

    @Autowired
    EventService eventService;

    @GetMapping("/event/queryByProjectId/{projectId}")
    public JsonResult queryByProjectId(@PathVariable("projectId") Integer projectId){
        return ResultTool.success(eventService.queryEventsByProjectId(projectId));
    }

    @GetMapping("/event/queryByUserId/{userId}")
    public JsonResult queryByUserId(@PathVariable("userId") Integer userId){
        return ResultTool.success(eventService.queryEventsByUserId(userId));
    }
}
