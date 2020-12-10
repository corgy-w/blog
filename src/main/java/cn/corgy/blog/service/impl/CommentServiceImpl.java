package cn.corgy.blog.service.impl;

import cn.corgy.blog.entity.CommentInfo;
import cn.corgy.blog.entity.UserInfo;
import cn.corgy.blog.entity.page.CommentPage;
import cn.corgy.blog.mapper.CommentsMapper;
import cn.corgy.blog.mapper.UserMapper;
import cn.corgy.blog.security.LoginUser;
import cn.corgy.blog.service.CommentService;
import cn.corgy.blog.utils.AssertUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentsMapper commentsMapper;
    @Autowired
    private UserMapper userMapper;


    @Override
    public void deleteById(Integer id) {
        //判断用户是不是符合删除自己的权限
        UserInfo user = userMapper.findById(commentsMapper.findAllById(id).getUserId());
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AssertUtil.istrue(!(user.getId() == loginUser.getUser().getId()), "意外访问");
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
        //判断用户是不是符合删除自己的权限
        UserInfo user = userMapper.findById(commentsMapper.findAllById(comment.getId()).getUserId());
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AssertUtil.istrue(!(user.getId() == loginUser.getUser().getId()), "意外访问");
        Integer integer = commentsMapper.updateByComment(comment);
        AssertUtil.istrue(integer < 1, "更新失败");
    }

    @Override
    public PageInfo<CommentInfo> findByUserId(Integer userId, CommentPage page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<CommentInfo> all = commentsMapper.findAllByUserId(userId);
        return new PageInfo<>(all);
    }
}
