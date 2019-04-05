package com.idyoga.yoga.view;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;


import com.idyoga.yoga.R;
import com.idyoga.yoga.adapter.YogaContextMenuAdapter;
import com.idyoga.yoga.model.YogaMenuBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 文 件 名: YogaContextMenu
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/4/27
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class YogaContextMenu {

    private Context  mContext;
    private List<YogaMenuBean> itemList;
    private PopupWindow popupWindow;
    private View contentView;
    private ListView mLvMenuList;
    private YogaContextMenuAdapter menuAdapter;
    private OnItemSelectListener onItemSelectListener;
    private static final int DEFAULT_HEIGHT =  ListView.LayoutParams.WRAP_CONTENT;
    private int popHeight = DEFAULT_HEIGHT;
    private int popWidth = ListView.LayoutParams.WRAP_CONTENT;
    private boolean showIcon = true;
    private boolean dimBackground = true;
    private boolean needAnimationStyle = true;

    private static final int DEFAULT_ANIM_STYLE = R.style.YogaContextMenu;
    private int animationStyle=DEFAULT_ANIM_STYLE;

    private float alpha = 0.5f;

    public interface OnItemSelectListener{
        void onItemSelect(int position);
    }

    public YogaContextMenu setOnItemSelectListener(OnItemSelectListener onItemSelectListener){
        this.onItemSelectListener = onItemSelectListener;
        return this;
    }

    public YogaContextMenu(Context  mContext){
        this.mContext = mContext;
        itemList = new ArrayList<>();
        initPopWindow();
    }

    /**
     * 初始化popwindow菜单
     */
    private void initPopWindow(){
        contentView = LayoutInflater.from(mContext).inflate(R.layout.dialog_context_menu, null);
        popupWindow = new PopupWindow(contentView);
        popupWindow.setHeight(popHeight);
        popupWindow.setWidth(popWidth);

        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        if (needAnimationStyle){
            popupWindow.setAnimationStyle(animationStyle <= 0 ? DEFAULT_ANIM_STYLE : animationStyle);
        }
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (dimBackground) {
                    setBackgroundAlpha(alpha, 1f, 300);
                }
            }
        });
        mLvMenuList = contentView.findViewById(R.id.lv_menu);
        menuAdapter = new YogaContextMenuAdapter(mContext,itemList,R.layout.item_context_menu);
        mLvMenuList.setAdapter(menuAdapter);
        mLvMenuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (onItemSelectListener != null){
                    onItemSelectListener.onItemSelect(position);
                }
                if (dimBackground) {
                    setBackgroundAlpha(alpha, 1f, 300);
                }
                popupWindow.dismiss();
            }
        });
    }

    private void initPw() {
        final Activity activity = (Activity) mContext;
        //设置半透明
        WindowManager.LayoutParams lp=  activity.getWindow().getAttributes();
        lp.alpha = 0.4f;
        activity.getWindow().setAttributes(lp);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.update();
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            //在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams lp =  activity. getWindow().getAttributes();
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                lp.alpha = 1f;
                activity.getWindow().setAttributes(lp);
            }
        });
    }

    public YogaContextMenu showMenu(View anchor){
        showMenu(anchor, 0, 0);
        return this;
    }

    public YogaContextMenu showMenu(View anchor, int xoff, int yoff){
        if (popupWindow == null){
            initPopWindow();
        }
        if (!popupWindow.isShowing()) {
            popupWindow.showAsDropDown(anchor, xoff, yoff);
            if (dimBackground){
                setBackgroundAlpha(1f, alpha, 240);
            }
        }
        return this;
    }

    private void setBackgroundAlpha(float from, float to, int duration) {
        final Activity activity = (Activity) mContext;
        final WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        ValueAnimator animator = ValueAnimator.ofFloat(from, to);
        animator.setDuration(duration);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                lp.alpha = (float) animation.getAnimatedValue();
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                activity.getWindow().setAttributes(lp);
            }
        });
        animator.start();
    }


    public void dismiss(){
        if (popupWindow != null && popupWindow.isShowing()){
            popupWindow.dismiss();
        }
    }

    public YogaContextMenu setHeight(int height){
        if (height <= 0 && height != ListView.LayoutParams.MATCH_PARENT
                && height != ListView.LayoutParams.WRAP_CONTENT){
            this.popHeight = DEFAULT_HEIGHT;
        }else {
            this.popHeight = height;
        }
        return this;
    }

    public YogaContextMenu setWidth(int width){
        if (width <= 0 && width != ListView.LayoutParams.MATCH_PARENT){
            this.popWidth = ListView.LayoutParams.WRAP_CONTENT;
        }else {
            this.popWidth = width;
        }
        return this;
    }

    /**
     * 添加单个菜单
     * @param item
     * @return
     */
    public YogaContextMenu addMenuItem(YogaMenuBean item){
        itemList.add(item);
        return this;
    }

    /**
     * 添加多个菜单
     * @param list
     * @return
     */
    public YogaContextMenu addMenuList(List<YogaMenuBean> list){
        itemList.addAll(list);
        return this;
    }

    /**
     * 是否让背景变暗
     * @param b
     * @return
     */
    public YogaContextMenu dimBackground(boolean b){
        this.dimBackground = b;
        return this;
    }

    /**
     * 否是需要动画
     * @param need
     * @return
     */
    public YogaContextMenu needAnimationStyle(boolean need){
        this.needAnimationStyle = need;
        return this;
    }

    /**
     * 设置动画
     * @param style
     * @return
     */
    public YogaContextMenu setAnimationStyle(int style){
        this.animationStyle = style;
        return this;
    }

}
