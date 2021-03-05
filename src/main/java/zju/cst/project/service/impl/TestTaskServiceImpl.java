package zju.cst.project.service.impl;

import org.springframework.stereotype.Service;
import zju.cst.project.dao.TestTaskDao;
import zju.cst.project.dao.TestTaskUserDao;
import zju.cst.project.entity.ProDevTask;
import zju.cst.project.entity.ProTestTask;
import zju.cst.project.entity.ProTestTaskUser;
import zju.cst.project.entity.vo.CreateTestTaskVo;
import zju.cst.project.service.TestTaskService;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author: wengyifan
 * @description:
 * @date: 2021/3/5 4:20 下午
 */
@Service
public class TestTaskServiceImpl implements TestTaskService {


    @Resource
    TestTaskDao testTaskDao;
    @Resource
    TestTaskUserDao testTaskUserDao;

    @Override
    public int createTestTask(CreateTestTaskVo createTestTaskVo) {
        ProTestTask proTestTask = new ProTestTask();
        proTestTask.setDevTid(createTestTaskVo.getDevTid());
        proTestTask.setTestCase(createTestTaskVo.getTestCase());
        proTestTask.setTestResults(createTestTaskVo.getTestResults());
        proTestTask.setProjectId(createTestTaskVo.getPid());
        proTestTask.setStatus(1);
        proTestTask.setFinished(0);
        proTestTask.setGmtCreate(new Date());
        proTestTask.setGmtUpdate(new Date());
        testTaskDao.insert(proTestTask);
        return proTestTask.getId();
    }

    @Override
    public boolean createTestTaskUser(int testTid, Integer uid) {
        ProTestTaskUser proTestTaskUser = new ProTestTaskUser();
        proTestTaskUser.setTestTid(testTid);
        proTestTaskUser.setUid(uid);
        return testTaskUserDao.insert(proTestTaskUser) > 0 ? true : false;
    }

    @Override
    public boolean deleteTestTask(Integer testTid) {
        return testTaskDao.deleteById(testTid) > 0 ? true : false;
    }

    @Override
    public boolean deleteTestTaskUser(Integer testTid) {
        return testTaskUserDao.deleteByTestTid(testTid) > 0 ? true : false;
    }
}
