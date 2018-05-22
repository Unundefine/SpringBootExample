//package com.example.springboot.aop;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.context.annotation.Configuration;
//
////描述切面类
//@Aspect
//@Configuration
//public class logger {
//
//    /*
//     * 定义一个切入点
//     */
//    // @Pointcut("execution (* findById*(..))")
//    @Pointcut("execution(* com.example.springboot.controller.*.*(..))")
//    public void excudeService(){}
//    /*
//     * 通过连接点切入
//     */
//    @Before("execution(* findById*(..)) &&" + "args(id,..)")
//    public void twiceAsOld1(Long id){
//        System.err.println ("切面before执行了。。。。id==" + id);
//
//    }
//
//    @Around("excudeService()")
//    public Object twiceAsOld(ProceedingJoinPoint thisJoinPoint){
//        System.err.println ("切面执行了。。。。");
//        try {
//            Object thing = (Object) thisJoinPoint.proceed ();
//            thing.setName (thing.getName () + "=========");
//            return thing;
//        } catch (Throwable e) {
//            e.printStackTrace ();
//        }
//        return null;
//    }
//
//}