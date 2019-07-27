package pers.mine.nookblog;

import org.junit.Test;

import java.util.ArrayList;

/**
 * @author yebukong
 * @description 测试类
 * @since 2019-02-15 19:15
 */

public class CommTest {

    @Test
    public void pathTest() {
        String x= "file:/D:/WorkSpace/Idea/nookblog/target/classes/static/blog/img/upyun_logo5.png";
        System.out.println(x.substring(x.indexOf("/Idea/")+6,x.length()-"upyun_logo5.png".length()));
    }



    @Test
    public void test1() {
        ArrayList<String> arr = new ArrayList<>();
        arr.add(100,"test");
        for (String s : arr) {
            System.out.println(s);
        }
    }



}
