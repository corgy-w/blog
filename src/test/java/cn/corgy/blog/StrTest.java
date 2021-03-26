package cn.corgy.blog;

import org.junit.Test;

import java.util.Arrays;

public class StrTest {
    @Test
    public void jichu() {
        String token = "[ROLE_ADMIN, ROLE_USER]";
        int length = token.length();
        final String tokenTemp = token.substring(1, length - 1);
        System.out.println("MMMMMMMMMMMM"+token);
        final String[] split = tokenTemp.split(",");
        System.out.println(Arrays.toString(split));
        for (String str : split) {
            str.replace("[", "");
            System.out.println(str);
        }
    }

}
