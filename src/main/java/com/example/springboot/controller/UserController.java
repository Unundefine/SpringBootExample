package com.example.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.example.springboot.model.User;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "redirect:/index.html";
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getUser(@PathVariable int id){

        User user = userService.getUserById(id);
        return JSON.toJSONString(user);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public String update(User user){
        int update = userService.update(user.getId(), user);
        return JSON.toJSONString(update);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String add(User user){
        int update = userService.update(user.getId(), user);
        return JSON.toJSONString(update);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable int id){
        int update = userService.delete(id);
        return JSON.toJSONString(update);
    }

}
