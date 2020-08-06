package com.dhwooden.ep.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.dhwooden.ep.modal.DhEntry;
import com.dhwooden.ep.modal.DhMailbox;
import com.dhwooden.ep.service.DhMailboxService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wk
 * @since 2018-08-27
 */
@Controller
@RequestMapping("/admin/mailbox/")
public class DhMailboxController {

    @Autowired
    private DhMailboxService dhMailboxService;
    @RequestMapping("/index")
    public String index(Model model,HttpServletResponse response) throws IOException {
        return "/admin/login/content";

    }
    @RequestMapping("/mail")
    public String wmail(Model model,HttpServletResponse response) throws IOException {
        return "/admin/mailbox/mailbox";

    }

    @RequestMapping("/datalist")
    @ResponseBody
    public Page<DhMailbox> datalist(Model model,HttpServletResponse response) throws IOException {
        Map map=new HashMap<>();
        return dhMailboxService.selectByPage(new Page<DhMailbox>(0, 12),map);


    }
    @RequiresPermissions("mail")
    @RequestMapping("/list")
    public String  list(Model model,HttpServletResponse response) throws IOException {
        return "/admin/mailbox/mailList";


    }

    @RequestMapping("/add")
    public String add(String receiveName,String sendName,String title,String content,String overt,String anonymous,  Model model,HttpServletResponse response,DhMailbox mailbox) throws IOException {
        mailbox.setReceiveName(receiveName);
        mailbox.setSendName(sendName);
        mailbox.setTitle(title);
        mailbox.setContent(content);
        mailbox.setOvert(overt);
        LocalDate date=LocalDate.now();
        mailbox.setAnonymous(anonymous);
        mailbox.setSendTime(date);
        dhMailboxService.insert(mailbox);
        return "/admin/mailbox/mailbox";
    }

	
}
