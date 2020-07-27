package com.wh.security.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * <p>重新账号密码验证逻辑</p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-07-17
 */
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        /*
         1.可以在此处对账号密码验证进行处理，默认 username 和 password 需要通过 form 表单提交，可以重写此方法 支持通过 JSON 提交数据
         2.若是前端传递参数时进行了加密 此处可以进行解密处理

         */
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        // JSON 格式提交走自定义处理逻辑
        if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {

            Map<String, String> parameterMap;
            try {
                parameterMap = new ObjectMapper().readValue(request.getInputStream(), new TypeReference<Map<String, String>>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
                throw new AuthenticationServiceException("Authentication parse parameter error: " + e.getMessage());
            }

            String username = parameterMap.get("username");
            String password = parameterMap.get("password");

            if (username == null) {
                username = "";
            }

            if (password == null) {
                password = "";
            }

            username = username.trim();

            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                    username, password);

            // Allow subclasses to set the "details" property
            setDetails(request, authRequest);

            return this.getAuthenticationManager().authenticate(authRequest);
        }

        // 非 JSON 形式提交走默认处理逻辑
        return super.attemptAuthentication(request, response);
    }
}
