package pers.mine.nookblog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.*;
import pers.mine.nookblog.interceptor.AuthHandlerInterceptor;

/**
 * @author yebukong
 * @description 项目web相关配置信息
 * @since 2018-10-23 11:00
 * 注:继承extends  WebMvcConfigurationSupport 会导致自动配置失效
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 拦截器相关配置
     */
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/error","/error/*"
                        ,"/login","/auth","/login.html"
                        ,"/css/**","/js/**","/lib/**","/images/**"
                        ,"/blog/**","/api/**");
    }

    /**
     * CORS 跨域相关配置
     */
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") //配置路径
                .allowedOrigins("*") // 设置你要允许的网站域名，如果全允许则设为 *
                .allowedMethods("GET", "POST") //允许方法
                .allowedHeaders("*")//允许访问的头信息
                .allowCredentials(true) //是否允许请求带有验证信息，XMLHttpRequest请求的withCredentials标志设置为true时，认证通过，浏览器才将数据给脚本程序。
                .maxAge(3600 * 24);//#预检请求的缓存时间（秒），即在这个时间段里，对于相同的跨域请求不会再预检了
    }

    /**
     * 视图解析器配置
     */
    public void configureViewResolvers(ViewResolverRegistry registry) {

    }

    /**
     * 资源处理器
     * 默认路径 org.springframework.boot.autoconfigure.web.ResourceProperties.CLASSPATH_RESOURCE_LOCATIONS
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置缓存策略【配置无效...被某个地方先行响应了】
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .setCacheControl(CacheControl.noStore());
        //setCachePeriod
        //CacheControl.maxAge(10, TimeUnit.MINUTES).cachePrivate()
        //no-store 任何情况都不缓存
        //no-cache
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        /*configurer.favorPathExtension(false).favorParameter(true)
                .parameterName("format").ignoreAcceptHeader(true)
                .defaultContentType(MediaType.APPLICATION_ATOM_XML)
                .mediaType("xml",MediaType.APPLICATION_ATOM_XML)
                .mediaType("json", MediaType.APPLICATION_JSON);*/
    }
}
