package com.itheima.aop;


import com.itheima.domain.SysLog;
import com.itheima.service.ILoggerService;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 日志切面类
 * （获取sysLog属性时可以不用反射，这里演示反射获取方法和URI）
 */
@Component
public class LogAop {

    //定义request对象
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ILoggerService loggerService;

    //访问开始时间
    private Date visitTime;

    //访问的类对象
    private Class visitClazz;

    //访问的方法对象
    private Method visitMethod;

    /**
     * 前置通知
     * 给成员变量访问开始时间赋值，获取访问类对象和访问方法对象
     *
     * @param joinPoint
     */

    public void doBefore(JoinPoint joinPoint) throws NoSuchMethodException {
        //获取访问方法的类对象和方法对象
        //获取访问时间
        visitTime = new Date();
        //获取访问的类对象
        visitClazz = joinPoint.getTarget().getClass();
        //获取访问的方法名称
        String methodName = joinPoint.getSignature().getName();
        //获取访问的方法
        //获取访问方法的参数，判断有无参数再获取方法对象
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) {
            //获取无参的方法
            visitMethod = visitClazz.getMethod(methodName);
        } else {
            //获取有参的方法
            //首先获取参数的字节码数组
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                //如果访问的方法参数中有Model对象就直接给一个Model的字节码，
                // 因为实际方法中的Model传入的是一个Map集合
                if (args[i] instanceof Model) {
                    classArgs[i] = Model.class;
                    continue;
                }
                classArgs[i] = args[i].getClass();
            }
            visitMethod = visitClazz.getMethod(methodName, classArgs);
        }
    }


    /**
     * 后置通知
     * 获取创建日志，设置属性，将日志对象存入数据可
     *
     * @param joinPoint
     */
    public void doAfter(JoinPoint joinPoint) {
        //获取访问时长
        Long executionTime = new Date().getTime() - visitTime.getTime();

        //获取访问用户名
        SecurityContext context = SecurityContextHolder.getContext();
        User user = (User) context.getAuthentication().getPrincipal();//通过上下文获取访问的用户对象
        String username = user.getUsername();

        //通过request对象获取ip地址
        String ip = "";
        if (request != null) {
            ip = request.getRemoteAddr();
        }

        //通过反射获取访问资源路径，也可以使用request对象request.getRequestURI()获取uri
        //定义url
        String url = "";
        if (visitClazz != null && visitMethod != null && visitClazz != LogAop.class) {//过滤掉日志切面（自己）
            //获取方法上的RequestMapping注解对象
            RequestMapping classAnnotation = (RequestMapping) visitClazz.getAnnotation(RequestMapping.class);
            if (classAnnotation != null) {
                url += classAnnotation.value()[0];
                //获取方法上的RequestMapping注解对象
                RequestMapping methodAnnotation = (RequestMapping) visitMethod.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    url += methodAnnotation.value()[0];

                    //当访问的类和方法还有注解都不为null时就可以封装sysLog对象了
                    SysLog sysLog = new SysLog();
                    sysLog.setIp(ip);
                    //设置访问的类名加方法名，有二种方法
                    //sysLog.setMethod("类名"+visitClazz.getName()+"方法名"+visitMethod.getName());
                    //截取字符串去掉返回值类型（第二种方法）用空格截取
                    sysLog.setMethod(joinPoint.getSignature().toString().split(" ")[1]);
                    sysLog.setExecutionTime(executionTime);
                    sysLog.setUrl(url);
                    sysLog.setUsername(username);
                    sysLog.setVisitTime(visitTime);
                    //调用service层将数据写入数据库
                    loggerService.save(sysLog);
                }
            }
        }
    }


}
