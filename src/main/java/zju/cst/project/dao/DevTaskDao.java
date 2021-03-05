package zju.cst.project.dao;

import org.mapstruct.Mapper;
import zju.cst.project.entity.ProDevTask;

import java.util.List;

/**
 * @author: wengyifan
 * @description: DevTask 数据接口层
 * @date: 2021/1/28 4:37 下午
 */
@Mapper
public interface DevTaskDao {

    /**
     * @return int
     * @throws
     * @author xushifeng
     * @description
     * @date 2021/3/4 9:42 下午
     */
    int getDevTaskNum();

    /**
     * @return java.util.List<zju.cst.project.entity.ProDevTask>
     * @throws
     * @author xushifeng
     * @description： 统计近7天任务创建情况
     * @date 2021/3/5 3:32 下午
     */
    List<ProDevTask> getTaskCreatedInAWeek();

    /**
     * @return java.util.List<zju.cst.project.entity.ProDevTask>
     * @throws
     * @author xushifeng
     * @description: 统计近7天任务完成情况
     * @date 2021/3/5 3:32 下午
     */
    List<ProDevTask> getTaskFinishedInAWeek();

    /**
     * @param id
     * @return {@link ProDevTask}
     * @throws
     * @author: wengyifan
     * @description: 根据id查询开发任务
     * @date: 2021/1/28 8:25 下午
     */
    ProDevTask queryById(Integer id);

    /**
     * @param pid
     * @return {@link List< ProDevTask>}
     * @throws
     * @author: wengyifan
     * @description: 根据项目id查询开发任务
     * @date: 2021/1/28 8:26 下午
     */
    List<ProDevTask> queryByPid(Integer pid);

    /**
     * @param uid
     * @return {@link List< ProDevTask>}
     * @throws
     * @author: wengyifan
     * @description: 根据用户id查询开发任务
     * @date: 2021/1/28 8:26 下午
     */
    List<ProDevTask> queryByUid(Integer uid);

    /**
     * @param proDevTask
     * @return {@link int}
     * @throws
     * @author: wengyifan
     * @description: 更新开发任务
     * @date: 2021/1/28 8:26 下午
     */
    int update(ProDevTask proDevTask);

    /**
     * @param proDevTask
     * @return {@link int}
     * @throws
     * @author: wengyifan
     * @description: 插入开发任务
     * @date: 2021/1/28 8:27 下午
     */
    int insert(ProDevTask proDevTask);

    /**
     * @param id
     * @return {@link int}
     * @throws
     * @author: wengyifan
     * @description: 删除开发任务
     * @date: 2021/1/28 8:27 下午
     */
    int deleteById(Integer id);

    List<ProDevTask> queryDevTaskByUidAndStatus(Integer uid, Integer status);

    List<ProDevTask> queryDevTaskByPidAndStatus(Integer pid, Integer status);
}
