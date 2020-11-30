package cn.corgy.controller;

import cn.corgy.entity.ArticleInfo;
import cn.corgy.entity.TypeInfo;
import cn.corgy.page.ArticlePage;
import cn.corgy.security.LoginUser;
import cn.corgy.service.ArticleService;
import cn.corgy.utils.MessageUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * 文章主要类
 */
@RestController
@RequestMapping("article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    //抛出登录者信息
    private static LoginUser getLoginUser() {
        return (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    //添加一条信息
    //添加认证的用户对象
    //通过查询封装原本类型对象
    @PreAuthorize("@ps.permission('ROLE_USER')")
    @PostMapping(value = "insert/{typeId}")
    public Map<String, Object> insertArticle(@RequestBody ArticleInfo articleInfo, @PathVariable("typeId") Integer typeId) {
        //获取操作者
        LoginUser loginUser = ArticleController.getLoginUser();
        TypeInfo typeInfo = new TypeInfo();
        typeInfo.setId(typeId);
        articleInfo.setUserInfo(loginUser.getUser());
        articleInfo.setTypeInfo(typeInfo);
        articleInfo.setTime(new Date());
        articleService.insertArticle(articleInfo);
        return MessageUtil.giveMsg(200, "文章添加成功");
    }

    //分页查看所有的文章
    @GetMapping("list")
    public PageInfo<ArticleInfo> findByQuery(ArticlePage page) {
        return articleService.findByQuery(page);
    }

    //修改文章
    @PreAuthorize("@ps.permission('ROLE_USER')")
    @PutMapping("update/{typeId}/{articleId}")  //typeId需要修改的文章类型
    public Map<String, Object> updateArticle(@PathVariable("typeId") Integer typeId, @PathVariable("articleId") Integer articleId, @RequestBody ArticleInfo articleInfo) {
        LoginUser loginUser = ArticleController.getLoginUser();
        TypeInfo typeInfo = new TypeInfo();
        typeInfo.setId(typeId);
        articleInfo.setId(articleId);
        articleInfo.setUserInfo(loginUser.getUser());
        articleInfo.setTypeInfo(typeInfo);
        articleInfo.setTime(new Date());
        articleService.updateArticle(articleInfo);
        return MessageUtil.giveMsg(200, "文章修改成功");
    }

    //删除一篇文章
    @PreAuthorize("@ps.permission('ROLE_USER')")
    @DeleteMapping("del/{articleId}")
    public Map<String, Object> updateUser(@PathVariable Integer articleId) {
        articleService.delArticle(articleId);
        return MessageUtil.giveMsg(200, "文章删除成功");
    }
}

