package pers.mine.nookblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pers.mine.nookblog.service.IArticleService;
import pers.mine.nookblog.service.ICodeService;
import pers.mine.nookblog.utils.MineUtil;

/**
 * @author yebukong
 * @description 模板跳转控制
 * @since 2018-10-29 0:43
 */
@Controller
@RequestMapping
public class PageController {
    @Autowired
    private IArticleService articleService;
    @Autowired
    private ICodeService codeService;
    @Autowired
    private Environment env;
    @GetMapping(value = "/welcome")
    public String welcome(Model model) {
        model.addAllAttributes(MineUtil.getTrimKeySystemInfoMap());
        model.addAttribute("springBootVersion",SpringBootVersion.getVersion());
        model.addAttribute("springProfilesActive",env.getProperty("spring.profiles.active"));
        model.addAttribute("serverPort",env.getProperty("server.port"));
        model.addAttribute("serverServletContextPath",env.getProperty("server.servlet.context-path"));
        return "cms/welcome";
    }

    @GetMapping(value = {"/","/index"})
    public String index() {
        return "cms/index";
    }


}
