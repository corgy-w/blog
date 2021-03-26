package cn.corgy.blog.config.aop;

import cn.corgy.blog.config.scheduled.RedisToMysql;
import cn.corgy.blog.service.RedisService;
import cn.corgy.blog.utils.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
@Slf4j
public class UvAspect {

    @Autowired
    private RedisService redisService;

    // 拿到@UVlog注解注释的方法，这里就是切点
    @Pointcut("@annotation(cn.corgy.blog.config.aop.Uvlog)")
    private void weblog() {

    }

    // 调用方法后都会进行统计操作，写入redis
    @After("weblog()")
    public void afterMethod(JoinPoint joinpoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        Object[] args = joinpoint.getArgs();
        String value = "IP:" + IpUtil.getIpAddr(request);
        if (RedisToMysql.prefixDate == null) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        String key = "BLOG:" + args[0] + ":" + RedisToMysql.prefixDate;
        log.info(value+"的用户访问文章 生成key = " + key);
        redisService.add(key, value);
    }
}