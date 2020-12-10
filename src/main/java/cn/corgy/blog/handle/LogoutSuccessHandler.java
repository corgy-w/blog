package cn.corgy.blog.handle;


import cn.corgy.blog.security.LoginUser;
import cn.corgy.blog.utils.MessageUtil;
import cn.corgy.blog.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class LogoutSuccessHandler implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        LoginUser principal;
        if (authentication == null) {
            ResponseUtil.render(request, response, MessageUtil.giveMsg(200, "请先登录"));
        } else {
            principal = (LoginUser) authentication.getPrincipal();
            log.info("{}退出成功", principal.getUsername());
            ResponseUtil.render(request, response, MessageUtil.giveMsg(200, "成功退出"));
        }


    }
}