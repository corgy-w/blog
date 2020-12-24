package cn.corgy.blog.config.security.securityconfig;

import cn.corgy.blog.config.security.securityHandle.*;
import cn.corgy.blog.config.security.securityService.DefaultUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * spring security的配置
 * 建造者模式开启配置类信息
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DefaultUserDetailsService userDetailsService;

    //建造自己的房子
    //匿名访问
    @Autowired
    private AnonymousAuthenticationEntryPointHandler anonymousAuthenticationEntryPointHandler;
    //登录注入
    @Autowired
    private LoginSuccessHandler loginSuccessHandler;
    @Autowired
    private LoginFailureHandler loginFailureHandler;
    //退出注入
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;
    //没有访问权限提示
    @Autowired
    private LoginUserAccessDeniedHandler accessDeniedHandler;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置认证方式等
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        //授权
        http.authorizeRequests()
//                .antMatchers("/article/details/*")
//                .permitAll()
//                .anyRequest()//任何请求
//                .authenticated()//需要身份认证
        ;
        http.logout()
                .permitAll()//允许所有用户
                .logoutSuccessHandler(logoutSuccessHandler)//登出成功处理逻辑
                .and()
                .formLogin().permitAll()//允许所有用户
                .successHandler(loginSuccessHandler)//登录成功处理逻辑
                .failureHandler(loginFailureHandler)//登录失败逻辑
        ;
        //session会话处理
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//关闭session
//                .maximumSessions(1)//同一账号同时登录最大用户数
//                .expiredSessionStrategy(sessionInformationExpiredHandler) // 顶号处理
//        ;
        //异常处理的类型
        http.exceptionHandling()
                .authenticationEntryPoint(anonymousAuthenticationEntryPointHandler)//匿名用户访问无权限资源时的异常处理
                .accessDeniedHandler(accessDeniedHandler)//登录用户没有权限访问资源
        ;
        //添加jwt认证
        http.addFilter(new JWTAuthenticationFilter(authenticationManager(), 7));
    }
}