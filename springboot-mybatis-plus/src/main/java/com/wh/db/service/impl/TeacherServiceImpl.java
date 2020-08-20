package com.wh.db.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wh.db.entity.Teacher;
import com.wh.db.mapper.TeacherMapper;
import com.wh.db.service.TeacherService;
import org.springframework.stereotype.Service;

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
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
}
