package com.idyoga.yoga.fragment.shop;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.idyoga.yoga.R;
import com.idyoga.yoga.adapter.ImagesAdapter;
import com.idyoga.yoga.base.BaseFragment;
import com.idyoga.yoga.comm.ApiConstants;
import com.idyoga.yoga.common.http.type1.HttpClient;
import com.idyoga.yoga.common.http.type1.HttpResponseHandler;
import com.idyoga.yoga.model.ImagesBean;
import com.idyoga.yoga.model.ResultBean;
import com.idyoga.yoga.utils.ToastUtil;
import com.idyoga.yoga.utils.ViewUtil;
import com.idyoga.yoga.view.RecycleViewDivider;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Request;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;

/**
 * @author weilgu
 * @time 2019/3/15 17:35
 * @des ${TODO}
 */

public class ShopImageFragment extends BaseFragment {

    private String mShopId;
    private int mPage = 1;

    @BindView(R.id.rlv_images)
    RecyclerView mRlvImages;
    private ImagesAdapter mAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_iamges_layout;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        mRlvImages.setLayoutManager(layoutManager);
        mAdapter = new ImagesAdapter(getContext());
        mRlvImages.setAdapter(mAdapter);
    }

    public void setShopId(String shopId){
        this.mShopId = shopId;
    }

    @Override
    protected void initData() {
        super.initData();
        getImageData();
    }

    private void getImageData() {
        Map map = new HashMap();
        map.put("shopId", mShopId + "");
        map.put("page", mPage + "");
        map.put("size", "20");
        map.put("size", "0");
        //mLayoutManager.showLoading();
        Logcat.i("提交的参数：" + map.toString());
        HttpClient.post(ApiConstants.Urls.SHOP_IAMGES_DATA, map, new HttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                dismissLoading();
                Logcat.i("Bean:" + content);
                ResultBean bean = JSON.parseObject(content, ResultBean.class);
                if (bean.getCode().equals("1")){
                    List<ImagesBean> imagesBeans = JSON.parseArray(bean.getData(), ImagesBean.class);
                    bindView(imagesBeans);
                }else{
                    ToastUtil.showToast(bean.getMsg());
                }
            }

            @Override
            public void onFailure(Request request, IOException e) {
                super.onFailure(request, e);
                dismissLoading();
                //mLayoutManager.showNetError();
            }
        });
    }

    private void bindView(List<ImagesBean> imagesBeans) {
        if (mAdapter != null){
            mAdapter.setDatas(imagesBeans);
            mAdapter.notifyDataSetChanged();
        }
    }
}
