package com.idyoga.yoga.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.alivc.player.JsonUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.home.SubjectListActivity;
import com.idyoga.yoga.activity.home.SubjectVideoActivity;
import com.idyoga.yoga.activity.video.VideoActivity;
import com.idyoga.yoga.common.adapter.CommonAdapter;
import com.idyoga.yoga.common.adapter.ViewHolder;
import com.idyoga.yoga.common.modle.PostResult;
import com.idyoga.yoga.model.CourseVideoListBean;
import com.idyoga.yoga.model.HomeSubjectBean;
import com.idyoga.yoga.model.HomeSubjectItemBean;
import com.idyoga.yoga.utils.GlideImgManager;

import java.util.List;

import butterknife.OnItemClick;
import de.greenrobot.event.EventBus;
import vip.devkit.library.Logcat;

public class HomeSubjectAdapter extends CommonAdapter<HomeSubjectItemBean.VideoGroupBean>  {


    public HomeSubjectAdapter(Context context, List<HomeSubjectItemBean.VideoGroupBean> mStringList,  int layoutId) {
        super(context, mStringList, layoutId);
        this.mContext = context;
        this.mBeanList = mStringList;
    }

    @Override
    public void convert(ViewHolder holder, HomeSubjectItemBean.VideoGroupBean homeSubjectBean, int i) {
        holder.setText(R.id.tv_home_list_title, homeSubjectBean.getTitle());
        ListView itemList = holder.getView(R.id.iv_home_item_list);
        if (itemList != null) {
            itemList.setPressed(false);//去掉焦点
            itemList.setFocusableInTouchMode(false);
            itemList.requestFocus();
            HomeSubjectListItemAdapter itemAdapter = new HomeSubjectListItemAdapter(mContext, homeSubjectBean.getVideoGroupAction(), R.layout.item_home_subject_video);
            itemList.setAdapter(itemAdapter);
            itemAdapter.notifyDataSetChanged();
            setListener(itemList, i);
        }
    }
    String  itemBeanJson;
    public void setSubjectItemBean(String itemBeanJson){
        this.itemBeanJson=itemBeanJson;
        Logcat.i("itemBean"+itemBeanJson);
    }

    protected void setListener(ListView listView, final int i) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle mBundle = new Bundle();
                mBundle.putString("subjectItemBean",itemBeanJson);
                mBundle.putString("videoId",mBeanList.get(i).getVideoGroupAction().get(position).getVideo());
                Intent intent = new Intent(mContext, SubjectVideoActivity.class);
                intent.putExtras(mBundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }
}