package com.idyoga.yoga.model;

import java.util.List;

public class TutorDetailBean {

    /**
     * id : 1063
     * name : 方老师1
     * mobile : 13377766381
     * mailbox : 472847166@qq.com
     * sex : 1
     * introduce : 这是老师的介绍123222
     * image : http://testyogabook.hq-xl.com/shop/tutor_avatar/27401305/5b3b1cc561510.png
     * labelList : [{"id":1,"image_url":"http://testyogabook.hq-xl.com/shop/tagImage/5afa37ccb9017.png","name":"全能导师"},{"id":1,"image_url":"http://testyogabook.hq-xl.com/shop/tagImage/5afa37ccb9017.png","name":"金牌导师"},{"id":1,"image_url":"http://testyogabook.hq-xl.com/shop/tagImage/5afa37ccb9017.png","name":"签约导师"},{"id":1,"image_url":"http://testyogabook.hq-xl.com/shop/tagImage/5afa37ccb9017.png","name":"海外导师"}]
     * lesson : [{"id":1228,"lesson_id":135,"lessonName":"热瑜伽"},{"id":1229,"lesson_id":4140,"lessonName":"我是一节课"}]
     * mainstream : [{"id":1557,"mainstream_id":9,"mainstreamName":"智瑜伽"},{"id":1558,"mainstream_id":21,"mainstreamName":"维尼瑜伽"},{"id":1559,"mainstream_id":23,"mainstreamName":"阿奴萨拉瑜伽"}]
     * course : [{"id":32862,"is_pay":2,"price":"1.00","start":1530792000,"end":1530793800,"number":10,"tutorName":"方老师1","lessonName":"热瑜伽","lessonImg":"http://testyogabook.hq-xl.com/shop/lesson_cover/27401305/5b3b4676e83a8.png","shopName":"科韵路瑜伽馆"}]
     * video : []
     * shop : {"id":27401305,"name":"科韵路瑜伽馆","address":"科韵路123","logopath":"http://testyogabook.hq-xl.com/static/images/logo/shop_1819298255.png","hotline":"100123","merid":0,"payinfos_id":33,"mobile":"13300000005","is_for_platform":0,"chain_id":131,"longitude":"89.1185360","latitude":"43.0295760","province":39,"city":72,"area":345,"introduce":null,"is_verify":0,"shop_code":"48076931","secret_key":"DrSf0G1hdnqZzcHb6MQP8pLosvImOFRy","uniacid":null,"renren_shop_name":null,"is_sex":0,"is_sign":0,"shopCommentCount":0}
     */

    private int id;
    private String name;
    private String mobile;
    private String mailbox;
    private int sex;
    private String introduce;
    private String image;
    private ShopBean shop;
    private List<LabelListBean> labelList;
    private List<LessonBean> lesson;
    private List<MainstreamBean> mainstream;
    private List<CourseBean> course;
    private List<VideoBean> video;

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

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ShopBean getShop() {
        return shop;
    }

    public void setShop(ShopBean shop) {
        this.shop = shop;
    }

    public List<LabelListBean> getLabelList() {
        return labelList;
    }

