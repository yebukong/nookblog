package pers.mine.nookblog.controller;

import com.baomidou.mybatisplus.extension.api.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.mine.nookblog.rpc.ITaobaoIpService;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;

/**
 * @author yebukong
 */
@Slf4j
@Controller
@RequestMapping()
public class AuthController {
    @Autowired
    ITaobaoIpService taobaoIpService;

    @GetMapping(value = "/auth", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public R<String> auth(HttpServletRequest req) {
        taobaoIpService.getIpInfo("");
        return R.failed("认证失败!");
    }
}
