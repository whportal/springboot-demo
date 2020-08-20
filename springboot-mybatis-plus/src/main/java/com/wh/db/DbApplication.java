package com.wh.db;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 *
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0.0
 * @date 2020/08/19
 */
@SpringBootApplication
@MapperScan("com.wh.db.**.mapper")
public class DbApplication {
    public static void main(String[] args) {
        SpringApplication.run(DbApplication.class, args);
    }
}
