package com.example.sharelearnproject.annotations;

import androidx.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
//生效范围。只在源码层生效。还有CLASS:编译层。RUNTIME:运行时层。他们之间是子集关系。
//注解获取view，必须是运行时。
@Retention (RetentionPolicy.RUNTIME)
//针对范围。FIELD:类成员
// TYPE：类。METHOD：方法。
// PARAMETER：参数等。
@Target(ElementType.FIELD)

//以上两个注解被称为元注解。即：修饰注解的注解被称为元注解。
public @interface ViewAnnotation {
    int value() default -1;


}
