package com.idyoga.yoga.fragment.child;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.idyoga.yoga.base.BaseFragment;

/**
 * 文 件 名: VipCradFragment
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/3/22
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class VipCradFragment extends BaseFragment {


    
    public static Fragment getInstance(Bundle bundle) {
        FragmentContent fragment = new FragmentContent();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int setLayoutId() {
        return 0;
    }
}
