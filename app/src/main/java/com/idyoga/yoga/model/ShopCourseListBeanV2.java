package com.idyoga.yoga.model;

import java.util.List;
/**
 * 文 件 名: ShopCourseListBeanV2
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/21
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class ShopCourseListBeanV2 {

    /**
     * id : 27401305
     * name : 科韵路瑜伽馆
     * logopath : http://testyogabook.hq-xl.com/static/images/logo/shop_1819298255.png
     * address : 科韵路123
     * compare : 9947.03km
     * url : http://wxyoga.hq-xl.com/studio/detail?shopId=27401305&lat=0&long=0
     * shopCommentCount : 0
     * course : [{"id":33434,"shop_id":74120289,"start_time":1531306800,"end_time":1531308600,"lessonName":"吐鲁番瑜伽","image":"http://testyogabook.hq-xl.com/static/images/lesson/5b1b872b69d44.png","tutorName":"西老师","type":1}]
     */

    private int id;
    private String name;
    private String logopath;
    private String address;
    private String compare;
    private String url;
    private int shopCommentCount;
    private List<CourseBean> course;

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

    public String getCompare() {
        return compare;
    }

    public void setCompare(String compare) {
        this.compare = compare;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getShopCommentCount() {
        return shopCommentCount;
    }

    public void setShopCommentCount(int shopCommentCount) {
        this.shopCommentCount = shopCommentCount;
    }

    public List<CourseBean> getCourse() {
        return course;
    }

    public void setCourse(List<CourseBean> course) {
        this.course = course;
    }

    public static class CourseBean {
        /**
         * id : 33434
         * shop_id : 74120289
         * start_time : 1531306800
         * end_time : 1531308600
         * lessonName : 吐鲁番瑜伽
         * image : http://testyogabook.hq-xl.com/static/images/lesson/5b1b872b69d44.png
         * tutorName : 西老师
         * type : 1
         * time : 90
         */
        private int id;
        private int time;
        private int shop_id;
        private int start_time;
        private int end_time;
        private String lessonName;
        private String image;
        private String tutorName;
        private int type;


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

        public String getTutorName() {
            return tutorName;
        }

        public void setTutorName(String tutorName) {
            this.tutorName = tutorName;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }
    }
}
