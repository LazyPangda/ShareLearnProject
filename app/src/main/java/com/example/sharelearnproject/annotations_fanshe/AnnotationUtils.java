package com.example.sharelearnproject.annotations_fanshe;

import android.app.Activity;
import android.os.Parcelable;
import android.view.View;

import java.lang.reflect.Field;
import java.util.Arrays;

public class AnnotationUtils {

    //运行时获取注解赋值view
    public static void init(Activity mActivity) {
        Class<? extends Activity> aClass = mActivity.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field mFiled : declaredFields) {
            //判断是否有注解
            if (mFiled.getDeclaredAnnotations() != null) {
                //确定注解类型
                if (mFiled.isAnnotationPresent(ViewAnnotation.class)) {
                    //允许修改属性，比如private
                    mFiled.setAccessible(true);
                    ViewAnnotation annotation = mFiled.getAnnotation(ViewAnnotation.class);
                    //获取到view，赋值给view引用
                    View viewById = mActivity.findViewById(annotation.value());
                    try {
                        mFiled.set(mActivity, viewById);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    public static void initIntent(Activity activity) {
        Field[] declaredFields = activity.getClass().getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            Field declaredField = declaredFields[i];
            boolean isPresent = declaredField.isAnnotationPresent(IntentAnnotation.class);
            if (isPresent) {
                declaredField.setAccessible(true);
                IntentAnnotation annotation = declaredField.getAnnotation(IntentAnnotation.class);
                String value = annotation.value();
                if (value.isEmpty()) {
                    String name = declaredField.getName();
                    value = name;

                } else {

                }
                Object o = activity.getIntent().getExtras().get(value);
                    //字段类型
                Class<?> type = declaredField.getType();
                if (type.isArray()) {
                    //获取集合中item的类型
                    Class<?> componentType = type.getComponentType();
                    //isAssignableFrom()方法是从类继承的角度去判断，instanceof关键字是从实例继承的角度去判断。
                    //isAssignableFrom()方法是判断是否为某个类的父类，instanceof关键字是判断是否某个类的子类。
                    if (Parcelable.class.isAssignableFrom(componentType)) {
                        Object[] objs = (Object[]) o;
                        //获取新的数组类型。
                        Object[] objects = Arrays.copyOf(objs, objs.length, (Class<? extends Object[]>) declaredField.getType());
                        o = objects;

                    }
                }


                try {
                    if (o != null) {
                        declaredField.set(activity, o);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
