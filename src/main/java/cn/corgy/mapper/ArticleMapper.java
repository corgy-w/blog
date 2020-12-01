package cn.corgy.mapper;

import cn.corgy.entity.ArticleInfo;
import cn.corgy.entity.TypeInfo;
import cn.corgy.entity.UserInfo;
import cn.corgy.page.ArticlePage;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface ArticleMapper {

    /**
     * 通过文章id获取信息
     *
     * @param articleId 文章id
     * @return ArticleInfo 文章信息
     */
    @Select("select * from articles where id=#{articleId}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "title", property = "title"),
            @Result(column = "context", property = "context"),
            @Result(column = "preview", property = "preview"),
            @Result(column = "images", property = "images"),
            @Result(column = "releaseTime", property = "time"),
            @Result(column = "readNum", property = "readNum"),
            @Result(
                    property = "userInfo",
                    javaType = UserInfo.class,
                    column = "userId",
                    one = @One(select = "cn.corgy.mapper.UserMapper.findByIdone")
            ),
    })
    ArticleInfo findByArticleId(Integer articleId);

    /**
     * 联名查询 通过用户名查询文章
     *
     * @param userId 用户id
     * @return 文章对象
     */
    @Select("select * from articles where userId=#{userId}")
    ArticleInfo findByUserId(Integer userId);

    /**
     * 添加一文章
     *
     * @param articleInfo 文章的对象
     * @return 更改的行数
     */
    @Insert("insert into articles " +
            "values(null,#{title},#{context},#{preview},#{images},#{time},#{readNum},#{userInfo.id},#{typeInfo.id}) ")
    Integer insertArticle(ArticleInfo articleInfo);

    /**
     * 通过分页获取所有的文章
     *
     * @param articlePage 传入分页的对象
     * @return 文章的列表
     */
    @Select({
            "<script>",
            "select * from articles",
            "<where>",
            "<if test='null!=title'> ",
            "and title LIKE CONCAT('%',#{title},'%')",
            "</if>",
            "<if test='null!=userId'> ",
            "and userId =#{userId}",
            "</if>",
            "</where>",
            "</script>",
    })
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "title", property = "title"),
            @Result(column = "context", property = "context"),
            @Result(column = "preview", property = "preview"),
            @Result(column = "images", property = "images"),
            @Result(column = "releaseTime", property = "time"),
            @Result(column = "readNum", property = "readNum"),
            @Result(
                    property = "typeInfo",
                    javaType = TypeInfo.class,
                    column = "typeId",
                    one = @One(select = "cn.corgy.mapper.TypeMapper.findByTypeId")
            )
    })
    List<ArticleInfo> findByQuery(ArticlePage articlePage);

    /**
     * 修改文章信息
     * 不包含阅读量
     *
     * @param articleInfo 要修改的文章对象
     * @return 受影响的行数
     */
    @Update("update articles set " +
            "title=#{title}," +
            "context=#{context}," +
            "preview=#{preview}," +
            "images=#{images}," +
            "releaseTime=#{time}," +
            "userId=#{userInfo.id}," +
            "typeId=#{typeInfo.id} " +
            "where id=#{id}")
    Integer updateArticle(ArticleInfo articleInfo);


    /**
     * 根据id删除文章
     *
     * @param id 文章id
     * @return 受影响的行数
     */
    @Delete("delete from articles where id=#{id}")
    Integer delArticle(Integer id);
}