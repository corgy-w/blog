package cn.corgy.blog.controller;

import cn.corgy.blog.entity.CommentInfo;
import cn.corgy.blog.entity.UserInfo;
import cn.corgy.blog.entity.page.CommentPage;
import cn.corgy.blog.entity.page.UserPage;
import cn.corgy.blog.service.CommentService;
import cn.corgy.blog.service.UserService;
import cn.corgy.blog.utils.MessageUtil;
import cn.corgy.blog.exception.ParamException;
import cn.corgy.blog.config.security.securityEntity.LoginUser;
import com.github.pagehelper.PageInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 用户的控制类
 */
@RequestMapping("user")
@RestController
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private CommentService commentService;

    //抛出登录者信息
    private static LoginUser getLoginUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal == "anonymousUser") {
            throw new ParamException(200, "没有权限非法访问");
        }
        return (LoginUser) principal;
    }

    //添加用户
    @PostMapping("insert")
    public Map<String, Object> insertUser(@RequestBody UserInfo userInfo) {
        userInfo.setPassword(BCrypt.hashpw(userInfo.getPassword(), BCrypt.gensalt()));
        userService.insertUser(userInfo);
        return MessageUtil.giveMsg(200, "添加成功");
    }

    //分页展现所有的用户
    @PreAuthorize("hasRole('ROLE_ADMIN') ")
    @GetMapping("list")
    public PageInfo<UserInfo> findAll(UserPage userPage) {
        return userService.findAll(userPage);
    }

    //获取固定用户的信息
    //可能用不到
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("info/{id}")
    public UserInfo findById(@PathVariable("id") Integer id) {
        return userService.findById(id);
    }

    //修改用户信息
    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("update")
    public Map<String, Object> updateUser(@RequestBody UserInfo userInfo) {
        userInfo.setId(UserController.getLoginUser().getUser().getId());
        userInfo.setUsername(UserController.getLoginUser().getUser().getUsername());
        userInfo.setPassword(BCrypt.hashpw(userInfo.getPassword(), BCrypt.gensalt()));
        userService.updateUser(userInfo);
        return MessageUtil.giveMsg(200, "用户修改成功");
    }

    //删除用户信息
    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("del/{id}")
    public Map<String, Object> updateUser(@PathVariable Integer id) {
        userService.delUser(id);
        return MessageUtil.giveMsg(200, "用户删除成功");
    }


    //查看用户所有的评论信息
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("comments")
    public Map<String, Object> findComment(CommentPage commentPage) {
        int id = getLoginUser().getUser().getId();
        PageInfo<CommentInfo> commentInfoPageInfo = commentService.findByUserId(id, commentPage);
        return MessageUtil.giveMsg(200, commentInfoPageInfo, "请求成功");
    }

}
