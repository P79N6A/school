package com.idyoga.yoga.activity.loading;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.MainActivity;
import com.idyoga.yoga.activity.lbs.SetCityUtil;
import com.idyoga.yoga.adapter.CitySettingAdapter2;
import com.idyoga.yoga.adapter.base.HeaderRecyclerAndFooterWrapperAdapter;
import com.idyoga.yoga.adapter.base.RvCommonAdapter;
import com.idyoga.yoga.adapter.base.ViewHolder;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.common.http.type1.HttpClient;
import com.idyoga.yoga.common.http.type1.HttpResponseHandler;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.listener.OnRvItemClickListener;
import com.idyoga.yoga.model.CityBean;
import com.idyoga.yoga.model.CitySettingHeaderBean;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.utils.ToastUtil;
import com.idyoga.yoga.utils.YogaViewUtil;
import com.idyoga.yoga.view.MyGridItemDecoration;
import com.idyoga.yoga.view.YogaLayoutManager;
import com.mcxtzhang.indexlib.IndexBar.bean.BaseIndexPinyinBean;
import com.mcxtzhang.indexlib.IndexBar.widget.IndexBar;
import com.mcxtzhang.indexlib.suspension.SuspensionDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import vip.devkit.library.ListUtil;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.library.StringUtil;

/**
 * 文 件 名: LoadingActivity
 * 创 建 人: By k
 * 创建日期: 2017/11/14 14:16
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注：
 */
