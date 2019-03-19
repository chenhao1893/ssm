package com.itheima.dao;

import com.itheima.domain.Member;

public interface IMemberDao {
    /**
     * 根据id查询会员信息
     * @param id
     * @return
     */
    public Member findById(String id);
}
