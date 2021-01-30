package zju.cst.project.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import zju.cst.project.dao.UserRoleDao;
import zju.cst.project.entity.ProUser;
import zju.cst.project.entity.ProUserRole;

import javax.annotation.Resource;

/**
 * @author: wengyifan
 * @description:
 * @date: 2021/1/29 10:49 下午
 */
@SpringBootTest
public class UserRoleTest {
    @Resource
    private UserRoleDao userRoleDao;

    @Test
    public void test1() {
        ProUserRole proUserRole = userRoleDao.selectByUid(1);
        System.out.println(proUserRole);
    }

    @Test
    public void test2() {
        ProUserRole proUserRole = userRoleDao.selectByUid(1);
        proUserRole.setUserId(4);
        proUserRole.setRoleId(3);
        userRoleDao.insert(proUserRole);
    }

    @Test
    public void test3() {
        ProUserRole proUserRole = userRoleDao.selectByUid(4);
        proUserRole.setRoleId(2);
        userRoleDao.update(proUserRole);
    }

    @Test
    public void test4() {
        userRoleDao.deleteByUid(4);
    }

}
