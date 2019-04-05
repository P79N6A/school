package com.idyoga.yoga.activity.shop;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.idyoga.yoga.R;
import com.idyoga.yoga.adapter.PopupWindowAddressAdapter;
import com.idyoga.yoga.adapter.PopupWindowSexAdapter;
import com.idyoga.yoga.adapter.ShopExperienceCourseAdapter;
import com.idyoga.yoga.adapter.ShopListTagAdapter;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.comm.AppContext;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.lbs.YogaLocationListener;
import com.idyoga.yoga.listener.OnVerticalScrollListener;
import com.idyoga.yoga.model.CourseHeadInfo;
import com.idyoga.yoga.model.EquityCourseShopListBean;
import com.idyoga.yoga.model.PopupWindowItemBean;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.ShopCourseListBeanV2;
import com.idyoga.yoga.model.ShopExperienceCourseTag;
import com.idyoga.yoga.model.ShopTag;
import com.idyoga.yoga.utils.PermissionUtil;
import com.idyoga.yoga.utils.ToastUtil;
import com.idyoga.yoga.utils.YogaViewUtil;
import com.idyoga.yoga.view.BgDarkPopupWindow;
import com.idyoga.yoga.view.MyGridItemDecoration;
import com.idyoga.yoga.view.YogaLayoutManager;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import vip.devkit.library.ListUtil;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.library.StringUtil;

import static com.idyoga.yoga.comm.AppContext.mContext;

