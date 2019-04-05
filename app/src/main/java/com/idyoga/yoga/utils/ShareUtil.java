package com.idyoga.yoga.utils;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.idyoga.yoga.R;
import com.idyoga.yoga.comm.Constants;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import vip.devkit.library.Logcat;
import vip.devkit.library.RegUtil;
import vip.devkit.library.StringUtil;

/**
 * 文 件 名: ShareUtil
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/4/25
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class ShareUtil {
    private IWXAPI api;

    public static Builder with(Context context) {
        return new Builder(context);
    }

    private ShareUtil(Builder builder) {
        this.mBitmap = builder.mBitmap;
        this.mContext = builder.context;
        this.shareTitle = builder.shareTitle;
        this.shareDescription = builder.shareDescription;
        this.shareImgUrl = builder.shareImgUrl;
        this.shareUrl = builder.shareUrl;
        this.mContext = builder.context;
        this.mBitmap= builder.mBitmap;
        if (null!=builder.mBitmap){
            this.mBitmap= BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.app_logo);
        }
    }

    Context mContext;

    String shareTitle;
    String shareDescription="";
    String shareUrl;
    String shareImgUrl;
    Bitmap mBitmap ;


    public void share() {
        if (StringUtil.isEmpty(shareTitle)) {
            Logcat.e("分享标题为空，请填写");
            return;
        }
//        if (StringUtil.isEmpty(shareDescription)) {
//            Logcat.e("分享简介为空，请填写");
//            return;
//        }
        if (StringUtil.isEmpty(shareUrl)) {
            Logcat.e("分享网页地址为空，请填写");
            return;
        }
//        if (StringUtil.isEmpty(shareImgUrl) && null != mBitmap) {
//            Logcat.e("分享缩略图地址为空，请填写");
//            return;
//        }
        ShareDialog();
    }
    Dialog dialog;
    private void ShareDialog() {
        dialog = new Dialog(mContext, R.style.quick_option_dialog);
        dialog.setContentView(R.layout.dialog_share);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().setGravity(Gravity.BOTTOM | Gravity.CENTER);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setWindowAnimations((R.style.popWindow_anim_style));
        dialog.show();
        LinearLayout shareToWx = (LinearLayout) dialog.findViewById(R.id.iv_wx);
        LinearLayout shareToWx2 = (LinearLayout) dialog.findViewById(R.id.iv_wx2);
        LinearLayout shareToQQ = (LinearLayout) dialog.findViewById(R.id.iv_qq1);
        LinearLayout shareToQzone = (LinearLayout) dialog.findViewById(R.id.iv_qq2);
        TextView tv_cance_share = (TextView) dialog.findViewById(R.id.tv_cance_share);
        shareToWx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!StringUtil.isEmpty(shareImgUrl)&& RegUtil.checkURL(shareImgUrl)) {
                    getShareBitmap("WxA", shareImgUrl);
                } else if (null != mBitmap) {
                    shareToWx("WxA", bitmap2Bytes(mBitmap, 32));
                } else {
                    Bitmap thumb = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.app_logo);
                    shareToWx("WxA", bitmap2Bytes(thumb, 32));
                }
            }
        });
        shareToWx2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!StringUtil.isEmpty(shareImgUrl)&& RegUtil.checkURL(shareImgUrl)) {
                    getShareBitmap("WxB", shareImgUrl);
                } else if (null != mBitmap) {
                    shareToWx("WxB", bitmap2Bytes(mBitmap, 32));
                } else {
                    Bitmap thumb = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.app_logo);
                    shareToWx("WxB", bitmap2Bytes(thumb, 32));
                }
            }
        });
        shareToQQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareToQQ();
            }
        });
        shareToQzone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareToQzone();

            }
        });
        tv_cance_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }


    private void shareToWx(String type, byte[] bytes) {
        api = WXAPIFactory.createWXAPI(mContext, Constants.WX_APP_ID, false);
        Logcat.e("分享至微信：" + type + "/" + api.getWXAppSupportAPI());
        WXWebpageObject webPage = new WXWebpageObject();
        webPage.webpageUrl = shareUrl;
        WXMediaMessage msg = new WXMediaMessage(webPage);
        msg.title = shareTitle;
        msg.description = shareDescription;
        Bitmap thumb = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.app_logo);
        if (bytes != null) {
            msg.thumbData = bytes;
        } else if (null == bytes) {
            msg.setThumbImage(BitmapUtil.Bytes2Bimap(bitmap2Bytes(mBitmap, 32)));
        } else if (null == mBitmap) {
            msg.thumbData = bitmap2Bytes(thumb, 32);
        }
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        //WXSceneTimeline 微信朋友圈   WXSceneSession 微信聊天界面/给朋友  WXSceneFavorite 微信收藏
        if (type.equals("WxA")) {
            req.scene = req.WXSceneSession;
        } else if (type.equals("WxB")) {
            req.scene = req.WXSceneTimeline;
        }
        api.sendReq(req);
        if (dialog != null) {
            dialog.dismiss();
        }
        Logcat.e(" 1 分享至微信：" + type + "/" + api.getWXAppSupportAPI());
    }

    private void shareToQQ() {

    }

    private void shareToQzone() {

    }


    public void getShareBitmap(final String type, final String url) {
        Glide.with(mContext).load(url).asBitmap().error(R.mipmap.app_logo).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                Logcat.e("bitmap2Bytes ：" + BitmapUtil.getBitmapSize(resource));
                shareToWx(type, bitmap2Bytes(resource, 32));
            }
        });
    }

    /**
     * Bitmap转换成byte[]并且进行压缩,压缩到不大于maxKb
     *
     * @param bitmap
     * @param maxKb
     * @return
     */
    public static byte[] bitmap2Bytes(Bitmap bitmap, int maxKb) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
        int options = 100;
        while (output.toByteArray().length > maxKb && options != 10) {
            output.reset(); //清空output
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, output);//这里压缩options%，把压缩后的数据存放到output中
            options -= 10;
        }
        return output.toByteArray();
    }

    /**
     * 调用系统分享
     *
     * @param context
     * @param description
     * @param paths
     */
    public static void shareImages(Context context, String description, ArrayList<Uri> paths) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        ArrayList<Uri> imageUris = new ArrayList<>();
        if (paths != null && paths.size() > 0) {
            for (int i = 0; i < paths.size(); i++) {
                imageUris.add(paths.get(i));
            }
        }
        shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
        shareIntent.setType("image/*");
        shareIntent.putExtra("Kdescription", description);
        // 3 Finals 2016-11-2 指定选择微信。
        ComponentName mComponentName = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");
        shareIntent.setComponent(mComponentName);
        context.startActivity(Intent.createChooser(shareIntent, "分享图片"));
    }


    /**
     * 分享Builder
     */
    public static class Builder {
        private String shareTitle;
        private String shareDescription;
        private String shareUrl;
        private String shareImgUrl;
        private Bitmap mBitmap;
        private Context context;

        public Builder(Context mContext) {
            this.context = mContext;
        }

        public Builder setShareTitle(String shareTitle) {
            this.shareTitle = shareTitle;
            return this;
        }

        public Builder setShareDescription(String shareDescription) {
            this.shareDescription = shareDescription;
            return this;
        }

        public Builder setShareUrl(String shareUrl) {
            this.shareUrl = shareUrl;
            return this;
        }

        public Builder setShareImgUrl(String shareImgUrl) {
            this.shareImgUrl = shareImgUrl;
            return this;
        }

        public Builder setBitmap(Bitmap bitmap) {
            mBitmap = bitmap;
            return this;
        }

        public ShareUtil build() {
            return new ShareUtil(this);
        }
    }
}