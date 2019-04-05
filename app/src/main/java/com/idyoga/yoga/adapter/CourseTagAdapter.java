package com.idyoga.yoga.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.idyoga.yoga.R;
import com.idyoga.yoga.holder.TagHorizontaHolder;
import com.idyoga.yoga.holder.TagVerticalHolder;

/**
 * @author weilgu
 * @time 2019/3/14 9:48
 * @des ${TODO}
 */

public class CourseTagAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private int mViewType = 0;
    private final LayoutInflater mFrom;
    private static final int HORIZONTA_LAYOUT = 0;
    private static final int VERTICAL_LAYOUT = 1;

    public CourseTagAdapter(Context context, int viewType){
        this.mContext = context;
        this.mViewType = viewType;
        mFrom = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HORIZONTA_LAYOUT){
            return new TagHorizontaHolder(mFrom.inflate(R.layout.item_tag_horizonta_layout,parent,false),mContext);
        }else {
            return new TagVerticalHolder(mFrom.inflate(R.layout.item_tag_vertica_layout,parent,false),mContext);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == HORIZONTA_LAYOUT){
            ((TagHorizontaHolder)holder).bindView();
        }else {
            ((TagVerticalHolder)holder).bindView();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mViewType;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
