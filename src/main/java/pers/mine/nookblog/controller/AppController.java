package pers.mine.nookblog.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.api.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.mine.nookblog.NookblogApplication;
import pers.mine.nookblog.utils.MineUtil;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.HashMap;
import java.util.Map;

/**
 * app相关
 *
 * @author yebukong
 */
@Slf4j
@Controller
@RequestMapping("/app")
public class AppController {
    @Autowired
    private Environment env;

    //TODO 相关配置在spring启动过程中赋值，这里只需要拿就可以
    @GetMapping(value = "/info", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public R<JSONObject> info() throws IOException {
        ApplicationHome home = new ApplicationHome(getClass());
        File jarF = home.getSource();
        Map<String, String> versionMap = new HashMap<String, String>();
        //启动jar所在路径
        versionMap.put("appPath", jarF.getAbsolutePath());
        //生效active配置
        versionMap.put("spring.profiles.active", env.getProperty("spring.profiles.active"));
        //https://www.cnblogs.com/LeesinDong/p/12225874.html
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        //获取进程PID  这个名字是以进程pid开头，以机器名结尾，中间用“@”连接而成的
        String name = runtimeMXBean.getName();
        long pid = runtimeMXBean.getPid();//不知道和上面截取方式有没有区别

        JSONObject info = new JSONObject();
        info.put("jvm", MineUtil.getTrimKeySystemInfoMap());
        info.put("spring", null);
        info.put("version", versionMap);
        return R.ok(info);
    }

    @GetMapping(value = "/restart", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public R<String> restart() throws IOException {
        //https://blog.csdn.net/qq_34871626/article/details/79100841  服务使用linux命令重启自身服务(java)
        //http://www.spring4all.com/article/1830
        if (NookblogApplication.getApplicationStartTime() < 0) {
            return R.failed("正在重启中,请勿重复进行重启操作");
        }else{
            NookblogApplication.restart();
            return R.ok("重启中,请等待数秒后访问");
        }
    }
}
