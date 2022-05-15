package com.example.aop.springaop.aspect;

import com.example.aop.springaop.model.Department;
import com.example.aop.springaop.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class GeneralInterceptorAspect {

    /*
    Pointcut
    * execution(*(1) com.example.aop.springaop.controller.*(2).*(3)(.(a).(b))) ->
    * *(1) -> any return type within the package
    * *(2) -> any class within the package
    * *(3) -> any method name of the mentioned class or all if used *(2)
    * .(a) -> no args method
    * .(b) -> all args method
    * */
    @Pointcut("execution(* com.example.aop.springaop.controller.*.*(..))")
    //@Pointcut("execution(* com.example.aop.springaop.controller..*(..))") // See notes point 4
    //@Pointcut("within(com.example.aop.springaop..*)")
    //@Pointcut("this(com.example.aop.springaop.service.DepartmentService)")
    //@Pointcut("@annotation(com.example.aop.springaop.annotation.CustomAnnotation)")
    public void loggingPointCut(){
    }

    /* Below are advices*/
    //Can not pass two types of args in one pointcut
    @Before(value = "loggingPointCut() && args(id)",argNames = "joinPoint,id")
    public void before1( JoinPoint joinPoint,Integer id){
        log.info("Before method invoked::"+joinPoint.getSignature());
        log.info("Request with parameter::"+id);
    }

    @Before(value = "loggingPointCut() && args(name)",argNames = "joinPoint,name")
    public void before2( JoinPoint joinPoint,String name){
        log.info("Before method invoked::"+joinPoint.getSignature());
        log.info("Request with parameter::"+name);
    }

    /*
    * Another way to write pointcut. Write point cut when writing advice.
    * */
    @AfterReturning(value = "execution(* com.example.aop.springaop.controller.*.*(..))",
            returning = "department")
    // Employee employee
    public void after1( JoinPoint joinPoint,Department department ){
        log.info("AfterReturning method invoked::"+department);
    }


    @After(value = "loggingPointCut()")
    public void after( JoinPoint joinPoint){
        log.info("After method invoked::"+joinPoint.getSignature());
    }

    /*
    @AfterThrowing(value = "execution(* com.example.aop.springaop.controller.*.*(..))",
            throwing = "e")
    public void after( JoinPoint joinPoint, Exception e ){
        log.info("After method invoked::"+e.getMessage());
    }*/


   /* @Around("loggingPointCut()")
    public Object around( ProceedingJoinPoint joinPoint ) throws Throwable {
        log.info("Before method invoked::"+joinPoint.getArgs()[0]);
        Object object = joinPoint.proceed();

        if(object instanceof Employee){
            log.info("After method invoked..."+joinPoint.getArgs()[0]);
        }else if(object instanceof Department){
            log.info("After method invoked..."+joinPoint.getArgs()[0]);
        }

        return object;
    }*/

}
