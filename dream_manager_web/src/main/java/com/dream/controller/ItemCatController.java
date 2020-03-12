package com.dream.controller;

import com.dream.common.pojo.EasyUiTreeNode;
import com.dream.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/item/cat")
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;
    @RequestMapping("/list")
    public List<EasyUiTreeNode> list(@RequestParam(value = "id",defaultValue ="0")Long parentId){
        return itemCatService.getItemCatListByParentId(parentId);
    }
}
