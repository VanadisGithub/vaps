package com.vanadis.future.Security;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.session.web.http.HttpSessionIdResolver;
import org.springframework.stereotype.Service;

@Slf4j
@Service("httpSessionIdResolver")
public class RestHttpSessionIdResolver implements HttpSessionIdResolver {

    @Override
    public List<String> resolveSessionIds(HttpServletRequest request) {
        System.out.println("resolveSessionIds");
        return null;
    }

    @Override
    public void setSessionId(HttpServletRequest request, HttpServletResponse response, String sessionId) {
        System.out.println("setSessionId");
    }

    @Override
    public void expireSession(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("expireSession");
    }
}