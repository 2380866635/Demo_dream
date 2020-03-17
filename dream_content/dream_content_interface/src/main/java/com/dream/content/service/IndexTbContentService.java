package com.dream.content.service;

import com.dream.pojo.TbContent;

import java.util.List;

public interface IndexTbContentService {
    /**
     *
     * @param ad1_categoy_id
     * @return
     */
    List<TbContent> selectContentByCategoryId(Long ad1_categoy_id);
}
