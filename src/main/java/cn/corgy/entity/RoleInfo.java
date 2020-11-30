package cn.corgy.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RoleInfo {
    private int id; //角色id
    private String roleName;    //角色名称
    private String RolrIntroduction;    //角色简介
}
