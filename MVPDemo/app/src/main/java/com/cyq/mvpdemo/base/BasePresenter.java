package com.cyq.mvpdemo.base;

import java.lang.ref.WeakReference;

/**
 * Create by 陈扬齐
 * Create on 2019-06-30
 * description:
 */
public abstract class BasePresenter<V extends BaseView, M extends BaseModel, CONTRACT> {

    protected M m;
    private WeakReference<V> vWeakReference;

    public BasePresenter() {
        m = getModel();
    }

    public void bindView(V v) {
        vWeakReference = new WeakReference<>(v);
    }

    public void unBindView() {
        if (vWeakReference != null) {
            vWeakReference.clear();
            vWeakReference = null;
            System.gc();
        }
    }

    //获取View,P--V
    public V getView() {
        if (vWeakReference != null) {
            return vWeakReference.get();
        }
        return null;
    }

    //获取子类具体契约（Model层和View层协商的共同业务）
    public abstract CONTRACT getContract();

    public abstract M getModel();
}
