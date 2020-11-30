package cn.corgy.service;

import cn.corgy.entity.UserInfo;
import cn.corgy.page.UserPage;
import com.github.pagehelper.PageInfo;

/**
 * 用户的主要方法
 */
public interface UserService {
    //分页展现所有的用户
    PageInfo<UserInfo> findAll(UserPage userPage);

    //展现某个人的信息
    UserInfo findById(Integer userId);

    //通过用户名查找信息
    UserInfo findByUsername(String username);

    //修改用户信息
    void updateUser(UserInfo userInfo);

    //删除用户信息
    void delUser(Integer id);

    //添加文章
    void insertUser(UserInfo userInfo);
}

