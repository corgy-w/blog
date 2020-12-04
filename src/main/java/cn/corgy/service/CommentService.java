package cn.corgy.service;

import cn.corgy.entity.CommentInfo;
import cn.corgy.page.CommentPage;
import com.github.pagehelper.PageInfo;

public interface CommentService {
    //通过id删除文章
    void deleteById(Integer id);

    //添加留言内容
    void insertComment(CommentInfo comment);

    //分页查看留言信息
    PageInfo<CommentInfo> findAll(CommentPage commentPage);

    //通过主键更新内容
    void updateByComment(CommentInfo comment);
}
