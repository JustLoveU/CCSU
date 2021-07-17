package com.lomtom.search.common.pojo;

import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.document.NestedMetaData;

import java.util.List;
import java.util.Map;

/**
 * @Author: LOMTOM
 * @Date: 2021/4/14
 * @Time: 17:51
 */


public class EsResult <T>{

    private  String index;

    private  String id;

    private  List<Object> sortValues;

    private  T content;

    private  Map<String, List<String>> highlightFields;

    private  Map<String, SearchHits<?>> innerHits;

    private  NestedMetaData nestedMetaData;

    public EsResult(SearchHit<T> searchHit) {
        this.index = searchHit.getIndex();
        this.id = searchHit.getId();
        this.sortValues = searchHit.getSortValues();
        this.content = searchHit.getContent();
        this.highlightFields = searchHit.getHighlightFields();
        this.innerHits = searchHit.getInnerHits();
        this.nestedMetaData = searchHit.getNestedMetaData();
    }
}
