package com.idyoga.yoga.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.R;
import com.idyoga.yoga.adapter.base.RvCommonAdapter;
import com.idyoga.yoga.adapter.base.ViewHolder;
import com.idyoga.yoga.model.CityBean;

import java.util.List;

/**
 * 文 件 名: CitySettingAdapter
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/12
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class CitySettingAdapter2 extends RvCommonAdapter<CityBean.CityListBean> {

    public CitySettingAdapter2(Context context,int layoutResId, @Nullable List<CityBean.CityListBean> data) {
        super(context,layoutResId, data);
    }

    @Override
    public void convert(ViewHolder helper, CityBean.CityListBean item) {
        helper.setText(R.id.tv_city_name, item.getName());
        if (item.isSelect()==true) {
            helper.getView(R.id.iv_img).setVisibility(View.VISIBLE);
        }else {
            helper.getView(R.id.iv_img).setVisibility(View.GONE);
        }
    }
}
