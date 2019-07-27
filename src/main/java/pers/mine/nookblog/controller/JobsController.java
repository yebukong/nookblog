package pers.mine.nookblog.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.mine.nookblog.entity.Article;
import pers.mine.nookblog.entity.Code;
import pers.mine.nookblog.service.IArticleService;
import pers.mine.nookblog.service.ICodeService;
import pers.mine.nookblog.utils.FreeMarkerUtil;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yebukong
 * @description 任务
 * @since 2018-11-07 1:21
 */
@Slf4j
@RestController
@RequestMapping("/job")
public class JobsController {
    @Autowired
    private IArticleService articleService;
    @Autowired
    private ICodeService codeService;

    @PostMapping("/createIndexHtml")
    public R<String> createHtml() {
        Code blogDirPathCode = codeService.getCodeItem(Code.SYS_CONFIG, "BlogDirPath");
        String blogDirPath = blogDirPathCode.getValue();
        String filePath = blogDirPath+ File.separator+"index.html";
        LambdaQueryWrapper<Article> lqw = new QueryWrapper<Article>().lambda();
        //noinspection unchecked
        lqw.select( Article::getId,
                 Article::getType,
                 Article::getTags,
                 Article::getBrief,
                 Article::getTitle,
                Article::getAddress,
                Article::getWordCount,
                 Article::getStatus,
                 Article::getCreated,
                 Article::getModified)
                .orderByDesc(Article::getCreated);
        IPage<Article> articlePi = articleService.page(new Page<Article>(1, 3), lqw);
        List<Code> articleTypes = codeService.getCodeItems(Code.ARTICLE_TYPE);
        List<Code> articleTags = codeService.getCodeItems(Code.ARTICLE_TAG);
        Map<String,Object> dataModel = new HashMap<>();
        dataModel.put("articles",articlePi.getRecords());
        dataModel.put("articleTypes",articleTypes);
        dataModel.put("articleTags",articleTags);
        FreeMarkerUtil.processFileWithTemplate("blog/index.ftl",filePath,dataModel);
        return R.ok("");
    }
}
