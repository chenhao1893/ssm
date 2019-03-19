package com.itheima.domain;


import com.itheima.utils.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class SysLog {

    private String id;
    private Date visitTime;//访问时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private String visitTimeStr;
    private String username;//访问用户名
    private String ip;//访问的ip地址
    private String url;//访问资源url
    private Long executionTime;//访问时长
    private String method;//访问的方法名

    public String getVisitTimeStr() {
        return DateUtils.dateToString(visitTime, "yyyy-MM-dd HH:mm");
    }

    public void setVisitTimeStr(String visitTimeStr) {
        this.visitTimeStr = visitTimeStr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Long executionTime) {
        this.executionTime = executionTime;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
