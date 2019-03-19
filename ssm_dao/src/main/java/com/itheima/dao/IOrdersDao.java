package com.itheima.dao;

import com.itheima.domain.Orders;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;

import java.util.List;

public interface IOrdersDao {
    /**
     * 查询所有
     * @return
     */
    List<Orders> findAll();

    /**
     * 根据id查询订单信息
     * @param id
     * @return
     */
    @Results(
            @Result()
    )
    Orders findOrdersById(String id);
}
