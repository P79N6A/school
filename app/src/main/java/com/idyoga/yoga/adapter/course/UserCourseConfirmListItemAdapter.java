package com.idyoga.yoga.adapter.course;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.course.CourseCommentActivity;
import com.idyoga.yoga.adapter.VipCardAdapter;
import com.idyoga.yoga.common.adapter.CommonAdapter;
import com.idyoga.yoga.common.adapter.ViewHolder;
import com.idyoga.yoga.model.UserCourseBean;

import java.util.List;

/**
 * 文 件 名: UserCourseAdapter
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/3/23
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class UserCourseConfirmListItemAdapter extends CommonAdapter<UserCourseBean> {
    List<UserCourseBean> mBeanList;

    public UserCourseConfirmListItemAdapter(Context context, List<UserCourseBean> mBeanList, int layoutId) {
        super(context, mBeanList, layoutId);
        this.mBeanList = mBeanList;
    }

    public List<UserCourseBean> getBeanList() {
        return mBeanList;
    }

    public void setBeanList(List<UserCourseBean> beanList) {
        mBeanList = beanList;
    }

    @Override
    public void convert(ViewHolder holder, final UserCourseBean userCourseBean, int i) {
        String start = VipCardAdapter.getDateStringByTimeSTamp(userCourseBean.getStart(), "HH:mm");
        String end = VipCardAdapter.getDateStringByTimeSTamp(userCourseBean.getEnd(), "HH:mm");
        ImageView imageView = holder.getView(R.id.iv_course_item_img);
        Glide.with(mContext)
                .load(mBeanList.get(i).getLessonImg())
                .placeholder(R.drawable.img_course)
                .error(R.drawable.img_course)
                .into(imageView);
        holder.setText(R.id.tv_course_item_name, userCourseBean.getLessonName())
                .setText(R.id.tv_course_item_teacher, userCourseBean.getTutorName())
                .setText(R.id.tv_course_item_address, start+"-"+end+" | " + userCourseBean.getShopAddress());
        holder.getView(R.id.tv_comment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("mUserCourseBean",userCourseBean);
                bundle.putString("CourseId",userCourseBean.getCourseId()+"");
                bundle.putString("ShopId",userCourseBean.getShop_id()+"");
                Intent intent = new Intent(mContext, CourseCommentActivity.class);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });

    }
}