/**
 * 文 件 名: ShopExperienceCourseActivity
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/21
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class ShopExperienceCourseActivity extends BaseActivity {

    @BindView(R.id.ll_title_back)
    LinearLayout mLlTitleBack;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.ll_common_layout)
    RelativeLayout mLlCommonLayout;
    @BindView(R.id.tab_view)
    TabLayout mTabView;
    @BindView(R.id.tv_sort)
    TextView mTvSort;
    @BindView(R.id.tv_filter)
    TextView mTvFilter;
    @BindView(R.id.ll_msg)
    LinearLayout mLlMsg;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.srl_Refresh)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.ll_content_layout)
    LinearLayout mLlContentLayout;
    @BindView(R.id.iv_sort)
    ImageView mIvSort;
    @BindView(R.id.iv_filter)
    ImageView mIvFilter;
    @BindView(R.id.ll_sort_layout)
    LinearLayout mLlSortLayout;
    @BindView(R.id.ll_filter_layout)
    LinearLayout mLlFilterLayout;
    @BindView(R.id.ll_address_layout)
    LinearLayout mllAddressLayout;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.iv_address)
    ImageView mIvAddress;

    private int pageIndex = 1;
    private boolean isLoadMore = true, isLoadMoreTag = true, isLoad = true, isTabTag = false;
    private String className, classId;
    private String cityId, latitude, longitude;
    private String isSex = "", type = "", labelClassifyId = "", status = "";
    //private List<ShopCourseListBeanV2> mBeanList = new ArrayList<>();
    private List<EquityCourseShopListBean> mBeanList = new ArrayList<>();
    private ShopExperienceCourseAdapter mAdapter;
    private List<PopupWindowItemBean> mSortListBean;
    //private List<ShopExperienceCourseTag> mTagBeanList;
    private List<CourseHeadInfo.TagListBean> mTagBeanList = new ArrayList<>();
    private BgDarkPopupWindow mPopupWindow;
    private PopupWindowSexAdapter mSexAdapter;
    private String areaId = "";
    private String sortType = "1";
    private String tagType = "1";
    private String classifyId = "";
    private String classifyType = "";
    private PopupWindowAddressAdapter mAddressAdapter;
    private List<CourseHeadInfo.AreaListBean> mAreaList = new ArrayList<>();
    private TextView mTvAll;
    private TextView mTvBoy;
    private TextView mTvGirl;
    private TextView mTvSpike;
    private TextView mTvSure;
    private TextView mTvReset;
    public static final int REQUEST_CAMERA_PERM = 101;
    private ShopListTagAdapter mTagAdapter1;
    private ArrayList<ShopTag> mShopTags;

    @Override
    protected void initData() {
        mTagBeanList = new ArrayList<>();
        mSortListBean = new ArrayList<>();
        mSortListBean.add(new PopupWindowItemBean("推荐排序", false));
        mSortListBean.add(new PopupWindowItemBean("离我最近", false));
        mSortListBean.add(new PopupWindowItemBean("最早开课", false));
        mShopTags = new ArrayList<>();
        mShopTags.add(new ShopTag("已认证",false,1));
        mShopTags.add(new ShopTag("课程丰富",false,2));
        mShopTags.add(new ShopTag("有团购",false,3));
        mShopTags.add(new ShopTag("有秒杀",false,4));
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            isTabTag = false;
            className = bundle.getString("className");
            classId = bundle.getString("classId");
            labelClassifyId = bundle.getString("classId");
            status = "2";
            getData();
        }

    }

    private void initViewData(CourseHeadInfo beanList) {
        //Logcat.e("pageIndex:" + pageIndex + " size:" + beanList.size());
        mTagBeanList.clear();
        mAreaList.clear();
        mBeanList.clear();

        //mTagBeanList
        List<CourseHeadInfo.TagListBean> tagList = beanList.getTagList();
        CourseHeadInfo.TagListBean tagListBean = new CourseHeadInfo.TagListBean();
        tagListBean.setName("全部");
        mTagBeanList.add(tagListBean);
        mTagBeanList.addAll(tagList);
        initTagViewData(mTagBeanList);
        //区域标签
        List<CourseHeadInfo.AreaListBean> areaList = beanList.getAreaList();
        CourseHeadInfo.AreaListBean areaListBean = new CourseHeadInfo.AreaListBean();
        areaListBean.setName("全部");
        mAreaList.add(areaListBean);
        mAreaList.addAll(areaList);

        //课程列表
        List<EquityCourseShopListBean> shopList = beanList.getShopList();

        mAdapter.removeAllFooterView();
        View view = null;
        if (shopList.size() == 15) {
            view = View.inflate(this, R.layout.view_loading_footer, null);
        } else if (shopList.size() < 15) {
            isLoadMore = false;
            if (shopList.size() < 2) {
                mRvList.setBackgroundColor(Color.parseColor("#f2f2f2"));
            } else {
                view = View.inflate(this, R.layout.view_list_footer, null);
            }
        }
        mBeanList.addAll(shopList);
        if (view != null) {
            mAdapter.addFooterView(view);
        }
        mAdapter.notifyDataSetChanged();
        pageIndex++;
        isLoad = true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlCommonLayout).init();
    }

    @Override
    protected YogaLayoutManager initLayoutManager() {
        return YogaLayoutManager.wrap(mRefreshLayout);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_experience_course;
    }

    @Override
    protected void initView() {
        mLayoutManager.setEmpty(R.layout.layout_card_child_empty);
        mLayoutManager.initView(YogaLayoutManager.EMPTY).setText(R.id.tv_hint,"暂无该分类下的课程");
        sexView = View.inflate(this, R.layout.layout_popupwindow_sex, null);
        mTvTitleText.setText(className);
        mLayoutManager.showLoading();
        mTabView.setVisibility(View.GONE);
        initRv();
    }

    private void initRv() {
        mAdapter = new ShopExperienceCourseAdapter(R.layout.item_shop_course_list_v2, mBeanList);
        MyGridItemDecoration decoration = new MyGridItemDecoration();
        decoration.setSize(20);
        decoration.setColor(Color.parseColor("#f4f4f4"));
        mRvList.addItemDecoration(decoration);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setAdapter(mAdapter);
    }

    public void initEmpty() {
        pageIndex = 1;
        isSex = "";
        type = "";
        labelClassifyId = classId;
        status = "2";
        mBeanList.clear();
        mTagBeanList.clear();
        isLoadMore = true;
        isLoad = true;
        if (mAdapter != null) {
            mAdapter.removeAllFooterView();
            mAdapter.notifyDataSetChanged();
        }
        mTvAddress.setText("地区");
        mTvSort.setText("排序");
        mTvFilter.setText("筛选");
        mTvAddress.setTextColor(Color.parseColor("#333333"));
        mTvSort.setTextColor(Color.parseColor("#333333"));
        mTvFilter.setTextColor(Color.parseColor("#333333"));
        mIvSort.setImageResource(R.drawable.condition_default);
        mIvSort.setImageResource(R.drawable.condition_default);
        mIvFilter.setImageResource(R.drawable.vip_icon_default);
    }

    public void initEmpty(boolean isALL) {
        pageIndex = 1;

        areaId = "";
        sortType = "1";
        tagType = "1";
        classifyId = "";
        classifyType = "";
        isLoadMore = true;
        isLoad = true;
        mBeanList.clear();
        mTvAddress.setText("地区");
        mTvSort.setText("排序");
        mTvFilter.setText("筛选");
        mTvAddress.setTextColor(Color.parseColor("#333333"));
        mTvSort.setTextColor(Color.parseColor("#333333"));
        mTvFilter.setTextColor(Color.parseColor("#333333"));
        mIvSort.setImageResource(R.drawable.condition_default);
        mIvSort.setImageResource(R.drawable.condition_default);
        mIvFilter.setImageResource(R.drawable.vip_icon_default);
        if (isALL == true) {
            mTagBeanList.clear();
            mTabView.removeAllTabs();
            mTabView.postInvalidate();
        }
        if (mAdapter != null) {
            mAdapter.removeAllFooterView();
            mAdapter.notifyDataSetChanged();
        }
    }

    int tag = 0;

    @Override
    protected void setListener() {
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("shopId", mBeanList.get(position).getShop_id() + "");
                bundle.putString("name", mBeanList.get(position).getName() + "");
                //bundle.putString("getUrl", mBeanList.get(position).getLogopath());
                startActivity(ShopDetailActivity.class, bundle);
            }
        });
        mRvList.addOnScrollListener(new OnVerticalScrollListener() {
            @Override
            public void onScrolledToBottom() {
                super.onScrolledToBottom();
                if (!ListUtil.isEmpty(mBeanList) && isLoadMore && isLoad) {
                    isLoad = false;
                    getData();
                }
            }
        });
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageIndex = 1;
                areaId = "";
                sortType = "1";
                tagType = "1";
                classifyId = "";
                classifyType = "";
                isLoadMore = true;
                isLoad = true;
                mBeanList.clear();
                mTvAddress.setText("地区");
                mTvSort.setText("排序");
                mTvFilter.setText("筛选");
                mTvAddress.setTextColor(Color.parseColor("#333333"));
                mTvSort.setTextColor(Color.parseColor("#333333"));
                mTvFilter.setTextColor(Color.parseColor("#333333"));
                mIvSort.setImageResource(R.drawable.condition_default);
                mIvSort.setImageResource(R.drawable.condition_default);
                mIvFilter.setImageResource(R.drawable.vip_icon_default);
                if (mAdapter!=null){
                    mAdapter.notifyDataSetChanged();
                }
                getData();
                mRefreshLayout.setRefreshing(false);
            }
        });
        mTabView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isTabTag = true;
            }
        });
        mTabView.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Logcat.e("--------------tab" + tab.getText().toString() + "/" + isTabTag + "/tag" + tag);
                if (tag != 0) {
                    isTabTag = true;
                }
                if (isTabTag == true) {
                    if (tab.getText().equals("全部")) {
                        Logcat.e("--------------tab1");
                        initEmpty(false);
                        classifyId = "";
                        classifyType = "";
                        labelClassifyId = classId;
                        status = "2";
                        getData();
                    } else {
                        Logcat.e("--------------tab2");
                        for (int i = 0; i < mAreaList.size(); i++) {
                            CourseHeadInfo.AreaListBean listBean = mAreaList.get(i);
                            if (tab.getText().equals(listBean.getName())){
                                classifyId = listBean.getId() + "";
                            }
                        }
                        classifyType = "1";
                        showLoading();
                        initEmpty(false);
                        if (mTagBeanList.get(tab.getPosition())!=null){
                            labelClassifyId = mTagBeanList.get(tab.getPosition()).getId() + "";
                        }else {
                            labelClassifyId=classId;
                        }
                        //status = "1";
                        getData();
                    }
                }
                tag++;
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    View sexView;

    @OnClick({R.id.ll_title_back, R.id.tv_sort, R.id.tv_filter, R.id.ll_sort_layout,
            R.id.ll_filter_layout,R.id.ll_address_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_back:
                finish();
                break;
            case R.id.tv_sort:
                showMenu(mTvFilter, "1");
                break;
            case R.id.tv_filter:
                showMenu(mTvFilter, "2");
                break;
            case R.id.ll_sort_layout:
                showMenu(mTvFilter, "1");
                break;
            case R.id.ll_filter_layout:
                showMenu(mTvFilter, "2");
                break;
            case R.id.ll_address_layout:
                showMenu(view, "0");
                break;
        }
    }

    public void showMenu(View view, String tag) {
        View contentView;
        if (tag.equals("1")) {
            contentView = View.inflate(this, R.layout.layout_popupwindow_sort, null);
            contentView.setBackgroundColor(Color.WHITE);
            showSort(contentView, view);
        } else if (tag.equals("2")) {
            contentView = View.inflate(this, R.layout.layout_popupwindow_sex, null);
            showSex(contentView, view);
        }else if("0".equals(tag)){
            contentView = View.inflate(this, R.layout.layout_popupwindow_sort, null);
            contentView.setBackgroundColor(Color.WHITE);
            showAddress(contentView, view);
        }
    }

    //显示选择地址
    private void showAddress(View contentView, View view) {
        final ListView listView = contentView.findViewById(R.id.lv_list);
        mAddressAdapter = new PopupWindowAddressAdapter(mContext, mAreaList, R.layout.item_select_d);
        listView.setAdapter(mAddressAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                initEmpty(false);
                mAddressAdapter.setVisibility(i);
                mAddressAdapter.notifyDataSetChanged();
                CourseHeadInfo.AreaListBean areaListBean = mAreaList.get(i);
                if (areaListBean.getName().equals("全部")){ //全部 areaId 为空
                    areaId = "";
                }else {
                    areaId = areaListBean.getId() + "";
                }

                if (mTvSort != null) {
                    mTvAddress.setText(areaListBean.getName());
                    mTvAddress.setTextColor(getResources().getColor(R.color.select_login_mode));
                    mIvAddress.setImageDrawable(getResources().getDrawable(R.drawable.condition_select));
                }
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
                getData();
            }
        });
        mPopupWindow = new BgDarkPopupWindow(this, contentView);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setHeight(getListHeight(mAddressAdapter,listView));
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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showLoading("加载中...");
                //mSexAdapter.setVisibility(i);
                mSexAdapter.notifyDataSetChanged();
                if (mSortListBean.get(i).getName().equals("离我最近")) {
                    initEmpty(false);
                    sortType = "2";
                    initPermissionR();
                } else if (mSortListBean.get(i).getName().equals("最早开课")){
                    initEmpty(false);
                    sortType = "3";
                    getData();
                } else {
                    initEmpty(false);//推荐排序
                    sortType = "1";
                    getData();
                }
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
        mPopupWindow = new BgDarkPopupWindow(this, contentView);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        mPopupWindow.setDarkStyle(-1);
        mPopupWindow.setDarkColor(Color.parseColor("#a0000000"));
        mPopupWindow.resetDarkPosition();
        mPopupWindow.darkBelow(view);
        mPopupWindow.setHeight(getListHeight(mSexAdapter, listView));
        mPopupWindow.showAsDropDown(view, 0, 0);
    }


    public void showSex(View contentView, View view) {
        RecyclerView rlvTags = contentView.findViewById(R.id.rlv_tag);
        mTvSure = contentView.findViewById(R.id.tv_sure);
        mTvReset = contentView.findViewById(R.id.tv_reset);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        rlvTags.setLayoutManager(layoutManager);
        mTvSure = contentView.findViewById(R.id.tv_sure);
        mTvReset = contentView.findViewById(R.id.tv_reset);
        mTagAdapter1 = new ShopListTagAdapter(R.layout.item_shop_list_tag_layout, mShopTags,this);
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
        mPopupWindow = new BgDarkPopupWindow(this, contentView);
        mPopupWindow.setHeight(YogaViewUtil.dp2px(this, 152));
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        mPopupWindow.setDarkStyle(-1);
        mPopupWindow.setDarkColor(Color.parseColor("#a0000000"));
        mPopupWindow.resetDarkPosition();
        mPopupWindow.darkBelow(view);
        mPopupWindow.showAsDropDown(view, 0, 0);
    }

    private void resetSelectTitle(int action) {
        if (action == 0){
            mTvFilter.setTextColor(getResources().getColor(R.color.text_color));
            mIvFilter.setImageDrawable(getResources().getDrawable(R.drawable.vip_icon_default));
        }else{
            mTvFilter.setTextColor(getResources().getColor(R.color.select_login_mode));
            mIvFilter.setImageDrawable(getResources().getDrawable(R.drawable.vip_icon_select));
        }
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

    private void getData() {
        cityId = (String) SharedPreferencesUtils.getSP(this, "cityId", "");
        latitude = (String) SharedPreferencesUtils.getSP(AppContext.getInstance(), "latitude", "");
        longitude = (String) SharedPreferencesUtils.getSP(AppContext.getInstance(), "longitude", "");
        Map map = new HashMap();
        map.put("cityId", cityId + "");
        map.put("areaId", areaId + "");
        map.put("sortType", sortType + "");
        map.put("tagType", tagType + "");
        map.put("lat", latitude + "");
        map.put("long", longitude + "");
        map.put("classifyId", classifyId + "");
        map.put("classifyType", classifyType + "");
        map.put("page", pageIndex + "");
        map.put("size", "15");

        OkHttpUtils.post().url(ApiConstants.Urls.EQUITY_COURSE_PAGE_DATA).params(map).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoading();
                mLayoutManager.showNetError();
            }

            @Override
            public void onResponse(String response, int id) {
                dismissLoading();
                mLayoutManager.showContent();
                ResultBean bean = JSON.parseObject(response, ResultBean.class);
                if (bean.getCode().equals("1")) {
                    CourseHeadInfo beanList = JSON.parseObject(bean.getData(), CourseHeadInfo.class);
                    initViewData(beanList);
                } else {
                    ToastUtil.showToast(bean.getMsg());
                }
            }
        });
    }

    private void initTagViewData(List<CourseHeadInfo.TagListBean> beanList) {
        for (int i = 0; i < mTagBeanList.size(); i++) {
            mTabView.addTab(mTabView.newTab().setText(mTagBeanList.get(i).getName() != null ? mTagBeanList.get(i).getName() : ""));
        }
        if (mTagBeanList.size()>0){
            mTabView.setVisibility(View.VISIBLE);
        }else {
            mTabView.setVisibility(View.GONE);
        }
    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //showLoading("加载中...");
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

    @AfterPermissionGranted(REQUEST_CAMERA_PERM)
    public void initPermissionR() {
        if (EasyPermissions.hasPermissions(this, PermissionUtil.permissions)) {
            initLocation();
        } else {
            ActivityCompat.requestPermissions(this, PermissionUtil.permissions, 100);
        }
    }

    LocationClient mLocationClient;
    /**
     * 定位
     */
    protected void initLocation() {
        mLocationClient = new LocationClient(this);
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("bd09ll");
        option.setScanSpan(1000);
        option.setOpenGps(true);
        option.setLocationNotify(true);
        option.setIgnoreKillProcess(false);
        option.SetIgnoreCacheException(false);
        option.setWifiCacheTimeOut(5 * 60 * 1000);
        option.setEnableSimulateGps(false);
        mLocationClient.setLocOption(option);
        mLocationClient.registerLocationListener(new YogaLocationListener());
        mLocationClient.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mLocationClient.stop();
            }
        }, 5000);
    }

    @Override
    public void onEvent(PostResult postResult) {
        super.onEvent(postResult);
        if(postResult.getTag().equals("lbs")){
            //离我最近
            latitude = (String) SharedPreferencesUtils.getSP(AppContext.getInstance(), "latitude", "");
            longitude = (String) SharedPreferencesUtils.getSP(AppContext.getInstance(), "longitude", "");
            getData();
        }
    }
}
