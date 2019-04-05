package com.idyoga.yoga.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.idyoga.yoga.R;
import com.idyoga.yoga.common.modle.CircleTransform;
import com.idyoga.yoga.common.modle.GlideRoundTransform;
import com.idyoga.yoga.utils.glide.BlurTransformation;

import java.io.ByteArrayOutputStream;

import vip.devkit.library.bitmap.BitmapUtil;

/**
 * 作者 by K
 * 时间：on 2017/9/20 11:49
 * 邮箱 by  vip@devkit.vip
 * <p/>
 * 类用途：
 * 最后修改：
 */
public class GlideImgManager {
    /**
     * @param url
     * @param erroImg
     * @param iv
     */
    public static void glideLoader(Context context, String url, int erroImg, ImageView iv) {
        //原生 API
//        Glide.with(context).load(url).placeholder(erroImg).error(erroImg).into(iv);
        Glide.with(context).load(url).error(erroImg).into(iv);

    }
    /**
     * @param url
     * @param iv
     */
    public static void glideLoaders(Context context, String url, ImageView iv) {
        Glide.with(context).load(url).placeholder(R.drawable.img_course).error(R.drawable.img_course).into(iv);
    }

    /**
     * 圆型
     *
     * @param url
     * @param erroImg
     * @param emptyImg
     * @param iv
     */
    public static void glideLoader(Context context, String url, int erroImg, int emptyImg, ImageView iv) {
        //原生 API
        Glide.with(context).load(url).placeholder(emptyImg).error(erroImg)
                .transform(new CircleTransform(context)).into(iv);
    }

    /**
     * 圆型
     *
     * @param iv
     */
    public static void glideLoader(Context context, int img, ImageView iv) {
        //原生 API
        Glide.with(context).load(img).placeholder(R.drawable.ic_user_hp).error(R.drawable.ic_user_hp)
                .transform(new CircleTransform(context)).into(iv);
    }

    /**
     * 圆型
     *
     * @param iv
     */
    public static void glideLoader(Context context, String url, ImageView iv) {
        //原生 API
        Glide.with(context).load(url).placeholder(R.drawable.img_02)
                .error(R.drawable.img_02).transform(new CircleTransform(context)).into(iv);
    }

    /**
     * 圆型
     *
     * @param iv
     */
    public static void glideLoader(Context context, Uri uri, ImageView iv) {
        Glide.with(context)
                .load(uri)
                .placeholder(R.drawable.ic_user_hp)
                .error(R.drawable.ic_user_hp).
                transform(new CircleTransform(context))
                .into(iv);
    }

    /**
     * lo圆角
     *
     * @param context
     * @param res
     * @param erroImg
     * @param emptyImg
     * @param iv
     */
    public static void glideLoader(Context context, int res, int erroImg, int emptyImg, ImageView iv) {
        //原生 API
        Glide.with(context).load(res).placeholder(emptyImg).error(erroImg)
                .transform(new CircleTransform(context)).into(iv);
    }

    /**
     * lo圆角
     *
     * @param url
     * @param erroImg
     * @param emptyImg
     * @param iv
     * @param tag
     */
    public static void glideLoader(Context context, int url, int erroImg, int emptyImg, ImageView iv, int tag) {

        //原生 API
        Glide.with(context).load(url).placeholder(emptyImg).error(erroImg).transform(new GlideRoundTransform(context, tag)).into(iv);

    }


    /**
     * lo圆角
     *
     * @param iv
     * @param tag
     */
    public static void glideLoader(Context context, Bitmap bitmap, ImageView iv, int tag) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] bytes = baos.toByteArray();
        Glide.with(context).load(bytes).asBitmap().placeholder(R.mipmap.app_logo).error(R.mipmap.app_logo)
                .transform(new GlideRoundTransform(context, tag)).into(iv);
    }

    /**
     * lo圆角and Blur
     *
     * @param iv
     * @param tag
     */
    public static void glideLoaderBlur(Context context, String url, final ImageView iv, int tag, int radius) {
        Glide.with(context)
                .load(url)
                .asBitmap()
                .dontAnimate()
                .placeholder(R.mipmap.app_logo)
                .error(R.mipmap.app_logo)
                .transform(new GlideRoundTransform(context, radius))
                .transform(new BlurTransformation(context, tag))
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        Bitmap bitmap = BitmapUtil.getRoundedCornerBitmap(resource, 20);
                        iv.setImageBitmap(bitmap);
                    }
                });
    }

}