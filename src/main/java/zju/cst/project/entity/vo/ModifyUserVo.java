package zju.cst.project.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: wengyifan
 * @Description: ModifyUserVo 修改用户信息封装类Vo
 * @Date Create in 2021/1/21 7:30 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModifyUserVo implements Serializable {
    // 用户账号
    private String account;
    // 用户名
    private String userName;
    // 用户密码
    private String password;
    // 所在部门
    private String department;
    // 联系电话
    private String telephone;
    // 职务
    private String position;
}
