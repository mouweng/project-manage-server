package zju.cst.project.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import zju.cst.project.dao.DevTaskUserDao;
import zju.cst.project.entity.ProDevTaskUser;

import javax.annotation.Resource;

/**
 * @author: wengyifan
 * @description:
 * @date: 2021/1/28 8:45 下午
 */
@SpringBootTest
public class DevTaskUserTest {

    @Resource
    DevTaskUserDao devTaskUserDao;

    @Test
    public void test1() {
        ProDevTaskUser proDevTaskUser = devTaskUserDao.queryById(1);
        System.out.println(proDevTaskUser);

        ProDevTaskUser proDevTaskUser1 = devTaskUserDao.queryByDevTidAndUid(2, 2);
        System.out.println(proDevTaskUser1);
    }

    @Test
    public void test2() {
        ProDevTaskUser proDevTaskUser = devTaskUserDao.queryById(4);
        proDevTaskUser.setUid(1);
        devTaskUserDao.update(proDevTaskUser);
    }

    @Test
    public void test3() {
        ProDevTaskUser proDevTaskUser = devTaskUserDao.queryById(4);
        proDevTaskUser.setUid(3);
        proDevTaskUser.setDevTid(3);
        devTaskUserDao.insert(proDevTaskUser);
    }

    @Test
    public void test4() {
        devTaskUserDao.deleteById(4);
    }

}
