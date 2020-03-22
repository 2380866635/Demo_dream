package com.dream.controller;

import com.dream.common.pojo.DreamResult;
import com.dream.common.pojo.EasyUiDataGridResult;
import com.dream.content.service.TbContentService;
import com.dream.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/content")
public class ContentController {
    @Autowired
    private TbContentService tbcontentService;

    @RequestMapping("/query/list")
    public EasyUiDataGridResult list(@RequestParam("page")int pageNum,
                                     @RequestParam("rows")int pageSize,
                                     long categoryId){
        return tbcontentService.list(pageNum,pageSize,categoryId);
    }
    @RequestMapping("/save")
    public DreamResult save(TbContent tbContent){
     return tbcontentService.save(tbContent);
    }
    @RequestMapping("/delete")
    public DreamResult delete(Long[] params){
        return tbcontentService.delete(Arrays.asList(params));
    }


}
