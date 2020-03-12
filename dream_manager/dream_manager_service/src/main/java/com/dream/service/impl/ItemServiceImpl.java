package com.dream.service.impl;

import com.dream.common.pojo.DreamResult;
import com.dream.common.pojo.EasyUiDataGridResult;
import com.dream.mapper.TbItemMapper;
import com.dream.pojo.TbItem;
import com.dream.pojo.TbItemExample;
import com.dream.service.ItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper tbItemMapper;

    @Override
    public DreamResult delete(List<Long> itemIds) {
        TbItemExample tbItemExample = new TbItemExample();
        TbItemExample.Criteria criteria = tbItemExample.createCriteria();
        criteria.andIdIn(itemIds);
        tbItemMapper.deleteByExample(tbItemExample);
        return DreamResult.ok();
    }

    @Override
    public TbItem selectByKey(long itemId) {
        return tbItemMapper.selectByPrimaryKey(itemId);
    }

    @Override
    public EasyUiDataGridResult list(int pageNum, int pageSize) {
        TbItemExample tbItemExample = new TbItemExample();
        tbItemExample.setOrderByClause("updated desc");

        List<TbItem> tbItems=tbItemMapper.selectByExample(tbItemExample);

        PageInfo<TbItem> tbItemPageInfo=new PageInfo<>(tbItems);
        long total=tbItemPageInfo.getTotal();
        return new EasyUiDataGridResult(total,tbItems);
    }
}
