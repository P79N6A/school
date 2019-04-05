package com.idyoga.yoga.activity.course;

import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.idyoga.yoga.R;
import com.idyoga.yoga.adapter.ClassifyChoiceAdapter;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import vip.devkit.library.Logcat;
import vip.devkit.library.StringUtil;

/**
 * 文 件 名: ClassifyChoiceActivity
 * 创 建 人: By k
 * 创建日期: 2018/5/14 09:21
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注：
 */
public class ClassifyChoiceActivity extends BaseActivity {


    @BindView(R.id.ll_title_back)
    LinearLayout mLlTitleBack;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.tv_title_right)
    TextView mTvTitleRight;
    @BindView(R.id.ll_title_right)
    LinearLayout mLlTitleRight;
    @BindView(R.id.ll_common_layout)
    RelativeLayout mLlCommonLayout;
    @BindView(R.id.lv_list)
    ListView mLvList;

    ClassifyChoiceAdapter mAdapter;

    List<tagBean> mBeanList = new ArrayList<>();
    List<String> mClassifyA = new ArrayList<>();
    List<String> mClassifyB = new ArrayList<>();
    List<String> mClassifyC = new ArrayList<>();


    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlCommonLayout).flymeOSStatusBarFontColor("#333333").init();
    }

    @Override
    protected void initData() {
        mClassifyA.add("形象");
        mClassifyA.add("力量");
        mClassifyA.add("韧性");
        mClassifyB.add("身高");
        mClassifyB.add("体重");
        mClassifyB.add("血型");
        mClassifyB.add("学历");
        mClassifyB.add("人品");
        mClassifyB.add("智力");
        mClassifyB.add("敏捷");
        mClassifyB.add("历史");
        for (int i = 0; i < 7; i++) {
            mClassifyC.add("C类 TAG:" + i);
        }
        mBeanList.add(new tagBean(".A类", mClassifyA));
        mBeanList.add(new tagBean(".B类", mClassifyB));
        mBeanList.add(new tagBean(".C类", mClassifyC));
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_classify_choice;
    }

    @Override
    protected void initView() {
        mTvTitleText.setText("筛选");
        mTvTitleRight.setText("完成");
        mAdapter = new ClassifyChoiceAdapter(this, mBeanList, R.layout.item_classify_choice);
        mLvList.setAdapter(mAdapter);
    }

    @Override
    protected void setListener() {
        mLvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


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
                List<String> list = new ArrayList<>();
                for (String key : mAdapter.getChoiceClassify().keySet()) {
                    if (!StringUtil.isEmpty(mAdapter.getChoiceClassify().get(key))) {
                        list.add(mAdapter.getChoiceClassify().get(key));
                    }
                }
                Logcat.i("TagList:" + list.size() + list.toString()+mAdapter.getChoiceClassify());
                if (list.size() == 0) {
                    ToastUtil.showToast("请选择类别");
                } else {

                }
                break;
        }
    }

    public class tagBean {
        String tagTitle;
        List<String> mList;

        public tagBean() {
        }

        public tagBean(String tagTitle, List<String> list) {
            this.tagTitle = tagTitle;
            mList = list;
        }

        public String getTagTitle() {
            return tagTitle;
        }

        public void setTagTitle(String tagTitle) {
            this.tagTitle = tagTitle;
        }

        public List<String> getList() {
            return mList;
        }

        public void setList(List<String> list) {
            mList = list;
        }
    }

}