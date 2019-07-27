package pers.mine.nookblog;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yebukong
 * @description
 * @since 2018-10-18 20:30
 */
@Data
public class B implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    public String test(String val) {
        System.out.println(val);
        Integer.toBinaryString(10086);
        return val + "";
    }

    public int test123(int i) {
        i = i + 1;
        return i;
    }
}
