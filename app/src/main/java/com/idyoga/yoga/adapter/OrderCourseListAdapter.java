package com.idyoga.yoga.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.R;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.holder.BaseHolder;
import com.idyoga.yoga.model.BaseCourseBean;
import com.idyoga.yoga.model.CourseListData;
import com.idyoga.yoga.model.SomeCourseBean;
import com.idyoga.yoga.utils.DateUtils;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * @author weilgu
 * @time 2019/3/15 14:57
 * @des 课程表
 */

public class OrderCourseListAdapter extends BaseQuickAdapter<BaseCourseBean,BaseViewHolder>{ //RecyclerView.Adapter<OrderCourseListAdapter.ViewHolder> {

    private Context mContext;
//    private final LayoutInflater mFrom;
//    private List<BaseCourseBean> mDatas;
    private int mAction = 0; //0 ://店铺详情查看所有课程课表  1: //查看某节课的课表

//    public OrderCourseListAdapter(Context context,int action){
    public OrderCourseListAdapter(Context context, List<BaseCourseBean> datas, @LayoutRes int layoutId){
        super(layoutId,datas);
        this.mContext = context;
//        mFrom = LayoutInflater.from(mContext);
//        mAction = action;
    }

    @Override
    protected void convert(final BaseViewHolder helper, BaseCourseBean item) {
        if (item.getBeanType() == 1){ //查看某节课的课表
            final SomeCourseBean some = (SomeCourseBean) item;
            StringBuilder sb = new StringBuilder();
            List<SomeCourseBean.Tutor> tutors = some.getTutor();
            if (tutors != null){
                for (int i = 0; i < tutors.size(); i++) {
                    sb.append(tutors.get(i).getName());
                    if (i < tutors.size() - 1){
                        sb.append(",");
                    }
                }
            }
            Glide.with(mContext)
                    .load(some.getLesson_image())
                    .placeholder(R.drawable.img_course)
                    .error(R.drawable.img_course)
                    .into((ImageView) helper.getView(R.id.iv_course_image));
            String start = DateUtils.timesTwo1(String.valueOf(some.getStart_time()));
            String end = DateUtils.timesTwo1(String.valueOf(some.getEnd_time()));
            helper.setText(R.id.tv_course_name,start + "~" + end)
                    .setText(R.id.tv_teacher_name,sb.toString())
                    .setText(R.id.tv_people_num,"剩余" + some.getLast_number() + "名额")
                    .setText(R.id.tv_order_people_num,"剩余" + some.getLast_number() + "名额");
            helper.getView(R.id.tv_order_people_num).setVisibility(View.GONE);
            helper.getView(R.id.tv_time).setVisibility(View.GONE);
            helper.getView(R.id.iv_right).setVisibility(View.GONE);
            helper.getView(R.id.iv_select).setVisibility(View.VISIBLE);
            helper.getView(R.id.tv_select).setVisibility(View.VISIBLE);
            helper.getView(R.id.iv_select).setSelected(some.isSelect() ? true : false);
            helper.getView(R.id.iv_select).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < mData.size(); i++) {
                        SomeCourseBean data = (SomeCourseBean) mData.get(i);
                        if (helper.getLayoutPosition() == i){
                            if (some.isSelect()){
                                helper.getView(R.id.iv_select).setSelected(false);
                                data.setSelect(false);
                            }else{
                                helper.getView(R.id.iv_select).setSelected(true);
                                data.setSelect(true);
                                PostResult postResult = new PostResult();
                                postResult.setTag("selectCourse");
                                postResult.setResult(data);
                                EventBus.getDefault().post(postResult);
                            }
                        }else{
                            data.setSelect(false);
                        }
                    }
                    notifyDataSetChanged();
                }
            });
        }else if (item.getBeanType() == 0){//店铺详情查看所有课程课表
            final CourseListData listData = (CourseListData) item;
            String start = DateUtils.timesTwo1(String.valueOf(listData.getStart_time()));
            String end = DateUtils.timesTwo1(String.valueOf(listData.getEnd_time()));
            helper.setText(R.id.tv_course_name,listData.getLessonName())
                    .setText(R.id.tv_teacher_name,listData.getTutorName())
                    .setText(R.id.tv_people_num,"剩余" + listData.getResidueNumber() + "名额")
                    .setText(R.id.tv_order_people_num,"剩余" + listData.getAppointmentNum() + "名额")
                    .setText(R.id.tv_time,start + "~" + end);
            Glide.with(mContext)
                    .load(listData.getImage())
                    .placeholder(R.drawable.img_course)
                    .error(R.drawable.img_course)
                    .into((ImageView) helper.getView(R.id.iv_course_image));
            helper.getView(R.id.tv_time).setVisibility(View.VISIBLE);
            helper.getView(R.id.tv_order_people_num).setVisibility(View.VISIBLE);
            helper.getView(R.id.iv_right).setVisibility(View.VISIBLE);
            helper.getView(R.id.tv_select).setVisibility(View.GONE);
            helper.getView(R.id.iv_select).setVisibility(View.GONE);
        }
        helper.getView(R.id.cl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = helper.getLayoutPosition();
                BaseCourseBean courseBean = mData.get(index);
                Bundle bundle = new Bundle();
                bundle.putString("tag","");
                if (courseBean.getBeanType() == 1){ //查看某节课的课表
                    SomeCourseBean some = (SomeCourseBean) courseBean;
                    bundle.putString("lessonId",String.valueOf(some.getLesson_id()));
                } else {
                    CourseListData listData = (CourseListData) courseBean;
                    bundle.putString("lessonId",String.valueOf(listData.getCourse_id()));
                }
                EventBus.getDefault().post(new PostResult("2CourseDetail",bundle));
            }
        });
    }

    /*@Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mFrom.inflate(R.layout.item_order_course_list_layout,parent,false));
    }*/

    /*@Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        BaseCourseBean baseCourseBean = mDatas.get(position);
        if (baseCourseBean.getBeanType() == 1){ //查看某节课的课表
            final SomeCourseBean some = (SomeCourseBean) baseCourseBean;
            StringBuilder sb = new StringBuilder();
            List<SomeCourseBean.Tutor> tutors = some.getTutor();
            if (tutors != null){
                for (int i = 0; i < tutors.size(); i++) {
                    sb.append(tutors.get(i).getName());
                    if (i < tutors.size() - 1){
                        sb.append(",");
                    }
                }
            }
            Glide.with(mContext)
                    .load(some.getLesson_image())
                    .placeholder(R.drawable.img_course)
                    .error(R.drawable.img_course)
                    .into(holder.mCourseImage);
            String start = DateUtils.timesTwo1(String.valueOf(some.getStart_time()));
            String end = DateUtils.timesTwo1(String.valueOf(some.getEnd_time()));
            holder.mCourseName.setText(start + "~" + end);
            holder.mTeacherName.setText(sb.toString());
            holder.mPeopleNum.setText("剩余" + some.getLast_number() + "名额");
            holder.mOrderPeopleNum.setVisibility(View.GONE);
            holder.mTime.setVisibility(View.GONE);
            holder.mRight.setVisibility(View.GONE);
            holder.mSelect.setVisibility(View.VISIBLE);
            holder.mTvSelect.setVisibility(View.VISIBLE);
            holder.mSelect.setSelected(some.isSelect() ? true : false);
            holder.mSelect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < mDatas.size(); i++) {
                        SomeCourseBean data = (SomeCourseBean) mDatas.get(i);
                        if (holder.getAdapterPosition() == i){
                            if (some.isSelect()){
                                holder.mSelect.setSelected(false);
                                data.setSelect(false);
                            }else{
                                holder.mSelect.setSelected(true);
                                data.setSelect(true);
                                PostResult postResult = new PostResult();
                                postResult.setTag("selectCourse");
                                postResult.setResult(data);
                                EventBus.getDefault().post(postResult);
                            }
                        }else{
                            data.setSelect(false);
                        }
                    }
                    notifyDataSetChanged();
                }
            });
        }else if (baseCourseBean.getBeanType() == 0){//店铺详情查看所有课程课表
            final CourseListData listData = (CourseListData) baseCourseBean;
            String start = DateUtils.timesTwo1(String.valueOf(listData.getStart_time()));
            String end = DateUtils.timesTwo1(String.valueOf(listData.getEnd_time()));
            holder.mTeacherName.setText(listData.getTutorName());
            holder.mPeopleNum.setText(String.valueOf(listData.getNumber()));
            Glide.with(mContext)
                    .load(listData.getImage())
                    .placeholder(R.drawable.img_course)
                    .error(R.drawable.img_course)
                    .into(holder.mCourseImage);
            holder.mPeopleNum.setText("剩余" + listData.getResidueNumber() + "名额");
            holder.mCourseName.setText(listData.getLessonName());
            holder.mTime.setText(start + "~" + end);
            holder.mTime.setVisibility(View.VISIBLE);
            holder.mOrderPeopleNum.setVisibility(View.VISIBLE);
            holder.mRight.setVisibility(View.VISIBLE);
            holder.mTvSelect.setVisibility(View.GONE);
            holder.mSelect.setVisibility(View.GONE);
        }
        holder.mCl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = holder.getAdapterPosition();
                BaseCourseBean courseBean = mDatas.get(index);
                Bundle bundle = new Bundle();
                bundle.putString("tag","");
                if (courseBean.getBeanType() == 1){ //查看某节课的课表
                    SomeCourseBean some = (SomeCourseBean) courseBean;
                    bundle.putString("lessonId",String.valueOf(some.getLesson_id()));
                } else {
                    CourseListData listData = (CourseListData) courseBean;
                    bundle.putString("lessonId",String.valueOf(listData.getCourse_id()));
                }
                EventBus.getDefault().post(new PostResult("2CourseDetail"));
            }
        });
    }*/

    /*@Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }*/

    /*public void setDatas(List<BaseCourseBean> datas) {
        this.mDatas = datas;
        notifyDataSetChanged();
    }*/

    /*class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mCourseImage;
        private ImageView mSelect;
        private ImageView mRight;
        private TextView mCourseName;
        private TextView mTeacherName;
        private TextView mTime;
        private TextView mPeopleNum;
        private TextView mOrderPeopleNum;
        private TextView mTvSelect;
        private ConstraintLayout mCl;

        public ViewHolder(View itemView) {
            super(itemView);
            mCourseImage = itemView.findViewById(R.id.iv_course_image);
            mSelect = itemView.findViewById(R.id.iv_select);
            mRight = itemView.findViewById(R.id.iv_right);
            mCourseName = itemView.findViewById(R.id.tv_course_name);
            mTeacherName = itemView.findViewById(R.id.tv_teacher_name);
            mTime = itemView.findViewById(R.id.tv_time);
            mPeopleNum = itemView.findViewById(R.id.tv_people_num);
            mOrderPeopleNum = itemView.findViewById(R.id.tv_order_people_num);
            mTvSelect = itemView.findViewById(R.id.tv_select);
            mCl = itemView.findViewById(R.id.cl);
        }
    }*/
}
