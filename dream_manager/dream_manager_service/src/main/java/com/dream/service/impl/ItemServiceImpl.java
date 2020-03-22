package com.dream.service.impl;

import com.dream.common.pojo.DreamResult;
import com.dream.common.pojo.EasyUiDataGridResult;
import com.dream.common.pojo.UUIDUtils;
import com.dream.mapper.TbItemDescMapper;
import com.dream.mapper.TbItemMapper;
import com.dream.pojo.TbItem;
import com.dream.pojo.TbItemDesc;
import com.dream.pojo.TbItemDescExample;
import com.dream.pojo.TbItemExample;
import com.dream.service.ItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private Destination destination;
    @Override
    public DreamResult delete(List<Long> itemIds) {
        TbItemExample tbItemExample = new TbItemExample();
        TbItemExample.Criteria criteria = tbItemExample.createCriteria();
        criteria.andIdIn(itemIds);
        tbItemMapper.deleteByExample(tbItemExample);
        for(Long itemid:itemIds){
            sendMqMessage(itemid+"");
        }
        return DreamResult.ok();
    }

    @Override
    public TbItem selectByKey(long itemId) {
        return tbItemMapper.selectByPrimaryKey(itemId);
    }

    //发送MQ消息
    private void sendMqMessage(String itemId){
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(itemId);
            }
        });
    }


    @Override
    public EasyUiDataGridResult list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
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
        sendMqMessage(itemId+"");
        return DreamResult.ok();
    }

    @Override
    public DreamResult update(TbItem tbItem, String desc) {
        //获得商品修改信息
        TbItemExample tbItemExample = new TbItemExample();
        TbItemExample.Criteria criteria = tbItemExample.createCriteria();
        criteria.andIdEqualTo(tbItem.getId());
        tbItem.setUpdated(new Date());
        tbItemMapper.updateByExampleSelective(tbItem,tbItemExample);
        //将商品描述的信息表补全
        TbItemDesc tbItemDesc = new TbItemDesc();
        TbItemDescExample tbItemDescExample = new TbItemDescExample();
        TbItemDescExample.Criteria criteria1 = tbItemDescExample.createCriteria();
        criteria1.andItemIdEqualTo(tbItem.getId());
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setUpdated(new Date());
        tbItemDescMapper.updateByExampleSelective(tbItemDesc,tbItemDescExample);
        //将修改的Id交给索引更新
        sendMqMessage(tbItem.getId()+"");
        return DreamResult.ok();
    }
}