public class SetCityActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.rl_head_layout)
    RelativeLayout mRlHeadLayout;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.ib_bar)
    IndexBar mIndexBar;
    @BindView(R.id.tvSideBarHint)
    TextView mTvSideBarHint;
    @BindView(R.id.tv_next)
    TextView mTvNext;
    private CityBean mCityBean;
    private LinearLayoutManager mManager;
    private SuspensionDecoration mDecoration;
    private List<CityBean.CityListBean> mBeanList = new ArrayList<>();
    private List<CityBean.HotCityBean> mHotCityBean = new ArrayList<>();
    private List<BaseIndexPinyinBean> mSourceDatas = new ArrayList<>();
    private List<CitySettingHeaderBean> mHeaderDatas = new ArrayList<>();
    private CitySettingAdapter2 mAllAdapter;
    private RvCommonAdapter mHotCityAdapter, mRecentAdapter;
    private HeaderRecyclerAndFooterWrapperAdapter mWrapperAdapter;
    boolean isRecent = false;
    String fromTag;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mRlHeadLayout).init();
    }

    @Override
    protected YogaLayoutManager initLayoutManager() {
        return YogaLayoutManager.wrap(mRvList);
    }

    @Override
    protected void initData() {
        mHeaderDatas.clear();
        mHotCityBean.clear();
        mSourceDatas.clear();
        mBeanList.clear();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            fromTag = bundle.getString("fromTag");
            if (!StringUtil.isEmpty(fromTag)) {
                isRecent = true;
            }
        }
        CityBean cityBean = SetCityUtil.getCityBean(this);
        if (cityBean == null) {
            Logcat.i("没有地址数据，重新获取");
            getData();
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_area_setting;
    }


    @Override
    protected void initView() {
        mTvNext.setEnabled(false);
        mLayoutManager.showLoading();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            fromTag = bundle.getString("fromTag");
            if (!StringUtil.isEmpty(fromTag)) {
                mIvBack.setVisibility(View.VISIBLE);
            } else {
                mIvBack.setVisibility(View.GONE);
            }
        }
        mRvList.setLayoutManager(mManager = new LinearLayoutManager(this));
        mRvList.addItemDecoration(mDecoration = new SuspensionDecoration(this, mSourceDatas)
                .setmTitleHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics()))
                .setColorTitleBg(Color.parseColor("#f2f2f2"))
                .setTitleFontSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()))
                .setColorTitleFont(Color.parseColor("#999999"))
                .setHeaderViewCount(mBeanList.size()));
        MyGridItemDecoration decoration = new MyGridItemDecoration(MyGridItemDecoration.VERTICAL);
        decoration.setColor(Color.parseColor("#F2F2F2"));
        decoration.setSize(YogaViewUtil.dp2px(this, 1));
        mRvList.addItemDecoration(decoration);
        if (isRecent) {
            List<CitySettingHeaderBean.CityBean> recentCity = new ArrayList<>();
            mHeaderDatas.add(new CitySettingHeaderBean(recentCity, "最近选择", "近"));
        }
        List<CitySettingHeaderBean.CityBean> hotCity = new ArrayList<>();
        mHeaderDatas.add(new CitySettingHeaderBean(hotCity, "热门城市", "热"));
        mSourceDatas.addAll(mHeaderDatas);
        mAllAdapter = new CitySettingAdapter2(this, R.layout.item_setting_city, mBeanList);
        mWrapperAdapter = new HeaderRecyclerAndFooterWrapperAdapter(mAllAdapter) {
            @Override
            protected void onBindHeaderHolder(ViewHolder holder, int headerPos, int layoutId, Object o) {
                switch (layoutId) {
                    case R.layout.item_rv:
                        final CitySettingHeaderBean headerBean = (CitySettingHeaderBean) o;
                        RecyclerView recyclerView = holder.getView(R.id.rl_list);
                        recyclerView.setAdapter(mHotCityAdapter = new RvCommonAdapter<CitySettingHeaderBean.CityBean>(mContext, R.layout.item_head_city, headerBean.getCityList()) {
                            @Override
                            public void convert(ViewHolder holder, CitySettingHeaderBean.CityBean cityBean) {
                                TextView textView = holder.getView(R.id.tv_name);
                                if (holder.getAdapterPosition() == headerBean.getCityList().size() - 1) {
                                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
                                    layoutParams.setMargins(0, YogaViewUtil.dp2px(mContext, 15), 0, YogaViewUtil.dp2px(mContext, 15));
                                    textView.setLayoutParams(layoutParams);
                                }
                                textView.setText(cityBean.getName());
                                if (cityBean.isSelect() == true) {
                                    textView.setTextColor(getResources().getColor(R.color.white));
                                    textView.setBackgroundResource(R.drawable.bg_city_select);
                                } else {
                                    textView.setTextColor(Color.parseColor("#333333"));
                                    textView.setBackgroundResource(R.drawable.bg_a_09);
                                }
                            }
                        });
                        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
                        mHotCityAdapter.setOnItemClickListener(new OnRvItemClickListener() {
                            @Override
                            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                                Logcat.i("头部点击了：" + position);
                                initAllCity();
                                selectIndex = position;
                                cityTag = 1;
                                setEnabled();
                                mHeaderDatas.get(0).getCityList().get(position).setSelect(true);
                                notifyDataSetChanged();
                                mWrapperAdapter.notifyDataSetChanged();
                            }
                        });
                        break;
                    case R.layout.item_rv_hot:
                        final CitySettingHeaderBean bean = (CitySettingHeaderBean) o;
                        RecyclerView mRvRecent = holder.getView(R.id.rl_list);
                        mRvRecent.setAdapter(mRecentAdapter = new RvCommonAdapter<CitySettingHeaderBean.CityBean>(mContext, R.layout.item_head_city, bean.getCityList()) {
                            @Override
                            public void convert(ViewHolder holder, CitySettingHeaderBean.CityBean cityBean) {
                                TextView textView = holder.getView(R.id.tv_name);
                                if (holder.getAdapterPosition() == bean.getCityList().size() - 1) {
                                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
                                    layoutParams.setMargins(0, YogaViewUtil.dp2px(mContext, 15), 0, YogaViewUtil.dp2px(mContext, 15));
                                    textView.setLayoutParams(layoutParams);
                                }
                                textView.setText(cityBean.getName());
                                if (cityBean.isSelect() == true) {
                                    textView.setTextColor(getResources().getColor(R.color.white));
                                    textView.setBackgroundResource(R.drawable.bg_city_select);
                                } else {
                                    textView.setTextColor(Color.parseColor("#333333"));
                                    textView.setBackgroundResource(R.drawable.bg_a_09);
                                }
                            }
                        });
                        mRvRecent.setLayoutManager(new GridLayoutManager(mContext, 3));
                        mRecentAdapter.setOnItemClickListener(new OnRvItemClickListener() {
                            @Override
                            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                                Logcat.i("默认点击了：" + position);
                                initAllCity();
                                setEnabled();
                                cityTag = 2;
                                selectIndex = position;
                                mHeaderDatas.get(1).getCityList().get(position).setSelect(true);
                                notifyDataSetChanged();
                                mWrapperAdapter.notifyDataSetChanged();
                            }
                        });
                        break;
                }

            }
        };
        if (isRecent) {
            mWrapperAdapter.setHeaderView(0, R.layout.item_rv, mHeaderDatas.get(0));
            mWrapperAdapter.setHeaderView(1, R.layout.item_rv_hot, mHeaderDatas.get(1));
        } else {
            mWrapperAdapter.setHeaderView(0, R.layout.item_rv, mHeaderDatas.get(0));
        }
        mIndexBar.setmPressedShowTextView(mTvSideBarHint)
                .setNeedRealIndex(false)
                .setmLayoutManager(mManager)
                .setHeaderViewCount(mWrapperAdapter.getHeaderViewCount() - mHeaderDatas.size());
        mRvList.setAdapter(mWrapperAdapter);
        CityBean cityBean = SetCityUtil.getCityBean(this);
        if (cityBean != null) {
            Logcat.i("已经有数据了:" + cityBean.toString());
            initViewData(cityBean);
        }
    }

    int selectIndex = -1;

    int cityTag = -1;

    public void initAllCity() {
        for (CityBean.CityListBean beans : mBeanList) {
            beans.setSelect(false);
        }
        for (CitySettingHeaderBean headerBean : mHeaderDatas) {
            for (int i = 0; i < headerBean.getCityList().size(); i++) {
                headerBean.getCityList().get(i).setSelect(false);
            }
        }
        if (mRecentAdapter != null) {
            mRecentAdapter.notifyDataSetChanged();
        }
        if (mHotCityAdapter != null) {
            mHotCityAdapter.notifyDataSetChanged();
        }
        if (mAllAdapter != null) {
            mAllAdapter.notifyDataSetChanged();
        }
        if (mWrapperAdapter != null) {
            mWrapperAdapter.notifyDataSetChanged();
        }

    }

    @Override
    protected void setListener() {
        mAllAdapter.setOnItemClickListener(new OnRvItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                initAllCity();
                cityTag = 3;
                mBeanList.get(position).setSelect(true);
                selectIndex = position;
                setEnabled();
                mAllAdapter.notifyDataSetChanged();
                mWrapperAdapter.notifyDataSetChanged();
            }
        });
    }


    public void setEnabled() {
        mTvNext.setEnabled(true);
        mTvNext.setBackgroundColor(getResources().getColor(R.color.theme_1));
        mTvNext.setText("选好了");
    }

    @OnClick({R.id.iv_back, R.id.tv_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_next:
                Logcat.i("TAG:" + cityTag + "/" + selectIndex);
                switch (cityTag) {
                    case 1:
                        CitySettingHeaderBean.CityBean bean1 = mHeaderDatas.get(0).getCityList().get(selectIndex);
                        setAddress(new CityBean.RecentCityBean(bean1.getId(), bean1.getName(), bean1.getShop_id()));
                        Logcat.e("-----" + bean1.toString());
                        break;
                    case 2:
                        CitySettingHeaderBean.CityBean bean2 = mHeaderDatas.get(1).getCityList().get(selectIndex);
                        Logcat.e("-----" + bean2.toString());
                        setAddress(new CityBean.RecentCityBean(bean2.getId(), bean2.getName(), bean2.getShop_id()));
                        break;
                    case 3:
                        CityBean.CityListBean cityListBean = mBeanList.get(selectIndex);
                        setAddress(new CityBean.RecentCityBean(cityListBean.getId(), cityListBean.getName(), cityListBean.getShop_id()));
                        break;
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            fromTag = bundle.getString("fromTag");
            if (!StringUtil.isEmpty(fromTag)) {
                finish();
            } else {
                ToastUtil.showToast("请选择所在城市");
            }
        }
    }

    public void getData() {
        HttpClient.get(ApiConstants.Urls.GET_CITY, new HashMap<String, String>(), new HttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                Logcat.i("返回的城市数据：" + content);
                ResultBean bean = JSON.parseObject(content, ResultBean.class);
                if (bean.getCode().equals("1")) {
                    mCityBean = JSON.parseObject(bean.getData(), CityBean.class);
                    initViewData(mCityBean);
                } else {
                    mLayoutManager.showNetError();
                }
            }
        });
    }

    private void initViewData(CityBean cityBean) {
        if (mIndexBar != null){
            mIndexBar.setVisibility(View.VISIBLE);
        }else{
            mIndexBar = findViewById(R.id.ib_bar);
            mIndexBar.setVisibility(View.VISIBLE);
        }
        mHotCityBean.addAll(cityBean.getHotCity());
        mBeanList.addAll(cityBean.getCityList());
        mSourceDatas.addAll(mBeanList);
        if (isRecent) {
            List<CityBean.RecentCityBean> beanList = SetCityUtil.init(mContext).getRecentCityBean();
            if (!ListUtil.isEmpty(beanList)) {
                for (int i = 0; i < beanList.size(); i++) {
                    CitySettingHeaderBean.CityBean bean = new CitySettingHeaderBean.CityBean();
                    bean.setName(beanList.get(i).getName());
                    bean.setId(beanList.get(i).getId());
                    bean.setShop_id(beanList.get(i).getShop_id());
                    String cityName = (String) SharedPreferencesUtils.getSP(this, "cityName", "");
                    if (!StringUtil.isEmpty(cityName) && cityName.equals(beanList.get(i).getName())) {
                        bean.setSelect(true);
                        selectIndex = i;
                        cityTag = 1;
                        setEnabled();
                    }
                    mHeaderDatas.get(0).getCityList().add(bean);
                }
            }
            for (int i = 0; i < cityBean.getHotCity().size(); i++) {
                CitySettingHeaderBean.CityBean bean = new CitySettingHeaderBean.CityBean();
                bean.setName(cityBean.getHotCity().get(i).getName());
                bean.setId(cityBean.getHotCity().get(i).getId());
                bean.setShop_id(cityBean.getHotCity().get(i).getShop_id());
                mHeaderDatas.get(1).getCityList().add(bean);
            }
        } else {
            for (int i = 0; i < cityBean.getHotCity().size(); i++) {
                CitySettingHeaderBean.CityBean bean = new CitySettingHeaderBean.CityBean();
                bean.setName(cityBean.getHotCity().get(i).getName());
                bean.setId(cityBean.getHotCity().get(i).getId());
                bean.setShop_id(cityBean.getHotCity().get(i).getShop_id());
                mHeaderDatas.get(0).getCityList().add(bean);
            }
        }
        mIndexBar.getDataHelper().sortSourceDatas(mBeanList);
        mIndexBar.setmSourceDatas(mSourceDatas)//设置数据
                .invalidate();
        mDecoration.setmDatas(mSourceDatas);
        if (mWrapperAdapter != null) {
            mWrapperAdapter.notifyDataSetChanged();
            mIndexBar.invalidate();
        }
        if (mHotCityAdapter != null) {
            mHotCityAdapter.notifyDataSetChanged();
            mIndexBar.invalidate();
        }
        mLayoutManager.showContent();
    }

    public void setAddress(CityBean.RecentCityBean cityBean) {
        Bundle bundle = new Bundle();
        if (cityBean != null) {
            SharedPreferencesUtils.setSP(this,"setAddress","tag");//设置地址的Tag 用户启动判断
            SharedPreferencesUtils.setSP(this, "shopId", cityBean.getShop_id() + "");
            SharedPreferencesUtils.setSP(this, "cityName", cityBean.getName() + "");
            SharedPreferencesUtils.setSP(this, "cityId", cityBean.getId() + "");
            SetCityUtil.init(this).addRecentCityBean(cityBean);
            SetCityUtil.setCityBean(this, mCityBean);
            EventBus.getDefault().post(new PostResult("cityId", cityBean.getId()));
            EventBus.getDefault().post(new PostResult("setAddress", cityBean));
            if (StringUtil.isEmpty(fromTag)) {
                startActivity(MainActivity.class, bundle);
            } else {
                setResult(900);
                finish();
            }
        }
    }
}
