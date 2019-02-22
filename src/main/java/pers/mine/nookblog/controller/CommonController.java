package pers.mine.nookblog.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.mine.nookblog.config.GlobalExceptionHandler;
import pers.mine.nookblog.utils.WebKit;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author yebukong
 * @description 放置一些通用api接口
 * @since 2018-10-10 1:21
 */
@Slf4j
@RestController
@RequestMapping("/comm")
public class CommonController {
//    @Value("classpath:static/data/zz.json")
//    private Resource treeDataResource;
    //如果返回值为String类型，响应类型不再默认json，需要指定
    @GetMapping(value = "/json/{data}", produces = "application/json;charset=UTF-8")
    public String jsonData(HttpServletRequest request, @PathVariable("data") String data) throws IOException {
        log.info("[获取json数据] {} --> {}.json",WebKit.ipAddr(request),data);
        Resource resource = new ClassPathResource(String.format("static/data/%s.json", data));
        return IOUtils.toString(resource.getInputStream(), Charset.forName("UTF-8"));
    }
}
