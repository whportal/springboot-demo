package com.wh.security.common.advance;

import com.wh.security.common.response.Result;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.security.auth.login.AccountExpiredException;
import javax.security.auth.login.CredentialException;
import javax.security.auth.login.CredentialExpiredException;

/**
 * <p></p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-07-17
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result<String> handlerException(Exception e) {

        if (e instanceof AuthenticationException) {
            if (e instanceof LockedException) {
                return Result.failed("账户被锁定，请联系管理员！");
            }
            if (e instanceof DisabledException) {
                return Result.failed("账户被禁用，请联系管理员！");
            }
            if (e instanceof BadCredentialsException) {
                return Result.failed("用户名或密码错误，请重新输入！");
            }
        }
        return Result.failed("未知异常");
    }
}
