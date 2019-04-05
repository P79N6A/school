package com.idyoga.yoga.activity.card;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.idyoga.yoga.R;
import com.idyoga.yoga.adapter.VipCardAdapter;
import com.idyoga.yoga.comm.NetWorkConstant;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.common.http.type1.HttpClient;
import com.idyoga.yoga.common.http.type1.HttpResponseHandler;
import com.idyoga.yoga.common.view.Label.LabelLinearLayout;
import com.idyoga.yoga.model.VIPCardBean;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Request;
import vip.devkit.library.Logcat;

/**
 * 文 件 名: CardDetailsActivity
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/3/22
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class CardDetailsActivity extends BaseActivity {

    @BindView(R.id.ll_title_back)
    LinearLayout mLlTitleBack;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.ll_common_layout)
    RelativeLayout mLlCommonLayout;
    @BindView(R.id.tv_vip_name)
    TextView mTvVipName;
    @BindView(R.id.tv_num)
    TextView mTvNum;
    @BindView(R.id.tv_date)
    TextView mTvDate;
    @BindView(R.id.ll_label)
    LabelLinearLayout mLlLabel;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.tv_card_child)
    TextView mTvCardChild;
    @BindView(R.id.tv_rule)
    TextView mTvRule;
    @BindView(R.id.iv_course_b_into)
    ImageView mIvCourseBInto;
    @BindView(R.id.rl_card_child)
    RelativeLayout mRlCardChild;
    @BindView(R.id.rl_card_course)
    RelativeLayout mRlCardCourse;
    String type;
    VIPCardBean mVIPCardBean;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlCommonLayout).init();
    }

    @Override
    protected void initData() {
        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            mVIPCardBean = mBundle.getParcelable("Bean");
            type = mBundle.getString("type");
            Logcat.e("卡详情："+mVIPCardBean.toString());
        }
//        getCardDetail();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.user_activity_vip_card_detail;
    }

    @Override
    protected void initView() {
        mTvTitleText.setText("VIP权益卡详情");
        mLlLabel.setLabelVisable(false);
        if (type != null && mVIPCardBean != null) {
            if (!type.equals("Valid")) {
                mLlLabel.setBackgroundResource(R.drawable.overdue_card_bg);
            } else {
                mLlLabel.setBackgroundResource(R.drawable.bg_blue);
            }
            mTvVipName.setText(mVIPCardBean.getCard_name());
            mTvDate.setText(VipCardAdapter.getDateStringByTimeSTamp((long) mVIPCardBean.getExpire_time(), "yyyy-MM-dd"));
            mTvNum.setText("有效次数： " + mVIPCardBean.getHas_time() + " (已用" + (mVIPCardBean.getHas_time() - mVIPCardBean.getValid_time()) + "次)");
            mTvTime.setText("剩余次数：" + mVIPCardBean.getValid_time());
            mTvCardChild.setText("附属卡：" + mVIPCardBean.getAnnex_num());
            mTvRule.setText("");
            if (mVIPCardBean.getAnnex_num()==0){
                mRlCardChild.setVisibility(View.GONE);
            }
        }
    }

    @Override
    protected void setListener() {

    }


    private void getCardDetail() {
        Map map = new HashMap();
        map.put("token", "");
        map.put("userId", "");
        map.put("MembershipId", "0");
        HttpClient.post(NetWorkConstant.USER_CARD_DETAIL, map, new HttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String content) {
                super.onSuccess(statusCode, content);

            }

            @Override
            public void onFailure(Request request, IOException e) {

            }
        });
    }


    @OnClick({R.id.ll_title_back, R.id.rl_card_child, R.id.rl_card_course})
    public void onViewClicked(View view) {
        Bundle bundle;
        switch (view.getId()) {
            case R.id.ll_title_back:
                finish();
                break;
            case R.id.rl_card_child:
                bundle = new Bundle();
                bundle.putString("cardId", mVIPCardBean.getId() + "");
//                bundle.putString("cardId", mVIPCardBean.getCard_id() + "");
                bundle.putString("shopId", mVIPCardBean.getShop_id() + "");
                bundle.putString("cardName", mVIPCardBean.getCard_name() + "");
                bundle.putParcelable("cardBean", mVIPCardBean);
                if (type.equals("Valid")){
                    startActivity(ChildCardListActivity.class, bundle);
                }else {
                    startActivity(InValidChildCardListActivity.class, bundle);
                }
                break;
            case R.id.rl_card_course:
                bundle = new Bundle();
                bundle.putString("cardId", mVIPCardBean.getId() + "");
//                bundle.putString("cardId", mVIPCardBean.getCard_id() + "");
                bundle.putString("shopId", mVIPCardBean.getShop_id() + "");
                bundle.putString("cardName", mVIPCardBean.getCard_name() + "");
                bundle.putParcelable("cardBean", mVIPCardBean);
                startActivity(CardCourseRangeActivity.class, bundle);
                break;
        }
    }
}
