package com.idyoga.yoga.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.idyoga.yoga.R;
import com.idyoga.yoga.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

import vip.devkit.library.Logcat;

/**
 * @author weilgu
 * @time 2019/3/14 16:55
 * @des 单行横向滚动的日历
 */

public class HorizontalCalendar extends LinearLayout implements View.OnClickListener {

    private TextView mTitle;
    private String mMonth;
    private String mYear;
    private RecyclerView mRlv;
    private CalendarAdapter mAdapter;
    private static int sDataNum ;
    private final List<State> DATAS = new ArrayList<>();
    private List<State> mDatas = new ArrayList<>();
    private OnCalendarClickListener mListener;
    private LayoutInflater mFrom;

    public HorizontalCalendar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /*mFrom = LayoutInflater.from(context);
        View view = LayoutInflater.from(context).inflate(R.layout.view_horizontal_calendar_layout, this, true);
        LinearLayout llLeft = view.findViewById(R.id.ll_left);
        LinearLayout llRight = view.findViewById(R.id.ll_right);
        mTitle = view.findViewById(R.id.tv_time);
        mRlv = view.findViewById(R.id.rlv);
        llLeft.setOnClickListener(this);
        llRight.setOnClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mAdapter = new CalendarAdapter();
        mRlv.setLayoutManager(layoutManager);
        mRlv.setAdapter(mAdapter);*/
    }

    public HorizontalCalendar(Context context) {
        this(context,null);
    }

    public HorizontalCalendar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public void initData() {
        /*for (int i = 0; i < 31; i++) {
            DATAS.add(new State());
        }
        long timeMillis = System.currentTimeMillis();
        String time = DateUtils.time(String.valueOf(timeMillis / 1000));
        String[] split1 = time.split(" ");
        Logcat.i("---------------split1-----------" + split1);
        if (split1 != null && split1.length >= 2 ){
            String[] split = split1[0].split("-");
            mYear = split[0]; //年
            mMonth = split[1]; //月
            String day = split[2]; //日
            mTitle.setText(mYear + " 年 " + mMonth + " 月");
            sDataNum = getDataNum(mMonth, mYear);
            for (int i = 0; i < sDataNum; i++) {
                State state = DATAS.get(i);
                state.setData(i);
                if ((i + 1 ) == Integer.valueOf(day)){
                    state.setClick(true);
                }
                mDatas.add(state);
            }
            mAdapter.setDataNum(mDatas);
            mRlv.scrollToPosition(Integer.valueOf(day) - 1);
        }*/
    }

    private int getDataNum(String s, String year) {
        int data = 0;
        switch (Integer.valueOf(s)){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return data = 31;
            case 2:
                if (Integer.valueOf(year) % 400 == 0 ){
                    return data = 29;
                }else if(Integer.valueOf(year) % 100 != 0 && Integer.valueOf(year) % 4 == 0){
                    return data = 29;
                }else{
                    return data = 28;
                }
            case 4:
            case 6:
            case 9:
            case 11:
                return data = 30;
        }
        return 0;
    }

    public void setOnCalendarClickListener(OnCalendarClickListener listener){
        this.mListener = listener;
    }

    @Override
    public void onClick(View view) {
        String timeStr = mTitle.getText().toString();
        String sub = timeStr.substring(0, 4);
        switch (view.getId()) {
            case R.id.ll_left:
                Integer s = Integer.valueOf(mMonth);
                s -= 1;
                if (s < 1){
                    s = 12;
                    if (Integer.valueOf(sub) - 1 >= 1970){
                        mYear = String.valueOf(Integer.valueOf(sub) - 1 );
                    }else{
                        return;
                    }
                }
                mMonth = String.valueOf(s);
                sDataNum = getDataNum(String.valueOf(s), mYear);
                mDatas.clear();
                for (int i = 0; i < sDataNum; i++) {
                    mDatas.add(DATAS.get(i));
                }
                mTitle.setText(mYear + " 年 " + s + " 月");
                mAdapter.setDataNum(mDatas);
                break;
            case R.id.ll_right:
                Integer ss = Integer.valueOf(mMonth);
                ss += 1;
                if (ss > 12){
                    ss = 1;
                    if (Integer.valueOf(sub) + 1 <= 2119){
                        mYear = String.valueOf(Integer.valueOf(sub) + 1);
                    }else{
                        return;
                    }
                }
                mMonth = String.valueOf(ss);
                sDataNum = getDataNum(String.valueOf(ss), mYear);
                mDatas.clear();
                for (int i = 0; i < sDataNum; i++) {
                    mDatas.add(DATAS.get(i));
                }
                mTitle.setText(mYear + " 年 " + ss + " 月");
                mAdapter.setDataNum(mDatas);
                break;
        }
    }

    class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder>{

        private List<State> mDatasss = new ArrayList<>();

        public void setDataNum(List<State> datas) {
            mDatasss.clear();
            this.mDatasss.addAll(datas);
            Logcat.i("CalendarAdapter setDataNum = " + datas);
            CalendarAdapter.this.notifyDataSetChanged();
        }

        @Override
        public CalendarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewHolder viewHolder = new ViewHolder(mFrom.inflate(R.layout.aa_calendar_item_layout, parent, false));
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final CalendarAdapter.ViewHolder holder, int position) {
            Logcat.i("CalendarAdapter onBindViewHolder " );
            State state = mDatasss.get(position);
            holder.mDataNum.setText(String.valueOf(state.getData() + 1));
            holder.mDataNum.setTag(position);
            if (state.isClick){
                holder.mDataNum.setSelected(true);
            }else{
                holder.mDataNum.setSelected(false);
            }
            holder.mDataNum.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int tag = (int) holder.mDataNum.getTag();
                    State state1 = mDatasss.get(tag);
                    if (!state1.isClick){
                        for (int i = 0; i < mDatasss.size(); i++) {
                            mDatasss.get(i).setClick(false);
                        }
                        state1.setClick(true);
                        holder.mDataNum.setSelected(true);
                        notifyDataSetChanged();
                        mListener.onClick(tag,mTitle.getText().toString());
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            Logcat.i("CalendarAdapter getItemCount " + mDatas != null ? mDatasss.size() : 0);
            return mDatasss != null ? mDatasss.size() : 0;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView mDataNum;

            public ViewHolder(View itemView) {
                super(itemView);
                mDataNum = itemView.findViewById(R.id.tv_calendar_num);
            }
        }
    }

    class State {

        private boolean isClick;
        private int data;

        public boolean isClick() {
            return isClick;
        }

        public void setClick(boolean click) {
            isClick = click;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }
    }

    public interface OnCalendarClickListener {
        void onClick(int position,String yearAndMonth);
    }
}
