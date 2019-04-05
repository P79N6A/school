package com.idyoga.yoga.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.MainActivity;
import com.idyoga.yoga.activity.SpecialDetailActivity;
import com.idyoga.yoga.model.HomePageData;
import com.idyoga.yoga.utils.ViewUtil;
import com.idyoga.yoga.view.RoundDrawable;
import com.idyoga.yoga.view.ShadowLayout;

import java.util.List;

import vip.devkit.library.Logcat;

/**
 * @author weilgu
 * @time 2019/2/27 16:01
 * @des vip权益课中的专题活动 adapter
 */

public class SpecialAdapter extends RecyclerView.Adapter<SpecialAdapter.ViewHolder> {

    private Context mContext;
    private final LayoutInflater mFrom;
    private List<HomePageData.SubjectListBean> mSubjectList;

    public SpecialAdapter(Context context){
        this.mContext = context;
        mFrom = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Logcat.i("---------------------------onCreateViewHolder------------");
        return new ViewHolder(mFrom.inflate(R.layout.item_special_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        HomePageData.SubjectListBean listBean = mSubjectList.get(position);
        Glide.with(mContext)
                .load(listBean.getImage_url())
                .placeholder(R.drawable.img_course)
                .error(R.drawable.img_course)
                .into(holder.iv);
        holder.tv_sub_title.setText(listBean.getTitle() + "");
        holder.mSlRoot.setBackground(new RoundDrawable(ViewUtil.dp2px(mContext,5),0.2f));
        holder.mSlRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("subjectId",mSubjectList.get(holder.getAdapterPosition()).getSubjectId());
                MainActivity ac = (MainActivity) mContext;
                Intent intent = new Intent(ac, SpecialDetailActivity.class);
                intent.putExtras(bundle);
                ac.startActivity(intent);
            }
        });
        Logcat.i("---------------------------onBindViewHolder------------");
    }

    @Override
    public int getItemCount() {
        return mSubjectList != null ? mSubjectList.size() : 0;
    }

    public void setDatas(List<HomePageData.SubjectListBean> subjectList) {
        this.mSubjectList = subjectList;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv;
        private TextView tv_sub_title;
        private ShadowLayout mSlRoot;

        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv_sub_title = itemView.findViewById(R.id.tv_sub_title);
            mSlRoot = itemView.findViewById(R.id.sl_root);
            Logcat.i("---------------------------ViewHolder------------");
//            select_login_mode = itemView.findViewById(R.id.select_login_mode);
        }
    }
}
