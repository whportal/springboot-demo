package com.wh.db.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0.0
 * @date 2020/08/20
 */
@Data
public class StudentDTO implements Serializable {

    private static final long serialVersionUID = -8576224065347271944L;

    private Integer id;

    private String name;
}
