package com.idyoga.yoga.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.R;
import com.idyoga.yoga.common.adapter.CommonAdapter;
import com.idyoga.yoga.common.adapter.ViewHolder;
import com.idyoga.yoga.listener.OnDateSelectListener;
import com.idyoga.yoga.model.AppointmentDateBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vip.devkit.calendarview.CalendarDay;
import vip.devkit.calendarview.CalendarMonth;
import vip.devkit.calendarview.MonthView;
import vip.devkit.library.Logcat;

/**
 * 文 件 名: AppointmentDateAdapter
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/26
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class AppointmentDateAdapter extends CommonAdapter<AppointmentDateBean> {

    OnDateSelectListener mSelectListener;

    public void setSelectListener(OnDateSelectListener selectListener) {
        mSelectListener = selectListener;
    }

    public AppointmentDateAdapter(Context context, List<AppointmentDateBean> mBeanList, int layoutId) {
        super(context, mBeanList, layoutId);
    }

    @Override
    public void convert(ViewHolder helper, AppointmentDateBean bean, final int position) {
        helper.setText(R.id.tv_month, bean.getYear() + "年" + (bean.getMonth() + 1) + "月");
        final Calendar calendar = Calendar.getInstance();
        calendar.set(bean.getYear(), bean.getMonth(), 1);
        MonthView mMonthView = helper.getView(R.id.mv_view);
        initMonthDisable(mMonthView, bean);
        if (!bean.isSelect()) {
            mMonthView.setSelection(null);
        }
        CalendarMonth mCalendarMonth = new CalendarMonth(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1);
        mMonthView.setSelectionMode(MonthView.SELECTION_SINGLE);
        mMonthView.setYearAndMonth(mCalendarMonth);
        mMonthView.setStartDayOfWeek(1);
        mMonthView.setOnSelectionChangeListener(new MonthView.OnSelectionChangeListener() {
            @Override
            public void onSelectionChanged(MonthView monthView, CalendarDay[] now, CalendarDay[] old, @Nullable CalendarDay selection, boolean byUser) {
                if (mSelectListener != null && now.length > 0) {
                    mSelectListener.onItemClick(monthView, now[0].getYear(), now[0].getMonth(), now[0].getDay());
                    for (int i = 0; i < mBeanList.size(); i++) {
                        if (i != position) {
                            mBeanList.get(i).setSelect(false);
                        } else {
                            mBeanList.get(i).setSelect(true);
                        }
                    }
                    monthView.setSelection(selection);
                    notifyDataSetChanged();
                }
            }
        });
    }

    private void initMonthDisable(MonthView monthView, AppointmentDateBean bean) {
        Calendar calendar = Calendar.getInstance();
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        if (bean.getYear() == calendar.get(Calendar.YEAR) && (bean.getMonth() + 1) == calendar.get(Calendar.MONTH) + 1) {
            int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            Logcat.e("本月多少天：" + lastDay + " 今天是：" + calendar.get(Calendar.DATE) + "/" + hours);
            for (int x = 1; x <= lastDay; x++) {
                if (x <= calendar.get(Calendar.DATE)) {
                    monthView.setDayDisable(new CalendarDay(monthView.getCurrentMonth(), x));
                    if (hours > 20) {
                        monthView.setDayDisable(new CalendarDay(monthView.getCurrentMonth(), x + 1));
                    }
                }
            }
        }
    }
}
