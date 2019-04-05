package com.idyoga.yoga.holder;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.idyoga.yoga.R;
import com.idyoga.yoga.adapter.ShopCardAdapter;
import com.idyoga.yoga.model.ShopDetailDataBean;

import java.util.List;

/**
 * @author weilgu
 * @time 2019/3/12 18:13
 * @des ${TODO}
 */

public class ShowMoreDataHolder extends RecyclerView.ViewHolder {

    private Context mContext;
    private TextView mType;
    private TextView mTable;
    private RecyclerView mCourse;
    private TextView mMore;

    public ShowMoreDataHolder(View itemView) {
        this(itemView,null);
    }

    public ShowMoreDataHolder(View itemView, Context context) {
        super(itemView);
        this.mContext = context;
        this.mType = itemView.findViewById(R.id.tv_type);
        this.mTable = itemView.findViewById(R.id.tv_table);
        this.mCourse = itemView.findViewById(R.id.rlv_course);
        this.mMore = itemView.findViewById(R.id.tv_more);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ShopCardAdapter adapter = new ShopCardAdapter(mContext);
        mCourse.setAdapter(adapter);
    }

    public void bindView(List<Object> beans){

    }
}
