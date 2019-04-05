package com.idyoga.yoga.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.idyoga.yoga.R;
import com.idyoga.yoga.common.adapter.CommonAdapter;
import com.idyoga.yoga.common.adapter.ViewHolder;
import com.idyoga.yoga.model.HomeSubjectBean;
import com.idyoga.yoga.model.HomeSubjectItemBean;
import com.idyoga.yoga.utils.DateUtils;
import com.idyoga.yoga.utils.GlideImgManager;
import com.idyoga.yoga.utils.glide.BlurTransformation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import vip.devkit.library.RelyUtil.IOUtil;
import vip.devkit.library.bitmap.BitmapUtil;
import vip.devkit.library.bitmap.ImageUtils;

public class HomeSubjectListItemAdapter extends CommonAdapter<HomeSubjectItemBean.VideoGroupBean.VideoGroupActionBean> {

    Context mContext;

    public HomeSubjectListItemAdapter(Context context, List<HomeSubjectItemBean.VideoGroupBean.VideoGroupActionBean> mStringList, int layoutId) {
        super(context, mStringList, layoutId);
        this.mContext = context;
    }
    @Override
    public void convert(final ViewHolder holder, HomeSubjectItemBean.VideoGroupBean.VideoGroupActionBean bean, int i) {
        Glide.with(mContext)
                .load(bean.getImage_url())
                .asBitmap()
                .dontAnimate()
                .placeholder(R.drawable.img_course)
                .error(R.drawable.img_course)
                .transform(new BlurTransformation(mContext, 30))
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        Bitmap bitmap = BitmapUtil.getRoundedCornerBitmap(resource, 20);
                        holder.setImageBitmap(R.id.iv_subject_img,bitmap) ;
                    }
                });
        holder.setText(R.id.tv_subject_title, bean.getTitle())
                .setText(R.id.tv_video_time, DateUtils.secToTime(bean.getRest_time())+"")
                .setText(R.id.tv_teacher_name,bean.getTutor_name()+"");

    }
}
