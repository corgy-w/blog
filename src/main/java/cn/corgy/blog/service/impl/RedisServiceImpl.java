package cn.corgy.blog.service.impl;

import cn.corgy.blog.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

//TODO 日志处理
@Service
@Slf4j
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public Set<Object> listAllKeys() {
        return (Set<Object>) redisTemplate.keys("BLOG*");
    }

    @Override
    public void deleteKeys(Set set) {
        redisTemplate.delete(set);
    }

    @Override
    public long size(String key) {
        Long size = redisTemplate.opsForSet().size(key);
        return size;
    }

    @Override
    public void add(String key, String val) {
        redisTemplate.opsForSet().add(key, val);
    }
}
