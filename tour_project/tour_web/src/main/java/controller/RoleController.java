package controller;

import domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping("/findByRoleId")
    public ModelAndView findByRoleId(String id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("role",roleService.findByRoleId(id));
        modelAndView.setViewName("role-show");
        return modelAndView;
    }

    @RequestMapping("/findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermission(String id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("role",roleService.findByRoleId(id));
        modelAndView.addObject("permissionList",roleService.findOtherPermission(id));
        modelAndView.setViewName("role-permission-add");
        return modelAndView;
    }

    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true)String roleId,@RequestParam(name = "ids",required = true)String[] permissionIds){
        roleService.addPermissionToRole(roleId,permissionIds);
        return "redirect:findAll";
    }
}
