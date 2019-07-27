package pers.mine.nookblog.controller;

import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.mine.nookblog.entity.CMSUser;
import pers.mine.nookblog.entity.Code;
import pers.mine.nookblog.service.ICodeService;
import pers.mine.nookblog.utils.MD5Util;
import pers.mine.nookblog.utils.StringX;
import pers.mine.nookblog.utils.WebKit;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author yebukong
 * @description 临时认证
 * @since 2018-10-23 1:51
 */
@Slf4j
@Controller
@RequestMapping()
public class AuthController  extends ApiController {
    @Autowired
    private ICodeService codeService;
    @Autowired
    private CMSUser deUser;
    @PostMapping(value ="/auth",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public R<String> auth(HttpServletRequest request,CMSUser user) {
        if(!Objects.isNull(user) && !Objects.isNull(deUser)){
            String userid = StringX.nvl(user.getUserID(),"");
            String password = StringX.nvl(user.getPassword(),"");
            Code authSaltSuffixCode = codeService.getCodeItem("SysConfig", "AuthSaltSuffix");
            String authSaltSuffix = "";
            if(authSaltSuffixCode!=null){
                authSaltSuffix = StringX.nvl(authSaltSuffixCode.getValue(), "");
            }
            String md5Pw = MD5Util.entcryptMD5HexWithSalt(password,userid+authSaltSuffix,128);
            if(userid.equalsIgnoreCase(deUser.getUserID()) && md5Pw.equals(deUser.getPassword())){
                user.setUserName(deUser.getUserName());
                request.getSession(true).setAttribute(CMSUser.SESSION_NAME,user);
                return R.ok(deUser.getUserName());
            }
        }
        return R.failed("认证失败!");
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login.html";
    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        //session失效
        try {
            request.getSession().invalidate();
        } catch (Exception e) {
            logger.warn("session失效异常",e);
        }
        String paramInfo = WebKit.parseUrlParam("errmsg","已注销");
        return "redirect:/login.html?"+paramInfo;
    }
}
