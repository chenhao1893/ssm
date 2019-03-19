package com.itheima.controller;


import com.github.pagehelper.PageInfo;
import com.itheima.domain.Permission;
import com.itheima.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService ;

    @RequestMapping("/findAll")
    public ModelAndView findAll(ModelAndView mv, @RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "pageSize", required = true, defaultValue = "3") Integer pageSize){
        List<Permission> permissions = permissionService.findAll(page,pageSize);
        PageInfo pageInfo = new PageInfo(permissions);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("permission-list");
        return mv;
    }

    @RequestMapping("/add")
    public String add(Permission permission){
        permissionService.add(permission);
        return "redirect:findAll";
    }

   /* @RequestMapping("/findById")
    public String findById(ModelAndView mv,String id){
        Permission permission = permissionService.findById(id);

    }*/
}
