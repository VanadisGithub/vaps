package com.vanadis.start.response;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import com.vanadis.lang.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice(basePackages = "com.vanadis")
@Slf4j
public class ResponseAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        if (Objects.isNull(o)) {
            return R.builder().message("success").build();
        }
        if (o instanceof R) {
            return o;
        }

        return R.builder().message("success").data(o).build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public R<?> exceptionHandler(HttpServletRequest request, Exception e) {
        log.error(e.getMessage(), e);
        return R.builder().message(e.getMessage()).status(R.fail).build();
    }

    /**
     * 针对Assert断言异常统一处理
     *
     * @param request
     * @param illegalArgumentExceptionException
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(code = HttpStatus.EXPECTATION_FAILED)
    public R<?> illegalArgumentExceptionHandler(HttpServletRequest request,
                                                IllegalArgumentException illegalArgumentExceptionException) {
        log.error("illegalArgumentExceptionException:" + illegalArgumentExceptionException.getMessage(),
            illegalArgumentExceptionException);
        return R.builder().message(illegalArgumentExceptionException.getMessage()).status(R.fail).build();
    }
}



