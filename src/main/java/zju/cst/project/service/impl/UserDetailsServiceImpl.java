package zju.cst.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import zju.cst.project.entity.ProPermission;
import zju.cst.project.entity.ProUser;
import zju.cst.project.service.PermissionService;
import zju.cst.project.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || "".equals(username)) {
            throw new RuntimeException("用户不能为空");
        }
        //根据用户名查询用户
        ProUser proUser = userService.selectByName(username);
        if (proUser == null) {
            throw new RuntimeException("用户不存在");
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (proUser != null) {
            //获取该用户所拥有的权限
            List<ProPermission> sysPermissions = permissionService.selectListByUser(proUser.getId());
            // 声明用户授权
            System.out.println(sysPermissions);
            sysPermissions.forEach(sysPermission -> {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(sysPermission.getPermissionCode());
                grantedAuthorities.add(grantedAuthority);
            });
        }
        return new User(proUser.getAccount(), proUser.getPassword(), proUser.getEnabled(), proUser.getAccountNonExpired(), proUser.getCredentialsNonExpired(), proUser.getAccountNonLocked(), grantedAuthorities);
    }
}
