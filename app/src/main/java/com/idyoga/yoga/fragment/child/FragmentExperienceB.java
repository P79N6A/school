package com.idyoga.yoga.fragment.child;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.course.ExperienceCourseClassifyActivity;
import com.idyoga.yoga.activity.shop.ShopDetailActivity;
import com.idyoga.yoga.activity.shop.ShopExperienceCourseActivity;
import com.idyoga.yoga.activity.web.YogaWebActivity;
import com.idyoga.yoga.adapter.PopupWindowClassAdapter;
import com.idyoga.yoga.adapter.PopupWindowSexAdapter;
import com.idyoga.yoga.adapter.PopupWindowTagAdapter;
import com.idyoga.yoga.adapter.ShopCourseListAdapter;
import com.idyoga.yoga.adapter.base.BaseDelegateAdapter;
import com.idyoga.yoga.base.BaseFragment;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.comm.AppContext;
import com.idyoga.yoga.common.http.type2.ICommonViewUi;
import com.idyoga.yoga.common.http.type2.presenter.ICommonRequestPresenter;
import com.idyoga.yoga.common.http.type2.presenter.impl.CommonRequestPresenterImpl;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.listener.OnItemClickListener;
import com.idyoga.yoga.listener.OnVerticalScrollListener;
import com.idyoga.yoga.model.CityBean;
import com.idyoga.yoga.model.PopupWindowItemBean;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.ShopCourseListBeanV2;
import com.idyoga.yoga.model.realm.ShopExperienceCourseClass;
import com.idyoga.yoga.model.realm.ShopExperienceCourseClassTag;
import com.idyoga.yoga.model.realm.ShopExperienceHeadBeanV2;
import com.idyoga.yoga.model.realm.ShopExperienceTag;
import com.idyoga.yoga.utils.BannerGlideImageLoader;
import com.idyoga.yoga.utils.YogaViewUtil;
import com.idyoga.yoga.view.BgDarkPopupWindow;
import com.idyoga.yoga.view.YogaLayoutManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import vip.devkit.library.DensityUtil;
import vip.devkit.library.ListUtil;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.view.common.banner.BannerConfig;
import vip.devkit.view.common.banner.BannerV;
import vip.devkit.view.common.banner.listener.OnBannerListener;

import static com.idyoga.yoga.comm.AppContext.mContext;

