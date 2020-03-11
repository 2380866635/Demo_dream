package com.dream.service;

import com.dream.pojo.TbItem;

public interface ItemService {
    TbItem selectByKey(long itemId);
}
