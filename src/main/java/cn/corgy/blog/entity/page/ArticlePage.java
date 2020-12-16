package cn.corgy.blog.entity.page;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticlePage {
    private Integer pageNum = 1; //默认第一页
    private Integer pageSize = 10;//每页的行数
    private String title;  //标题
    private String userId; //用户的id
}
