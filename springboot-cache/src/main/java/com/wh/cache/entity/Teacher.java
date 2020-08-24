package com.wh.cache.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

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
public class Teacher implements Serializable {

    private static final long serialVersionUID = -8600111906319973932L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer age;
}
