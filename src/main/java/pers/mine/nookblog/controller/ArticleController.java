package pers.mine.nookblog.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.bind.annotation.*;
import pers.mine.nookblog.entity.Article;
import pers.mine.nookblog.entity.Code;
import pers.mine.nookblog.service.IArticleService;
import pers.mine.nookblog.service.IBlogPageService;
import pers.mine.nookblog.service.ICodeService;
import pers.mine.nookblog.utils.FreeMarkerUtil;
import pers.mine.nookblog.utils.StringX;
import pers.mine.nookblog.utils.WebKit;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/article")
public class ArticleController extends ApiController {

    @Autowired
    private IArticleService service;
    @Autowired
    private ICodeService codeService;
    @Autowired
    private IBlogPageService blogPageService;


    @GetMapping(value = "/tableData", produces = "application/json;charset=UTF-8")
    public R<IPage<Article>> tableData(Page<Article> page, Article qOne, boolean hasKey) {
        LambdaQueryWrapper<Article> lqw = new QueryWrapper<Article>().lambda();
        //noinspection unchecked
        lqw.select(Article::getId,
                Article::getType,
                Article::getTags,
                Article::getBrief,
                Article::getTitle,
                Article::getStatus)
                .orderByDesc(Article::getCreated);
        if (hasKey) {
            lqw.and(e -> {
                if (!StringX.isEmpty(qOne.getTitle())) e.or().like(Article::getTitle, qOne.getTitle());
                if (!StringX.isEmpty(qOne.getBrief())) e.or().like(Article::getBrief, qOne.getBrief());
                if (!StringX.isEmpty(qOne.getTags())) e.or().like(Article::getTags, qOne.getTags());
                if (!StringX.isEmpty(qOne.getContent())) e.or().like(Article::getContent, qOne.getContent());
                if (!StringX.isEmpty(qOne.getType())) e.or().in(Article::getType, (Object[]) qOne.getType().split(","));
                return e;
            });
        }
        IPage<Article> codes = service.page(page, lqw);
        return R.ok(codes);
    }

    @PostMapping("/add")
    public R<Article> save(HttpServletRequest request, Article one) {
        //检查并插入文章标签信息
        if (!StringX.isEmpty(one.getTags())) {
            codeService.checkAndSaveArticleTags(one.getTags().split("/"));
        }
        String ip = WebKit.ipAddr(request);
        one.setIp(ip);//设置ip
        one.setAddress(WebKit.getCityByIP(ip));
        one.setBrief(StringX.nvl(one.getBrief(), "摘要未填写[Orz]"));//TODO 摘要生成
        one.setStatus("INVALID");//新增默认未发布
        boolean result = service.save(one);
        return result ? R.ok(one) : R.failed("新增出错");
    }


    @PostMapping("/updateStatus")
    public R<Article> updateStatusById(Article one) throws Exception {
        boolean result = service.updateStatusById(one);
        Code blogDirPathCode = codeService.getCodeItem(Code.SYS_CONFIG, "BlogDirPath");
        if (blogDirPathCode != null) {
            String aPath = blogDirPathCode.getValue() + File.separator + "article";
            if ("VALID".equalsIgnoreCase(one.getStatus())) {
                blogPageService.updatePageFile(aPath, one.getId() + "");
            } else {
                blogPageService.delPageFile(aPath, one.getId() + "");
            }
        }
        return result ? R.ok(one) : R.failed("更新状态失败：主键有误？");
    }

    @GetMapping("/initEditData")
    public R<Map<String, Object>> initEditData(String id) {
        List<Code> articleTypes = codeService.getCodeItems(Code.ARTICLE_TYPE);
        Article one = service.getById(id);
        Map<String, Object> data = new HashMap<>();
        data.put("articleTypes", articleTypes);
        data.put("editData", one);
        return R.ok(data);
    }

