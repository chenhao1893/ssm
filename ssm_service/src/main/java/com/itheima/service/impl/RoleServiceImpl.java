package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.IRoleDao;
import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<Role> findAll(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Role> roleList = roleDao.findAll();
        return roleList;
    }

    @Override
    public void add(Role role) {
        roleDao.add(role);
    }

    @Override
    public Role findById(String id) {
        Role role = roleDao.findById(id);
        return role;
    }

    /**
     * 根据角色查找可以添加的权限信息
     *
     * @param id
     * @return
     */
    @Override
    public List<Permission> findAdditablePermission(String id) {
        List<Permission> permissions = roleDao.findAdditablePermission(id);
        return permissions;
    }

    /**
     * 给角色添加权限
     *
     * @param roleId
     * @param ids
     */
    @Override
    public void addPermission(String roleId, String[] ids) {
        if (roleId.length() > 0) {
            for (String id : ids) {
                roleDao.addPermission(roleId, id);
            }
        }
    }
}
