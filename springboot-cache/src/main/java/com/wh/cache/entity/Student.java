package com.wh.cache.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0.0
 * @date 2020/08/19
 */
@Data
public class Student implements Serializable {

    private static final long serialVersionUID = 1129093460261329822L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer age;

    /**
     * 性别使用枚举
     */
    private String gender;

    private LocalDateTime gmtCreate;

    private Date birthday;
}
