package pers.mine.nookblog.rpc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pers.mine.nookblog.NookblogApplication;
import pers.mine.nookblog.NookblogApplicationTests;

import java.util.Map;

/**
 * @author yebukong
 * @description TODO
 * @since 2019-07-28 01:39
 */

public class ITaobaoIpServiceTest extends NookblogApplicationTests {

    @Autowired
    private ITaobaoIpService taobaoIpService;
    @Test
    public void getIpInfo() {
        Map ipInfo = taobaoIpService.getIpInfo("180.170.115.100");
        System.out.println(ipInfo);
    }
}