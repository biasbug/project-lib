package com.xhd.dynamic_privilege.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @RequestMapping(value = { "", "/index" }, method = RequestMethod.GET)
    public String home() {
        return "index";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String userPage() {
        return "user-page";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage() {
        return "admin-page";
    }

    @RequestMapping("/403")
    public String forbidden() {
        return "403";
    }
}
