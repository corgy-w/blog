package cn.corgy.service.impl;

import cn.corgy.entity.RoleInfo;
import cn.corgy.mapper.RoleMapper;
import cn.corgy.service.RoleService;
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
