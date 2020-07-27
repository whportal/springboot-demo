package com.wh.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * <p></p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-07-17
 */
@SpringBootTest
public class SecurityTest {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    void testGeneratePassword() {
        String password = bCryptPasswordEncoder.encode("whportal");
        // $2a$10$ftjP7M.mAurkTME1bmnwY.oecqrowem8ZOHAqzkjAO9rjwQznlBsW
        System.out.println("password = " + password);
    }
}
