package com.wh.cache.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wh.cache.dto.StudentDTO;
import com.wh.cache.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 不加 @Repository 注解，在启动类统一进行扫描
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0.0
 * @date 2020/08/19
 */
public interface StudentMapper extends BaseMapper<Student> {

    List<Student> selectList();

    StudentDTO detail(@Param("id") String id);

}
