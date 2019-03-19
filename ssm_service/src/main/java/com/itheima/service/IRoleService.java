package com.itheima.service;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;

import java.util.List;

public interface IRoleService {

    List<Role> findAll(Integer page, Integer pageSize);

    void add(Role role);


    Role findById(String id);

    List<Permission> findAdditablePermission(String id);

    void addPermission(String roleId, String[] ids);
}
