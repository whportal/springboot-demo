package com.wh.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wh.db.entity.Teacher;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 不加 @Repository 注解，在启动类统一进行扫描
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0.0
 * @date 2020/08/19
 */
public interface TeacherMapper extends BaseMapper<Teacher> {

}
