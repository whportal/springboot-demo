package com.wh.demo.controller;

import com.wh.demo.config.ResourceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p></p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-07-21
 */
@RestController
@RequestMapping("resource")
public class ResourceController {

    @Autowired
    private ResourceProperties resourceProperties;

    @GetMapping
    public String resource() {
        String common = resourceProperties.getCommon();
        String profile = resourceProperties.getProfile();
        String test = resourceProperties.getTest();
        System.out.println("test = " + test);
        System.out.println("profile = " + profile);
        System.out.println("common = " + common);
        return null;
    }
}
