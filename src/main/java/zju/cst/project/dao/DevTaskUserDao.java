package zju.cst.project.dao;

import org.mapstruct.Mapper;
import zju.cst.project.entity.ProDevTaskUser;

/**
 * @author: wengyifan
 * @description: DevTaskUserDao数据接口层
 * @date: 2021/1/28 8:28 下午
 */
@Mapper
public interface DevTaskUserDao {
    /**
     * @param id
     * @return {@link ProDevTaskUser}
     * @throws
     * @author: wengyifan
     * @description: 根据id查询
     * @date: 2021/1/28 8:29 下午
     */
    ProDevTaskUser queryById(Integer id);

    /**
     * @param tid
     * @param uid
     * @return {@link ProDevTaskUser}
     * @throws
     * @author: wengyifan
     * @description: 根据tid和uid查询
     * @date: 2021/1/28 8:30 下午
     */
    ProDevTaskUser queryByDevTidAndUid(Integer tid, Integer uid);

    /**
     * @param proDevTaskUser
     * @return {@link int}
     * @throws
     * @author: wengyifan
     * @description: 更新
     * @date: 2021/1/28 8:32 下午
     */
    int update(ProDevTaskUser proDevTaskUser);

    /**
     * @param proDevTaskUser
     * @return {@link int}
     * @throws
     * @author: wengyifan
     * @description:插入
     * @date: 2021/1/28 8:32 下午
     */
    int insert(ProDevTaskUser proDevTaskUser);

    /**
     * @param id
     * @return {@link int}
     * @throws
     * @author: wengyifan
     * @description: 根据id删除
     * @date: 2021/1/28 8:32 下午
     */
    int deleteById(Integer id);

}
