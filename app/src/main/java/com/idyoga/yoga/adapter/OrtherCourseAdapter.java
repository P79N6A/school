package com.idyoga.yoga.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.idyoga.yoga.R;
import com.idyoga.yoga.model.CoureseDetailInfo;

import org.w3c.dom.Text;

import java.util.List;

/**
 * @author weilgu
 * @time 2019/3/13 17:01
 * @des ${TODO}
 */

public class OrtherCourseAdapter extends BaseAdapter{ //RecyclerView.Adapter<OrtherCourseAdapter.ViewHolder> {

    private Context mContext;
    private final LayoutInflater mFrom;
    private List<CoureseDetailInfo.LessonList> mDatas;

    public OrtherCourseAdapter(Context context){
        this.mContext = context;
        mFrom = LayoutInflater.from(mContext);
    }

   /* @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mFrom.inflate(R.layout.item_shop_show_more_data,parent,false));
    */

    @Override
    public int getCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = mFrom.inflate(R.layout.item_in_shop_course_list,parent,false);
            holder.mIvShopImage = convertView.findViewById(R.id.iv_shop_image);
            holder.mTvCourseName = convertView.findViewById(R.id.tv_course_name);
            holder.mTvCourseTag1 = convertView.findViewById(R.id.tv_course_tag1);
            holder.mTvCourseTag1.setVisibility(View.GONE);
            holder.mTvCourseTag2 = convertView.findViewById(R.id.tv_course_tag2);
            holder.mTvOrderNum = convertView.findViewById(R.id.tv_order_num);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        CoureseDetailInfo.LessonList lessonList = mDatas.get(position);
        String image = lessonList.getImage();
        holder.mTvCourseName.setText(lessonList.getName() + "");
        holder.mTvOrderNum.setText(lessonList.getAppointmentNum() != null ? lessonList.getAppointmentNum().toString() : "");
        Glide.with(mContext)
                .load(image)
                .placeholder(R.drawable.img_course)
                .error(R.drawable.img_course)
                .into(holder.mIvShopImage);
        return convertView;
    }

    public void setData(List<CoureseDetailInfo.LessonList> lessonLists){
        this.mDatas = lessonLists;
        notifyDataSetChanged();
    }

    class ViewHolder { //extends RecyclerView.ViewHolder {

        private ImageView mIvShopImage;
        private TextView mTvCourseName;
        private TextView mTvCourseTag1;
        private TextView mTvCourseTag2;
        private TextView mTvOrderNum;

        /*public ViewHolder(View itemView) {
            super(itemView);
            mType = itemView.findViewById(R.id.tv_type);
            mTable = itemView.findViewById(R.id.tv_table);
            mCourse = itemView.findViewById(R.id.rlv_course);
            mMore = itemView.findViewById(R.id.tv_more);
            mType.setText("该馆其他课程");
            mTable.setVisibility(View.GONE);
            mMore.setVisibility(View.GONE);
        }*/
    }
}
