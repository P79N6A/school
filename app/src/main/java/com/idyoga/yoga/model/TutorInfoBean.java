package com.idyoga.yoga.model;

import java.util.List;

/**
 * 文 件 名: TutorSelectBean
 * 创 建 人: By k
 * 创建日期: 2018/5/14 11:58
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注： 老师列表
 */
public class TutorInfoBean {


    private List<BannerListBean> bannerList;
    private List<TagListBean> tagList;
    private List<TutorBean> tutor;

    public List<BannerListBean> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<BannerListBean> bannerList) {
        this.bannerList = bannerList;
    }

    public List<TagListBean> getTagList() {
        return tagList;
    }

    public void setTagList(List<TagListBean> tagList) {
        this.tagList = tagList;
    }

    public List<TutorBean> getTutor() {
        return tutor;
    }

    public void setTutor(List<TutorBean> tutor) {
        this.tutor = tutor;
    }

    public static class BannerListBean {
        /**
         * id : 19
         * image : https://platform.hq-xl.com/banner_thumb/2018/06/5b1a1d3916887.jpg
         * url : www.baidu.com
         */

        private int id;
        private String image;
        private String url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class TagListBean {
        /**
         * id : 16
         * image_url : https://platform.hq-xl.com/classify_image/5afcedb76e8a9.jpg
         * name : 导师分类4
         */

        private int id;
        private String image_url;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class TutorBean {

        /**
         * id : 1063
         * name : 方老师1
         * image : http://testyogabook.hq-xl.com/shop/tutor_avatar/27401305/5b3b1cc561510.png
         * mobile : 13377766381
         * mailbox : 472847166@qq.com
         * sex : 1
         * create_time : 1528940827
         * shop_id : 27401305
         * longitude : 89.1185360
         * latitude : 43.0295760
         * lesson : [{"lessonName":"热瑜伽"},{"lessonName":"我是一节课"}]
         * mainstream : [{"mainstreamName":"智瑜伽"},{"mainstreamName":"维尼瑜伽"},{"mainstreamName":"阿奴萨拉瑜伽"}]
         * course : [{"id":32862,"is_pay":2,"start":1530792000,"end":1530793800,"lessonName":"热瑜伽"}]
         * labelList : [{"id":1,"image_url":"http://testyogabook.hq-xl.com/shop/tagImage/5afa37ccb9017.png","name":"全能导师"},{"id":1,"image_url":"http://testyogabook.hq-xl.com/shop/tagImage/5afa37ccb9017.png","name":"金牌导师"},{"id":1,"image_url":"http://testyogabook.hq-xl.com/shop/tagImage/5afa37ccb9017.png","name":"签约导师"},{"id":1,"image_url":"http://testyogabook.hq-xl.com/shop/tagImage/5afa37ccb9017.png","name":"海外导师"}]
         */

        private int id;
        private String name;
        private String image;
        private String mobile;
        private String mailbox;
        private int sex;
        private int create_time;
        private int shop_id;
        private String longitude;
        private String latitude;
        private List<LessonBean> lesson;
        private List<MainstreamBean> mainstream;
        private List<CourseBean> course;
        private List<LabelListBean> labelList;

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

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getMailbox() {
            return mailbox;
        }

        public void setMailbox(String mailbox) {
            this.mailbox = mailbox;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }

        public int getShop_id() {
            return shop_id;
        }

        public void setShop_id(int shop_id) {
            this.shop_id = shop_id;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public List<LessonBean> getLesson() {
            return lesson;
        }

        public void setLesson(List<LessonBean> lesson) {
            this.lesson = lesson;
        }

        public List<MainstreamBean> getMainstream() {
            return mainstream;
        }

        public void setMainstream(List<MainstreamBean> mainstream) {
            this.mainstream = mainstream;
        }

        public List<CourseBean> getCourse() {
            return course;
        }

        public void setCourse(List<CourseBean> course) {
            this.course = course;
        }

        public List<LabelListBean> getLabelList() {
            return labelList;
        }

        public void setLabelList(List<LabelListBean> labelList) {
            this.labelList = labelList;
        }

        public static class LessonBean {
            /**
             * lessonName : 热瑜伽
             */

            private String lessonName;

            public String getLessonName() {
                return lessonName;
            }

            public void setLessonName(String lessonName) {
                this.lessonName = lessonName;
            }
        }

        public static class MainstreamBean {
            /**
             * mainstreamName : 智瑜伽
             */

            private String mainstreamName;

            public String getMainstreamName() {
                return mainstreamName;
            }

            public void setMainstreamName(String mainstreamName) {
                this.mainstreamName = mainstreamName;
            }
        }

        public static class CourseBean {
            /**
             * id : 32862
             * is_pay : 2
             * start : 1530792000
             * end : 1530793800
             * lessonName : 热瑜伽
             */

            private int id;
            private int is_pay;
            private int start;
            private int end;
            private String lessonName;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getIs_pay() {
                return is_pay;
            }

            public void setIs_pay(int is_pay) {
                this.is_pay = is_pay;
            }

            public int getStart() {
                return start;
            }

            public void setStart(int start) {
                this.start = start;
            }

            public int getEnd() {
                return end;
            }

            public void setEnd(int end) {
                this.end = end;
            }

            public String getLessonName() {
                return lessonName;
            }

            public void setLessonName(String lessonName) {
                this.lessonName = lessonName;
            }
        }

        public static class LabelListBean {
            /**
             * id : 1
             * image_url : http://testyogabook.hq-xl.com/shop/tagImage/5afa37ccb9017.png
             * name : 全能导师
             */

            private int id;
            private String image_url;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
