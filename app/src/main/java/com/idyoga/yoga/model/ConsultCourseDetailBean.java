package com.idyoga.yoga.model;

/**
 * 文 件 名: ConsultCourseBean
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/26
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class ConsultCourseDetailBean {

    /**
     * id : 89
     * lessonName : 吐瑜伽
     * lessonTime : 20
     * image : http://testyogabook.hq-xl.com/static/images/lesson/5b34a5cf31776.png
     * shopName : 吐瑜伽馆
     * hotline : 123
     * address : 吐瑜伽馆地址
     * expectTime : 1530979200
     * status : 1
     * time : 0
     * type : 已过期
     */

    private int id;
    private String lessonName;
    private int lessonTime;
    private String image;
    private String shopName;
    private String hotline;
    private String address;
    private int expectTime;
    private int status;
    private int time;
    private String type;

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

    public int getLessonTime() {
        return lessonTime;
    }

    public void setLessonTime(int lessonTime) {
        this.lessonTime = lessonTime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getHotline() {
        return hotline;
    }

    public void setHotline(String hotline) {
        this.hotline = hotline;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getExpectTime() {
        return expectTime;
    }

    public void setExpectTime(int expectTime) {
        this.expectTime = expectTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
