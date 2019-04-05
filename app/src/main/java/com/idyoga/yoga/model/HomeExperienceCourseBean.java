package com.idyoga.yoga.model;

/**
 * 文 件 名: HomeExperienceBean
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/4
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class HomeExperienceCourseBean {


    /**
     * id : 25638
     * shop_id : 63684083
     * start_time : 1546128000
     * end_time : 1546131600
     * start : 2018-12-30 08:00
     * end : 2018-12-30 09:00
     * number : 10
     * tutorName : 张老师
     * tagName : 肝胆系列
     * lessonName : 养肝减脂
     * lessonImage : http://testyogabook.hq-xl.com/static/images/lesson/5ad9a86e0c174.jpg
     * classroomName : 大班课室
     */

    private int id;
    private int shop_id;
    private int start_time;
    private int end_time;
    private String start;
    private String end;
    private int number;
    private String tutorName;
    private String tagName;
    private String lessonName;
    private String lessonImage;
    private String classroomName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public int getStart_time() {
        return start_time;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public int getEnd_time() {
        return end_time;
    }

    public void setEnd_time(int end_time) {
        this.end_time = end_time;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getLessonImage() {
        return lessonImage;
    }

    public void setLessonImage(String lessonImage) {
        this.lessonImage = lessonImage;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }
}
