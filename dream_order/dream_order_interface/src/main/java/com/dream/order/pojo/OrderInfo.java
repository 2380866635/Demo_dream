package com.dream.order.pojo;

import com.dream.pojo.TbOrder;
import com.dream.pojo.TbOrderItem;
import com.dream.pojo.TbOrderShipping;

import java.io.Serializable;
import java.util.List;

public class OrderInfo extends TbOrder implements Serializable {

    //一个订单对应多个商品信息
    private List<TbOrderItem> orderItems;
    //一个订单只能有一个收货地址
    private TbOrderShipping orderShipping;

    public List<TbOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<TbOrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public TbOrderShipping getOrderShipping() {
        return orderShipping;
    }

    public void setOrderShipping(TbOrderShipping orderShipping) {
        this.orderShipping = orderShipping;
    }
}
