package com.idyoga.yoga.adapter;

import android.content.Context;
import android.graphics.Color;

import com.idyoga.yoga.R;
import com.idyoga.yoga.common.adapter.CommonAdapter;
import com.idyoga.yoga.common.adapter.ViewHolder;
import com.idyoga.yoga.common.view.Label.LabelLinearLayout;
import com.idyoga.yoga.model.UserVipCardBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
public class UserCardAdapter extends CommonAdapter<UserVipCardBean> {
    int type;

    public UserCardAdapter(Context context, List<UserVipCardBean> mBeanList, int layoutId, int type) {
        super(context, mBeanList, layoutId);
        this.type = type;
    }


    @Override
    public void convert(ViewHolder holder, UserVipCardBean bean, int i) {
        LabelLinearLayout mLabelL = holder.getView(R.id.ll_label);
//        mLabelL.setLabelVisable(true);

        Logcat.i("是否有效：" + bean.getExpire_time() + "/" + DateUtil.genTimeStamp());
        if (bean.getExpire_time() > DateUtil.genTimeStamp()) {
            Logcat.i("是否有效：" + 1);
            mLabelL.setTextContent("有效");
            mLabelL.setLabelBackGroundColor(Color.parseColor("#b96bb0"));
            mLabelL.setBackgroundResource(R.drawable.bg_card_02);
        } else {
            Logcat.i("是否有效：" + 2);
            mLabelL.setTextContent("过期");
            mLabelL.setLabelBackGroundColor(Color.parseColor("#FF989898"));
            mLabelL.setBackgroundResource(R.drawable.bg_card_03);
//            mLabelL.setLabelVisable(false);
        }
        holder.setText(R.id.tv_card_name, bean.getCard_name())
                .setText(R.id.tv_card_type, "类型：" + "")
                .setText(R.id.tv_card_shop, "有效瑜伽馆：" + "")
                .setText(R.id.tv_card_course, "有效课程：" + "")
                .setText(R.id.tv_card_num, "有效次数：" + bean.getValid_time())
                .setText(R.id.tv_card_date, "有效时间：" + getDateStringByTimeSTamp(Long.valueOf(bean.getExpire_time()), "yyyy-MM-dd"));
    }

    /**
     * 将时间戳转成日期字符串
     *
     * @param timeStamp 时间戳的值,类型为：Long
     * @param pattern   转成字符串的格式
     * @return
     */
    public static String getDateStringByTimeSTamp(Long timeStamp, String pattern) {
        String result = null;
        Date date = new Date(timeStamp * 1000);
        SimpleDateFormat sd = new SimpleDateFormat(pattern);
        result = sd.format(date);
        return result;
    }
}
