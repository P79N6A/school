package com.idyoga.yoga.fragment.news;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.news.NewsDetailsActivity;
import com.idyoga.yoga.adapter.SystemNoticeAdapter;
import com.idyoga.yoga.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SystemNoticeFragment extends BaseFragment {


    @BindView(R.id.lv_list)
    ListView mLvList;
    SystemNoticeAdapter mAdapter;
    List<Object> mBeanList = new ArrayList<>();
    @Override
    protected void initData() {

        for (int i = 0; i <10 ; i++) {
            mBeanList.add(new Object());
        }
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mAdapter = new SystemNoticeAdapter(mActivity,mBeanList,R.layout.item_news_system_notice);
        mLvList.setAdapter(mAdapter);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_list;
    }


    @Override
    protected void setListener() {
        mLvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                startActivityWithExtras(NewsDetailsActivity.class,bundle);
            }
        });

    }

}
