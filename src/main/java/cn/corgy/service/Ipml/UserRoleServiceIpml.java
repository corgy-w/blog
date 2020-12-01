package cn.corgy.service.Ipml;


import cn.corgy.entity.UserRole;
import cn.corgy.mapper.UserRoleMapper;
import cn.corgy.service.UserRoleService;
import cn.corgy.utils.AssertUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserRoleServiceIpml implements UserRoleService {
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
