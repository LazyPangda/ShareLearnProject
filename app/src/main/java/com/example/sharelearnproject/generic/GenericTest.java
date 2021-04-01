package com.example.sharelearnproject.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型是jdk1.5引入的概念
 */
public class GenericTest<V,T extends V> {

    private V value;


    public void test() {
        List<? extends String> extendList = new ArrayList<>();
        //<?>和<? extends String>只有只读功能，不能增删改。
        //List<? > extendList = new ArrayList<>();
       // extendList.add("test");编译失败

        /**
         * <? super String>可以正常添加修改。
         */
        List<? super String> superList = new ArrayList<>();
        superList.add("test");

        genericMethod("adsfa");

    }


    //泛型方法(与类的T没有关系)
    public <T> T  genericMethod(T t){
        return t;
    }

    //普通方法。
    public void setValue(V value){
        this.value=value;
    }

    public void setValue(V value,T tt){
        this.value=value;
    }




}
