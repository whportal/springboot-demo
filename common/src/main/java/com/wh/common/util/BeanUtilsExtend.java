package com.wh.common.util;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * <p>
 * BeanUtil 扩展
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0.0
 * @date 2020/08/11
 */
public class BeanUtilsExtend {

    /**
     * 获取对象中字段值为 null 的字段名数组
     *
     * @param source 对象
     * @return 字段值为 null 的字段名数组
     */
    public static String[] getNullPropertyNames(Object source) {
        Set<String> nullPropertyNames = new HashSet<>();
        Field[] declaredFields = source.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            try {
                if (Objects.isNull(field.get(source)) || "".equals(field.get(source))) {
                    nullPropertyNames.add(field.getName());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new IllegalArgumentException("Could not get property name '" + field.getName() + "' from source");
            }
        }
        return nullPropertyNames.toArray(new String[0]);
    }
}
