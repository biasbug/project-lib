package controller;

import domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.ProductService;

import javax.annotation.security.RolesAllowed;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;


    @RequestMapping("/findAll")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ModelAndView findAll(){
        ModelAndView mav = new ModelAndView();
        try {
            mav.addObject("productList",productService.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mav.setViewName("product-list");
        return mav;
    }

    @RequestMapping("save")
    public String save(Product product){
        productService.save(product);
        return "redirect:findAll";
    }
}
