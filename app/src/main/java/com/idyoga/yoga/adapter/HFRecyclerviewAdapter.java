package com.idyoga.yoga.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.idyoga.yoga.R;
import com.idyoga.yoga.holder.BannerHolder;
import com.idyoga.yoga.holder.BaseHolder;
import com.idyoga.yoga.holder.HomeMineInfoHolder;
import com.idyoga.yoga.holder.HotHolder;
import com.idyoga.yoga.holder.OrderHolder;
import com.idyoga.yoga.holder.SignHolder;
import com.idyoga.yoga.model.HomePageData;
import com.idyoga.yoga.model.HomeRecommendBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import vip.devkit.library.Logcat;


/**
 * @author weilgu
 * @time 2019/3/11 9:55
 * @des 首页fragment 的recycler view 的 adapter
 */

public class HFRecyclerviewAdapter extends BaseLoadMoreAdapter<Object,BaseHolder>{//extends RecyclerView.Adapter {

    private Context mContext;
    private final LayoutInflater mFrom;
    private static final int SHOW_BANNER = 0; //banner
    private static final int SHOW_MENU = 1;  // 快速问诊,预约,商城 ,抢
    private static final int SHOW_HOT_VIDEO = 2; // 专题活动
    private static final int SHOW_ORTHRE_VIDEO = 3; //猜你喜欢
    private static final int SHOW_HOME_MINE_INFO = 4; //用户信息,打卡等

    public HFRecyclerviewAdapter(Context context) {
        super(context);
        mContext = context;
        mFrom = LayoutInflater.from(mContext);
    }

    public void setDatas(HomePageData homeRecommendBean){
        if (mDatas != null && !mDatas.isEmpty()){
            mDatas.clear();
        }
        List<HomePageData.BannerBean> banner = homeRecommendBean.getBanner();
        HomePageData.JumpUrlArrBean jumpUrlArr = homeRecommendBean.getJumpUrlArr();
        HomePageData.UserDataBean userData = homeRecommendBean.getUserData();
        List<HomePageData.SubjectListBean> subjectList = homeRecommendBean.getSubjectList();
        List<HomePageData.ShopListBean> shopList = homeRecommendBean.getShopList();
        if (banner != null){
            mDatas.add(banner);
        }
        if (jumpUrlArr != null){
            mDatas.add(jumpUrlArr);
        }
        if (userData != null){
            mDatas.add(userData);
        }
        if (subjectList != null){
            mDatas.add(subjectList);
        }
        if (shopList != null){
            mDatas.add(shopList);
        }
        notifyDataSetChanged();
    }

    @Override
    protected BaseHolder childAdapterContentHolder(ViewGroup parent, int viewType) {
        if (viewType == SHOW_BANNER){
            return new BannerHolder(mFrom.inflate(R.layout.layout_banner,parent,false),mContext);
        }else if(viewType == SHOW_MENU){
            return new SignHolder(mFrom.inflate(R.layout.layout_home_menu,parent,false),mContext);
        }else if(viewType == SHOW_HOT_VIDEO){
            return new HotHolder(mFrom.inflate(R.layout.item_hot_video_layout,parent,false),mContext);
        }else if(viewType == SHOW_ORTHRE_VIDEO){
            return new OrderHolder(mFrom.inflate(R.layout.item_guess_layout,parent,false),mContext);
        }else if(viewType == SHOW_HOME_MINE_INFO){
            return new HomeMineInfoHolder(mFrom.inflate(R.layout.item_home_mine_info_layout,parent,false),mContext);
        }
        return null;
    }

    @Override
    protected View loadMoreView(ViewGroup parent) {
        //BaseRecyclerViewAdapterHelper
        return mFrom.inflate(R.layout.a_item_test_layout,parent,false);
    }

    @Override
    protected void bindView(BaseHolder holder, int position) {
        holder.bindView(mDatas.get(position));
    }

    @Override
    protected int childAdapterItemType(int position) {
        Object obj = mDatas.get(position);
        if(obj instanceof HomePageData.JumpUrlArrBean){
            return SHOW_MENU;
        } else if(obj instanceof HomePageData.UserDataBean){
            return SHOW_HOME_MINE_INFO;
        } else if(obj instanceof List){
            List list = (List) obj;
            if (list.size() >= 1){
                Object object = list.get(0);
                if (object instanceof HomePageData.BannerBean){
                    return SHOW_BANNER;
                }else if(object instanceof HomePageData.SubjectListBean){
                    return SHOW_HOT_VIDEO;
                }else if(object instanceof HomePageData.ShopListBean){
                    return SHOW_ORTHRE_VIDEO;
                }
            }
        }
        return -1;
    }

    public void addData(HomePageData homeBean) {

        List<HomePageData.ShopListBean> shopList = homeBean.getShopList();
        if (shopList != null){
            for (int i = 0; i < mDatas.size(); i++) {
                Object o = mDatas.get(i);
                if (o instanceof List){
                    if (((List)o).size() > 0){
                        Object o1 = ((List) o).get(0);
                        if (o1 instanceof HomePageData.ShopListBean){
                            ((List)o).addAll(homeBean.getShopList());
                            notifyDataSetChanged();
                        }
                    }
                }
            }
        }
    }
}
