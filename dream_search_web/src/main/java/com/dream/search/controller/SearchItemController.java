package com.dream.search.controller;

import com.dream.common.pojo.SearchResult;
import com.dream.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;

@Controller
public class SearchItemController {
    @Autowired
    private SearchItemService searchItemService;
    @Value("${ITEM_ROWS}")
    private Integer ITEM_ROWS;
    @RequestMapping("/search")
    public String search(@RequestParam("q") String queryString,
                         @RequestParam(defaultValue = "1")Integer page, Model model) throws Exception {
        //发出该请求的是门户系统  请求方式为get方式？q=搜索内容
        //基本上搜索内容中必有中文  但是web解决得只是post方式 中文乱码
        //解决Get方式乱码把字符串进行转码


        queryString = new String(queryString.getBytes("ISO-8859-1"), "UTF-8");
        SearchResult search = searchItemService.search(queryString, page, ITEM_ROWS);
        model.addAttribute("query",queryString);
        model.addAttribute("totalPages",search.getTotalPages());
        model.addAttribute("itemList",search.getItemList());
        model.addAttribute("page",page);

        return  "search";
    }

}
