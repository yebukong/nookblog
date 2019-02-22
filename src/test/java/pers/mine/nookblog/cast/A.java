package pers.mine.nookblog.cast;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yebukong
 * @description TODO
 * @since 2018-10-18 20:29
 */
@Data
public class A implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    public String test(String val){
        System.out.println(val);
        return val+"";
    }
}
