package com.idyoga.yoga.activity.search;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.loading.SetCityActivity;
import com.idyoga.yoga.adapter.HomeFrPagerAdapter;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.comm.AppContext;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.fragment.child.FragmentSearchAll;
import com.idyoga.yoga.fragment.child.FragmentSearchCourse;
import com.idyoga.yoga.fragment.child.FragmentSearchShop;
import com.idyoga.yoga.fragment.child.FragmentSearchTutor;
import com.idyoga.yoga.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.library.StringUtil;
import vip.devkit.view.common.tab.AdvancedPagerSlidingTabStrip;

import static com.idyoga.yoga.activity.search.SearchLeadActivity.addSearchRecord;

/**
 * 文 件 名: SearchLeadActivity
 * 创 建 人: By k
 * 创建日期: 2018/5/11 16:00
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注：
 */
public class SearchActivity extends BaseActivity {

    @BindView(R.id.ll_title_back)
    LinearLayout mLlTitleBack;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.iv_search)
    ImageView mIvSearch;
    @BindView(R.id.rl_common_layout)
    RelativeLayout mRlCommonLayout;
    @BindView(R.id.tab_view)
    AdvancedPagerSlidingTabStrip mTabView;
    @BindView(R.id.vp_view)
    ViewPager mVpView;
    private String mShopId;
    private String mKeyword;
    private String cityName;
    private List<String> mTabList = new ArrayList<>();
    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.flymeOSStatusBarFontColor("#333333").titleBar(mRlCommonLayout).init();
    }


    @Override
    protected void initData() {
        mTabList.clear();
        fragments.clear();
        mShopId = (String) SharedPreferencesUtils.getSP(this, "shopId", "");
        cityName = (String) SharedPreferencesUtils.getSP(AppContext.getInstance(), "cityName", "");

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mKeyword = bundle.getString("mKeyword");
            Logcat.i("关键字：" + mKeyword + "/" + mShopId);
            setKeyword(mKeyword);
        } else {
            Logcat.e("缺少参数：");
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_search_list;
    }

    @Override
    protected void initView() {
        if (mKeyword != null) {
            mEtSearch.setText(mKeyword);
        }
        if (!StringUtil.isEmpty(cityName)) {
            mTvAddress.setText(cityName);
        }
        mTabList.clear();
        fragments.clear();
        mTabList.add("综合");
        mTabList.add("瑜伽馆");
        mTabList.add("权益课");
        mTabList.add("导师");

        fragments.add(new FragmentSearchAll());
        fragments.add(new FragmentSearchShop());
        fragments.add(new FragmentSearchCourse());
        fragments.add(new FragmentSearchTutor());
        mVpView.setAdapter(new HomeFrPagerAdapter(getSupportFragmentManager(), mTabList, fragments));
        mVpView.setCurrentItem(0);
        mTabView.setViewPager(mVpView);
        mVpView.getCurrentItem();
        mVpView.setOffscreenPageLimit(4);
    }

    @Override
    protected void setListener() {
        mEtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                String mKeyword = textView.getText().toString();
                if (!StringUtil.isEmpty(mKeyword)) {
                    addSearchRecord(SearchActivity.this, mKeyword);
                    EventBus.getDefault().post(new PostResult("searchTag", mKeyword));
                } else {
                    ToastUtil.showToast("亲，请输入内容!");
                }
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        cityName = (String) SharedPreferencesUtils.getSP(AppContext.getInstance(), "cityName", "");
        if (!StringUtil.isEmpty(cityName)) {
            mTvAddress.setText(cityName);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(800);
        finish();
    }

    @OnClick({R.id.ll_title_back, R.id.iv_search, R.id.tv_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_back:
                setResult(800);
                finish();
                break;
            case R.id.tv_address:
                Bundle bundle = new Bundle();
                bundle.putString("fromTag", "home");
                startActivity(SetCityActivity.class, bundle);
                break;
            case R.id.iv_search:
                mKeyword = mEtSearch.getText().toString();
                if (StringUtil.isEmpty(mKeyword)) {
                    ToastUtil.showToast("请输入搜索内容");
                } else {
                    addSearchRecord(this, mKeyword);
                    EventBus.getDefault().post(new PostResult("searchTag", mKeyword));
                }
                break;
        }
    }

    public String getKeyword() {
        return mKeyword;
    }

    public void setKeyword(String keyword) {
        mKeyword = keyword;
    }


    public void setTab(int position) {
        mVpView.setCurrentItem(position);
        mTabView.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mTabView.setSelectItem(position);
            }

            @Override
            public void onPageSelected(int position) {
                mTabView.setSelectItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


}