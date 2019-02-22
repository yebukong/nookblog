package pers.mine.nookblog.utils;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author yebukong
 * @description 测试类
 * @since 2018-10-23 14:11
 */
public class WebKitTest {

    @Test
    public void parseUrlParams() {
        Map<String,String> params= new LinkedHashMap<>();
        params.put("拉斯加德","拉斯加德");
        params.put("阿斯顿","啊啊");
        params.put("A","123123");
        params.put("ask电话","111111");
        String s = WebKit.parseUrlParams(params);
        System.out.println(s);
    }

    @Test
    public void getUrlParams() {

    }


    @Test
    public void getCityByIP() {
       System.out.println(WebKit.getCityByIP("127.0.0.1")) ;
    }
}