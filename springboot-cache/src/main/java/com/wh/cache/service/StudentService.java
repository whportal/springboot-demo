package com.wh.cache.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.wh.cache.dto.StudentDTO;
import com.wh.cache.entity.Student;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0.0
 * @date 2020/08/19
 */
public interface StudentService extends IService<Student> {

    List<Student> selectList();

    Student selectById(String id);

    List<Student> selectListByMapper();

    PageInfo<Student> selectListByPage();

    StudentDTO detail(String id);

    Student insert(Student student);

    void deleteById(Long id);
}
