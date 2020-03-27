package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.SysLogService;

@Controller
@RequestMapping("/sysLog")
public class SysLogDao {

    @Autowired
    SysLogService sysLogService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("sysLogs",sysLogService.findAll());
        modelAndView.setViewName("syslog-list");
        return modelAndView;
    }
}
