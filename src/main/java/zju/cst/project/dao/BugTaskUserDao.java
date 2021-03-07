package zju.cst.project.dao;

import org.mapstruct.Mapper;
import zju.cst.project.entity.ProBugTaskUser;
import zju.cst.project.entity.ProDevTaskUser;

@Mapper
public interface BugTaskUserDao {
    ProBugTaskUser queryById(Integer id);

    ProBugTaskUser queryByBugTidAndUid(Integer tid, Integer DevUid, Integer TestUid);

    int update(ProBugTaskUser proBugTaskUser);

    int insert(ProBugTaskUser proBugTaskUser);

    int deleteById(Integer id);

    int deleteByBugTid(Integer bugTid);
}
