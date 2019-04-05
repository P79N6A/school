package com.idyoga.yoga.holder;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.idyoga.yoga.R;
import com.idyoga.yoga.adapter.TeacherInItemAdapter;
import com.idyoga.yoga.model.ShopDetailDataBean;
import com.idyoga.yoga.model.TutorListBeans;

import java.util.List;

/**
 * @author weilgu
 * @time 2019/3/13 11:09
 * @des ${TODO}
 */

public class TeacherHolder extends RecyclerView.ViewHolder {

    private Context mContext;
    private RecyclerView mRlv;
    private TextView mType;
    private TeacherInItemAdapter mAdapter;

    public TeacherHolder(View itemView) {
        this(itemView,null);
    }

    public TeacherHolder(View itemView, Context context) {
        super(itemView);
        this.mContext = context;
        this.mRlv = itemView.findViewById(R.id.rlv_teacher);
        this.mType = itemView.findViewById(R.id.tv_type);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRlv.setLayoutManager(layoutManager);
        mAdapter = new TeacherInItemAdapter(mContext);
        mRlv.setAdapter(mAdapter);
        mType.setText("导师");
    }

    public void bindView(List<TutorListBeans> beans){
        mAdapter.setData(beans);
    }
}
