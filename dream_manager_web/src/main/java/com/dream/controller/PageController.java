package com.dream.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    /**
     *
     * @return
     */
    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page, String redirect, Model model){
        //redirect并不是一定有参数 所以判断下
        if(StringUtils.isNoneBlank(redirect)){
            model.addAttribute("redirect",redirect);
        }
        return page;
    }


    @RequestMapping("/")
    public String index(){
        return "index";
    }

}
