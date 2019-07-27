package pers.mine.nookblog.utils;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author yebukong
 * @description
 * @since 2018-10-30 1:06
 */
public class MineUtilTest {

    @Test
    public void getLocalHost() {
        System.out.println(MineUtil.getLocalHostIP());
    }

    @Test
    public void getSystemInfoMap() {
        MineUtil.getSystemInfoMap().forEach((k,v) -> System.out.println("[" + k + "] =  " + v));
        //MineUtil.getSystemInfoMap().keySet().stream().sorted().forEach(e -> System.out.println("["+e+"]"));
        //MineUtil.getSystemInfoMap().forEach((k,v) -> System.out.println("[" + k + "]"));

    }

    @Test
    public void getIpByName() throws InterruptedException {
        System.out.println(MineUtil.getIPByName("www.yebukong.com"));
        TimeUnit.SECONDS.sleep(10);
        System.out.println(123);
    }

    @Test
    public void getTrimKeySystemInfoMap() {
        MineUtil.getTrimKeySystemInfoMap().forEach((k,v) -> System.out.println("[" + k + "] =  " + v));
    }
}