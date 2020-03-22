package com.dream.search.dao;

import com.dream.common.pojo.SearchItem;
import com.dream.common.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;

public interface SearchItemDao {

    SearchResult search(SolrQuery query) throws Exception;


}
