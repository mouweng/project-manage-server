package zju.cst.project.config.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import zju.cst.project.entity.ProPermission;
import zju.cst.project.service.PermissionService;

import java.util.Collection;
import java.util.List;

/**
 * @Author: wengyifan
 * @Description: 权限过滤验证
 * @Date Create in 2021/1/21 7:30 下午
 */
@Component
public class CustomizeFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Autowired
    PermissionService permissionService;
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        // 获取请求地址
        String requestUrl = ((FilterInvocation) o).getRequestUrl();

        // todo: 把后方的数字id给去掉
        System.out.println("requestUrl: " + requestUrl);
        requestUrl = deleteNum(requestUrl);

        // 查询具体某个接口的权限
        List<ProPermission> permissionList =  permissionService.selectListByPath(requestUrl);

        if(permissionList == null || permissionList.size() == 0){
            //请求路径没有配置权限，表明该请求接口可以任意访问
            return null;
        }
        String[] attributes = new String[permissionList.size()];
        for(int i = 0;i<permissionList.size();i++){
            attributes[i] = permissionList.get(i).getPermissionCode();
        }
        return SecurityConfig.createList(attributes);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    public static String deleteNum(String s) {
        // 首先去掉问号后面的字符串
        int index = 0;
        while (index < s.length() && s.charAt(index) != '?') {
            index ++;
        }
        s = s.substring(0, index);

        StringBuffer sb = new StringBuffer();
        String[] arr = s.split("/");
        if (arr.length == 0) return "/";
        for(int i = 1; i < arr.length; i ++) {
            if (arr[i].matches("[0-9]{1,}")){
                continue;
            }
            sb.append("/");
            sb.append(arr[i]);
        }
        return sb.toString();
    }
}
