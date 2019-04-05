package com.idyoga.yoga.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.idyoga.yoga.R;
import com.idyoga.yoga.activity.home.AppointmentIntroductionActivity;
import com.idyoga.yoga.activity.shop.ShopCourseInfoActivity;
import com.idyoga.yoga.common.modle.CircleTransform;
import com.idyoga.yoga.listener.OnItemClickListener;
import com.idyoga.yoga.model.ShopItemCourseBean;
import com.idyoga.yoga.model.ShopListBean;
import com.idyoga.yoga.model.TutorDetailBean;
import com.idyoga.yoga.model.TutorInfoBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vip.devkit.library.ListUtil;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.view.common.suklib.view.FlowLayout.FlowLayout;
import vip.devkit.view.common.suklib.view.FlowLayout.TagAdapter;
import vip.devkit.view.common.suklib.view.FlowLayout.TagFlowLayout;

public class TutorListAdapter extends BaseQuickAdapter<TutorInfoBean.TutorBean, BaseViewHolder> {

    private ShopItemCourseAdapter courseAdapter;
    private ListView listView;
    private List setList;
    private Map<Integer, Boolean> mMap = new HashMap();
    private com.idyoga.yoga.listener.OnItemClickListener mItemClickListener;

    public void setItemClickListener(com.idyoga.yoga.listener.OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public TutorListAdapter(int layoutResId, @Nullable List<TutorInfoBean.TutorBean> data) {
        super(layoutResId, data);
        for (int i = 0; i < data.size(); i++) {
            mMap.put(data.get(i).getId(), false);
        }
    }

    public void setData(@Nullable List<TutorInfoBean.TutorBean> data) {
        this.mData = data;
        Logcat.i("ShopListAdapter data:" + data.size());
        for (int i = 0; i < data.size(); i++) {
            mMap.put(data.get(i).getId(), false);
        }
        notifyDataSetChanged();
    }

    @Override
    public void setNewData(@Nullable List<TutorInfoBean.TutorBean> data) {
        super.setNewData(data);
        for (int i = 0; i < data.size(); i++) {
            mMap.put(data.get(i).getId(), false);
        }
    }

    List<BaseViewHolder> mHolderList = new ArrayList<>();

    @Override
    protected void convert(final BaseViewHolder holder, final TutorInfoBean.TutorBean bean) {
        mHolderList.add(holder);
        final List<ShopItemCourseBean> beanList = new ArrayList();
        if (!ListUtil.isEmpty(bean.getCourse())) {
            List<ShopItemCourseBean> list = new ArrayList<>();
            for (TutorInfoBean.TutorBean.CourseBean courseBean : bean.getCourse()) {
                ShopItemCourseBean itemCourseBean = new ShopItemCourseBean();
                itemCourseBean.setEnd_time(courseBean.getEnd() + "");
                itemCourseBean.setLessonName(courseBean.getLessonName());
                itemCourseBean.setStart_time(courseBean.getStart() + "");
                itemCourseBean.setId(courseBean.getId());
                list.add(itemCourseBean);
            }
            beanList.addAll(list);
        }
        Glide.with(mContext)
                .load(bean.getImage())
                .placeholder(R.drawable.img_teacher)
                .error(R.drawable.img_teacher)
                .transform(new CircleTransform(mContext))
                .into((ImageView) holder.getView(R.id.iv_img));
        StringBuffer lesson= new StringBuffer();
        StringBuffer type= new StringBuffer();
        if (ListUtil.isEmpty(bean.getLesson())) {
            lesson.append("暂未填写");
        } else {
            for (TutorInfoBean.TutorBean.LessonBean b : bean.getLesson()) {
                lesson.append(b.getLessonName()).append(" ");
            }
        }
        if (ListUtil.isEmpty(bean.getMainstream())) {
            type.append( "暂未填写");
        } else {
            for (TutorInfoBean.TutorBean.MainstreamBean b : bean.getMainstream()) {
                type.append(b.getMainstreamName()).append(" ");
            }
        }
        holder.setText(R.id.tv_name, bean.getName())
                .setText(R.id.tv_type, "所属流派:" + type + "")
                .setText(R.id.tv_excellent, "擅长领域:" + lesson + "");
        TagFlowLayout tagLayout = holder.getView(R.id.tag_view);
        tagLayout.setAdapter(new TagAdapter<TutorInfoBean.TutorBean.LabelListBean>(bean.getLabelList()) {
            @Override
            public View getView(FlowLayout parent, int position, TutorInfoBean.TutorBean.LabelListBean labelListBean) {
                LayoutInflater mInflater = LayoutInflater.from(mContext);
                TextView tv = (TextView) mInflater.inflate(R.layout.layout_option_tag, parent, false);
                tv.setText(labelListBean.getName());
                tv.setTextSize(10f);
                tv.setPadding(0,0,0,0);
                tv.setTextColor(Color.parseColor("#b86caf"));
                tv.setBackgroundResource(R.drawable.bg_shop_01);
                return tv;
            }
        });


        TextView textView = holder.getView(R.id.tv_footer_view);
        if (ListUtil.isEmpty(bean.getCourse())) {
            textView.setVisibility(View.GONE);
            textView.setText("暂未排课");
        } else {
            if (bean.getCourse().size() > 2) {
                textView.setVisibility(View.VISIBLE);
                textView.setText("查看更多");
            } else {
                textView.setVisibility(View.GONE);
            }
        }
        listView = holder.getView(R.id.lv_list);
        setList = new ArrayList();

        courseAdapter = new ShopItemCourseAdapter(mContext, beanList, R.layout.item_experience_shop_course, 700);
        listView.setAdapter(courseAdapter);
        courseAdapter.notifyDataSetChanged();
        if (null != mMap.get(bean.getId()) && mMap.get(bean.getId())) {
            holder.setText(R.id.tv_footer_view, "关闭更多显示");
            Logcat.i("关闭更多显示");
            if (beanList.size() > 5) {
                setList.clear();
                for (int i = 0; i < 5; i++) {
                    setList.add(beanList.get(i));
                }
                courseAdapter.setBeanList(setList);
                courseAdapter.notifyDataSetChanged();
            } else {
                courseAdapter.setBeanList(beanList);
                courseAdapter.notifyDataSetChanged();
            }
        } else {
            if (beanList.size() > 2) {
                setList.clear();
                for (int i = 0; i < 2; i++) {
                    setList.add(beanList.get(i));
                }
                courseAdapter = new ShopItemCourseAdapter(mContext, setList, R.layout.item_experience_shop_course, 700);
                listView.setAdapter(courseAdapter);
                courseAdapter.notifyDataSetChanged();
            } else {
                courseAdapter.setBeanList(beanList);
                courseAdapter.notifyDataSetChanged();
            }
        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView getV = (TextView) view;
                switch (getV.getText().toString()) {
                    case "查看更多":
                        Logcat.i("查看更多");
                        mMap.put(bean.getId(), true);
                        holder.setText(R.id.tv_footer_view, "关闭更多显示");
                        break;
                    case "关闭更多显示":
                        Logcat.i("关闭更多显示");
                        mMap.put(bean.getId(), false);
                        holder.setText(R.id.tv_footer_view, "查看更多");
                        break;
                    case "暂未排课":
                        Logcat.i("暂未排课");
                        holder.setText(R.id.tv_footer_view, "暂未排课");
                        break;
                }
                notifyItemChanged(holder.getAdapterPosition());
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String shopId = (String) SharedPreferencesUtils.getSP(mContext, "shopId", "");
                Intent intent = new Intent(mContext, AppointmentIntroductionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("shopId", bean.getShop_id() + "");
                bundle.putString("courseId", beanList.get(i).getId() + "");
                bundle.putString("lessonId", beanList.get(i).getId()+ "");
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
        if (mItemClickListener!=null){
            mItemClickListener.onItemClick(0,holder.itemView,holder.getLayoutPosition());
        }
    }

}
