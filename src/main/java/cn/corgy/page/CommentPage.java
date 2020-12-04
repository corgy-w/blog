package cn.corgy.page;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentPage {
    private Integer pageNum = 1;    //默认第一页
    private Integer pageSize = 10;  //每页的行数
    private String comment;         //留言内容
    private String userId;          //用户的id
    private String articleId;       //文章id
}
