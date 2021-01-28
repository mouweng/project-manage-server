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
        System.out.println(proProjects);
    }

    @Test
    public void test2() {
        ProProject proProject = projectDao.queryById(1);
        System.out.println(proProject);
    }

    @Test
    public void test3() {
        List<ProProject> proProjects = projectDao.queryByManager(2);
        System.out.println(proProjects);
    }

    @Test
    public void test4() {
        List<ProProject> proProjects = projectDao.queryByTestLeader(3);
        System.out.println(proProjects);
    }

    @Test
    public void test5() {
        ProProject proProject = projectDao.queryById(3);
        System.out.println(proProject);
        proProject.setName("redis-java");
        proProject.setDescription("personal");
        proProject.setProjectManager(2);
        proProject.setTestLeader(3);
        proProject.setGmtCreate(new Date());
        proProject.setGmtUpdate(new Date());
        projectDao.update(proProject);
    }

    @Test
    public void test6() {
        ProProject proProject = new ProProject();
        proProject.setName("redis-java");
        proProject.setDescription("personal");
        proProject.setProjectManager(2);
        proProject.setTestLeader(3);
        proProject.setGmtCreate(new Date());
        proProject.setGmtUpdate(new Date());
        projectDao.insert(proProject);
    }

    @Test
    public void test7() {
        projectDao.deleteById(4);
    }

}
