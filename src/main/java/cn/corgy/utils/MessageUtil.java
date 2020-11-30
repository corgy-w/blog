package cn.corgy.utils;

import java.util.HashMap;
import java.util.Map;

public class MessageUtil {
    //创建静态方法抛出成功的信息
    public static Map<String, Object> giveMsg(Integer code, String msg) {
        //创建返回的map信息
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("msg", msg);
        return map;
    }

    //创建静态方法抛出成功的信息
    public static Map<String, Object> giveMsg(Integer code, Object data, String msg) {
        //创建返回的map信息
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("data", data);
        map.put("msg", msg);
        return map;
    }
}
