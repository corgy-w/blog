package cn.corgy.service.impl;

import cn.corgy.entity.UserInfo;
import cn.corgy.entity.UserRole;
import cn.corgy.mapper.UserMapper;
import cn.corgy.mapper.UserRoleMapper;
import cn.corgy.page.UserPage;
import cn.corgy.service.UserService;
import cn.corgy.utils.AssertUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper usermapper;
    @Resource
    private UserRoleMapper userRoleMapper;

    //查看所有用户的信息
    @Override
    public PageInfo<UserInfo> findAll(UserPage userPage) {
        //开启分页
        PageHelper.startPage(userPage.getPageNum(), userPage.getPageSize());
        List<UserInfo> query = usermapper.findAll(userPage);
        return new PageInfo<>(query);
    }

    //查看部分用户的信息
    //登录后进行单用户的查询
    //查看验证自己的信息
    @Override
    public UserInfo findById(Integer userId) {
        return usermapper.findById(userId);
    }

    //通过username查询用户信息
    @Override
    public UserInfo findByUsername(String username) {
        return usermapper.findByusername(username);
    }

    //修改信息
    @Override
    public void updateUser(UserInfo userInfo) {
        Map<String, Object> map = new HashMap<>();
        Integer integer = usermapper.updateUser(userInfo);
        AssertUtil.istrue(integer < 1, "修改失败");
    }

    //删除用户得信息
    @Override
    public void delUser(Integer id) {
        Integer integer = usermapper.delUser(id);
        AssertUtil.istrue(integer < 1, "删除失败");
    }

    @Override
    public void insertUser(UserInfo userInfo) {
        UserInfo temp = usermapper.findByusername(userInfo.getUsername());
        AssertUtil.istrue(ObjectUtil.isNotNull(temp), "用户已经存在");
        Integer integer = usermapper.insertUser(userInfo);
        AssertUtil.istrue(integer < 1, "添加失败");
        //注册的同时赋予基础权限
        UserInfo byUsername = usermapper.findByusername(userInfo.getUsername());
        Integer integer1 = userRoleMapper.insertCompetence(new UserRole(byUsername.getId(), 2));
        AssertUtil.istrue(integer1<1,"添加权限失败 请联系管理员");
    }
}
