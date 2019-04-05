package com.idyoga.yoga.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.R;
import com.idyoga.yoga.listener.OnItemClickListener;
import com.idyoga.yoga.model.TutorListBean;

import java.util.List;

import vip.devkit.view.common.suklib.view.FlowLayout.FlowLayout;
import vip.devkit.view.common.suklib.view.FlowLayout.TagAdapter;
import vip.devkit.view.common.suklib.view.FlowLayout.TagFlowLayout;

/**
 * 文 件 名: TutorListAdapter
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/5/14
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class TutorRecommendListAdapter extends DelegateAdapter.Adapter<BaseViewHolder> {

    Context mContext;
    List<TutorListBean.TutorBean> mBeanList;
    LayoutHelper mLayoutHelper;
    int mViewTypeItem;
    OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
    public TutorRecommendListAdapter(Context context, List<TutorListBean.TutorBean> beanList, LayoutHelper layoutHelper, int mViewTypeItem) {
        this.mContext = context;
        this.mBeanList = beanList;
        this.mLayoutHelper = layoutHelper;
        this.mViewTypeItem = mViewTypeItem;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == mViewTypeItem) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_tutor_list, parent, false);
            return new BaseViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        TutorListBean.TutorBean tutorBean = mBeanList.get(position);
        String mMainstream = "";
        String mLesson = "";
        for (int i = 0; i < tutorBean.getMainstream().size(); i++) {
            mMainstream = tutorBean.getMainstream().get(i) .getMainstreamName()+ "\t\t";
        }
        for (int i = 0; i < tutorBean.getLesson().size(); i++) {
            mLesson = tutorBean.getLesson().get(i).getLessonName()+ "\t\t";
        }
        holder.setText(R.id.tv_name, tutorBean.getName())
                .setText(R.id.tv_classify, "所属流派：" + mMainstream)
                .setText(R.id.tv_advantage, "擅长领域：" + mLesson);
        TagFlowLayout mTagView = holder.getView(R.id.tag_view);
        mTagView.setAdapter(new TagAdapter<TutorListBean.TutorBean.TutorLabelBean>(tutorBean.getTutorLabel()) {
            @Override
            public View getView(FlowLayout parent, int position, TutorListBean.TutorBean.TutorLabelBean labelBean) {
                LayoutInflater mInflater = LayoutInflater.from(mContext);
                TextView tv = (TextView) mInflater.inflate(R.layout.layout_option_tag, parent, false);
                tv.setText(labelBean.getName());
                tv.setTextSize(10);
                tv.setTextColor(Color.parseColor("#b86caf"));
                tv.setBackgroundResource(R.drawable.bg_tutor_list_tag);
                return tv;
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener!=null){
                    mOnItemClickListener.onItemClick(0,view,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBeanList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mViewTypeItem;
    }
}
