package com.wh.cache.test;

import com.wh.cache.entity.Student;
import com.wh.cache.service.StudentService;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0.0
 * @date 2020/08/21
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CacheTest {

    private static final Logger log = LoggerFactory.getLogger(CacheTest.class);

    private static final String CACHE_NAME_USER = "students";

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private StudentService studentService;

    @Test
    public void testSelectById() {
        Student student1 = studentService.selectById("21");
        log.info("student:{}", student1);
        // 判断缓存中是否已存在
        Cache cache = cacheManager.getCache(CACHE_NAME_USER);
        MatcherAssert.assertThat(cache, Matchers.notNullValue());
        Student cacheStudent = cache.get("21", Student.class);
        MatcherAssert.assertThat(cacheStudent, Matchers.notNullValue());

        // 再次查询
        Student student2 = studentService.selectById("21");
        MatcherAssert.assertThat(student2, Matchers.notNullValue());
    }

    @Test
    public void testInsertStudent() {
        Student student = new Student();
        student.setAge(18);
        student.setGender("1");
        student.setGmtCreate(LocalDateTime.now());
        student.setName("Tom");
        student.setBirthday(new Date());
        Student insert = studentService.insert(student);
        log.info("insert:{}", insert);
        // 判断缓存中是否已存在
        Cache cache = cacheManager.getCache(CACHE_NAME_USER);
        MatcherAssert.assertThat(cache, Matchers.notNullValue());
        Student cacheStudent = cache.get(student.getId(), Student.class);
        log.info("cacheStudent:{}", cacheStudent);
        MatcherAssert.assertThat(cacheStudent, Matchers.notNullValue());
    }
}
