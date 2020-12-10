package cn.corgy.blog.service.impl;

import cn.corgy.blog.entity.RoleInfo;
import cn.corgy.blog.mapper.RoleMapper;
import cn.corgy.blog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public RoleInfo findByRoleId(Integer roleId) {
        return roleMapper.findByRoleId(roleId);
    }
}
