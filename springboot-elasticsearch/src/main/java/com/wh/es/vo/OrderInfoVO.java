package com.wh.es.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0.0
 * @date 2020/08/10
 */
@Data
public class OrderInfoVO implements Serializable {

    private static final long serialVersionUID = -8395644622235538665L;

    private Integer pageNum;

    private Integer pageSize;

    private String searchParam;

    private String gmtCreateBegin;

    private String gmtCreateEnd;
}
