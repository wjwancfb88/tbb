package com.dhwooden.ep.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Lucare.Feng on 2017/2/28.
 */
@Controller
@RequestMapping("/common")
public class MainController {

    @RequestMapping("/frame")
    public String index(Model model) {
        return "/common/frame";
    }

    @RequestMapping("/index")
    public String loginIndex(String deptId) {

        return "/admin/login/content";
    }
    @RequestMapping("/test")
    public String xiaocanting(String deptId) {

        return "/admin/xiaocanting/yy";
    }

}
