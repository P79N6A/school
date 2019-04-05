package com.idyoga.yoga.model;

/**
 * @author weilgu
 * @time 2019/3/18 17:02
 * @des ${TODO}
 */

public class LessonListBean {

    /**
     * id : 6698
     * name : Urban
     * image : http://testyogabook.hq-xl.com/static/images/lesson/5bfb9ba1bce22.jpg
     * appointmentNum : 0
     * isCourse : 1
     */

    private int id;
    private String name;
    private String image;
    private int appointmentNum;
    private int isCourse;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getAppointmentNum() {
        return appointmentNum;
    }

    public void setAppointmentNum(int appointmentNum) {
        this.appointmentNum = appointmentNum;
    }

    public int getIsCourse() {
        return isCourse;
    }

    public void setIsCourse(int isCourse) {
        this.isCourse = isCourse;
    }
}
