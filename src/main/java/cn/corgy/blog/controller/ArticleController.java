package cn.corgy.blog.controller;

import cn.corgy.blog.config.aop.Uvlog;
import cn.corgy.blog.entity.ArticleInfo;
import cn.corgy.blog.entity.CommentInfo;
import cn.corgy.blog.entity.TypeInfo;
import cn.corgy.blog.entity.UserInfo;
import cn.corgy.blog.entity.page.CommentPage;
import cn.corgy.blog.service.CommentService;
import cn.corgy.blog.service.UserService;
import cn.corgy.blog.utils.MessageUtil;
import cn.corgy.blog.exception.ParamException;
import cn.corgy.blog.entity.page.ArticlePage;
import cn.corgy.blog.config.security.securityEntity.LoginUser;
import cn.corgy.blog.service.ArticleService;
import com.github.pagehelper.PageInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * 文章主要类
 * 包含留言内容的处理接口
 */
@RestController
@RequestMapping("article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @Resource
    private UserService userService;

    @Resource
    private CommentService commentService;

    //抛出登录者信息
    private LoginUser getLoginUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal == "anonymousUser") {
            throw new ParamException(200, "没有权限非法访问");
        }
        return (LoginUser) principal;
    }

    //添加一条信息
    //添加认证的用户对象
    //通过查询封装原本类型对象
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(value = "insert/{typeId}")
    public Map<String, Object> insertArticle(@RequestBody ArticleInfo articleInfo, @PathVariable("typeId") Integer typeId) {
        //获取操作者
        TypeInfo typeInfo = new TypeInfo();
        typeInfo.setId(typeId);
        articleInfo.setUserInfo(getLoginUser().getUser());
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

    //用户查看自己文章列表
    @GetMapping("myList")
    public Map<String, Object> readList(Integer id) {
        UserInfo userInfo;
        if (id != 0) {
            userInfo = userService.findById(id);
        } else {
            userInfo = userService.findById(getLoginUser().getUser().getId());
        }
        return MessageUtil.giveMsg(200, userInfo.getArticleInfoList(), "请求成功");
    }

    //通过文章的id查询文章的详细内容
    @Uvlog
    @GetMapping("details/{articleId}")
    public Map<String, Object> updateArticle(@PathVariable("articleId") Integer articleId) {
        ArticleInfo article = articleService.findByArticleId(articleId);
        return MessageUtil.giveMsg(200, article, "请求成功");
    }


    //修改文章的信息
    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("update/{typeId}/{articleId}")  //typeId需要修改的文章类型
    public Map<String, Object> updateArticle(@PathVariable("typeId") Integer typeId, @PathVariable("articleId") Integer articleId, @RequestBody ArticleInfo articleInfo) {
        TypeInfo typeInfo = new TypeInfo();
        typeInfo.setId(typeId);
        articleInfo.setId(articleId);
        articleInfo.setUserInfo(getLoginUser().getUser());
        articleInfo.setTypeInfo(typeInfo);
        articleInfo.setTime(new Date());
        articleService.updateArticle(articleInfo);
        return MessageUtil.giveMsg(200, "文章修改成功");
    }

    //删除一篇文章
    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("del/{articleId}")
    public Map<String, Object> updateUser(@PathVariable Integer articleId) {
//        UserInfo user = getLoginUser().getUser();
        articleService.delArticle(articleId);
        return MessageUtil.giveMsg(200, "文章删除成功");
    }


    //对文章添加评论
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("details/{articleId}")
    public Map<String, Object> insertComment(@PathVariable Integer articleId, @RequestBody CommentInfo commentInfo) {
        UserInfo user = getLoginUser().getUser();
        commentInfo.setArticleId(articleId);
        commentInfo.setUserId(user.getId());
        commentService.insertComment(commentInfo);
        return MessageUtil.giveMsg(200, "留言成功");
    }

    //查看本文章的评论
    @GetMapping("details")
    public Map<String, Object> insertComment(CommentPage page) {
        PageInfo<CommentInfo> all = commentService.findAll(page);
        return MessageUtil.giveMsg(200, all, "请求成功");
    }

    //删除留言
    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("details/del/{id}")
    public Map<String, Object> delComment(@PathVariable Integer id) {
        commentService.deleteById(id);
        return MessageUtil.giveMsg(200, "留言成功");
    }

    //更新留言（重新编辑留言）
    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("details/put")
    public Map<String, Object> updateComment(@RequestBody CommentInfo commentInfo) {
        commentService.updateByComment(commentInfo);
        return MessageUtil.giveMsg(200, "更新成功");
    }
}

