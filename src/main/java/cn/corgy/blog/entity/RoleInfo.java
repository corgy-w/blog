package cn.corgy.blog.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class RoleInfo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private int id; //角色id
    private String roleName;    //角色名称
    private String RolrIntroduction;    //角色简介
}
