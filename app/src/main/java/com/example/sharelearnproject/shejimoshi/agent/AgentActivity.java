package com.example.sharelearnproject.shejimoshi.agent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.sharelearnproject.R;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class AgentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent);
        StandardAgent standardAgent = new StandardAgent();
        final StandardAgent.GoodsImpl goods = standardAgent.new GoodsImpl();
        //也可以这种方式获取interface  StandardAgent.GoodsImpl.class.getInterfaces()
       Object o= Proxy.newProxyInstance(StandardAgent.GoodsImpl.class.getClassLoader(), new Class[]{StandardAgent.IGoods.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return method.invoke(goods,args);
            }
        });

        ((StandardAgent.IGoods) o).delete();

        List<? super String>temList=new ArrayList<>();
        temList.add("1");






//        public class TestDynamicProxy implements InvocationHandler {
//            Object target;
//            public TestDynamicProxy(Object target){
//                this.target = target;
//            }
//            @Override public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                Log.e("wcwcwc", "proxy: " + proxy.getClass().getCanonicalName() + "\n" +
//                        "method: " + method + "\n" +
//                        "args: " + args);
//                Object result = method.invoke(target, args);
//                return result;
//            }
//        }
//
//        //实际应场景。每次都会通过代理生成一个代理对象。
//        RealAction realAction = new RealAction();
//        Action action = (Action) Proxy.newProxyInstance(RealAction.class.getClassLoader(), new Class[]{Action.class}, new TestDynamicProxy(realAction));
//        action.doWork();



    }
}
