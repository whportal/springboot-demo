package com.wh.test;

import com.wh.demo.DemoApplication;
import com.wh.starter.WhPortalService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>
 *
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0.0
 * @date 2020/08/24
 */
@SpringBootTest(classes = DemoApplication.class)
@RunWith(SpringRunner.class)
public class StarterTest {

    @Autowired
    private WhPortalService whPortalService;

    @Test
    public void testStarterName() {
        System.out.println("whPortalService.getName() = " + whPortalService.getName());
    }

    @Test
    public void testStarterMsg() {
        System.out.println("whPortalService.getMsg() = " + whPortalService.getMsg());
    }
}
