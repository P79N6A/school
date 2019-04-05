package com.idyoga.yoga.model;

import android.content.Context;
import android.content.Intent;
import android.graphics.Outline;
import android.graphics.drawable.ShapeDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.course.AppointmentCourseDetailActivity;
import com.idyoga.yoga.utils.DateUtils;
import com.idyoga.yoga.utils.ViewUtil;
import com.idyoga.yoga.view.RoundDrawable;
import com.idyoga.yoga.view.ShadowLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vip.devkit.library.SharedPreferencesUtils;

/**
 * @author weilgu
 * @time 2019/3/8 11:30
 * @des  我的预约 列表 adapter
 */

public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.ViewHolder> implements View.OnClickListener{

    private Map<String, List<UserCourseBean>> mListMap;
    private Context mContext;
    private final LayoutInflater mFrom;
    private List<Object> mDatas;
    private String mType;
    //显示时间
    private static final int SHOW_START_TIME = 0;
    //显示课程信息
    private static final int SHOW_COURSE_INFO = 1;

    public CourseListAdapter(Context context){
        this.mContext = context;
        mFrom = LayoutInflater.from(mContext);
        mDatas = new ArrayList<>();
    }

    @Override
    public CourseListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == SHOW_START_TIME){
            return new ViewHolder(mFrom.inflate(R.layout.item_follow_shop_time_layout, parent, false),SHOW_START_TIME);
        }else if (viewType == SHOW_COURSE_INFO){
            return new ViewHolder(mFrom.inflate(R.layout.item_user_course_item,parent,false),SHOW_COURSE_INFO);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final CourseListAdapter.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == SHOW_START_TIME){
            holder.showTime((String)mDatas.get(position));
        }else if(itemViewType == SHOW_COURSE_INFO){
            holder.showInfo((UserCourseBean)mDatas.get(position));
            holder.mCvRoot.setBackground(new RoundDrawable(ViewUtil.dp2px(mContext,5),0.2f));
            holder.mRlv.setTag(position);
            holder.mRlv.setOnClickListener(this);
        }
    }

    @Override
    public int getItemViewType(int position) {
        Object obj = mDatas.get(position);
        if (obj instanceof String){
            return SHOW_START_TIME;
        }else if (obj instanceof UserCourseBean){
            return SHOW_COURSE_INFO;
        }
        return -1;
    }

    public void setDatas(List<String> stringList,HashMap<String, List<UserCourseBean>> hashMap){
        mDatas.clear();
        for (int i = 0; i < stringList.size(); i++) {
            String time = stringList.get(i);
            mDatas.add(time);
            mDatas.addAll(hashMap.get(time));
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void setType(String type) {
        this.mType = type;
    }

    @Override
    public void onClick(View v) {
        RelativeLayout root = (RelativeLayout) v;
        int position = (int) root.getTag();
        Bundle mBundle = new Bundle();
        UserCourseBean userCourseBean = (UserCourseBean) mDatas.get(position);
        int userId = (int) SharedPreferencesUtils.getSP(mContext, "UserId", 0);
        mBundle.putString("userId", userId + "");
        mBundle.putString("lessonId", userCourseBean.getId() + "");
        mBundle.putString("lessonType", userCourseBean.getLessonType() + "");
        Intent intent = new Intent(mContext, AppointmentCourseDetailActivity.class);
        intent.putExtras(mBundle);
        mContext.startActivity(intent);
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mTime;
        private ImageView mCourseIcon;
        private TextView mCourseName;
        private TextView mShopName;
        private TextView mAddress;
        private RelativeLayout mRlv;
        private ShadowLayout mCvRoot;

        public ViewHolder(View itemView) {
            this(itemView,-1);
        }

        public ViewHolder(View itemView,int viewType){
            super(itemView);
            if (viewType == SHOW_START_TIME){
                mTime = itemView.findViewById(R.id.tv_follow_time);
            }else if(viewType == SHOW_COURSE_INFO){
                mCourseIcon = itemView.findViewById(R.id.iv_course_item_img);
                mCourseName = itemView.findViewById(R.id.tv_course_item_name);
                mShopName = itemView.findViewById(R.id.tv_course_item_teacher);
                mAddress = itemView.findViewById(R.id.tv_course_item_address);
                mRlv = itemView.findViewById(R.id.rlv_root);
                mCvRoot = itemView.findViewById(R.id.cv_root);
            }
        }

        public void showTime(String followShopTime) {
            mTime.setText(followShopTime + "");
        }

        public void showInfo(UserCourseBean userCourseBean) {
            mCourseName.setText(userCourseBean.getLessonName() + "");
            mShopName.setText(userCourseBean.getShopName() + "");
            mAddress.setText("开课时间: " + DateUtils.timet(String.valueOf(userCourseBean.getTime())));
            Glide.with(mContext)
                    .load(userCourseBean.getLessonImg())
                    .placeholder(R.drawable.img_course)
                    .error(R.drawable.img_course)
                    .into((ImageView) mCourseIcon);
        }
    }

}
