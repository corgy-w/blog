package cn.corgy.security;

import cn.corgy.handle.AnonymousAuthenticationEntryPointHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service("ps")
public class PermissionService {
    public boolean permission(String permission) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal == "anonymousUser") {

        }
        LoginUser loginUser = (LoginUser) principal;
        for (GrantedAuthority userPermission : loginUser.getAuthorities()) {
            if (permission.matches(String.valueOf(userPermission))) {
                return true;
            }
        }
        if (log.isDebugEnabled()) {
            log.debug("用户userId={}, userName={} 权限不足以访问[{}], 用户具有权限：{}, 访问", loginUser.getUser().getId(),
                    loginUser.getUsername(), permission, loginUser.getAuthorities());
        } else {
            log.info("用户userId={}, userName={} [{}]权限不足以访问此链接", loginUser.getUser().getId(), loginUser.getUsername(), permission);
        }
        return false;
    }
}