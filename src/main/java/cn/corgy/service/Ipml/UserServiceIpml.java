package cn.corgy.service.Ipml;

import cn.corgy.entity.UserInfo;
import cn.corgy.mapper.Usermapper;
import cn.corgy.page.UserPage;
import cn.corgy.service.UserService;
import cn.corgy.utils.AssertUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceIpml implements UserService {
    @Resource
    private Usermapper usermapper;

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
        Integer integer = usermapper.insertUser(userInfo);
        AssertUtil.istrue(integer < 1, "删除失败");
    }
}
