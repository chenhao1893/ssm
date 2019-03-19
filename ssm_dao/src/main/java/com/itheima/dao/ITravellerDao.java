package com.itheima.dao;

import com.itheima.domain.Traveller;

import java.util.List;

public interface ITravellerDao {
    /**
     * 根据订单id查询游客信息
     * @param id
     * @return
     */
    public List<Traveller> findTravellersByOrdersId(String id);
}
