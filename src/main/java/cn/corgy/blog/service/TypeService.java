package cn.corgy.blog.service;

import cn.corgy.blog.entity.TypeInfo;

import java.util.List;


public interface TypeService {

    //添加文章类型
    void insertType(TypeInfo typeInfo);

    //查询所有的文章类型
    List<TypeInfo> findAllByType();

    //修改文章类型
    void updateType(TypeInfo typeInfo);

    //删除文章类型
    void delType(Integer id);
}
