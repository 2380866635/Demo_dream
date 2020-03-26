package com.dream.cart.controller;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.fastjson.JSONArray;
import com.dream.common.pojo.CookieUtils;
import com.dream.common.pojo.DreamResult;
import com.dream.pojo.TbItem;
import com.dream.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    private ItemService itemService;
    @Value("${DREAM_CART_COOKIE_KEY}")
    private String DREAM_CART_COOKIE_KEY;
    @Value("${COOKIE_CART_EXPIRE}")
    private Integer COOKIE_CART_EXPIRE;

    @RequestMapping("/cart/add/{itemId}")
    public String addCart(@PathVariable Long itemId, Integer num,
                          HttpServletRequest request, HttpServletResponse response) {
        //先从cookic中获取商品列表
        List<TbItem> cartList = this.getCartList(request);
        //判断购物车中是否有此商品
        boolean flag=false;
        for (TbItem item:cartList){
            if (item.getId()==itemId.longValue()){
                item.setNum(item.getNum()+num);//num 原本是库存 放在这里当购物的数量看
                flag=true;
                break;
            }
        }
        //购物车中没有商品
        if (!flag) {
            //1根据item 查找到商品信息
            TbItem tbItem = itemService.selectByKey(itemId);
            tbItem.setNum(num);
            //获取第一张图片呢
            String image = tbItem.getImage();
            if (StringUtils.isNoneBlank(image)){
                tbItem.setImage(image.split(",")[0]);
            }
            cartList.add(tbItem);
        }
        try {
            CookieUtils.setCookie(request,response,DREAM_CART_COOKIE_KEY,
                    JSON.json(cartList),COOKIE_CART_EXPIRE,true);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "cartSuccess";
    }

    @RequestMapping("/cart/cart")

    public  String showCart(HttpServletRequest request){
        //获取商品列表
        List<TbItem> cartList = this.getCartList(request);
        request.setAttribute("cartList",cartList);
        return "cart";
    }
    @RequestMapping("/cart/update/num/{itemId}/{num}")
    @ResponseBody
    public DreamResult updateNum(@PathVariable Long itemId, @PathVariable Integer num,
                                 HttpServletRequest request,HttpServletResponse response){
        List<TbItem> cartList = getCartList(request);
        //遍历商品
        for (TbItem item:cartList){
            if (item.getId()==itemId.longValue()){
                //更改商品数量
                item.setNum(num);
                break;
            }
        }
        //重新写人cookie中
        try {
            CookieUtils.setCookie(request,response,DREAM_CART_COOKIE_KEY,JSON.json(cartList),COOKIE_CART_EXPIRE,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  DreamResult.ok();
    }
    @RequestMapping("/cart/delete/{itemId}")
    public  String deleteCartItem(@PathVariable Long itemId,HttpServletResponse response,
                                  HttpServletRequest request){
        List<TbItem> cartList = getCartList(request);
        for (TbItem item:cartList){
            //商品id存在于购物车
            if (item.getId()==itemId.longValue()){
                //从集合中删除
                cartList.remove(item);
                break;
            }
        }
        try {
            CookieUtils.setCookie(request,response,DREAM_CART_COOKIE_KEY,
                    JSON.json(cartList),COOKIE_CART_EXPIRE,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/cart/cart.html"; //forward 转发
    }
    //从cookie中获取商品列表
    private List<TbItem> getCartList(HttpServletRequest request) {
        String cookieValue = CookieUtils.getCookieValue(request, DREAM_CART_COOKIE_KEY,
                true);
        //判断是否有商品列表
        if(StringUtils.isBlank(cookieValue)){
            return new ArrayList<>();
        }
        //如果有 吧Json数据转换为集合
        List<TbItem> tbItems = JSONArray.parseArray(cookieValue, TbItem.class);
        return  tbItems;
    }

}
