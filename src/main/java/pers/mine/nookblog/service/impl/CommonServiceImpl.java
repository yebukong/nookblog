package pers.mine.nookblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.mine.nookblog.entity.Article;
import pers.mine.nookblog.entity.Code;
import pers.mine.nookblog.mapper.ArticleMapper;
import pers.mine.nookblog.rpc.IBaiduMapSerivice;
import pers.mine.nookblog.rpc.ITaobaoIpService;
import pers.mine.nookblog.rpc.fallback.TaobaoIpServiceFallback;
import pers.mine.nookblog.service.IArticleService;
import pers.mine.nookblog.service.ICodeService;
import pers.mine.nookblog.service.ICommonService;
import pers.mine.nookblog.utils.MD5Util;
import pers.mine.nookblog.utils.StringX;
import pers.mine.nookblog.utils.WebKit;

import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 通用服务实现类
 */
@Slf4j
@Service
public class CommonServiceImpl implements ICommonService {
    @Autowired
    private ITaobaoIpService taobaoIpServicece;
    @Autowired
    private IBaiduMapSerivice baiduMapSerivice;
    @Autowired
    private ICodeService codeService;

    public String getCityByIP(String ip) {
        String result = "unknown";
        try {
            Code ak = codeService.getCodeItem(IBaiduMapSerivice.CODE_BAIDU_API_CONFIG, "ak");
            Code sk = codeService.getCodeItem(IBaiduMapSerivice.CODE_BAIDU_API_CONFIG, "sk");
            if (ak != null && sk != null) {
                Map paramsMap = new LinkedHashMap<String, String>();
                paramsMap.put("ip", ip);
                paramsMap.put("ak", ak.getValue());
                paramsMap.put("coor", "");
                String wholeStr = new String(IBaiduMapSerivice.API_PATH_LOCATION_IP + "?" + WebKit.parseUrlParams(paramsMap) + sk.getValue());
                Map<String, Object> ipInfo = baiduMapSerivice.getIpInfo(ip, ak.getValue(), "", MD5Util.baiduApiMD5(URLEncoder.encode(wholeStr, "UTF-8")));
                if ("0".equals(ipInfo.get("status") + "")) {
                    result = StringX.nvl((String) ((Map) ipInfo.get("content")).get("address"), "unknown");
                }
            }
            if ("unknown".equals(result)) {
                Map ipInfo = taobaoIpServicece.getIpInfo(ip);
                if ("0".equals(ipInfo.get("code") + "")) {
                    result = StringX.nvl((String) ((Map) ipInfo.get("data")).get("city"), "unknown");
                }
            }
        } catch (Exception e) {
            log.warn("[解析IP发生异常:ip=" + ip + "] ", e);
        }
        return result;
    }
}