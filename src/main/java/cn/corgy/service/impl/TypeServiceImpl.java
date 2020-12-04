package cn.corgy.service.impl;

import cn.corgy.entity.TypeInfo;
import cn.corgy.mapper.TypeMapper;
import cn.corgy.service.TypeService;
import cn.corgy.utils.AssertUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Resource
    private TypeMapper typeMapper;

    //添加文章类型
    @Override
    public void insertType(TypeInfo typeInfo) {
        Integer integer = typeMapper.insertType(typeInfo);
        AssertUtil.istrue(integer < 1, "添加失败");
    }

    //查询所有的文章类型
    @Override
    public List<TypeInfo> findAllByType() {
        return typeMapper.findAllByType();
    }

    //修改的方法
    @Override
    public void updateType(TypeInfo typeInfo) {
        Integer integer = typeMapper.updateType(typeInfo);
        AssertUtil.istrue(integer < 1, "修改失败");
    }

    //删除的方法
    @Override
    public void delType(Integer id) {
        Integer integer = typeMapper.delType(id);
        AssertUtil.istrue(integer < 1, "删除失败");
    }


}
