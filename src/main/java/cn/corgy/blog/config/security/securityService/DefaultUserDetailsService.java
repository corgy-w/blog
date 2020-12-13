package cn.corgy.blog.config.security.securityService;


import cn.corgy.blog.entity.RoleInfo;
import cn.corgy.blog.entity.UserInfo;
import cn.corgy.blog.entity.UserRole;
import cn.corgy.blog.config.security.securityEntity.LoginType;
import cn.corgy.blog.config.security.securityEntity.LoginUser;
import cn.corgy.blog.service.RoleService;
import cn.corgy.blog.service.UserRoleService;
import cn.corgy.blog.service.UserService;
import cn.corgy.blog.utils.IpUtil;
import cn.corgy.blog.utils.ServletUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@Component
public class DefaultUserDetailsService implements UserDetailsService {

    @Resource
    private UserService userService;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private RoleService roleService;

    //定义数据 获取登录信息
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StrUtil.isBlank(username)) {//是否为空
            System.out.println(username);
            log.info("登录用户：{} 不存在", username);
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }
        // 查出密码  通过用户名查询密码 进行判断
        UserInfo user = userService.findByUsername(username);
        if (ObjectUtil.isNull(user)) {
            log.info("登录用户：{} 不存在", username);
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }
        //通过上面查出的用户id获取用户权限
        List<UserRole> userRoles = userRoleService.findByUserId(user.getId());
        //添加角色权限
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (UserRole userRole : userRoles) {
            RoleInfo role = roleService.findByRoleId(userRole.getRoleId());
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.getRoleName());
            authorities.add(simpleGrantedAuthority);
        }
        LoginUser loginUser = new LoginUser(
                user, IpUtil.getIpAddr(ServletUtil.getRequest()),
                LocalDateTime.now(),
                LoginType.PASSWORD,
                true);
        loginUser.setAuthorities(authorities);
        return loginUser;
        /*初试写法 只有几个信息
        UserDetails userDetails = User.withUsername(user.getUsername().password(user.getPassword()).authorities("/list")//权限.build();*/
    }
}
