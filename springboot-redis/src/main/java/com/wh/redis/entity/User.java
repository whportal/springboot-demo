package com.wh.redis.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0.0
 * @date 2020/08/18
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -5705953961378110137L;

    private String username;

    private String password;

    private Integer age;

    private Long money;

    private Date birthday;
}
