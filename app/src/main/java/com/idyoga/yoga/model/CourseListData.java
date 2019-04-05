package com.idyoga.yoga.model;

/**
 * Created by Administrator on 2019/03/24.
 *
 */

public class CourseListData extends BaseCourseBean{

    private int course_id;
    private String lessonName;
    private String image;
    private long start_time;
    private long end_time;
    private int number;
    private String tutorName;
    private int appointmentNum;
    private boolean isSelect;
    private int residueNumber;

    public int getResidueNumber() {
        return residueNumber;
    }

    public void setResidueNumber(int residueNumber) {
        this.residueNumber = residueNumber;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
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

    public long getStart_time() {
        return start_time;
    }

    public void setStart_time(long start_time) {
        this.start_time = start_time;
    }

    public long getEnd_time() {
        return end_time;
    }

    public void setEnd_time(long end_time) {
        this.end_time = end_time;
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

    public int getAppointmentNum() {
        return appointmentNum;
    }

    public void setAppointmentNum(int appointmentNum) {
        this.appointmentNum = appointmentNum;
    }
}
