package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Product;
import com.itheima.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @RolesAllowed("USER")
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(required = true, value = "page", defaultValue = "1") Integer page, @RequestParam(name = "pageSize", required = true, defaultValue = "5") Integer pageSize, ModelAndView mv) {
        List<Product> products = productService.findAll(page, pageSize);
        //使用pageInfo对象将分页信息传递到页面:
        PageInfo pageinfo = new PageInfo(products);
        mv.addObject("pageinfo", pageinfo);
        mv.setViewName("product-list");
        return mv;
    }

    @RolesAllowed("ADMIN")
    @RequestMapping("/save")
    public String save(Product product) {
        productService.save(product);
        return "redirect:findAll";
    }
    @RolesAllowed("ADMIN")
    @RequestMapping("/delete")
    public String delete(String[] ids) {
        productService.delete(ids);
        return "redirect:findAll";
    }

    @RolesAllowed("ADMIN")
    @RequestMapping("/openProduct")
    public String openProduct(String[] ids){
        productService.openProduct(ids);
        return "redirect:findAll";
    }

    @RolesAllowed("ADMIN")
    @RequestMapping("/closeProduct")
    public String closeProduct(String[] ids){
        productService.closeProduct(ids);
        return "redirect:findAll";
    }

}
