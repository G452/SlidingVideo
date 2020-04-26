package com.g2452.demo.base;

import android.content.Context;

import com.g2452.demo.contract.Icontract;

import java.lang.ref.WeakReference;

public abstract class BasePersenter<M extends Icontract.Model, V extends Icontract.View> implements Icontract.Persenter<M, V> {


    protected M model;
    protected WeakReference<V> vWeakReference;
    protected Context context;

    public void registerModel(Icontract.Model model) {
        this.model = (M) model;
    }

    public void registerView(V view) {
        vWeakReference = new WeakReference<>(view);
    }


    @Override
    public V getView() {
        return vWeakReference == null ? null : vWeakReference.get();
    }

    @Override
    public void destory() {
        if (vWeakReference != null) {
            vWeakReference.clear();
            vWeakReference=null;
        }
         onViewDestory();
    }

    public void initContext(Context context){
        this.context=context;
    };

    protected abstract void onViewDestory();
}
