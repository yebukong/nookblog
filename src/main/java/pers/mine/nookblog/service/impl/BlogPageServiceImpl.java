package pers.mine.nookblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.mine.nookblog.entity.Article;
import pers.mine.nookblog.entity.Code;
import pers.mine.nookblog.service.IArticleService;
import pers.mine.nookblog.service.IBlogPageService;
import pers.mine.nookblog.service.ICodeService;
import pers.mine.nookblog.utils.FreeMarkerUtil;
import pers.mine.nookblog.utils.StringX;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author yebukong
 * @description 博客页面相关服务
 * @since 2019-01-23 01:05
 */
@Service
public class BlogPageServiceImpl implements IBlogPageService {
    @Autowired
    private ICodeService codeService;
    @Autowired
    private IArticleService articleService;


    public Map<String, Object> getIndexPageAttribute() {
        Map<String, Object> result = new HashMap<>(3);
        LambdaQueryWrapper<Article> lqw = new QueryWrapper<Article>().lambda();
        //noinspection unchecked
        lqw.select(Article::getId,
                Article::getType,
                Article::getTags,
                Article::getBrief,
                Article::getTitle,
                Article::getAddress,
                Article::getWordCount,
                Article::getStatus,
                Article::getCreated,
                Article::getModified)
                .eq(Article::getStatus, "VALID")
                .orderByDesc(Article::getCreated);
        IPage<Article> articlePi = articleService.page(new Page<Article>(1, 5), lqw);
        //TODO 这里可以优化
        List<Code> articleTypes = codeService.getCodeItems(Code.ARTICLE_TYPE);
        List<Code> articleTags = codeService.getCodeItems(Code.ARTICLE_TAG);
        result.put("articles", articlePi.getRecords());
        result.put("articleTypes", articleTypes);
        result.put("articleTags", articleTags);
        return result;
    }

    public Map<String, Object> getTimeLinePageAttribute(String year) {
        Map<String, Object> result = new HashMap<>(4);

        int beginYear = 2018;
        int curYear = Calendar.getInstance().get(Calendar.YEAR);
        if (curYear < 2018) curYear = 2018;
        List<String> years = new ArrayList<>();
        for (int i = 2018; i <= curYear; i++) {
            years.add(i + "");
        }
        year = StringX.nvl(year, curYear + "");
        LambdaQueryWrapper<Article> lqw = new QueryWrapper<Article>().lambda();
        //noinspection unchecked
        lqw.select(Article::getId,
                Article::getType,
                Article::getTags,
                Article::getTitle,
                Article::getWordCount,
                Article::getStatus,
                Article::getCreated,
                Article::getModified)
                .eq(Article::getStatus, "VALID")
                .likeRight(Article::getCreated, year)
                .orderByDesc(Article::getCreated);
        int count = articleService.count(new QueryWrapper());
        List<Article> articles = articleService.list(lqw);
        result.put("year", year);//查询年份
        result.put("years", years);//所有年份
        result.put("count", count + "");
        result.put("articles", articles);
        return result;
    }

    public Map<String, Object> getUnfinishAttribute() {
        return new HashMap<>();
    }

    public Map<String, Object> getAboutMeAttribute() {
        return new HashMap<>();
    }

    public Map<String, Object> getSingleArticlePageAttribute(Long id) {
        Map<String, Object> result = new HashMap<>(3);

        Article article = articleService.getById(id);
        Code articleTypeCode = codeService.getCodeItem(Code.ARTICLE_TYPE, article.getType());
        result.put("article", article);
        //标签转换为List
        result.put("articleTags", StringX.nvl(article.getTags(), "UnTag").split("/"));
        result.put("articleTypeName", articleTypeCode.getItemName());
        return result;
    }

    public void delPageFile(String path, String pageID) throws IOException{
        String targetFile = path + File.separator + pageID + BLOG_PAGE_FILE_SUFFIX;
        File file = new File(targetFile);
        if(file.exists()){
            file.delete();
        }
        return;
    }

    public void updatePageFile(String path, String pageID) throws Exception {
        String templateName = "";
        Map<String, Object> dataModel = null;
        switch (pageID){
            case INDEX_BLOG_PAGE_ID :
                templateName = "blog/index.ftl";
                dataModel = this.getIndexPageAttribute();
                break;
            case TIME_LINE_BLOG_PAGE_ID :
                templateName = "blog/timeline.ftl";
                dataModel = this.getTimeLinePageAttribute("");
                break;
            case UNFINISH_BLOG_PAGE_ID :
                templateName = "blog/unfinish.ftl";
                dataModel = this.getUnfinishAttribute();
                break;
            case ABOUT_ME_BLOG_PAGE_ID :
                templateName = "blog/about-me.ftl";
                dataModel = this.getAboutMeAttribute();
                break;
            default:
                templateName = "blog/article/single.ftl";
                dataModel = this.getSingleArticlePageAttribute(Long.valueOf(pageID));
        }
        String targetFile = path + File.separator + pageID + BLOG_PAGE_FILE_SUFFIX;
        FreeMarkerUtil.processFileWithTemplate(templateName,targetFile,dataModel);
        return;
    }
}
