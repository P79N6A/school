package com.idyoga.yoga.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.course.ClassifyChoiceActivity;
import com.idyoga.yoga.common.adapter.CommonAdapter;
import com.idyoga.yoga.common.adapter.ViewHolder;
import com.idyoga.yoga.utils.ToastUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vip.devkit.library.Logcat;
import vip.devkit.library.StringUtil;
import vip.devkit.view.common.suklib.view.FlowLayout.FlowLayout;
import vip.devkit.view.common.suklib.view.FlowLayout.TagAdapter;
import vip.devkit.view.common.suklib.view.FlowLayout.TagFlowLayout;

/**
 * 文 件 名: SearchAdapter
 * 创 建 人: By k
 * 创建日期: 2018/5/11 16:41
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注：
 */
public class ClassifyChoiceAdapter extends CommonAdapter<ClassifyChoiceActivity.tagBean> {

    private Context mContext;
    List<ClassifyChoiceActivity.tagBean> mBeanList;

    public ClassifyChoiceAdapter(Context context, List<ClassifyChoiceActivity.tagBean> mBeanList, int layoutId) {
        super(context, mBeanList, layoutId);
        this.mContext = context;
        this.mBeanList = mBeanList;
    }

    @Override
    public void convert(ViewHolder holder, final ClassifyChoiceActivity.tagBean tagBean, final int position) {
        holder.setText(R.id.tv_title, tagBean.getTagTitle());
        String tagTitle = tagBean.getTagTitle();
        TextView textView = holder.getView(R.id.tv_clean);
        if (!StringUtil.isEmpty(tagTitle) && tagTitle.equals("历史搜索")) {
            textView.setVisibility(View.VISIBLE);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToastUtil.showToast("清除历史搜索");
                    mBeanList.remove(position);
                    notifyDataSetChanged();
                }
            });
        } else {
            textView.setVisibility(View.GONE);
        }
        final TagFlowLayout mTagView = holder.getView(R.id.tag_view);
        mMap.put(tagBean.getTagTitle(),"");
        mTagView.setAdapter(new TagAdapter<String>(tagBean.getList()) {
            @Override
            public View getView(FlowLayout parent, int position, String tag) {
                LayoutInflater mInflater = LayoutInflater.from(mContext);
                TextView tv = (TextView) mInflater.inflate(R.layout.option_item, parent, false);
                tv.setText(tag);
                tv.setTextColor(Color.parseColor("#333333"));
//                tv.setBackgroundResource(R.drawable.bg_serach_tag);
                return tv;
            }
        });
        mTagView.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int TagPosition, FlowLayout parent) {
                String mTag = mTagView.getSelectedList().toString().replace("[", "").replace("]", "");
                Logcat.i("选择了：" + mTag);
                if (StringUtil.isEmpty(mTag)){
                    mMap.put(tagBean.getTagTitle(),"");
                }else {
                    mMap.put(tagBean.getTagTitle(),tagBean.getList().get(Integer.valueOf(mTag)));
                }
                return true;
            }
        });
    }
    Map<String,String> mMap = new HashMap();
    public  Map<String,String> getChoiceClassify() {
        return mMap;
    }

}