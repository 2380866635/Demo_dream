package com.dream.protal.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class IndexController {

   /* @Value("${AD1_CATEGORY_ID}")
    private Long AD1_CATEGORY_ID;
    @Value("${AD1_WIDTH}")
    private String AD1_WIDTH;
    @Value("${AD1_WIDTH_B}")
    private String AD1_WIDTH_B;
    @Value("${AD1_HEIGHT}")
    private String AD1_HEIGHT;
    @Value("${AD1_HEIGHT_B}")
    private String AD1_HEIGHT_B;*/


    @RequestMapping("/index")
    public String index(Model model) {
      /*  List<TbContent> tbContents =
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
        model.addAttribute(jsonString);*/
        return "index";
    }
}
