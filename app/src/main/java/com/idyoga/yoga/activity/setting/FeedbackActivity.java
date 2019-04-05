package com.idyoga.yoga.activity.setting;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.TestUploadFileActivity;
import com.idyoga.yoga.adapter.AddImgAdapter;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.comm.AppConfig;
import com.idyoga.yoga.common.adapter.CommonAdapter;
import com.idyoga.yoga.common.adapter.ViewHolder;
import com.idyoga.yoga.listener.OnItemClickListener;
import com.idyoga.yoga.listener.OnRvItemClickListener;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.utils.LoginUtil;
import com.idyoga.yoga.utils.ToastUtil;
import com.idyoga.yoga.utils.UserUtil;
import com.idyoga.yoga.view.MyGridItemDecoration;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
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
import vip.devkit.library.StringUtil;
import vip.devkit.view.common.ImgPicker.ImagePicker;
import vip.devkit.view.common.ImgPicker.bean.ImageItem;
import vip.devkit.view.common.ImgPicker.ui.ImageGridActivity;
import vip.devkit.view.common.ImgPicker.ui.ImagePreviewDelActivity;

import static vip.devkit.view.common.ImgPicker.ImagePicker.REQUEST_CODE_PREVIEW;
import static vip.devkit.view.common.ImgPicker.ImagePicker.getInstance;

/**
 * 文 件 名: FeedbackActivity
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/12
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class FeedbackActivity extends BaseActivity {
    @BindView(R.id.ll_title_back)
    LinearLayout mLlTitleBack;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.ll_common_layout)
    RelativeLayout mLlCommonLayout;
    @BindView(R.id.et_content)
    EditText mEtContent;
    @BindView(R.id.tv_num)
    TextView mTvNum;
    @BindView(R.id.ll_content)
    LinearLayout mLlContent;
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;
    @BindView(R.id.tv_pic_tag)
    TextView mTvPicTag;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    List<ImageItem> mItemList = new ArrayList<>();
    AddImgAdapter mAdapter;
    private int maxImgCount = 4;

    @Override
    protected void initData() {
        mItemList.clear();
//        addDefaultImg();
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlCommonLayout).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_feedback;
    }


    @Override
    protected void initView() {
        mTvTitleText.setText("意见反馈");
        AppConfig.getInstance().initImgPicker(4);
        mEtContent.addTextChangedListener(new TextChange());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        MyGridItemDecoration decoration = new MyGridItemDecoration(MyGridItemDecoration.HORIZONTAL);
        decoration.setColor(Color.parseColor("#f4f4f4"));
        decoration.setSize(20);
        mRvList.addItemDecoration(decoration);
        mRvList.setLayoutManager(layoutManager);
//        mAdapter = new AddImgAdapter(R.layout.item_add_img, new ArrayList<ImageItem>());
        mAdapter= new AddImgAdapter(this,new ArrayList<ImageItem>(),4);
        mRvList.setAdapter(mAdapter);
    }


    private static final int PHOTO_PICKER = 0x000101;
    private static final int PREVIEW_PICKER = PHOTO_PICKER + 1;

    @Override
    protected void setListener() {
        mAdapter.setOnItemClickListener(new AddImgAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Logcat.e("点击了 1：" + 0+"/"+position+"/"+"");
                if (position ==999) {
                    AppConfig.getInstance().initImgPicker(maxImgCount - mItemList.size());
                    Intent intent = new Intent(FeedbackActivity.this, ImageGridActivity.class);
                    startActivityForResult(intent, PHOTO_PICKER);
                } else {
                    Intent intentPreview = new Intent(FeedbackActivity.this, ImagePreviewDelActivity.class);
                    intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) mAdapter.getImages());
                    intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                    intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                    startActivityForResult(intentPreview, PREVIEW_PICKER);
                }
            }
        });
      /*  mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int type, View view, int position) {
                Logcat.e("点击了 1：" + type+"/"+position+"/"+"");
                if (type == 0&&position==999) {
                    AppConfig.getInstance().initImgPicker(maxImgCount - mItemList.size());
                    Intent intent = new Intent(FeedbackActivity.this, ImageGridActivity.class);
                    startActivityForResult(intent, PHOTO_PICKER);
                } else {
                    Intent intentPreview = new Intent(FeedbackActivity.this, ImagePreviewDelActivity.class);
                    intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) mAdapter.getImages());
                    intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                    intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                    startActivityForResult(intentPreview, PREVIEW_PICKER);
                }
            }
        });*/
    }

    public void submitData(String content) {
        Map f = new HashMap();
        if (mAdapter != null) {
            List<ImageItem> items = mAdapter.getImages();
            for (int i = 0; i < items.size(); i++) {
                if (!items.get(i).name.equals("yoga_feedback")) {
                    f.put("yoga_" + items.get(i).name, new File(items.get(i).path));
                }
            }
        }
        Map map = new HashMap();
        map.put("userId", UserUtil.getUserBean(this).getId() + "");
        map.put("content", content);
        map.put("status", "0");// 0 安卓,1 IOS
        Logcat.e("提交的内容：" + map.toString() + "/" + ApiConstants.Urls.APP_FEEDBACK + "/f" + f.size());
        OkHttpUtils.post().url(ApiConstants.Urls.APP_FEEDBACK).params(map).files("image[]", f).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Logcat.e("返回内容：" + e);
            }

            @Override
            public void onResponse(String response, int id) {
                Logcat.e("返回内容：" + response);
                ResultBean bean = JSON.parseObject(response, ResultBean.class);
                if (bean.getCode().equals("1")) {
                    show();
                } else {
                    ToastUtil.showToast(bean.getMsg());
                }
            }
        });

    }

    @OnClick({R.id.ll_title_back, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_back:
                finish();
                break;
            case R.id.tv_submit:
                if (!LoginUtil.checkLogin(this)) return;
                String content = mEtContent.getText().toString();
                if (!StringUtil.isEmpty(content)) {
                    submitData(content);
                } else {
                    ToastUtil.showToast("请输入反馈或者意见");
                }
                break;
        }
    }


    class TextChange implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable.length() > 400) {
                ToastUtil.showToast("最多可输入400个字符");
                mEtContent.setText(editable.toString().substring(0, 400));
                mEtContent.requestFocus();
                mEtContent.setSelection(400);
                mTvNum.setText("0");
            } else {
                int length = (400 - editable.length());
                mTvNum.setText("" + length);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            switch (requestCode) {
                case PHOTO_PICKER:
                    if (data != null) {
                        ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                        mItemList.addAll(images);
                        mAdapter.getImages().clear();
                        mAdapter.notifyDataSetChanged();
                        mAdapter.setImages(mItemList);
                        mAdapter.notifyDataSetChanged();
                        Logcat.i("选择的图片数据：PHOTO_PICKER" + mItemList.size() + "/" + Arrays.toString(images.toArray()));
                    } else {
                        ToastUtil.showToast("请重新选择");
                    }
                    break;
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == PREVIEW_PICKER) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    mItemList.clear();
                    mAdapter.getImages().clear();
                    mAdapter.notifyDataSetChanged();
                    mItemList.addAll(images);
                    mAdapter.setImages(mItemList);
                    mAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    public void show() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_sure);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        dialog.findViewById(R.id.btn_confirm_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                finish();
            }
        });
    }
}
