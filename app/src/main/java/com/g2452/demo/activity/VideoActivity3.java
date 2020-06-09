package com.g2452.demo.activity;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.g2452.demo.R;
import com.g2452.demo.adapter.VideoPkAdapter;
import com.g2452.demo.adapter.VideoPkAdapter2;
import com.g2452.demo.adapter.VideoPkAdapter_zy1;
import com.g2452.demo.adapter.VideoPkAdapter_zy2;
import com.g2452.demo.base.BaseActivity;
import com.g2452.demo.base.BasePersenter;
import com.g2452.demo.base.BaseViewHolder;
import com.g2452.demo.bean.VideoBean;
import com.g2452.demo.contract.Icontract;
import com.g2452.demo.util.LogUtil;
import com.g2452.demo.view.EmptyControlVideo;
import com.shuyu.gsyvideoplayer.GSYVideoManager;

import java.util.ArrayList;


public class VideoActivity3<M extends Icontract.Model, V extends Icontract.View, P extends Icontract.Persenter> extends BaseActivity {
    private Context mContext;
    private ArrayList<VideoBean> list = new ArrayList<>();
    private RecyclerView mRecycleView1;
    private RecyclerView mRecycleView2;
    private int newPos = 0;
    private VideoPkAdapter_zy1 videoAdapter1;
    private VideoPkAdapter_zy2 videoAdapter2;
    private LinearLayoutManager manager1;
    private LinearLayoutManager manager2;
    private EmptyControlVideo mVideotVideo;
    private PagerSnapHelper mPagerSnapHelper1;
    private PagerSnapHelper mPagerSnapHelper2;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video3;
    }

    @Override
    public Icontract.Model creatModel() {
        return null;
    }

    @Override
    public Icontract.View creatView() {
        return null;
    }

    @Override
    public void initView() {
        super.initView();
        this.mContext = this;
        initvideoData();
        mRecycleView1 = (RecyclerView) findViewById(R.id.recycle_1);
        mRecycleView2 = (RecyclerView) findViewById(R.id.recycle_2);
        manager1 = new LinearLayoutManager(mContext);
        manager2 = new LinearLayoutManager(mContext);
        manager1.setOrientation(LinearLayoutManager.VERTICAL);
        manager2.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView1.setLayoutManager(manager1);
        mRecycleView2.setLayoutManager(manager2);
        mPagerSnapHelper1 = new PagerSnapHelper();
        mPagerSnapHelper2 = new PagerSnapHelper();
        mPagerSnapHelper1.attachToRecyclerView(mRecycleView1);
        mPagerSnapHelper2.attachToRecyclerView(mRecycleView2);
        videoAdapter1 = new VideoPkAdapter_zy1(mContext);
        videoAdapter2 = new VideoPkAdapter_zy2(mContext);
        videoAdapter1.setList(list);
        videoAdapter2.setList(list);
        mRecycleView1.setAdapter(videoAdapter1);
        mRecycleView2.setAdapter(videoAdapter2);
        /*RecyclerView监听事件*/
        RecyclerViewListener();
    }

    /*RecyclerView监听事件*/
    private void RecyclerViewListener() {
        /*首次播放*/
        videoAdapter1.setFirstPlaykLinter(new VideoPkAdapter_zy1.OnFirstPlayLintener() {
            @Override
            public void onItem(BaseViewHolder holder) {
                if (holder != null) {
                    playVideoholder(holder);
                }
            }
        });

        /*滑动监听*/
        mRecycleView1.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    /*正在拖拽*/
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        LogUtil.getInstance().d("正在拖拽");
                        break;
                    /*滑动停止*/
                    case RecyclerView.SCROLL_STATE_IDLE:
                        if (recyclerView != null && recyclerView.getChildCount() > 0) {
                            View viewIdle = mPagerSnapHelper1.findSnapView(manager1);
                            if (viewIdle != null) {
                                int postion = manager1.getPosition(viewIdle);
                                if (newPos != postion) {
                                    LogUtil.getInstance().d("滑动停止" + postion);
                                    if (postion < list.size()) {
                                        for (int viewP = 0; viewP < list.size(); viewP++) {
                                            if (viewP == postion) {
                                                /*播放*/
                                                playVideo(postion, 1);
                                                /*暂停*/
//                                                pauseVide(postion + 1, 1);
//                                                pauseVide(postion - 1, 1);
                                            }
                                        }
                                    } else {
                                        toast("没有更多了");
                                    }
                                    newPos = postion;
                                }
                            }
                        }
                        break;
                    /*惯性滑动中*/
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        LogUtil.getInstance().d("惯性滑动中");
                        break;
                }
            }
        }); /*滑动监听*/
        mRecycleView2.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    /*正在拖拽*/
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        LogUtil.getInstance().d("正在拖拽");
                        break;
                    /*滑动停止*/
                    case RecyclerView.SCROLL_STATE_IDLE:
                        if (recyclerView != null && recyclerView.getChildCount() > 0) {
                            View viewIdle = mPagerSnapHelper2.findSnapView(manager2);
                            if (viewIdle != null) {
                                int postion = manager2.getPosition(viewIdle);
                                if (newPos != postion) {
                                    LogUtil.getInstance().d("滑动停止" + postion);
                                    if (postion < list.size()) {
                                        for (int viewP = 0; viewP < list.size(); viewP++) {
                                            if (viewP == postion) {
                                                /*播放*/
                                                playVideo(postion,2);
                                                /*暂停*/
                                            }
                                        }
                                    } else {
                                        toast("没有更多了");
                                    }
                                    newPos = postion;
                                }
                            }
                        }
                        break;
                    /*惯性滑动中*/
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        LogUtil.getInstance().d("惯性滑动中");
                        break;
                }
            }
        });
    }

    /**
     * 暂停视频
     */
    private void pauseVide(int postion, int type) {
        View viewByPosition;
        if (type == 1) {
              viewByPosition = manager1.findViewByPosition(postion);
        } else {
              viewByPosition = manager2.findViewByPosition(postion);
        }
        if (viewByPosition != null) {
            EmptyControlVideo video_layout_video = (EmptyControlVideo) viewByPosition.findViewById(R.id.video_layout_video);
            video_layout_video.release();
            video_layout_video.onVideoPause();
        }
    }

    /**
     * 播放视频
     */
    private void playVideo(int postion,int type) {
        View viewByPosition;
        if (type == 1) {
            viewByPosition = manager1.findViewByPosition(postion);
        } else {
            viewByPosition = manager2.findViewByPosition(postion);
        }
        if (viewByPosition != null) {
            mVideotVideo = (EmptyControlVideo) viewByPosition.findViewById(R.id.video_layout_video);
            mVideotVideo.startPlayLogic();
        }
    }

    /**
     * 播放视频
     */
    private void playVideoholder(BaseViewHolder holder) {
        mVideotVideo = (EmptyControlVideo) holder.getView(R.id.video_layout_video);
        mVideotVideo.startPlayLogic();
    }


    /**
     * 模拟数据
     */
    private void initvideoData() {
        list = new ArrayList<>();
        list.add(new VideoBean("http://vicdn.baichuan99.cn/user/202004/12/18/59/1586689143613AD7CC47.mp4", "http://imgcdn.baichuan99.cn/do?uri=/vimg/202004/12/18/59/1586689143611ABDA6AC.jpg&yt=1"));
        list.add(new VideoBean("http://vicdn.baichuan99.cn/user/202004/12/18/59/1586689143319AEAA87F.mp4", "http://imgcdn.baichuan99.cn/do?uri=/vimg/202004/12/18/59/1586689143317A7ED6B0.jpg&yt=1"));
        list.add(new VideoBean("http://vicdn.baichuan99.cn/user/202004/12/18/59/1586689147864A233F61.mp4", "http://imgcdn.baichuan99.cn/do?uri=/vimg/202004/12/18/59/1586689147862A194FAE.jpg&yt=1"));
        list.add(new VideoBean("http://vicdn.baichuan99.cn/user/201910/31/21/07/1572527230302A19E99C.mp4", "http://imgcdn.baichuan99.cn/do?uri=/vimg/201910/31/21/07/1572527230302A4427A3.jpg&yt=1"));
        list.add(new VideoBean("http://vicdn.baichuan99.cn/user/201910/31/21/07/1572527230880AFC8378.mp4", "http://imgcdn.baichuan99.cn/do?uri=/vimg/201910/31/21/07/1572527230880A6D89BA.jpg&yt=1"));
        list.add(new VideoBean("http://vicdn.baichuan99.cn/user/201909/15/17/17/1568539053368A8ED944.mp4", "http://imgcdn.baichuan99.cn/do?uri=/vimg/201909/15/17/17/1568539053365AB3E023.jpg&yt=1"));
        list.add(new VideoBean("http://vicdn.baichuan99.cn/user/201909/22/13/53/1569131615974A101E8C.mp4", "http://imgcdn.baichuan99.cn/do?uri=/vimg/201909/22/13/53/1569131615972A0FD312.jpg&yt=1"));
        list.add(new VideoBean("http://vicdn.baichuan99.cn/user/201909/15/17/17/1568539077214A6A2441.mp4", "http://imgcdn.baichuan99.cn/do?uri=/vimg/201909/15/17/17/1568539077212A94BFB3.jpg&yt=1"));
        list.add(new VideoBean("http://vicdn.baichuan99.cn/user/201909/15/17/17/1568539075354A2242CE.mp4", "http://imgcdn.baichuan99.cn/do?uri=/vimg/201909/15/17/17/1568539075352AD63364.jpg&yt=1"));
        list.add(new VideoBean("http://vicdn.baichuan99.cn/user/201909/15/17/17/1568539067671AA5C2E3.mp4", "http://imgcdn.baichuan99.cn/do?uri=/vimg/201909/15/17/17/1568539067669ABA7459.jpg&yt=1"));
    }


    @Override
    public BasePersenter creatPersenter() {
        return null;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mVideotVideo != null) {
            //释放所有
            mVideotVideo.release();
            mVideotVideo.setVideoAllCallBack(null);
            GSYVideoManager.releaseAllVideos();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (mVideotVideo != null) {
            mVideotVideo.startPlayLogic();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mVideotVideo != null) {
            mVideotVideo.onVideoPause();
            GSYVideoManager.onPause();
            int duration = mVideotVideo.getDuration();
            LogUtil.getInstance().d("当期进度" + duration);
        }
    }
}
