package com.idyoga.yoga.adapter;

import android.content.Context;
import android.view.View;

import com.idyoga.yoga.R;
import com.idyoga.yoga.common.adapter.CommonAdapter;
import com.idyoga.yoga.common.adapter.ViewHolder;
import com.idyoga.yoga.model.ChildCardListBean;
import com.idyoga.yoga.utils.CommonUtils;

import java.util.List;

import vip.devkit.library.StringUtil;

/**
 * 文 件 名: ChildCardAdapter
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/12
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class ChildCardAdapter extends CommonAdapter<ChildCardListBean.CardListBean> {

    private String cardName;

    public ChildCardAdapter(Context context, List<ChildCardListBean.CardListBean> mBeanList, int layoutId) {
        super(context, mBeanList, layoutId);
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    @Override
    public void convert(ViewHolder holder, ChildCardListBean.CardListBean bean, int position) {
        if (bean.isAdd()) {
            holder.getView(R.id.ll_add_layout).setVisibility(View.VISIBLE);
            holder.getView(R.id.ll_label).setVisibility(View.GONE);
            if (!StringUtil.isEmpty(cardName)) {
                holder.setText(R.id.tv_child_card_name, cardName);
            }
        } else {
            holder.getView(R.id.ll_add_layout).setVisibility(View.GONE);
            holder.getView(R.id.ll_label).setVisibility(View.VISIBLE);
            holder.setText(R.id.tv_card_name, bean.getCard_name())
                    .setText(R.id.tv_name, "·姓名:" + bean.getUsername())
                    .setText(R.id.tv_address, "·地区:" + "")
                    .setText(R.id.tv_mobile, "·手机号码：" + bean.getMobile())
                    .setText(R.id.tv_date, "·有效期：" + CommonUtils.getDateStringByTimeSTamp((long) bean.getExpire_time(), "yyyy-MM-dd"));

        }
    }
}
