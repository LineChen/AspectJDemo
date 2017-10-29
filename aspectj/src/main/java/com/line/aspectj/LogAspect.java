package com.line.aspectj;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by chenliu on 17/10/26.
 */

@Aspect
public class LogAspect {
    private static final String POINTCUT_METHOD = "execution(* com.line.aspectjdemo.MainActivity.on*(..))";

    @Pointcut(POINTCUT_METHOD)
    public void logActivity(){}

    @Before("logActivity()")
    public void logBefore(JoinPoint joinPoint){
        Log.e("LogAspect", "logBefore:" + joinPoint.toShortString());
    }

    @After("logActivity()")
    public void logAfter(JoinPoint joinPoint){
        Log.e("LogAspect", "logAfter:" + joinPoint.toShortString());
    }
}
