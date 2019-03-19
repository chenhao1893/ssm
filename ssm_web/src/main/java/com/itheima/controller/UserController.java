package com.itheima.controller;


import com.github.pagehelper.PageInfo;
import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import com.itheima.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    /**
     * 查询所有
     * @param mv
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(ModelAndView mv, @RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "pageSize", required = true, defaultValue = "3") Integer pageSize) {
        //创建一个pageInfo对象

        List<User> users = userService.findAll(page, pageSize);
        PageInfo pageInfo = new PageInfo(users);
        mv.addObject("pageinfo", pageInfo);
        mv.setViewName("user-list");
        return mv;
    }

    /**
     * 添加用户
     *
     * @param userInfo
     * @return
     */
    @RequestMapping("add")
    public String add(UserInfo userInfo) {
        userService.add(userInfo);
        return "redirect:findAll";
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @RequestMapping("findById")
    public ModelAndView findById(ModelAndView mv , String id) {
        UserInfo user = userService.findById(id);
        mv.addObject("user", user);
        mv.setViewName("user-show");
        return mv;
    }

    /**
     * 添加用户权限
     *
     * @param id
     * @return 用户对象和可以添加的角色集合
     */
    @RequestMapping("findAdditableRoles")
    public ModelAndView findAdditableRoles(String id, ModelAndView mv) {
        //查询用户可以添加的角色集合
        List<Role> roleList = userService.findAdditableRoles(id);
        mv.addObject("userId", id);
        mv.addObject("roleList", roleList);
        mv.setViewName("user-role-add");
        return mv;
    }

    /**
     * 给用户添加角色
     *
     * @param ids    角色的id集合
     * @param userId 用户id
     * @return
     */
    @RequestMapping("addRole")
    public String addRole(String[] ids, String userId) {
        userService.addRole(ids, userId);
        return "redirect:findAll";
    }
}
