package pers.mine.nookblog.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pers.mine.nookblog.NookblogApplication;
import pers.mine.nookblog.service.IBlogPageService;

/**
 * @author yebukong
 * @description idea使用ctrl+shift+t生成测试类 :单元测试
 * @since 2019-04-08 17:03
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
@SpringBootTest(classes= NookblogApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BlogPageServiceImplTest {
    @Autowired
    ApplicationContext ctx;
    @Test
    public void getIndexPageAttribute() {
        IBlogPageService blogPageServiceImpl = ctx.getBean(IBlogPageService.class);
        System.out.println(blogPageServiceImpl);
    }

    @Test
    public void getTimeLinePageAttribute() {
    }

    @Test
    public void getUnfinishAttribute() {
    }

    @Test
    public void getAboutMeAttribute() {
    }

    @Test
    public void getSingleArticlePageAttribute() {
    }

    @Test
    public void delPageFile() {
    }

    @Test
    public void updatePageFile() {
    }
}