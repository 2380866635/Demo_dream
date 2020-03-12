package com.dream.service.impl;

import com.dream.common.pojo.DreamResult;
import com.dream.common.pojo.EasyUiDataGridResult;
import com.dream.common.pojo.UUIDUtils;
import com.dream.mapper.TbItemDescMapper;
import com.dream.mapper.TbItemMapper;
import com.dream.pojo.TbItem;
import com.dream.pojo.TbItemDesc;
import com.dream.pojo.TbItemExample;
import com.dream.service.ItemService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;
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

    @Override
    public DreamResult save(TbItem tbItem, String desc) {
        //补全添加的商品信息
        //商品的id
        long itemId= UUIDUtils.getItemId();
        //商品的其他描述
        tbItem.setId(itemId);
        tbItem.setStatus((byte)1);
        tbItem.setCreated(new Date());
        tbItem.setUpdated(tbItem.getCreated());
        //将商品信息添加到数据库
        tbItemMapper.insertSelective(tbItem);
        //将商品描述的信息表补全
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(itemId);
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setCreated(new Date());
        tbItemDesc.setUpdated(tbItemDesc.getCreated());
        //将信息添加到数据库表中
        tbItemDescMapper.insertSelective(tbItemDesc);
        return DreamResult.ok();
    }
}
