package cn.corgy.controller;

import cn.corgy.entity.UserInfo;
import cn.corgy.page.UserPage;
import cn.corgy.security.LoginUser;
import cn.corgy.service.UserService;
import cn.corgy.utils.MessageUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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

    //抛出登录者信息
    private static LoginUser getLoginUser() {
        return (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    //添加用户
    @PreAuthorize("@ps.permission('ROLE_ADMIN')")
    @PostMapping("insert")
    public Map<String, Object> insertUser(@RequestBody UserInfo userInfo) {
        userService.insertUser(userInfo);
        return MessageUtil.giveMsg(200, "添加成功");
    }

    //分页展现所有的用户
    @PreAuthorize("@ps.permission('ROLE_ADMIN') ")
    @GetMapping("list")
    public PageInfo<UserInfo> findAll(UserPage userPage) {
        return userService.findAll(userPage);
    }

    //获取固定用户的信息
    @PreAuthorize("@ps.permission('ROLE_ADMIN')")
    @GetMapping("info/{id}")
    public UserInfo findById(@PathVariable("id") Integer id) {
        return userService.findById(id);
    }

    //修改用户信息
    @PreAuthorize("@ps.permission('ROLE_USER')")
    @PutMapping("update")
    public Map<String, Object> updateUser(@RequestBody UserInfo userInfo) {
        userService.updateUser(userInfo);
        return MessageUtil.giveMsg(200, "用户修改成功");
    }

    //删除用户信息
    @PreAuthorize("@ps.permission('ROLE_USER')")
    @DeleteMapping("del/{id}")
    public Map<String, Object> updateUser(@PathVariable Integer id) {
        userService.delUser(id);
        return MessageUtil.giveMsg(200, "用户删除成功");
    }

}
