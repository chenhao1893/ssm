package com.itheima.dao;

import com.itheima.domain.Permission;

import java.util.List;

public interface IPermissionDao {
    List<Permission> findAll();

    void add(Permission permission);

    List<Permission> findPermissionsByRoleId(String id);

    Permission findById(String id);
}
