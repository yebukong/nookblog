package pers.mine.nookblog.rpc.fallback;

import org.springframework.stereotype.Component;
import pers.mine.nookblog.rpc.IBaiduMapSerivice;

import java.util.HashMap;
import java.util.Map;

@Component
public class IBaiduMapSeriviceFallback implements IBaiduMapSerivice {
    @Override
    public Map<String, Object> getIpInfo(String ip, String ak, String coor,String sn) {
        Map<String, Object> errMap = new HashMap<>();
        errMap.put("status", -1);
        errMap.put("address", null);
        errMap.put("content", null);
        return errMap;
    }
}
