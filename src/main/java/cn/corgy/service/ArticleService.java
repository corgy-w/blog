package cn.corgy.service;

import cn.corgy.entity.ArticleInfo;
import cn.corgy.entity.TypeInfo;
import cn.corgy.page.ArticlePage;
import com.github.pagehelper.PageInfo;

public interface ArticleService {


    //添加一篇文章
    void insertArticle(ArticleInfo articleInfo);

    //分页查询所有的文章
    PageInfo<ArticleInfo> findByQuery(ArticlePage articlePage);

    //修改篇文章
    void updateArticle(ArticleInfo articleInfo);

    //删除一篇文章
    void delArticle(Integer id);



}
