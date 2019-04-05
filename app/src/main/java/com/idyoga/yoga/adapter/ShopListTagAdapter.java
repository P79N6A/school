package com.idyoga.yoga.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.R;
import com.idyoga.yoga.model.ShopTag;

import java.util.List;

/**
 * @author weilgu
 * @time 2019/4/3 17:45
 * @des ${TODO}
 */

public class ShopListTagAdapter extends BaseQuickAdapter<ShopTag,BaseViewHolder>{
    private Context mContext;
    private OnTagClickListener mListener;

    public ShopListTagAdapter(int layoutResId, @Nullable List<ShopTag> data, Context context) {
        super(layoutResId, data);
        this.mContext = context;
    }

    public void setListener(OnTagClickListener listener) {
        mListener = listener;
    }

    @Override
    protected void convert(BaseViewHolder helper, final ShopTag item) {
        helper.setText(R.id.tv_all,item.getTagName());
        if (item.isSelect()){
            helper.setTextColor(R.id.tv_all,mContext.getResources().getColor(R.color.select_login_mode));
            helper.getView(R.id.tv_all).setSelected(true);
        }else{
            helper.setTextColor(R.id.tv_all,mContext.getResources().getColor(R.color.text_color));
            helper.getView(R.id.tv_all).setSelected(false);
        }
        helper.getView(R.id.tv_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.isSelect()){
                    item.setSelect(false);
                }else{
                    item.setSelect(true);
                }
                if (mListener != null){
                    mListener.onTagClickListener(item);
                }
                notifyDataSetChanged();
            }
        });
    }


    public interface OnTagClickListener{
        void onTagClickListener(ShopTag item);
    }
}
    /*RecyclerView.Adapter<ShopListTagAdapter.ViewHolder> {


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}*/
