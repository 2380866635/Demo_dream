package com.dream.common.pojo;

import java.io.Serializable;
import java.util.List;

public class SearchResult implements Serializable {
    /**
     *查詢总页数 需要根据总数量和页码来计算
     */
    private Long totalPages;
    /**
     * 记录的总数
     */
    private Long totalNum;
    /**
     * 查询到的商品数据集合
     */
    private List<SearchItem> itemList;

    public Long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }

    public Long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }

    public List<SearchItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<SearchItem> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "totalPages=" + totalPages +
                ", totalNum=" + totalNum +
                ", itemList=" + itemList +
                '}';
    }
}
