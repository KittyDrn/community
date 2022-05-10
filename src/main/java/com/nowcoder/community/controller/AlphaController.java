package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.Name;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Deng_Ruonan
 * @create 2022-05-04-15:51
 */
@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "Hello Spring Boot.";
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return alphaService.find();
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        //获取请求数据
        System.out.println(request.getMethod());//获取请求方式
        System.out.println(request.getServletPath());//请求路径
        Enumeration<String> enumeration = request.getHeaderNames();//迭代器对象 得到所有请求行的key
        while(enumeration.hasMoreElements()){//老迭代器用while访问 hasMoreElements 功能和iterator一样
            String name = enumeration.nextElement();//从中获取请求行的名字
            String value = request.getHeader(name);//name对应的value
            System.out.println(name + ":" + value);
        }
        System.out.println(request.getParameter("code"));

        //response给浏览器返回响应数据
        response.setContentType("text/html;charset=utf-8");//返回数据的类型 支持中文
        try(
            PrintWriter writer = response.getWriter();
        ) {
            writer.write("<h1>牛客网</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //GET请求
    // /students?current=1&limit=20
    @RequestMapping(path = "/students",method = RequestMethod.GET)//path声明请求路径  method声明请求方式为get
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current",required = false,defaultValue = "1") int current,
            @RequestParam(name = "limit",required = false,defaultValue = "10") int limit) {
        //name对应为current的参数，required=false意为也可以不传这个参数进来，默认值是1
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }

    // /student/123
    @RequestMapping(path= "/student/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id")int id){
        //PathVariable("id")注解会帮我们从路径中得到id变量 赋值给参数id
        System.out.println(id);
        return "a student";
    }

    @RequestMapping(path = "/student",method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name,int age){//名称对应会自动传过来
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    //响应HTML数据
    @RequestMapping(path = "/teacher",method = RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("name","张三");
        mav.addObject("age","30");
        mav.setViewName("/demo/view");
        return mav;
    }

    //相对更简洁的响应HTML方式
    @RequestMapping(path = "/school",method = RequestMethod.GET)
    public String getSchool(Model model){
        model.addAttribute("name","北京大学");
        model.addAttribute("age","80");
        return "/demo/view";
    }

    //响应JSON数据(异步请求)
    //Java对象 --> JSON字符串 --> JS对象
    @RequestMapping(path = "/emp",method = RequestMethod.GET)
    @ResponseBody//返回JSON对象需要使用注解@ResponseBody 否则默认返回html
    public Map<String,Object> getMap(){
        Map<String,Object> emp = new HashMap<>();
        emp.put("name","张三");
        emp.put("age",23);
        emp.put("salary",8000);
        return emp;
    }

    //多个相似数据的结构 集合形式的JSON字符串
    @RequestMapping(path = "/emps",method = RequestMethod.GET)
    @ResponseBody//返回JSON对象需要使用注解@ResponseBody 否则默认返回html
    public List<Map<String,Object>> getMaps(){
        List<Map<String,Object>> list = new ArrayList<>();

        Map<String,Object> emp = new HashMap<>();
        emp.put("name","张三");
        emp.put("age",23);
        emp.put("salary",8000);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name","李四");
        emp.put("age",24);
        emp.put("salary",9000);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name","王五");
        emp.put("age",25);
        emp.put("salary",10000);
        list.add(emp);
        return list;
    }
}
