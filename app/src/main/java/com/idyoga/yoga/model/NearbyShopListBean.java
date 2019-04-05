package com.idyoga.yoga.model;

/**
 * @author weilgu
 * @time 2019/3/18 17:05
 * @des ${TODO}
 */

public class NearbyShopListBean {

    /**
     * id : 87388202
     * name : 亚玛瑜伽(俊园馆)
     * address : 中山大道枫叶路3号珠江俊园11商铺(大嘴渔港斜对面)
     * logopath : http://testyogabook.hq-xl.com/static/images/logo/shop_1428762062.png
     * courese_start_time : null
     * compare : 1.01
     * lessonImage : http://testyogabook.hq-xl.com/static/images/lesson/5bfcfd7fbc54c.jpg
     * shopImage : null
     * studentImage : null
     */

    private int id;
    private String name;
    private String address;
    private String logopath;
    private Object courese_start_time;
    private String compare;
    private String lessonImage;
    private String shopImage;
    private String studentImage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogopath() {
        return logopath;
    }

    public void setLogopath(String logopath) {
        this.logopath = logopath;
    }

    public Object getCourese_start_time() {
        return courese_start_time;
    }

    public void setCourese_start_time(Object courese_start_time) {
        this.courese_start_time = courese_start_time;
    }

    public String getCompare() {
        return compare;
    }

    public void setCompare(String compare) {
        this.compare = compare;
    }

    public String getLessonImage() {
        return lessonImage;
    }

    public void setLessonImage(String lessonImage) {
        this.lessonImage = lessonImage;
    }

    public String getShopImage() {
        return shopImage;
    }

    public void setShopImage(String shopImage) {
        this.shopImage = shopImage;
    }

    public String getStudentImage() {
        return studentImage;
    }

    public void setStudentImage(String studentImage) {
        this.studentImage = studentImage;
    }
}
