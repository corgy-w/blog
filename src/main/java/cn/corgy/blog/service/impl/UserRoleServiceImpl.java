package cn.corgy.blog.service.impl;


import cn.corgy.blog.entity.UserRole;
import cn.corgy.blog.mapper.UserRoleMapper;
import cn.corgy.blog.service.UserRoleService;
import cn.corgy.blog.utils.AssertUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Resource
    private UserRoleMapper userRoleMapper;

    //查询用户的权限
    @Override
    public List<UserRole> findByUserId(Integer userId) {
        return userRoleMapper.findByUserId(userId);
    }

    @Override
    public void insertCompetence(UserRole userRole) {
        Integer integer = userRoleMapper.insertCompetence(userRole);
        AssertUtil.istrue(integer < 1, "内部错误联系管理员");
    }

}
