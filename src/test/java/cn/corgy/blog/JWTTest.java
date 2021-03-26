package cn.corgy.blog;

import cn.corgy.blog.utils.JWTUtil;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)

@SpringBootTest
public class JWTTest {
    @Resource
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void test11() {
        String encode = bCryptPasswordEncoder.encode("123");
        System.out.printf(encode);
    }

    @Test

    public void jwtTest() {
        Map<String, String> map = new HashMap<>();//用来存放payload
        map.put("id", "1");
        map.put("username", "小王");
        final String token = JWTUtil.getToken(map);
        System.out.println(token);
    }

    @Test
    public void jwtTest1() {
        final DecodedJWT token = JWTUtil.getToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjEiLCJleHAiOjE2MDgxMDkwMDIsInVzZXJuYW1lIjoi5bCP546LIn0.ChSHJf42Z7aODzCELm-pb1S6nOru3jWNoXSxntS7fVY");
        System.out.println(token.getClaim("username").asString());
        System.out.println(token.getClaim("id").asString());
    }


}

