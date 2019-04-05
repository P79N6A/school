package com.idyoga.yoga.activity.user;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.bumptech.glide.Glide;
import com.idyoga.yoga.R;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.comm.AppConfig;
import com.idyoga.yoga.comm.AppContext;
import com.idyoga.yoga.comm.NetWorkConstant;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.common.http.type1.HttpClient;
import com.idyoga.yoga.common.http.type1.HttpResponseHandler;
import com.idyoga.yoga.common.http.type2.ICommonViewUi;
import com.idyoga.yoga.common.http.type2.OkHttpClientManager;
import com.idyoga.yoga.common.http.type2.presenter.ICommonRequestPresenter;
import com.idyoga.yoga.common.http.type2.presenter.impl.CommonRequestPresenterImpl;
import com.idyoga.yoga.common.modle.CircleTransform;
import com.idyoga.yoga.common.modle.GlideRoundTransform;
import com.idyoga.yoga.common.view.CustomPopupWindow;
import com.idyoga.yoga.model.AddressDataBean;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.model.UserInfoBean;
import com.idyoga.yoga.model.address.AddressBean;
import com.idyoga.yoga.utils.CommonUtils;
import com.idyoga.yoga.utils.GlideImgManager;
import com.idyoga.yoga.utils.JsonUtil;
import com.idyoga.yoga.utils.ThreadUtils;
import com.idyoga.yoga.utils.ToastUtil;
import com.idyoga.yoga.utils.UserUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Request;
import vip.devkit.library.CommonUtil;
import vip.devkit.library.ListUtil;
import vip.devkit.library.Logcat;
import vip.devkit.library.RelyUtil.IOUtil;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.library.StringUtil;
import vip.devkit.view.common.ImgPicker.ImagePicker;
import vip.devkit.view.common.ImgPicker.bean.ImageItem;
import vip.devkit.view.common.ImgPicker.ui.ImageGridActivity;

public class AccountActivity extends BaseActivity implements ICommonViewUi {

