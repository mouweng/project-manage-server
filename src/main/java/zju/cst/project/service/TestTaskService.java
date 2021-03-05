package zju.cst.project.service;

import zju.cst.project.entity.vo.CreateTestTaskVo;

/**
 * @author: wengyifan
 * @description:
 * @date: 2021/3/5 4:19 下午
 */
public interface TestTaskService {

    int createTestTask(CreateTestTaskVo createTestTaskVo);

    boolean createTestTaskUser(int devTid, Integer uid);

    boolean deleteTestTask(Integer testTid);

    boolean deleteTestTaskUser(Integer testTid);
}
