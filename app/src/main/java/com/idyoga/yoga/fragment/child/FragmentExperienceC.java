package com.idyoga.yoga.fragment.child;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.MainActivity;
import com.idyoga.yoga.activity.course.ExperienceCourseClassifyActivity;
import com.idyoga.yoga.activity.home.AppointmentIntroductionActivity;
import com.idyoga.yoga.activity.loading.SetCityActivity;
import com.idyoga.yoga.activity.search.SearchLeadActivity;
import com.idyoga.yoga.activity.shop.ShopDetailActivity;
import com.idyoga.yoga.activity.shop.ShopExperienceCourseActivity;
import com.idyoga.yoga.activity.web.YogaWebActivity;
import com.idyoga.yoga.adapter.PopupWindowAddressAdapter;
import com.idyoga.yoga.adapter.PopupWindowClassAdapter;
import com.idyoga.yoga.adapter.PopupWindowSexAdapter;
import com.idyoga.yoga.adapter.PopupWindowTagAdapter;
import com.idyoga.yoga.adapter.ShopExperienceCourseAdapter;
import com.idyoga.yoga.adapter.ShopListTagAdapter;
import com.idyoga.yoga.adapter.SpecialAdapter;
import com.idyoga.yoga.base.BaseFragment;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.comm.AppContext;
import com.idyoga.yoga.common.adapter.CommonAdapter;
import com.idyoga.yoga.common.adapter.ViewHolder;
import com.idyoga.yoga.common.http.type1.HttpClient;
import com.idyoga.yoga.common.http.type1.HttpResponseHandler;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.listener.OnVerticalScrollListener;
import com.idyoga.yoga.model.AddressBean;
import com.idyoga.yoga.model.AdvertiBean;
import com.idyoga.yoga.model.CityBean;
import com.idyoga.yoga.model.CourseHeadInfo;
import com.idyoga.yoga.model.EquityCourseShopListBean;
import com.idyoga.yoga.model.HomePageData;
import com.idyoga.yoga.model.PopupWindowItemBean;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.ShopCourseListBeanV2;
import com.idyoga.yoga.model.ShopTag;
import com.idyoga.yoga.model.realm.ShopExperienceCourseClass;
import com.idyoga.yoga.model.realm.ShopExperienceCourseClassTag;
import com.idyoga.yoga.model.realm.ShopExperienceHeadBeanV2;
import com.idyoga.yoga.model.realm.ShopExperienceTag;
import com.idyoga.yoga.utils.BannerGlideImageLoader;
import com.idyoga.yoga.utils.ShowAdverti;
import com.idyoga.yoga.utils.ViewUtil;
import com.idyoga.yoga.utils.YogaViewUtil;
import com.idyoga.yoga.utils.sp.ExperienceClassTagUtil;
import com.idyoga.yoga.view.BgDarkPopupWindow;
import com.idyoga.yoga.view.RoundDrawable;
import com.idyoga.yoga.view.YogaBanner;
import com.idyoga.yoga.view.decoration.HorizontalDividerItemDecoration;
import com.sina.weibo.sdk.utils.UIUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;
import okhttp3.Call;
import okhttp3.Request;
import vip.devkit.library.ListUtil;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.library.StringUtil;
import vip.devkit.view.common.banner.BannerConfig;
import vip.devkit.view.common.banner.BannerV;
import vip.devkit.view.common.banner.listener.OnBannerListener;

import static com.idyoga.yoga.comm.AppContext.mContext;