    @BindView(R.id.ll_title_back)
    LinearLayout mLlTitleBack;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.tv_title_right)
    TextView mTvTitleRight;
    @BindView(R.id.ll_title_right)
    LinearLayout mLlTitleRight;
    @BindView(R.id.ll_common_layout)
    RelativeLayout mLlCommonLayout;
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.rg_view)
    RadioGroup mRgView;
    @BindView(R.id.tv_birthday)
    TextView mTvBirthday;
    @BindView(R.id.et_mobile)
    EditText mEtMobile;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;
    @BindView(R.id.rb_boy)
    RadioButton mRbBoy;
    @BindView(R.id.rb_girl)
    RadioButton mRbGirl;
    @BindView(R.id.iv_img)
    ImageView mIvHp;
    UserInfoBean mUserInfoBean;
    private String userId, Token, shopId;
    private String name, birthday, address, mobile, imageUrl;
    private int sexId;
    private OptionsPickerView pvOptions;
    private CustomPopupWindow customPopwindow;
    private ArrayList<AddressDataBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<AddressDataBean.CityListBean>> options2Items = new ArrayList<>();
    private List<List<List<AddressDataBean.CityListBean.AreaListBean>>> options3Items = new ArrayList<>();


    @Override
    protected ICommonRequestPresenter initICommonViewUi() {
        return this.iCommonRequestPresenter = new CommonRequestPresenterImpl(this, this);
    }

    @Override
    protected void initData() {
        mUserInfoBean = UserUtil.getUserBean(this);
        shopId = (String) SharedPreferencesUtils.getSP(this, "shopId", "");
        if (mUserInfoBean == null) {
            Logcat.e("参数为空！！！");
        } else {
            Logcat.i("mUserInfoBean：" + mUserInfoBean.toString());
        }
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlCommonLayout).flymeOSStatusBarFontColor("#333333").init();
    }


    @Override
    protected int setLayoutId() {
        return R.layout.activity_user_account;
    }


    @Override
    protected void initView() {
        mTvTitleText.setText("个人信息");
        mEtMobile.setEnabled(false);
        AppConfig.getInstance().initImgPicker(1);//设置可选择数量
        if (mUserInfoBean != null) {
            if (mUserInfoBean.getBirthday() != 0) {
                mTvBirthday.setText(CommonUtils.getDateStringByTimeSTamp(Long.valueOf(mUserInfoBean.getBirthday()), "yyyy-MM-dd"));
            }
            mEtName.setText(mUserInfoBean.getUsername());
            mEtMobile.setText(mUserInfoBean.getMobile());
            mTvBirthday.setText(CommonUtils.getDateStringByTimeSTamp((long) mUserInfoBean.getBirthday(), "yyyy-MM-dd"));
            mTvAddress.setText(mUserInfoBean.getAddress() + "");
            Logcat.e("-------------------------"+mUserInfoBean.getAvatar_url());
            Glide.with(mContext)
                    .load(mUserInfoBean.getAvatar_url())
                    .placeholder(R.drawable.img_02)
                    .transform(new CircleTransform(this))
                    .error(R.drawable.img_02)
                    .into(mIvHp);
            if (mUserInfoBean.getSex() == 1) {
                mRbBoy.setChecked(true);
            } else {
                mRbGirl.setChecked(true);
            }
        }
        initPvOptions();
    }

    private void initPvOptions() {
        pvOptions = new OptionsPickerView.Builder(this, mOptionsSelectListener)
                .setTitleText("地区选择")
                .setContentTextSize(20)
                .isCenterLabel(false)
                .setBackgroundId(0x66000000) //设置外部遮罩颜色
                .build();
        String json = (String) SharedPreferencesUtils.getSP(this, "addressJson", "");
        if (!StringUtil.isEmpty(json)) {
            List<AddressDataBean> beanList = JSON.parseArray(json, AddressDataBean.class);
            initAddressData(beanList, false);
        }
    }

    @Override
    protected void setListener() {

    }

    @OnClick({R.id.ll_title_back, R.id.tv_birthday, R.id.tv_address, R.id.tv_submit, R.id.iv_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_back:
                finish();
                break;
            case R.id.tv_birthday:
                showTimePicker();
                break;
            case R.id.tv_address:
                showLoading("加载地址中...");
                if (pvOptions != null && pvOptions.isShowing()) {
                    pvOptions.dismiss();
                }
                if (!ListUtil.isEmpty(options1Items)) {
                    dismissLoading();
                    pvOptions.show();
                } else {
                    getAddressData();
                }
                break;
            case R.id.tv_submit:
                name = mEtName.getText().toString();
                birthday = mTvBirthday.getText().toString();
                address = mTvAddress.getText().toString();
                mobile = mEtMobile.getText().toString();
                RegData(name, birthday, address, mobile);
                break;
            case R.id.iv_img:
                customPopwindow = new CustomPopupWindow(this, itemsOnClick, "1");
                customPopwindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
        }
    }

    private void getAddressData() {
        Map map = new HashMap();
        OkHttpUtils.post().url("https://platform.hq-xl.com/mall/Address/getMallAddress").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoading();
                ToastUtil.showToast("网络错误，请重试");
            }

            @Override
            public void onResponse(String response, int id) {
                dismissLoading();
                if (!TextUtils.isEmpty(response)) {
                    ResultBean resultBean = JSON.parseObject(response, ResultBean.class);
                    if (resultBean != null && resultBean.getCode().equals("1")) {
                        String json = JsonUtil.parserSingleData(resultBean.getData(), "province");
                        List<AddressDataBean> beanList = JSON.parseArray(json, AddressDataBean.class);
                        initAddressData(beanList, true);
                    } else {
                        ToastUtil.showToast("加载失败，请重试");
                    }
                } else {
                    ToastUtil.showToast("网络错误，请重试");
                }
            }
        });
    }

    private void RegData(String name, String birthday, String address, String mobile) {
        if (StringUtil.isEmpty(imageUrl)) {
            imageUrl = mUserInfoBean.getAvatar_url();
        }
        if (StringUtil.isEmpty(name)) {
            ToastUtil.showToast("请输入姓名");
            return;
        }
        if (mRgView.getCheckedRadioButtonId() == -1) {
            ToastUtil.showToast("选择性别");
            return;
        }
        if (StringUtil.isEmpty(birthday)) {
            ToastUtil.showToast("请选择生日");
            return;
        }
        if (StringUtil.isEmpty(address) || address.equals("点击选择所在地区")) {
            ToastUtil.showToast("请选择地区");
            return;
        }
        if (StringUtil.isEmpty(mobile)) {
        }
        if (!CommonUtils.isMobile(mobile)) {
        }
        RadioButton button = mRgView.findViewById(mRgView.getCheckedRadioButtonId());
        String sex = button.getText().toString();
        sex = sex.replaceAll(" ", "");
        if (sex.equals("男")) {
            sexId = 1;
        } else {
            sexId = 2;
        }
        int d = 0;
        try {
            d = (int) (new SimpleDateFormat("yyyy-MM-dd").parse(birthday).getTime() / 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Logcat.e("---------------:" + mUserInfoBean.getUsername()
                + "|" + name + "/" +
                mUserInfoBean.getSex()
                + "|" + sexId + "/"
                + mUserInfoBean.getBirthday()
                + "|" + d + "/"
                + mUserInfoBean.getAvatar_url()
                + "|" + imageUrl
                + mUserInfoBean.getAddress()
                + "|"+address);
        String userName=StringUtil.isEmpty(mUserInfoBean.getAvatar_url())?"":mUserInfoBean.getUsername();
        String userAddress=StringUtil.isEmpty(mUserInfoBean.getAddress())?"":mUserInfoBean.getAddress();
        String userImg=StringUtil.isEmpty(mUserInfoBean.getAvatar_url())?"":mUserInfoBean.getAvatar_url();
        int userSex = StringUtil.isEmpty(mUserInfoBean.getSex()+"")?-1:mUserInfoBean.getSex();
        int userBirthday = StringUtil.isEmpty(mUserInfoBean.getBirthday()+"")?-1:mUserInfoBean.getBirthday();
        if (userName.equals(name)
                && userSex == sexId
                && userBirthday == d
                && userImg.equals(imageUrl)
                && userAddress.equals(address)) {
            ToastUtil.showToast("请做出修改再保存");
            return;
        }
        toRequest(ApiConstants.EventTags.USER_UPDATE_INFO);
    }

    @Override
    public void toRequest(int eventTag) {
        Map map = new HashMap();
        if (eventTag == ApiConstants.EventTags.USER_UPDATE_INFO) {
            map.put("userName", name);
            map.put("sex", sexId + "");
            map.put("mobile", mobile + "");
            map.put("birthday", birthday);
            map.put("address", address);
            map.put("userId", mUserInfoBean.getId() + "");
            map.put("shopId", shopId + "");
            map.put("imageUrl", imageUrl);
            Logcat.i("修改提交的参数：" + map.toString());
            iCommonRequestPresenter.request(eventTag, this, ApiConstants.Urls.USER_UPDATE_INFO, map);
        }

    }

    @Override
    public void getRequestData(int eventTag, String result) {
        Logcat.i("eventTag:" + eventTag + "/" + result);
        ResultBean bean = JSON.parseObject(result, ResultBean.class);
        if (bean.getCode().equals("1") && bean.getData() != null) {
            if (eventTag == ApiConstants.EventTags.USER_UPDATE_INFO) {
                UserInfoBean mUserInfoBean = UserUtil.getUserBean(this);
                mUserInfoBean.setUsername(name);
                mUserInfoBean.setMobile(mobile);
                mUserInfoBean.setAvatar_url(imageUrl);
                mUserInfoBean.setAddress(address);
                mUserInfoBean.setType("true");
                Date date = null;
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    date = formatter.parse(birthday);
                    mUserInfoBean.setBirthday((int) (formatter.getCalendar().getTimeInMillis() / 1000));
                    Logcat.e("setBirthday:" + birthday + "/" + formatter.format(date) + "/" + mUserInfoBean.getBirthday());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                mUserInfoBean.setSex(sexId);
                UserUtil.setUserBean(mContext, JSON.toJSONString(mUserInfoBean));
                ToastUtil.showToast("修改成功");
                setResult(900);
                finish();
            }
        } else {
            ToastUtil.showToast(bean.getMsg());
        }

    }

    @Override
    public void onRequestFailureException(int eventTag, String msg) {

    }


    private OptionsPickerView.OnOptionsSelectListener mOptionsSelectListener = new OptionsPickerView.OnOptionsSelectListener() {
        @Override
        public void onOptionsSelect(int options1, int options2, int options3, View v) {
            String address = options1Items.get(options1).getRegion_name() + "\t\t"
                    + options2Items.get(options1).get(options2).getRegion_name() + "\t\t"
                    + options3Items.get(options1).get(options2).get(options3).getRegion_name();
            Logcat.i("选择的地址：" + address);
            mTvAddress.setText(address);

        }
    };


    /**
     * 时间选择
     */
    private void showTimePicker() {
        Calendar calendar = Calendar.getInstance();
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        selectedDate.set(2001, 0, 1);
        startDate.set(1950, 0, 1);
        Calendar endDate = Calendar.getInstance();
//        endDate.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH - 1), calendar.get(Calendar.DATE));
        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                Logcat.i("选择的日期：" + date + "/");
                SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
                String result = sd.format(date);
                mTvBirthday.setText("" + result);
            }
        }).setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate, endDate)//起始终止年月日设定.build();
                .setTitleText("生日选择")
                .setType(new boolean[]{true, true, true, false, false, false})
                .setBackgroundId(0x66000000) //设置外部遮罩颜色
                .build();
        pvTime.show();
    }


    private View.OnClickListener itemsOnClick = new View.OnClickListener() {

        public void onClick(View v) {
            Intent intent = new Intent(AccountActivity.this, ImageGridActivity.class);
            customPopwindow.dismiss();
            customPopwindow.backgroundAlpha(AccountActivity.this, 1f);
            switch (v.getId()) {
                case R.id.ll_select01:
                    intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                    startActivityForResult(intent, CAMERA_PICKER);
                    break;
                case R.id.ll_select02:
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
                        Logcat.i("选择的图片数据：PHOTO_PICKER" + Arrays.toString(images.toArray()));
                        uploadFile(images.get(0));
                        initImgView(images.get(0).path);
                    } else {
                        ToastUtil.showToast("请重新选择");
                    }
                    break;
                case CAMERA_PICKER:
                    if (data != null) {
                        ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                        Logcat.i("选择的图片数据：CAMERA_PICKER" + Arrays.toString(images.toArray()));
                        uploadFile(images.get(0));
                        initImgView(images.get(0).path);
                    } else {
                        ToastUtil.showToast("请重新选择");
                    }
                    break;
                default:
                    break;
            }
        }
    }

    void initImgView(String url) {
        Logcat.e("img:" + url);
        Glide.with(mContext)
                .load(Uri.fromFile(new File(url)))
                .placeholder(R.drawable.img_02)
                .transform(new CircleTransform(this))
                .error(R.drawable.img_02)
                .into(mIvHp);
    }

    private void initAddressData(List<AddressDataBean> beanList, boolean isShow) {
        if (options1Items.size() > 0) {
            options1Items.clear();
        }
        if (options2Items.size() > 0) {
            options2Items.clear();
        }
        if (options3Items.size() > 0) {
            options2Items.clear();
        }

        if (ListUtil.isEmpty(beanList)) {
            Logcat.i("empty");
            return;
        }
        SharedPreferencesUtils.setSP(this, "addressJson", JSON.toJSONString(beanList));
        Logcat.i("------开始---------" + System.currentTimeMillis() / 1000 + "/"
                + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:").format(new Date()));
        int p = beanList.size();
        options1Items.addAll(beanList);
        for (int i = 0; i < p; i++) {
            List<AddressDataBean.CityListBean> cityListBeans = beanList.get(i).getCityList();
            options2Items.add((ArrayList<AddressDataBean.CityListBean>) cityListBeans);
            int c = beanList.get(i).getCityList().size();
            List<List<AddressDataBean.CityListBean.AreaListBean>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）
            for (int j = 0; j < c; j++) {
                List<AddressDataBean.CityListBean.AreaListBean> City_AreaList = beanList.get(i).getCityList().get(j).getAreaList();
                Province_AreaList.add(City_AreaList);
            }
            options3Items.add(Province_AreaList);
        }
        Logcat.i("------结束---------" + System.currentTimeMillis() / 1000 + "/"
                + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
        dismissLoading();
        pvOptions.setPicker(options1Items, options2Items, options3Items);
        if (isShow) {
            pvOptions.show();
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
