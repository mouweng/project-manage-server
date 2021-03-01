package zju.cst.project.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import zju.cst.project.dao.ProjectDao;
import zju.cst.project.entity.ProProject;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author: wengyifan
 * @description:
 * @date: 2021/1/28 11:18 上午
 */
@SpringBootTest
public class ProjectTest {

    @Resource
    private ProjectDao projectDao;


    @Test
    public void test1() {
        List<ProProject> proProjects = projectDao.queryAll();
        for (ProProject proProject : proProjects) {
            System.out.println(proProject);
        }
    }

    @Test
    public void test2() {
        ProProject proProject = projectDao.queryById(1);
        System.out.println(proProject);
    }


    @Test
    public void test5() {
        ProProject proProject = projectDao.queryById(3);
        System.out.println(proProject);
        proProject.setName("redis");
        proProject.setDescription("fine");
        proProject.setGmtUpdate(new Date());
        projectDao.update(proProject);
    }

    @Test
    public void test6() {
        ProProject proProject = new ProProject();
        proProject.setName("javaxxx");
        proProject.setDescription("wyf");
        proProject.setGmtCreate(new Date());
        proProject.setGmtUpdate(new Date());
        projectDao.insert(proProject);
    }

    @Test
    public void test7() {
        projectDao.deleteById(6);
    }

    @Test
    public void test8() {
        List<ProProject> proProjects = projectDao.queryProjectByUidAndType(2, 1);
        for (ProProject proProject : proProjects) {
            System.out.println(proProject);
        }
    }

    @Test
    public void test9() {
        List<ProProject> proProjects = projectDao.queryProjectByUidAndType(3, 2);
        for (ProProject proProject : proProjects) {
            System.out.println(proProject);
        }
    }

    @Test
    public void test10() {
        List<ProProject> proProjects = projectDao.queryProjectByUidAndType(3, 3);
        for (ProProject proProject : proProjects) {
            System.out.println(proProject);
        }
    }

    @Test
    public void test11() {
        ProProject proProject = projectDao.queryById(1);
        int num = projectDao.insert(proProject);
        System.out.println(proProject.getId());
    }

}