    public void setLabelList(List<LabelListBean> labelList) {
        this.labelList = labelList;
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

    public List<VideoBean> getVideo() {
        return video;
    }

    public void setVideo(List<VideoBean> video) {
        this.video = video;
    }

    public static class ShopBean {
        /**
         * id : 27401305
         * name : 科韵路瑜伽馆
         * address : 科韵路123
         * logopath : http://testyogabook.hq-xl.com/static/images/logo/shop_1819298255.png
         * hotline : 100123
         * merid : 0
         * payinfos_id : 33
         * mobile : 13300000005
         * is_for_platform : 0
         * chain_id : 131
         * longitude : 89.1185360
         * latitude : 43.0295760
         * province : 39
         * city : 72
         * area : 345
         * introduce : null
         * is_verify : 0
         * shop_code : 48076931
         * secret_key : DrSf0G1hdnqZzcHb6MQP8pLosvImOFRy
         * uniacid : null
         * renren_shop_name : null
         * is_sex : 0
         * is_sign : 0
         * shopCommentCount : 0
         */

        private int id;
        private String name;
        private String address;
        private String logopath;
        private String hotline;
        private int merid;
        private int payinfos_id;
        private String mobile;
        private int is_for_platform;
        private int chain_id;
        private String longitude;
        private String latitude;
        private int province;
        private int city;
        private int area;
        private Object introduce;
        private int is_verify;
        private String shop_code;
        private String secret_key;
        private Object uniacid;
        private Object renren_shop_name;
        private int is_sex;
        private int is_sign;
        private int shopCommentCount;

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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLogopath() {
            return logopath;
        }

        public void setLogopath(String logopath) {
            this.logopath = logopath;
        }

        public String getHotline() {
            return hotline;
        }

        public void setHotline(String hotline) {
            this.hotline = hotline;
        }

        public int getMerid() {
            return merid;
        }

        public void setMerid(int merid) {
            this.merid = merid;
        }

        public int getPayinfos_id() {
            return payinfos_id;
        }

        public void setPayinfos_id(int payinfos_id) {
            this.payinfos_id = payinfos_id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getIs_for_platform() {
            return is_for_platform;
        }

        public void setIs_for_platform(int is_for_platform) {
            this.is_for_platform = is_for_platform;
        }

        public int getChain_id() {
            return chain_id;
        }

        public void setChain_id(int chain_id) {
            this.chain_id = chain_id;
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

        public int getProvince() {
            return province;
        }

        public void setProvince(int province) {
            this.province = province;
        }

        public int getCity() {
            return city;
        }

        public void setCity(int city) {
            this.city = city;
        }

        public int getArea() {
            return area;
        }

        public void setArea(int area) {
            this.area = area;
        }

        public Object getIntroduce() {
            return introduce;
        }

        public void setIntroduce(Object introduce) {
            this.introduce = introduce;
        }

        public int getIs_verify() {
            return is_verify;
        }

        public void setIs_verify(int is_verify) {
            this.is_verify = is_verify;
        }

        public String getShop_code() {
            return shop_code;
        }

        public void setShop_code(String shop_code) {
            this.shop_code = shop_code;
        }

        public String getSecret_key() {
            return secret_key;
        }

        public void setSecret_key(String secret_key) {
            this.secret_key = secret_key;
        }

        public Object getUniacid() {
            return uniacid;
        }

        public void setUniacid(Object uniacid) {
            this.uniacid = uniacid;
        }

        public Object getRenren_shop_name() {
            return renren_shop_name;
        }

        public void setRenren_shop_name(Object renren_shop_name) {
            this.renren_shop_name = renren_shop_name;
        }

        public int getIs_sex() {
            return is_sex;
        }

        public void setIs_sex(int is_sex) {
            this.is_sex = is_sex;
        }

        public int getIs_sign() {
            return is_sign;
        }

        public void setIs_sign(int is_sign) {
            this.is_sign = is_sign;
        }

        public int getShopCommentCount() {
            return shopCommentCount;
        }

        public void setShopCommentCount(int shopCommentCount) {
            this.shopCommentCount = shopCommentCount;
        }

        @Override
        public String toString() {
            return "ShopBean{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    ", logopath='" + logopath + '\'' +
                    ", hotline='" + hotline + '\'' +
                    ", merid=" + merid +
                    ", payinfos_id=" + payinfos_id +
                    ", mobile='" + mobile + '\'' +
                    ", is_for_platform=" + is_for_platform +
                    ", chain_id=" + chain_id +
                    ", longitude='" + longitude + '\'' +
                    ", latitude='" + latitude + '\'' +
                    ", province=" + province +
                    ", city=" + city +
                    ", area=" + area +
                    ", introduce=" + introduce +
                    ", is_verify=" + is_verify +
                    ", shop_code='" + shop_code + '\'' +
                    ", secret_key='" + secret_key + '\'' +
                    ", uniacid=" + uniacid +
                    ", renren_shop_name=" + renren_shop_name +
                    ", is_sex=" + is_sex +
                    ", is_sign=" + is_sign +
                    ", shopCommentCount=" + shopCommentCount +
                    '}';
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

    public static class LessonBean {
        /**
         * id : 1228
         * lesson_id : 135
         * lessonName : 热瑜伽
         */

        private int id;
        private int lesson_id;
        private String lessonName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLesson_id() {
            return lesson_id;
        }

        public void setLesson_id(int lesson_id) {
            this.lesson_id = lesson_id;
        }

        public String getLessonName() {
            return lessonName;
        }

        public void setLessonName(String lessonName) {
            this.lessonName = lessonName;
        }
    }

    public static class MainstreamBean {
        /**
         * id : 1557
         * mainstream_id : 9
         * mainstreamName : 智瑜伽
         */

        private int id;
        private int mainstream_id;
        private String mainstreamName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMainstream_id() {
            return mainstream_id;
        }

        public void setMainstream_id(int mainstream_id) {
            this.mainstream_id = mainstream_id;
        }

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
         * price : 1.00
         * start : 1530792000
         * end : 1530793800
         * number : 10
         * tutorName : 方老师1
         * lessonName : 热瑜伽
         * lessonImg : http://testyogabook.hq-xl.com/shop/lesson_cover/27401305/5b3b4676e83a8.png
         * shopName : 科韵路瑜伽馆
         */

        private int id;
        private int is_pay;
        private String price;
        private int start;
        private int end;
        private int number;
        private String tutorName;
        private String lessonName;
        private String lessonImg;
        private String shopName;

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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
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

        public String getLessonName() {
            return lessonName;
        }

        public void setLessonName(String lessonName) {
            this.lessonName = lessonName;
        }

        public String getLessonImg() {
            return lessonImg;
        }

        public void setLessonImg(String lessonImg) {
            this.lessonImg = lessonImg;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }
    }

    public static class VideoBean {
        /**
         * id : 22
         * title : 体式入门动作基础系列一
         * time : 30
         * grade : 2
         * deplete : 30
         * description : 体式入门动作基础系列一
         * image_url : https://platform.hq-xl.com/video_image/2018/03/5ab8af5b5d042.jpg
         * is_free : 0
         * price : 0.00
         * number : 100
         * efficacy : 修身
         * tutor : 林大师
         * videoLabel : [{"name":"限时免费"},{"name":"海外导师"},{"name":"强身健体"}]
         */

        private int id;
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
        private String tutor;
        private List<VideoBean.VideoLabelBean> videoLabel;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getTutor() {
            return tutor;
        }

        public void setTutor(String tutor) {
            this.tutor = tutor;
        }

        public List<VideoBean.VideoLabelBean> getVideoLabel() {
            return videoLabel;
        }

        public void setVideoLabel(List<VideoBean.VideoLabelBean> videoLabel) {
            this.videoLabel = videoLabel;
        }

        public static class VideoLabelBean {
            /**
             * name : 限时免费
             */

            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }

}
