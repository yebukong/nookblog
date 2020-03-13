package pers.mine.nookblog.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Map;
@Slf4j
public class WebKit {
    public static final String UNKNOWN_MAGIC = "unknown";

    private WebKit() {
        throw new AssertionError("No WebKit instances for you!");
    }

    /**
     * 根据request获取客户ip
     */
    public static String ipAddr(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (StringX.isBlank(ipAddress) || UNKNOWN_MAGIC.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (StringX.isBlank(ipAddress) || UNKNOWN_MAGIC.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringX.isBlank(ipAddress) || UNKNOWN_MAGIC.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("X-Real-IP");
        }
        if (StringX.isBlank(ipAddress) || UNKNOWN_MAGIC.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringX.isBlank(ipAddress) || UNKNOWN_MAGIC.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringX.isBlank(ipAddress) || UNKNOWN_MAGIC.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        //多个ip取第一个
        if (!StringX.isBlank(ipAddress) && ipAddress.contains(",")) {
            ipAddress = ipAddress.trim().split(",")[0];
        }
        if (StringX.isBlank(ipAddress)) {
            ipAddress = UNKNOWN_MAGIC;
        }
        return ipAddress;
    }

    /**
     * 判断是否为ajax请求
     */
    public static boolean isAjax(HttpServletRequest request) {
        String temp = request.getHeader("x-requested-with");
        return "XMLHttpRequest".equalsIgnoreCase(temp);
    }

    /**
     * 判断是否明确指定响应json数据
     */
    public static boolean acceptExplicitForJson(HttpServletRequest request) {
        String temp = StringX.nvl(request.getHeader("Accept"), "");
        return temp.toUpperCase().contains("APPLICATION/JSON");
    }

    /**
     * 生成UTF-8编码的url转码参数字符串
     */
    public static String parseUrlParams(Map<String, String> params) {
        StringBuffer sbu = new StringBuffer();
        if (params != null) {
            params.forEach((k, v) -> {
                sbu.append("&").append(parseUrlParam(k, v));
            });
        }
        return sbu.length() > 0 ? sbu.substring(1) : "";
    }

    /**
     * 生成UTF-8编码的url转码参数字符串
     *
     * @param key   参数名
     * @param value 参数值
     * @return URLEncoder.encode(key)=URLEncoder.encode(value)
     */
    public static String parseUrlParam(String key, String value) {
        StringBuffer sbu = new StringBuffer();
        try {
            sbu.append(URLEncoder.encode(key, "utf-8"));
            sbu.append("=");

            sbu.append(URLEncoder.encode(value, "utf-8"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sbu.toString();
    }

    public static Map<String, String> getUrlParams(String paramUrl) {
        throw new UnsupportedOperationException();
    }


    public static String commonHttpRequestWithTimeOut(String url, String jsonPara, HttpMethod httpMethod, int connectTimeout, int readTimeout) {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        //设置超时时间
        requestFactory.setConnectTimeout(connectTimeout);
        requestFactory.setReadTimeout(readTimeout);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        headers.setAccept(Arrays.asList(new MediaType[]{new MediaType("application", "json", Charset.forName("UTF-8"))}));
        HttpEntity<String> requestEntity = new HttpEntity<String>(jsonPara, headers);
        ResponseEntity<String> exchange = restTemplate.exchange(url, httpMethod, requestEntity, String.class);
        String result = exchange.getBody();

        return result;
    }

    public static String commonHttpRequest(String url, String jsonPara, HttpMethod httpMethod) {
        return commonHttpRequestWithTimeOut(url, jsonPara, httpMethod, 10, 10);
    }

    public static String getCityByIP(String ip){
        String aliIPUrl = "http://ip.taobao.com/service/getIpInfo.php?ip="+ip;
        String result ="unknown";
        try {
            SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
            RestTemplate restTemplate = new RestTemplate(requestFactory);
            String resultJson = restTemplate.getForObject("http://ip.taobao.com/service/getIpInfo.php?ip={ip}", String.class,ip);
            ObjectMapper mapper = new ObjectMapper();
            Map ipMap=mapper.readValue(resultJson, Map.class);
            if("0".equals(ipMap.get("code")+"")){
                result =   StringX.nvl((String)((Map)ipMap.get("data")).get("city"),"unknown");
            }
        } catch (Exception e) {
           log.warn("[解析IP发生异常:ip="+ip+"] "+e.getMessage());
        }
        log.info("[解析IP:"+ip+"] -> "+result);
        return  result;
    }
}
