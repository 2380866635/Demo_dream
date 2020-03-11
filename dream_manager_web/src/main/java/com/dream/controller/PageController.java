package com.dream.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    /**
     *
     * @return
     */
    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page){
        return page;
    }
    @RequestMapping("/")
    public String index(){
        return "index";
    }

}
