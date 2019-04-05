package com.idyoga.yoga.activity.shop;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.bigkoo.pickerview.lib.WheelView;
import com.bigkoo.pickerview.listener.OnItemSelectedListener;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.course.OrderCourseSuccessActivity;
import com.idyoga.yoga.adapter.AppointmentDateAdapter;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.common.http.type1.HttpClient;
import com.idyoga.yoga.common.http.type1.HttpResponseHandler;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.listener.OnDateSelectListener;
import com.idyoga.yoga.model.AppointmentDateBean;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.utils.ToastUtil;
import com.idyoga.yoga.utils.YogaViewUtil;
import com.idyoga.yoga.view.YogaLayoutManager;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

/**
 * 文 件 名: ShopMarketCourseAppointmentActivity
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/22
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注： 店铺其他时间预约课程
 */
public class ShopMarketCourseAppointmentActivity extends BaseActivity {
    @BindView(R.id.ll_title_back)
    LinearLayout mLlTitleBack;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.ll_common_layout)
    RelativeLayout mLlCommonLayout;
    @BindView(R.id.lv_list)
    ListView mLvList;
    @BindView(R.id.tv_title_right)
    TextView mTvTitleRight;
    @BindView(R.id.ll_title_right)
    LinearLayout mLlTitleRight;
    private String shopId, lessonId;
    private String userId, token;
    private String Date, Time;
    AppointmentDateAdapter mAdapter;
    private List<AppointmentDateBean> mBeanList = new ArrayList<>();

    @Override
    protected void initData() {
        mBeanList.clear();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            shopId = bundle.getString("shopId");
            lessonId = bundle.getString("courseId");
            token = (String) SharedPreferencesUtils.getSP(this, "Token", "");
            userId = String.valueOf((int) SharedPreferencesUtils.getSP(this, "UserId", 0));
        } else {
            Logcat.e("缺少参数");
        }

    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlCommonLayout).init();
    }

    @Override
    protected YogaLayoutManager initLayoutManager() {
        return YogaLayoutManager.wrap(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_shop_course_appointment_time;
    }

    @Override
    protected void initView() {
        mTvTitleText.setText("选择上课时间");
        Calendar calendar = Calendar.getInstance();

        for (int i = 0; i < 12; i++) {
            mBeanList.add(new AppointmentDateBean(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1));
            calendar.add(Calendar.MONTH, +1);
        }
        mAdapter = new AppointmentDateAdapter(this, mBeanList, R.layout.item_appointment_date);
        mLvList.setAdapter(mAdapter);
    }

    @Override
    protected void setListener() {
        mAdapter.setSelectListener(new OnDateSelectListener() {
            @Override
            public void onItemClick(View view, int year, int month, int day) {
                showTime(year, month, day);
            }
        });
    }


    @OnClick({R.id.ll_title_back, R.id.ll_common_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_back:
                finish();
                break;
            case R.id.ll_common_layout:
                break;
        }
    }

    int selectIndex;
    String hours = "";

    /**
     *
     *
     * @param year
     * @param month
     * @param day
     */
    public void showTime(final int year, final int month, final int day) {
        final List<String> mOptionsItems = new ArrayList<>();

        /**早上 7点 - 晚上 8点*/
        for (int i = 7; i < 23; i++) {
            if (i < 10) {
                mOptionsItems.add("0" + i + ":00");
            } else {
                mOptionsItems.add(i + ":00");
            }
        }
        Calendar toDay = Calendar.getInstance();
//        if (toDay.get(Calendar.YEAR) == year && toDay.get(Calendar.MONTH) + 1 == month && toDay.get(Calendar.DATE) == day && toDay.get(Calendar.HOUR_OF_DAY) >= 23) {
//            ToastUtil.showToast("亲，夜深了。请预约明天的呢！");
//            return;
//        } else if (toDay.get(Calendar.YEAR) == year && toDay.get(Calendar.MONTH) + 1 == month && toDay.get(Calendar.DATE) == day) {
//            for (int i = toDay.get(Calendar.HOUR_OF_DAY) + 1; i < 25; i++) {
//                if (i < 10) {
//                    mOptionsItems.add("0" + i + ":00");
//                } else {
//                    mOptionsItems.add(i + ":00");
//                }
//            }
//        } else {
//            for (int i = 1; i < 25; i++) {
//                if (i < 10) {
//                    mOptionsItems.add("0" + i + ":00");
//                } else {
//                    mOptionsItems.add(i + ":00");
//                }
//            }
//        }
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_date_item);
        dialog.getWindow().setGravity(Gravity.CENTER | Gravity.BOTTOM);
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = YogaViewUtil.dp2px(mContext, 240);
        dialog.getWindow().setAttributes(params);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setWindowAnimations((R.style.popWindow_anim_style));
        if (mOptionsItems.size() > 0) {
            dialog.show();
        }
        final TextView mTvTitle = dialog.findViewById(R.id.tv_title);
        mTvTitle.setText(year + "年" + month + "月" + day + "日");
        WheelView wheelView = dialog.findViewById(R.id.wv_view);
        wheelView.setCyclic(true);
        wheelView.setCurrentItem(0);
        wheelView.setGravity(Gravity.CENTER);
        wheelView.setAdapter(new ArrayWheelAdapter(mOptionsItems));
        wheelView.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                Logcat.i("选择的时间：" + mOptionsItems.get(index));
                hours = mOptionsItems.get(index);
                mTvTitle.setText(year + "年" + month + "月" + day + "日" + hours + ":00时");
            }
        });
        dialog.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.tv_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logcat.i("选择的时间：" + mOptionsItems.get(selectIndex) + "/" + hours);
                String hour;
                if (!StringUtil.isEmpty(hours)) {
                    hour = hours.substring(0, 2);
                } else {
                    hour = mOptionsItems.get(0).substring(0, 2);
                }
                EventBus.getDefault().post(new PostResult("selectCourseTime",year + "年" + month + "月" + day + "日" + " " + hours));
                //submitData(year, month, day, Integer.valueOf(hour));
                dialog.dismiss();
                finish();
            }
        });
        dialog.show();
    }

    /*private void submitData(int year, int month, int day, int hours) {
        String s = year + "-" + month + "-" + day + " " + hours + ":00:00";
        Logcat.e("选择的日期：" + s);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = simpleDateFormat.parse(s);
            Logcat.e("d:" + date.toString());
            long ss = date.getTime() / 1000;
            Map map = new HashMap();
            map.put("userId", userId);
            map.put("lessonId", lessonId);
            map.put("shopId", shopId);
            map.put("expectTime", String.valueOf(ss));
            showLoading("正在提交数据...");
            Logcat.e("添加用户申请期望时间上课课程：" + map.toString());
            HttpClient.post(ApiConstants.Urls.CONSULT_LESSON, map, new HttpResponseHandler() {
                @Override
                public void onSuccess(String content) {
                    super.onSuccess(content);
                    Logcat.e("onSuccess：" + content);
                    dismissLoading();
                    ResultBean bean = JSON.parseObject(content, ResultBean.class);
                    if (bean.getCode().equals("1")) {
                        show();
                    } else {
                        ToastUtil.showToast(bean.getMsg());
                    }
                }

                @Override
                public void onFailure(Request request, IOException e) {
                    super.onFailure(request, e);
                    dismissLoading();
                }
            });
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }*/

    //提交成功
    public void show() {

        Bundle bundle = new Bundle();
        startActivity(OrderCourseSuccessActivity.class,bundle);
        finish();
        /*final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_sure);
        ((TextView)dialog.findViewById(R.id.tv_hint)).setText("咨询预约成功\n可在我的咨询里面查询预约记录");
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        dialog.findViewById(R.id.btn_confirm_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.showToast("咨询预约成功\n在我的咨询里面查询预约记录");
                dialog.dismiss();
                finish();
            }
        });*/
    }

}
