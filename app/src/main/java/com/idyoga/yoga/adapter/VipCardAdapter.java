package com.idyoga.yoga.adapter;

import android.content.Context;
import android.view.View;

import com.idyoga.yoga.R;
import com.idyoga.yoga.common.adapter.CommonAdapter;
import com.idyoga.yoga.common.adapter.ViewHolder;
import com.idyoga.yoga.common.view.Label.LabelLinearLayout;
import com.idyoga.yoga.model.VIPCardBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import vip.devkit.library.DateUtil;
import vip.devkit.library.Logcat;

/**
 * 文 件 名: VipCardAdapter
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/3/22
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class VipCardAdapter extends CommonAdapter<VIPCardBean> {
    int type;
    public VipCardAdapter(Context context, List<VIPCardBean> mBeanList, int layoutId,int type) {
        super(context, mBeanList, layoutId);
        this.type=type;
    }

    @Override
    public void convert(ViewHolder holder, VIPCardBean vipCardBean, int i) {
        LabelLinearLayout mLabelL = holder.getView(R.id.ll_label);
        mLabelL.setLabelVisable(false);
        if (type == 1) {
//            mLabelL.setTextContent("未使用");
            mLabelL.setBackgroundResource(R.drawable.card_zbg);
        } else if (type == 2) {
//            mLabelL.setTextContent("已过期");
            mLabelL.setBackgroundResource(R.drawable.overdue_card_bg);
        } else {
            mLabelL.setLabelVisable(false);
        }
        Logcat.i(i+"/"+vipCardBean.getExpire_time());
        if (vipCardBean.isActivation()){
            View view = holder.getView(R.id.ll_activation_card);
            View view1 = holder.getView(R.id.ll_label);
            view.setVisibility(View.VISIBLE);
            view1.setVisibility(View.GONE);
        }else{
            View view = holder.getView(R.id.ll_activation_card);
            View view1 = holder.getView(R.id.ll_label);
            view.setVisibility(View.GONE);
            view1.setVisibility(View.VISIBLE);
            holder.setText(R.id.tv_vip_name, vipCardBean.getCard_name())
                    .setText(R.id.tv_num,"总次数： "+vipCardBean.getHas_time()+" (有效"+vipCardBean.getValid_time()+"次)")
                    .setText(R.id.tv_date,"有效期： "+ getDateStringByTimeSTamp((long) vipCardBean.getExpire_time(),"yyyy-MM-dd"));
            View date = holder.getView(R.id.tv_date);
            View v_heng = holder.getView(R.id.v_heng);
            date.setAlpha(0.7f);
            v_heng.setAlpha(0.7f);
        }

    }
    /**
     * 将时间戳转成日期字符串
     * @param timeStamp 时间戳的值,类型为：Long
     * @param pattern 转成字符串的格式
     * @return
     */
    public static String getDateStringByTimeSTamp(Long timeStamp,String pattern){
        String result = null;
        Date date = new Date(timeStamp*1000);
        SimpleDateFormat sd = new SimpleDateFormat(pattern);
        result = sd.format(date);
        return result;
    }
}
