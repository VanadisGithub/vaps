package com.vanadis.vap.controller;

import com.vanadis.lang.String.ValidateCode;
import com.vanadis.vap.common.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @program: vaps
 * @description:
 * @author: 遥远
 * @create: 2019-08-08 10:10
 */
@Api
public class LoginController {

    /**
     * 获取验证码
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @ApiOperation("获取验证码")
    @RequestMapping(value = "imgCode", method = RequestMethod.GET)
    public void imgCode(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        
        HttpSession session = request.getSession();
        ValidateCode vCode = new ValidateCode(120, 40, 4, 150);
        session.setAttribute(Constants.VALIDATE_CODE, vCode.getCode());
        vCode.write(response.getOutputStream());
    }
}
