package pers.mine.nookblog.controller;

import com.baomidou.mybatisplus.extension.api.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * @author yebukong
 * @description 对外api
 * @since 2018-11-05 1:51
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class ApiController extends com.baomidou.mybatisplus.extension.api.ApiController {
    @Autowired
    private Environment env;

    @GetMapping(value = "/appInfo", produces = "application/json;charset=UTF-8")
    public R<Map<String,String>> version() throws IOException {
        ApplicationHome home = new ApplicationHome(getClass());
        File jarF = home.getSource();
        Map<String,String> versionMap = new HashMap<String,String>();
        versionMap.put("appPath", jarF.getAbsolutePath());
        versionMap.put("spring.profiles.active", env.getProperty("spring.profiles.active"));
        return R.ok(versionMap);
    }
}
