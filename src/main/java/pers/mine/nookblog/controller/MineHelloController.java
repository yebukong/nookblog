package pers.mine.nookblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


//@Controller注解需要配合模板使用
@RestController
@RequestMapping("/test")
public class MineHelloController {
    @RequestMapping(value= {"/Hello","/Hi"})
    public String helloWorld(){
        return "Hello World!";
    }
    private String height="10";

    private String mi="123";



    @RequestMapping(value="info",method = RequestMethod.GET)
    public String height(){


        System.out.printf(height);

        return mi.toString();
    }

    @RequestMapping(value="/info1",produces = "application/json;charset=UTF-8")
    public Map height1(){
        System.out.printf(height);
        Map one = new HashMap();
        one.put("aaa", "bbb");
        return one;
    }
}
