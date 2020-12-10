package cn.corgy.blog.service;

import cn.corgy.blog.entity.UserRole;

import java.util.List;

public interface UserRoleService {

    //查询用户之间的关系
    List<UserRole> findByUserId(Integer userId);

    //添加用户权限
    void insertCompetence(UserRole userRole);
}
