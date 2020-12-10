package cn.corgy.blog.mapper;

import cn.corgy.blog.entity.page.CommentPage;
import cn.corgy.blog.entity.CommentInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentsMapper {

    //通过id删除文章
    Integer deleteById(Integer id);

    //添加留言内容
    Integer insertComment(CommentInfo comment);

    //查询留言的内容 通过分页来实现三个功能查询
    List<CommentInfo> findAll(CommentPage commentPage);

    //更新留言内容
    Integer updateByComment(CommentInfo comment);
    //通过主键查询留言内容
    CommentInfo findAllById(Integer id);

    //通过用户id获取他的所有留言信息
    List<CommentInfo> findAllByUserId(Integer userId);
}