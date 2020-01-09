package pers.mine.nookblog.rpc.fallback;

import org.springframework.stereotype.Component;
import pers.mine.nookblog.rpc.ITaobaoIpService;

import java.util.HashMap;
import java.util.Map;

@Component
public class TaobaoIpServiceFallback implements ITaobaoIpService {
    @Override
    public Map<String,Object> getIpInfo(String ip) {
        Map<String, Object> errMap = new HashMap<>();
        errMap.put("code", "-1");
        errMap.put("data", null);
        return errMap;
    }
}
