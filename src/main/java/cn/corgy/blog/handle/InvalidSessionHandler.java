package cn.corgy.blog.handle;


import cn.corgy.blog.utils.MessageUtil;
import cn.corgy.blog.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class InvalidSessionHandler implements InvalidSessionStrategy {
    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.info("用户登录超时，访问[{}]失败", request.getRequestURI());
        ResponseUtil.render(request, response, MessageUtil.giveMsg(200, "登录超时"));

    }
}