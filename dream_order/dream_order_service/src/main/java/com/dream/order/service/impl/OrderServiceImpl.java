package com.dream.order.service.impl;

import com.dream.common.pojo.DreamResult;
import com.dream.mapper.TbOrderItemMapper;
import com.dream.mapper.TbOrderMapper;
import com.dream.mapper.TbOrderShippingMapper;
import com.dream.order.jedis.JedisClient;
import com.dream.order.pojo.OrderInfo;
import com.dream.order.service.OrderService;
import com.dream.pojo.TbOrderItem;
import com.dream.pojo.TbOrderShipping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
   @Autowired
   private TbOrderMapper tbOrderMapper;
   @Autowired
   private TbOrderItemMapper tbOrderItemMapper;
   @Autowired
   private TbOrderShippingMapper tbOrderShippingMapper;
   @Autowired
   private JedisClient jedisClient;
   @Value("${ORDER_GEN_KEY}")
    private String ORDER_GEN_KEY;
   @Value("${ORDER_DETAIL_GEN_KEY}")
   private String ORDER_DETAIL_GEN_KEY;
   @Value("${ORGER_ID_INIT}")
    private String ORGER_ID_INIT;
    @Override
    public DreamResult createOrder(OrderInfo orderInfo) {
        //生成一个订单号使用 redis进行存储 incr来生成ORDER_GER_KEY订单号
        //判斷訂單是否存在
        if(!jedisClient.exists(ORDER_GEN_KEY)){
            //如果不存在进行初始化
            jedisClient.set(ORDER_GEN_KEY,ORGER_ID_INIT);
        }
        //如果存在
        String orderId = jedisClient.incr(ORDER_GEN_KEY).toString();
        //向订单中插入数据
        orderInfo.setOrderId(orderId);
        //包邮
        orderInfo.setPostFee("0");
        //订单状态
        orderInfo.setStatus(1);
        //创建和修改时间
        orderInfo.setCreateTime(new Date());
        orderInfo.setUpdateTime(orderInfo.getCreateTime());
        //插入数据
        tbOrderMapper.insert(orderInfo);
        //向订单明细表中插入数据
        List<TbOrderItem> orderItems = orderInfo.getOrderItems();
        for (TbOrderItem orderItem:orderItems){
            //生成一个订单明细表的主键
            Long incr = jedisClient.incr(ORDER_DETAIL_GEN_KEY);

            orderItem.setId(incr.toString());
            //设置订单id
            orderItem.setOrderId(orderId);
            //插入数据
            tbOrderItemMapper.insert(orderItem);
        }
        //向订单物流表中插入信息
        TbOrderShipping orderShipping = orderInfo.getOrderShipping();
        //设置订单ID
        orderShipping.setOrderId(orderId);
        orderShipping.setCreated(new Date());
        orderShipping.setUpdated(orderShipping.getCreated());
        //插入
        tbOrderShippingMapper.insert(orderShipping);
        return DreamResult.ok(orderId);
    }
}
