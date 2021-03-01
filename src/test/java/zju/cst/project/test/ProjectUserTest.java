package zju.cst.project.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import zju.cst.project.dao.ProjectDao;
import zju.cst.project.dao.ProjectUserDao;
import zju.cst.project.entity.ProProject;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: wengyifan
 * @description:
 * @date: 2021/2/28 11:23 上午
 */
@SpringBootTest
public class ProjectUserTest {
    @Resource
    private ProjectUserDao projectUserDao;

    @Test
    public void test1() {
        Integer num = projectUserDao.queryProjectUserByUidPid(2, 1);
        System.out.println(num);
    }

    @Test
    public void test2() {
        Integer num = projectUserDao.delete(5, 3);
        System.out.println(num);
    }
}
