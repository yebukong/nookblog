package pers.mine.nookblog.service;

import java.io.IOException;
import java.util.Map;

/**
 * 博客页面 服务层接口
 */
public interface IBlogPageService {
    /**
     * 博客页文件后缀
     */
    final static String BLOG_PAGE_FILE_SUFFIX = ".html";
    /**
     * 首页ID
     */
    final static String INDEX_BLOG_PAGE_ID = "index";
    /**
     * 时间轴页ID
     */
    final static String TIME_LINE_BLOG_PAGE_ID = "timeline";
    /**
     * 未完成页ID
     */
    final static String UNFINISH_BLOG_PAGE_ID = "unfinish";
    /**
     * 关于页ID
     */
    final static String ABOUT_ME_BLOG_PAGE_ID = "about-me";

    /**
     * 获取博客首页参数信息
     */
    Map<String, Object> getIndexPageAttribute();

    /**
     * 获取时间轴页参数信息
     */
    Map<String, Object> getTimeLinePageAttribute(String year);

    /**
     * 获取关于页参数信息
     */
    Map<String, Object> getAboutMeAttribute();

    /**
     * 获取未完成页面参数信息
     */
    Map<String, Object> getUnfinishAttribute();

    /**
     * 获取文章页参数信息
     */
    Map<String, Object> getSingleArticlePageAttribute(Long id);

    /**
     * 删除指定路径指定ID页面
     *
     * @param path   页面路径
     * @param pageID pageID + BLOG_PAGE_FILE_SUFFIX = 文件名
     * @return 操作结果
     */
    void delPageFile(String path, String pageID) throws IOException;


    /**
     * 更新指定路径指定ID页面,不存在则自动生成
     *
     * @param path   页面路径
     * @param pageID pageID + BLOG_PAGE_FILE_SUFFIX = 文件名
     * @return 操作结果
     */
    void updatePageFile(String path, String pageID) throws Exception;

}