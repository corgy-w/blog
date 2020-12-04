package cn.corgy.mapper;

import cn.corgy.entity.ArticleInfo;
import cn.corgy.entity.CommentInfo;
import cn.corgy.page.CommentPage;
import com.github.pagehelper.PageInfo;
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

    //通过主键更新内容
    Integer updateByComment(CommentInfo comment);

}