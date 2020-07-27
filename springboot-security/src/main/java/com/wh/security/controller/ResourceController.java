package com.wh.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p></p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-07-17
 */
@RestController
public class ResourceController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello Security EveryOne";
    }

    @GetMapping("/admin/hello")
    public String adminHello() {
        return "Hello Security Admin";
    }

    @GetMapping("/user/hello")
    public String user() {
        return "Hello Security User";
    }

    @GetMapping("/r/r1")
    public String r1() {
        return "访问资源-r1";
    }

    @GetMapping("/r/r2")
    public String r2() {
        return "访问资源-r2";
    }

    @GetMapping("/admin")
    public String admin() {
        return " 访问资源 admin";
    }

    @GetMapping("/rememberMe")
    public String rememberMe() {
        return " 访问资源 rememberMe";
    }
}
