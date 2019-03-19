package com.itheima.service;

import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IUserService extends UserDetailsService {

    List<User> findAll(Integer page,Integer pageSize);

    void add(UserInfo userInfo);

    UserInfo findById(String id);

    /**
     * 根据用户id查询可以添加的角色集合
     * @param id
     * @return
     */
    List<Role> findAdditableRoles(String id);

    /**
     * 给用户添加角色
     * @param ids
     * @param userId
     */
    void addRole(String[] ids, String userId);
}
