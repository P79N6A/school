package com.idyoga.yoga.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.idyoga.yoga.R;
import com.idyoga.yoga.holder.OrtherShopInfoHolder;
import com.idyoga.yoga.holder.ShopCourseItemHolder;
import com.idyoga.yoga.holder.ShopInfoHolder;
import com.idyoga.yoga.holder.StartOrEndHolder;
import com.idyoga.yoga.holder.TeacherHolder;
import com.idyoga.yoga.model.Aaaaa;
import com.idyoga.yoga.model.LessonListBean;
import com.idyoga.yoga.model.NearbyShopListBean;
import com.idyoga.yoga.model.ShopDetailDataBean;
import com.idyoga.yoga.model.TutorListBeans;

import java.util.ArrayList;
import java.util.List;

/**
 * @author weilgu
 * @time 2019/3/4 14:49
 * @des ${TODO}
 */

public class ShopDetailAdapter extends RecyclerView.Adapter{

    private Context mContext;
    //private List<Object> mDatas;
    private ShopDetailDataBean mData;
    private List<Object> mDataList = new ArrayList<>();
    private final LayoutInflater mFrom;
    private static final int SHOW_SHOP_INFO = 0;           //店铺信息
    private static final int SHOW_SHOP_COURSE_LIST = 1;    //课程
    private static final int SHOW_SHOP_ROB_INFO = 2;       //秒杀
    private static final int SHOW_SHOP_GROUP_INFO = 3;     //团购
    private static final int SHOW_SHOP_TEACHER = 4;        //店铺老师
    private static final int SHOW_ORTHER_SHOP_INFO = 5;    //其他店铺
    private static final int SHOW_START_OR_END = 6;        //到底了
    private int mCount = 1;
    private boolean isShowMore = false;

    public ShopDetailAdapter(Context context) {
        mContext = context;
        mFrom = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == SHOW_SHOP_INFO){
            return new ShopInfoHolder(mFrom.inflate(R.layout.item_shop_title_layout,parent,false),mContext);
        }else if(viewType == SHOW_SHOP_COURSE_LIST){
            return new ShopCourseItemHolder(mFrom.inflate(R.layout.item_shop_show_more_data,parent,false),mContext);
        }/*else if(viewType == SHOW_SHOP_ROB_INFO){
            return new ShowMoreDataHolder(mFrom.inflate(R.layout.item_shop_show_more_data,parent,false),mContext);
        }else if(viewType == SHOW_SHOP_GROUP_INFO){
            return new ShowMoreDataHolder(mFrom.inflate(R.layout.item_shop_show_more_data,parent,false),mContext);
        }*/else if(viewType == SHOW_SHOP_TEACHER){
            return new TeacherHolder(mFrom.inflate(R.layout.item_teacher_list,parent,false),mContext);
        }else if(viewType == SHOW_ORTHER_SHOP_INFO){
            return new OrtherShopInfoHolder(mFrom.inflate(R.layout.item_orther_shop_info_layout,parent,false),mContext);
        }else if(viewType == SHOW_START_OR_END){
            return new StartOrEndHolder(mFrom.inflate(R.layout.item_list_start_or_end,parent,false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == SHOW_SHOP_INFO){
            ((ShopInfoHolder)holder).bindView(mData);
        }else if(viewType == SHOW_SHOP_COURSE_LIST){
            List<LessonListBean> listBeans = (List<LessonListBean>) mDataList.get(position);
            ((ShopCourseItemHolder)holder).bindView(listBeans);
        }/*else if(viewType == SHOW_SHOP_ROB_INFO){

        }else if(viewType == SHOW_SHOP_GROUP_INFO){

        }*/else if(viewType == SHOW_SHOP_TEACHER){
            List<TutorListBeans> tutorListBeans = (List<TutorListBeans>) mDataList.get(position);
            ((TeacherHolder)holder).bindView(tutorListBeans);
        }else if(viewType == SHOW_ORTHER_SHOP_INFO){
            List<NearbyShopListBean> nearbyShopListBeans = (List<NearbyShopListBean>) mDataList.get(position);
            ((OrtherShopInfoHolder)holder).bindView(nearbyShopListBeans);
        }else if(viewType == SHOW_START_OR_END){
            Aaaaa aaaaa = (Aaaaa) mDataList.get(position);
            ((StartOrEndHolder)holder).bindView(aaaaa.getShopName());
        }
    }

    @Override
    public int getItemViewType(int position) {
        Object obj = mDataList.get(position);
        if (obj instanceof ShopDetailDataBean){
            return SHOW_SHOP_INFO;
        }else if (obj instanceof List){
            ArrayList<Object> objs = (ArrayList<Object>) obj;
            if (objs.size() >= 1){
                Object object = objs.get(0);
                if (object instanceof LessonListBean){ //店铺课程
                    return SHOW_SHOP_COURSE_LIST;
                }else if (object instanceof TutorListBeans){ //店铺老师列表
                    return SHOW_SHOP_TEACHER;
                }else if (object instanceof NearbyShopListBean){ //附近店铺
                    return SHOW_ORTHER_SHOP_INFO;
                }/*else if (obj instanceof ){ //秒杀
                    return SHOW_SHOP_ROB_INFO;
                }*/
            }
        }else if(obj instanceof Aaaaa){
            return SHOW_START_OR_END;
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        return mDataList != null ? mDataList.size() : 0;
    }

    public void setData(ShopDetailDataBean data){
        this.mData = data;
        mDataList.clear();
        mCount = 1;
        mDataList.add(data);
        List<LessonListBean> lessonList = data.getLessonList();
        if (lessonList != null && !lessonList.isEmpty()){
            mCount += 1;
            mDataList.add(lessonList);
        }
        List<?> seckillList = data.getSeckillList();
        if (seckillList != null && !seckillList.isEmpty()){
            mCount += 1;
            mDataList.add(seckillList);
        }
        List<?> spellteamList = data.getSpellteamList();
        if (spellteamList != null && !spellteamList.isEmpty()){
            mCount += 1;
            mDataList.add(spellteamList);
        }
        List<TutorListBeans> tutorList = data.getTutorList();
        if (tutorList != null && !tutorList.isEmpty()){
            mCount += 1;
            mDataList.add(tutorList);
        }

        List<NearbyShopListBean> nearbyShopList = data.getNearbyShopList();
        if (seckillList == null || spellteamList == null || lessonList == null || tutorList == null && nearbyShopList != null){
            Aaaaa aaaaa1 = new Aaaaa("附近的瑜伽馆");
            mDataList.add(aaaaa1);
            if (nearbyShopList != null && !nearbyShopList.isEmpty()){
                mCount += 1;
                mDataList.add(nearbyShopList);
            }
            Aaaaa aaaaa = new Aaaaa("到底了");
            mDataList.add(aaaaa);
        }
        notifyDataSetChanged();
    }

    public void setShowMore(boolean showMore) {
        isShowMore = showMore;
    }
}
