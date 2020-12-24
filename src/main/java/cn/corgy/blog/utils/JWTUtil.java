package cn.corgy.blog.utils;

import cn.corgy.blog.config.security.securityEntity.SecurityConstant;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.GrantedAuthority;

import java.util.*;

public class JWTUtil {
    private static final String TOKEN = SecurityConstant.JWT_SIGN_KEY;

    /**
     * @param username    用户
     * @param date        过期实践
     * @param authorities 用户的角色
     * @return 返回token
     */
    public static String getToken(String username, Date date, Collection<GrantedAuthority> authorities) {
        JWTCreator.Builder builder = JWT.create();
        //将角色列表加入配置中
        builder.withClaim(SecurityConstant.AUTHORITIES, Arrays.toString(authorities.toArray()));
        builder.withClaim("username", username);
        builder.withExpiresAt(date);
        return SecurityConstant.TOKEN_SPLIT + builder.sign(Algorithm.HMAC256(TOKEN));
    }

    /**
     * 生成token
     *
     * @param map 传入payload
     * @return 返回token
     */
    public static String getToken(Map<String, String> map) {
        JWTCreator.Builder builder = JWT.create();
        map.forEach(builder::withClaim);
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MINUTE, 1);
        builder.withExpiresAt(instance.getTime());
        return builder.sign(Algorithm.HMAC256(TOKEN));
    }

    /**
     * 验证token
     *
     * @param token 传入token
     */
    public static void verify(String token) {
        JWT.require(Algorithm.HMAC256(TOKEN)).build().verify(token);
    }

    /**
     * 获取token中payload
     *
     * @param token 传入token
     * @return 返回解密后的信息
     */
    public static DecodedJWT getToken(String token) {
        return JWT.require(Algorithm.HMAC256(TOKEN)).build().verify(token);
    }
}