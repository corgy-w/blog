package cn.corgy.service;

import cn.corgy.entity.UserRole;

import java.util.List;

public interface UserRoleService {
    List<UserRole> findByUserId(Integer userId);
}
