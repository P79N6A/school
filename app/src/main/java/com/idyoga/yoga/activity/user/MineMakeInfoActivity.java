/**
 * ****************************** Copyright (c)*********************************\
 * *
 * *                 (c) Copyright 2017, DevKit.vip, china, qd. sd
 * *                          All Rights Reserved
 * *
 * *                           By(K)
 * *
 * *-----------------------------------版本信息------------------------------------
 * * 版    本: V0.1
 * *
 * *------------------------------------------------------------------------------
 * *******************************End of Head************************************\
 */
package com.idyoga.yoga.activity.user;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.OptionsPickerView;
import com.idyoga.yoga.R;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.comm.AppConfig;
import com.idyoga.yoga.comm.NetWorkConstant;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.common.http.type1.HttpClient;
import com.idyoga.yoga.common.http.type1.HttpResponseHandler;
import com.idyoga.yoga.common.view.CustomPopupWindow;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.UserInfoBean;
import com.idyoga.yoga.model.address.AddressBean;
import com.idyoga.yoga.utils.CheckBoxUtil;
import com.idyoga.yoga.utils.GlideImgManager;
import com.idyoga.yoga.utils.StringUtils;
import com.idyoga.yoga.utils.ToastUtil;
import com.idyoga.yoga.utils.UserUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Request;
import vip.devkit.library.ListUtil;
import vip.devkit.library.Logcat;
import vip.devkit.library.RelyUtil.IOUtil;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.library.StringUtil;
import vip.devkit.view.common.ImgPicker.ImagePicker;
import vip.devkit.view.common.ImgPicker.bean.ImageItem;
import vip.devkit.view.common.ImgPicker.ui.ImageGridActivity;

/**
 * 文 件 名: MineMakeInfoActivity
 * 创 建 人: By k
 * 创建日期: 2018/3/20 17:33
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注：
 */
