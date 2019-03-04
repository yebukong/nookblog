package pers.mine.nookblog.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author yebukong
 * @description
 * @since 2019-01-16 20:16
 */
public class MD5UtilTest {

    @Test
    public void entcryptMD5HexWithSalt() {
        String o1 = MD5Util.entcryptMD5HexWithSalt("minenook","test",128);
        String o2 = MD5Util.entcryptMD5HexWithSalt("测试","盐",128);
        System.out.println(o1 +" \n"+ o2);
        System.out.println(o1.equals(o2));
    }

    @Test
    public void entcryptMD5Hex() {
    }

    @Test
    public void byteToHex() {
    }
}