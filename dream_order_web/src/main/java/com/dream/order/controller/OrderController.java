package com.dream.order.controller;

import com.alibaba.fastjson.JSONArray;
import com.dream.common.pojo.CookieUtils;
import com.dream.common.pojo.DreamResult;
import com.dream.order.pojo.OrderInfo;
import com.dream.order.service.OrderService;
import com.dream.pojo.TbItem;
import com.dream.pojo.TbUser;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

   // @Autowired
    private OrderService orderService;
    @Value("${COOKIE_CART_KEY}")
    private String COOKIE_CART_KEY;
   @RequestMapping(value = "/order/create",method = RequestMethod.POST)
   public String createOrder(OrderInfo orderInfo,HttpServletRequest request){
       TbUser user = (TbUser) request.getAttribute("user");
       orderInfo.setUserId(user.getId());
       orderInfo.setBuyerNick(user.getUsername());
       //调用生成订单
       DreamResult order = orderService.createOrder(orderInfo);
       request.setAttribute("orderId",order.getData().toString());
       request.setAttribute("payment",orderInfo.getPayment());
        //预计送达时间
       //要把当前时间加上配送时间 ，要使calendar来进行时间的增加太麻烦 使用joda插件
       DateTime dateTime = new DateTime();
       //三天后
       dateTime = dateTime.plusDays(3);
        request.setAttribute("date",dateTime.toString("yyyy-MM-dd"));
    return "success";
   }

    @RequestMapping("/order/order-cart")
    public String showOrderCart(HttpServletRequest request){
        //获得商品列表
        List<TbItem> cartList = this.getCartList(request);
        //获取用户ID
        TbUser user = (TbUser) request.getAttribute("user");

       System.out.println("这是测试瓶"+cartList.size());
        //正常来说应该根据用户id进行查询 收货地址  在调用 业务层根据user_Id查询地址 这里绑定转发 使用静态数据
        request.setAttribute("cartList",cartList);
        return "order-cart";
    }


    //从cookie中获取商品列表
    private List<TbItem> getCartList(HttpServletRequest request) {
        String cookieValue = CookieUtils.getCookieValue(request,COOKIE_CART_KEY,
                true);
        //判断是否有商品列表
        if(StringUtils.isBlank(cookieValue)){
            return new ArrayList<>();
        }
        //如果有 吧Json数据转换为集合
        //List<TbItem> tbItems = JSON.parseArray(cookieValue, TbItem.class);
        List<TbItem> tbItems = JSONArray.parseArray(cookieValue, TbItem.class);
        return  tbItems;
    }
}
