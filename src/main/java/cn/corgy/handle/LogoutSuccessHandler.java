package cn.corgy.handle;


import cn.corgy.security.LoginUser;
import cn.corgy.utils.MessageUtil;
import cn.corgy.utils.ResponseUtil;
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
        // TODO 登出成功 记录登出日志
        LoginUser principal = (LoginUser) authentication.getPrincipal();
        log.info("{}退出成功", principal.getUsername());
        ResponseUtil.render(request, response, MessageUtil.giveMsg(200, "成功退出"));

    }
}