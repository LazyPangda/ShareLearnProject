package com.example.sharelearnproject.annotations;

import android.app.Activity;
import android.os.MemoryFile;
import android.view.View;

import java.lang.reflect.Field;

public class AnnotationUtils {

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

}
