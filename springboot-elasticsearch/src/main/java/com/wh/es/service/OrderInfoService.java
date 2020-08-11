package com.wh.es.service;

import com.wh.common.response.CommonPage;
import com.wh.es.entity.OrderInfo;
import com.wh.es.vo.OrderInfoVO;

/**
 * <p>
 *
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0.0
 * @date 2020/08/10
 */
public interface OrderInfoService {

    CommonPage<OrderInfo> list(OrderInfoVO orderInfoVO);

    CommonPage<OrderInfo> listByCondition(OrderInfoVO orderInfoVO);

    String update(OrderInfo orderInfo);

    OrderInfo updateByRepository(OrderInfo orderInfo);

    String deleteById(String id);
}
