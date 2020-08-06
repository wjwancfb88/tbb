package com.dhwooden.ep.controller;

import com.dhwooden.ep.service.DhLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Lucare.Feng on 2017/2/28.
 */
@Controller
@RequestMapping("/admin/login")
public class LoginController {

    @Autowired
    private DhLoginService dhLoginService;

    @RequestMapping("/index")
    public String index(Model model) {
        System.out.println("this is frame");
        return "/admin/login/index";
    }

    @RequestMapping("/sxy")
    public String sxy(Model model) {
        System.out.println("this is error");
        return "/admin/sxy/index";
    }

    @RequestMapping("/jsfc")
    public String jsfc(Model model) {
       // System.out.println("this is error");
        return "/admin/sxy/jsfc";
    }
    @RequestMapping("/info")
    public String info(Model model) {
      //  System.out.println("this is error");
        return "/admin/sxy/info";
    }


    @RequestMapping("/app")
        public String error(Model model) {
        System.out.println("this is error");
        return "app";
    }
    @RequestMapping("/logout")
    public String logout() {
        return "logout" ;
    }
}
