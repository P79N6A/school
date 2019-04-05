package com.idyoga.yoga.model;

import java.util.List;

/**
 * 文 件 名: ShopMarketCourseInfoBean
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/22
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class ShopMarketCourseInfoBean {


    /**
     * id : 4219
     * lessonName : 小小奇葩课_111
     * lessonImage : http://testyogabook.hq-xl.com/shop/lesson_cover/19287727/5b2cc3392be1d.jpg
     * time : 40
     * lessonIntroduce : 小小奇葩课_111
     * shopName : 现联瑜伽馆
     * address : 天河
     * compare: 1.0
     * shopIntroduce : null
     * url : https://platform.hq-xl.com/mall/lesson/lessonDetail?shopId=19287727&lessonId=4219
     * courseTime : [{"id":29022,"lessonId":4219,"start":"2018-06-30 17:00","end":"2018-06-30 17:40","start_time":1530349200,"end_time":1530351600,"number":30,"tutorName":"林大师,方老师","lessonImage":"http://testyogabook.hq-xl.com/shop/lesson_cover/19287727/5b2cc3392be1d.jpg","lessonName":"小小奇葩课_111","classroomName":"4-102"}]
     */

    private int id;
    private String lessonName;
    private String lessonImage;
    private int time;
    private double compare;
    private String lessonIntroduce;
    private String shopName;
    private String address;
    private Object shopIntroduce;
    private String url;
    private List<CourseTimeBean> courseTime;

    public double getCompare() {
        return compare;
    }

    public void setCompare(double compare) {
        this.compare = compare;
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

    public String getLessonImage() {
        return lessonImage;
    }

    public void setLessonImage(String lessonImage) {
        this.lessonImage = lessonImage;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getLessonIntroduce() {
        return lessonIntroduce;
    }

    public void setLessonIntroduce(String lessonIntroduce) {
        this.lessonIntroduce = lessonIntroduce;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Object getShopIntroduce() {
        return shopIntroduce;
    }

    public void setShopIntroduce(Object shopIntroduce) {
        this.shopIntroduce = shopIntroduce;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<CourseTimeBean> getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(List<CourseTimeBean> courseTime) {
        this.courseTime = courseTime;
    }

    public static class CourseTimeBean {
        /**
         * id : 29022
         * lessonId : 4219
         * start : 2018-06-30 17:00
         * end : 2018-06-30 17:40
         * start_time : 1530349200
         * end_time : 1530351600
         * number : 30
         * tutorName : 林大师,方老师
         * lessonImage : http://testyogabook.hq-xl.com/shop/lesson_cover/19287727/5b2cc3392be1d.jpg
         * lessonName : 小小奇葩课_111
         * classroomName : 4-102
         */

        private int id;
        private int lessonId;
        private int shop_id;
        private String start;
        private String end;
        private int start_time;
        private int end_time;
        private int number;
        private String tutorName;
        private String lessonImage;
        private String lessonName;
        private String classroomName;

        public int getId() {
            return id;
        }

        public int getShop_id() {
            return shop_id;
        }

        public void setShop_id(int shop_id) {
            this.shop_id = shop_id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLessonId() {
            return lessonId;
        }

        public void setLessonId(int lessonId) {
            this.lessonId = lessonId;
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

        public String getLessonImage() {
            return lessonImage;
        }

        public void setLessonImage(String lessonImage) {
            this.lessonImage = lessonImage;
        }

        public String getLessonName() {
            return lessonName;
        }

        public void setLessonName(String lessonName) {
            this.lessonName = lessonName;
        }

        public String getClassroomName() {
            return classroomName;
        }

        public void setClassroomName(String classroomName) {
            this.classroomName = classroomName;
        }
    }
}
