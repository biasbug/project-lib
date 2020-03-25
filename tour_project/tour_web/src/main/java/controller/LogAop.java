package controller;

import domain.SysLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import service.SysLogService;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SysLogService sysLogService;
    private Date visitTime;//访问时间

    //配置一个切入点
    @Pointcut("execution(* controller.*.*(..))")
    public void pointcut(){};

    //配置前置通知
    @Before("pointcut()")
    public void before(JoinPoint joinPoint) throws NoSuchMethodException {
        //获取访问时间
        visitTime = new Date();
    }

    //配置后置通知,除访问时间，其他操作都放在后置
    @After("execution(* controller.*.*(..))")
    public void afterReturning(JoinPoint joinPoint){
        //获取执行的时间
        Long executionTime = new Date().getTime() - visitTime.getTime();
        //获取访问用户名
        String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        //获取访问的类路径
        Class excutionClass = joinPoint.getTarget().getClass();
        //获取访问的方法
        String methodName = joinPoint.getSignature().getName();
        //获取访问的IP
        String ip = request.getRemoteAddr();
        //获取访问的url路径
        String url = request.getRequestURL().toString();

        //将上述的信息存储到SysLog，并存储到数据库
        SysLog sysLog = new SysLog();
        sysLog.setExecutionTime(executionTime);
        sysLog.setIp(ip);
        sysLog.setMethod(excutionClass.getName()+"."+methodName);
        sysLog.setUrl(url);
        sysLog.setUsername(username);
        sysLog.setVisitTime(visitTime);
        sysLogService.save(sysLog);
    }
}
