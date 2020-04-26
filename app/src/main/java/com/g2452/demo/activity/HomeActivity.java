package com.g2452.demo.activity;

import android.util.Log;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.g2452.demo.R;
import com.g2452.demo.adapter.HomeAdapter;
import com.g2452.demo.contract.Icontract;
import com.g2452.demo.http.HttpUrl;
import com.g2452.demo.bean.HomeBean;
import com.g2452.demo.mvp.model.HomeModel;
import com.g2452.demo.base.BaseActivity;
import com.g2452.demo.mvp.persenter.HomePersenter;
import com.g2452.demo.mvp.view.HomeView;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeActivity<M extends HomeModel, V extends HomeView,P extends HomePersenter>extends BaseActivity implements HomeView  {
    private HomePersenter homePersenter;
    private TextView viewById;
    private RecyclerView mRecycle;
    private HomeAdapter homeAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.home_activity;
    }

    @Override
    public HomeModel creatModel() {
        return new HomeModel();
    }

    @Override
    public Icontract.View creatView() {
        return this;
    }

    @Override
    public HomePersenter creatPersenter() {
        homePersenter = new HomePersenter();
        homePersenter.initContext(this);
        return homePersenter;
    }

    @Override
    public void initData() {
        super.initData();
        Map<String,String> map = new HashMap<>();
        map.put("key","f11333ad4bff7e6fc86bfc4e6f1086b8");
        map.put("num","20");
        homePersenter.getData(HttpUrl.TEST_URL,map);
        toast("哈哈哈哈");
    }

    @Override
    public void initView() {
        super.initView();
        viewById = (TextView) findViewById(R.id.mytext);
        mRecycle = (RecyclerView) findViewById(R.id.recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecycle.setLayoutManager(linearLayoutManager);
        homeAdapter = new HomeAdapter(this);
        mRecycle.setAdapter(homeAdapter);
    }

    @Override
    public void getData(String data) {
        Log.d("撒大声地==","da=="+data);
        StringBuffer stringBuffer = new StringBuffer();
        HomeBean homeBean = new Gson().fromJson(data, HomeBean.class);
        List<HomeBean.NewslistBean> newslist = homeBean.getNewslist();
        for(int i=0;i<newslist.size();i++){
            stringBuffer.append(newslist.get(i).getTitle()+"=======");
        }
        viewById.setText(stringBuffer.toString());
        homeAdapter.setList(newslist);
    }
}
