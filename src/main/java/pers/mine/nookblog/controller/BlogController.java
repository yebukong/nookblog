package pers.mine.nookblog.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pers.mine.nookblog.entity.Article;
import pers.mine.nookblog.entity.Code;
import pers.mine.nookblog.service.IArticleService;
import pers.mine.nookblog.service.IBlogPageService;
import pers.mine.nookblog.service.ICodeService;
import pers.mine.nookblog.utils.MineUtil;
import pers.mine.nookblog.utils.StringX;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author yebukong
 * @description 前端Blog页面
 * @since 2018-10-29 0:43
 */
@Slf4j
@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private IArticleService articleService;
    @Autowired
    private ICodeService codeService;
    @Autowired
    private IBlogPageService blogPageService;

    /**重定向首页*/
    @GetMapping(value = {"/"})
    public String blogIndex() {
        return  "redirect:/blog/index.html";
    }

    /**首页*/
    @GetMapping(value = {"/index.html"})
    public String blogIndex(Model model) {
        model.addAllAttributes(blogPageService.getIndexPageAttribute());
        return "blog/index";
    }

    /**时间轴 */
    @GetMapping(value = {"/timeline.html"})
    public String blogtimeline(@RequestParam(value ="year",required = false)String year, Model model) {
        model.addAllAttributes(blogPageService.getTimeLinePageAttribute(year));
        return "blog/timeline";
    }

    /** 关于我 */
    @GetMapping(value = {"/about-me.html"})
    public String about_me(Model model) {
        model.addAllAttributes(blogPageService.getAboutMeAttribute());
        return "blog/about-me";
    }

    /** 未完成页面 */
    @GetMapping(value = {"/unfinish.html"})
    public String unfinish(Model model) {
        model.addAllAttributes(blogPageService.getUnfinishAttribute());
        return "blog/unfinish";
    }

    /**文章页*/
    @GetMapping(value = {"/article/{id}.html"})
    public String single(@PathVariable("id") Long id, Model model) {
        try {
            model.addAllAttributes(blogPageService.getSingleArticlePageAttribute(id));
        } catch (Exception e) {
            log.warn("获取文章: "+id+" 参数发生异常",e);
            return "error/500";
        }
        return "blog/article/single";
    }

    @GetMapping(value = "/articleBriefList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public R<IPage<Article>> tableData(Page<Article> page) {
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
        IPage<Article> articles = articleService.page(page, lqw);
        return R.ok(articles);
    }
}
