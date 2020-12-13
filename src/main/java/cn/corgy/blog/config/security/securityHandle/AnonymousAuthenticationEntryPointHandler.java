package cn.corgy.blog.config.security.securityHandle;

import cn.corgy.blog.utils.MessageUtil;
import cn.corgy.blog.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理没有权限访问资源的问题
 * 与与进行权限分配的类冲突
 */
@Slf4j
@Component
public class AnonymousAuthenticationEntryPointHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        log.warn("用户需要登录，访问[{}]失败，AuthenticationException=" + e, request.getRequestURI());
        ResponseUtil.render(request, response, MessageUtil.giveMsg(200, "请先登录"));
    }
}
