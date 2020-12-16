package cn.corgy.blog.mapper;

import cn.corgy.blog.entity.TypeInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TypeMapper {

    //增加文章的类型
    @Insert("insert into types(name,introduction) values(#{name},#{introduction})")
    Integer insertType(TypeInfo typeInfo);

    //通过id获取文章的类型
    //暂时没用mapper
    @Select("select * from types where id=#{typeId}")
    TypeInfo findByTypeId();

    //查看所有文章类型
    @Select("select * from types")
    List<TypeInfo> findAllByType();

    //修改类型信息
    @Update("update types set name=#{name},introduction=#{introduction} where id=#{id}")
    Integer updateType(TypeInfo typeInfo);

    //根据id删除没某个类型的对象
    @Delete("delete from types where id=#{id}")
    Integer delType(Integer id);
}