/**
 * 文 件 名: FragmentExperienceC
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/7/18
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class FragmentExperienceC extends BaseFragment {

    @BindView(R.id.gv_list)
    GridView mGvList;
    @BindView(R.id.tv_type)
    TextView mTvType;
    @BindView(R.id.tv_sort)
    TextView mTvSort;
    @BindView(R.id.tv_filter)
    TextView mTvFilter;
    @BindView(R.id.ll_msg)
    LinearLayout mLlMsg;
    @BindView(R.id.abl_layout)
    AppBarLayout mAblLayout;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.cl_layout)
    CoordinatorLayout mClLayout;
    @BindView(R.id.bv_view)
    YogaBanner mBvView;
    @BindView(R.id.srl_Refresh)
    SwipeRefreshLayout mSrlRefresh;
    @BindView(R.id.iv_type)
    ImageView mIvType;
    @BindView(R.id.ll_type_layout)
    LinearLayout mLlTypeLayout;
    @BindView(R.id.iv_sort)
    ImageView mIvSort;
    @BindView(R.id.ll_sort_layout)
    LinearLayout mLlSortLayout;
    @BindView(R.id.iv_filter)
    ImageView mIvFilter;
    @BindView(R.id.ll_filter_layout)
    LinearLayout mLlFilterLayout;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.ll_address)
    LinearLayout mLlAddress;
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.iv_search)
    ImageView mIvSearch;
    @BindView(R.id.ll_search_title)
    RelativeLayout mLlSearchTitle;
    Unbinder unbinder;
    @BindView(R.id.iv_address_select)
    ImageView mIvAddressSelect;
    @BindView(R.id.tv_address_select)
    TextView mTvAddressSelect;
    @BindView(R.id.ll_address_layout)
    LinearLayout mLlAddressLayout;
    @BindView(R.id.rlv_special)
    RecyclerView mRlvSpecial;
    @BindView(R.id.ll_head_banner)
    LinearLayout mLlHeadBanner;

    private String cityId = "";
    private String areaId = "";
    private String classifyType = "";
    private String classifyId = "";
    private String copyClassifyId = "";
    private String latitude = "23.16";
    private String longitude = "214.23";
    private int pageIndex = 1;
    private boolean isTop;
    private boolean isLoadMore = false, isLoadMoreTag = true, isLoad;
    private String tagType = "", labelClassifyId = "", status = "", sortType = "1";
    private List<String> mBvList = new ArrayList<>();
    private List<CourseHeadInfo.TagListBean> mTagListBeanList = new ArrayList<>();
    private List<EquityCourseShopListBean> mBeanList = new ArrayList<>();
    private CommonAdapter mHeadAdapter;
    private ShopExperienceCourseAdapter mCourseAdapter;
    private Bundle mBundle;
    private PopupWindowAddressAdapter mAddressAdapter;
    List<PopupWindowItemBean> mSortListBean;
    BgDarkPopupWindow mPopupWindow;
    PopupWindowSexAdapter mSexAdapter;
    PopupWindowTagAdapter mTagAdapter;
    PopupWindowClassAdapter mClassAdapter;
    int classTag = 0;
    int childTag = -1;
    int classChildTag = -1;
    private List<CourseHeadInfo.AreaListBean> mAreaList = new ArrayList<>();
    private List<CourseHeadInfo.LabelListBean> mLabelList = new ArrayList<>();
    private List<HomePageData.SubjectListBean> mSubjectList = new ArrayList<>();
    private List<CourseHeadInfo.LabelListBean.LabelBean> mChildLabelList = new ArrayList<>();
    private TextView mMTvAll;
    private TextView mTvBoy;
    private TextView mTvGirl;
    private TextView mTvSpike;
    private TextView mTvSure;
    private TextView mTvReset;
    private SpecialAdapter mSpecialAdapter;
    private ArrayList<View> mViews;
    private List<String> mTags = new ArrayList<>();
    private static String MTvAlltagType  = "1";
    private static String TvBoytagType   = "2";
    private static String TvGirltagType  = "3";
    private static String TvSpiketagType = "4";
    private ShopListTagAdapter mTagAdapter1;
    private ArrayList<ShopTag> mShopTags;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlSearchTitle)
                .keyboardEnable(true)
                .flymeOSStatusBarFontColor("#333333")
                .init();
    }

    @Override
    protected void initData() {
        super.initData();
        mSortListBean = new ArrayList<>();
        mSortListBean.add(new PopupWindowItemBean("推荐排序", false));
        mSortListBean.add(new PopupWindowItemBean("离我最近", false));
        mSortListBean.add(new PopupWindowItemBean("最早开课", false));
        mShopTags = new ArrayList<>();
        mShopTags.add(new ShopTag("已认证",false,1));
        mShopTags.add(new ShopTag("课程丰富",false,2));
        mShopTags.add(new ShopTag("有团购",false,3));
        mShopTags.add(new ShopTag("有秒杀",false,4));
        ShopExperienceHeadBeanV2 headBeanV2 = null;
        List<ShopExperienceCourseClass> classTags = null;
        headBeanV2 = ExperienceClassTagUtil.getClassData(mActivity);
        classTags = ExperienceClassTagUtil.getTagData(mActivity);
        setEmptyView(0);
        getHeadInfo();
        //getData();
        //        getShopDetailData();
    }

    @Override
    protected void onFirstVisible() {
        super.onFirstVisible();
        ShopExperienceHeadBeanV2 headBeanV2 = null;
        List<ShopExperienceCourseClass> classTags = null;
        headBeanV2 = ExperienceClassTagUtil.getClassData(mActivity);
        classTags = ExperienceClassTagUtil.getTagData(mActivity);
        setEmptyView(0);
        /*cityId = (String) SharedPreferencesUtils.getSP(mActivity, "cityId", "");
        latitude = (String) SharedPreferencesUtils.getSP(AppContext.getInstance(), "latitude", "");
        longitude = (String) SharedPreferencesUtils.getSP(AppContext.getInstance(), "longitude", "");*/
        getHeadInfo();
        getAdvertiInfo();
        //getData();
    }

    @Override
    protected void onVisible() {
        super.onVisible();
        if (isLoad) {
            showLoading("加载中...");
            setEmptyView(0);
            initEmpty(true);
            ShopExperienceHeadBeanV2 headBeanV2 = ExperienceClassTagUtil.getClassData(mActivity);
            getHeadInfo();
            //getData();
            //getClassTag();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Logcat.i("setUserVisibleHint:" + isVisibleToUser + "/" + isTop);
        //EventBus.getDefault().post(new PostResult("toTop", "0"));
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_experience_c;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        setEmptyView(0);
        mGvList.setAdapter(mHeadAdapter = new CommonAdapter<CourseHeadInfo.TagListBean>(getContext(), mTagListBeanList, R.layout.item_experience_menu) {
            @Override
            public void convert(ViewHolder holder, CourseHeadInfo.TagListBean courseHeadInfo, int position) {
                View tagRootView = holder.getView(R.id.ll_itemView);
                tagRootView.setTag(position);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tagRootView.getLayoutParams();
                if (position < 5) {
                    layoutParams.setMargins(0, YogaViewUtil.dp2px(mActivity, 12), 0, 0);
                } else {
                    layoutParams.setMargins(0, 0, 0, YogaViewUtil.dp2px(mActivity, 12));
                }
                tagRootView.setLayoutParams(layoutParams);
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
        });
        mBvView.clearFocus();
        mRvList.clearFocus();
        mGvList.setFocusable(false);
        mGvList.setFocusableInTouchMode(false);
        mRvList.setFocusable(false);
        mRvList.setLayoutManager(new LinearLayoutManager(mActivity));
        mCourseAdapter = new ShopExperienceCourseAdapter(R.layout.item_shop_course_list_v2, mBeanList);
        View emptyView = View.inflate(mActivity, R.layout.layout_card_child_empty, null);
        ((TextView) emptyView.findViewById(R.id.tv_hint)).setText("暂无该分类下的权益课");
        mCourseAdapter.setEmptyView(emptyView);
        mRvList.addItemDecoration(new HorizontalDividerItemDecoration.Builder(mActivity)
                .color(Color.parseColor("#f4f4f4"))
                .size(YogaViewUtil.dp2px(mActivity, 10))
                .build());
        mRvList.setAdapter(mCourseAdapter);
        Logcat.i("------------------initView----------------------");
        //专题
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRlvSpecial.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mSpecialAdapter = new SpecialAdapter(getActivity());
        mRlvSpecial.setAdapter(mSpecialAdapter);
        mRlvSpecial.setNestedScrollingEnabled(false);
        mSpecialAdapter.setDatas(mSubjectList);
    }

    @Override
    protected void setListener() {
        super.setListener();
        mSrlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initEmpty(false);
                setEmptyView(0);
                classTag = 0;
                childTag = -1;
                classChildTag = -1;
                pageIndex = 1;
                getData();
                mSrlRefresh.setRefreshing(false);
            }
        });
        mAblLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (mSrlRefresh == null)
                    return;
                mSrlRefresh.setEnabled(verticalOffset >= 0 ? true : false);
            }
        });
        //
        mGvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Bundle bundle = new Bundle();
                if (position == 9 && mTagListBeanList.get(position).getName().equals("查看更多")) {
                    startActivityWithExtras(ExperienceCourseClassifyActivity.class, bundle);
                } else {
                    bundle.putString("classId", mTagListBeanList.get(position).getId() + "");
                    bundle.putString("className", mTagListBeanList.get(position).getName() + "");
                    startActivityWithExtras(ShopExperienceCourseActivity.class, bundle);
                }
            }
        });
        mRvList.addOnScrollListener(new OnVerticalScrollListener() {
            @Override
            public void onScrolledToTop() {
                super.onScrolledToTop();
                isTop = true;
            }

            @Override
            public void onScrolledDown(int dx, int dy) {
                super.onScrolledDown(dx, dy);
                Logcat.i("---------------   dx:" + dx + "/" + dy);
                if (dy > YogaViewUtil.getScreenWidth(mActivity) / 2) {
                    isTop = false;
                }
            }

            @Override
            public void onScrolledDown() {
                super.onScrolledDown();
            }

            @Override
            public void onScrolledToBottom() {
                super.onScrolledToBottom();
                /*if (isLoadMore && isLoadMoreTag) {
                    isLoadMoreTag = false;
                    pageIndex += 1;
                    getData();
                }*/
                isLoadMore = true;
                pageIndex += 1;
                getData();
            }
        });
        mCourseAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("shopId", mBeanList.get(position).getShop_id() + "");
                bundle.putString("name", mBeanList.get(position).getName() + "");
                startActivityWithExtras(ShopDetailActivity.class, bundle);
                //AppointmentIntroductionActivity 课程详情
            }
        });
    }


    public void initBannerView(final List<String> list) {
        mBvView.setBannerStyle(1);
        mBvView.setImageLoader(new BannerGlideImageLoader());
        mBvView.setImages(list);
        mBvView.isAutoPlay(true);
        mBvView.setDelayTime(3000);
        mBvView.setIndicatorGravity(BannerConfig.CENTER);
        mBvView.setYogaClipToPadding(false);
        mBvView.setLeftRightPadding(ViewUtil.dp2px(getContext(), 20));
        mBvView.setLeftRightMargin(ViewUtil.dp2px(getContext(), 10));
        //设置viewPager 滑动过程中的page大小变化
        mBvView.setBannerPageTransformer(new ViewPager.PageTransformer() {

            private static final float MIN_SCALE = 0.85f;

            @Override
            public void transformPage(View page, float position) {
                if (position < -1 || position > 1) {
                    page.setScaleY(MIN_SCALE);

                } else if (position <= 1) { // [-1,1]  [-1,0] [0,1]
                    if (position < 0) {
                        float scaleX = 1 + 0.15f * position;
                        page.setScaleY(scaleX);
                    } else {
                        float scaleX = 1 - 0.15f * position;
                        page.setScaleY(scaleX);
                    }
                }
            }
        });
        if (list.size() > 0) {
            mBvView.start();
            mBvView.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Bundle bundle = new Bundle();
                    bundle.putString("getUrl", list.get(position));
                    startActivityWithExtras(YogaWebActivity.class, bundle);
                }
            });
        }
    }

    /**
     * get data
     */
    private void getData() {
        Map map = new HashMap();
        cityId = (String) SharedPreferencesUtils.getSP(mActivity, "cityId", "");
        latitude = (String) SharedPreferencesUtils.getSP(AppContext.getInstance(), "latitude", "");
        longitude = (String) SharedPreferencesUtils.getSP(AppContext.getInstance(), "longitude", "");
        map.put("cityId", cityId + "");
        map.put("areaId", areaId + ""); //区域id
        map.put("sortType", sortType + ""); //排序 1:推荐排序 2:离我最近 3:最早开课
        map.put("tagType", tagType + ""); //1已认证，2课程丰富，3有团购，4有秒杀
        map.put("lat", latitude + "");
        map.put("long", longitude + "");
        map.put("classifyId", classifyId + "");//功效筛选
        map.put("classifyType", classifyType + "");//功效筛选类型
        map.put("page", pageIndex + "");
        map.put("size", 10 + "");

        Logcat.e("获取店铺下权益课 提交的参数：" + map.toString());
        OkHttpUtils.get().url(ApiConstants.Urls.EQUITY_COURSE_SHOP_LISTS)
                .params(map).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Logcat.e("获取店铺下权益课：" + e);
                dismissLoading();
                setEmptyView(2);
            }

            @Override
            public void onResponse(String response, int id) {
                dismissLoading();
                Logcat.e("获取店铺下权益课：" + response);
                ResultBean resultBean = JSON.parseObject(response, ResultBean.class);
                if (resultBean.getCode().equals("1")) {
                    List<EquityCourseShopListBean> beanList = JSON.parseArray(resultBean.getData(), EquityCourseShopListBean.class);
                    initShopData(beanList);
                }
            }
        });
    }

    /**
     * 获取广告信息
     */
    private void getAdvertiInfo() {
        HashMap<String, String> map = new HashMap<>();
        map.put("cityId", cityId + "");
        map.put("type", "1");
        OkHttpUtils.get().url(ApiConstants.Urls.YOGA_ADVERTI).params(map).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(String response, int id) {
                ResultBean resultBean = JSON.parseObject(response, ResultBean.class);
                if (resultBean.getCode().equals("1")) {
                    AdvertiBean adverti = JSON.parseObject(resultBean.getData(), AdvertiBean.class);
                    ShowAdverti.getInstance().showAdverti(adverti, getActivity(), "ShopList");
                }
            }
        });
    }

    /**
     * 获取头部
     */
    public void getHeadInfo() {
        Map map = new HashMap();
        cityId = (String) SharedPreferencesUtils.getSP(mActivity, "cityId", "");
        map.put("cityId", cityId + "");//
        //        OkHttpUtils.get().url(ApiConstants.Urls.COURSE_HEAD_INFO).params(map).build().execute(new StringCallback() {
        OkHttpUtils.get().url(ApiConstants.Urls.EQUITY_COURSE_PAGE_DATA).params(map).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoading();
            }

            @Override
            public void onResponse(String response, int id) {
                Logcat.e("权益课课 getHeadInfo :" + cityId + "/" + response);
                dismissLoading();
                response = response.replace("\"data\":[]", "\"data\":null");
                ResultBean resultBean = JSON.parseObject(response, ResultBean.class);
                if ("1".equals(resultBean.getCode())) {
                    CourseHeadInfo courseHeadInfo = JSON.parseObject(resultBean.getData(), CourseHeadInfo.class);
                    initViewData(courseHeadInfo);
                }
            }
        });
    }

    /**
     * @param beanList
     */
    //    private void initShopData(List<ShopCourseListBeanV2> beanList) {
    private void initShopData(List<EquityCourseShopListBean> beanList) {
        if (pageIndex == 1 && ListUtil.isEmpty(beanList)) {
            setEmptyView(1);
            mBeanList.clear();
            mCourseAdapter.notifyDataSetChanged();
            return;
        }
        mCourseAdapter.removeAllFooterView();
        View view = null;
        if (beanList.size() == 10) {
            view = View.inflate(mActivity, R.layout.view_loading_footer, null);
        } else if (beanList.size() < 10) {
            isLoadMore = false;
            if (beanList.size() < 2) {
                mRvList.setBackgroundColor(Color.parseColor("#f2f2f2"));
            } else {
                view = View.inflate(mActivity, R.layout.view_list_footer, null);
            }
        }
        if (view != null) {
            mCourseAdapter.addFooterView(view);
        }
        pageIndex++;
        isLoadMoreTag = true;
        mBeanList.addAll(beanList);
        mCourseAdapter.notifyDataSetChanged();
    }


    /**
     * @param bean 更新view
     */
    //    private void initViewData(final ShopExperienceHeadBeanV2 bean) {
    private void initViewData(final CourseHeadInfo bean) {
        if (bean == null) {
            return;
        }
        mBvList.clear();
        mTagListBeanList.clear();
        mSubjectList.clear();
        //banner
        List<CourseHeadInfo.BannerListBean> bannerList = bean.getBannerList();
        for (CourseHeadInfo.BannerListBean bannerBean : bannerList) {
            mBvList.add(bannerBean.getImage_url());
        }
        //tag
        List<CourseHeadInfo.TagListBean> tagList = bean.getTagList();
        if (!ListUtil.isEmpty(tagList)) {
            if (tagList.size() >= 7) {
                CourseHeadInfo.TagListBean moreBean = new CourseHeadInfo.TagListBean();
                moreBean.setName("查看更多");
                moreBean.setId(0);
                mTagListBeanList.addAll(tagList);
                mTagListBeanList.add(9, moreBean);
            } else {
                mTagListBeanList.addAll(tagList);
            }
        }
        //专题
        mSubjectList.addAll(bean.getSubjectList());
        //条件 功效 筛选
        List<CourseHeadInfo.LabelListBean> labelList = bean.getLabelList();
        CourseHeadInfo.LabelListBean labelListBean = new CourseHeadInfo.LabelListBean();
        labelListBean.setName("全部功效");
        labelListBean.setSelect(true);
        mLabelList.add(labelListBean);
        mLabelList.addAll(labelList);

        //条件 地区 筛选
        List<CourseHeadInfo.AreaListBean> areaList = bean.getAreaList();
        if (areaList != null && !areaList.isEmpty()) {
            CourseHeadInfo.AreaListBean listBean = new CourseHeadInfo.AreaListBean();
            listBean.setName("全部");
            mAreaList.add(listBean);
            mAreaList.addAll(areaList);
        }
        initShopData(bean.getShopList());
        mSpecialAdapter.notifyDataSetChanged();
        if (mTagListBeanList.size() > 0 && mHeadAdapter != null) {
            mHeadAdapter.notifyDataSetChanged();
        }
        if (mBvList.size() > 0) {
            initBannerView(mBvList);
        }
        ExperienceClassTagUtil.setClassData(mActivity, bean);
    }

    @OnClick({R.id.ll_type_layout, R.id.ll_sort_layout, R.id.ll_filter_layout, R.id.ll_address,
            R.id.iv_search, R.id.et_search, R.id.ll_address_layout})
    public void onViewClicked(View view) {
        menuToTop();
        switch (view.getId()) {
            case R.id.ll_type_layout:
                showMenu(view, "1");
                break;
            case R.id.ll_sort_layout:
                showMenu(view, "2");
                break;
            case R.id.ll_filter_layout:
                showMenu(view, "3");
                break;
            case R.id.ll_address:
                mBundle = new Bundle();
                mBundle.putString("fromTag", "other");
                startActivityWithExtras(SetCityActivity.class, mBundle);
                break;
            case R.id.et_search:
                startActivityWithoutExtras(SearchLeadActivity.class);
                break;
            case R.id.iv_search:
                startActivityWithoutExtras(SearchLeadActivity.class);
                break;
            case R.id.ll_address_layout:
                showMenu(view, "0");
                break;
        }
    }

    //隐藏banner等选择框上面的view
    public void menuToTop() {
        //int h = YogaViewUtil.getViewHeight(mBvView) + YogaViewUtil.getViewHeight(mGvList);
        int h = YogaViewUtil.getViewHeight(mLlHeadBanner);
        Logcat.e("滑动距离：" + h);
        //mAblLayout.setExpanded(false);
        CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) mAblLayout.getLayoutParams()).getBehavior();
        behavior.onNestedPreScroll(mClLayout, mAblLayout, mRvList, 0, h, new int[]{0, 0}, 1);
        behavior.onStopNestedScroll(mClLayout, mAblLayout, mRvList, 1);
    }

    public void showMenu(View view, String tag) {
        View contentView;
        //        resetView();
        if (tag.equals("1")) {
            contentView = View.inflate(mActivity, R.layout.layout_shop_experience_class_tag, null);
            contentView.setBackgroundColor(Color.WHITE);
            showClassTag(contentView, view);
        } else if (tag.equals("2")) {
            contentView = View.inflate(mActivity, R.layout.layout_popupwindow_sort, null);
            contentView.setBackgroundColor(Color.WHITE);
            showSort(contentView, view);
        } else if (tag.equals("3")) {
            contentView = View.inflate(mActivity, R.layout.layout_popupwindow_sex, null);
            showSex(contentView, view);
        } else if (tag.equals("0")) {
            contentView = View.inflate(mActivity, R.layout.layout_popupwindow_sort, null);
            contentView.setBackgroundColor(Color.WHITE);
            showAddress(contentView, view);
        }
    }

    //显示选择地址
    private void showAddress(View contentView, View view) {
        final ListView listView = contentView.findViewById(R.id.lv_list);
        mAddressAdapter = new PopupWindowAddressAdapter(mContext, mAreaList, R.layout.item_select_d);
        listView.setAdapter(mAddressAdapter);
        if (mTvSort != null) {
            mTvAddressSelect.setTextColor(getResources().getColor(R.color.select_login_mode));
            mIvAddressSelect.setImageDrawable(getResources().getDrawable(R.drawable.condition_select));
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //                initEmpty(false);
                mAddressAdapter.setVisibility(i);
                mAddressAdapter.notifyDataSetChanged();
                CourseHeadInfo.AreaListBean areaListBean = mAreaList.get(i);
                if (areaListBean.getName().equals("全部")) { //全部 areaId 为空
                    areaId = "";
                } else {
                    areaId = areaListBean.getId() + "";
                }
                if (mTvSort != null) {
                    mTvAddressSelect.setText(areaListBean.getName());
                    mTvAddressSelect.setTextColor(getResources().getColor(R.color.select_login_mode));
                    mIvAddressSelect.setImageDrawable(getResources().getDrawable(R.drawable.condition_select));
                }
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
                pageIndex = 1;
                mBeanList.clear();
                getData();
            }
        });
        mPopupWindow = new BgDarkPopupWindow(mActivity, contentView);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setHeight(getListHeight(mAddressAdapter, listView));
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        mPopupWindow.setDarkStyle(-1);
        mPopupWindow.setDarkColor(Color.parseColor("#a0000000"));
        mPopupWindow.resetDarkPosition();
        mPopupWindow.darkBelow(view);
        mPopupWindow.showAsDropDown(view, 0, 0);
    }

    private void showClassTag(View contentView, View view) {
        Logcat.i("childTag:" + childTag + "/" + classTag);
        final ListView listView = contentView.findViewById(R.id.lv_p_list);
        ListView childListView = contentView.findViewById(R.id.lv_c_list);
        mClassAdapter = new PopupWindowClassAdapter(mContext, mLabelList, R.layout.item_select_g);
        mTagAdapter = new PopupWindowTagAdapter(mContext, mChildLabelList, R.layout.item_select_d);
        listView.setAdapter(mClassAdapter);
        childListView.setAdapter(mTagAdapter);
        if (mTvType != null && mIvType != null) {
            mTvType.setTextColor(getResources().getColor(R.color.select_login_mode));
            mIvType.setImageDrawable(getResources().getDrawable(R.drawable.condition_select));
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //                initEmpty(false);
                CourseHeadInfo.LabelListBean labelListBean = mLabelList.get(i);
                if (labelListBean.getName().equals("全部功效")) {
                    classifyId = "";
                    copyClassifyId = "";
                    classifyType = "";
                } else {
                    classifyType = "1";
                    classifyId = labelListBean.getId() + "";
                    copyClassifyId = labelListBean.getId() + "";
                }

                for (int j = 0; j < mLabelList.size(); j++) {
                    CourseHeadInfo.LabelListBean bean = mLabelList.get(j);
                    if (j == i) {
                        bean.setSelect(true);
                    } else {
                        bean.setSelect(false);
                    }
                }
                if (mTvType != null) {
                    mTvType.setText(labelListBean.getName());
                }
                Logcat.i("-------------------------------------classifyId--" + classifyId + "---copyClassifyId-" + copyClassifyId + "--copyClassifyId---" + copyClassifyId);

                if (!ListUtil.isEmpty(mLabelList.get(i).getLabel())) {
                    mChildLabelList.clear();
                    CourseHeadInfo.LabelListBean.LabelBean labelBean = new CourseHeadInfo.LabelListBean.LabelBean();
                    labelBean.setName("全部子分类");
                    mChildLabelList.add(labelBean);
                    mChildLabelList.addAll(mLabelList.get(i).getLabel());
                    for (int j = 0; j < mChildLabelList.size(); j++) {
                        mChildLabelList.get(j).setSelect(false);
                    }
                } else {
                    pageIndex = 1;
                    mBeanList.clear();
                    getData();
                    mPopupWindow.dismiss();
                }
                mClassAdapter.notifyDataSetChanged();
                mTagAdapter.notifyDataSetChanged();
            }
        });
        childListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //                initEmpty(false);
                for (int j = 0; j < mChildLabelList.size(); j++) {
                    CourseHeadInfo.LabelListBean.LabelBean bean = mChildLabelList.get(j);
                    bean.setSelect(false);
                    if (j == i) {
                        bean.setSelect(true);
                    }
                }
                CourseHeadInfo.LabelListBean.LabelBean labelBean = mChildLabelList.get(i);
                if (labelBean.getName().equals("全部子分类")) {
                    classifyId = copyClassifyId;
                    classifyType = "1";
                } else {
                    classifyId = labelBean.getId() + "";
                    classifyType = "2";
                }
                Logcat.i("-----------------------------------child--classifyId--" + classifyId + "---copyClassifyId-" + copyClassifyId + "--copyClassifyId---" + copyClassifyId);
                labelClassifyId = labelBean.getId() + "";
                if (mTvType != null && mIvType != null) {
                    mTvType.setText(labelBean.getName());
                    mTvType.setTextColor(getResources().getColor(R.color.select_login_mode));
                    mIvType.setImageDrawable(getResources().getDrawable(R.drawable.condition_select));
                }
                pageIndex = 1;
                mBeanList.clear();
                getData();
                mPopupWindow.dismiss();
                mTagAdapter.notifyDataSetChanged();
            }
        });

        mPopupWindow = new BgDarkPopupWindow(mActivity, contentView);
        if (YogaViewUtil.getScreenHeight(mActivity) / 2.5 < getListHeight(mClassAdapter, listView)) {
            mPopupWindow.setHeight((int) (YogaViewUtil.getScreenHeight(mActivity) / 2.5));
        } else {
            mPopupWindow.setHeight(getListHeight(mClassAdapter, listView));
        }
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
        final ListView listView = contentView.findViewById(R.id.lv_list);
        mSexAdapter = new PopupWindowSexAdapter(mContext, mSortListBean, R.layout.item_select_d);
        listView.setAdapter(mSexAdapter);
        if (mTvSort != null) {
            mTvSort.setTextColor(getResources().getColor(R.color.select_login_mode));
            mIvSort.setImageDrawable(getResources().getDrawable(R.drawable.condition_select));
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mSexAdapter.setVisibility(i);
                mSexAdapter.notifyDataSetChanged();
                if (mSortListBean.get(i).getName().equals("离我最近")) {
                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.initPermissionR();
                    //initEmpty(false);
                    sortType = "2";
                } else if (mSortListBean.get(i).getName().equals("最早开课")) {
                    //initEmpty(false);
                    sortType = "3";
                } else {
                    //initEmpty(false);//推荐排序
                    sortType = "1";
                }
                pageIndex = 1;
                mBeanList.clear();
                getData();
                if (mTvSort != null) {
                    mTvSort.setText(mSortListBean.get(i).getName());
                    mTvSort.setTextColor(getResources().getColor(R.color.select_login_mode));
                    mIvSort.setImageDrawable(getResources().getDrawable(R.drawable.condition_select));
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
        mPopupWindow.showAsDropDown(view, 0, 0);
    }

    public void showSex(View contentView, View view) {
        RecyclerView rlvTags = contentView.findViewById(R.id.rlv_tag);
        mTvSure = contentView.findViewById(R.id.tv_sure);
        mTvReset = contentView.findViewById(R.id.tv_reset);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 4);
        rlvTags.setLayoutManager(layoutManager);
        mTvSure = contentView.findViewById(R.id.tv_sure);
        mTvReset = contentView.findViewById(R.id.tv_reset);
        mTagAdapter1 = new ShopListTagAdapter(R.layout.item_shop_list_tag_layout, mShopTags,getContext());
        rlvTags.setAdapter(mTagAdapter1);
        mTagAdapter1.setListener(new ShopListTagAdapter.OnTagClickListener() {
            @Override
            public void onTagClickListener(ShopTag item) {
                int num = 0;
                int index  = -1;
                //StringBuffer nameSb = new StringBuffer();
                for (int i = 0; i < mShopTags.size(); i++) {
                    ShopTag shopTag = mShopTags.get(i);
                    if (shopTag.isSelect()){
                        num += 1;
                        index = i;
                    }
                }
                if (num == 1){
                    ShopTag shopTag = mShopTags.get(index);
                    tagType = String.valueOf(shopTag.getTag());
                }else{
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < mShopTags.size(); i++) {
                        ShopTag shopTag = mShopTags.get(i);
                        if (shopTag.isSelect()){
                            sb.append(shopTag.getTag());
                            if (i < mShopTags.size() - 1){
                                sb.append(",");
                            }
                        }
                    }
                    tagType = sb.toString();
                }
//                mTvFilter.setText(nameSb.toString());
                if (tagType.isEmpty()){
                    resetSelectTitle(0);
                }else{
                    resetSelectTitle(1);
                }
            }
        });
        mTvSure.setOnClickListener(mClickListener);
        mTvReset.setOnClickListener(mClickListener);
        if (mTvFilter != null) {
            mTvFilter.setTextColor(getResources().getColor(R.color.select_login_mode));
            mIvFilter.setImageDrawable(getResources().getDrawable(R.drawable.vip_icon_select));
        }
        mPopupWindow = new BgDarkPopupWindow(mActivity, contentView);
        mPopupWindow.setHeight(YogaViewUtil.dp2px(mActivity, 152));
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

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tv_sure:
                    if (mPopupWindow != null) {
                        mPopupWindow.dismiss();
                    }
                    pageIndex = 1;
                    mBeanList.clear();
                    getData();
                    break;
                case R.id.tv_reset:
                    tagType = "";
                    List<ShopTag> data = mTagAdapter1.getData();
                    for (ShopTag tag:data){
                        tag.setSelect(false);
                    }
                    mTagAdapter1.notifyDataSetChanged();
                    resetSelectTitle(0);
                    break;
            }
        }
    };

    private void resetSelectTitle(int action) {
        if (action == 0){
            mTvFilter.setTextColor(getResources().getColor(R.color.text_color));
            mIvFilter.setImageDrawable(getResources().getDrawable(R.drawable.vip_icon_default));
        }else{
            mTvFilter.setTextColor(getResources().getColor(R.color.select_login_mode));
            mIvFilter.setImageDrawable(getResources().getDrawable(R.drawable.vip_icon_select));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 初始化 shopList
     *
     * @param isAll false 就只重置 shopList
     */
    public void initEmpty(boolean isAll) {
        sortType = "";
        tagType = "";
        labelClassifyId = "";
        pageIndex = 1;
        status = "";
        resetView();
        if (isAll) {
            mBvList.clear();
            mTagListBeanList.clear();
            mBeanList.clear();
            if (mCourseAdapter != null) {
                mCourseAdapter.notifyDataSetChanged();
            }
        } else {
            mBeanList.clear();
            if (mCourseAdapter != null) {
                mCourseAdapter.notifyDataSetChanged();
            }
        }
    }

    private void resetView() {
        mTvAddressSelect.setText("地区");
        mTvType.setText("功效");
        mTvSort.setText("推荐排序");
        mTvFilter.setText("筛选");
        Logcat.i("-------------------------------------------------------------------------initEmpty---");
        mTvType.setTextColor(getResources().getColor(R.color.text_color_3));
        mTvSort.setTextColor(getResources().getColor(R.color.text_color_3));
        mTvFilter.setTextColor(getResources().getColor(R.color.text_color_3));
        mTvAddressSelect.setTextColor(getResources().getColor(R.color.text_color_3));
        mIvAddressSelect.setImageDrawable(getResources().getDrawable(R.drawable.condition_default));
        mIvType.setImageDrawable(getResources().getDrawable(R.drawable.condition_default));
        mIvSort.setImageDrawable(getResources().getDrawable(R.drawable.condition_default));
        mIvFilter.setImageDrawable(getResources().getDrawable(R.drawable.vip_icon_default));
    }

    @Override
    public void onEvent(PostResult postResult) {
        super.onEvent(postResult);
        if (postResult.getTag().equals("setAddress")) {
            CityBean.RecentCityBean cityBean = (CityBean.RecentCityBean) postResult.getResult();
            cityId = cityBean.getId() + "";
            if (getUserVisibleHint()) {
                initEmpty(true);
                showLoading("加载中...");
                setEmptyView(0);
                getHeadInfo();
                //getClassTag();
                //getData();
                isLoad = false;
            } else {
                isLoad = true;
            }
        } else if (postResult.getTag().equals("HOME_TOP") && getUserVisibleHint()) {
            Logcat.i("TOP:" + this.getClass().getName());
            /*mRvList.scrollToPosition(0);
            mRvList.post(new Runnable() {
                @Override
                public void run() {
                    mRvList.scrollToPosition(0);
                }
            });*/
            //展开
            //mAblLayout.setExpanded(true, true);
            //EventBus.getDefault().post(new PostResult("toTop", "0"));
        } else if (postResult.getTag().equals("lbs")) {
            //离我最近
            /*latitude = (String) SharedPreferencesUtils.getSP(AppContext.getInstance(), "latitude", "");
            longitude = (String) SharedPreferencesUtils.getSP(AppContext.getInstance(), "longitude", "");*/
            pageIndex = 1;
            mBeanList.clear();
            getData();
        } else if (postResult.getTag().equals("advertiDetail")) {
            //跳去广告页
            Bundle bundle = new Bundle();
            bundle.putString("getUrl", (String) postResult.getResult());
            startActivityWithExtras(YogaWebActivity.class, bundle);
        }
    }

    public void setEmptyView(int type) {
        View emptyView = null;
        if (type == 0) {/**加载中...*/
            emptyView = View.inflate(mActivity, R.layout.layout_common_loading, null);
        } else if (type == 1) {/**空数据...*/
            emptyView = View.inflate(mActivity, R.layout.layout_card_child_empty, null);
            ((TextView) emptyView.findViewById(R.id.tv_hint)).setText("该分类下暂无课程");
        } else if (type == 2) {/**网络异常...*/
            emptyView = View.inflate(mActivity, R.layout.yoga_layout_net_error, null);
            emptyView.findViewById(R.id.tv_retry).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    initEmpty(false);
                    setEmptyView(0);
                    getData();
                }
            });
        } else if (type == 3) {

        }
        if (mCourseAdapter != null && emptyView != null) {
            mCourseAdapter.setEmptyView(emptyView);
            mCourseAdapter.notifyDataSetChanged();
        }
    }
}
