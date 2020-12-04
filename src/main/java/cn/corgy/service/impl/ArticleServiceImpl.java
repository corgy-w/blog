package cn.corgy.service.impl;

import cn.corgy.entity.ArticleInfo;
import cn.corgy.entity.UserInfo;
import cn.corgy.mapper.ArticleMapper;
import cn.corgy.mapper.UserMapper;
import cn.corgy.page.ArticlePage;
import cn.corgy.security.LoginUser;
import cn.corgy.service.ArticleService;
import cn.corgy.utils.AssertUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private RedisTemplate<String, Serializable> redisTemplate;

    //添加文章 返回信息
    @Override
    public void insertArticle(ArticleInfo articleInfo) {
        Map<String, Object> map = new HashMap<>();
        Integer integer = articleMapper.insertArticle(articleInfo);
        AssertUtil.istrue(integer < 1, "添加失败");

    }


    //查询所有的文章
    @Override
    public PageInfo<ArticleInfo> findByQuery(ArticlePage articlePage) {
        PageHelper.startPage(articlePage.getPageNum(), articlePage.getPageSize());
        //查询操作
        List<ArticleInfo> query = articleMapper.findByQuery(articlePage);
        return new PageInfo<>(query);
    }

    //根据articleId查询文章信息
    @Override
    public ArticleInfo findByArticleId(Integer articleId) {
        //判断在redis是否有以articleId
        ArticleInfo article = (ArticleInfo) redisTemplate.opsForValue().get(Integer.toString(articleId));
        if (ObjectUtil.isNotNull(article)) {
            return article;
        }
        ArticleInfo article1 = articleMapper.findByArticleId(articleId);
        redisTemplate.opsForValue().set(Integer.toString(articleId), article1);
        return article1;
    }


    //修改文章 只能修改自己的文章
    @Override
    public void updateArticle(ArticleInfo articleInfo) {
        //信息校验工具防止用户跨权限访问
        UserInfo user = userMapper.findById(articleInfo.getUserInfo().getId());
        List<ArticleInfo> articleInfoList = user.getArticleInfoList();
        Integer[] arr = new Integer[articleInfoList.size()];
        for (int i = 0; i < articleInfoList.size(); i++) {
            ArticleInfo it = articleInfoList.get(i);
            arr[i] = it.getId();
        }
        AssertUtil.istrue(!Arrays.asList(arr).contains(articleInfo.getId()), "意外访问");
        Integer integer = articleMapper.updateArticle(articleInfo);
        AssertUtil.istrue(integer < 1, "修改失败");
    }

    //删除文章
    @Override
    public void delArticle(Integer id) {
        ArticleInfo articleInfo = articleMapper.findByArticleId(id);
        UserInfo user = userMapper.findById(articleInfo.getUserInfo().getId());
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AssertUtil.istrue(!(user.getId() == loginUser.getUser().getId()), "意外访问");
        Integer integer = articleMapper.delArticle(id);
        AssertUtil.istrue(integer < 1, "删除失败");
    }
}
