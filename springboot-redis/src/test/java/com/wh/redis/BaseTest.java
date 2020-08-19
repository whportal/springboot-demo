package com.wh.redis;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>
 *
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0.0
 * @date 2020/08/18
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BaseTest {

    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);

    @Before
    public void before() {
        log.info("------------------------- before -------------------------");
    }

    @After
    public void after() {
        log.info("------------------------- before -------------------------");
    }
}
