package cn.corgy.blog.service;

import cn.corgy.blog.entity.ArticleInfo;
import cn.corgy.blog.entity.page.ArticlePage;
import com.github.pagehelper.PageInfo;

public interface ArticleService {


    //添加一篇文章
    void insertArticle(ArticleInfo articleInfo);

    //分页查询所有的文章
    PageInfo<ArticleInfo> findByQuery(ArticlePage articlePage);

    //通过文章id获取文章详细信息
    ArticleInfo findByArticleId(Integer articleId);

    //修改篇文章
    void updateArticle(ArticleInfo articleInfo);

    //删除一篇文章
    void delArticle(Integer id);


}
