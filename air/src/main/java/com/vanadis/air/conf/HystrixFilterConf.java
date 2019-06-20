package com.vanadis.air.conf;

/**
 * @program: vaps
 * @description: 拦截所有路径(拦截规则)
 * @author: 遥远
 * @create: 2019-06-17 16:42
 */

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * urlPatterns:
 * filterName:过滤器名称
 */
@WebFilter(urlPatterns = "/*", filterName = "hystrixFilter")
public class HystrixFilterConf implements Filter {
    public void destroy() { }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 初始化Hystrix上下文
        HystrixRequestContext ctx = HystrixRequestContext.initializeContext();
        try {
            chain.doFilter(request, response);
        } catch (Exception e) {

        } finally {
            ctx.shutdown();
        }
    }
    public void init(FilterConfig arg0) throws ServletException { }
}