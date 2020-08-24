package com.wh.cache.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wh.cache.dto.StudentDTO;
import com.wh.cache.entity.Student;
import com.wh.cache.mapper.StudentMapper;
import com.wh.cache.service.StudentService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
@CacheConfig(cacheNames = "students")
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Override
    public List<Student> selectList() {
        return this.list();
    }

    @Cacheable(key = "#id")
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

    @CachePut(key = "#student.id")
    @Override
    public Student insert(Student student) {
        this.save(student);
        return student;
    }

    @CacheEvict(key = "#id")
    @Override
    public void deleteById(Long id) {
        this.baseMapper.deleteById(id);
    }
}
