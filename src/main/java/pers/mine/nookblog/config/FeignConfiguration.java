package pers.mine.nookblog.config;

import feign.Logger;
import feign.Request;
import feign.Retryer;
import feign.codec.Decoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yebukong
 * @description Feign相关配置
 * @since 2019-07-28 00:18
 */
@Configuration
public class FeignConfiguration {
    public static int connectTimeOutMillis = 2 * 1000;
    public static int readTimeOutMillis = 4 * 000;

    @Bean
    public Request.Options options() {
        // connectTimeoutMillis=2*1000 链接超时时间
        // readTimeoutMillis=4*1000 响应超时时间
        return new Request.Options(connectTimeOutMillis, readTimeOutMillis);
    }

    /**
     * NEVER_RETRY 取消重试
     */
    @Bean
    public Retryer feignRetryer() {
        // period=100 发起当前请求的时间间隔，单位毫秒
        // maxPeriod=1000 发起当前请求的最大时间间隔，单位毫秒
        // maxAttempts=5 最多请求次数，包括第一次
        return new Retryer.Default(100, 1000, 3);
    }
    @Value("${mine.feign.loglevel}")
    private String feignLogLeve;
    @Bean
    Logger.Level feignLoggerLevel() {
        if("FULL".equals(feignLogLeve)){
            return Logger.Level.FULL;
        }else if("HEADERS".equals(feignLogLeve)){
            return Logger.Level.HEADERS;
        }else if("BASIC".equals(feignLogLeve)){
            return Logger.Level.BASIC;
        }else{
            return Logger.Level.NONE;
        }
    }


    /**
     * 支持text/html响应的json
     */
    @Bean
    public Decoder feignDecoder() {
        ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(new MineMessageConverter());
        return new SpringDecoder(objectFactory);
    }

}

class MineMessageConverter extends MappingJackson2HttpMessageConverter {
    public MineMessageConverter() {
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.TEXT_PLAIN);
        mediaTypes.add(MediaType.TEXT_HTML);
        setSupportedMediaTypes(mediaTypes);
    }
}