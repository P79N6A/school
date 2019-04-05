package com.idyoga.yoga.model;

/**
 * @author weilgu
 * @time 2019/3/19 16:08
 * @des ${TODO}
 */

public class EquityCourseShopListBean {


    /**
     * shop_id : 25
     * name : 印尼瑜伽馆
     * logopath : https://admin.idyoga.cn/static/images/logo/shop_base.png
     * address : 印尼
     * is_verify : 0
     * sumCourse : null
     * compare : 12401.46
     * lessonImage : https://admin.idyoga.cn/static/images/lesson/5c0f78942817e.jpg
     * shopImage : null
     * studentMienImage : http://testyogabook.hq-xl.com/static/images/student_mien_image/5c862ad21fed1.png
     */

    private int shop_id;
    private String name;
    private String logopath;
    private String address;
    private int is_verify;
    private int sumCourse;
    private String compare;
    private String lessonImage;
    private String shopImage;
    private String studentMienImage;

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogopath() {
        return logopath;
    }

    public void setLogopath(String logopath) {
        this.logopath = logopath;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIs_verify() {
        return is_verify;
    }

    public void setIs_verify(int is_verify) {
        this.is_verify = is_verify;
    }

    public int getSumCourse() {
        return sumCourse;
    }

    public void setSumCourse(int sumCourse) {
        this.sumCourse = sumCourse;
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

    public String getStudentMienImage() {
        return studentMienImage;
    }

    public void setStudentMienImage(String studentMienImage) {
        this.studentMienImage = studentMienImage;
    }
}
