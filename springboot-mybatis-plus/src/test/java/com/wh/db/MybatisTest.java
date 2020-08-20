package com.wh.db;

import com.github.pagehelper.PageInfo;
import com.wh.db.dto.StudentDTO;
import com.wh.db.entity.Student;
import com.wh.db.service.impl.StudentServiceImpl;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
@SpringBootTest
@RunWith(SpringRunner.class)
public class MybatisTest {

    private static final Logger log = LoggerFactory.getLogger(MybatisTest.class);

    @Autowired
    private StudentServiceImpl studentService;

    @Test
    public void should_returnList() {
        List<Student> students = studentService.selectList();
        log.info("studentList:{}", students);
        MatcherAssert.assertThat(students.size(), Matchers.greaterThan(0));
    }

    @Test
    public void should_returnOne() {
        StudentDTO detail = studentService.detail("21");
        log.info("detail:{}", detail);
        MatcherAssert.assertThat(detail, Matchers.notNullValue());
    }

    @Test
    public void should_returnLteThanPageSize() {
        PageInfo<Student> studentPageInfo = studentService.selectListByPage();
        MatcherAssert.assertThat(studentPageInfo.getList().size(), Matchers.lessThanOrEqualTo(studentPageInfo.getPageSize()));
    }
}
