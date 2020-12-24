package cn.corgy.blog.entity;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;             //用户编号
    private String name;        //用户名称
    private String username;    //用户账号
    private String password;    //用户密码
    private String mail;        //用户邮箱
    private String phone;       //用户电话
    private String sex;         //用户性别
    private String status;      //用户状态
    private List<ArticleInfo> articleInfoList; //用户拥有的文章列表
}
