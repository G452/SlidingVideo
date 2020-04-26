package com.g2452.demo.activity;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.g2452.demo.R;
import com.g2452.demo.adapter.VideoAdapter2;
import com.g2452.demo.base.BaseActivity;
import com.g2452.demo.base.BasePersenter;
import com.g2452.demo.base.BaseViewHolder;
import com.g2452.demo.bean.VideoBean;
import com.g2452.demo.contract.Icontract;
import com.g2452.demo.mvp.model.VideoModel;
import com.g2452.demo.mvp.persenter.VideoPersenter;
import com.g2452.demo.mvp.view.VideoView;
import com.g2452.demo.util.LogUtil;
import com.g2452.demo.view.EmptyControlVideo;
import com.shuyu.gsyvideoplayer.GSYVideoManager;

import java.util.ArrayList;

import static com.shuyu.gsyvideoplayer.video.base.GSYVideoView.CURRENT_STATE_ERROR;
import static com.shuyu.gsyvideoplayer.video.base.GSYVideoView.CURRENT_STATE_NORMAL;
import static com.shuyu.gsyvideoplayer.video.base.GSYVideoView.CURRENT_STATE_PLAYING;


public class VideoActivity<M extends VideoModel, V extends VideoView, P extends VideoPersenter> extends BaseActivity implements VideoView {


    private VideoPersenter videoPersenter;
    private Context mContext;
    private ArrayList<VideoBean> list = new ArrayList<>();
    private RecyclerView mRecycleView;
    private int newPos = 0;
    private VideoAdapter2 videoAdapter2;
    private LinearLayoutManager manager;
    private EmptyControlVideo mVideotVideo;
    private PagerSnapHelper mPagerSnapHelper;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video;
    }

    @Override
    public Icontract.Model creatModel() {
        return new VideoModel();
    }

    @Override
    public Icontract.View creatView() {
        return this;
    }

    @Override
    public void initView() {
        super.initView();
        this.mContext = this;
        initvideoData();
        mRecycleView = (RecyclerView) findViewById(R.id.viedo_recycle);
        manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(manager);
        mPagerSnapHelper = new PagerSnapHelper();
        mPagerSnapHelper.attachToRecyclerView(mRecycleView);
        videoAdapter2 = new VideoAdapter2(mContext);
        videoAdapter2.setList(list);
        mRecycleView.setAdapter(videoAdapter2);
        /*RecyclerView监听事件*/
        RecyclerViewListener();

    }
   /*RecyclerView监听事件*/
    private void RecyclerViewListener() {
        /*首次播放*/
        videoAdapter2.setFirstPlaykLinter(new VideoAdapter2.OnFirstPlayLintener() {
            @Override
            public void onItem(BaseViewHolder holder) {
                if (holder != null) {
                    playVideoholder(holder);
                }
            }
        });
        /*滑动监听*/
        mRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                            View viewIdle = mPagerSnapHelper.findSnapView(manager);
                            if (viewIdle != null) {
                                int postion = manager.getPosition(viewIdle);
                                if (newPos != postion) {
                                    LogUtil.getInstance().d("滑动停止" + postion);
                                    if (postion < list.size()) {
                                        for (int viewP = 0; viewP < list.size(); viewP++) {
                                            if (viewP == postion) {
                                                /*播放*/
                                                playVideo(postion);
                                                /*暂停*/
                                                pauseVide(postion + 1);
                                                pauseVide(postion - 1);
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
//        /*视频点击*/
//        videoAdapter2.setClickLinter(new VideoAdapter2.OnClickLintener() {
//            @Override
//            public void onclick(BaseViewHolder holder,int postion) {
//                mVideotVideo = (EmptyControlVideo) holder.getView(R.id.video_layout_video);
////                当前播放状态
//                int lastState = mVideotVideo.getGSYVideoManager().getLastState();
////                暂停状态则开始播放
//                if(lastState==CURRENT_STATE_NORMAL||lastState==CURRENT_STATE_ERROR){
//                    playVideo(postion);
//                    mVideotVideo.isPauseImg(View.GONE);
//                }else if(lastState==CURRENT_STATE_PLAYING){
////                    播放状态暂停
//                    mVideotVideo.onVideoPause();
//                    mVideotVideo.isPauseImg(View.VISIBLE);
//                }
//
//
//            }
//        });
    }

    /**
     * 暂停视频
     */
    private void pauseVide(int postion) {
        View viewByPosition = manager.findViewByPosition(postion);
        if (viewByPosition != null) {
            EmptyControlVideo video_layout_video = (EmptyControlVideo) viewByPosition.findViewById(R.id.video_layout_video);
            video_layout_video.release();
            video_layout_video.onVideoPause();
        }
    }
    /**
     * 播放视频
     */
    private void playVideo(int postion) {
        View viewByPosition = manager.findViewByPosition(postion);
        if (viewByPosition != null) {
            mVideotVideo = (EmptyControlVideo) viewByPosition.findViewById(R.id.video_layout_video);
            mVideotVideo.release();
            mVideotVideo.setUp(list.get(postion).getVideoUrl(), true, "");
            mVideotVideo.setLooping(true);
            mVideotVideo.startPlayLogic();
        }
    }   /**
     * 播放视频
     */
    private void playVideoholder(BaseViewHolder holder) {
        mVideotVideo = (EmptyControlVideo) holder.getView(R.id.video_layout_video);
        mVideotVideo.release();
        mVideotVideo.setUp(list.get(0).getVideoUrl(), true, "");
        mVideotVideo.setLooping(true);
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
        videoPersenter = new VideoPersenter();
        videoPersenter.initContext(this);
        return videoPersenter;
    }

    @Override
    public void getData(String data) {

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
            LogUtil.getInstance().d("当期进度"+duration);
        }
    }
}
