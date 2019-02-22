package pers.mine.nookblog.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.mine.nookblog.entity.Code;
import pers.mine.nookblog.service.ICodeService;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yebukong
 * @description 简单缓存
 * 参考自com.tale.utils.MapCache
 * @since 2018-11-02 16:58
 */
@Component
public class MapCache {

    private static ICodeService codeService;

    private static final int DEFAULT_CACHES = 20;

    private static final String CODE_PREFIX = "Code.";

    private static Map<String, CacheObject> cachePool = new ConcurrentHashMap<>(DEFAULT_CACHES);


    @Autowired
    public void setCodeService(ICodeService codeService) {
        MapCache.codeService = codeService;
    }


    /**
     * 设置一个缓存
     * @param key   缓存key
     * @param value 缓存value
     */
    public static void set(String key, Object value) {
        set(key, value, -1);
    }

    /**
     * 设置一个CodeMap缓存，不会过期
     * @param codeNo
     */
    public static void setCode(String codeNo) {
        String key = CODE_PREFIX+codeNo;
        List<Code> codeItems = codeService.getCodeItems(codeNo);
        if(Objects.isNull(codeItems)){
            remove(key);
            return;
        }
        Map<String, Code> codeMap = new LinkedHashMap<>();
        codeItems.forEach(e->{
            codeMap.put(e.getItemNo(),e);
        });
        set(key, codeMap, -1);
    }
    /**
     * 获取一个CodeItem<br>
     * @param codeNo
     * @return
     */
    public static Code getCodeItem(String codeNo,String itemNo) {
        Map<String,Code> codeMap= getCodeMap(codeNo);
        if(Objects.isNull(codeMap)||codeMap.isEmpty()){
            return null;
        }else{
            return codeMap.get(itemNo);
        }
    }
    /**
     * 获取一个Code缓存列表<br>
     * @param codeNo
     * @return
     */
    public static List<Code> getCodeItems(String codeNo) {
        Map<String, Code> codeMap = getCodeMap(codeNo);
        if(!Objects.isNull(codeMap)){
            List<Code> result =  new ArrayList<>();
            codeMap.forEach((k,v) ->{
                result.add(v);
            });
            return result;
        }
        return null;
    }

    /**
     * 获取一个Code缓存Map<br>
     * 未缓存将自动查找数据库，缓存并返回
     * @param codeNo
     * @return
    */
    public static Map<String,Code> getCodeMap(String codeNo) {
        String key = CODE_PREFIX+codeNo;
        Map<String,Code> result = get(key);
        if(Objects.isNull(result)){
            List<Code> codeItems = codeService.getCodeItems(codeNo);
            if(Objects.isNull(codeItems)){
                result =  null;
            }else{
                result = new HashMap<>();
                Map<String, Code> finalResult = result;
                codeItems.forEach(e->{
                    finalResult.put(e.getItemNo(),e);
                });
                result =  finalResult;
                set(key, result, -1);
            }
        }
        return result;
    }
    /**
     * 设置一个缓存并带过期时间
     *
     * @param key     缓存key
     * @param value   缓存value
     * @param expired 过期时间，单位为秒
     */
    public static  void set(String key, Object value, long expired) {
        expired = expired > 0 ? System.currentTimeMillis() / 1000 + expired : expired;
        CacheObject cacheObject = new CacheObject(key, value, expired);
        cachePool.put(key, cacheObject);
    }

    /**
     * 读取一个缓存
     * @param key 缓存key
     * @param <T>
     */
    public static <T> T get(String key) {
        CacheObject cacheObject = cachePool.get(key);
        if (null != cacheObject) {
            long cur = System.currentTimeMillis() / 1000;
            if (cacheObject.getExpired() <= 0 || cacheObject.getExpired() > cur) {
                Object result = cacheObject.getValue();
                return (T) result;
            }else{
                remove(key);
            }
        }
        return null;
    }

    /**
     * 根据key删除缓存
     * @param key 缓存key
     */
    public static void remove(String key) {
        cachePool.remove(key);
    }

    public static void cleanAll() {
        cachePool.clear();
    }

    static class CacheObject {
        private String key;
        private Object value;
        private long expired;

        public CacheObject(String key, Object value, long expired) {
            this.key = key;
            this.value = value;
            this.expired = expired;
        }
        public String getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }

        public long getExpired() {
            return expired;
        }
    }
}
