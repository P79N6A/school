package com.idyoga.yoga.model;

/**
 * 文 件 名: ShopOfflineCourseListBean
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/5/21
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class ShopOfflineCourseListBean {


    /**
     * id : 24453
     * price : 0.01
     * start : 2018-05-23 14:00
     * end : 2018-05-23 14:30
     * number : 30
     * start_time : 1527055200
     * end_time : 1527057000
     * tutorName : 林某,林大师,方老师
     * lessonName : 测试课程
     * lessonImage : http://testyogabook.hq-xl.com/static/images/lesson/5a71653bb44c0.png
     * classroomName : 测试教室
     */

    private int id;
    private String price;
    private String start;
    private String end;
    private int number;
    private int start_time;
    private int end_time;
    private String tutorName;
    private String lessonName;
    private String lessonImage;
    private String classroomName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
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
