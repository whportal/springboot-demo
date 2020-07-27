package com.wh.security.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * <p>自定义验证码校验逻辑</p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-07-18
 */
public class AuthenticationProvider extends DaoAuthenticationProvider {

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        // 验证码校验逻辑
        // 不在 LoginFilter 中加入校验验证码逻辑是因为 只有在登录的时候才需要校验验证码 在此处校验验证码 更合适

        super.additionalAuthenticationChecks(userDetails, authentication);
    }
}
