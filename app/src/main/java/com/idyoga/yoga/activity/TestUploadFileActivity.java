package com.idyoga.yoga.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.user.MineMakeInfoActivity;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.comm.AppConfig;
import com.idyoga.yoga.common.adapter.CommonAdapter;
import com.idyoga.yoga.common.adapter.ViewHolder;
import com.idyoga.yoga.common.http.type2.OkHttpClientManager;
import com.idyoga.yoga.common.http.type2.OkHttpResponseHandler;
import com.idyoga.yoga.utils.GlideImgManager;
import com.idyoga.yoga.utils.ToastUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import vip.devkit.library.Logcat;
import vip.devkit.library.RelyUtil.IOUtil;
import vip.devkit.view.common.ImgPicker.ImagePicker;
import vip.devkit.view.common.ImgPicker.bean.ImageItem;
import vip.devkit.view.common.ImgPicker.ui.ImageGridActivity;

/**
 * 文 件 名: TestUploadFileActivity
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/12
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class TestUploadFileActivity extends BaseActivity {
    /*@BindView(R.id.btn_photo)
    Button mBtnPhoto;
    @BindView(R.id.btn_cream)
    Button mBtnCream;
    @BindView(R.id.btn_upload)
    Button mBtnUpload;
    @BindView(R.id.gv_list)
    GridView mGridView;*/

    int chooiceIndex = -1;
    List<ImageItem> mItemList = new ArrayList<>();
    CommonAdapter mAdapter;

    @Override
    protected void initData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.a_test;
    }


    @Override
    protected void initView() {
        AppConfig.getInstance().initImgPicker(5);
        ImageItem imageItem = new ImageItem();
        imageItem.name = "54007";
        mItemList.add(imageItem);//默认的
        /*mGridView.setNumColumns(5);
        mGridView.setAdapter(mAdapter = new CommonAdapter<ImageItem>(this, mItemList, R.layout.item_add_img) {
            @Override
            public void convert(ViewHolder holder, ImageItem imageItem, int position) {
                if (imageItem.name.equals("54007")) {
                    Glide.with(mContext).load(R.drawable.icon_add).into((ImageView) holder.getView(R.id.iv_img));
                } else {
                    Glide.with(mContext).load(new File(imageItem.path)).into((ImageView) holder.getView(R.id.iv_img));
                }
            }
        });*/
    }

    int selectIndex=-1;
    @Override
    protected void setListener() {
        /*mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AppConfig.getInstance().initImgPicker(6 - mItemList.size());
                Intent intent = new Intent(TestUploadFileActivity.this, ImageGridActivity.class);
                startActivityForResult(intent, PHOTO_PICKER);
            }
        });*/
    }

    /*@OnClick({R.id.btn_photo, R.id.btn_cream, R.id.btn_upload})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_photo:
                AppConfig.getInstance().initImgPicker(6 - mItemList.size());
                intent = new Intent(TestUploadFileActivity.this, ImageGridActivity.class);
                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                startActivityForResult(intent, CAMERA_PICKER);
                break;
            case R.id.btn_cream:
                AppConfig.getInstance().initImgPicker(6 - mItemList.size());
                intent = new Intent(TestUploadFileActivity.this, ImageGridActivity.class);
                startActivityForResult(intent, PHOTO_PICKER);
                break;
            case R.id.btn_upload:
                chooiceIndex = 2;
                break;
        }
    }*/


    private void uploadFile() {
        Map map = new HashMap();
        OkHttpClientManager
                .postAsyn("http://testyogabook.hq-xl.com/mall/Shop/appSaveShopInfo", map, new OkHttpResponseHandler<String>(this));

        ImageItem imageItem = new ImageItem();

        mItemList.add(new ImageItem());


        Map<String, File> f = new HashMap();
        for (int i = 0; i < mItemList.size(); i++) {
            Logcat.e("name:" + mItemList.get(i).name + "/" + mItemList.get(i).path);
            f.put(mItemList.get(i).name, new File(mItemList.get(i).path));
        }
        OkHttpUtils.post()
                .files("image[]", f)
                .url("http://testyogabook.hq-xl.com/mall/Shop/appSaveShopInfo")
                .addParams("type", "0")
                .addParams("imageId", "13")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Logcat.e("返回的数据：" + response);
                    }
                });
    }

    private static final int PHOTO_PICKER = 0x000101;
    private static final int CAMERA_PICKER = PHOTO_PICKER + 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            switch (requestCode) {
                case PHOTO_PICKER:
                    if (data != null) {
                        ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                        mItemList.addAll(images);
                        if (mItemList.size()<5){
                            ImageItem imageItem = new ImageItem();
                            imageItem.name = "54007";
                            mItemList.add(imageItem);//默认的
                        }
                        mAdapter.notifyDataSetChanged();
//                        initData(images);
                        Logcat.i("选择的图片数据：PHOTO_PICKER" + Arrays.toString(images.toArray()));
                    } else {
                        ToastUtil.showToast("请重新选择");
                    }
                    break;
                case CAMERA_PICKER:
                    if (data != null) {
                        ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                        if (chooiceIndex != -1) {
                        } else {
                            mItemList.addAll(images);
                        }
                        Logcat.i("选择的图片数据：CAMERA_PICKER" + Arrays.toString(images.toArray()));
                    } else {
                        ToastUtil.showToast("请重新选择");
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void initData(List<ImageItem> itemList) {
        if (chooiceIndex != -1 && mItemList.size() < 5) {
            if (mItemList.size() < 5) {
                mItemList.remove(mItemList.size() - 1);
                mItemList.addAll(itemList);
                mItemList.add(new ImageItem());
            } else {
                mItemList.addAll(itemList);
            }
        } else if (chooiceIndex != -1 && mItemList.size() == 5) {
            mItemList.set(chooiceIndex, new ImageItem());
        } else {
            if (mItemList.size() < 1 && mItemList.size() < 5) {
                mItemList.remove(mItemList.size() - 1);
                mItemList.addAll(itemList);
                mItemList.add(new ImageItem());
            } else {
                mItemList.addAll(itemList);
            }
        }

        for (int i = 0; i < itemList.size(); i++) {
            Logcat.i("itemList:" + itemList.get(i).name);
        }
        Logcat.i("itemList:" + itemList.size());
    }


}
