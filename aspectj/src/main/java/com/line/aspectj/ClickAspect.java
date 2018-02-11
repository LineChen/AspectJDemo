package com.line.aspectj;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by chenliu on 17/10/29.
 */
@Aspect
public class ClickAspect {
    public static final String TAG = "ClickAspect";
    public static final String CLICK_POINTCUT = "execution(@com.line.aspectj.ClickAnnotation * *(..)) && @annotation(annotation)";

    @Before(CLICK_POINTCUT)
    public void clickAfter(JoinPoint joinPoint, final ClickAnnotation annotation){
//        Log.e(TAG, "logAfter:toLongString-" + joinPoint.toLongString());
//        Log.e(TAG, "logAfter:declaredName-" + annotation.declaredName());

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
            //1
//            try {
//                final String value = changeValueAnotation.value();
//                InvocationHandler h = Proxy.getInvocationHandler(annotation);
//                // 获取 AnnotationInvocationHandler 的 memberValues 字段
//                Field hField = h.getClass().getDeclaredField("memberValues");
//                Log.e(TAG, hField.getType().getName());
//                // 因为这个字段事 private final 修饰，所以要打开权限
//                hField.setAccessible(true);
//                // 获取 memberValues
//                Map memberValues = (Map) hField.get(h);
//                // 修改 value 属性值
//                memberValues.put("declaredName", value);
//            } catch (Exception e){
//                e.printStackTrace();
//            }

            ///2
//            try{
//                Annotation newAnnotation = new ClickAnnotation() {
//
//                    @Override
//                    public String declaredName() {
//                        return "another value";
//                    }
//
//                    @Override
//                    public Class<? extends Annotation> annotationType() {
//                        return annotation.annotationType();
//                    }
//                };
//                Field field = Class.class.getDeclaredField("annotationData");
//                field.setAccessible(true);
//                Map<Class<? extends Annotation>, Annotation> annotations = (Map<Class<? extends Annotation>, Annotation>) field.get(method);
//                annotations.put(ClickAnnotation.class, newAnnotation);
//
//            } catch (Exception e){
//                e.printStackTrace();
//            }

//            try {
//                Class<?> superclass = method.getClass().getSuperclass();
//                Field declaredField = superclass.getDeclaredField("declaredAnnotations");
//                declaredField.setAccessible(true);
//                @SuppressWarnings("unchecked")
//                Map<Class<? extends Annotation>, Annotation> map = (Map<Class<? extends Annotation>, Annotation>) declaredField
//                        .get(method);
//                map.put(ClickAnnotation.class, new ClickAnnotation() {
//
//                    @Override
//                    public Class<? extends Annotation> annotationType() {
//                        return ClickAnnotation.class;
//                    }
//
//                    @Override
//                    public String declaredName  () {
//                        return "new";
//                    }
//                });
//
//            } catch (Exception e){
//                e.printStackTrace();
//            }

//            ClassPool pool = ClassPool.getDefault();
//            //获取需要修改的类
//            CtClass ct = pool.get("com.tgb.itoo.collection.base.CollectionBase");
//
//            //获取类里的所有方法
//            CtMethod[] cms = ct.getDeclaredMethods();
//            CtMethod cm = cms[0];
//            System.out.println("方法名称====" + cm.getName());
//
//            //获取注解信息
//            AnnotationsAttribute attribute2 = new AnnotationsAttribute(cp, AnnotationsAttribute.visibleTag);
//            Annotation ann = new Annotation("javax.persistence.PersistenceContext", cp);

        }
        Log.e(TAG, Arrays.toString(declaredAnnotations));
        Log.e(TAG, "method:" + method.toString());
    }
}
