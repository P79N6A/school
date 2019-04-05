package com.idyoga.yoga.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.idyoga.yoga.R;
import com.idyoga.yoga.holder.CourseInfoHolder;
import com.idyoga.yoga.holder.CourseRequireHolder;
import com.idyoga.yoga.holder.OrtherCourseHolder;
import com.idyoga.yoga.model.CoureseDetailInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author weilgu
 * @time 2019/3/13 16:00
 * @des 课程详情 adapter
 */

public class CourseDetailAdapter extends RecyclerView.Adapter{

    private Context mContext;
    private static final int COURSE_INFO = 0; //课程信息
    private static final int ORTHER_COURSE = 1;//其他课程
    private static final int ORTHER_BANNER = 2;//图片
    private final LayoutInflater mFrom;
    private List<Object> mDatas;

    public CourseDetailAdapter(Context context) {
        mContext = context;
        mFrom = LayoutInflater.from(mContext);
        mDatas = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == COURSE_INFO){
            return new CourseInfoHolder(mFrom.inflate(R.layout.item_course_detail,parent,false),mContext);
        }else if(viewType == ORTHER_COURSE){
            return new OrtherCourseHolder(mFrom.inflate(R.layout.item_orther_course_layout,parent,false),mContext);
        }else if(viewType == ORTHER_BANNER){
            return new CourseRequireHolder(mFrom.inflate(R.layout.item_course_detail_banner,parent,false),mContext);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Object o = mDatas.get(position);
        int viewType = getItemViewType(position);
        if (viewType == COURSE_INFO){
            ((CourseInfoHolder)holder).bindView((CoureseDetailInfo)o);
        }else if(viewType == ORTHER_COURSE){
            ((OrtherCourseHolder)holder).bindView((List<CoureseDetailInfo.LessonList>)o);
        }else if(viewType == ORTHER_BANNER){
            ((CourseRequireHolder)holder).bindView((List<CoureseDetailInfo.Banner>)o);
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = -1;
        Object obj = mDatas.get(position);
        if (obj instanceof CoureseDetailInfo){
            viewType = COURSE_INFO;
        }else if (obj instanceof List){
            List list = (List) obj;
            if (list.size() > 0){
                Object o = list.get(0);
                if(o instanceof CoureseDetailInfo.Banner){
                    viewType = ORTHER_BANNER;
                }else if(o instanceof CoureseDetailInfo.LessonList){
                    viewType = ORTHER_COURSE;
                }
            }
        }
        return viewType;
    }

    public void setDatas(CoureseDetailInfo datas) {
        if (datas != null){
            List<CoureseDetailInfo.Banner> banner = datas.getBanner();
            List<CoureseDetailInfo.LessonList> lessonList = datas.getLessonList();
            if (banner != null && banner.size() > 0){
                mDatas.add(banner);
            }
            mDatas.add(datas);
            if (lessonList != null && lessonList.size() > 0){
                mDatas.add(lessonList);
            }
            notifyDataSetChanged();
        }
    }
}
