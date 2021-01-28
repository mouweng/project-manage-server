package zju.cst.project.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import zju.cst.project.dao.UserDao;
import zju.cst.project.entity.ProUser;

import javax.annotation.Resource;

/**
 * @author: wengyifan
 * @description: 用户模块测试
 * @date: 2021/1/28 10:40 上午
 */
@SpringBootTest
public class UserTest {

    @Resource
    private UserDao userDao;

    @Test
    public void test1() {
        ProUser user = userDao.queryById(1);
        System.out.println(user);
    }

    @Test
    public void test2() {
        ProUser user = userDao.selectByName("zju2");
        System.out.println(user);
    }

}
