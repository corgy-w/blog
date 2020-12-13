package cn.corgy.blog.config.scheduled;

import cn.corgy.blog.entity.ArticleInfo;
import cn.corgy.blog.mapper.ArticleMapper;
import cn.corgy.blog.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@Component
@Slf4j
public class RedisToMysql {

    public static String prefixDate = null;//错误的方式还要修改 花点时间学习一下 Scheduled在进行完善

    @Autowired
    private RedisService redisService;

    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 每6个小时将redis的key添加到mysql
     * 先读取，将数量统计并落盘
     * 后删除键，等待下一个调用
     */
    @Scheduled(initialDelay = 5000, fixedDelay = 6 * DateUtils.MILLIS_PER_HOUR)
    private void redisToMySQL() {
        Set<Object> keys = redisService.listAllKeys();//获取所有的值
        if (!keys.isEmpty()) {
            // 读取key，然后取出文章id，进行数据库操作更新阅读数量
            for (Object it : keys) {
                String key = (String) it;
                String articleId = key.split(":")[1];
                ArticleInfo article = articleMapper.findByArticleId(Integer.parseInt(articleId));
                Integer readNum = article.getReadNum();
                long count = redisService.size(key);//获取长度
                article.setReadNum(Math.toIntExact(readNum + count));
                articleMapper.updateArticleReadNum(article);
            }
            redisService.deleteKeys(keys);//删除这些列表
        } else {
            log.info("该时间段无新访问者访问文章");
        }
    }

    // 更新每天的键值
    @PostConstruct
    @Scheduled(cron = " 0 0 0 * * ?")
    private static void updatePrefixDate() {
        log.info("当前prefixDate为 " + prefixDate);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        prefixDate = String.valueOf(format.format(new Date()));
        log.info("更新prefixDate为 " + prefixDate);
    }
}