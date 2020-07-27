package com.wh.security.controller;

import com.wh.security.config.SysRole;
import com.wh.security.config.SysUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * <p></p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-07-17
 */
@RestController
public class LoginController {

    @GetMapping({"/", "/index"})
    public String index() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        System.out.println("name = " + name);
        Object details = authentication.getDetails();
        System.out.println("details = " + details);
        Object principal = authentication.getPrincipal();
        if (principal instanceof SysUser) {
            SysUser sysUser = (SysUser) principal;
            return "登录成功~ 首页 CODE:10000 欢迎回来~" + sysUser;
        }
        // 返回给前端特定的自定义响应状态码表示登录成功，前端根据登录成功状态码跳转到首页或其他指定页面
        return "登录成功~ 首页 CODE:10000 欢迎回来~" + name;
    }

    /*@GetMapping("/login")
    public String login(String username, String password) {
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        return "Login ...";
    }*/
}
