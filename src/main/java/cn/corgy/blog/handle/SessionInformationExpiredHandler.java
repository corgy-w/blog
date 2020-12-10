package cn.corgy.blog.handle;

import cn.corgy.blog.utils.MessageUtil;
import cn.corgy.blog.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import java.io.IOException;


/**
 * 账号被顶
 * 权限查过
 * 被管理员踢下
 */
@Slf4j
@Component
public class SessionInformationExpiredHandler implements SessionInformationExpiredStrategy {


    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException, ServletException {
        ResponseUtil.render(sessionInformationExpiredEvent.getRequest(), sessionInformationExpiredEvent.getResponse(), MessageUtil.giveMsg(200, "访问意外结束"));
    }
}