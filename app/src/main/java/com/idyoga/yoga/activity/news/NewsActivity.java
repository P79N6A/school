package com.idyoga.yoga.activity.news;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.user.LoginActivity;
import com.idyoga.yoga.adapter.HomeFrPagerAdapter;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.fragment.news.ConsultNoticeFragment;
import com.idyoga.yoga.fragment.news.SystemNoticeFragment;
import com.idyoga.yoga.utils.LoginUtil;
import com.idyoga.yoga.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import vip.devkit.library.Logcat;
import vip.devkit.view.common.tab.AdvancedPagerSlidingTabStrip;

/**
 * 文 件 名: NewsActivity
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/5/9
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class NewsActivity extends BaseActivity {

    @BindView(R.id.ll_title_back)
    LinearLayout mLlTitleBack;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.ll_common_layout)
    RelativeLayout mLlCommonLayout;
    @BindView(R.id.course_tabs)
    AdvancedPagerSlidingTabStrip mCourseTabs;
    @BindView(R.id.course_vp_content)
    ViewPager mCourseVpContent;
    private List<String> mTabList = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void initData() {

    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlCommonLayout).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_news;
    }

    @Override
    protected void initView() {
        mTabList.add("系统通知");
        mTabList.add("我的消息");
        mFragments.add(new SystemNoticeFragment());
        mFragments.add(new ConsultNoticeFragment());
        Logcat.i("list:" + mTabList.size() + "/" + mFragments.size());
        if (mTabList.size() != 0 && mFragments.size() != 0) {
            mCourseVpContent.setAdapter(new HomeFrPagerAdapter(getSupportFragmentManager(), mTabList, mFragments));
            mCourseTabs.setViewPager(mCourseVpContent);
        }
    }

    @Override
    protected void setListener() {
        mCourseVpContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position != 0&& !LoginUtil.checkIsLogin(NewsActivity.this)) {
                    mCourseTabs.setSelectItem(0);
                    mCourseVpContent.setCurrentItem(0);
                    ToastUtil.showToast("请先登录");
                    startActivityWithoutExtras(LoginActivity.class);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }


    @OnClick({R.id.ll_title_back, R.id.ll_title_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_back:
                finish();
                break;
            case R.id.ll_title_right:
                break;
        }
    }
}