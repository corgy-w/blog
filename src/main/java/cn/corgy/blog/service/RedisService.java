package cn.corgy.blog.service;

import java.util.Set;

public interface RedisService {
    //获取redis所有的值
    Set<Object> listAllKeys(String key);

    //删除所有的值
    void deleteKeys(Set set);

    //获取所有key的长度
    long size(String key);

    //添加集合
    void add(String key, String val);

}