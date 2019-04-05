package com.idyoga.yoga.model;

import java.util.List;

/**
 * 文 件 名: ExperienceCourseClassBean
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/5/19
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class ExperienceCourseClassBean {

    private List<ShopBean> shop;
    private List<CourseListBean> courseList;

    public List<ShopBean> getShop() {
        return shop;
    }

    public void setShop(List<ShopBean> shop) {
        this.shop = shop;
    }

    public List<CourseListBean> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<CourseListBean> courseList) {
        this.courseList = courseList;
    }

    public static class ShopBean {
        /**
         * id : 87109608
         * name : 致舞者艺术工作室
         * address : 黄埔大沙地东城国际广场2201室(近摩登百货)
         * logopath :
         * hotline : 13512771715
         * merid : 0
         * mobile : 13512771715
         * sms_number : 2000
         * is_for_platform : 0
         * chain_id : null
         * longitude : 113.4829336
         * latitude : 23.1659457
         * province : 1
         * city : 17
         * area : 122
         * shopCommentCount : 0
         * course : [{"id":24504,"start":"2018-05-31 16:00","end":"2018-05-31 16:30","start_time":1527753600,"end_time":1527755400,"number":30,"tutorName":"林大大","lessonImage":"http://testyogabook.hq-xl.com/static/images/lesson/5b0265c47b4d6.jpg","lessonName":"林大大课程","classroomName":"林大大教室"}]
         * compare : 0
         */

        private int id;
        private String name;
        private String address;
        private String logopath;
        private String hotline;
        private int merid;
        private String mobile;
        private int sms_number;
        private int is_for_platform;
        private Object chain_id;
        private String longitude;
        private String latitude;
        private int province;
        private int city;
        private int area;
        private int shopCommentCount;
        private int compare;
        private String url;
        private List<CourseBean> course;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

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

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getSms_number() {
            return sms_number;
        }

        public void setSms_number(int sms_number) {
            this.sms_number = sms_number;
        }

        public int getIs_for_platform() {
            return is_for_platform;
        }

        public void setIs_for_platform(int is_for_platform) {
            this.is_for_platform = is_for_platform;
        }

        public Object getChain_id() {
            return chain_id;
        }

        public void setChain_id(Object chain_id) {
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

        public int getShopCommentCount() {
            return shopCommentCount;
        }

        public void setShopCommentCount(int shopCommentCount) {
            this.shopCommentCount = shopCommentCount;
        }

        public int getCompare() {
            return compare;
        }

        public void setCompare(int compare) {
            this.compare = compare;
        }

        public List<CourseBean> getCourse() {
            return course;
        }

        public void setCourse(List<CourseBean> course) {
            this.course = course;
        }

        public static class CourseBean {
            /**
             * id : 24504
             * start : 2018-05-31 16:00
             * end : 2018-05-31 16:30
             * start_time : 1527753600
             * end_time : 1527755400
             * number : 30
             * tutorName : 林大大
             * lessonImage : http://testyogabook.hq-xl.com/static/images/lesson/5b0265c47b4d6.jpg
             * lessonName : 林大大课程
             * classroomName : 林大大教室
             */

            private int id;
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

            public void setId(int id) {
                this.id = id;
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

    public static class CourseListBean {
        /**
         * id : 23248
         * is_pay : 2
         * price : 0.01
         * start : 1527728400
         * end : 1527730200
         * number : 30
         * tutorName : 林某,林大师
         * tagName : 减肥
         * lessonName : 林大师蛇形瑜伽课
         * lessonImg : https://admin.idyoga.cn/static/images/lesson/5ae2956e5cb82.jpg
         * introduce :
         * classroomName : 现联
         * allAppNum : 0
         * shopName : Lily Yoga 瑜伽中心(南山店)
         * shopAddress : 南山区大冲城市花园4b32b（高新园地铁站B出口）
         * longitude : 113.9623610
         * latitude : 22.5486376
         */

        private int id;
        private int is_pay;
        private String price;
        private int start;
        private int end;
        private int number;
        private String tutorName;
        private String tagName;
        private String lessonName;
        private String lessonImg;
        private String introduce;
        private String classroomName;
        private int allAppNum;
        private String shopName;
        private String shopAddress;
        private String longitude;
        private String latitude;

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

        public String getLessonImg() {
            return lessonImg;
        }

        public void setLessonImg(String lessonImg) {
            this.lessonImg = lessonImg;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getClassroomName() {
            return classroomName;
        }

        public void setClassroomName(String classroomName) {
            this.classroomName = classroomName;
        }

        public int getAllAppNum() {
            return allAppNum;
        }

        public void setAllAppNum(int allAppNum) {
            this.allAppNum = allAppNum;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public String getShopAddress() {
            return shopAddress;
        }

        public void setShopAddress(String shopAddress) {
            this.shopAddress = shopAddress;
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
    }
}