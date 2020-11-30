package cn.corgy.service.Ipml;


import cn.corgy.entity.UserRole;
import cn.corgy.mapper.UserRoleMapper;
import cn.corgy.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceIpml implements UserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;

    //查询用户的权限
    @Override
    public List<UserRole> findByUserId(Integer userId) {
        return userRoleMapper.findByUserId(userId);
    }

}
