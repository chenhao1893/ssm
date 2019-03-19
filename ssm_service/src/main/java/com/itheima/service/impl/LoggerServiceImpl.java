package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.ILoggerDao;
import com.itheima.domain.SysLog;
import com.itheima.service.ILoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoggerServiceImpl implements ILoggerService {

    @Autowired
    private ILoggerDao loggerDao ;

    /**
     * 保存日志
     * @param sysLog
     */
    @Override
    public void save(SysLog sysLog) {
        loggerDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        return loggerDao.findAll();
    }
}
