package cn.corgy.blog.config.security.securityHandle;

import cn.corgy.blog.config.security.securityEntity.LoginType;
import cn.corgy.blog.config.security.securityEntity.LoginUser;
import cn.corgy.blog.config.security.securityEntity.SecurityConstant;
import cn.corgy.blog.entity.UserInfo;
import cn.corgy.blog.utils.IpUtil;
import cn.corgy.blog.utils.JWTUtil;
import cn.corgy.blog.utils.ServletUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {
    private Integer tokenExpireTime;
    //继承父类使用SpringSecurity的默认处理器拦截器
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, Integer tokenExpireTime) {
        super(authenticationManager);
        this.tokenExpireTime = tokenExpireTime;
    }

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationManager, authenticationEntryPoint);
    }
    //重写拦截器流程的逻辑
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(SecurityConstant.HEADER);
        log.info("这是请求的头信息{}", header);
        if (StrUtil.isBlank(header)) header = request.getParameter(SecurityConstant.HEADER);
        //判断是都存在heard与KEY为TOKEN的信息
        boolean notValid = StrUtil.isBlank(header) || (!header.startsWith(SecurityConstant.TOKEN_SPLIT));
        if (notValid) chain.doFilter(request, response);
        try {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(header, response);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            log.error("jwt处理问题{}", e.toString());
        }
        chain.doFilter(request, response);
    }

    //信息的校验
    private UsernamePasswordAuthenticationToken getAuthentication(String header, HttpServletResponse response) {
        //用户名
        String username;
        //权限
        List<GrantedAuthority> authorities = new ArrayList<>();

        //获取解析jwt  异常处理放在外处理器
        DecodedJWT token = JWTUtil.getToken(header.replace(SecurityConstant.TOKEN_SPLIT, ""));
        log.info("过期时间：" + token);
        //获取用户名
        username = token.getClaim("username").asString();
        log.info("用户：" + username);
        //获取权限
        String authority = token.getClaim(SecurityConstant.AUTHORITIES).asString();
        log.info("角色列表：" + authority);
        if (!StrUtil.isEmpty(authority)) {
            final int length = authority.length();
            final String substring = authority.substring(1, length - 1);
            final String[] split = substring.split(",");
            for (String s : split) {
                final String s1 = s.replaceAll(" ", "");
                authorities.add(new SimpleGrantedAuthority(s1));
            }
        }
        //自定义信息
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setId(1);
        if (StrUtil.isNotBlank(username)) {
            //踩坑提醒 此处password不能为null
            LoginUser loginUser = new LoginUser(
                    userInfo, IpUtil.getIpAddr(ServletUtil.getRequest()),
                    LocalDateTime.now(),
                    LoginType.PASSWORD,//状态信息
                    true);
            return new UsernamePasswordAuthenticationToken(loginUser, null, authorities);
        }
        return null;
    }
}