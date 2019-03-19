package com.itheima.dao;

import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface IUserDao {

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    UserInfo findByUsername(String username);

    /**
     * 查询所有用户
     * @return
     */
    List<User> findAll();

    /**
     * 添加用户
     * @param userInfo
     */
    void add(UserInfo userInfo);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    UserInfo findById(String id);

    /**
     * 根据角色id查询用户
     * @param id
     * @return
     */
    List<UserInfo> findByRoleId(String id);

    /**
     * 根据用户id查询可以添加的角色
     * @param id
     * @return
     */
    List<Role> findAdditableRoles(String id);

    /**
     * 给用户添加一个角色
     * @param id 角色id
     * @param userId 用户id
     *               当使用mybatis框架传入多个参数时，默认的key为param1，param2...
     *               可以使用@Param("")指定传递参数的key
     */
    void addRole(@Param("id") String id, @Param("userId") String userId);
}
