package com.idyoga.yoga.model;

/**
 * @author weilgu
 * @time 2019/3/25 14:36
 * @des ${TODO}
 */

public class AboutAppointment {

    private int id;//109096,-----------------课程预约id
    private String lessonName;//"lessonName": "POPPIN",------------课程名
    private String shopName;// "shopName": "魔鬼瑜伽馆",---------------瑜伽馆名
    private long start_time;// "start_time": 1551930900,-------------课程开始时间
    private String lessonType;// "lessonType": 1--------------------课程类型（1已排课程2自选时间课程）

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public long getStart_time() {
        return start_time;
    }

    public void setStart_time(long start_time) {
        this.start_time = start_time;
    }

    public String getLessonType() {
        return lessonType;
    }

    public void setLessonType(String lessonType) {
        this.lessonType = lessonType;
    }
}
