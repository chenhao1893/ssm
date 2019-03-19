package com.itheima.dao;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IRoleDao {

    /**
     * 根据用户id查询角色
     *
     * @param id
     * @return
     */
    List<Role> findByUserInfoId(String id);

    List<Role> findAll();


    void add(Role role);

    Role findById(String id);

    /**
     * 根据角色id查找可以添加的权限信息
     *
     * @param id
     * @return
     */
    List<Permission> findAdditablePermission(String id);

    /**
     * 根据权限id查询角色
     *
     * @param id
     * @return
     */
    List<Role> findByPermissionId(String id);

    void addPermission(@Param("roleId") String roleId, @Param("id") String id);
}
