package com.wh.security.config;

import com.wh.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * <p>实现 Spring Security UserDetailsService</p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-07-17
 */
@Service
public class SysUserDetailServiceImpl implements UserDetailsService {

    private UserService userService;

    @Autowired
    public SysUserDetailServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 从数据库根据用户名查找用户信息
        return userService.getUserByUsername(username);
    }
}
