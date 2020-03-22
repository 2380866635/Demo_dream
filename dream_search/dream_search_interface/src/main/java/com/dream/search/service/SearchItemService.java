package com.dream.search.service;

import com.dream.common.pojo.DreamResult;
import com.dream.common.pojo.SearchResult;

import java.io.IOException;

public interface SearchItemService {
    /**
     * 导入索引
     * @return 导入是否成功
     * @throws Exception
     */
     DreamResult importAllItems() throws Exception;

    /**
     * 删除索引
     * @return 删除是否成功
     * @throws Exception
     */
     DreamResult deleteAllItems() throws Exception;

    /**
     * 条件查询索引返回需数据
     * @param queryString 查询信息
     * @param page 当前页
     * @param rows 显示条数
     * @return
     * @throws Exception
     */
     SearchResult search(String queryString,Integer page,Integer
                         rows) throws Exception;

}
