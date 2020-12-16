package cn.corgy.blog.service;

import cn.corgy.blog.entity.RoleInfo;

public interface RoleService {

    //查询用户权限 通过roleId
    RoleInfo findByRoleId(Integer roleId);
}
