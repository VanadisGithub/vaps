package com.vanadis.start.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * TestFilter
 *
 * @author yy287502@alibaba-inc.com
 * @date 2020/12/29 6:00 下午
 */
@Slf4j
@Component
//@Order(1)
public class TestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        log.debug("init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
        throws IOException, ServletException {
        log.debug("doFilter-start");
        filterChain.doFilter(servletRequest, servletResponse);
        log.debug("doFilter-end");
    }

    @Override
    public void destroy() {
        log.debug("destroy");
    }
}
