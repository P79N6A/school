package com.idyoga.yoga.holder;

import android.content.Context;
import android.content.MutableContextWrapper;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import com.idyoga.yoga.R;
import com.idyoga.yoga.adapter.OrtherCourseAdapter;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.model.CoureseDetailInfo;
import com.idyoga.yoga.view.ItemListView;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * @author weilgu
 * @time 2019/3/13 16:50
 * @des ${TODO}
 */

public class OrtherCourseHolder extends RecyclerView.ViewHolder {

    private Context mContext;
    private ItemListView mOrtherCourse;
    private OrtherCourseAdapter mAdapter;
    private List<CoureseDetailInfo.LessonList> beans;

    public OrtherCourseHolder(View itemView) {
        this(itemView,null);
    }

    public OrtherCourseHolder(View itemView, Context context) {
        super(itemView);
        this.mContext = context;
        mOrtherCourse = itemView.findViewById(R.id.rlv_orther_course);
        mAdapter = new OrtherCourseAdapter(mContext);
        mOrtherCourse.setAdapter(mAdapter);
        mOrtherCourse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CoureseDetailInfo.LessonList lessonList = beans.get(position);
                int courseId = lessonList.getId();
                Bundle bundle = new Bundle();
                bundle.putString("lessonId",String.valueOf(courseId));
                EventBus.getDefault().post(new PostResult("2CourseDetail",bundle));
            }
        });
    }


    public void bindView(List<CoureseDetailInfo.LessonList> bean) {
        this.beans = bean;
        mAdapter.setData(bean);
    }
}
