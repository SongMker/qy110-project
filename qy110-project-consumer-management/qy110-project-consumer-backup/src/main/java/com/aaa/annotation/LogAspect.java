package com.aaa.annotation;

import com.aaa.model.LoginLog;
import com.aaa.model.User;
import com.aaa.service.IProjectService;
import com.aaa.utils.AddressUtils;
import com.aaa.utils.IPUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.util.DateUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static com.aaa.staticproperties.TimeFormatProperties.TIME_FORMAT;

/**
 * @Author: Joy
 * @Date: 2020/7/15 15:24
 * @Description: AOP
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Autowired
    private IProjectService projectService;

    @Pointcut("@annotation(com.aaa.annotation.LoginAnnotation)")
    public void pointcut() {
        //TODO nothing to do
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws ClassNotFoundException {
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable th) {
            th.printStackTrace();
        }

        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest();
//        String ipAddr = IPUtils.getIpAddr(request);
        String ipAddr = "222.137.210.39";
        Map<String, Object> addressMap = AddressUtils.getAddresses(ipAddr, "UTF-8");
        System.out.println("地址" + addressMap);

        LoginLog loginLog = new LoginLog();
        //todo IP为127.0.0.1获取不到地址 报错  IP已写死
        loginLog.setIp(ipAddr);
        loginLog.setLocation(addressMap.get("province") + "|" + addressMap.get("city"));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_FORMAT);
        String dateString = simpleDateFormat.format(new Date());
        loginLog.setLoginTime(dateString);

        Object[] args = proceedingJoinPoint.getArgs();
        User user = (User) args[0];
        loginLog.setUsername(user.getUsername());

        String tarClassName = proceedingJoinPoint.getTarget().getClass().getName();
        String tarMethodName = proceedingJoinPoint.getSignature().getName();
        Class tarClass = Class.forName(tarClassName);
        Method[] methods = tarClass.getMethods();
        String operationType = "";
        String operationName = "";

        for (Method method : methods) {
            String methodName = method.getName();
            if (tarMethodName.equals(methodName)) {
                Class[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == args.length) {
                    operationType = method.getAnnotation(LoginAnnotation.class).operationType();
                    operationName = method.getAnnotation(LoginAnnotation.class).operationName();
                }
            }
        }

        loginLog.setOperationName(operationName);
        loginLog.setOperationType(operationType);

        projectService.addLoginLog(loginLog);

        return result;
    }
}
