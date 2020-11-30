package cn.corgy.mapper;

import cn.corgy.entity.RoleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RoleMapper {
    //查询用户权限
    @Select("select * from roles where id=#{roleId}")
    RoleInfo findByRoleId(Integer roleId);
    //添加用户
}
