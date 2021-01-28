package zju.cst.project.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import zju.cst.project.dao.DevTaskDao;
import zju.cst.project.entity.ProDevTask;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author: wengyifan
 * @description:
 * @date: 2021/1/28 7:31 下午
 */
@SpringBootTest
public class DevTaskTest {
    @Resource
    DevTaskDao devTaskDao;

    @Test
    public void test1() {
        ProDevTask proDevTask = devTaskDao.queryById(1);
        System.out.println(proDevTask);

        List<ProDevTask> proDevTasks = devTaskDao.queryByPid(1);
        System.out.println(proDevTasks);

        List<ProDevTask> proDevTasks1 = devTaskDao.queryByUid(3);
        System.out.println(proDevTasks1);
    }

    @Test
    public void test2() {
        ProDevTask proDevTask = devTaskDao.queryById(1);
        proDevTask.setProjectId(2);
        proDevTask.setContent("开发任务4");
        devTaskDao.insert(proDevTask);
    }

    @Test
    public void test3() {
        ProDevTask proDevTask = devTaskDao.queryById(4);
        proDevTask.setContent("文档任务1");
        proDevTask.setSelfTest("doc");
        proDevTask.setStatus(1);
        proDevTask.setFinished(1);
        proDevTask.setTestPass(1);
        proDevTask.setProjectId(3);
        proDevTask.setEstimatedStartTime(new Date());
        proDevTask.setEstimatedEndTime(new Date());
        proDevTask.setActualStartTime(new Date());
        proDevTask.setActualEndTime(new Date());
        proDevTask.setGmtCreate(new Date());
        proDevTask.setGmtUpdate(new Date());
        devTaskDao.update(proDevTask);
    }

    @Test
    public void test4() {
        devTaskDao.deleteById(5);
    }
}
