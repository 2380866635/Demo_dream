package com.dream.protal.controller;
import com.alibaba.fastjson.JSON;
import com.dream.content.service.TbContentService;
import com.dream.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexNodeController{
    @Value("${AD1_CATEGORY_ID}")
    private Long AD1_CATEGORY_ID;
    @Value("${AD1_WIDTH}")
    private String AD1_WIDTH;
    @Value("${AD1_WIDTH_B}")
    private String AD1_WIDTH_B;
    @Value("${AD1_HEIGHT}")
    private String AD1_HEIGHT;
    @Value("${AD1_HEIGHT_B}")
    private String AD1_HEIGHT_B;
    @Autowired
    private TbContentService tbContentService;

    @RequestMapping("/index")
    public String index(Model model) {
        List<TbContent> tbContents =
                tbContentService.selectContentByCategoryId(AD1_CATEGORY_ID);
        ArrayList<Ad1Node> Ad1Node = new ArrayList<>();
        for (TbContent tbContent : tbContents) {
            Ad1Node node = new Ad1Node();
            node.setAlt(tbContent.getTitle());
            node.setHref(tbContent.getUrl());
            node.setSrc(tbContent.getPic());
            node.setStrB(tbContent.getPic2());

            node.setWidth(AD1_WIDTH);
            node.setWidthB(AD1_WIDTH_B);
            node.setHeight(AD1_HEIGHT);
            node.setHeightB(AD1_HEIGHT_B);
            Ad1Node.add(node);
        }
        String jsonString = JSON.toJSONString(Ad1Node);
        model.addAttribute("ad1",jsonString);
        return "index";
    }
}
