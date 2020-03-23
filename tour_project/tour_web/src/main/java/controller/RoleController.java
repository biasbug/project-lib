package controller;

import domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("roleList",roleService.findAll());
        modelAndView.setViewName("role-list");
        return modelAndView;
    }

    @RequestMapping("/save")
    public String save(Role role) throws Exception{
        roleService.save(role);
        return "redirect:findAll";
    };
}
