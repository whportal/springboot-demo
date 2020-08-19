package com.wh.es.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class UserDTO implements Serializable {

    private static final long serialVersionUID = -989262321532266876L;

    private Long id;

    private String username;

    private String password;

    private Integer age;

    private LocalDateTime birthday;
}
