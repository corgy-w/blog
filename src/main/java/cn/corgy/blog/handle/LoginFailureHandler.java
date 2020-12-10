package cn.corgy.blog.handle;

import cn.corgy.blog.utils.MessageUtil;
import cn.corgy.blog.utils.ResponseUtil;
import cn.hutool.core.map.MapUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        Map<String, String[]> map = request.getParameterMap();
        String username = MapUtil.get(map, "username", String.class);
        if (e instanceof BadCredentialsException) {
            log.info("{}信息不正确", username);
            ResponseUtil.render(request, response, MessageUtil.giveMsg(500, "用户密码错误"));
        } else if (e instanceof DisabledException) {
            log.info("{}用户被禁用", username);
            ResponseUtil.render(request, response, MessageUtil.giveMsg(500, "用户被禁用"));
        }
    }
}
