package cn.corgy.service;

import cn.corgy.entity.RoleInfo;

public interface RoleService {

    //查询用户权限 通过roleId
    RoleInfo findByRoleId(Integer roleId);
}
