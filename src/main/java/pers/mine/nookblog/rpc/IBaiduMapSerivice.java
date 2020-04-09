package pers.mine.nookblog.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pers.mine.nookblog.config.FeignConfiguration;
import pers.mine.nookblog.rpc.fallback.IBaiduMapSeriviceFallback;

import java.util.Map;

/**
 * @author yebukong
 * @description 百度地图API
 * http://lbsyun.baidu.com/index.php?title=webapi/ip-api
 */
@FeignClient(
        name = "baidu.mapService",
        url = "https://api.map.baidu.com",
        fallback = IBaiduMapSeriviceFallback.class,
        configuration = FeignConfiguration.class)
public interface IBaiduMapSerivice {
    /**
     * ip转地址api Path
     */
    String API_PATH_LOCATION_IP = "/location/ip";
    String CODE_BAIDU_API_CONFIG = "BaiduApiConfig";

    /**
     * <pre>
     * {
     *  "address": "CN|上海|上海|None|CHINANET|0|0",
     *  "content": {
     *      "address_detail": {
     *          "province": "上海市",
     *          "city": "上海市",
     *          "district": "",
     *          "street": "",
     *          "street_number": "",
     *          "city_code": 289
     *      },
     *     "address": "上海市",
     *     "point": {
     *         "y": "3642780.37",
     *         "x": "13524118.26"
     *      }
     *  },
     *  "status": 0
     * }
     * <pre/>
     */
    @RequestMapping(value = API_PATH_LOCATION_IP, method = RequestMethod.GET, produces = "application/json")
    Map<String, Object> getIpInfo(@RequestParam(value = "ip") String ip,
                                  @RequestParam(value = "ak") String ak,
                                  @RequestParam(value = "coor") String coor,
                                  @RequestParam(value = "sn") String sn);

}
