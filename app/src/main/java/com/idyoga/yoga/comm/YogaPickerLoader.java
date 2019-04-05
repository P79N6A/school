package com.idyoga.yoga.comm;


import android.app.Activity;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.idyoga.yoga.R;

import java.io.File;

import vip.devkit.library.StringUtil;
import vip.devkit.view.common.ImgPicker.loader.ImageLoader;

/**
 * 作者 by K
 * 时间：on 2018/7/28 16:22
 * 邮箱 by  vip@devkit.vip
 * <p/>
 * 类用途：
 * 最后修改：
 */
public class YogaPickerLoader implements ImageLoader {
    private static YogaPickerLoader mInstance;
    private int level=0;
    public YogaPickerLoader() {
    }
    public static YogaPickerLoader getInstance() {
        if (mInstance == null) {
            synchronized (YogaPickerLoader.class) {
                if (mInstance == null) {
                    mInstance = new YogaPickerLoader();
                }
            }
        }
        return mInstance;
    }


    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        Glide.with(activity)
                .load((path.startsWith("http://")||path.startsWith("https://"))?path: Uri.fromFile(new File(path)))
                .error(R.drawable.img_dj)
                    .priority(level == 0 ? Priority.NORMAL: Priority.IMMEDIATE)
                .placeholder(R.drawable.img_dj)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    @Override
    public void displayImagePreview(Activity activity, String path, ImageView imageView, int width, int height) {
        if (StringUtil.isEmpty(path)){
            Glide.with(activity)                             //配置上下文
                    .load((""))//设置图片路径
                    .error(R.drawable.img_dj)           //设置错误图片
                    .priority(level == 0 ? Priority.NORMAL: Priority.IMMEDIATE)
                    .placeholder(R.drawable.img_dj)     //设置占位图片
                    .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                    .into(imageView);
            return;
        }
        Glide.with(activity)                             //配置上下文
                .load((path.startsWith("http://")||path.startsWith("https://"))?path: Uri.fromFile(new File(path)))//设置图片路径
                .error(R.drawable.img_dj)           //设置错误图片
                .priority(level == 0 ? Priority.NORMAL: Priority.IMMEDIATE)
                .placeholder(R.drawable.img_dj)     //设置占位图片
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                .into(imageView);
    }

    @Override
    public void clearMemoryCache() {

    }
}
