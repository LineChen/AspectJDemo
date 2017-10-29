package com.line.aspectj;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by chenliu on 17/10/29.
 */
@Aspect
public class ClickAspect {
    public static final String TAG = "ClickAspect";
    public static final String CLICK_POINTCUT = "execution(@com.line.aspectj.ClickAnnotation * *(..)) && @annotation(annotation)";

    @After(CLICK_POINTCUT)
    public void clickAfter(JoinPoint joinPoint, ClickAnnotation annotation){
        Log.e(TAG, "logAfter:toLongString-" + joinPoint.toLongString());
        Log.e(TAG, "logAfter:declaredName-" + annotation.declaredName());
    }
}
