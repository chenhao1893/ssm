package com.itheima.dao;

import com.itheima.domain.SysLog;

import java.util.List;

public interface ILoggerDao {

    /**
     * 保存日志
     * @param sysLog
     */
    void save(SysLog sysLog);

    /**
     * 查询所有
     * @return
     */
    List<SysLog> findAll();
}
