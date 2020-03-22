package com.dream.search.dao;

import com.dream.common.pojo.SearchItem;
import com.dream.common.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class SearchItemDaoImpl implements SearchItemDao{
    @Autowired
    private SolrServer solrServer;

    @Override
    public SearchResult search(SolrQuery query) throws Exception {
       //根据SolrQuery搜索索引库
        QueryResponse response=solrServer.query(query);
        //获取查询结果
        SolrDocumentList list = response.getResults();
        //商品列表
        ArrayList<SearchItem> itemList = new ArrayList<>();
        for (SolrDocument documents:list){
            //把查询结果内容取得封装到itemList中
            SearchItem item = new SearchItem();
            item.setId((String) documents.get("id"));
            item.setCategoryName((String) documents.get("item_category_name"));
            item.setImage((String) documents.get("item_image"));
            item.setPrice((Long) documents.get("item_price"));
            item.setSellPoint((String) documents.get("item_sell_point"));
            //取得高亮显示
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            //取得高亮显示的title
            List<String> titleList= highlighting.get(documents.get("id")).get("item_title");
            String itemTitle="";
            //有高亮的显示内容时
            if (titleList!=null && titleList.size()>0){
                //高亮显示不止一个我们取第一个
                itemTitle=titleList.get(0);
            }else {
                //如果没有高亮显示内容时
                itemTitle= (String) documents.get("item_title");
            }
            item.setTitle(itemTitle);
            //把封装的商品封装到集合

            itemList.add(item);
        }
        //返回SearchResult对象
        SearchResult searchResult = new SearchResult();
        searchResult.setItemList(itemList);
        //总记录数
        searchResult.setTotalNum(list.getNumFound());
        return searchResult;
    }
}
