package pers.mine.nookblog.utils;

import org.junit.Test;

/**
 * @author yebukong
 * @description
 * @since 2018-11-04 6:39
 */
public class FreeMarkerUtilTest {

    @Test
    public void checkAndMkdirs() {
        FreeMarkerUtil.checkAndMkdirs("//a//b//c/s/c. o// ");
    }
}