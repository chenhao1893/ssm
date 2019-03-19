package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.IUserDao;
import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import com.itheima.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Autowired(required = false)
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userDao.findByUsername(username);
        //创建UserDetails对象返回给spring_security框架进行验证
        return new User(userInfo.getUsername(), userInfo.getPassword(), userInfo.getStatus() == 1, true, true, true, getAuthority(userInfo.getRoles()));

    }

    /**
     * 获取用户权限集合
     *
     * @param roles
     * @return
     */
    private List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
        if (roles.size() > 0) {
            for (Role role : roles) {
                //System.out.println("role = " + role);
                list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
                //System.out.println("结果：ROLE_" + role.getRoleName());
            }
        }
        return list;
    }

    /**
     * 查询所有用户信息
     *
     * @return
     */
    @Override
    public List<User> findAll(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<User> users = userDao.findAll();
        return users;
    }

    /**
     * 添加用户，并使用加密类将密码进行加密
     *
     * @param userInfo
     */
    @Override
    public void add(UserInfo userInfo) {
        //使用加密类对用户密码进行加密
        if (passwordEncoder != null) {
            userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        }
        userDao.add(userInfo);
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @Override
    public UserInfo findById(String id) {
        UserInfo userInfo = userDao.findById(id);
        return userInfo;
    }

    /**
     * 根据用户id查询可以添加的角色集合
     * @param id
     * @return
     */
    @Override
    public List<Role> findAdditableRoles(String id) {
        List<Role> roleList = userDao.findAdditableRoles(id);
        return roleList;
    }


    /**
     * 遍历角色id集合ids 给用户添加角色，实际上就是在中间表中插入数据
     * @param ids
     * @param userId
     */
    @Override
    public void addRole(String[] ids, String userId) {
        for (String id : ids) {
            userDao.addRole(id,userId);
        }

    }
}
