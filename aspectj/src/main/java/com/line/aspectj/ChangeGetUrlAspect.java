package com.line.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ConstPool;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.StringMemberValue;

/**
 * Created by chenliu on 2018/1/31.
 */
@Aspect
public class ChangeGetUrlAspect {
    public static final String TAG = "ChangeGetUrlAspect";
    public static final String CLICK_POINTCUT = "execution(@com.line.aspectj.MyGet * *(..)) && @annotation(annotation)";

    @Before(CLICK_POINTCUT)
    public void click(JoinPoint joinPoint, final MyGet annotation) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        java.lang.annotation.Annotation[] declaredAnnotations = method.getAnnotations();
        ChangeValueAnotation changeValueAnotation = null;
        for (int i = 0; i < declaredAnnotations.length; i++) {
            java.lang.annotation.Annotation ann = declaredAnnotations[i];
            if(ann instanceof ChangeValueAnotation){
                changeValueAnotation = (ChangeValueAnotation) ann;
            }
        }
        if (changeValueAnotation != null) {
            try{
//                ClassPool pool = new javassist.ClassPool(true);
//                pool.appendClassPath(new LoaderClassPath(annotation.clazz().getClassLoader()));
                ClassPool pool = ClassPool.getDefault();
                pool.insertClassPath(new ClassClassPath(annotation.clazz()));
                //获取需要修改的类
                CtClass ct = pool.get(annotation.clazz().getName());


                //获取类里的所有方法
                CtMethod[] cms = ct.getDeclaredMethods();
                CtMethod cm = null;
                for (int i = 0; i < cms.length; i++) {
                    if(cms[i].getName().equals(method.getName())){
                        cm = cms[i];
                    }
                }
                if (cm != null) {
                    MethodInfo minInfo = cm.getMethodInfo();
                    System.out.println("方法名称====" + cm.getName());
                    ConstPool constPool = minInfo.getConstPool();
                    //获取注解信息
                    AnnotationsAttribute attribute2 = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
                    Annotation[] annotations = attribute2.getAnnotations();
                    for (int i = 0; i < annotations.length; i++) {
                        Annotation anno = annotations[i];
                        if(anno.getTypeName().equals(MyGet.class.getName())){

                        }
                    }

                    Annotation ann = new Annotation("javax.persistence.PersistenceContext", constPool);

                    //修改名称为unitName的注解
                    ann.addMemberValue("url", new StringMemberValue("basic-entity", constPool));
                    attribute2.setAnnotation(ann);
                    minInfo.addAttribute(attribute2);
                }

            } catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}