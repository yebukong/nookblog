package pers.mine.nookblog.service;

/**
 * @author yebukong
 * @description 通用服务
 * @since 2019-07-28 02:03
 */
public interface ICommonService {
    /**
     * 通过ip返回城市名称
     * @param ip 查询ip
     * @return
     */
    String getCityByIP(String ip);
}
