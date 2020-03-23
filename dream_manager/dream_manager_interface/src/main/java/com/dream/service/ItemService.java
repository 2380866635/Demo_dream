package com.dream.service;

import com.dream.common.pojo.DreamResult;
import com.dream.common.pojo.EasyUiDataGridResult;
import com.dream.pojo.TbItem;
import com.dream.pojo.TbItemDesc;

import java.util.List;

public interface ItemService {
    /**
     * 删除功能
     * @param itemIds
     * @return
     */
    DreamResult delete(List<Long> itemIds);

    /**
     * 根据itemId查询商品详细
     * @param itemId
     * @return
     */
    TbItemDesc getItemDescById(long itemId);

    /**
     * 根据商品Id进行查询
     * @param itemId
     * @return
     */
    TbItem selectByKey(long itemId);

    /**
     * 分页查询所有商品
     * @param pageNum
     * @param pageSize
     * @return
     */
    EasyUiDataGridResult list(int pageNum, int pageSize);
    /**
     * 增加商品功能 将增加商品添加到数据库
     * @param tbItem
     * @param desc
     * @return
     */
    DreamResult save(TbItem tbItem, String desc);

    /**
     * 修改商品信息功能
     * @param tbItem
     * @param desc
     * @return
     */
    DreamResult update(TbItem tbItem, String desc);
}
