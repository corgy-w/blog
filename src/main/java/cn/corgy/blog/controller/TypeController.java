package cn.corgy.blog.controller;


import cn.corgy.blog.entity.TypeInfo;
import cn.corgy.blog.service.TypeService;
import cn.corgy.blog.utils.MessageUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 文章类型主要类
 */
@RestController
@RequestMapping("type")
public class TypeController {
    @Resource
    private TypeService typeService;


    //添加文章类型
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("insert")
    public Map<String, Object> insertType(@RequestBody TypeInfo typeInfo) {
        typeService.insertType(typeInfo);
        return MessageUtil.giveMsg(200, "类型添加成功");
    }

    //获取所有的类型信息
    @GetMapping("list")
    public List<TypeInfo> findAllByType() {
        return typeService.findAllByType();
    }

    //修改信息
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("update")
    public Map<String, Object> updateType(@RequestBody TypeInfo typeInfo) {
        typeService.updateType(typeInfo);
        return MessageUtil.giveMsg(200, "类型修改成功");
    }

    //删除信息
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("del/{id}")
    public Map<String, Object> delType(@PathVariable("id") Integer id) {
        typeService.delType(id);
        return MessageUtil.giveMsg(200, "类型删除成功");
    }

}
