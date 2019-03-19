package com.itheima.controller;


import com.github.pagehelper.PageInfo;
import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(ModelAndView mv, @RequestParam(required = true, value = "page", defaultValue = "1") Integer page, @RequestParam(name = "pageSize", required = true, defaultValue = "5") Integer pageSize) {
        List<Role> roleList = roleService.findAll(page, pageSize);
        PageInfo pageInfo = new PageInfo(roleList);
        mv.addObject("pageinfo", pageInfo);
        mv.setViewName("role-list");

        return mv;
    }

    @RequestMapping("/add")
    public String add(Role role) {
        roleService.add(role);
        return "redirect:findAll";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(ModelAndView mv, String roleId) {
        Role role = roleService.findById(roleId);
        mv.addObject("role", role);
        mv.setViewName("role-show");
        return mv;
    }


    @RequestMapping("/findAdditablePermission")
    public ModelAndView findAdditablePermission(ModelAndView mv,String roleId) {
        //查询该角色可以添加的权限
        List<Permission> permissions = roleService.findAdditablePermission(roleId);
        mv.addObject("roleId", roleId);
        mv.addObject("permissions", permissions);
        mv.setViewName("role-permission-add");
        return mv;
    }

    @RequestMapping("/addPermission")
    public String addPermission(String roleId, String[] ids) {
        roleService.addPermission(roleId, ids);
        return "redirect:findAll";
    }


}
