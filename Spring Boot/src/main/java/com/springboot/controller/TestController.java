/**
 * FileName: TestController
 * Author:   嘉平十七
 * Date:     2020/7/22 17:18
 * Description:
 * notes：
 */
package com.springboot.controller;


import com.springboot.domain.Classmate;
import com.springboot.service.ClassmateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;
import java.util.List;

@Controller
public class TestController {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ClassmateService classmateService;

    @GetMapping("hello")
    @ResponseBody
    public String hello(){
//        System.out.println(dataSource);
        return "Hello";
    }

    @RequestMapping("/test")
    public String thymeleaf(Model model){
        model.addAttribute("msg","测试Thymeleaf模板");
        //这里不跳转的原因是上面使用了@RestController注解，而这个注解是相当于@Controller和@ResponseBody两个注解
        return "test";
    }

    @GetMapping("/queryAll")
    public List<Classmate> queryAll(){
        List<Classmate> classmates = classmateService.queryAll();
        return classmates;
    }

}