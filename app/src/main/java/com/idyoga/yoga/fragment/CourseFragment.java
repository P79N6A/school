package com.idyoga.yoga.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.OptionsPickerView;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.news.NewsActivity;
import com.idyoga.yoga.activity.search.SearchLeadActivity;
import com.idyoga.yoga.activity.tutor.TutorListActivity;
import com.idyoga.yoga.adapter.HomeFrPagerAdapter;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.comm.NetWorkConstant;
import com.idyoga.yoga.base.BaseFragment;
import com.idyoga.yoga.common.http.type1.HttpClient;
import com.idyoga.yoga.common.http.type1.HttpResponseHandler;
import com.idyoga.yoga.common.http.type2.ICommonViewUi;
import com.idyoga.yoga.common.http.type2.presenter.impl.CommonRequestPresenterImpl;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.fragment.web.FragmentCourseByOffline;
import com.idyoga.yoga.fragment.child.FragmentCourseByVideo;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.address.AddressBean;
import com.idyoga.yoga.utils.CommonUtils;
import com.idyoga.yoga.utils.UserAgentUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import okhttp3.Request;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.library.StringUtil;
import vip.devkit.view.common.tab.AdvancedPagerSlidingTabStrip;

import static com.idyoga.yoga.comm.AppContext.mContext;

/**
 * 作者 by K
 * 时间：on 2018/2/20
 * 邮箱 by  vip@devkit.vip
 * <p/>
 * 类用途：
 * 最后修改：
 */
public class CourseFragment extends BaseFragment implements ICommonViewUi {

    @BindView(R.id.course_tabs)
    AdvancedPagerSlidingTabStrip mCourseTabs;
    @BindView(R.id.course_vp_content)
    ViewPager mCourseVpContent;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.iv_address)
    ImageView mIvAddress;
    @BindView(R.id.ll_address)
    LinearLayout mLlAddress;
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.ll_search_title)
    RelativeLayout mLlSearchTitle;
    private List<String> mTabList = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private ArrayList<AddressBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<AddressBean.CityBean>> options2Items = new ArrayList<>();
    private OptionsPickerView pvOptions;
    private static final int HandlerFLAG = 2;
    private Handler mCourseHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Logcat.i("" + msg.what);
            switch (msg.what) {
                case HandlerFLAG:
                    pvOptions.setPicker(options1Items, options2Items);
                    pvOptions.show();
                    break;
            }
            return false;
        }
    });

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar
                .titleBar(mLlSearchTitle)
                .keyboardEnable(true)
                .flymeOSStatusBarFontColor("#333333")
                .init();
    }

    @Override
    protected void initData() {
        super.initData();
        Logcat.i("Item:" + getUserVisibleHint());
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_course;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        iCommonRequestPresenter = new CommonRequestPresenterImpl(mContext, this);
        CommonUtils.clearFocus(mEtSearch);
        if (!StringUtil.isEmpty((String) SharedPreferencesUtils.getSP(mActivity, "cityName", ""))) {
            mTvAddress.setText((String) SharedPreferencesUtils.getSP(mActivity, "cityName", "广州"));
        }
        mTabList.add("线下课程");
        mTabList.add("视频课程");
        mFragments.add(new FragmentCourseByOffline());
        mFragments.add(new FragmentCourseByVideo());
        Logcat.i("list:" + mTabList.size() + "/" + mFragments.size());
        if (mTabList.size() != 0 && mFragments.size() != 0) {
            mCourseVpContent.setAdapter(new HomeFrPagerAdapter(getChildFragmentManager(), mTabList, mFragments));
            mCourseTabs.setViewPager(mCourseVpContent);
        }
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
    public void onResume() {
        super.onResume();
    }


    @OnClick({R.id.ll_address, R.id.et_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_address:
                if (pvOptions != null && pvOptions.isShowing()) {
                    pvOptions.dismiss();
                }
                getAddressData();
                break;
            case R.id.et_search:
                startActivityWithoutExtras(SearchLeadActivity.class);
                break;
        }
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
                msg.what = HandlerFLAG;
                msg.obj = new Bundle();
                mCourseHandler.sendMessage(msg);
            } else {
                Logcat.e("地址集合小于1");
            }


        } else {
            showLoading("加载中...", true);
            final Map map = new HashMap();
            HttpClient.get(NetWorkConstant.GET_ADDRESS, map, new HttpResponseHandler() {
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
                            msg.what = HandlerFLAG;
                            msg.obj = new Bundle();
                            mCourseHandler.sendMessage(msg);
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
            EventBus.getDefault().post(new PostResult("address", shopId));
            EventBus.getDefault().post(new PostResult("setAddress", options2Items.get(options1).get(options2).getName()));
        }
    };

    @Override
    public void toRequest(int eventTag) {
        if (eventTag == ApiConstants.EventTags.ADDRESS_SELECT) {
            Logcat.i("地址：" + eventTag + "/");
            Map map = new HashMap();
            iCommonRequestPresenter.request(eventTag, mContext, ApiConstants.Urls.ADDRESS, map);
        }
    }

    @Override
    public void getRequestData(int eventTag, String result) {
        Logcat.i("地址：" + eventTag + "/" + result);
        if (eventTag == ApiConstants.EventTags.ADDRESS_SELECT) {
            ResultBean mResultBean = JSON.parseObject(result, ResultBean.class);
            List<AddressBean> addressBeanList = JSON.parseArray(mResultBean.getData(), AddressBean.class);
            options1Items.clear();
            options2Items.clear();
            for (int i = 0; i < addressBeanList.size(); i++) {
                options1Items.add(addressBeanList.get(i));
                ArrayList<AddressBean.CityBean> options2Item = new ArrayList<>();
                options2Item.addAll(options1Items.get(i).getCity());
                options2Items.add(options2Item);
            }
            if (options1Items.size() > 0) {
                Message msg = new Message();
                msg.what = HandlerFLAG;
                msg.obj = new Bundle();
                mCourseHandler.sendMessage(msg);
            } else {
                Logcat.e("地址集合小于1");
            }
        }
    }


    @Override
    public void onRequestFailureException(int eventTag, String msg) {

    }


    @Override
    public void onEvent(PostResult postResult) {
        super.onEvent(postResult);
        if (postResult.getTag().equals("setAddress")) {
            if (mTvAddress != null) {
                mTvAddress.setText(postResult.getResult().toString());
            }
        }
    }
}