    @PostMapping("/edit")
    public R<Article> edit(HttpServletRequest request, Article one) {
        if (!StringX.isEmpty(one.getTags())) {
            codeService.checkAndSaveArticleTags(one.getTags().split("/"));
        }
        String ip = WebKit.ipAddr(request);
        one.setIp(ip);//设置ip
        one.setAddress(WebKit.getCityByIP(ip));
        one.setStatus("INVALID");//更新默认未发布
        boolean result = service.updateById(one);
        return result ? R.ok(one) : R.failed("修改出错");
    }

    @PostMapping("/createSingleHtml")
    public R<String> createHtml(@RequestParam(value = "ids[]") String[] ids) {
        if (ids == null || ids.length < 1) {
            return R.failed("未选择发布文章!");
        }
        Code blogDirPathCode = codeService.getCodeItem(Code.SYS_CONFIG, "BlogDirPath");
        String aPath = blogDirPathCode.getValue() + File.separator + "article";
        int successNum = 0;
        for (String id : ids) {
            try {
                Article article = service.getById(id);
                if (article == null) {
                    return R.ok("id:[" + id + "]不合法");
                }
                article.setStatus("VALID");
                service.updateStatusById(article);
                blogPageService.updatePageFile(aPath, article.getId() + "");
                successNum++;
            } catch (Exception e) {
                log.warn("id:" + id + "文章发布异常", e);
            }
        }
        return R.ok("").setMsg("总数:" + ids.length + "项,成功:" + successNum + "项");
    }

    @PostMapping("/createBlogPageHtml")
    public R<String> createBlogPageHtml() {
        String[] ids = {IBlogPageService.INDEX_BLOG_PAGE_ID, IBlogPageService.TIME_LINE_BLOG_PAGE_ID, IBlogPageService.UNFINISH_BLOG_PAGE_ID, IBlogPageService.ABOUT_ME_BLOG_PAGE_ID};
        Code blogDirPathCode = codeService.getCodeItem(Code.SYS_CONFIG, "BlogDirPath");
        String aPath = blogDirPathCode.getValue();
        int successNum = 0;
        for (String id : ids) {
            try {
                blogPageService.updatePageFile(aPath, id);
                successNum++;
            } catch (Exception e) {
                log.warn("id:" + id + "发布异常", e);
            }
        }
        return R.ok("").setMsg("总数:" + ids.length + "项,成功:" + successNum + "项");
    }

    /**
     * 将前端博客页复制到指定路径【注:旧数据需手动删除】
     */
    @PostMapping("/initBlog")
    public R<String> initBlog(){
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = null;
        try {
            resources = resolver.getResources("static/blog/**");
        } catch (IOException e) {
            log.warn("初始化博客：发生异常",e );
            return R.failed("初始化博客：发生异常,"+e.getMessage());
        }
        Code blogDirPathCode = codeService.getCodeItem(Code.SYS_CONFIG, "BlogDirPath");
        String aPath = blogDirPathCode.getValue();
        int successFile = 0;
        for (Resource resource : resources) {
            try {
                FreeMarkerUtil.copyResourceToFile(resource, aPath,"/static/blog/");
                successFile++;
            } catch (Exception e) {
                log.warn("复制资源"+resource.getDescription()+"发生异常："+e.getMessage());
            }
        }
        return R.ok("").setMsg("初始化博客：共复制" + resources.length + "个文件到["+aPath+"]路径,成功"+successFile+"个");
    }
    @PostMapping("/cleanBlog")
    public R<String> cleanBlog(){
        Code blogDirPathCode = codeService.getCodeItem(Code.SYS_CONFIG, "BlogDirPath");
        String aPath = blogDirPathCode.getValue();
        if(!StringX.isEmpty(aPath)){
            FreeMarkerUtil.deleteDir(aPath+File.separator+"css");
            FreeMarkerUtil.deleteDir(aPath+File.separator+"img");
            FreeMarkerUtil.deleteDir(aPath+File.separator+"js");
            FreeMarkerUtil.deleteDir(aPath+File.separator+"lib");
            return R.ok("").setMsg("清空静态目录成功(排除文章发布目录)");
        }else{
            return R.failed("清空静态目录失败!");
        }
    }
}
