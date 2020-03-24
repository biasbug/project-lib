package controller;

import domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.UserService;
import service.UserServiceImpl;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userList",userService.findAll());
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

    @RequestMapping("/save")
    public String save(UserInfo userInfo){
        userService.save(userInfo);
        return "redirect:findAll";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(String id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user",userService.findById(id));
        modelAndView.setViewName("user-show");
        return modelAndView;
    }

    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(String id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user",userService.findById(id));
        modelAndView.addObject("roleList",userService.findOtherRole(id));
        modelAndView.setViewName("user-role-add");
        return modelAndView;
    }

    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(@RequestParam(name = "userId",required = true) String userId,@RequestParam(name = "ids",required = true)String[] roleIds){
        userService.addRoleToUser(userId,roleIds);
        return "redirect:findAll";
    }
}
