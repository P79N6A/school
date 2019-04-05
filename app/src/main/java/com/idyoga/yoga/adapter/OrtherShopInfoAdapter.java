package com.idyoga.yoga.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.idyoga.yoga.R;

/**
 * @author weilgu
 * @time 2019/3/13 14:28
 * @des ${TODO}
 */

public class OrtherShopInfoAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private final LayoutInflater mFrom;
    private static final int SHOW_SHOP_INFO = 0;
    private static final int SHOW_START_OR_END = 1;

    public OrtherShopInfoAdapter(Context context){
        this.mContext = context;
        mFrom = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == SHOW_SHOP_INFO){
            new ViewHolder(mFrom.inflate(R.layout.item_shop_course_list_v2,parent,false));
        }else if(viewType == SHOW_START_OR_END){
            new StartHolder(mFrom.inflate(R.layout.item_list_start_or_end,parent,false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == SHOW_SHOP_INFO){
            ((ViewHolder)holder).bindView();
        }else if(viewType == SHOW_START_OR_END){
            ((StartHolder)holder).bindView();
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mShopImage;
        private TextView mShopName;
        private TextView mAddress;
        private TextView mDistance;
        private RecyclerView mOrtherImages;
        private LinearLayout mGroup;
        private TextView mObjectName;
        private TextView mOldPic;
        private final HomeGuessAdapter mAdapter;


        public ViewHolder(View itemView) {
            super(itemView);
            mShopImage = itemView.findViewById(R.id.iv_img);
            mShopImage = itemView.findViewById(R.id.tv_shop_name);
            mAddress = itemView.findViewById(R.id.tv_address);
            mDistance = itemView.findViewById(R.id.tv_distance);
            mOrtherImages = itemView.findViewById(R.id.rlv_shop_image);
            mGroup = itemView.findViewById(R.id.rl_group);
            mObjectName = itemView.findViewById(R.id.tv_object_name);
            mOldPic = itemView.findViewById(R.id.tv_old_pic);
            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
            mOrtherImages.setLayoutManager(layoutManager);
            mAdapter = new HomeGuessAdapter(mContext);
            mOrtherImages.setAdapter(mAdapter);
        }

        public void bindView() {

        }
    }

    class StartHolder extends RecyclerView.ViewHolder {
        private TextView mTitle;

        public StartHolder(View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.tv_title);
        }

        public void bindView() {

        }
    }
}
