package pers.mine.nookblog.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 记录请求日志
 *
 * @author yebukong
 */
@Slf4j
public class RequestLogInterceptor extends HandlerInterceptorAdapter {
    private final ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        startTime.set(System.currentTimeMillis());
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
        //TODO 入库或者文件
        log.info("[{}] -> [{}] from: {} costs: {}ms",
                request.getMethod(),
                request.getServletPath(),
                request.getRemoteAddr(),
                System.currentTimeMillis() - startTime.get()
        );
    }
}
