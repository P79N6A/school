package com.idyoga.yoga.model;

/**
 * 文 件 名: UserAppointmentVideoCourseBean
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/11
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：预约的视频课程
 */
public class UserAppointmentVideoCourseBean {


    /**
     * id : 23
     * user_video_id : 6
     * title : 体式入门动作基础系列二
     * time : 30
     * grade : 2
     * deplete : 30
     * description : 体式入门动作基础系列二
     * image_url : https://platform.hq-xl.com/video_image/2018/03/5ab8af8779428.jpg
     * is_free : 0
     * price : 0.00
     * number : 100
     * efficacy : 修身
     * tutor_name : null
     */

    private int id;
    private int user_video_id;
    private String title;
    private int time;
    private int grade;
    private int deplete;
    private String description;
    private String image_url;
    private int is_free;
    private String price;
    private int number;
    private String efficacy;
    private String tutor_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_video_id() {
        return user_video_id;
    }

    public void setUser_video_id(int user_video_id) {
        this.user_video_id = user_video_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getDeplete() {
        return deplete;
    }

    public void setDeplete(int deplete) {
        this.deplete = deplete;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getIs_free() {
        return is_free;
    }

    public void setIs_free(int is_free) {
        this.is_free = is_free;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getEfficacy() {
        return efficacy;
    }

    public void setEfficacy(String efficacy) {
        this.efficacy = efficacy;
    }

    public String getTutor_name() {
        return tutor_name;
    }

    public void setTutor_name(String tutor_name) {
        this.tutor_name = tutor_name;
    }
}
