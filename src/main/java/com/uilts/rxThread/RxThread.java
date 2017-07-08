package com.uilts.rxThread;/**
 * Created by Administrator on 2017/6/7.
 */


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * Zhou keda
 * 2017/6/7
 */
public class RxThread {
    /***
     * 在IO线程中执行任务 * * @param <T>
     */
    public static <T> void doInIOThread(final IOTask<T> ioTask) {
        Observable.just(ioTask)
                .observeOn(Schedulers.io())
                .subscribe(new Observer<IOTask<T>>() {
                    public void onNext(IOTask<T> tioTask) {
                        ioTask.doInIOThread();
                    }
                    public void onSubscribe(Disposable disposable) {

                    }
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    public void onComplete() {

                    }
                });
    }
}
