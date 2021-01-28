package zju.cst.project.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import zju.cst.project.dao.FileDao;
import zju.cst.project.dao.UserDao;
import zju.cst.project.entity.ProFile;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author: wengyifan
 * @description:
 * @date: 2021/1/28 3:42 下午
 */
@SpringBootTest
public class FileTest {
    @Resource
    private FileDao fileDao;


    @Test
    public void test1() {
        ProFile proFile = new ProFile();
        proFile.setPid(1);
        proFile.setFileName("需求文档");
        proFile.setFilePath("/file/");
        proFile.setSuffix(".pdf");
        proFile.setGmtCreate(new Date());
        proFile.setGmtUpdate(new Date());

        fileDao.insert(proFile);
        proFile.setFileName("设计文档");
        fileDao.insert(proFile);
        proFile.setFileName("开发文档");
        fileDao.insert(proFile);
    }

    @Test
    public void test2() {
        ProFile proFile = fileDao.queryById(1);
        System.out.println(proFile);

        List<ProFile> proFiles = fileDao.queryByPid(1);
        System.out.println(proFiles);
    }

    @Test
    public void test3() {
        ProFile proFile = fileDao.queryById(2);
        proFile.setPid(2);
        proFile.setFileName("某翁设计文档");
        proFile.setFilePath("/yifan/");
        proFile.setSuffix(".doc");
        proFile.setGmtUpdate(new Date());
        proFile.setGmtCreate(new Date());
        fileDao.update(proFile);
    }

    @Test
    public void test4() {
        fileDao.deleteById(2);
    }

    @Test
    public void test5() {
        fileDao.deleteByPid(1);
    }
}
