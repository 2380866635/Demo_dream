package com.dream.content.service;

import com.dream.common.pojo.DreamResult;
import com.dream.common.pojo.EasyUiDataGridResult;
import com.dream.pojo.TbContent;

import java.util.List;

public interface TbContentService {
    /**
     * 查询功能
     * @param pageNum 当前页
     * @param pageSize 显示条数
     * @return
     */
    EasyUiDataGridResult list(int pageNum, int pageSize, long categoryId);

    /**
     * 广告栏内容增加方法
     * @param tbContent
     * @return
     */
    DreamResult save(TbContent tbContent);

    /**
     * 刪除方法
     * @param params
     * @return
     */
    DreamResult delete(List<Long> params);

    /**
     * 修改方法
     * @param tbContent
     * @return
     */
    DreamResult contentEdit(TbContent tbContent);

    /**
     * 根据Id查找到中间广告的内容进行轮播
     * @param ad1_categoy_id
     * @return
     */
    List<TbContent> selectContentByCategoryId(Long ad1_categoy_id);
}
