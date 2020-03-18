package controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import domain.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.OrdersService;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    OrdersService ordersService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView mav = new ModelAndView();
        List<Orders> ordersList = ordersService.findAll();
        mav.addObject("ordersList",ordersList);
        mav.setViewName("orders-list");
        return mav;
    }

    @RequestMapping("/findByPage")
    public ModelAndView findByPage(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,@RequestParam(name = "pageSiez",required = true,defaultValue = "4")Integer pageSize){
        ModelAndView mav = new ModelAndView();
        Page<Object> startPage = PageHelper.startPage(page, pageSize);
        List<Orders> ordersList = ordersService.findByPage(page,pageSize);
        PageInfo<Orders> pageInfo = new PageInfo(startPage.getResult());
        pageInfo.setList(ordersList);
        mav.addObject("pageInfo",pageInfo);
        mav.setViewName("orders-list-page");
        return mav;
    }
}
