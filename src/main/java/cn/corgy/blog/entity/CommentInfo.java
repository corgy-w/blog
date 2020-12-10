package cn.corgy.blog.entity;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Time;

/**
 * comments
 *
 * @author corgy
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommentInfo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer id;       //留言ID
    private String comContent;//留言内容
    private Integer articleId;//文章
    private Integer userId;   //留言者
    private Time commentTime;     //留言时间
}