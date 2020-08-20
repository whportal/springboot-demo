package com.wh.db.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * <p>
 *
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0.0
 * @date 2020/08/20
 */
public enum GenderEnum implements IEnum<String> {

    /**
     * 性别枚举
     */
    MAN("1","男"),
    WOMEN("0","女"),
    UNKNOWN("-","未知");

    private String value;



    /**
     * 标记响应json值
     */
    @EnumValue
    @JsonValue
    private String desc;

    GenderEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    /**
     * 枚举数据库存储值
     */
    @Override
    public String getValue() {
        return value;
    }
    public String getDesc() {
        return desc;
    }
}
