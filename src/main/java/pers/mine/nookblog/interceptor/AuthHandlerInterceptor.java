package pers.mine.nookblog.interceptor;

import com.baomidou.mybatisplus.extension.api.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import pers.mine.nookblog.entity.CMSUser;
import pers.mine.nookblog.utils.StringX;
import pers.mine.nookblog.utils.WebKit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author yebukong
 * @description 认证拦截器
 * @since 2018-10-23 9:51
 */
@Slf4j
public class AuthHandlerInterceptor implements HandlerInterceptor {
    /**
     * 进入controller之前调用
     */
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        CMSUser curUser = (CMSUser) req.getSession().getAttribute(CMSUser.SESSION_NAME);
        String uri = req.getRequestURI();
        //System.out.println(uri);
        if (curUser == null || StringX.isEmpty(curUser.getUserName())) {
            if (WebKit.isAjax(req)||WebKit.acceptExplicitForJson(req)) {//响应json
                log.info("Auth拦截器:"+req.getRequestURI()+"--> [ajax]");
                resp.setContentType("application/json;charset=utf-8");
                resp.setCharacterEncoding("utf-8");
                PrintWriter pw = resp.getWriter();
                pw.write(new ObjectMapper().writeValueAsString( R.failed("非法请求!")));
                pw.flush();
                pw.close();
            }else{//重定向到登录
                String paramInfo =WebKit.parseUrlParam("errmsg","请先进行登录");
                log.info("Auth拦截器:"+req.getRequestURI()+"--> "+req.getContextPath()+"/login?"+paramInfo);
                resp.sendRedirect(req.getContextPath()+"/login?"+paramInfo);
            }
            return false;
        }
        return true;
    }

    /**
     * controller处理完视图渲染之前调用
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * DispatcherServlet 渲染了对应的视图之后执行
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
