package cn.corgy.service.Ipml;

import cn.corgy.entity.ArticleInfo;
import cn.corgy.entity.TypeInfo;
import cn.corgy.mapper.ArticleMapper;
import cn.corgy.page.ArticlePage;
import cn.corgy.service.ArticleService;
import cn.corgy.utils.AssertUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;

    //添加文章 返回信息
    @Override
    public void insertArticle(ArticleInfo articleInfo) {
        Map<String, Object> map = new HashMap<>();
        Integer integer = articleMapper.insertArticle(articleInfo);
        AssertUtil.istrue(integer < 1, "添加失败");

    }


    //查询所有的文章
    @Override
    public PageInfo<ArticleInfo> findByQuery(ArticlePage articlePage) {
        PageHelper.startPage(articlePage.getPageNum(), articlePage.getPageSize());
        //查询操作
        List<ArticleInfo> query = articleMapper.findByQuery(articlePage);
        return new PageInfo<>(query);
    }


    @Override
    public void updateArticle(ArticleInfo articleInfo) {
        Integer integer = articleMapper.updateArticle(articleInfo);
        AssertUtil.istrue(integer < 1, "修改失败");
    }

    @Override
    public void delArticle(Integer id) {
        Integer integer = articleMapper.delArticle(id);
        AssertUtil.istrue(integer < 1, "删除失败");
    }
}
