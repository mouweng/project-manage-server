package zju.cst.project.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import zju.cst.project.dao.UserDao;
import zju.cst.project.entity.ProUser;

import javax.annotation.Resource;
import java.util.List;

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

    @Test
    public void test3() {
        List<ProUser> proUsers1 = userDao.queryUserByPidAndType(1,1);
        List<ProUser> proUsers2 = userDao.queryUserByPidAndType(1, 2);
        List<ProUser> proUsers3 = userDao.queryUserByPidAndType(1, 3);
        for (ProUser proUser : proUsers1) {
            System.out.println(proUser);
        }
        for (ProUser proUser : proUsers2) {
            System.out.println(proUser);
        }
        for (ProUser proUser : proUsers3) {
            System.out.println(proUser);
        }
    }
}
