package cn.corgy.controller;


import cn.corgy.entity.TypeInfo;
import cn.corgy.security.LoginUser;
import cn.corgy.service.TypeService;
import cn.corgy.utils.MessageUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("type")
public class TypeController {
    @Resource
    private TypeService typeService;

    //抛出登录者信息
    private static LoginUser getLoginUser() {
        return (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    //添加文章类型
    @PreAuthorize("@ps.permission('ROLE_ADMIN')")
    @PostMapping("insert")
    public Map<String, Object> insertType(@RequestBody TypeInfo typeInfo) {
        typeService.insertType(typeInfo);
        return MessageUtil.giveMsg(200, "类型添加成功");
    }

    //获取所有的类型信息
//    @PreAuthorize("@ps.permission('ROLE_USER')")
    @GetMapping("list")
    public List<TypeInfo> findAllByType() {
        return typeService.findAllByType();
    }

    //修改信息
    @PreAuthorize("@ps.permission('ROLE_ADMIN')")
    @PutMapping("update")
    public Map<String, Object> updateType(@RequestBody TypeInfo typeInfo) {
        typeService.updateType(typeInfo);
        return MessageUtil.giveMsg(200, "类型修改成功");

    }

    //删除信息
    @PreAuthorize("@ps.permission('ROLE_ADMIN')")
    @DeleteMapping("del/{id}")
    public Map<String, Object> delType(@PathVariable("id") Integer id) {
        typeService.delType(id);
        return MessageUtil.giveMsg(200, "类型删除成功");
    }

}
