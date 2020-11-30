package cn.corgy.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class ArticleInfo {
    private int id;            //文章id
    private String title;      //文章标题
    private String context;    //文章内容
    private String preview;    //文章略语
    private String images;     //文章图片链接
    private Date time;          //文件创建链接
    private int readNum;       //阅读数量
    private UserInfo userInfo; //属于谁的文章
    private TypeInfo typeInfo; //文章的类型
}
