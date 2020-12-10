package cn.corgy.blog.entity.page;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPage {
    private Integer pageNum = 1; //默认第一页
    private Integer pageSize = 5;//每页的行数
    private String username;  //用户名
}
