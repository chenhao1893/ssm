package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.IOrdersDao;
import com.itheima.domain.Orders;
import com.itheima.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IOrdersServiceImpl implements IOrdersService{
    @Autowired
    private IOrdersDao ordersDao;

    @Override
    public List<Orders> findAll(Integer page,Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Orders> ordersList = ordersDao.findAll();
        return ordersList;
    }

    @Override
    public Orders findOrdersById(String id) {
        Orders orders = ordersDao.findOrdersById(id);
        return orders;
    }
}
