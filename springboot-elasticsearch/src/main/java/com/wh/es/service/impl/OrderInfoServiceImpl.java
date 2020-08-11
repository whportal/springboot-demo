package com.wh.es.service.impl;

import com.wh.common.response.CommonPage;
import com.wh.common.util.BeanUtilsExtend;
import com.wh.es.entity.OrderInfo;
import com.wh.es.repository.OrderInfoRepository;
import com.wh.es.service.OrderInfoService;
import com.wh.es.vo.OrderInfoVO;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.data.elasticsearch.core.query.UpdateResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0.0
 * @date 2020/08/10
 */
@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    private OrderInfoRepository orderInfoRepository;

    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    public OrderInfoServiceImpl(OrderInfoRepository orderInfoRepository, ElasticsearchRestTemplate elasticsearchRestTemplate) {
        this.orderInfoRepository = orderInfoRepository;
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
    }

    @Override
    public CommonPage<OrderInfo> list(OrderInfoVO orderInfoVO) {

        Pageable page = PageRequest.of(orderInfoVO.getPageNum(), orderInfoVO.getPageSize());
        Page<OrderInfo> orderInfoPage = orderInfoRepository.findAll(page);
        CommonPage<OrderInfo> commonPage = new CommonPage<>();
        commonPage.setList(orderInfoPage.getContent());
        commonPage.setTotalPage(orderInfoPage.getTotalPages());
        commonPage.setTotal(orderInfoPage.getTotalElements());
        return commonPage;
    }

    @Override
    public CommonPage<OrderInfo> listByCondition(OrderInfoVO orderInfoVO) {
        // 构建分页条件
        int pageNum = orderInfoVO.getPageNum();
        int pageSize = orderInfoVO.getPageSize();
        Pageable pageable = PageRequest.of(pageNum, pageSize);

        // 构建查询条件
        NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder().withPageable(pageable)
                .withSort(SortBuilders.fieldSort("gmt_create").order(SortOrder.DESC));
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (!StringUtils.isEmpty(orderInfoVO.getSearchParam())) {
            boolQueryBuilder.should(QueryBuilders.wildcardQuery("order_no", orderInfoVO.getSearchParam()))
                    .should(QueryBuilders.wildcardQuery("saleman_tel", orderInfoVO.getSearchParam()));
        }
        if (!StringUtils.isEmpty(orderInfoVO.getGmtCreateBegin())) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("gmt_create").gte(orderInfoVO.getGmtCreateBegin()));
        }
        if (!StringUtils.isEmpty(orderInfoVO.getGmtCreateEnd())) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("gmt_create").lte(orderInfoVO.getGmtCreateBegin()));
        }
        Query query = searchQueryBuilder.withQuery(boolQueryBuilder).build();
        SearchHits<OrderInfo> searchHits = elasticsearchRestTemplate.search(query, OrderInfo.class);
        List<SearchHit<OrderInfo>> searchHitList = searchHits.getSearchHits();
        List<OrderInfo> orderInfoList = searchHitList.stream().map(SearchHit::getContent).collect(Collectors.toList());
        CommonPage<OrderInfo> commonPage = new CommonPage<>();
        commonPage.setList(orderInfoList);
        long totalHits = searchHits.getTotalHits();
        commonPage.setTotal(totalHits);
        long totalPage = totalHits / pageSize;
        if (totalHits % pageSize != 0) {
            totalPage += 1;
        }
        commonPage.setTotalPage((int) totalPage);
        return commonPage;
    }

    @Override
    public String update(OrderInfo orderInfo) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("remark", orderInfo.getRemark());
        // 更新文档的部分内容
        UpdateQuery query = UpdateQuery.builder(orderInfo.getOrderNo())
                .withDocument(Document.from(map))
                .build();
        UpdateResponse response = elasticsearchRestTemplate.update(query, IndexCoordinates.of("test-20200807"));
        return response.getResult().toString();
    }

    @Override
    public OrderInfo updateByRepository(OrderInfo orderInfo) {
        // 先查询
        OrderInfo existOrderInfo = orderInfoRepository.findById(orderInfo.getOrderNo()).orElse(new OrderInfo());
        // 属性拷贝 忽略空值
        BeanUtils.copyProperties(orderInfo,existOrderInfo,BeanUtilsExtend.getNullPropertyNames(orderInfo));
        // 更新
        orderInfoRepository.save(existOrderInfo);
        return existOrderInfo;
    }

    @Override
    public String deleteById(String id) {
        return elasticsearchRestTemplate.delete(id, OrderInfo.class);
    }
}
