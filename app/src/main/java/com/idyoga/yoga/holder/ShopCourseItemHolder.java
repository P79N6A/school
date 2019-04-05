package com.idyoga.yoga.holder;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.idyoga.yoga.R;
import com.idyoga.yoga.adapter.OrderCourseListAdapter;
import com.idyoga.yoga.adapter.ShopCardAdapter;
import com.idyoga.yoga.adapter.ShopInCourseAdapter;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.model.LessonListBean;
import com.idyoga.yoga.model.ShopDetailDataBean;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * @author weilgu
 * @time 2019/3/13 10:32
 * @des ${TODO}
 */

public class ShopCourseItemHolder extends RecyclerView.ViewHolder{

    private Context mContext;
    private TextView mType;
    private TextView mTable;
    private RecyclerView mCourse;
    private TextView mMore;
    private ShopInCourseAdapter mAdapter;
    private List<LessonListBean> mLessonList;
    private List<LessonListBean> mCopyLessonList = new ArrayList<>();
    private boolean isShowMoreData = false;

    public ShopCourseItemHolder(View itemView) {
        this(itemView,null);
    }

    public ShopCourseItemHolder(View itemView, Context context) {
        super(itemView);
        this.mContext = context;
        this.mType = itemView.findViewById(R.id.tv_type);
        this.mTable = itemView.findViewById(R.id.tv_table);
        this.mCourse = itemView.findViewById(R.id.rlv_course);
        this.mMore = itemView.findViewById(R.id.tv_more);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mCourse.setLayoutManager(layoutManager);
        mAdapter = new ShopInCourseAdapter(mContext);
        mCourse.setAdapter(mAdapter);
        //查看课表
        mTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("action",String.valueOf(0));
                EventBus.getDefault().post(new PostResult("2OrderCourseListActivity",bundle));
            }
        });
        //查看更多
        mMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isShowMoreData){
                    mAdapter.setData(mLessonList);
                    isShowMoreData = true;
                    mMore.setVisibility(View.GONE);
                    EventBus.getDefault().post(new PostResult("notifyShopAdapterChanger"));
                }
            }
        });
    }

    public void bindView(List<LessonListBean> lessonList){
        mLessonList = lessonList;
        if (mLessonList != null && mLessonList.size() <= 2){
            mMore.setVisibility(View.GONE);
            mAdapter.setData(lessonList);
        }else if (mLessonList != null && mLessonList.size() > 2){
            mMore.setVisibility(View.VISIBLE);
            mCopyLessonList.add(lessonList.get(0));
            mCopyLessonList.add(lessonList.get(1));
            mAdapter.setData(mCopyLessonList);
        }
    }
}
