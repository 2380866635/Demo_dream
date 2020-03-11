package com.dream.controller;


import com.dream.common.pojo.DreamResult;
import com.dream.common.pojo.EasyUiDataGridResult;
import com.dream.pojo.TbItem;
import com.dream.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/selectByKey/{itemId}")
    public TbItem selectByKey(@PathVariable long itemId){
        return itemService.selectByKey(itemId);
    }
    @RequestMapping("/list")
    public EasyUiDataGridResult list(@RequestParam("page")int pageNum,
                                     @RequestParam("rows")int pageSize){
        return itemService.list(pageNum,pageSize);
    }
    @RequestMapping("/delete")
    public DreamResult delete(Long [] ids){
        return itemService.delete(Arrays.asList(ids));
    }

}
