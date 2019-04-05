package com.idyoga.yoga.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.shop.ShopDetailActivity;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.model.LessonListBean;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * @author weilgu
 * @time 2019/3/13 11:00
 * @des ${TODO}
 */

public class ShopInCourseAdapter extends RecyclerView.Adapter<ShopInCourseAdapter.ViewHolder> {

    private Context mContext;
    private final LayoutInflater mFrom;
    private List<LessonListBean> mLessonList;

    public ShopInCourseAdapter(Context context) {
        mContext = context;
        mFrom = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mFrom.inflate(R.layout.item_in_shop_course_list,parent,false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        LessonListBean listBean = mLessonList.get(position);
        String image = listBean.getImage();
        String name = listBean.getName();
        int isCourse = listBean.getIsCourse();
        int appointmentNum = listBean.getAppointmentNum();
        holder.tv_course_name.setText(name != null ? name : "");
        holder.tv_order_num.setText("已约次数: " + appointmentNum);
        holder.tv_course_tag1.setText(isCourse == 1 ? "已排课程" : "时间自选");
        Glide.with(mContext)
                .load(image)
                .placeholder(R.drawable.img_course)
                .error(R.drawable.img_course)
                .into(holder.iv_shop_image);
        if (position == mLessonList.size() -1){
            holder.view_bottom.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mLessonList != null ? mLessonList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_shop_image;
        private TextView tv_course_name;
        private TextView tv_course_tag1;
        private TextView tv_course_tag2;
        private ImageView iv_right_btn;
        private TextView tv_order_num;
        private RelativeLayout rl_root_course;
        private View view_bottom;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_shop_image = itemView.findViewById(R.id.iv_shop_image);
            tv_course_name = itemView.findViewById(R.id.tv_course_name);
            tv_course_tag1 = itemView.findViewById(R.id.tv_course_tag1);
            tv_course_tag2 = itemView.findViewById(R.id.tv_course_tag2);
            iv_right_btn = itemView.findViewById(R.id.iv_right_btn);
            tv_order_num = itemView.findViewById(R.id.tv_order_num);
            view_bottom = itemView.findViewById(R.id.view_bottom);
            rl_root_course = itemView.findViewById(R.id.rl_root_course);
            rl_root_course.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = getAdapterPosition();
                    LessonListBean lessonListBean = mLessonList.get(index);
                    int id = lessonListBean.getId();
                    Bundle bundle = new Bundle();
                    bundle.putString("lessonId",String.valueOf(id));
                    EventBus.getDefault().post(new PostResult("startAppointmentIntroductionActivity",bundle));
                    //ShopDetailActivity activity = (ShopDetailActivity) mContext;

                }
            });
        }
    }

    public void setData(List<LessonListBean> lessonList){
        mLessonList = lessonList;
        notifyDataSetChanged();
    }
}
