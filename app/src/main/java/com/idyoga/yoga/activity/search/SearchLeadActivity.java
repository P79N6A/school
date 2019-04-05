package com.idyoga.yoga.activity.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.loading.SetCityActivity;
import com.idyoga.yoga.adapter.SearchAdapter;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.comm.AppContext;
import com.idyoga.yoga.common.http.type2.ICommonViewUi;
import com.idyoga.yoga.common.http.type2.presenter.ICommonRequestPresenter;
import com.idyoga.yoga.common.http.type2.presenter.impl.CommonRequestPresenterImpl;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.SearchRecordBean;
import com.idyoga.yoga.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import vip.devkit.library.ListUtil;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.library.StringUtil;

/**
 * 文 件 名: SearchLeadActivity
 * 创 建 人: By k
 * 创建日期: 2018/5/11 16:00
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注：
 */
public class SearchLeadActivity extends BaseActivity implements ICommonViewUi {
    @BindView(R.id.ll_title_back)
    LinearLayout mLlTitleBack;
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.rl_common_layout)
    RelativeLayout mRlCommonLayout;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.iv_search)
    ImageView mIvSearch;
    @BindView(R.id.lv_list)
    ListView mLvList;
    List<SearchRecordBean.TagBean> mBeanList = new ArrayList<>();
    List<String> mHotTagList = new ArrayList<>();
    List<String> mHistoryList = new ArrayList<>();
    SearchAdapter mAdapter;
    int userId;
    String cityName;
    SearchRecordBean mSearchRecordBean;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mRlCommonLayout).flymeOSStatusBarFontColor("#333333").init();
    }

    @Override
    protected ICommonRequestPresenter initICommonViewUi() {
        return iCommonRequestPresenter = new CommonRequestPresenterImpl(this, this);
    }

    @Override
    protected void initData() {
        userId = (int) SharedPreferencesUtils.getSP(this, "UserId", 0);
        cityName = (String) SharedPreferencesUtils.getSP(AppContext.getInstance(), "cityName", "");
        toRequest(ApiConstants.EventTags.SEARCH_TAG_LIST);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        mAdapter = new SearchAdapter(this, mBeanList, R.layout.item_search_tag);
        mLvList.setAdapter(mAdapter);
        if (!StringUtil.isEmpty(cityName)) {
            mTvAddress.setText(cityName);
        }
    }

    @Override
    protected void setListener() {
        mEtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                String mKeyword = textView.getText().toString();
                if (!StringUtil.isEmpty(mKeyword)) {
                    addSearchRecord(SearchLeadActivity.this, mKeyword);
                    Bundle bundle = new Bundle();
                    bundle.putString("mKeyword", mKeyword);
                    startActivity(SearchActivity.class, bundle);
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

    @OnClick({R.id.ll_title_back, R.id.iv_search, R.id.tv_address})
    public void onViewClicked(View view) {
        Bundle bundle;
        switch (view.getId()) {
            case R.id.ll_title_back:
                finish();
                break;
            case R.id.tv_address:
                bundle = new Bundle();
                bundle.putString("fromTag", "home");
                startActivity(SetCityActivity.class, bundle);
                break;
            case R.id.iv_search:
                String mKeyword = mEtSearch.getText().toString();
                if (!StringUtil.isEmpty(mKeyword)) {
                    addSearchRecord(this, mKeyword);
                    bundle = new Bundle();
                    bundle.putString("mKeyword", mKeyword);
//                    startActivity(SearchActivity.class, bundle);
                    Intent intent = new Intent(this,SearchActivity.class);
                    intent.putExtras(bundle);
                    startActivityForResult(intent,800);
                } else {
                    ToastUtil.showToast("亲，请输入内容!");
                }
                break;
        }
    }

    @Override
    public void toRequest(int eventTag) {
        if (eventTag == ApiConstants.EventTags.SEARCH_TAG_LIST) {
            Map map = new HashMap();
            map.put("userId", userId + "");
            iCommonRequestPresenter.request(eventTag, this, ApiConstants.Urls.SEARCH_TAG_LIST, map);
        }

    }

    private void initViewData(SearchRecordBean searchRecordBean) {
        mBeanList.clear();
        mHistoryList.clear();
        mHotTagList.clear();
        if (searchRecordBean.getHotSeek().size() > 0) {
            for (int i = 0; i < searchRecordBean.getHotSeek().size(); i++) {
                mHotTagList.add(searchRecordBean.getHotSeek().get(i).getName());
            }
            mBeanList.add(new SearchRecordBean.TagBean("热门搜索", mHotTagList));
            mAdapter.notifyDataSetChanged();
        }
        List<String> list = getSearchRecord(this);
        if (!ListUtil.isEmpty(list)) {
            mHistoryList.addAll(list);
            mBeanList.add(new SearchRecordBean.TagBean("历史记录", mHistoryList));
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getRequestData(int eventTag, String result) {
        Logcat.i("返回：" + result);
        dismissLoading();
        ResultBean resultBean = JSON.parseObject(result, ResultBean.class);
        if (eventTag == ApiConstants.EventTags.SEARCH_TAG_LIST) {
            if (resultBean.getCode().equals("1") && resultBean.getData() != null) {
                mSearchRecordBean = JSON.parseObject(resultBean.getData(), SearchRecordBean.class);
                initViewData(mSearchRecordBean);
            }
        }
    }


    @Override
    public void onRequestFailureException(int eventTag, String msg) {

    }


    public static List<String> getSearchRecord(Context context) {
        String mHistoryJson = (String) SharedPreferencesUtils.getSP(context, "SearchRecord", "");
        Logcat.i("getSearchRecord:" + mHistoryJson);
        if (!StringUtil.isEmpty(mHistoryJson)) {
            List<SearchRecordBean.SearchTag> list = JSON.parseArray(mHistoryJson, SearchRecordBean.SearchTag.class);
            if (!ListUtil.isEmpty(list)) {
                List<String> setList = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    setList.add(list.get(i).getName());
                }
                return setList;
            }
        }
        return null;
    }

    public static void addSearchRecord(Context context, String str) {
        String mHistoryJson = (String) SharedPreferencesUtils.getSP(context, "SearchRecord", "");
        if (!StringUtil.isEmpty(mHistoryJson)) {
            List<SearchRecordBean.SearchTag> list = JSON.parseArray(mHistoryJson, SearchRecordBean.SearchTag.class);
            if (ListUtil.isEmpty(list)) {
                list = new ArrayList<>();
                list.add(new SearchRecordBean.SearchTag(str));
            } else {
                list.add(new SearchRecordBean.SearchTag(str));
            }
            SharedPreferencesUtils.setSP(context, "SearchRecord", JSON.toJSONString(removeDuplicate(list)));
        } else {
            List<SearchRecordBean.SearchTag> list = new ArrayList<>();
            list.add(new SearchRecordBean.SearchTag(str));
            SharedPreferencesUtils.setSP(context, "SearchRecord", JSON.toJSONString(list));
        }
    }

    /**
     * @param list
     * @return 去重
     */
    public static List<SearchRecordBean.SearchTag> removeDuplicate(List<SearchRecordBean.SearchTag> list) {
        HashSet set = new HashSet(list);
        list.clear();
        removeDuplicate(set);
        list.addAll(set);
        return list;
    }

    private static Set<SearchRecordBean.SearchTag> removeDuplicate(Set<SearchRecordBean.SearchTag> set) {
        Map<String, SearchRecordBean.SearchTag> map = new HashMap<>();
        Set<SearchRecordBean.SearchTag> tempSet = new HashSet<>();
        for (SearchRecordBean.SearchTag p : set) {
            if (map.get(p.getName()) == null) {
                map.put(p.getName(), p);
            } else {
                tempSet.add(p);
            }
        }
        set.removeAll(tempSet);
        return set;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Logcat.i("requestCode:"+requestCode+"/"+resultCode);
        if (requestCode==800){
            toRequest(ApiConstants.EventTags.SEARCH_TAG_LIST);
        }

    }
}