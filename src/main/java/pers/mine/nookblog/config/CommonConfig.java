package pers.mine.nookblog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yebukong
 * @description 项目通用配置信息
 * @since 2018-10-23 11:00
 * 注:继承extends  WebMvcConfigurationSupport 会导致自动配置失效
 */
@Configuration
public class CommonConfig {
    @Autowired
    private freemarker.template.Configuration freemarkerConfig;
    @Autowired
    private Environment env;

    // Spring 初始化执行
    @PostConstruct
    public void setConfigure() throws Exception {
        //配置全局属性
        Map<String,Object> gitalkConfig = new HashMap<>();
        gitalkConfig.put("clientID",env.getProperty("gitalkConfig.clientID"));
        gitalkConfig.put("clientSecret",env.getProperty("gitalkConfig.clientSecret"));
        gitalkConfig.put("repo",env.getProperty("gitalkConfig.repo"));
        gitalkConfig.put("owner",env.getProperty("gitalkConfig.owner"));
        gitalkConfig.put("admin", env.getProperty("gitalkConfig.admin"));
        freemarkerConfig.setSharedVariable("GitalkConfig", gitalkConfig);
    }

}
