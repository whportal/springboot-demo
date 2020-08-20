package com.wh.db.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wh.db.dto.StudentDTO;
import com.wh.db.entity.Student;
import com.wh.db.mapper.StudentMapper;
import com.wh.db.service.StudentService;
import com.wh.db.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Override
    public List<Student> selectList() {
        return this.list();
    }

    @Override
    public Student selectById(String id) {
        return this.getOne(new QueryWrapper<Student>().lambda().eq(Student::getId, id));
    }

    @Override
    public List<Student> selectListByMapper() {
        return this.baseMapper.selectList();
    }

    @Override
    public PageInfo<Student> selectListByPage() {
        PageHelper.startPage(1, 2);
        List<Student> list = this.baseMapper.selectList();
        return new PageInfo<>(list);
    }

    @Override
    public StudentDTO detail(String id) {
       return this.baseMapper.detail(id);
    }


}
