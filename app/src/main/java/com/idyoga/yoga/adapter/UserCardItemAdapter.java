package com.idyoga.yoga.adapter;

import android.content.Context;
import android.view.View;

import com.idyoga.yoga.R;
import com.idyoga.yoga.common.adapter.CommonAdapter;
import com.idyoga.yoga.common.adapter.ViewHolder;
import com.idyoga.yoga.model.UserCardDetailPermission;

import java.util.List;

import vip.devkit.library.Logcat;

/**
 * 文 件 名: UserCardItemAdapter
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/5/17
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class UserCardItemAdapter extends CommonAdapter<UserCardDetailPermission.ShopLessonListBean.LessonBean> {

   protected List<UserCardDetailPermission.ShopLessonListBean.LessonBean> mLessonBeanList;
   protected int count;
   private  int itemPosition;

    public UserCardItemAdapter(Context context, List<UserCardDetailPermission.ShopLessonListBean.LessonBean> mBeanList, int layoutId, int count,int position) {
        super(context, mBeanList, layoutId);
        this.mLessonBeanList = mBeanList;
        this.count = count;
        this.itemPosition = position;
    }

    public void setBeanList(List<UserCardDetailPermission.ShopLessonListBean.LessonBean> lessonBeanList) {
        Logcat.i("lessonBeanList 2:"+mLessonBeanList.size()+"/"+lessonBeanList.size());
        this.mLessonBeanList=lessonBeanList;
        this.notifyDataSetChanged();
        Logcat.i("lessonBeanList 2:"+mLessonBeanList.size()+"/"+lessonBeanList.size());
    }

    @Override
    public void convert(ViewHolder holder, UserCardDetailPermission.ShopLessonListBean.LessonBean membershipLessonBean, final int position) {
        holder.setText(R.id.tv_lesson_name, membershipLessonBean.getLessonName());
    }

    public interface onLoadClickListener {
        void onLoad(int position);
    }

    onLoadClickListener mLoadClickListener;
    public void setLoadClickListener(onLoadClickListener loadClickListener) {
        mLoadClickListener = loadClickListener;
    }
}
