package pers.mine.nookblog.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yebukong
 * @description 测试
 * @since 2018-09-17 0:06
 */
@Controller
@RequestMapping(value = "fm")
public class FreemarkerController {
    @Resource
    Configuration cfg;

    @RequestMapping(value = "test")
    public String Test(Model model) { //对于页面，templates目录为spring boot默认配置的动态页面路径
        String w = "Welcome FreeMarker!";
        model.addAttribute("w", w);
        Map root = new HashMap();
        root.put("w", w);
        freeMarkerContent(root);
        return "test";
    }

    /**
     * 生成静态页面
     *
     * @param root
     */
    private void freeMarkerContent(Map<String, Object> root) {
        try {
            Template temp = cfg.getTemplate("blog/test.html");
            //以classpath下面的static目录作为静态页面的存储目录，同时命名生成的静态html文件名称
            String path = this.getClass().getResource("/").toURI().getPath() + "test.html";
            Writer file = new FileWriter(new File(path.substring(path.indexOf("/"))));
            temp.process(root, file);
            file.flush();
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
