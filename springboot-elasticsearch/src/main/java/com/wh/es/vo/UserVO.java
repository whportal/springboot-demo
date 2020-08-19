package com.wh.es.vo;

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
public class UserVO implements Serializable {

    private static final long serialVersionUID = 1778484128826534138L;

    private Long id;

    private String username;

    private Integer age;

    private LocalDateTime birthday;
}
