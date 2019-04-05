package com.idyoga.yoga.adapter;

import android.content.Context;
import android.view.View;

import com.idyoga.yoga.R;
import com.idyoga.yoga.common.adapter.CommonAdapter;
import com.idyoga.yoga.common.adapter.ViewHolder;
import com.idyoga.yoga.listener.OnLoadMoreListener;
import com.idyoga.yoga.model.ShopMarketCourseInfoBean;
import com.idyoga.yoga.utils.CommonUtils;

import java.util.List;

import vip.devkit.library.Logcat;

/**
 * 文 件 名: ShopMarketCourseDateAdapter
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/22
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class ShopMarketCourseDateAdapter extends CommonAdapter<ShopMarketCourseInfoBean.CourseTimeBean> {

    private int count;
    private OnLoadMoreListener mLoadMoreListener;

    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        mLoadMoreListener = loadMoreListener;
    }

    public ShopMarketCourseDateAdapter(Context context, List<ShopMarketCourseInfoBean.CourseTimeBean> mBeanList, int layoutId) {
        super(context, mBeanList, layoutId);
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public void convert(ViewHolder holder, ShopMarketCourseInfoBean.CourseTimeBean bean, final int position) {


        holder.setText(R.id.tv_course_info,
                CommonUtils.getDateStringByTimeSTamp((long) bean.getStart_time(), "yy年MM月dd日") +"  "+
                     CommonUtils.getDateStringByTimeSTamp((long) bean.getStart_time(), "HH:mm") +"-"+
                     CommonUtils.getDateStringByTimeSTamp((long) bean.getEnd_time(), "HH:mm") +"  "+
                      bean.getTutorName());


        Logcat.e(position + "/" + mBeanList.size() + "/" + count);
        if (count > mBeanList.size() && position == mBeanList.size() - 1) {
            holder.getView(R.id.ll_foot_layout).setVisibility(View.VISIBLE);
        } else {
            holder.getView(R.id.ll_foot_layout).setVisibility(View.GONE);
        }
        holder.getView(R.id.ll_foot_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mLoadMoreListener != null) {
                    mLoadMoreListener.onLoadMore(0, view, position);
                }
            }
        });
    }


}
