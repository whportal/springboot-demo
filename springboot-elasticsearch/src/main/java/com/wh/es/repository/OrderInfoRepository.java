package com.wh.es.repository;

import com.wh.es.entity.OrderInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * <p>
 *
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0.0
 * @date 2020/08/10
 */
public interface OrderInfoRepository extends ElasticsearchRepository<OrderInfo,String> {
}
