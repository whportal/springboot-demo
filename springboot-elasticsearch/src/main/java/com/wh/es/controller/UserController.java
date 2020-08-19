package com.wh.es.controller;

import com.wh.common.response.Result;
import com.wh.es.entity.User;
import com.wh.es.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0.0
 * @date 2020/08/18
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public Result<User> save(@RequestBody User user) {
        return Result.success(userService.save(user));
    }

    @GetMapping("/{id}")
    public Result<User> getUser(@PathVariable("id") Long id) {
        return Result.success(userService.getById(id));
    }
}
