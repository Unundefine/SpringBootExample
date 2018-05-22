package com.example.springboot.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;

@Aspect
@Order(1)
@Component
public class WebLogAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.example.springboot.controller.*.*(..)) && @annotation(org.springframework.web.bind.annotation.ResponseBody)")
    public void webLog() {
    }

    @Around("webLog()")
    public Object around(JoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch(joinPoint.getTarget().getClass().getName());
        stopWatch.start();
        String method = joinPoint.getSignature().getName();
        Object obj = null;
        try {
            obj = ((ProceedingJoinPoint) joinPoint).proceed();
            Object[] args = joinPoint.getArgs();
            logger.info("调用方法{},{}", method, Arrays.toString(args));
        } catch (Exception ex) {
            logger.error("调用方法{}方法异常:", ex.getMessage());
            throw ex;
        } finally {
            stopWatch.stop();
            logger.info("调用[{}]方法执行时间:{}ms", method, stopWatch.getTotalTimeMillis());
        }
        logger.info("调用结果:{}", obj);
        return obj;
    }

//    @Before("webLog()")
//    public void doBefore(JoinPoint joinPoint) {
//
//        // 接收到请求，记录请求内容
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//
//        HttpServletRequest request = attributes.getRequest();
//
//
//        // 记录下请求内容
//        logger.info("调用[{}]方法，参数为{}",joinPoint.getSignature().getName(),Arrays.toString(joinPoint.getArgs()));
//
////        logger.info("URL : " + request.getRequestURL().toString());
////        logger.info("HTTP_METHOD : " + request.getMethod());
////        logger.info("IP : " + request.getRemoteAddr());
////        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
////        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
////        //获取所有参数方法一：
////        Enumeration<String> enu = request.getParameterNames();
////        while (enu.hasMoreElements()) {
////            String paraName = enu.nextElement();
////            System.out.println(paraName + ": " + request.getParameter(paraName));
////        }
//    }

//    @AfterReturning("webLog()")
//    public void doAfterReturning(JoinPoint joinPoint) throws Throwable {
//        Object obj = ((ProceedingJoinPoint) joinPoint).proceed();
//        // 处理完请求，返回内容
//        logger.info("调用结果：{}",obj);
//
//    }

}
