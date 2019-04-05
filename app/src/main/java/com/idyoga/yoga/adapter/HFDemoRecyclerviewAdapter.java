package com.idyoga.yoga.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.R;
import com.idyoga.yoga.holder.BannerHolder;
import com.idyoga.yoga.holder.BaseHolder;
import com.idyoga.yoga.holder.HomeMineInfoHolder;
import com.idyoga.yoga.holder.HotHolder;
import com.idyoga.yoga.holder.OrderHolder;
import com.idyoga.yoga.holder.SignHolder;
import com.idyoga.yoga.holder.TestHolder;
import com.idyoga.yoga.model.HomePageData;

import java.util.ArrayList;
import java.util.List;

import vip.devkit.library.Logcat;


/**
 * @author weilgu
 * @time 2019/3/11 9:55
 * @des 首页fragment 的recycler view 的 adapter
 */

public class HFDemoRecyclerviewAdapter extends BaseLoadMoreAdapter<Object,BaseHolder> {

    private Context mContext;
    private LayoutInflater mFrom;
    private static final int SHOW_BANNER = 0; //banner
    private static final int SHOW_MENU = 1;  // 快速问诊,预约,商城 ,抢
    private static final int SHOW_HOT_VIDEO = 2; // 专题活动
    private static final int SHOW_ORTHRE_VIDEO = 3; //猜你喜欢
    private static final int SHOW_HOME_MINE_INFO = 4; //用户信息,打卡等
    private static final int SHOW_LOAD_MORE = 5; //加载更多
    private boolean mIsShowLoadMore = false;

    public HFDemoRecyclerviewAdapter(Context context) {
        super(context);
        mContext = context;
        mFrom = LayoutInflater.from(mContext);
    }

    @Override
    protected BaseHolder childAdapterContentHolder(ViewGroup parent, int viewType) {
        /*if (viewType == SHOW_BANNER){
            return new BannerHolder(mFrom.inflate(R.layout.layout_banner,parent,false),mContext);
        }else if(viewType == SHOW_MENU){
            return new SignHolder(mFrom.inflate(R.layout.layout_home_menu,parent,false),mContext);
        }else if(viewType == SHOW_HOT_VIDEO){
            return new HotHolder(mFrom.inflate(R.layout.item_hot_video_layout,parent,false),mContext);
        }else if(viewType == SHOW_ORTHRE_VIDEO){
            return new OrderHolder(mFrom.inflate(R.layout.item_guess_layout,parent,false),mContext);
        }else if(viewType == SHOW_HOME_MINE_INFO){
            return new HomeMineInfoHolder(mFrom.inflate(R.layout.item_home_mine_info_layout,parent,false),mContext);
        }else if(viewType == SHOW_LOAD_MORE){
            return new TestHolder(mFrom.inflate(R.layout.a_item_test_layout,parent,false));
        }*/
        if(viewType == SHOW_HOT_VIDEO){
            return new HotHolder(mFrom.inflate(R.layout.item_hot_video_layout,parent,false),mContext);
        }else if(viewType == SHOW_ORTHRE_VIDEO){
            return new OrderHolder(mFrom.inflate(R.layout.item_guess_layout,parent,false),mContext);
        }else if(viewType == SHOW_LOAD_MORE){
            return new TestHolder(mFrom.inflate(R.layout.a_item_test_layout,parent,false));
        }
        return null;
    }

    @Override
    protected View loadMoreView(ViewGroup parent) {
        return mFrom.inflate(R.layout.a_item_test_layout,parent,false);
    }

    @Override
    protected void bindView(BaseHolder holder, int position) {
        int viewType = getItemViewType(position);
        Logcat.i("----------------onBindViewHolder  viewType = " + viewType);
        holder.bindView(mDatas.get(position));
        /*if (viewType == SHOW_BANNER){
            ((BannerHolder)holder).bindView((List<HomePageData.BannerBean>)mDatas.get(position));
        }else if(viewType == SHOW_MENU){
            ((SignHolder) holder).bindView((HomePageData.JumpUrlArrBean)mDatas.get(position));
        }else if(viewType == SHOW_HOT_VIDEO){
            ((HotHolder) holder).bindView((List<HomePageData.SubjectListBean>)mDatas.get(position));
        }else if(viewType == SHOW_ORTHRE_VIDEO){
            ((OrderHolder) holder).bindView((List<HomePageData.ShopListBean>)mDatas.get(position));
        }else if(viewType == SHOW_HOME_MINE_INFO){
            HomePageData.UserDataBean bean = (HomePageData.UserDataBean)mDatas.get(position);
            ((HomeMineInfoHolder) holder).bindView(bean);
        }*/
        /*if(viewType == SHOW_HOT_VIDEO){
            ((HotHolder) holder).bindView((List<HomePageData.SubjectListBean>)mDatas.get(position));
        }else if(viewType == SHOW_ORTHRE_VIDEO){
            ((OrderHolder) holder).bindView((List<HomePageData.ShopListBean>)mDatas.get(position));
        }*/
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

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() + 1 : 0;
    }

    public void setDatas(HomePageData mHomeBean) {
        /*if (mDatas != null && !mDatas.isEmpty()){
            mDatas.clear();
        }*/
        Logcat.i("-------------------------------------------setDatas ");
        List<HomePageData.BannerBean> banner = mHomeBean.getBanner();
        HomePageData.JumpUrlArrBean jumpUrlArr = mHomeBean.getJumpUrlArr();
        HomePageData.UserDataBean userData = mHomeBean.getUserData();
        List<HomePageData.SubjectListBean> subjectList = mHomeBean.getSubjectList();
        List<HomePageData.ShopListBean> shopList = mHomeBean.getShopList();
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

    public void addData(HomePageData homeBean){
        Logcat.i("-------------------------------------------addData ");
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

    public void setShowLoadMore(boolean isShowLoadMore) {
        this.mIsShowLoadMore = isShowLoadMore;

    }
}
