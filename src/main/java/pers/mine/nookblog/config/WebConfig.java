package pers.mine.nookblog.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pers.mine.nookblog.interceptor.RequestLogInterceptor;

/**
 * Spring MVC 配置
 */
@Configuration(proxyBeanMethods = false)
@Slf4j
public class WebConfig implements WebMvcConfigurer {
    @Value("${app.request-log.enabled:false}")
    private boolean requestLogEnabled;
    
    @Autowired
    private RequestLogInterceptor requestLogInterceptor;

    @Bean
    public RequestLogInterceptor requestLogInterceptor() {
        return new RequestLogInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (requestLogEnabled) {
            registry.addInterceptor(requestLogInterceptor);
        }
    }
}
