package cn.corgy.service.impl;

import cn.corgy.entity.CommentInfo;
import cn.corgy.mapper.CommentsMapper;
import cn.corgy.page.CommentPage;
import cn.corgy.service.CommentService;
import cn.corgy.utils.AssertUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentsMapper commentsMapper;


    @Override
    public void deleteById(Integer id) {
        Integer integer = commentsMapper.deleteById(id);
        AssertUtil.istrue(integer < 1, "删除失败");
    }

    @Override
    public void insertComment(CommentInfo comment) {
        Integer integer = commentsMapper.insertComment(comment);
        AssertUtil.istrue(integer < 1, "添加失败");
    }

    @Override
    public PageInfo<CommentInfo> findAll(CommentPage commentPage) {
        PageHelper.startPage(commentPage.getPageNum(), commentPage.getPageSize());
        //查询操作
        List<CommentInfo> all = commentsMapper.findAll(commentPage);
        return new PageInfo<>(all);
    }

    @Override
    public void updateByComment(CommentInfo comment) {
        Integer integer = commentsMapper.updateByComment(comment);
        AssertUtil.istrue(integer < 1, "更新失败");
    }
}
