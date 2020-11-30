package cn.corgy.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserRole {
    private Integer userId;  //用户id
    private Integer roleId;  //对应的角色
}
