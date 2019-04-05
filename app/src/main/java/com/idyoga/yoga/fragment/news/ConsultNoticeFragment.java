package com.idyoga.yoga.fragment.news;
import android.view.View;
import android.widget.ListView;

import com.idyoga.yoga.R;
import com.idyoga.yoga.adapter.ConsultAdapter;
import com.idyoga.yoga.adapter.SystemNoticeAdapter;
import com.idyoga.yoga.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ConsultNoticeFragment extends BaseFragment {


    @BindView(R.id.lv_list)
    ListView mLvList;
    ConsultAdapter mAdapter;
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
        mAdapter = new ConsultAdapter(mActivity,mBeanList,R.layout.item_news_system_consult);
        mLvList.setAdapter(mAdapter);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_list;
    }


    @Override
    protected void setListener() {

    }

}
