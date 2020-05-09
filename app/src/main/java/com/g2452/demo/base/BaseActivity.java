package com.g2452.demo.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.g2452.demo.R;
import com.g2452.demo.contract.Icontract;


public abstract class BaseActivity<M extends Icontract.Model, V extends Icontract.View, P extends BasePersenter> extends AppCompatActivity implements BaseMVP<M, V, P> {


    private View view;
    private P persenter;
    private SparseArray<View> views = new SparseArray<>();
    private RelativeLayout prelateChild;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = LayoutInflater.from(this).inflate(R.layout.layout_base, null);
        setRequestedOrientation(ActivityInfo
                .SCREEN_ORIENTATION_PORTRAIT);//竖屏
        setContentView(view);
        initViews();
        persenter = creatPersenter();
        if (persenter != null) {
            persenter.registerModel(creatModel());
            persenter.registerView(creatView());
        }
        initContext(this);
        initData();
        initView();
    }



    private void initContext(Context context) {

    }

    private void initViews() {
        prelateChild = (RelativeLayout) view.findViewById(R.id.main_base_layout);
        //获取一个view
        View child = LayoutInflater.from(this).inflate(getLayoutId(), null);
        //追加到布局中
        prelateChild.addView(child);
    }

    /*获取子view*/
    protected abstract int getLayoutId();

    public void initData() {
    }
    public void initView() {

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (persenter != null) {
            persenter.destory();
        }
    }

    /**
     * 获取控件
     *
     * @param viewId
     * @return
     */
    public View getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = findViewById(viewId);
            views.put(viewId, view);
        }
        return view;
    }

    /**
     * Toast方法
     */

    public void toast(String s, int t) {
        Toast.makeText(this, s, t).show();
    }
    /**
     * Toast方法
     */
    public void toast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }


}
