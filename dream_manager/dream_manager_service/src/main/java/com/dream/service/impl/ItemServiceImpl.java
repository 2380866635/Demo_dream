package com.dream.service.impl;

import com.dream.mapper.TbItemMapper;
import com.dream.pojo.TbItem;
import com.dream.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper tbItemMapper;
    @Override
    public TbItem selectByKey(long itemId) {
        return tbItemMapper.selectByPrimaryKey(itemId);
    }
}