public class MineMakeInfoActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.iv_user_hp)
    ImageView mIvUserHp;
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.cb_sex_box)
    CheckBox mCbSexBox;
    @BindView(R.id.cb_sex_girl)
    CheckBox mCbSexGirl;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.btn_submit)
    Button mBtnSubmit;
    CheckBoxUtil mCheckBoxUtil;
    Bundle mBundle;
    @BindView(R.id.tv_upload_hp)
    TextView mTvUploadHp;
    private String mobile, code, pwd, imgUrl, name, address;
    private OptionsPickerView pvOptions;
    private ArrayList<AddressBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<AddressBean.CityBean>> options2Items = new ArrayList<>();
    private CustomPopupWindow customPopwindow;
    private Uri imageUri;
    private Bitmap bitmap = null;
    private String imageUrl = "";

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mIvBack).flymeOSStatusBarFontColor("#333333").init();
    }

    @Override
    protected void initData() {
        AppConfig.getInstance().initImgPicker(1);//设置可选图片数量
        mBundle = getIntent().getExtras();
        if (mBundle != null) {
            mobile = mBundle.getString("mobile");
            code = mBundle.getString("code");
            pwd = mBundle.getString("pwd");
        } else {
            ToastUtil.showToast("参数异常，请重新操作");
        }
        getAddressData();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.user_activity_makeinfo;
    }

    @Override
    protected void initView() {
        mCheckBoxUtil = new CheckBoxUtil();
        mCheckBoxUtil.CheckBoxUtil(mCbSexBox, mCbSexGirl);
    }

    @Override
    protected void setListener() {

    }


    @OnClick({R.id.iv_back, R.id.iv_user_hp, R.id.tv_upload_hp, R.id.cb_sex_box, R.id.cb_sex_girl, R.id.tv_address, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_user_hp:
                customPopwindow = new CustomPopupWindow(this, itemsOnClick, "1");
                customPopwindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.tv_upload_hp:
//                if (bitmap != null) {
//                    ToastUtil.showToast("头像上传");
//                } else {
//                    ToastUtil.showToast("请先选择头像");
//                }
                break;
            case R.id.cb_sex_box:
                mCheckBoxUtil.SwitchCheckBox(mCbSexBox);
                break;
            case R.id.cb_sex_girl:
                mCheckBoxUtil.SwitchCheckBox(mCbSexGirl);
                break;
            case R.id.tv_address:
                if (options1Items.size() > 0) {
                    pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                        @Override
                        public void onOptionsSelect(int options1, int options2, int options3, View v) {
                            address = options1Items.get(options1).getPickerViewText() + "  "
                                    + options2Items.get(options1).get(options2).getName();
                            Logcat.i("选择的地址：" + address);
                            mTvAddress.setText(address);

                        }
                    })
                            .setTitleText("城市选择")
                            .setContentTextSize(20)
                            .isCenterLabel(false)
                            .setBackgroundId(0x66000000) //设置外部遮罩颜色
                            .build();
                    pvOptions.setPicker(options1Items, options2Items);
                    pvOptions.show();
                } else {
                    Logcat.i("地址集合小于1");
                }
                break;
            case R.id.btn_submit:
                RegData(mobile, pwd, code, address, imageUrl, mEtName.getText().toString());
                break;
        }
    }

    private void RegData(String mobile, String pwd, String code, String address, String imgUrl, String name) {
        Logcat.i(StringUtil.isEmpty(name) + "/" + StringUtil.isEmpty(address) + "/" + address);
        if (StringUtils.isBlank(name)) {
            ToastUtil.showToast("请填写姓名");
            return;
        }
        if (mCheckBoxUtil.isChecked() == null) {
            ToastUtil.showToast("请选择性别");
            return;
        }
        if (StringUtils.isBlank(address)) {
            ToastUtil.showToast("请选择地区");
            return;
        }
        if (mCheckBoxUtil.isChecked() == mCbSexBox) {
            ExecuteNext(mobile, code, pwd, name, "1", address, imgUrl);
        } else {
            ExecuteNext(mobile, code, pwd, name, "2", address, imgUrl);
        }
    }


    private void ExecuteNext(String mobile, String code, String pwd, String name, String sex, String address, String imgUrl) {
        Map map = new HashMap();
        map.put("mobile", mobile);
        map.put("userName", name);
        map.put("password", pwd);
        map.put("smsNumber", code);
        map.put("sex", sex);
        map.put("address", address);
        map.put("imageUrl", ""+imgUrl);
        final String str = map.toString();
        HttpClient.post(NetWorkConstant.USER_REISTER, map, new HttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String content) {
                super.onSuccess(statusCode, content);
                Logcat.i("\n接口地址：" + NetWorkConstant.USER_REISTER + "\n提交的参数：" + str + "\n" + "返回码：" + statusCode + "\n" + "返回参数：" + content);
                final ResultBean mResultBean = JSON.parseObject(content, ResultBean.class);
                if (mResultBean.getCode().equals("1")) {
                    UserInfoBean userInfoBean = JSON.parseObject(mResultBean.getData(), UserInfoBean.class);
                    UserUtil.setUserBean(MineMakeInfoActivity.this, JSON.toJSONString(userInfoBean));
                    Logcat.e("原始数据" + userInfoBean.toString());
                    SharedPreferencesUtils.setSP(MineMakeInfoActivity.this, "Mobile", userInfoBean.getMobile());
                    SharedPreferencesUtils.setSP(MineMakeInfoActivity.this, "UserId", userInfoBean.getId());
                    SharedPreferencesUtils.setSP(MineMakeInfoActivity.this, "Token", userInfoBean.getToken());
                    UserUtil.setUserBean(MineMakeInfoActivity.this, JSON.toJSONString(userInfoBean));
                    UserInfoBean userInfoBean1 = UserUtil.getUserBean(MineMakeInfoActivity.this);
                    Logcat.i("数据插入成功：" + userInfoBean1.toString() + "/\n" + userInfoBean1.toString());
                } else {
                    Logcat.e("" + mResultBean.getMsg());
                    ToastUtil.showToast(mResultBean.getMsg());
                }
            }

            @Override
            public void onFailure(Request request, IOException e) {
                super.onFailure(request, e);
                ToastUtil.showToast("网络错误，请重试");
            }
        });

    }

    private void getAddressData() {
        HttpClient.get(NetWorkConstant.GET_ADDRESS, null, new HttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String content) {
                super.onSuccess(statusCode, content);
                Logcat.i("\n提交的参数：" + 0 + "\n" + "返回码：" + statusCode + "\n" + "返回参数：" + content);
                if (statusCode == 200) {
                    ResultBean mResultBean = JSON.parseObject(content, ResultBean.class);
                    List<AddressBean> addressBeanList = JSON.parseArray(mResultBean.getData(), AddressBean.class);
                    for (int i = 0; i < addressBeanList.size(); i++) {
                        options1Items.add(addressBeanList.get(i));
                        ArrayList<AddressBean.CityBean> options2Item = new ArrayList<>();
                        options2Item.addAll(options1Items.get(i).getCity());
                        options2Items.add(options2Item);
                    }
                }
            }

            @Override
            public void onFailure(Request request, IOException e) {
                super.onFailure(request, e);
            }
        });

    }

    private View.OnClickListener itemsOnClick = new View.OnClickListener() {

        public void onClick(View v) {
            Intent intent = new Intent(MineMakeInfoActivity.this, ImageGridActivity.class);
            customPopwindow.dismiss();
            customPopwindow.backgroundAlpha(MineMakeInfoActivity.this, 1f);
            switch (v.getId()) {
                case R.id.ll_select01:
                    //相机
                    intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                    startActivityForResult(intent, CAMERA_PICKER);
                    break;
                case R.id.ll_select02:
                    //相册
                    startActivityForResult(intent, PHOTO_PICKER);
                    break;
            }
        }
    };
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
                        if (!ListUtil.isEmpty(images)) {
                            uploadFile(images.get(0));
                        }
                        Logcat.i("选择的图片数据：PHOTO_PICKER" + Arrays.toString(images.toArray()));
                        GlideImgManager.glideLoader(this, Uri.fromFile(new File(images.get(0).path)), mIvUserHp);
                        InputStream in;
                        try {
                            in = this.getContentResolver().openInputStream(Uri.fromFile(new File(images.get(0).path)));
                            bitmap = BitmapFactory.decodeStream(in);
                            IOUtil.close(in);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else {
                        ToastUtil.showToast("请重新选择");
                    }
                    break;
                case CAMERA_PICKER:
                    if (data != null) {
                        ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                        if (!ListUtil.isEmpty(images)) {
                            uploadFile(images.get(0));
                        }
                        Logcat.i("选择的图片数据：CAMERA_PICKER" + Arrays.toString(images.toArray()));
                        GlideImgManager.glideLoader(this, Uri.fromFile(new File(images.get(0).path)), mIvUserHp);
                        ContentResolver cr = this.getContentResolver();
                        try {
                            bitmap = BitmapFactory.decodeStream(cr.openInputStream(Uri.fromFile(new File(images.get(0).path))));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else {
                        ToastUtil.showToast("请重新选择");
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void uploadFile(ImageItem imageItem) {
        String fileName;
        if (StringUtil.isEmpty(imageItem.name)) {
            fileName = imageItem.path.substring(imageItem.path.lastIndexOf("/") + 1, imageItem.path.length());
        } else {
            fileName = imageItem.name;
        }
        Logcat.i("文件名：" + fileName);

        if (fileName.length() < 15) {
            fileName = fileName.substring(fileName.length() - 10, fileName.length());
        }
        Logcat.i("处理之后的文件名：" + fileName);

        OkHttpUtils.post()
                .addFile("image", "Yoga_" + fileName, new File(imageItem.path))
                .url(ApiConstants.Urls.UPLOAD_IMG)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtil.showToast("上传失败，请重新上传");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ResultBean bean = JSON.parseObject(response, ResultBean.class);
                        if (bean.getCode().equals("1")) {
                            imageUrl = bean.getData();
                        } else {
                            if (bean.getMsg().equals("上传文件大小不符！")) {
                                ToastUtil.showToast("图片过大，请上传小于2M的图片");
                            } else {
                                ToastUtil.showToast(bean.getMsg());
                            }
                        }
                        Logcat.e("返回的数据：" + response);
                    }
                });
    }

}
