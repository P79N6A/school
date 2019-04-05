package com.idyoga.yoga.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.idyoga.yoga.R;
import com.idyoga.yoga.model.State;

import java.util.List;

import vip.devkit.library.Logcat;

/**
 * Created by Administrator on 2019/03/24.
 *
 */

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {

    private List<State> mDatas = null;
    private OnCalendarClickListener mListener;
    private LayoutInflater mFrom;
    private Context mContext;

    public CalendarAdapter(Context context){
        mContext = context;
        mFrom = LayoutInflater.from(context);
    }

    public void setDataNum(List<State> datas) {
        this.mDatas = datas;
        Logcat.i("CalendarAdapter setDataNum = " + datas);
        notifyDataSetChanged();
    }

    @Override
    public CalendarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CalendarAdapter.ViewHolder viewHolder = new CalendarAdapter.ViewHolder(mFrom.inflate(R.layout.aa_calendar_item_layout, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CalendarAdapter.ViewHolder holder, int position) {
        Logcat.i("CalendarAdapter onBindViewHolder " );
        State state = mDatas.get(position);
        holder.mDataNum.setText(String.valueOf(state.getData() + 1));
        if (state.isClick()){
            holder.mDataNum.setSelected(true);
            holder.mDataNum.setTextColor(mContext.getResources().getColor(R.color.white));
        }else{
            holder.mDataNum.setSelected(false);
            holder.mDataNum.setTextColor(mContext.getResources().getColor(R.color.text_color));
        }
        holder.mDataNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tag = holder.getAdapterPosition();
                State state1 = mDatas.get(tag);
                if (!state1.isClick()){
                    for (int i = 0; i < mDatas.size(); i++) {
                        mDatas.get(i).setClick(false);
                    }
                    state1.setClick(true);
                    notifyDataSetChanged();
                    mListener.onClick(tag,String.valueOf(mDatas.get(tag).getData()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        Logcat.i("----------------------------------" + mDatas != null ? mDatas.size() : 0);
        int num = mDatas != null ? mDatas.size() : 0;
        return num;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mDataNum;

        public ViewHolder(View itemView) {
            super(itemView);
            mDataNum = itemView.findViewById(R.id.tv_calendar_num);
        }
    }

    public interface OnCalendarClickListener {
        void onClick(int position,String yearAndMonth);
    }

    public void setOnCalendarClickListener(OnCalendarClickListener listener){
        this.mListener = listener;
    }
}
