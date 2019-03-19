package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Orders;
import com.itheima.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private IOrdersService ordersService;

    @RequestMapping("findAll")
    public ModelAndView findAll(@RequestParam(required = true, value = "page", defaultValue = "1") Integer page, @RequestParam(name = "pageSize", required = true, defaultValue = "5") Integer pageSize, ModelAndView mv) {
        List<Orders> ordersList = ordersService.findAll(page, pageSize);
        PageInfo pageinfo = new PageInfo(ordersList);
        mv.addObject("pageinfo", pageinfo);
        mv.setViewName("orders-list");
        return mv;
    }


    @RequestMapping("findOrdersById")
    public ModelAndView findOrdersById(ModelAndView mv, @RequestParam(required = true, name = "id", defaultValue = "1") String id) {
        Orders orders = ordersService.findOrdersById(id);
        mv.addObject("orders", orders);
        mv.setViewName("orders-show");
        return mv;
    }
}
