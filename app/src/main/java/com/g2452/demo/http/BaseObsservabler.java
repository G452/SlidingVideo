package com.g2452.demo.http;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * 作者：G on 2020/4/22
 * 时间：11:00
 * 概述：BaseObsservabler
 */
public abstract class BaseObsservabler<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public abstract void onNext(T t) ;

    @Override
    public abstract void onError(Throwable e);

    @Override
    public void onComplete() {

    }

}
