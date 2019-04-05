package com.idyoga.yoga.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.idyoga.yoga.R;
import com.idyoga.yoga.holder.BannerHolder;
import com.idyoga.yoga.holder.VideoBannerHolder;
import com.idyoga.yoga.holder.VideoHolder;
import com.idyoga.yoga.holder.VideoListHolder;
import com.idyoga.yoga.model.HomePageData;
import com.idyoga.yoga.model.VideoHomeData;

import java.util.ArrayList;
import java.util.List;

/**
 * @author weilgu
 * @time 2019/3/12 15:43
 * @des VideoFragment 的recyclerview的adapter
 */

public class VideoFragmentAdapter extends RecyclerView.Adapter {

//    private List<Object> mDatas;
//    List<VideoHomeData.VideoBean> mDatas;
    List<Object> mDatas;
    private Context mContext;
    private final LayoutInflater mFrom;
    private static final int SHOW_BANNER = 0;
    private static final int SHOW_CHARM_VIDEO = 1;

    public VideoFragmentAdapter(Context context) {
        this.mContext = context;
        mDatas = new ArrayList<>();
        mFrom = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == SHOW_BANNER){
            return new VideoBannerHolder(mFrom.inflate(R.layout.layout_banner,parent,false),mContext);
        }else if(viewType == SHOW_CHARM_VIDEO){
            return new VideoListHolder(mFrom.inflate(R.layout.item_video_list_layout,parent,false),mContext);
        }
        return null;
        //return new VideoListHolder(mFrom.inflate(R.layout.item_video_list_layout,parent,false),mContext);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        /*VideoHomeData.VideoBean videoBean = mDatas.get(position);
        List<VideoHomeData.VideoList> videoList = videoBean.getVideoList();
        ((VideoListHolder)holder).bindView(videoList,videoBean);*/

        int itemViewType = getItemViewType(position);
        if (itemViewType == SHOW_BANNER){
            List bannerList = (List) mDatas.get(position);
            if (bannerList.size() > 0){
                ArrayList<HomePageData.BannerBean> arrayList = new ArrayList<>();
                for (int i = 0; i < bannerList.size(); i++) {
                    HomePageData.BannerBean bean = (HomePageData.BannerBean) bannerList.get(i);
                    arrayList.add(bean);
                }
                ((VideoBannerHolder)holder).bindView(arrayList);
            }
        }else if(itemViewType == SHOW_CHARM_VIDEO){
//            List videoList = (List) mDatas.get(position);//List<VideoBean>
            VideoHomeData.VideoBean videoBean = (VideoHomeData.VideoBean)mDatas.get(position);
            List<VideoHomeData.VideoList> videoLists = videoBean.getVideoList();
            ((VideoListHolder)holder).bindView(videoLists,videoBean);
            /*if (videoList.size() > 0){
                for (int i = 0; i < videoList.size(); i++) {
                    VideoHomeData.VideoBean videoBean = (VideoHomeData.VideoBean)videoList.get(i);
                    List<VideoHomeData.VideoList> videoLists = videoBean.getVideoList();
                    ((VideoListHolder)holder).bindView(videoLists,videoBean);
                }
            }*/
        }

    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (mDatas.get(position) instanceof List){
            return SHOW_BANNER;
        }else if (mDatas.get(position) instanceof VideoHomeData.VideoBean){
            return SHOW_CHARM_VIDEO;
        }
        return -1;
    }

//    public void setDatas(List<VideoHomeData.VideoBean> datas) {
    public void setDatas(VideoHomeData datas) {
//        this.mDatas = datas;
        mDatas.clear();
        mDatas.add(datas.getBanner());
        mDatas.addAll(datas.getVideo());// List<VideoBean>
        notifyDataSetChanged();
    }
}
