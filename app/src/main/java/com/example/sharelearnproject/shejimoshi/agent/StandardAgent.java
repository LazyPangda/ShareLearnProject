package com.example.sharelearnproject.shejimoshi.agent;

import android.util.Log;

/**
 * 静态代理模式的好处：
 * 可以使真实角色的操作更加纯粹，不用关注一些公共的业务
 * 公共的业务交给代理角色，实现了业务的分工,解耦
 * 公共业务扩展的时候，方便集中管理
 * <p>
 * 缺点：
 * 一个真实角色就对应一个代理角色，代码量会翻倍，开发效率会变低
 */
public class StandardAgent {

    public interface IGoods {
        void delete();
    }

    public class GoodsImpl implements IGoods {

        @Override
        public void delete() {
            //删除商品
            Log.e("GoodsImpl", "正式删除");

        }
    }


    public class GoodsAgent implements IGoods {
        GoodsImpl goods;

        public GoodsAgent(GoodsImpl goods) {
            this.goods = goods;

        }

        @Override
        public void delete() {
            //doPrepare();
            goods.delete();
            //doAfter();
        }
    }


    public void useAgent() {
        GoodsAgent goodsAgent = new GoodsAgent(new GoodsImpl());
        goodsAgent.delete();
    }
}
