package com.idyoga.yoga.model;

/**
 * 文 件 名: AppointmentDateBean
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/26
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class AppointmentDateBean {
    private int year;
    private int month;
    private int date;
    private boolean isSelect ;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int yaer) {
        this.year = yaer;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public AppointmentDateBean() {
    }

    public AppointmentDateBean(int year, int month, int date) {
        this.year = year;
        this.month = month;
        this.date = date;
    }

    public AppointmentDateBean(int year, int month, int date, boolean isSelect) {
        this.year = year;
        this.month = month;
        this.date = date;
        this.isSelect = isSelect;
    }

    @Override
    public String toString() {
        return "AppointmentDateBean{" +
                "year=" + year +
                ", month=" + month +
                ", date=" + date +
                '}';
    }
}
