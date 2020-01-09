package pers.mine.nookblog.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pers.mine.nookblog.config.FeignConfiguration;
import pers.mine.nookblog.rpc.fallback.TaobaoIpServiceFallback;

import java.util.Map;

/**
 * @author yebukong
 * @description 淘宝ip转换服务
 */
//name必须指定，且当不使用注册发现服务时，url地址必须指定
@FeignClient(
        name = "taobao.ipService",
        url = "http://ip.taobao.com",
        fallback = TaobaoIpServiceFallback.class,
        configuration = FeignConfiguration.class)
public interface ITaobaoIpService {

    @RequestMapping(value = "/service/getIpInfo.php", method = RequestMethod.GET, produces = "application/json")
        //此接口不支持ip6
        //eg:{code=0, data={ip=180.170.115.137, country=中国, area=, region=上海, city=上海, county=XX, isp=电信, country_id=CN, area_id=, region_id=310000, city_id=310100, county_id=xx, isp_id=100017}}
    Map<String,Object> getIpInfo(@RequestParam(value = "ip") String ip);
}
