package pers.mine.nookblog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;

@EnableTransactionManagement
@SpringBootApplication
@ComponentScan(basePackages = {
        "pers.mine.nookblog.config",
        "pers.mine.nookblog.controller",
        "pers.mine.nookblog.service",
        "pers.mine.nookblog.rpc",
        "pers.mine.nookblog.utils",
        "pers.mine.nookblog.entity",
        "pers.mine.nookblog.task"})
@EnableCaching //开启缓存
@EnableScheduling //开启定时任务
@EnableFeignClients(basePackages = { "pers.mine.nookblog.rpc"})
public class NookblogApplication extends SpringBootServletInitializer {
    protected final static Logger logger = LoggerFactory.getLogger(NookblogApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(NookblogApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
        logger.info("NookblogApplication 启动成功!");
    }

    /**
     * 控制台打印spring管理的Bean
     */
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            logger.debug("Let's inspect the beans provided by Spring Boot(检查spring管理的beans):");
            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                logger.debug(beanName);
            }
        };
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(NookblogApplication.class);
    }
}
