package com.idyoga.yoga.model;

import com.google.gson.annotations.SerializedName;

public class ShopItemCourseBean {


    /**
     * id : 106
     * lessonName : 啊啊啊啊
     * image : null
     * time : 60
     * type : 2
     * start_time :
     * end_time :
     */

    private int id;
    private String lessonName;
    private String image;
    private int time;
    private int type;
    private String tutorName;
    private String shop_id;
    private String start_time;
    private String end_time;



    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }


}
