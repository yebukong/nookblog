package pers.mine.nookblog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
@EnableFeignClients(basePackages = {"pers.mine.nookblog.rpc"})
public class NookblogApplication extends SpringBootServletInitializer {
    protected final static Logger logger = LoggerFactory.getLogger(NookblogApplication.class);

    private static volatile ConfigurableApplicationContext context;
    private static volatile long applicationStartTime = -1;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(NookblogApplication.class);
        app.setBannerMode(Banner.Mode.CONSOLE);
        context = app.run(args);
        applicationStartTime = System.currentTimeMillis();
        logger.info("NookblogApplication started!");
    }

    /**
     * 软重启
     */
    public static synchronized void restart() {
        Thread thread = new Thread(() -> {
            try {
                applicationStartTime = -1;
                Thread.sleep(2000);
                synchronized (context) {
                    ApplicationArguments args = context.getBean(ApplicationArguments.class);
                    context.close();
                    SpringApplication app = new SpringApplication(NookblogApplication.class);
                    app.setBannerMode(Banner.Mode.CONSOLE);
                    context = app.run(args.getSourceArgs());
                    applicationStartTime = System.currentTimeMillis();
                    logger.info("NookblogApplication restarted!");
                }
            } catch (Exception e) {
                logger.warn("NookblogApplication started error", e);
            }
        });
        thread.setDaemon(false);
        thread.start();
    }

    /**
     * 硬重启
     */
    public static synchronized void deepRestart() {

    }

    public static long getApplicationStartTime() {
        return applicationStartTime;
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
