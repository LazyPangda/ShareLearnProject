package com.example.sharelearnproject.annotations;

import java.lang.reflect.Constructor;

public class FanShe {
    void test(){
        AptClass aptClass = new AptClass();
        Class<? extends AptClass> aClass = aptClass.getClass();

        try {
            Constructor<? extends AptClass> constructor = aClass.getDeclaredConstructor(Integer.class, String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
