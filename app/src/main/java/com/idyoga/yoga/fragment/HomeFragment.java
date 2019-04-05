package com.idyoga.yoga.fragment;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.bigkoo.pickerview.OptionsPickerView;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.loading.SetCityActivity;
import com.idyoga.yoga.activity.qrc.SaoErWeiMaActivity;
import com.idyoga.yoga.activity.search.SearchLeadActivity;
import com.idyoga.yoga.base.BaseFragment;
import com.idyoga.yoga.comm.AppContext;
import com.idyoga.yoga.comm.NetWorkConstant;
import com.idyoga.yoga.common.http.type1.HttpClient;
import com.idyoga.yoga.common.http.type1.HttpResponseHandler;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.fragment.child.FragmentHomeRecommend;
import com.idyoga.yoga.lbs.YogaLocationListener;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.address.AddressBean;
import com.idyoga.yoga.utils.CommonUtils;
import com.idyoga.yoga.utils.ToastUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;
import okhttp3.Request;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.library.StringUtil;


/**
 * 作者 by K
 * 时间：on 2017/8/28 16:02
 * 邮箱 by  vip@devkit.vip
 * <p/>
 * 类用途：
 * 最后修改：
 */
public class HomeFragment extends BaseFragment {
    /*@BindView(R.id.home_tabs)
    AdvancedPagerSlidingTabStrip mHomeTabs;
    @BindView(R.id.home_vp_content)
    ViewPager mHomeVpContent;*/
    @BindView(R.id.ll_search_title)
    RelativeLayout mLlHomeTitle;
    @BindView(R.id.iv_address)
    ImageView mIvAddress;
    @BindView(R.id.iv_search)
    ImageView mIcSearch;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.ll_address)
    LinearLayout mLlAddress;
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.layout_fragment)
    LinearLayout mLayout;
    @BindView(R.id.fl_content)
    FrameLayout mFlContent;
    Unbinder unbinder;

    private ArrayList<AddressBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<AddressBean.CityBean>> options2Items = new ArrayList<>();
    private List<String> mTabList = new ArrayList<>();
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private OptionsPickerView pvOptions;
    private String shopName;
    private int flag = 0;
    protected static HomeFragment mHomeFragment;
    private static final int VIEW_FLAG = 1;
    String latitude, longitude;
    private int currentIndex = 0;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case VIEW_FLAG:
                    pvOptions.setPicker(options1Items, options2Items);
                    pvOptions.show();
                    break;
            }
            return false;
        }

    });


    public static HomeFragment getInstance() {
        return mHomeFragment;
    }

    @Override
    protected void initData() {
        super.initData();
        Logcat.i("Item:" + getUserVisibleHint());
        latitude = (String) SharedPreferencesUtils.getSP(AppContext.getInstance(), "latitude", "");
        longitude = (String) SharedPreferencesUtils.getSP(AppContext.getInstance(), "longitude", "");
        shopName = (String) SharedPreferencesUtils.getSP(AppContext.getInstance(), "cityName", "");

        if (StringUtil.isEmpty(latitude) || StringUtil.isEmpty(longitude)) {
            final LocationClient mLocationClient = new LocationClient(AppContext.getInstance());
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
            mLocationClient.registerLocationListener(new YogaLocationListener() {
                @Override
                public void onReceiveLocation(BDLocation location) {
                    super.onReceiveLocation(location);
                    double latitude = location.getLatitude();    //获取纬度信息
                    double longitude = location.getLongitude();    //获取经度信息
                    Logcat.e("定位结果：" + "latitude：" + latitude + "longitude：" + longitude);
                    SharedPreferencesUtils.setSP(AppContext.getInstance(), "latitude", latitude);
                    SharedPreferencesUtils.setSP(AppContext.getInstance(), "longitude", longitude);
                }
            });
            mLocationClient.start();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mLocationClient.stop();
                }
            }, 5000);

        }
    }


    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlHomeTitle).flymeOSStatusBarFontColor("#333333").init();
    }


    @Override
    protected int setLayoutId() {
        return R.layout.fragment_home;
    }


    @Override
    protected void onFirstVisible() {
        super.onFirstVisible();
        Logcat.i("---1--------onFirstVisible----");
    }

    @Override
    protected void onVisible() {
        super.onVisible();
        Logcat.i("------1-----onVisible----");
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        Logcat.i("--------------a------- if " + getUserVisibleHint() + "/" + isHidden());
        mHomeFragment = this;
        CommonUtils.clearFocus(mEtSearch);
        if (!StringUtil.isEmpty((String) SharedPreferencesUtils.getSP(mActivity, "cityName", ""))) {
            mTvAddress.setText((String) SharedPreferencesUtils.getSP(mActivity, "cityName", "广州"));
        } else {
            mTvAddress.setText(shopName);
        }
       /* mTabList.add("推荐区");
        mTabList.add("权益课");
        mTabList.add("瑜伽馆");
        mTabList.add("导师");
        fragments.add(new FragmentHomeRecommend());
        fragments.add(new FragmentExperienceC());
        fragments.add(new FragmentShop());
        fragments.add(new FragmentTutor());
        mHomeVpContent.setAdapter(new HomeFrPagerAdapter(getChildFragmentManager(), mTabList, fragments));
        mHomeVpContent.setCurrentItem(currentIndex);
        mHomeTabs.setViewPager(mHomeVpContent);
        mHomeVpContent.getCurrentItem();
        mHomeVpContent.setOffscreenPageLimit(4);*/
        //mFlContent
        FragmentHomeRecommend fragmentHomeRecommend = new FragmentHomeRecommend();
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fl_content,fragmentHomeRecommend);
        transaction.commit();
        transaction.show(fragmentHomeRecommend);

        mImmersionBar.titleBar(mLlHomeTitle).flymeOSStatusBarFontColor("#333333").init();
        initPvOptions();
    }

    private void initPvOptions() {
        pvOptions = new OptionsPickerView.Builder(mActivity, mOptionsSelectListener)
                .setTitleText("城市选择")
                .setContentTextSize(20)
                .isCenterLabel(false)
                .setBackgroundId(0x66000000) //设置外部遮罩颜色
                .build();
    }

    @Override
    protected void setListener() {
        super.setListener();
       /* mHomeTabs.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                initPageFragment(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });*/
    }

    /**
     * @param currentItem 给外部设置选中的设置
     */
    public void setHomeTabs(int currentItem) {
        /*mHomeTabs.setSelectItem(currentItem);
        mHomeVpContent.setCurrentItem(currentItem);*/
    }

    public void initPageFragment(int currentItem) {
        // 获取FragmentTransaction对象，为了保证兼容性这里使用getSupportFragmentManager()
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {
            if (i != currentItem) {
                transaction.hide(fragments.get(currentItem));
            }
        }
        transaction.show(fragments.get(currentItem));
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            EventBus.getDefault().post(new PostResult("toTop_home", "0"));
        }
        if (!hidden && mImmersionBar != null)
            ;
        mImmersionBar.titleBar(mLlHomeTitle).flymeOSStatusBarFontColor("#333333").init();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            String cityName = (String) SharedPreferencesUtils.getSP(mActivity, "cityName", "");
            if (!StringUtil.isEmpty(cityName)) {
                mTvAddress.setText(cityName);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            String cityName = (String) SharedPreferencesUtils.getSP(mActivity, "cityName", "");
            if (!StringUtil.isEmpty(cityName)) {
                mTvAddress.setText(cityName);
            }
        }
    }

    @OnClick({R.id.ll_address, R.id.et_search, R.id.iv_search})
    public void onViewClicked(View view) {
        Bundle bundle;
        switch (view.getId()) {
            case R.id.ll_address:
                if (pvOptions != null && pvOptions.isShowing()) {
                    pvOptions.dismiss();
                }
                bundle = new Bundle();
                bundle.putString("fromTag", "home");
                startActivityWithExtras(SetCityActivity.class, bundle);

                break;
            case R.id.et_search:
                startActivityWithoutExtras(SearchLeadActivity.class);
                break;
            case R.id.iv_search:
                startActivityWithoutExtras(SearchLeadActivity.class);
                break;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
            grantResults) {
        Logcat.i("权限回调");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && requestCode == 200) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Logcat.i("有权限:" + 1);
                startActivityWithoutExtras(SaoErWeiMaActivity.class);
            } else {
                ToastUtil.showToast("请在设置中打开权限后继续");
            }
        }
    }

    /**
     * 获取地址数据
     */
    private void getAddressData() {
        if (options1Items.size() > 0) {
            options1Items.clear();
        }
        if (options2Items.size() > 0) {
            options2Items.clear();
        }
        if (!StringUtil.isEmpty((String) SharedPreferencesUtils.getSP(mActivity, "address", ""))) {
            String addressJson = (String) SharedPreferencesUtils.getSP(mActivity, "address", "");
            List<AddressBean> addressBeanList = JSON.parseArray(addressJson, AddressBean.class);
            for (int i = 0; i < addressBeanList.size(); i++) {
                options1Items.add(addressBeanList.get(i));
                ArrayList<AddressBean.CityBean> options2Item = new ArrayList<>();
                options2Item.addAll(options1Items.get(i).getCity());
                options2Items.add(options2Item);
            }
            if (options1Items.size() > 0) {
                Message msg = new Message();
                msg.what = VIEW_FLAG;
                msg.obj = new Bundle();
                mHandler.sendMessage(msg);
            } else {
                Logcat.e("地址集合小于1");
            }
        } else {
            showLoading(null, true);
            HttpClient.get(NetWorkConstant.GET_ADDRESS, null, new HttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, String content) {
                    super.onSuccess(statusCode, content);
                    Logcat.i("\n提交的参数：" + 0 + "\n" + "返回码：" + statusCode + "\n" + "返回参数：" + content);
                    if (statusCode == 200) {
                        ResultBean mResultBean = JSON.parseObject(content, ResultBean.class);
                        List<AddressBean> addressBeanList = JSON.parseArray(mResultBean.getData(), AddressBean.class);
                        SharedPreferencesUtils.setSP(mActivity, "address", JSON.toJSONString(addressBeanList));
                        for (int i = 0; i < addressBeanList.size(); i++) {
                            options1Items.add(addressBeanList.get(i));
                            ArrayList<AddressBean.CityBean> options2Item = new ArrayList<>();
                            options2Item.addAll(options1Items.get(i).getCity());
                            options2Items.add(options2Item);
                        }
                        if (options1Items.size() > 0) {
                            Message msg = new Message();
                            msg.what = VIEW_FLAG;
                            msg.obj = new Bundle();
                            mHandler.sendMessage(msg);
                        } else {
                            Logcat.e("地址集合小于1");
                        }
                    }
                }

                @Override
                public void onFailure(Request request, IOException e) {
                    super.onFailure(request, e);
                }
            });
            dismissLoading();
        }
    }


    private OptionsPickerView.OnOptionsSelectListener mOptionsSelectListener = new OptionsPickerView.OnOptionsSelectListener() {
        @Override
        public void onOptionsSelect(int options1, int options2, int options3, View v) {
            String address = options1Items.get(options1).getPickerViewText() + "  "
                    + options2Items.get(options1).get(options2).getName();
            Logcat.i("选择的地址：" + address);
            mTvAddress.setText(options2Items.get(options1).get(options2).getName());
            SharedPreferencesUtils.setSP(mActivity, "shopId", options2Items.get(options1).get(options2).getShop_id() + "");
            SharedPreferencesUtils.setSP(mActivity, "cityName", options2Items.get(options1).get(options2).getName() + "");
            SharedPreferencesUtils.setSP(mActivity, "cityId", options2Items.get(options1).get(options2).getId() + "");

            String shopId = (String) SharedPreferencesUtils.getSP(mActivity, "shopId", "");
            Logcat.i("存入SP的地址ID：" + shopId);
            EventBus.getDefault().post(new PostResult("address", options2Items.get(options1).get(options2)));
            EventBus.getDefault().post(new PostResult("setAddress", options2Items.get(options1).get(options2).getName()));

        }
    };


    @Override
    public void onEvent(PostResult postResult) {
        super.onEvent(postResult);
        //        Logcat.i("onEvent:" + postResult.getTag() + "/" + postResult.getResult().toString());
        if (postResult.getTag().equals("setAddress")) {
            if (mTvAddress != null) {
                mTvAddress.setText(postResult.getResult().toString());
            }
        } else if (postResult.getTag().equals("FTag")) {
            if (postResult.getResult().equals("0_1")) {
                setHomeTabs(1);
            }
        } else if ("toBespokeCourse".equals(postResult.getTag())) {
            /*Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (mHomeVpContent != null && mHomeTabs != null){
                                mHomeVpContent.setCurrentItem(1);
                                mHomeTabs.setSelectItem(1);
                            }
                        }
                    });
                }
            };
            timer.schedule(task,300);*/
        } else if ("text_activity".equals(postResult.getTag())) {
            //mHomeVpContent.setCurrentItem(1);
           /* this.currentIndex = 1;
//            setHomeTabs(2);
            ToastUtil.showToast("text_activity ------------");*/
        }
    }
}
