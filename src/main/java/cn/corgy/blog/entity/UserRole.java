package cn.corgy.blog.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserRole implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer userId;  //用户id
    private Integer roleId;  //对应的角色
}
