package com.idyoga.yoga.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.video.VideoClassifyActivity;
import com.idyoga.yoga.common.adapter.CommonAdapter;
import com.idyoga.yoga.common.adapter.ViewHolder;
import com.idyoga.yoga.model.VideoClassifyBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import vip.devkit.library.Logcat;
import vip.devkit.library.StringUtil;
import vip.devkit.view.common.suklib.view.FlowLayout.FlowLayout;
import vip.devkit.view.common.suklib.view.FlowLayout.TagAdapter;
import vip.devkit.view.common.suklib.view.FlowLayout.TagFlowLayout;

/**
 * 文 件 名: VideoClassifyAdapter
 * 创 建 人: By k
 * 创建日期: 2018/5/28 09:27
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注：
 */
public class VideoClassifyAdapter extends CommonAdapter<VideoClassifyBean> {
    List<VideoClassifyBean> mBeanList;

    List<TagFlowLayout> mList = new ArrayList<>();

    public VideoClassifyAdapter(Context context, List<VideoClassifyBean> mBeanList, int layoutId) {
        super(context, mBeanList, layoutId);
        this.mBeanList = mBeanList;
    }

    public void setBeanList(List<VideoClassifyBean> beanList) {
        this.mBeanList = beanList;
        Logcat.i("mBeanList:" + mBeanList.size() + "/" + beanList.size());
        notifyDataSetChanged();
    }

    @Override
    public void convert(ViewHolder holder, final VideoClassifyBean bean, int position) {
        holder.setText(R.id.tv_classify_name, bean.getCate_name());
        final TagFlowLayout mTagView = holder.getView(R.id.tag_view);
        mTagView.setMaxSelectCount(bean.getLabel().size());
        if (!mList.contains(mTagView)) {
            mList.add(mTagView);
        }
        mTagView.setAdapter(new TagAdapter<VideoClassifyBean.LabelBean>(bean.getLabel()) {
            @Override
            public View getView(FlowLayout parent, int position, VideoClassifyBean.LabelBean bean) {
                LayoutInflater mInflater = LayoutInflater.from(mContext);
                TextView tv = (TextView) mInflater.inflate(R.layout.layout_option_tag, parent, false);
                tv.setText(bean.getName());
                tv.setTextColor(Color.parseColor("#333333"));
                tv.setBackgroundResource(R.drawable.bg_video_tag);
                return tv;
            }
        });
        mTagView.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int TagPosition, FlowLayout parent) {
                String mTag = mTagView.getSelectedList().toString().replace("[", "").replace("]", "");
                Logcat.i("选择了：" + mTag);
                if (StringUtil.isEmpty(mTag)) {
                    mMap.put(bean.getCate_name(), null);
                } else {
                    List<Integer> list = new ArrayList();
                    list.addAll(mTagView.getSelectedList());
                    for (int i = 0; i < list.size(); i++) {
                        mMap.put(bean.getCate_name(), bean.getLabel().get(list.get(i)).getName());
                    }
                }
                return true;
            }
        });
        mTagView.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                Logcat.i("selectPosSet：" + selectPosSet.toString());
            }
        });
    }

    Map<String, String> mMap = new HashMap();

    public Map<String, String> getChoiceClassify() {
        return mMap;
    }


    public List<String> getClassify() {
        List list = new ArrayList();
        for (int i = 0; i < mList.size(); i++) {
            TagFlowLayout mTagView = mList.get(i);
            List<Integer> getList = new ArrayList<>();
            getList.addAll(mTagView.getSelectedList());
            for (int j = 0; j < getList.size(); j++) {
                list.add(mBeanList.get(i).getLabel().get(j).getName());
            }
        }
        return list;
    }
}