package com.idyoga.yoga.holder;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.idyoga.yoga.R;
import com.idyoga.yoga.adapter.ItemOrtherShopInfoAdapter;
import com.idyoga.yoga.model.NearbyShopListBean;
import com.idyoga.yoga.model.ShopDetailDataBean;
import com.idyoga.yoga.view.decoration.HorizontalDividerItemDecoration;

import java.util.List;

/**
 * @author weilgu
 * @time 2019/3/13 15:03
 * @des
 */

public class OrtherShopInfoHolder extends RecyclerView.ViewHolder {

    private RecyclerView mRlv;
    private ItemOrtherShopInfoAdapter mAdapter;

    public OrtherShopInfoHolder(View itemView) {
        this(itemView,null);
    }

    public OrtherShopInfoHolder(View itemView, Context context) {
        super(itemView);
        mRlv = itemView.findViewById(R.id.rlv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRlv.setLayoutManager(layoutManager);
        mRlv.addItemDecoration(new HorizontalDividerItemDecoration.Builder(context).build());
        mAdapter = new ItemOrtherShopInfoAdapter(context);
        mRlv.setAdapter(mAdapter);
    }

    public void bindView(List<NearbyShopListBean> beans){
        mAdapter.setDatas(beans);
    }

}
