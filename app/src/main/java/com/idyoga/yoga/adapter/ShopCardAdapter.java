package com.idyoga.yoga.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.idyoga.yoga.R;
import com.idyoga.yoga.view.dialog.BuyCardDialog;

/**
 * @author weilgu
 * @time 2019/3/13 10:26
 * @des ${TODO}
 */

public class ShopCardAdapter extends RecyclerView.Adapter<ShopCardAdapter.ViewHolder> {

    private Context mContext;
    private final LayoutInflater mFrom;

    public ShopCardAdapter(Context context) {
        mContext = context;
        mFrom = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mFrom.inflate(R.layout.item_shop_deteil_item_in_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mBuy;

        public ViewHolder(View itemView) {
            super(itemView);
            mBuy = itemView.findViewById(R.id.tv_buy);
        }

        public void bindView(int position){
            mBuy.setTag(position);
            mBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BuyCardDialog cardDialog = new BuyCardDialog(mContext);
                    cardDialog.show();
                }
            });
        }
    }
}
