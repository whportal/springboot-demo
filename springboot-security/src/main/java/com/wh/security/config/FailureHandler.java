package com.wh.security.config;

import com.wh.security.common.response.Result;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.security.auth.login.CredentialExpiredException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>登录认证失败的处理逻辑</p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-07-17
 */
@Component
public class FailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        // 可以自定义登录认证失败要响应的消息 前端可以跟剧消息灵活处理
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        String msg = "";
        if (exception instanceof LockedException) {
            msg = "账户被锁定，请联系管理员！";
        }
        if (exception instanceof DisabledException) {
            msg = "账户被禁用，请联系管理员！";
        }
        if (exception instanceof BadCredentialsException) {
            msg = "用户名或密码错误，请重新输入！";
        }
        out.write(msg);
        out.flush();
        out.close();
    }
}
