package com.dream.search.service.impl;

import com.dream.common.pojo.DreamResult;
import com.dream.common.pojo.SearchItem;
import com.dream.common.pojo.SearchResult;
import com.dream.search.dao.SearchItemDao;
import com.dream.search.mapper.SearchItemMapper;
import com.dream.search.service.SearchItemService;
import org.apache.solr.client.solrj.SolrQuery;
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
    @Autowired
    private SearchItemDao searchItemDao;

    /**
     * 搜索索引内容
     * @param queryString 查询信息
     * @param page 当前页
     * @param rows 显示条数
     * @return
     * @throws Exception
     */
    @Override
    public SearchResult search(String queryString, Integer page, Integer rows) throws Exception {
        //1创建一个SolrQuery对象
        SolrQuery solrQuery = new SolrQuery();
        //2设置搜索条件
        solrQuery.setQuery(queryString);
        //3 设置分页条件 查询索引库 跟查询数据库的概念的一样的 start跟limit一样起始下标
        solrQuery.setStart((page-1)*rows);
        solrQuery.setRows(rows);
        //4指定默认的搜索域
        solrQuery.set("df","item_title");
        //5 设置高亮显示
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePre("<em style=\"color:red\">");
        // 中間就是高亮的内容
        solrQuery.setHighlightSimplePost("</em>");
        //6 执行查询
        SearchResult search = searchItemDao.search(solrQuery);
        //7设置总页数 总记录数/每页显示的条数 5.1-》6。0 取整
        Long totalNum = search.getTotalNum();
        long totalPages = totalNum/rows;
        if(totalNum%rows>0){
            totalPages++;
        }
        search.setTotalPages(totalPages);
        return search;
    }

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

    //删除索引方法
    public DreamResult deleteAllItems() throws Exception {
        List<SearchItem> itemList=searchItemMapper.getSerarchItemList();
            for (SearchItem searchItem:itemList){
                    solrServer.deleteById(searchItem.getId());
            }
             solrServer.commit();
            return DreamResult.ok();
    }
    //更新索引方法
    public DreamResult updateSearchItemById(Long id) throws Exception {
        return searchItemDao.updateSearchItemById(id);
    }


}
