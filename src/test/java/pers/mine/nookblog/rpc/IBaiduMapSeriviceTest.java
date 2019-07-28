package pers.mine.nookblog.rpc;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pers.mine.nookblog.NookblogApplicationTests;
import pers.mine.nookblog.entity.Code;
import pers.mine.nookblog.service.ICodeService;
import pers.mine.nookblog.utils.MD5Util;
import pers.mine.nookblog.utils.WebKit;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author yebukong
 * @description
 * @since 2019-07-28 12:47
 */
public class IBaiduMapSeriviceTest extends NookblogApplicationTests {
    @Autowired
    private IBaiduMapSerivice baiduMapSerivice;
    @Autowired
    private ICodeService codeService;
    @Test
    public void getIpInfo() throws UnsupportedEncodingException {
        Code akCode = codeService.getCodeItem(IBaiduMapSerivice.CODE_BAIDU_API_CONFIG, "ak");
        Code skCode = codeService.getCodeItem(IBaiduMapSerivice.CODE_BAIDU_API_CONFIG, "sk");
        String ip = "180.170.115.100";
        String ak = akCode.getValue();
        String sk = skCode.getValue();
        Map paramsMap = new LinkedHashMap<String, String>();
        paramsMap.put("ip", ip);
        paramsMap.put("ak", ak);
        paramsMap.put("coor", "");
        String wholeStr = new String("/location/ip?" + WebKit.parseUrlParams(paramsMap) + sk);
        Map<String, Object> ipInfo = baiduMapSerivice.getIpInfo(ip, ak,"",MD5Util.baiduApiMD5(URLEncoder.encode(wholeStr, "UTF-8")));
        System.out.println(ipInfo);
    }
}