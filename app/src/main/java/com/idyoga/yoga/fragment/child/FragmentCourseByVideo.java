package com.idyoga.yoga.fragment.child;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.home.SubjectListActivity;
import com.idyoga.yoga.activity.tutor.TutorSelectActivity;
import com.idyoga.yoga.activity.web.YogaWebActivity;
import com.idyoga.yoga.base.BaseFragment;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.comm.AppContext;
import com.idyoga.yoga.common.adapter.CommonAdapter;
import com.idyoga.yoga.common.adapter.ViewHolder;
import com.idyoga.yoga.common.http.type2.ICommonViewUi;
import com.idyoga.yoga.common.http.type2.presenter.ICommonRequestPresenter;
import com.idyoga.yoga.common.http.type2.presenter.impl.CommonRequestPresenterImpl;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.VideoCourseBean;
import com.idyoga.yoga.model.address.AddressBean;
import com.idyoga.yoga.utils.BannerGlideImageLoader;
import com.idyoga.yoga.utils.DateUtils;
import com.idyoga.yoga.view.CustomScrollView;
import com.idyoga.yoga.view.YogaLayoutManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import vip.devkit.library.Logcat;
import vip.devkit.library.ResourceUtils;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.library.StringUtil;
import vip.devkit.view.common.banner.BannerConfig;
import vip.devkit.view.common.banner.BannerV;
import vip.devkit.view.common.banner.listener.OnBannerListener;

import static com.idyoga.yoga.model.VideoCourseBean.VideoListBean;

