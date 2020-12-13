package cn.corgy.blog.config.security.securityHandle;

import cn.corgy.blog.utils.MessageUtil;
import cn.corgy.blog.utils.ResponseUtil;
import cn.corgy.blog.config.security.securityEntity.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 登录成功返回客户端的处理类
 */
@Slf4j
@Component//添加到spring容器
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
//        //比下面方法多了一个返回类 返回拦截器的前端控件
//    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        LoginUser principal = (LoginUser) authentication.getPrincipal();
        log.info("{}用户在{}登录了博客", principal.getUsername(), principal.getLoginIp());
        ResponseUtil.render(request, response, MessageUtil.giveMsg(200, authentication, "请求成功"));
    }
}
