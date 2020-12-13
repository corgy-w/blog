package cn.corgy.blog.config.security.securityEntity;

import cn.corgy.blog.entity.UserInfo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;

@Data
@NoArgsConstructor//代替无参构造
public class LoginUser implements UserDetails, CredentialsContainer {


    //private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    //用户
    private UserInfo user;
    //登录ip
    private String loginIp;
    //登录时间
    private LocalDateTime loginTime;
    //登陆类型
    private LoginType loginType;
    //权限集合
    private Collection<? extends GrantedAuthority> authorities;
    //账户是否可以使用
    private Boolean emblem;


    //有参构造
    public LoginUser(UserInfo user, String loginIp, LocalDateTime loginTime, LoginType loginType, Boolean emblem) {
        this.user = user;
        this.loginIp = loginIp;
        this.loginTime = loginTime;
        this.loginType = loginType;
        this.emblem = emblem;

    }

    //登录类型覆盖
    public LoginUser(UserInfo user, String loginIp, LocalDateTime loginTime, String loginType, Boolean emblem) {
        this.user = user;
        this.loginIp = loginIp;
        this.loginTime = loginTime;
        this.loginType = LoginType.valueOf(loginType);
        this.emblem = emblem;

    }

    //认证完成后，擦除密码 完成后操作
    @Override
    public void eraseCredentials() {
        user.setPassword(null);
    }

    //返回权限信息
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    //返回密码
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    //返回用户名
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // 账户是否未过期，过期无法验证
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //指定用户是否解锁，锁定的用户无法进行身份验证
    @Override
    public boolean isAccountNonLocked() {
        //return ObjectUtil.equal(user.getPwdLockFlag(), LockFlag.UN_LOCKED);
        return true;
    }

    //指示是否已过期的用户的凭据(密码)，过期的凭据防止认证  用户凭证是否过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //用户是否被启用或禁用。禁用的用户无法进行身份验证。
    @Override
    public boolean isEnabled() {
        return emblem;
    }
}
