package com.dream.order.service;

import com.dream.common.pojo.DreamResult;
import com.dream.order.pojo.OrderInfo;

public interface OrderService {
    /**
     * 生成订单
     * @param orderInfo
     * @return
     */
    DreamResult createOrder(OrderInfo orderInfo);

}
