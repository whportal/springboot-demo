package com.wh.es.service.impl;

import com.wh.common.response.CommonPage;
import com.wh.es.dto.UserDTO;
import com.wh.es.entity.User;
import com.wh.es.repository.UserRepository;
import com.wh.es.service.UserService;
import com.wh.es.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

/**
 * <p>
 *
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0.0
 * @date 2020/08/18
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        // user.setBirthday(LocalDateTime.now());
        user.setBirthday(new Date());
        User save = userRepository.save(user);
        log.info("保存成功---{}", save);
        return save;
    }

    @Override
    public CommonPage<UserDTO> search(UserVO vo) {
        // 嵌套布尔查询
        // // 内层条件
        // BoolQueryBuilder innerBoolQueryBuilder = QueryBuilders.boolQuery();
        // innerBoolQueryBuilder.should(QueryBuilders.matchPhraseQuery("content","字一码"));
        // innerBoolQueryBuilder.should(QueryBuilders.matchPhraseQuery("topic","字一码"));
        //
        // // 外层条件
        // BoolQueryBuilder outerBoolQueryBuilder = QueryBuilders.boolQuery();
        // outerBoolQueryBuilder.must(QueryBuilders.termQuery("type", "1"));
        // outerBoolQueryBuilder.must(innerBoolQueryBuilder);
        return null;
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
