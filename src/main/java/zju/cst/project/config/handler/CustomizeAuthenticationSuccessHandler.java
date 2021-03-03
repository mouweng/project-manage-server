package zju.cst.project.config.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import zju.cst.project.common.entity.JsonResult;
import zju.cst.project.common.utils.ResultTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import zju.cst.project.entity.ProProject;
import zju.cst.project.entity.ProUser;
import zju.cst.project.entity.vo.ReturnUserVo;
import zju.cst.project.service.ProjectService;
import zju.cst.project.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Author: wengyifan
 * @Description: 登录成功处理逻辑
 * @Date Create in 2021/1/21 7:30 下午
 */
@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    UserService userService;

    @Autowired
    ProjectService projectService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //更新用户表上次登录时间、更新人、更新时间等字段
        User userDetails = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ProUser proUser = userService.selectByName(userDetails.getUsername());
        proUser.setLastLoginTime(new Date());
        userService.update(proUser);

        //此处还可以进行一些处理，比如登录成功之后可能需要返回给前台当前用户有哪些菜单权限，
        //进而前台动态的控制菜单的显示等，具体根据自己的业务需求进行扩展

        //返回json数据
        ReturnUserVo returnUserVo = new ReturnUserVo(proUser);
        List<ProProject> proProjects = projectService.queryByUidAll(returnUserVo.getId());
        returnUserVo.setProjects(proProjects);
        JsonResult result = ResultTool.success(returnUserVo);
        httpServletResponse.setContentType("text/json;charset=utf-8");
        // httpServletResponse.getWriter().write(JSON.toJSONString(result));
        httpServletResponse.getWriter().write(JSON.toJSONString(result, SerializerFeature.WriteDateUseDateFormat));
    }
}
