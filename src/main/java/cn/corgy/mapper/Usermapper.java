package cn.corgy.mapper;

import cn.corgy.entity.UserInfo;
import cn.corgy.page.UserPage;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface Usermapper {


    //添加用户
    @Insert("insert into users " +
            "values(null,#{name},#{username},#{password},#{mail},#{phone},#{sex},#{status})")
    Integer insertUser(UserInfo userInfo);

    //查询所有用户的信息  管理员查看所有用户的信息  单纯返回基本信息
    @Select({
            "<script>",
            "select id,name,username,mail,phone,sex,status from users",
            "<where>",
            "<if test='null!=username'> ",
            "and username LIKE CONCAT('%',#{username},'%')",
            "</if>",
            "</where>",
            "</script>",
    })
    List<UserInfo> findAll(UserPage userPage);

//    //获取基本消息
//    @Select("select * from users where id=#{userId}")
//    UserInfo findByIdone(Integer userId);

    //通过用户的主键获取他的信息 查询当前用户的信息 通过用户获取他的文章 一对多
    @Select("select id,name,username,mail,phone,sex,status from users where id=#{userId}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "username", property = "username"),
//            @Result(column = "password", property = "password"),
            @Result(column = "mail", property = "mail"),
            @Result(column = "phone", property = "phone"),
            @Result(column = "sex", property = "sex"),
            @Result(column = "status", property = "status"),
            @Result(
                    property = "articleInfoList",
                    column = "id",
                    javaType = List.class,
                    many = @Many(select = "cn.corgy.mapper.ArticleMapper.findByUserId")
            )
    })
    UserInfo findById(Integer userId);

    //通过用户名查找密码
    @Select("select * from users where username=#{username}")
    UserInfo findByusername(String username);


    //修改用户信息
    @Update("update users set " +
            "name=#{name}," +
            "username=#{username}," +
            "password=#{password}," +
            "mail=#{mail}," +
            "phone=#{phone}," +
            "sex=#{sex}" +
            " where id=#{id}")
    Integer updateUser(UserInfo userInfo);

    //删除用户信息
    @Delete("delete from users where id=#{id}")
    Integer delUser(Integer id);
}
