package com.dhwooden.ep.controller;

import com.dhwooden.ep.modal.DhTest;
import com.dhwooden.ep.service.DhTestService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wk
 * @since 2018-12-04
 */
@Controller
@RequestMapping("/admin/exam")
public class DhTestController {
    @Autowired
    private DhTestService dhTestService;

    @RequestMapping("/exam")
    @ResponseBody
    public String exam(String id,String testanswer, String testcontent,Integer offset, Integer limit ,Model model,HttpServletResponse response,HttpServletRequest request) throws IOException {
        DhTest test=dhTestService.selectById(id);
        if(test.getAnswer().equals(testanswer)){
            test.setRight(test.getRight()+1);
            dhTestService.updateById(test);
            return "right+1";
        }else{
            test.setWrong(test.getWrong()+1);
            return "wrong+1";
        }

    }

    @RequestMapping("/add")
    public String add(String id,String content, String answer,Integer offset, Integer limit ,Model model,HttpServletResponse response,HttpServletRequest request) throws IOException {
        DhTest test=new DhTest();
        test.setAnswer(answer);
        test.setProblem(content);
        test.setRight(0);
        test.setWrong(0);
        dhTestService.insert(test);
        return "/admin/test/add";

    }
    @RequestMapping("/addIndex")
    public String addIndex(String id,String testanswer, String testcontent,Integer offset, Integer limit ,Model model,HttpServletResponse response,HttpServletRequest request) throws IOException {
        return "/admin/test/add";

    }


    @RequestMapping("/index")
    public String index(Integer pageNumber,Integer offset, Integer limit ,Model model,HttpServletResponse response,HttpServletRequest request) throws IOException {
        return "/admin/test/test";

    }

    @RequestMapping("/list")
    @ResponseBody
    public DhTest add(Integer pageNumber,Integer offset, Integer limit ,Model model,HttpServletResponse response,HttpServletRequest request) throws IOException {
        Map map=new HashMap<>();
        List<DhTest> list=dhTestService.selectByMap(map);
        Random r1 = new Random();
        Integer ran=r1.nextInt(list.size()-1);
        DhTest test=list.get(ran);
        return test;

    }
	
}
