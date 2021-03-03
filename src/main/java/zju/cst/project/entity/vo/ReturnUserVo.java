package zju.cst.project.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import zju.cst.project.entity.ProProject;
import zju.cst.project.entity.ProUser;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author: wengyifan
 * @description:
 * @date: 2021/3/3 10:24 上午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnUserVo implements Serializable {
    // 自增id
    private Integer id;
    //账号
    private String account;
    //用户名
    private String userName;
    //用户密码
    private String password;
    //上一次登录时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastLoginTime;
    //账号是否可用。默认为1（可用）
    private Boolean enabled;
    //是否过期。默认为1（没有过期）
    private Boolean accountNonExpired;
    //账号是否锁定。默认为1（没有锁定）
    private Boolean accountNonLocked;
    //证书（密码）是否过期。默认为1（没有过期）
    private Boolean credentialsNonExpired;
    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    //修改时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    //创建人
    private Integer createUser;
    //修改人
    private Integer updateUser;
    //头像地址
    private String photoFile;
    //所在部门
    private String department;
    //联系电话
    private String telephone;
    //职务
    private String position;


    private List<ProProject> projects;

    public ReturnUserVo(ProUser proUser) {
        id = proUser.getId();
        account = proUser.getAccount();
        userName = proUser.getUserName();
        password = proUser.getPassword();
        lastLoginTime = proUser.getLastLoginTime();
        enabled = proUser.getEnabled();
        accountNonExpired = proUser.getAccountNonExpired();
        accountNonLocked = proUser.getAccountNonLocked();
        credentialsNonExpired = proUser.getCredentialsNonExpired();
        createTime = proUser.getCreateTime();
        updateTime = proUser.getUpdateTime();
        createUser = proUser.getCreateUser();
        updateUser = proUser.getUpdateUser();
        photoFile = proUser.getPhotoFile();
        department = proUser.getDepartment();
        telephone = proUser.getTelephone();
        position = proUser.getPosition();
    }
}
