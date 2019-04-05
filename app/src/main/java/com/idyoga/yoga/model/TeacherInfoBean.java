package com.idyoga.yoga.model;

import java.io.PipedReader;
import java.util.List;

/**
 * @author weilgu
 * @time 2019/3/22 19:39
 * @des ${TODO}
 */

public class TeacherInfoBean {


    /**
     * tutorImage : []
     * tutorDetail : {"tutor_id":2521,"image":"http://testyogabook.hq-xl.com/static/images/tutor/5c862572ae23f.jpg","name":"林某","experience":"","lessonName":null,"working_years":"2019","introduce":"很厉害"}
     * tutorCourse : []
     */

    private TutorDetailBean tutorDetail;
    private List<TeacherIma> tutorImage;
    private List<TeacherCourse> tutorCourse;

    public TutorDetailBean getTutorDetail() {
        return tutorDetail;
    }

    public void setTutorDetail(TutorDetailBean tutorDetail) {
        this.tutorDetail = tutorDetail;
    }

    public List<TeacherIma> getTutorImage() {
        return tutorImage;
    }

    public void setTutorImage(List<TeacherIma> tutorImage) {
        this.tutorImage = tutorImage;
    }

    public List<TeacherCourse> getTutorCourse() {
        return tutorCourse;
    }

    public void setTutorCourse(List<TeacherCourse> tutorCourse) {
        this.tutorCourse = tutorCourse;
    }

    public static class TutorDetailBean {
        /**
         * tutor_id : 2521
         * image : http://testyogabook.hq-xl.com/static/images/tutor/5c862572ae23f.jpg
         * name : 林某
         * experience :
         * lessonName : null
         * working_years : 2019
         * introduce : 很厉害
         */

        private int tutor_id;
        private String image;
        private String name;
        private String experience;
        private Object lessonName;
        private String working_years;
        private String introduce;

        public int getTutor_id() {
            return tutor_id;
        }

        public void setTutor_id(int tutor_id) {
            this.tutor_id = tutor_id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getExperience() {
            return experience;
        }

        public void setExperience(String experience) {
            this.experience = experience;
        }

        public Object getLessonName() {
            return lessonName;
        }

        public void setLessonName(Object lessonName) {
            this.lessonName = lessonName;
        }

        public String getWorking_years() {
            return working_years;
        }

        public void setWorking_years(String working_years) {
            this.working_years = working_years;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }
    }

    public static class TeacherIma{
        private String image_url;

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }
    }

    public static class TeacherCourse{
        /*"course_id": 83544,--------------课程ID
                "name": "林大师蛇形瑜伽课",----------课程名
                "image": "https://admin.idyoga.cn/static/images/lesson/5ae2956e5cb82.jpg",--课程图片
                "start_time": 1547100000,----课程开始时间
                "end_time": 1547101800,------课程结束时间
                "number": 10,------------课程可预约人数
                "appointmentNum": 1------------课程已预约人数*/
        private int course_id;
        private String name;
        private String image;
        private long start_time;
        private long end_time;
        private int number;
        private int appointmentNum;

        public int getCourse_id() {
            return course_id;
        }

        public void setCourse_id(int course_id) {
            this.course_id = course_id;
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

        public int getAppointmentNum() {
            return appointmentNum;
        }

        public void setAppointmentNum(int appointmentNum) {
            this.appointmentNum = appointmentNum;
        }
    }
}
