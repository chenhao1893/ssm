package com.itheima.service;

import com.itheima.domain.Permission;

import java.util.List;

public interface IPermissionService {

    List<Permission> findAll(Integer page, Integer pageSize);

    void add(Permission permission);

    Permission findById(String id);
}
