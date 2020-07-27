package com.wh.security.service;

import com.wh.security.config.SysRole;
import com.wh.security.config.SysUser;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * <p></p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-07-17
 */
@Service
public class UserService {

    public SysUser getUserByUsername(String username) {
        SysUser sysUser = new SysUser();
        sysUser.setId(10010L);
        sysUser.setPassword("$2a$10$ftjP7M.mAurkTME1bmnwY.oecqrowem8ZOHAqzkjAO9rjwQznlBsW");
        sysUser.setStatus("1");
        sysUser.setUsername(username);
        SysRole sysRole = new SysRole();
        sysRole.setId(1L);
        sysRole.setRoleName("p1");
        sysUser.setRoles(Collections.singletonList(sysRole));
        return sysUser;
    }
}
