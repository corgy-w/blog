package cn.corgy.blog.service;

import cn.corgy.blog.entity.CommentInfo;
import cn.corgy.blog.entity.page.CommentPage;
import com.github.pagehelper.PageInfo;

public interface CommentService {
    //通过id删除留言
    void deleteById(Integer id);

    //添加留言内容
    void insertComment(CommentInfo comment);

    //分页查看留言信息
    PageInfo<CommentInfo> findAll(CommentPage commentPage);

    //通过主键更新内容
    void updateByComment(CommentInfo comment);

    //通过用户id查询用户的留言信息
    PageInfo<CommentInfo> findByUserId(Integer userId, CommentPage page);
}
