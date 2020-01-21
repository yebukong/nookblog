package pers.mine.nookblog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
@EnableFeignClients(basePackages = {"pers.mine.nookblog.rpc"})
public class NookblogApplication extends SpringBootServletInitializer {
    protected final static Logger logger = LoggerFactory.getLogger(NookblogApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(NookblogApplication.class);
        app.setBannerMode(Banner.Mode.CONSOLE);
        app.run(args);
        logger.info("NookblogApplication started!");
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            logger.debug("spring管理的beans:");
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
