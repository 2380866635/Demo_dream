package com.dream.controller;

import com.dream.common.pojo.DreamResult;
import com.dream.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchItemController {
    @Autowired
    private SearchItemService searchItemService;
    @RequestMapping("/index/importAll")
    public DreamResult importAllItems(){
        try {
            return searchItemService.importAllItems();
        } catch (Exception e) {
            return DreamResult.build(500,"导入索引失败！",null);
        }
    }

    @RequestMapping("/index/deletsAll")
    public DreamResult deleteAllItems(){
        try {
            return searchItemService.deleteAllItems();
        } catch (Exception e) {
            return DreamResult.build(500,"刪除索引失败！",null);
        }
    }


}
