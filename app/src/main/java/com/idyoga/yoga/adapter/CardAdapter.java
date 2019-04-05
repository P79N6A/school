package com.idyoga.yoga.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.idyoga.yoga.R;
import com.idyoga.yoga.model.CourseUserInfo;

import java.util.List;

/**
 * @author weilgu
 * @time 2019/3/26 18:03
 * @des ${TODO}
 */

public class CardAdapter extends BaseAdapter {

    private Context mContext;
    private final LayoutInflater mFrom;
    private List<CourseUserInfo.UserInfoBean.CardBean> mCardList;

    public CardAdapter(Context context,List<CourseUserInfo.UserInfoBean.CardBean> cardList){
        this.mContext = context;
        this.mCardList = cardList;
        mFrom = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mCardList != null ? mCardList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mCardList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = mFrom.inflate(R.layout.item_dialog_menber_card_layout,parent,false);
            holder.mCardName = convertView.findViewById(R.id.tv_card_name);
            holder.mSelectIma = convertView.findViewById(R.id.iv_select);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        CourseUserInfo.UserInfoBean.CardBean cardBean = mCardList.get(position);
        holder.mCardName.setText(cardBean.getCard_name());
        if (cardBean.isSelect()){
            holder.mCardName.setTextColor(Color.parseColor("#8338F9"));
            holder.mSelectIma.setImageDrawable(mContext.getResources().getDrawable(R.drawable.select_card));
        }else{
            holder.mCardName.setTextColor(Color.parseColor("#333333"));
            holder.mSelectIma.setImageDrawable(mContext.getResources().getDrawable(R.drawable.un_select_card));
        }
        return convertView;
    }

    class ViewHolder {
        private TextView mCardName;
        private ImageView mSelectIma;
    }
}
