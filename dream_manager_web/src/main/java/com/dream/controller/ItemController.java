package com.dream.controller;


import com.dream.pojo.TbItem;
import com.dream.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/selectByKey/{itemId}")
    public TbItem selectByKey(@PathVariable long itemId){
        return itemService.selectByKey(itemId);
    }
}
