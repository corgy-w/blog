package cn.corgy.mapper;

import cn.corgy.entity.UserRole;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


//获取权限信息
@Mapper
public interface UserRoleMapper {
    //通过userid查询用户类型
    @Select("select * from user_role where userId=#{userId}")
    List<UserRole> findByUserId(Integer userId);

    //添加权限
    @Insert("insert into user_role values(#{userId},#{roleId})")
    Integer insertCompetence(UserRole userRole);
}
