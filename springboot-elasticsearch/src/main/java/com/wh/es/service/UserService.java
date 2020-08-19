package com.wh.es.service;

import com.wh.common.response.CommonPage;
import com.wh.es.dto.UserDTO;
import com.wh.es.entity.User;
import com.wh.es.vo.UserVO;

/**
 * <p>
 *
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0.0
 * @date 2020/08/18
 */
public interface UserService {

    User save(User user);

    CommonPage<UserDTO> search(UserVO vo);

    User getById(Long id);
}
