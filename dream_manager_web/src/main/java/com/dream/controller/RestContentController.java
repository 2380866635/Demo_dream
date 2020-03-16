package com.dream.controller;

import com.dream.common.pojo.DreamResult;
import com.dream.content.service.TbContentService;
import com.dream.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/rest/content")
public class RestContentController {

    @Autowired
    private TbContentService tbcontentService;

    @RequestMapping("/edit")
    public DreamResult contentEdit(TbContent tbContent){
        return tbcontentService.contentEdit(tbContent);
    }
}
