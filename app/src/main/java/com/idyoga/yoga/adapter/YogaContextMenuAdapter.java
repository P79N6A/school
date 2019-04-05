package com.idyoga.yoga.adapter;

import android.content.Context;


import com.idyoga.yoga.R;
import com.idyoga.yoga.common.adapter.CommonAdapter;
import com.idyoga.yoga.common.adapter.ViewHolder;
import com.idyoga.yoga.model.YogaMenuBean;

import java.util.List;

/**
 * 文 件 名: YogaContextMenuAdapter
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/4/27
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class YogaContextMenuAdapter extends CommonAdapter<YogaMenuBean> {

    public YogaContextMenuAdapter(Context context, List<YogaMenuBean> mBeanList, int layoutId) {
        super(context, mBeanList, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, YogaMenuBean contextMenuItemBean, int position) {
        holder.setText(R.id.tv_menu_item_text, contextMenuItemBean.getText());
        holder.setImageResource(R.id.iv_menu_item_icon, contextMenuItemBean.getIcon());
    }
}
