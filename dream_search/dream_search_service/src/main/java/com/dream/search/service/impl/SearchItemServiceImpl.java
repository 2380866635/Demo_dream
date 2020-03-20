package com.dream.search.service.impl;

import com.dream.common.pojo.DreamResult;
import com.dream.common.pojo.SearchItem;
import com.dream.search.mapper.SearchItemMapper;
import com.dream.search.service.SearchItemService;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class SearchItemServiceImpl implements SearchItemService {
    @Autowired
    private SearchItemMapper searchItemMapper;
    @Autowired
    private SolrServer solrServer;
    @Override
    public DreamResult importAllItems() throws Exception {
       //查詢所有商品數據
        List<SearchItem> itemList=searchItemMapper.getSerarchItemList();
        //循环将每个商品信息添加到Solr中
        for (SearchItem Item:itemList){
            //为每一个商品创建SolrInputDocument
            SolrInputDocument document = new SolrInputDocument();
            //为文档添加数据
            document.addField("id",Item.getId());
            document.addField("item_title",Item.getTitle());
            document.addField("item_sell_point",Item.getSellPoint());
            document.addField("item_price",Item.getPrice());
            document.addField("item_image",Item.getImage());
            document.addField("item_category_name",Item.getCategoryName());
            document.addField("item_desc",Item.getItemDesc());
            //将文档添加到索引库
            solrServer.add(document);
        }
        //提交
        solrServer.commit();
        return DreamResult.ok();
    }
}
