package com.dream.item.controller;

import com.dream.common.pojo.Item;
import com.dream.pojo.TbItem;
import com.dream.pojo.TbItemDesc;
import com.dream.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    /**
     * 查询商品详情  进行商品详情页面的准备
     * @param itemId
     * @param model
     * @return
     */

    @RequestMapping("/item/{itemId}")
    public String showItemInfo(@PathVariable Long itemId,Model model){
        //1.得到商品信息
        TbItem tbItem = itemService.selectByKey(itemId);
        TbItemDesc itemDescById = itemService.getItemDescById(itemId);
        //2.把商品信息封装到Item对象中去
        Item item = new Item(tbItem);
        //3.将商品信息进行绑定传给前端
        System.out.println("价格"+item.getPrice());
        model.addAttribute("item",item);
        model.addAttribute("itemDesc",itemDescById);
        return "item";
    }

}
