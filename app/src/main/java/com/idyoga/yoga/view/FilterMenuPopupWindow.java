package com.idyoga.yoga.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.idyoga.yoga.R;
import com.idyoga.yoga.common.adapter.CommonAdapter;
import com.idyoga.yoga.common.adapter.ViewHolder;
import com.idyoga.yoga.listener.OnPopupWindowListener;
import com.idyoga.yoga.listener.OnPopupWindowMoreListener;
import com.idyoga.yoga.model.PopupWindowItemBean;

import java.util.ArrayList;
import java.util.List;

import vip.devkit.library.Logcat;

/**
 * 文 件 名: FilterMenuPopupWindow
 * 创 建 人: By k
 * 创建日期: 2017/11/9 15:04
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注：
 */
public class FilterMenuPopupWindow {


    View contentView;
    View view;
    String tag;
    Context mContext;
    List mListBean;
    OnPopupWindowListener mPopupWindowListener;
    OnPopupWindowMoreListener mPopupWindowMoreListener;
    BgDarkPopupWindow mPopupWindow;

    public static FilterMenuPopupWindow init(Context context) {
        return new FilterMenuPopupWindow(context);
    }

    public FilterMenuPopupWindow(Context context) {
        mContext = context;
    }


    public FilterMenuPopupWindow setData(View contentView, View view, String tag, List list) {
        this.contentView=contentView;
        this.view=view;
        this.tag=tag;
        this.mListBean=list;
        return this;
    }

    public FilterMenuPopupWindow setContentView(View contentView) {
        if (contentView == null)
            throw new IllegalArgumentException("contentView不能为null");
        this.contentView = contentView;
        return this;
    }

    public FilterMenuPopupWindow setView(View view) {
        if (view == null)
            throw new IllegalArgumentException("view不能为null");
        this.view = view;
        return this;
    }

    public FilterMenuPopupWindow setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public FilterMenuPopupWindow setList(List list) {
        if (list == null)
            throw new IllegalArgumentException("数据不能为null");
        this.mListBean = list;
        return this;
    }

    public FilterMenuPopupWindow setPopupWindowListener(OnPopupWindowListener popupWindowListener) {
        this.mPopupWindowListener = popupWindowListener;
        return this;
    }

    public FilterMenuPopupWindow setPopupWindowMoreListener(OnPopupWindowMoreListener popupWindowMoreListener) {
        this.mPopupWindowMoreListener = popupWindowMoreListener;
        return this;
    }

    public FilterMenuPopupWindow build() {
        if (tag.equals("1")) {
            initSexView();
        } else if (tag.equals("2")) {

        }
        return this;
    }
     List<PopupWindowItemBean> list = new ArrayList<>();
    private void initSexView() {
        final ListView listView = (ListView) contentView;
        list.clear();
        list.addAll(mListBean);
        final CommonAdapter commonAdapter;
        listView.setAdapter(commonAdapter = new CommonAdapter<PopupWindowItemBean>(mContext, list, R.layout.item_setting_city) {
            @Override
            public void convert(ViewHolder holder, PopupWindowItemBean bean, int position) {
                holder.setText(R.id.tv_city_name, bean.getName());
                TextView textView = holder.getView(R.id.tv_city_name);
                if (bean.isVisibility() == true) {
                    holder.getView(R.id.iv_img).setVisibility(View.VISIBLE);
                    textView.setBackgroundColor(mContext.getResources().getColor(R.color.theme_1));
                } else {
                    holder.getView(R.id.iv_img).setVisibility(View.GONE);
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                for (int j = 0; j < list.size(); j++) {
                    if (j == i) {
                        list.get(j).setVisibility(true);
                    } else {
                        list.get(j).setVisibility(false);
                    }
                }
                if (mPopupWindowListener!=null){
                    mPopupWindowListener.onItemClick(mPopupWindow,view,list.get(i),i);
                }
                commonAdapter.notifyDataSetChanged();
            }
        });

    }


    public void show() {
        Logcat.e("还未初始化或设置参数");
        if (mPopupWindow == null) {
            Logcat.e("还未初始化或设置参数");
        } else {
            if (contentView == null)
                throw new IllegalArgumentException("contentView 不能为null");
            if (view == null)
                throw new IllegalArgumentException("view 不能为null");
            mPopupWindow = new BgDarkPopupWindow(mContext, contentView);
            mPopupWindow.setFocusable(true);
            mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
            mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
            mPopupWindow.setDarkStyle(-1);
            mPopupWindow.setDarkColor(Color.parseColor("#a0000000"));
            mPopupWindow.resetDarkPosition();
            mPopupWindow.darkBelow(view);
            mPopupWindow.showAsDropDown(view, 0, 0);
        }
    }

    public void dismiss() {
        if (mPopupWindow == null) {
            Logcat.e("还未初始化或设置参数");
        } else {
            mPopupWindow.dismiss();
        }
    }


}
