package com.wh.es.controller;

import com.wh.common.response.CommonPage;
import com.wh.common.response.Result;
import com.wh.es.entity.OrderInfo;
import com.wh.es.service.OrderInfoService;
import com.wh.es.vo.OrderInfoVO;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * <p>
 *
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0.0
 * @date 2020/08/10
 */
@RestController
@RequestMapping("search")
public class ElasticSearchController {

    private OrderInfoService orderInfoService;

    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    private RestHighLevelClient restHighLevelClient;

    @Autowired
    public ElasticSearchController(OrderInfoService orderInfoService, ElasticsearchRestTemplate elasticsearchRestTemplate, RestHighLevelClient restHighLevelClient) {
        this.orderInfoService = orderInfoService;
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
        this.restHighLevelClient = restHighLevelClient;
    }

    @GetMapping("list")
    public Result<CommonPage<OrderInfo>> list(OrderInfoVO orderInfoVO) {
        return Result.success(orderInfoService.list(orderInfoVO));
    }

    @GetMapping("list/search")
    public Result<CommonPage<OrderInfo>> listByCondition(OrderInfoVO orderInfoVO) {
        return Result.success(orderInfoService.listByCondition(orderInfoVO));
    }

    @GetMapping("index/create")
    public Result<Boolean> createIndex() {
        boolean result = elasticsearchRestTemplate.createIndex(OrderInfo.class);
        return Result.success(result);
    }

    @GetMapping("index/delete")
    public Result<Boolean> deleteIndex() {
        boolean result = elasticsearchRestTemplate.deleteIndex(OrderInfo.class);
        return Result.success(result);
    }

    @GetMapping("list/template")
    public Result<CommonPage<SearchHit<OrderInfo>>> lSearchResultMapperistByTemplate(OrderInfoVO orderInfoVO) {
        Query query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchAllQuery())
                .withPageable(PageRequest.of(orderInfoVO.getPageNum(), orderInfoVO.getPageSize()))
                .build();
        SearchHits<OrderInfo> orderInfos = elasticsearchRestTemplate.search(query, OrderInfo.class);
        CommonPage<SearchHit<OrderInfo>> page = new CommonPage<>();
        page.setList(orderInfos.getSearchHits());
        page.setTotal(orderInfos.getTotalHits());
        return Result.success(page);
    }

    @GetMapping("list/client")
    public GetResponse listByClient() throws IOException {
        GetRequest getRequest = new GetRequest().index("test-20200807").id("2019121140000006");
        return restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
    }

    @PutMapping("update")
    public Result<String> update(@RequestBody OrderInfo orderInfo) {
        return Result.success(orderInfoService.update(orderInfo));
    }

    @PutMapping("update/v2")
    public Result<OrderInfo> updateByRepository(@RequestBody OrderInfo orderInfo) {
        return Result.success(orderInfoService.updateByRepository(orderInfo));
    }

    @DeleteMapping("{id}")
    public Result<String> delete(@PathVariable("id") String id) {
        return Result.success(orderInfoService.deleteById(id));
    }
}
