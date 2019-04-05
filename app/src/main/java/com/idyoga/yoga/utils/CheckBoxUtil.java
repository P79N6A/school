package com.idyoga.yoga.utils;

import android.widget.CheckBox;

import vip.devkit.library.Logcat;

/**
 * 文 件 名: CheckBoxUtil
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/3/21
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class CheckBoxUtil {
    CheckBox mCheckBox1, mCheckBox2;

    public void CheckBoxUtil() {
    }

    public void CheckBoxUtil(CheckBox checkBox1, CheckBox checkBox2) {
        this.mCheckBox1 = checkBox1;
        this.mCheckBox2 = checkBox2;
    }

    public void SwitchCheckBox(CheckBox checkBox) {
        if (mCheckBox1 == checkBox || mCheckBox2 == checkBox) {
            if (checkBox == mCheckBox1) {
                mCheckBox2.setChecked(false);
            } else if (checkBox == mCheckBox2){
                mCheckBox1.setChecked(false);
            }else {
                Logcat.e("请确认传入的CheckBox是否是初始化的View");
            }
        } else {
            Logcat.e("请初始化CheckBox");
        }
    }

    public CheckBox isChecked(){
        if (mCheckBox2.isChecked()==true){
            return mCheckBox2;
        }
        if (mCheckBox1.isChecked()==true){
            return mCheckBox1;
        }
        return null;
    }
}