/**
 * 文 件 名: FragmentExperience
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/5/17
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class FragmentExperienceB extends BaseFragment implements ICommonViewUi {
    @BindView(R.id.srl_Refresh)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.iv_top)
    ImageView mIvTop;
    DelegateAdapter adapters;
    VirtualLayoutManager mVirtualLayoutManager;
    List<DelegateAdapter.Adapter> mAdapters;
    List<String> mBvList = new ArrayList<>();
    List<ShopExperienceTag> mTagListBeanList = new ArrayList<>();
    List<ShopCourseListBeanV2> mShopBeanList = new ArrayList<>();
    BaseDelegateAdapter mBannerAdapter, mMenuAdapter, mHotListAdapter, mListMenuAdAdapter;
    ShopCourseListAdapter mListAdapter;
    ShopExperienceHeadBeanV2 mExperienceHeadBean;
    Unbinder unbinder;
    private String cityId = "";
    private String latitude = "23.16";
    private String longitude = "214.23";
    private String mShopId = "";
    private int pageIndex = 1;
    private boolean isLoadMore = true,isLoad;
    private String isSex, labelClassifyId, status, type;
    private String classifyId;


    @Override
    protected ICommonRequestPresenter initICommonViewUi() {
        return iCommonRequestPresenter = new CommonRequestPresenterImpl(mActivity, this);
    }

    @Override
    protected void initData() {
        mShopId = (String) SharedPreferencesUtils.getSP(mActivity, "shopId", "");
        cityId = (String) SharedPreferencesUtils.getSP(mActivity, "cityId", "");
        latitude = (String) SharedPreferencesUtils.getSP(AppContext.getInstance(), "latitude", "");
        longitude = (String) SharedPreferencesUtils.getSP(AppContext.getInstance(), "longitude", "");
        mSortListBean = new ArrayList<>();
        mSortListBean.add(new PopupWindowItemBean("离我最近", false));
        mSortListBean.add(new PopupWindowItemBean("即将开课", false));
    }


    /**
     * 第一次显示加载数据
     */
    @Override
    protected void onFirstVisible() {
        super.onFirstVisible();
        Logcat.i("第一次显示");
        showLoading("加载中...");
        toRequest(ApiConstants.EventTags.HOME_EXPERIENCE);
        toRequest(ApiConstants.EventTags.HOME_EXPERIENCE_SHOP);
        toRequest(ApiConstants.EventTags.HOME_EXPERIENCE_TAG);
    }

    @Override
    protected void onVisible() {
        super.onVisible();
        Logcat.i("当前显示");
        if (isLoad){
            initEmpty();
            showLoading("加载中...");
            toRequest(ApiConstants.EventTags.HOME_EXPERIENCE);
            toRequest(ApiConstants.EventTags.HOME_EXPERIENCE_SHOP);
            toRequest(ApiConstants.EventTags.HOME_EXPERIENCE_TAG);
        }
    }

    @Override
    protected YogaLayoutManager initLayoutManager() {
        return YogaLayoutManager.wrap(mRvList);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_experience;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mLayoutManager.showLoading();
        initEmpty();
        initVLayout();
    }


    private void initVLayout() {
        mVirtualLayoutManager = new VirtualLayoutManager(mActivity);
        mRvList.setLayoutManager(mVirtualLayoutManager);
        mRvList.setBackgroundColor(getResources().getColor(R.color.white));
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        mRvList.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(1, 5);
        viewPool.setMaxRecycledViews(2, 5);
        viewPool.setMaxRecycledViews(3, 10);
        viewPool.setMaxRecycledViews(4, 1);
        adapters = new DelegateAdapter(mVirtualLayoutManager, true);
        initAllTypeView();
        adapters.setAdapters(mAdapters);
        mRvList.setAdapter(adapters);
        mBannerAdapter.notifyDataSetChanged();
    }


    private void initAllTypeView() {
        mAdapters = new LinkedList<>();
        initBannerView();
        initMenuView();
        initListMenuView();
        initHotView();
    }

    private void initBannerView() {
        mBannerAdapter = new BaseDelegateAdapter(mActivity, new LinearLayoutHelper(), R.layout.layout_banner, 0, 1) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                Logcat.i("BannerView:" + mBvList.size());
                BannerV mBanner = holder.getView(R.id.bv_view);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mBanner.getLayoutParams();
                layoutParams.height = DensityUtil.dp2px(mActivity, 120);
                mBanner.setLayoutParams(layoutParams);
                mBanner = holder.getView(R.id.bv_view);
                mBanner.setBannerStyle(1);
                mBanner.setImageLoader(new BannerGlideImageLoader());
                mBanner.setImages(mBvList);
                mBanner.isAutoPlay(true);
                mBanner.setDelayTime(3000);
                mBanner.setIndicatorGravity(BannerConfig.CENTER);
                if (mBvList.size() > 0) {
                    mBanner.start();
                    mBanner.setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(int position) {
                            Bundle bundle = new Bundle();
                            bundle.putString("getUrl", mBvList.get(position));
                            startActivityWithExtras(YogaWebActivity.class, bundle);
                        }
                    });
                }
            }
        };
        mAdapters.add(mBannerAdapter);
    }

    private void initMenuView() {
        GridLayoutHelper layoutHelper = new GridLayoutHelper(4);
        layoutHelper.setAutoExpand(false);
        layoutHelper.setBgColor(Color.parseColor("#ffffff"));
        layoutHelper.setMargin(25,
                DensityUtil.dp2px(mActivity, 3),
                25, DensityUtil.dp2px(mActivity, 15));
        mMenuAdapter = new BaseDelegateAdapter(mActivity, layoutHelper, R.layout.item_experience_menu, 0, 2) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                ImageView imageView = holder.getView(R.id.iv_img);
                if (position == 7 && mTagListBeanList.get(position).getName().equals("查看更多")) {
                    holder.setImageResource(R.id.iv_img, R.drawable.icon_menu_more);
                } else {
                    Glide.with(mActivity)
                            .load(mTagListBeanList.get(position).getImage_url())
                            .into(imageView);
                }
                holder.setText(R.id.tv_menu_name, mTagListBeanList.get(position).getName());
            }
        };
        mAdapters.add(mMenuAdapter);
    }

    TextView mTvType, mTvSort, mTvFlter;

    private void initListMenuView() {
        mListMenuAdAdapter = new BaseDelegateAdapter(mActivity, new StickyLayoutHelper(), R.layout.item_experience_list_menu, 0, 3) {
            OnItemClickListener mOnItemClickListener;

            @Override
            public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
                mOnItemClickListener = onItemClickListener;
            }

            @Override
            public void onBindViewHolder(BaseViewHolder holder, final int position) {
                super.onBindViewHolder(holder, position);
                mTvType = holder.getView(R.id.tv_type);
                mTvType.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mOnItemClickListener != null) {
                            mOnItemClickListener.onItemClick(1, view, position);
                        }
                    }
                });
                mTvSort = holder.getView(R.id.tv_sort);
                mTvSort.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mOnItemClickListener != null) {
                            mOnItemClickListener.onItemClick(2, view, position);
                        }
                    }
                });
                mTvFlter = holder.getView(R.id.tv_filter);
                mTvFlter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mOnItemClickListener != null) {
                            mOnItemClickListener.onItemClick(3, view, position);
                        }
                    }
                });
            }
        };
        mAdapters.add(mListMenuAdAdapter);
    }

    private void initHotView() {
        LinearLayoutHelper layoutHelper = new LinearLayoutHelper();
        layoutHelper.setDividerHeight(20);
        layoutHelper.setBgColor(Color.parseColor("#f5f5f5"));
        mListAdapter = new ShopCourseListAdapter(mContext, layoutHelper, mShopBeanList, R.layout.item_shop_course_list_v2, 4);
        mListAdapter.setAdapter(adapters);
        mAdapters.add(mListAdapter);
    }

    /**
     * @param bean 更新view
     */
    private void initViewData(ShopExperienceHeadBeanV2 bean) {
        if (bean == null) {
            return;
        } else {
        }
        mBvList.clear();
        mTagListBeanList.clear();
        Logcat.e("size:" + bean.getTagList().size() + "/007");
        for (int i = 0; i < bean.getBannerList().size(); i++) {
            mBvList.add(bean.getBannerList().get(i).getImage_url());
        }
        if (!ListUtil.isEmpty(bean.getTagList())) {
            if (bean.getTagList().size() >= 7) {
               ShopExperienceTag moreBean = new ShopExperienceTag();
                moreBean.setName("查看更多");
                moreBean.setId(0);
                mTagListBeanList.addAll(bean.getTagList());
                mTagListBeanList.add(7, moreBean);
                mMenuAdapter.setCount(8);
            } else {
                mTagListBeanList.addAll(bean.getTagList());
                mMenuAdapter.setCount(bean.getTagList().size());
            }
        }
        mListMenuAdAdapter.setCount(1);
        if (mBvList.size() > 0) {
            mBannerAdapter.setCount(1);
        }
        mMenuAdapter.notifyDataSetChanged();
        mBannerAdapter.notifyDataSetChanged();
        mListMenuAdAdapter.notifyDataSetChanged();
    }

    /**
     * 初始化list page
     */
    void initEmpty() {
        status = "";
        type = "";
        isSex = "";
        labelClassifyId = "";
        pageIndex = 1;
        mBvList.clear();
        mTagListBeanList.clear();
        mShopBeanList.clear();

        if (mBannerAdapter != null) {
            mBannerAdapter.setCount(0);
            mBannerAdapter.notifyDataSetChanged();
        }
        if (mListMenuAdAdapter != null) {
            mListMenuAdAdapter.setCount(0);
            mListMenuAdAdapter.notifyDataSetChanged();
        }
        if (mMenuAdapter != null) {
            mMenuAdapter.setCount(0);
            mMenuAdapter.notifyDataSetChanged();
        }
        if (mListAdapter != null) {
            mListAdapter.notifyDataSetChanged();
        }
    }


    /**
     * 初始化 shopList
     */
    public void initShopList() {
        type = "";
        isSex = "";
        labelClassifyId = "";
        pageIndex = 1;
        status = "";
        mTvType.setText("全部");
        mTvSort.setText("排序");
        mTvFlter.setText("筛选");
        mTvType.setTextColor(getResources().getColor(R.color.text_color_3));
        mTvSort.setTextColor(getResources().getColor(R.color.text_color_3));
        mTvFlter.setTextColor(getResources().getColor(R.color.text_color_3));
        mShopBeanList.clear();
        if (mListAdapter != null) {
            mListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void toRequest(int eventTag) {
        Map map = new HashMap();
        if (eventTag == ApiConstants.EventTags.HOME_EXPERIENCE) {
            map.put("cityId", cityId + "");
            map.put("shopId", mShopId + "");
            map.put("lat", latitude + "");
            map.put("long", longitude + "");
            map.put("page", 1 + "");
            map.put("size", 10 + "");
            Logcat.i("权益课 菜单  首页提交的参数：" + map.toString() + "/" + ApiConstants.Urls.HOME_EXPERIENCE_V2);
            iCommonRequestPresenter.request(eventTag, mActivity, ApiConstants.Urls.HOME_EXPERIENCE_V2, map);
        } else if (eventTag == ApiConstants.EventTags.HOME_EXPERIENCE_SHOP) {
            map.put("cityId", cityId + "");
            map.put("shopId", mShopId + "");
            map.put("lat", latitude + "");
            map.put("long", longitude + "");
            map.put("page", pageIndex + "");
            map.put("size", 15 + "");
            map.put("isSex", isSex + "");/**性别筛选*/
            map.put("type", type + "");/**排序*/
            map.put("labelClassifyId", labelClassifyId + "");
            map.put("status", status + "");
            Logcat.i("权益课SHOP 首页 提交的参数：" + map.toString() + "/" + ApiConstants.Urls.HOME_EXPERIENCE_SHOP_2);
            iCommonRequestPresenter.request(eventTag, mActivity, ApiConstants.Urls.HOME_EXPERIENCE_SHOP_2, map);
        } else if (eventTag == ApiConstants.EventTags.HOME_EXPERIENCE_TAG) {
            map.put("cityId", cityId + "");
            Logcat.i("分类ID找到标签 首页 提交的参数：" + map.toString() + "/");
            iCommonRequestPresenter.request(eventTag, mActivity, ApiConstants.Urls.SHOP_EXPERIENCE_CLASS_TAG, map);
        }
    }

    private void initShopData(List<ShopCourseListBeanV2> beanList) {
        Logcat.e("刷新操作：" + beanList.size());
        if (pageIndex == 1 && ListUtil.isEmpty(beanList)) {
            mLayoutManager.showEmpty();
        } else {
            mLayoutManager.showContent();
        }
        mShopBeanList.addAll(beanList);
        mListAdapter.notifyDataSetChanged();
        adapters.notifyDataSetChanged();
    }

    @Override
    public void getRequestData(int eventTag, String result) {
        dismissLoading();
        Logcat.i("eventTag：" + eventTag + "/" + result);
        ResultBean resultBean = JSON.parseObject(result, ResultBean.class);
        if (resultBean.getCode().equals("1")) {
            if (eventTag == ApiConstants.EventTags.HOME_EXPERIENCE_TAG) {
                List<ShopExperienceCourseClass> beanList = JSON.parseArray(resultBean.getData(), ShopExperienceCourseClass.class);
                initClassTagData(beanList);
            } else if (eventTag == ApiConstants.EventTags.HOME_EXPERIENCE_SHOP) {
                List<ShopCourseListBeanV2> beanList = JSON.parseArray(resultBean.getData(), ShopCourseListBeanV2.class);
                initShopData(beanList);
            } else if (eventTag == ApiConstants.EventTags.HOME_EXPERIENCE) {
                mExperienceHeadBean = JSON.parseObject(resultBean.getData(), ShopExperienceHeadBeanV2.class);
                if (mExperienceHeadBean != null) {
                    initViewData(mExperienceHeadBean);
                }
            }
        }
    }


    @Override
    public void onRequestFailureException(int eventTag, String msg) {
        dismissLoading();
    }



    @Override
    protected void setListener() {
        super.setListener();
        mListMenuAdAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(final int type, final View view, int position) {
                mVirtualLayoutManager.scrollToPosition(9);
                mVirtualLayoutManager.scrollToPositionWithOffset(9, 0);
                mVirtualLayoutManager.setStackFromEnd(false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showMenu(view, type + "");
                    }
                }, 0);
            }
        });
        mMenuAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int type, View view, int position) {
                Bundle bundle = new Bundle();
                if (position == 7 && mTagListBeanList.get(position).getName().equals("查看更多")) {
                    startActivityWithExtras(ExperienceCourseClassifyActivity.class, bundle);
                } else {
                    bundle.putString("classId", mTagListBeanList.get(position).getId() + "");
                    bundle.putString("className", mTagListBeanList.get(position).getName() + "");
                    startActivityWithExtras(ShopExperienceCourseActivity.class, bundle);
                }
            }
        });
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initEmpty();
                toRequest(ApiConstants.EventTags.HOME_EXPERIENCE);
                toRequest(ApiConstants.EventTags.HOME_EXPERIENCE_SHOP);
                toRequest(ApiConstants.EventTags.HOME_EXPERIENCE_COURSE);
                mRefreshLayout.setRefreshing(false);
            }
        });
        mRvList.addOnScrollListener(new OnVerticalScrollListener() {
            @Override
            public void onScrolledToTop() {
                super.onScrolledToTop();
                mIvTop.setVisibility(View.GONE);
            }

            @Override
            public void onScrolledUp() {
                super.onScrolledUp();
                mIvTop.setVisibility(View.GONE);
            }

            @Override
            public void onScrolledDown() {
                super.onScrolledDown();
                mIvTop.setVisibility(View.VISIBLE);
            }

            @Override
            public void onScrolledToBottom() {
                super.onScrolledToBottom();
                mIvTop.setVisibility(View.VISIBLE);
                Logcat.i("瑜伽馆加载更多");
                if (isLoadMore) {
                    ++pageIndex;
                    toRequest(ApiConstants.EventTags.HOME_EXPERIENCE_SHOP);
                }
            }
        });
        mListAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int type, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("shopId", mShopBeanList.get(position).getId() + "");
                bundle.putString("name", mShopBeanList.get(position).getName() + "");
                bundle.putString("getUrl", mShopBeanList.get(position).getUrl());
                startActivityWithExtras(ShopDetailActivity.class, bundle);
            }
        });
    }

    private void initClassTagData(List<ShopExperienceCourseClass> beanList) {
        if (!ListUtil.isEmpty(beanList)) {
            mParentListBean.addAll(beanList);
        }
    }

    public void showMenu(View view, String tag) {
        View contentView;
        if (tag.equals("1")) {
            contentView = View.inflate(mActivity, R.layout.layout_shop_experience_class_tag, null);
            contentView.setBackgroundColor(Color.WHITE);
            showClassTag(contentView, view);
        } else if (tag.equals("2")) {
            contentView = new ListView(mActivity);
            contentView.setBackgroundColor(Color.WHITE);
            showSort(contentView, view);
        } else if (tag.equals("3")) {
            contentView = View.inflate(mActivity, R.layout.layout_popupwindow_sex, null);
            showSex(contentView, view);
        }
    }

    List<PopupWindowItemBean> mSortListBean;
    BgDarkPopupWindow mPopupWindow;
    PopupWindowSexAdapter mSexAdapter;
    List<ShopExperienceCourseClass> mParentListBean = new ArrayList<>();
    List<ShopExperienceCourseClassTag> childListBean = new ArrayList<>();
    PopupWindowTagAdapter mTagAdapter;
    PopupWindowClassAdapter mClassAdapter;
    int classTag = 0;

    private void showClassTag(View contentView, View view) {
        Logcat.e(mParentListBean.size() + "/");
        if (!ListUtil.isEmpty(mParentListBean) && !ListUtil.isEmpty(mParentListBean.get(classTag).getLabel())) {
            childListBean.clear();
            childListBean.addAll(mParentListBean.get(classTag).getLabel());
        }
        final ListView listView = contentView.findViewById(R.id.lv_p_list);
        ListView childListView = contentView.findViewById(R.id.lv_c_list);
        //mClassAdapter = new PopupWindowClassAdapter(mContext, mParentListBean, R.layout.item_setting_city);
        //mTagAdapter = new PopupWindowTagAdapter(mContext, childListBean, R.layout.item_setting_city);
        //listView.setAdapter(mClassAdapter);
        //childListView.setAdapter(mTagAdapter);
        if (!ListUtil.isEmpty(mParentListBean) && !ListUtil.isEmpty(mParentListBean.get(classTag).getLabel())) {
            listView.performItemClick(listView.getAdapter().getView(classTag, null, null), classTag, listView.getItemIdAtPosition(classTag));
            listView.smoothScrollToPosition(classTag);
            listView.post(new Runnable() {
                @Override
                public void run() {
                    listView.smoothScrollToPosition(classTag);
                }
            });
            //mClassAdapter.setVisibility(classTag);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //mClassAdapter.setVisibility(i);
                classTag = i;
                if (!ListUtil.isEmpty(mParentListBean.get(i).getLabel())) {
                    childListBean.clear();
                    childListBean.addAll(mParentListBean.get(i).getLabel());
//                    mTagAdapter.notifyDataSetChanged();
                } else {
                    initShopList();
                    status = "2";
                    labelClassifyId = mParentListBean.get(i).getId() + "";
                    mTvType.setText(mParentListBean.get(i).getName());
                    mTvType.setTextColor(getResources().getColor(R.color.theme_1));
                    toRequest(ApiConstants.EventTags.HOME_EXPERIENCE_SHOP);
                    mPopupWindow.dismiss();
                }
            }
        });
        childListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                mTagAdapter.setVisibility(i);
                initShopList();
                status = "1";
                labelClassifyId = childListBean.get(i).getId() + "";
                mTvType.setText(childListBean.get(i).getName());
                mTvType.setTextColor(getResources().getColor(R.color.theme_1));
                toRequest(ApiConstants.EventTags.HOME_EXPERIENCE_SHOP);
                mPopupWindow.dismiss();
            }
        });

        mPopupWindow = new BgDarkPopupWindow(mActivity, contentView);
        /*if (YogaViewUtil.getScreenHeight(mActivity) / 2.5 < getListHeight(mClassAdapter, listView)) {
            mPopupWindow.setHeight((int) (YogaViewUtil.getScreenHeight(mActivity) / 2.5));
        } else {
            mPopupWindow.setHeight(getListHeight(mClassAdapter, listView));
        }*/
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        mPopupWindow.setDarkStyle(-1);
        mPopupWindow.setDarkColor(Color.parseColor("#a0000000"));
        mPopupWindow.resetDarkPosition();
        mPopupWindow.darkBelow(view);
        mPopupWindow.showAsDropDown(view, 0, 0);
    }

    public void showSort(View contentView, View view) {
        final ListView listView = (ListView) contentView;
        mSexAdapter = new PopupWindowSexAdapter(mContext, mSortListBean, R.layout.item_setting_city);
        listView.setAdapter(mSexAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mSexAdapter.setVisibility(i);
                mSexAdapter.notifyDataSetChanged();
                if (mSortListBean.get(i).getName().equals("离我最近")) {
                    initShopList();
                    type = "1";
                    toRequest(ApiConstants.EventTags.HOME_EXPERIENCE_SHOP);
                } else {
                    initShopList();
                    type = "2";
                    toRequest(ApiConstants.EventTags.HOME_EXPERIENCE_SHOP);
                }
                if (mTvSort != null) {
                    mTvSort.setText(mSortListBean.get(i).getName());
                    mTvSort.setTextColor(getResources().getColor(R.color.theme_1));
                }
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
            }
        });
        mPopupWindow = new BgDarkPopupWindow(mActivity, contentView);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setHeight(getListHeight(mSexAdapter, listView));
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        mPopupWindow.setDarkStyle(-1);
        mPopupWindow.setDarkColor(Color.parseColor("#a0000000"));
        mPopupWindow.resetDarkPosition();
        mPopupWindow.darkBelow(view);
        mPopupWindow.showAsDropDown(view, 0, 2);
    }

    public void showSex(View contentView, View view) {
        contentView.findViewById(R.id.tv_all).setOnClickListener(mClickListener);
        /*contentView.findViewById(R.id.tv_boy).setOnClickListener(mClickListener);
        contentView.findViewById(R.id.tv_girl).setOnClickListener(mClickListener);*/
        mPopupWindow = new BgDarkPopupWindow(mActivity, contentView);
        mPopupWindow.setHeight(YogaViewUtil.dp2px(mActivity, 50));
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        mPopupWindow.setDarkStyle(-1);
        mPopupWindow.setDarkColor(Color.parseColor("#a0000000"));
        mPopupWindow.resetDarkPosition();
        mPopupWindow.darkBelow(view);
        mPopupWindow.showAsDropDown(view, 0, 0);
    }

    private int getListHeight(BaseAdapter adapter, ListView listView) {
        if (adapter == null) {
            return 0;
        }
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        totalHeight = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));
        return totalHeight;
    }

    @Override
    public void onEvent(PostResult postResult) {
        super.onEvent(postResult);
        if (postResult.getTag().equals("setAddress")) {
            CityBean.RecentCityBean cityBean = (CityBean.RecentCityBean) postResult.getResult();
            cityId = cityBean.getId() + "";
            mShopId = cityBean.getShop_id() + "";
            if (getUserVisibleHint()) {
                initEmpty();
                showLoading("加载中...");
                initEmpty();
                toRequest(ApiConstants.EventTags.HOME_EXPERIENCE);
                toRequest(ApiConstants.EventTags.HOME_EXPERIENCE_SHOP);
                toRequest(ApiConstants.EventTags.HOME_EXPERIENCE_COURSE);
                isLoad = false;
            } else {
                isLoad = true;
            }
        }
    }

    @OnClick({R.id.iv_top})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_top:
                mRvList.scrollToPosition(0);
                break;
        }
    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                /*case R.id.tv_all:
                    if (mPopupWindow != null) {
                        mPopupWindow.dismiss();
                    }
                    initShopList();
                    if (mTvFlter != null) {
                        mTvFlter.setText("男女不限");
                    }
                    isSex = "0";
                    toRequest(ApiConstants.EventTags.HOME_EXPERIENCE_SHOP);
                    break;
                case R.id.tv_boy:
                    if (mPopupWindow != null) {
                        mPopupWindow.dismiss();
                    }
                    initShopList();
                    if (mTvFlter != null) {
                        mTvFlter.setText("只限男生");
                    }
                    isSex = "1";
                    toRequest(ApiConstants.EventTags.HOME_EXPERIENCE_SHOP);
                    break;
                case R.id.tv_girl:
                    if (mPopupWindow != null) {
                        mPopupWindow.dismiss();
                    }
                    initShopList();
                    if (mTvFlter != null) {
                        mTvFlter.setText("只限女生");
                    }
                    isSex = "2";
                    toRequest(ApiConstants.EventTags.HOME_EXPERIENCE_SHOP);
                    break;*/
            }
            if (mTvFlter != null) {
                mTvFlter.setTextColor(getResources().getColor(R.color.theme_1));
            }
        }
    };
}
