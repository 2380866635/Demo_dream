package com.dream.search.dao;

import com.dream.common.pojo.DreamResult;
import com.dream.common.pojo.SearchItem;
import com.dream.common.pojo.SearchResult;
import com.dream.search.mapper.SearchItemMapper;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class SearchItemDaoImpl implements SearchItemDao{
    @Autowired
    private SolrServer solrServer;
    @Autowired
    private SearchItemMapper searchItemMapper;

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

    @Override
    public DreamResult updateSearchItemById(Long itemId) throws Exception {
        //调用Mapper来查询内容
        SearchItem item = searchItemMapper.getItemById(itemId);
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id",item.getId());
        document.addField("item_title",item.getTitle());
        document.addField("item_sell_point",item.getSellPoint());
        document.addField("item_image",item.getImage());
        document.addField("item_price",item.getPrice());
        document.addField("item_category_name",item.getCategoryName());
        document.addField("itemm_desc",item.getItemDesc());
        //向索引库添加文档
        solrServer.add(document);
        //提交
        solrServer.commit();
        return DreamResult.ok();
    }
}
