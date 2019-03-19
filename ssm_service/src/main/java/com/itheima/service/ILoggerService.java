package com.itheima.service;


import com.itheima.domain.SysLog;

import java.util.List;

/**
 * 日志service层
 */
public interface ILoggerService {

    /**
     * 保存日志
     * @param sysLog
     */
    public void save(SysLog sysLog);

    /**
     * 分页查询日志
     * @param page
     * @param pageSize
     * @return
     */
    List<SysLog> findAll(Integer page, Integer pageSize);
}
