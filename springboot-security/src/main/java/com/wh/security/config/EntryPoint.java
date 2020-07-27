package com.wh.security.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>未登录的处理逻辑</p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-07-17
 */
@Component
public class EntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // 可以响应一个未登录的自定义状态码 前端根据此状态码进行处理
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write("尚未登录，请登录");
        out.flush();
        out.close();
    }
}
