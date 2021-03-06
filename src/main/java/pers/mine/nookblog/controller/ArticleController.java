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
import pers.mine.nookblog.service.ICommonService;
import pers.mine.nookblog.utils.FreeMarkerUtil;
import pers.mine.nookblog.utils.StringX;
import pers.mine.nookblog.utils.WebKit;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
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
    @Autowired
    private ICommonService commonService;

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
        one.setAddress(commonService.getCityByIP(ip));
        //转义md内容左右尖括号
        String content = one.getContent();
        if (!StringX.isEmpty(content)) {
            content = content.replace("<", "&lt;").replace(">", "&gt;");
        }
        one.setContent(content);
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

        String cityName = commonService.getCityByIP(ip);
        String address = StringX.nvl(one.getAddress(), "unknown");
        if (!"unknown".equals(cityName)) {
            address = cityName;
        }
        one.setAddress(address);
        //转义md内容左右尖括号
        String content = one.getContent();
        if (!StringX.isEmpty(content)) {
            content = content.replace("<", "&lt;").replace(">", "&gt;");
        }
        one.setContent(content);
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
                    return R.failed(String.format("id:[%s]不合法", id));
                }
                article.setStatus("VALID");
                service.updateStatusById(article);
                blogPageService.updatePageFile(aPath, article.getId() + "");
                successNum++;
            } catch (Exception e) {
                log.warn("id:{}文章发布异常", id, e);
            }
        }
        return R.ok("").setMsg(String.format("总数: %s 项,成功: %s 项", ids.length, successNum));
    }

    @PostMapping("/reCreateAll")
    public R<String> reCreateAll() {
        //查询所有已发布文章
        List<Article> aList = service.list(
                new QueryWrapper<Article>().lambda()
                        .eq(Article::getStatus, "VALID")
                        .orderByAsc(Article::getId));
        if (aList == null || aList.isEmpty()) {
            return R.failed("未找到已发布文章!");
        }
        Code blogDirPathCode = codeService.getCodeItem(Code.SYS_CONFIG, "BlogDirPath");
        String aPath = blogDirPathCode.getValue() + File.separator + "article";
        int successNum = 0;
        for (Article article : aList) {
            try {
                blogPageService.updatePageFile(aPath, article.getId() + "");
                successNum++;
            } catch (Exception e) {
                log.warn("id:" + article.getId() + "文章重新发布异常", e);
            }
        }
        return R.ok("").setMsg(String.format("总数: %s 项,成功: %s 项", aList.size(), successNum));
    }


    /**
     * 生成博客标题页（首页，时间轴，实验室，关于）
     */
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
        return R.ok("").setMsg(String.format("总数: %s 项,成功: %s 项", ids.length, successNum));
    }

    /**
     * 将前端博客页复制到指定路径【注:旧数据需手动删除】
     */
    @PostMapping("/initBlog")
    public R<String> initBlog() {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = null;
        try {
            resources = resolver.getResources("static/blog/**");
        } catch (IOException e) {
            log.warn("初始化博客：发生异常", e);
            return R.failed(String.format("初始化博客：发生异常,%s", e.getMessage()));
        }
        Code blogDirPathCode = codeService.getCodeItem(Code.SYS_CONFIG, "BlogDirPath");
        String aPath = blogDirPathCode.getValue();
        int successFile = 0;
        for (Resource resource : resources) {
            try {
                FreeMarkerUtil.copyResourceToFile(resource, aPath, "/static/blog/");
                successFile++;
            } catch (Exception e) {
                log.warn("复制资源 {} 发生异常：", resource.getDescription(), e);
            }
        }
        return R.ok("").setMsg(String.format("初始化博客：共复制%s个文件到[%s]路径,成功%s个", resources.length, aPath, successFile));
    }

    @PostMapping("/cleanBlog")
    public R<String> cleanBlog() {
        Code blogDirPathCode = codeService.getCodeItem(Code.SYS_CONFIG, "BlogDirPath");
        String aPath = blogDirPathCode.getValue();
        if (!StringX.isEmpty(aPath)) {
            FreeMarkerUtil.deleteDir(aPath + File.separator + "css");
            FreeMarkerUtil.deleteDir(aPath + File.separator + "img");
            FreeMarkerUtil.deleteDir(aPath + File.separator + "js");
            FreeMarkerUtil.deleteDir(aPath + File.separator + "lib");
            return R.ok("").setMsg("清空静态目录成功(排除文章发布目录)");
        } else {
            return R.failed("清空静态目录失败!");
        }
    }
}
