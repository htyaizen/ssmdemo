package com.uilts.rxThread;/**
 * Created by Administrator on 2017/6/7.
 */


import org.reactivestreams.Subscriber;

/**
 * Zhou keda
 * 2017/6/7
 */
public abstract class MyOnSubscribe<C> implements Subscriber<C> {
    private C c;

    public MyOnSubscribe(C c) {
        setT(c);
    }

    public C getT() {
        return c;
    }

    public void setT(C c) {
        this.c = c;
    }


}
