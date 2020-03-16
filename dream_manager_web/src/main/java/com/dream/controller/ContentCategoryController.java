package com.dream.controller;

import com.dream.common.pojo.DreamResult;
import com.dream.common.pojo.EasyUiTreeNode;
import com.dream.content.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/content/category")
public class ContentCategoryController{
    @Autowired
    private TbContentCategoryService tbContentCategoryService;

    @RequestMapping("/list")
    public List<EasyUiTreeNode> list(@RequestParam(value = "id",defaultValue ="0")Long parentId){

        return tbContentCategoryService.getContentCategoryByParentId(parentId);
    }
    @RequestMapping("/create")
    public DreamResult create(long parentId,String name){
        return tbContentCategoryService.create(parentId,name);
    }
    @RequestMapping("/delete")
    public DreamResult delete(long id){
        return tbContentCategoryService.delete(id);
    }
    @RequestMapping("/update")
    public  DreamResult update(long id,String name){
        return tbContentCategoryService.update(id,name);
    }
}
