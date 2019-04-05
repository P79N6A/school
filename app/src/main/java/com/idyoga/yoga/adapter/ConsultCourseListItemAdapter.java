package com.idyoga.yoga.adapter;

        import android.content.Context;
        import android.graphics.Color;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.bumptech.glide.Glide;
        import com.idyoga.yoga.R;
        import com.idyoga.yoga.common.adapter.CommonAdapter;
        import com.idyoga.yoga.common.adapter.ViewHolder;
        import com.idyoga.yoga.fragment.child.FragmentConsult;
        import com.idyoga.yoga.model.ConsultCourseBean;
        import com.idyoga.yoga.model.UserCourseBean;
        import com.idyoga.yoga.utils.CommonUtils;

        import java.util.List;

        import vip.devkit.library.Logcat;

/**
 * 文 件 名: UserCourseAdapter
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/3/23
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class ConsultCourseListItemAdapter extends CommonAdapter<ConsultCourseBean> {

    String tag;

    public ConsultCourseListItemAdapter(Context context, List<ConsultCourseBean> mBeanList, int layoutId, String tag) {
        super(context, mBeanList, layoutId);
        this.tag = tag;
    }

    @Override
    public void convert(ViewHolder holder, ConsultCourseBean bean, int i) {
        Logcat.i("bean:" + bean.toString() + "/" + tag);
        Glide.with(mContext)
                .load(bean.getLessonImage())
                .placeholder(R.drawable.img_course)
                .error(R.drawable.img_course)
                .into((ImageView) holder.getView(R.id.iv_course_item_img));
        holder.setText(R.id.tv_course_name, bean.getLessonName())
                .setText(R.id.tv_shop_name, "瑜伽馆：" + bean.getShopName())
                .setText(R.id.tv_state, bean.getType())
                .setText(R.id.tv_time, "申请时间:" + CommonUtils.getDateStringByTimeSTamp((long) bean.getExpectTime(), "yyyy年MM月dd日 HH:mm:ss"));
        TextView textView = holder.getView(R.id.tv_state);
        if (tag.equals("1")) {
            textView.setTextColor(mContext.getResources().getColor(R.color.theme_1));
        } else if (tag.equals("3")) {
            textView.setTextColor(mContext.getResources().getColor(R.color.text_color_d));
        } else {
            holder.setText(R.id.tv_time, "上课时间：" + CommonUtils.getDateStringByTimeSTamp((long) bean.getTime(), "HH:mm"));
            textView.setTextColor(mContext.getResources().getColor(R.color.text_color_r));
        }
    }
}
