package com.lomtom.search.main.service.impl;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.lomtom.search.common.pojo.EsResult;
import com.lomtom.search.common.util.Constant;
import com.lomtom.search.main.entity.InfoEntity;
import com.lomtom.search.main.mapper.InfoMapper;
import com.lomtom.search.main.service.InfoSearchService;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: LOMTOM
 * @Date: 2021/4/14
 * @Time: 15:34
 */

@DS("slave")
@Service
public class InfoSearchServiceImpl implements InfoSearchService {


    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private InfoMapper infoMapper;


    /**
     * 按照查询条件进行数据查询
     */
    @Override
    public Map<String, Object> search(Map<String, String> searchMap) {
        if (searchMap != null) {
            HashMap<String, Object> hashMap = new HashMap<>();
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();

            //关键字查询
            String keywords = searchMap.get(Constant.KEYWORDS);
            if (StringUtils.isNotEmpty(keywords)) {
                booleanQueryBuilder.must(QueryBuilders.matchQuery(searchMap.get(Constant.SEARCH_FIELD), keywords).operator(Operator.AND));

                //设置高亮域以及高亮的样式
                //高亮域
                HighlightBuilder.Field highlight = new HighlightBuilder.Field(searchMap.get(Constant.SEARCH_FIELD))
                        //高亮样式的前缀
                        .preTags("<span style='color:red'>")
                        //高亮样式的后缀
                        .postTags("</span>");
                nativeSearchQueryBuilder.withHighlightFields(highlight);
            }

            //根据来源筛选
            String source = searchMap.get(Constant.SOURCE);
            if (StringUtils.isNotEmpty(source)) {
                booleanQueryBuilder.must(QueryBuilders.matchQuery(Constant.SOURCE, source).operator(Operator.AND));
            }

            //按照年分区间
            String year = searchMap.get(Constant.YEAR);
            if (StringUtils.isNotEmpty(year)) {
                String[] years = year.split("-");
                booleanQueryBuilder.must(QueryBuilders.rangeQuery(Constant.TIME).lt(years[1]).gte(years[0]));
//                booleanQueryBuilder.filter(QueryBuilders.rangeQuery(Constant.TIME).gte(years[0]));
            }

//            TermsAggregationBuilder field = AggregationBuilders.terms("sources").field("source").size(10);
//            nativeSearchQueryBuilder.addAggregation(field);

            nativeSearchQueryBuilder.withQuery(booleanQueryBuilder);

            //开启分页查询
            String pageNum = searchMap.get(Constant.PAGE);
            String pageSize = searchMap.get(Constant.LIMIT);
            if (StringUtils.isEmpty(pageNum)) {
                pageNum = "1";
            }
            if (StringUtils.isEmpty(pageSize)) {
                pageSize = "10";
            }
            //设置分页
            //第一个参数:当前页 是从0开始
            //第二个参数:每页显示多少条
            nativeSearchQueryBuilder.withPageable(PageRequest.of(Integer.parseInt(pageNum) - 1, Integer.parseInt(pageSize)));

            //按照时间、访问量排序
            String sortField = searchMap.get(Constant.SORT_FIELD);
            if (StringUtils.isNotEmpty(sortField)) {
                String sortRule = searchMap.get(Constant.SORT_RULE);
                if (Constant.ASC.equals(sortRule)) {
                    //升序
                    nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort(sortField).order(SortOrder.ASC));
                } else {
                    //降序
                    nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort(sortField).order(SortOrder.DESC));
                }

            }


            SearchHits<InfoEntity> search = elasticsearchRestTemplate.search(nativeSearchQueryBuilder.build(), InfoEntity.class);

            hashMap.put("total", search.getTotalHits());
            float maxScore = search.getMaxScore();
            if (Float.isNaN(maxScore)) {
                List<SearchHit<InfoEntity>> searchHits = search.getSearchHits();
                List<EsResult<InfoEntity>> list = new ArrayList<>();
                for (SearchHit<InfoEntity> infoEntitySearchHit : searchHits) {
                    list.add(new EsResult<>(infoEntitySearchHit));
                }
                hashMap.put("list", list);
            } else {
                hashMap.put("maxScore", maxScore);
                hashMap.put("list", search.getSearchHits());
            }
            List<String> strings = infoMapper.selectSources();
            hashMap.put("source", strings);
            return hashMap;
        }
        return null;
    }
}
