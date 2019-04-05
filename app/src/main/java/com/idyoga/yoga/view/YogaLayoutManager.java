package com.idyoga.yoga.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntDef;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.idyoga.yoga.R;
import com.idyoga.yoga.adapter.base.ViewHolder;
import com.idyoga.yoga.view.layoutmanager.LViewHolder;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Map;

import vip.devkit.library.Logcat;

/**
 * 文 件 名: YogaLayoutManager
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/1
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class YogaLayoutManager extends FrameLayout {
    @IntDef({EMPTY, RETRY, LOADING, ERROR, NET_ERROR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ViewType {

    }

    public static final int EMPTY = 0x00000000;
    public static final int RETRY = 0x00000001;
    public static final int LOADING = 0x00000002;
    public static final int ERROR = 0x00000003;
    public static final int NET_ERROR = 0x00000005;

    private YogaLayoutManager.OnRetryClickListener mRetryClickListener;
    private YogaLayoutManager.OnInflateListener mInflateListener;

    /**
     * 初始化Activity
     *
     * @param activity the activity
     * @return the
     */
    public static YogaLayoutManager wrap(Activity activity) {
        return wrap(((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0));
    }

    /**
     * 初始化Fragment
     *
     * @param fragment the fragment
     * @return
     */
    public static YogaLayoutManager wrap( android.support.v4.app.Fragment fragment) {
        return wrap(fragment.getView());
    }

    /**
     * 初始化Fragment
     *
     * @param fragment the fragment
     * @return
     */
    public static YogaLayoutManager wrap(android.app.Fragment fragment) {
        return wrap(fragment.getView());
    }

    /**
     * 初始化View
     *
     * @param view the view
     * @return
     */
    public static YogaLayoutManager wrap(View view) {
        if (view == null) {
            throw new RuntimeException("content view can not be null");
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (view == null) {
            throw new RuntimeException("parent view can not be null");
        }
        if (view == null) {
            throw new RuntimeException("parent view can not be null");
        }
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        int index = parent.indexOfChild(view);
        parent.removeView(view);

        YogaLayoutManager layout = new YogaLayoutManager(view.getContext());
        parent.addView(layout, index, lp);
        layout.addView(view);
        layout.setContentView(view);
        return layout;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() == 0) {
            return;
        }
        if (getChildCount() > 1) {
            removeViews(1, getChildCount() - 1);
        }
        View view = getChildAt(0);
        setContentView(view);
        showLoading();
    }

    int mEmptyImage;
    CharSequence mEmptyText;
    int mErrorImage;
    CharSequence mErrorText, mRetryText;
    int mTextColor, mTextSize;
    int mButtonTextColor, mButtonTextSize;
    Drawable mButtonBackground;
    int mEmptyResId = NO_ID, mLoadingResId = NO_ID, mErrorResId = NO_ID, mNetErrorResId = NO_ID;
    int mContentId = NO_ID;

    Map<Integer, View> mLayouts = new HashMap<>();

    public YogaLayoutManager(Context context) {
        this(context, null, R.attr.styleYogaBaseLayoutManager);
    }

    public YogaLayoutManager(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.styleYogaBaseLayoutManager);
    }

    LayoutInflater mInflater;
    public YogaLayoutManager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mInflater = LayoutInflater.from(context);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.YogaBaseLayoutManager, defStyleAttr, R.style.YogaBaseLayoutManager_Style);
        mEmptyImage = a.getResourceId(R.styleable.YogaBaseLayoutManager_llEmptyImage, NO_ID);
        mEmptyText = a.getString(R.styleable.YogaBaseLayoutManager_llEmptyText);

        mErrorImage = a.getResourceId(R.styleable.YogaBaseLayoutManager_llErrorImage, NO_ID);
        mErrorText = a.getString(R.styleable.YogaBaseLayoutManager_llErrorText);
        mRetryText = a.getString(R.styleable.YogaBaseLayoutManager_llRetryText);

        mTextColor = a.getColor(R.styleable.YogaBaseLayoutManager_llTextColor, 0xff999999);
        mTextSize = a.getDimensionPixelSize(R.styleable.YogaBaseLayoutManager_llTextSize, dp2px(16));

        mButtonTextColor = a.getColor(R.styleable.YogaBaseLayoutManager_llButtonTextColor, 0xff999999);
        mButtonTextSize = a.getDimensionPixelSize(R.styleable.YogaBaseLayoutManager_llButtonTextSize, dp2px(16));
        mButtonBackground = a.getDrawable(R.styleable.YogaBaseLayoutManager_llButtonBackground);

        mEmptyResId = a.getResourceId(R.styleable.YogaBaseLayoutManager_llEmptyResId, R.layout.layout_common_empty);
        mLoadingResId = a.getResourceId(R.styleable.YogaBaseLayoutManager_llLoadingResId, R.layout.layout_common_loading);
        mErrorResId = a.getResourceId(R.styleable.YogaBaseLayoutManager_llErrorResId, R.layout.layout_common_error);
        mNetErrorResId = a.getResourceId(R.styleable.YogaBaseLayoutManager_llNetErrorResId, R.layout.yoga_layout_net_error);
        a.recycle();
    }

    int dp2px(float dp) {
        return (int) (getResources().getDisplayMetrics().density * dp);
    }

    private void setContentView(View view) {
        mContentId = view.getId();
        mLayouts.put(mContentId, view);
    }

    public YogaLayoutManager setLoading(@LayoutRes int id) {
        if (mLoadingResId != id) {
            remove(mLoadingResId);
            mLoadingResId = id;
            mLayouts.put(mLoadingResId, layout(mLoadingResId));
        }
        return this;
    }

    public YogaLayoutManager setEmpty(@LayoutRes int id) {
        if (mEmptyResId != id) {
            remove(mEmptyResId);
            mEmptyResId = id;
            mLayouts.put(mEmptyResId, layout(mEmptyResId));
        }
        return this;
    }

    public YogaLayoutManager setError(@LayoutRes int id) {
        if (mErrorResId != id) {
            remove(mErrorResId);
            mErrorResId = id;
            mLayouts.put(mErrorResId, layout(mErrorResId));
        }
        return this;
    }

    public YogaLayoutManager setNetError(@LayoutRes int id) {
        if (mNetErrorResId != id) {
            remove(mNetErrorResId);
            mNetErrorResId = id;
            mLayouts.put(mNetErrorResId, layout(mNetErrorResId));
        }
        return this;
    }


    public void showLoading() {
        show(mLoadingResId);
    }

    public void showEmpty() {
        show(mEmptyResId);
    }

    public void showError() {
        show(mErrorResId);
    }

    public void showNetError() {
        show(mNetErrorResId);
    }

    public void showContent() {
        hide();
    }


    private void show(int layoutId) {
        for (View view : mLayouts.values()) {
            view.setVisibility(GONE);
        }
        layout(layoutId).setVisibility(VISIBLE);
    }

    private void hide() {
        for (View view : mLayouts.values()) {
            view.setVisibility(GONE);
        }
        mLayouts.get(mContentId).setVisibility(VISIBLE);
    }


    private void remove(int layoutId) {
        if (mLayouts.containsKey(layoutId)) {
            View vg = mLayouts.remove(layoutId);
            removeView(vg);
        }
    }

    private View layout(final int layoutId) {
//        Logcat.e("mLayouts 是否包含 layoutId："+mLayouts.containsKey(layoutId));
        if (mLayouts.containsKey(layoutId)) {
            return mLayouts.get(layoutId);
        }
        View layout = mInflater.inflate(layoutId, this, false);
        layout.setVisibility(GONE);
        addView(layout);
        mLayouts.put(layoutId, layout);
        if (layoutId == mLoadingResId) {
        } else if (layoutId == mEmptyResId) {
        } else if (layoutId == mErrorResId) {
        } else if (layoutId == mNetErrorResId) {
            layout.findViewById(R.id.tv_retry).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Logcat.i("点击了重试");
                    if (mRetryClickListener != null) {
                        mRetryClickListener.onRetryClick();
                    }
                }
            });
        }
        ViewGroup viewGroup = (ViewGroup) layout;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            viewGroup.getChildAt(i).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mInflateListener != null) {
                        if (layoutId == mLoadingResId) {
                            mInflateListener.onInflate(LOADING, view);
                        } else if (layoutId == mEmptyResId) {
                            mInflateListener.onInflate(EMPTY, view);
                        } else if (layoutId == mErrorResId) {
                            mInflateListener.onInflate(ERROR, view);
                        } else if (layoutId == mNetErrorResId) {
                            mInflateListener.onInflate(NET_ERROR, view);
                        }
                    }
                }
            });
        }
        return layout;
    }

    /**
     * 测量view
     *
     * @param view
     */
    public static void measureView(View view) {
        ViewGroup.LayoutParams p = view.getLayoutParams();
        if (p == null) {
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0, p.width);
        int lpHeight = p.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight, MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        }
        view.measure(childWidthSpec, childHeightSpec);
    }

    /**
     * 获取view 的宽度
     *
     * @param view
     * @return
     */
    public static int getViewWidth(View view) {
        measureView(view);
        return view.getMeasuredWidth();
    }

    /**
     * 获取View 的高度
     *
     * @param view
     * @return
     */
    public static int getViewHeight(View view) {
        measureView(view);
        return view.getMeasuredHeight();
    }

    /**
     * 监听重试
     *
     * @param listener {@link YogaLayoutManager.OnRetryClickListener}
     */
    public void setOnRetryClickListener(YogaLayoutManager.OnRetryClickListener listener) {
        this.mRetryClickListener = listener;
    }

    /**
     * Listener used to receive a notification after the RetryView is clicked.
     */
    public interface OnRetryClickListener {
        void onRetryClick();
    }


    /**
     * Specifies the inflate listener to be notified after this StateView successfully
     * inflated its layout resource.
     *
     * @param inflateListener The OnInflateListener to notify of successful inflation.
     * @see YogaLayoutManager.OnInflateListener
     */
    public void setOnInflateListener(YogaLayoutManager.OnInflateListener inflateListener) {
        this.mInflateListener = inflateListener;
    }

    /**
     * Listener used to receive a notification after a StateView has successfully
     * inflated its layout resource.
     *
     * @see YogaLayoutManager#setOnInflateListener(YogaLayoutManager.OnInflateListener)
     */
    public interface OnInflateListener {
        /**
         * @param view The inflated View.
         */
        void onInflate(@YogaLayoutManager.ViewType int viewType, View view);
    }

    /**
     * @param viewType
     * @return 返回 viewType 对应的 View
     */
    public LViewHolder initView(@YogaLayoutManager.ViewType int viewType) {
        LViewHolder holder = null;
        if (viewType == LOADING) {
            holder = new LViewHolder(getContext(), mLayouts.get(mLoadingResId));
        } else if (viewType == EMPTY) {
            holder = new LViewHolder(getContext(), mLayouts.get(mEmptyResId));
        } else if (viewType == ERROR) {
            holder = new LViewHolder(getContext(), mLayouts.get(mErrorResId));
        } else if (viewType == NET_ERROR) {
            holder = new LViewHolder(getContext(), mLayouts.get(mNetErrorResId));
        }
        return holder;
    }


}