/**
 * 文 件 名: FragmentCourseByVideo
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/5/9
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class FragmentCourseByVideo extends BaseFragment implements ICommonViewUi {

    @BindView(R.id.ll_layout)
    LinearLayout mLlLayout;
    @BindView(R.id.csv_view)
    CustomScrollView mScrollView;
    @BindView(R.id.bv_view)
    BannerV mBvView;
    @BindView(R.id.srl_Refresh)
    SwipeRefreshLayout mSrlRefresh;
    @BindView(R.id.lv_list)
    ListView mLvList;
    @BindView(R.id.tv_tag_a)
    TextView mTvTagA;
    @BindView(R.id.iv_tag_a)
    ImageView mIvTagA;
    @BindView(R.id.ll_tag_a)
    LinearLayout mLlTagA;
    @BindView(R.id.tv_tag_b)
    TextView mTvTagB;
    @BindView(R.id.iv_tag_b)
    ImageView mIvTagB;
    @BindView(R.id.ll_tag_b)
    LinearLayout mLlTagB;
    @BindView(R.id.tv_tag_c)
    TextView mTvTagC;
    @BindView(R.id.iv_tag_c)
    ImageView mIvTagC;
    @BindView(R.id.ll_tag_c)
    LinearLayout mLlTagC;
    Unbinder unbinder;
    private boolean isLoadMore = true;
    private String shopId;
    private String cityId;
    private VideoCourseBean mVideoCourseBean;
    private List<String> mBannerList = new ArrayList<>();
    private List<VideoListBean> mVideoListBeanList = new ArrayList<>();
    private String orderType = "1";
    private int pageIndex = 1;
    private Map<Integer, List<VideoListBean>> mListMap = new HashMap<>();
    private List<View> mViewList = new ArrayList<>();
    private CommonAdapter mCommonAdapter;
    private boolean isLoad;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlLayout).init();
    }


    @Override
    protected ICommonRequestPresenter initICommonViewUi() {
        return iCommonRequestPresenter = new CommonRequestPresenterImpl(getActivity(), this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_video_course;
    }

    @Override
    protected void initData() {
        super.initData();
        cityId = (String) SharedPreferencesUtils.getSP(AppContext.getInstance(), "cityId", "");
        shopId = (String) SharedPreferencesUtils.getSP(AppContext.getInstance(), "shopId", "");
        toRequest(ApiConstants.EventTags.VIDEO_COURSE_LIST);
    }

    @Override
    protected YogaLayoutManager initLayoutManager() {
        return YogaLayoutManager.wrap(mLvList);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mLayoutManager = YogaLayoutManager.wrap(mLvList);
        mLayoutManager.showLoading();
        initEmpty();
        initHotView();
    }

    @Override
    protected void onVisible() {
        super.onVisible();
        if (isLoad) {
            initEmpty();
            toRequest(ApiConstants.EventTags.VIDEO_COURSE_LIST);
        }
    }

    void initEmpty() {
        pageIndex = 1;
        isLoadMore = true;
        mBannerList.clear();
        mVideoListBeanList.clear();
        mIvTagA.setImageResource(R.drawable.icon_sort);
        mIvTagB.setImageResource(R.drawable.icon_sort);
        mIvTagC.setImageResource(R.drawable.icon_sort);
        if (mCommonAdapter!=null){
            mCommonAdapter.notifyDataSetChanged();
        }
    }


    @Override
    protected void setListener() {
        mSrlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initEmpty();
                toRequest(ApiConstants.EventTags.VIDEO_COURSE_LIST);
                mSrlRefresh.setRefreshing(false);
            }
        });
        mBvView.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putString("getUrl", mBannerList.get(position));
                startActivityWithExtras(YogaWebActivity.class, bundle);
            }
        });
        mLvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putString("videoId", mVideoListBeanList.get(i).getId() + "");
                startActivityWithExtras(SubjectListActivity.class, bundle);
            }
        });
        mScrollView.setOnScrollChangeListener(new CustomScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChanged(CustomScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    if (isLoadMore) {
                        Logcat.i("BOTTOM SCROLL:" + pageIndex);
                        for (int i = 0; i < mViewList.size(); i++) {
                            mLvList.removeFooterView(mViewList.get(i));
                        }
                        View view = View.inflate(mActivity, R.layout.view_loading_footer, null);
                        mViewList.add(view);
                        mLvList.addFooterView(view);
                        ++pageIndex;
                        toRequest(ApiConstants.EventTags.VIDEO_COURSE_LIST);
                    }
                }
            }
        });
    }

    private void initHotView() {
        mLvList.setAdapter(mCommonAdapter = new CommonAdapter<VideoListBean>(getActivity(), mVideoListBeanList, R.layout.item_video_course_list) {
            @Override
            public void convert(ViewHolder holder, VideoListBean videoListBean, int position) {
                Glide.with(getActivity())
                        .load(videoListBean.getImage_url()).placeholder(R.drawable.img_course).error(R.drawable.img_course)
                        .into((ImageView) holder.getView(R.id.iv_img));
                String tutor = "";
                if (!StringUtil.isEmpty(videoListBean.getTutor_name())) {
                    tutor = videoListBean.getTutor_name();
                }
                holder.setText(R.id.tv_course_name, videoListBean.getTitle())
                        .setText(R.id.tv_course_tutor, "导师：" + tutor)
                        .setText(R.id.tv_course_time, "时长：" + DateUtils.secToTime(videoListBean.getTime()) + "");
            }
        });
    }

    private void initBannerView(List<String> list) {
        mBvView.setBannerStyle(1);
        mBvView.setImageLoader(new BannerGlideImageLoader());
        mBvView.setImages(list);
        mBvView.isAutoPlay(true);
        mBvView.setDelayTime(3000);
        mBvView.setIndicatorGravity(BannerConfig.CENTER);
        if (mBannerList.size() > 0) {
            mBvView.start();
        }
    }

    private void initViewData(VideoCourseBean bean) {
        if (bean == null) {
            return;
        }
        for (int i = 0; i < bean.getBannerList().size(); i++) {
            mBannerList.add(bean.getBannerList().get(i).getImage());
        }
        if (mBannerList.size() > 0) {
            initBannerView(mBannerList);
        }
        if (bean.getVideoList().size() > 0) {
            mVideoListBeanList.addAll(bean.getVideoList());
        }
        if (pageIndex == 1 && bean.getVideoList().size() == 0) {
            mLayoutManager.showEmpty();
        } else {
            mLayoutManager.showContent();
        }
        mListMap.put(pageIndex, bean.getVideoList());
        if (bean.getVideoList().size() > 0 && bean.getVideoList().size() < 15) {
            isLoadMore = false;
            for (int i = 0; i < mViewList.size(); i++) {
                mLvList.removeFooterView(mViewList.get(i));
            }
            View view = View.inflate(mActivity, R.layout.view_list_footer, null);
            mViewList.add(view);
            mLvList.addFooterView(view);
        }
        mCommonAdapter.notifyDataSetChanged();
    }

    @Override
    public void toRequest(int eventTag) {
        Map map = new HashMap();
        if (eventTag == ApiConstants.EventTags.VIDEO_COURSE_LIST) {
            map.put("cityId", cityId + "");
            map.put("page", pageIndex + "");
            map.put("size", 15 + "");
            map.put("orderType", orderType + "");
            Logcat.i("eventTag:" + eventTag + "/" + map.toString());
            iCommonRequestPresenter.request(eventTag, getActivity(), ApiConstants.Urls.VIDEO_COURSE_LIST, map);
        }
    }

    @Override
    public void getRequestData(int eventTag, String result) {
        dismissLoading();
        Logcat.i("eventTag:" + eventTag + "/" + result);
        ResultBean bean = JSON.parseObject(result, ResultBean.class);
        if (bean.getCode().equals("1") && bean.getData() != null) {
            mVideoCourseBean = JSON.parseObject(bean.getData(), VideoCourseBean.class);
            initViewData(mVideoCourseBean);
        }
    }


    @Override
    public void onRequestFailureException(int eventTag, String msg) {
        dismissLoading();
        mLayoutManager.showError();
    }


    @Override
    public void onEvent(PostResult postResult) {
        super.onEvent(postResult);
        if (postResult.getTag().equals("address")) {
            AddressBean.CityBean bean = (AddressBean.CityBean) postResult.getResult();
            cityId = bean.getId() + "";
            shopId = bean.getShop_id() + "";
            if (getUserVisibleHint()) {
                initEmpty();
                toRequest(ApiConstants.EventTags.VIDEO_COURSE_LIST);
            } else {
                isLoad = true;
            }
        }
    }

    int tagA = 1, tagB = 1, tagC;
    @OnClick({R.id.ll_tag_a, R.id.ll_tag_b, R.id.ll_tag_c})
    public void onViewClicked(View view) {
        showLoading();
        switch (view.getId()) {
            case R.id.ll_tag_a:
                if (tagA == 0) {
                    initEmpty();
                    orderType = "1";
                    mIvTagA.setImageResource(R.drawable.icon_drop_up);
                    toRequest(ApiConstants.EventTags.VIDEO_COURSE_LIST);
                } else if (tagA == 1) {
                    initEmpty();
                    orderType = "2";
                    mIvTagA.setImageResource(R.drawable.icon_down_sel);
                    toRequest(ApiConstants.EventTags.VIDEO_COURSE_LIST);
                }
                tagA = (tagA + 1) % 2;
                break;
            case R.id.ll_tag_b:
                if (tagB == 0) {
                    initEmpty();
                    orderType = "3";
                    mIvTagB.setImageResource(R.drawable.icon_drop_up);
                    toRequest(ApiConstants.EventTags.VIDEO_COURSE_LIST);
                } else if (tagB == 1) {
                    initEmpty();
                    orderType = "4";
                    mIvTagB.setImageResource(R.drawable.icon_down_sel);
                    toRequest(ApiConstants.EventTags.VIDEO_COURSE_LIST);
                }
                tagB = (tagB + 1) % 2;
                break;
            case R.id.ll_tag_c:
                if (tagC == 0) {
                    initEmpty();
                    orderType = "5";
                    mIvTagC.setImageResource(R.drawable.icon_drop_up);
                    toRequest(ApiConstants.EventTags.VIDEO_COURSE_LIST);
                } else if (tagC == 1) {
                    initEmpty();
                    orderType = "6";
                    mIvTagC.setImageResource(R.drawable.icon_down_sel);
                    toRequest(ApiConstants.EventTags.VIDEO_COURSE_LIST);
                }
                tagC = (tagC + 1) % 2;
                break;
        }
    }


}