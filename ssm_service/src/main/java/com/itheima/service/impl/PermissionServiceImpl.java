package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.IPermissionDao;
import com.itheima.domain.Permission;
import com.itheima.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public List<Permission> findAll(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Permission> permissions = permissionDao.findAll();
        return permissions;
    }

    @Override
    public void add(Permission permission) {
        permissionDao.add(permission);
    }

    @Override
    public Permission findById(String id) {
        Permission permission = permissionDao.findById(id);
        return permission;
    }
}
