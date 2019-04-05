package com.idyoga.yoga.model;

import java.util.List;

/**
 * Created by domain on 2018-03-21.
 * Email:sunlongyue@foxmail.com
 * describe:
 */

public class ExpricenClassDetailsBean {

    /**
     * code : 1
     * msg : 操作成功
     * time : 1521619603
     * data : {"userInfo":[],"courseAppList":[],"courseDetail":{"id":3455,"shop_id":63684083,"parent_shop_id":45140653,"lesson_id":371,"start_time":1521630000,"end_time":1521633600,"classroom_id":200,"number":10,"create_time":1521105235,"update_time":1521105235,"is_deleted":0,"cancel_use_time":0,"course_color":null,"limit_number":0,"limit_cencel_time":0,"is_pay":2,"price":"1000.00","is_recommend":0,"start":1521630000,"end":1521633600,"lessonName":"胃痛减缓瑜伽","lessonId":371,"lessonTime":60,"lessonImg":"","introduce":"脾胃系列","lessonContent":"","classroomName":"小班课室","classroomId":200,"allAppNum":0,"appFromYueKeNum":0,"appFromQuanYiNum":0,"tutorList":[{"id":243,"name":"刘老师"}],"isExpired":0,"quanYiIsFull":1,"quanYiCanAppNum":0,"yueKeCanAppNum":10,"hasBook":"0"},"shopInfo":{"id":63684083,"name":"广州","address":"123","logopath":"","hotline":"123","merid":0,"payinfos_id":33,"mobile":"13500000000","is_for_platform":0,"chain_id":214,"longitude":null,"latitude":null},"parentShopInfo":{"id":45140653,"name":"梵天优珈瑜伽(珠江新城店)","address":"珠江新城金穗路72号盈嘉花园3层(5号线猎德地铁站)","logopath":"","hotline":"13200000007","merid":0,"payinfos_id":0,"mobile":"13200000007","is_for_platform":0,"chain_id":null,"longitude":"113.3388899","latitude":"23.1244134"}}
     */

    private String code;
    private String msg;
    private String time;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * userInfo : []
         * courseAppList : []
         * courseDetail : {"id":3455,"shop_id":63684083,"parent_shop_id":45140653,"lesson_id":371,"start_time":1521630000,"end_time":1521633600,"classroom_id":200,"number":10,"create_time":1521105235,"update_time":1521105235,"is_deleted":0,"cancel_use_time":0,"course_color":null,"limit_number":0,"limit_cencel_time":0,"is_pay":2,"price":"1000.00","is_recommend":0,"start":1521630000,"end":1521633600,"lessonName":"胃痛减缓瑜伽","lessonId":371,"lessonTime":60,"lessonImg":"","introduce":"脾胃系列","lessonContent":"","classroomName":"小班课室","classroomId":200,"allAppNum":0,"appFromYueKeNum":0,"appFromQuanYiNum":0,"tutorList":[{"id":243,"name":"刘老师"}],"isExpired":0,"quanYiIsFull":1,"quanYiCanAppNum":0,"yueKeCanAppNum":10,"hasBook":"0"}
         * shopInfo : {"id":63684083,"name":"广州","address":"123","logopath":"","hotline":"123","merid":0,"payinfos_id":33,"mobile":"13500000000","is_for_platform":0,"chain_id":214,"longitude":null,"latitude":null}
         * parentShopInfo : {"id":45140653,"name":"梵天优珈瑜伽(珠江新城店)","address":"珠江新城金穗路72号盈嘉花园3层(5号线猎德地铁站)","logopath":"","hotline":"13200000007","merid":0,"payinfos_id":0,"mobile":"13200000007","is_for_platform":0,"chain_id":null,"longitude":"113.3388899","latitude":"23.1244134"}
         */

        private CourseDetailBean courseDetail;
        private ShopInfoBean shopInfo;
        private ParentShopInfoBean parentShopInfo;
        private List<?> userInfo;
        private List<?> courseAppList;

        public CourseDetailBean getCourseDetail() {
            return courseDetail;
        }

        public void setCourseDetail(CourseDetailBean courseDetail) {
            this.courseDetail = courseDetail;
        }

        public ShopInfoBean getShopInfo() {
            return shopInfo;
        }

        public void setShopInfo(ShopInfoBean shopInfo) {
            this.shopInfo = shopInfo;
        }

        public ParentShopInfoBean getParentShopInfo() {
            return parentShopInfo;
        }

        public void setParentShopInfo(ParentShopInfoBean parentShopInfo) {
            this.parentShopInfo = parentShopInfo;
        }

        public List<?> getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(List<?> userInfo) {
            this.userInfo = userInfo;
        }

        public List<?> getCourseAppList() {
            return courseAppList;
        }

        public void setCourseAppList(List<?> courseAppList) {
            this.courseAppList = courseAppList;
        }

        public static class CourseDetailBean {
            /**
             * id : 3455
             * shop_id : 63684083
             * parent_shop_id : 45140653
             * lesson_id : 371
             * start_time : 1521630000
             * end_time : 1521633600
             * classroom_id : 200
             * number : 10
             * create_time : 1521105235
             * update_time : 1521105235
             * is_deleted : 0
             * cancel_use_time : 0
             * course_color : null
             * limit_number : 0
             * limit_cencel_time : 0
             * is_pay : 2
             * price : 1000.00
             * is_recommend : 0
             * start : 1521630000
             * end : 1521633600
             * lessonName : 胃痛减缓瑜伽
             * lessonId : 371
             * lessonTime : 60
             * lessonImg :
             * introduce : 脾胃系列
             * lessonContent :
             * classroomName : 小班课室
             * classroomId : 200
             * allAppNum : 0
             * appFromYueKeNum : 0
             * appFromQuanYiNum : 0
             * tutorList : [{"id":243,"name":"刘老师"}]
             * isExpired : 0
             * quanYiIsFull : 1
             * quanYiCanAppNum : 0
             * yueKeCanAppNum : 10
             * hasBook : 0
             */

            private int id;
            private int shop_id;
            private int parent_shop_id;
            private int lesson_id;
            private int start_time;
            private int end_time;
            private int classroom_id;
            private int number;
            private int create_time;
            private int update_time;
            private int is_deleted;
            private int cancel_use_time;
            private Object course_color;
            private int limit_number;
            private int limit_cencel_time;
            private int is_pay;
            private String price;
            private int is_recommend;
            private int start;
            private int end;
            private String lessonName;
            private int lessonId;
            private int lessonTime;
            private String lessonImg;
            private String introduce;
            private String lessonContent;
            private String classroomName;
            private int classroomId;
            private int allAppNum;
            private int appFromYueKeNum;
            private int appFromQuanYiNum;
            private int isExpired;
            private int quanYiIsFull;
            private int quanYiCanAppNum;
            private int yueKeCanAppNum;
            private String hasBook;
            private List<TutorListBean> tutorList;

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

            public int getParent_shop_id() {
                return parent_shop_id;
            }

            public void setParent_shop_id(int parent_shop_id) {
                this.parent_shop_id = parent_shop_id;
            }

            public int getLesson_id() {
                return lesson_id;
            }

            public void setLesson_id(int lesson_id) {
                this.lesson_id = lesson_id;
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

            public int getClassroom_id() {
                return classroom_id;
            }

            public void setClassroom_id(int classroom_id) {
                this.classroom_id = classroom_id;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public int getCreate_time() {
                return create_time;
            }

            public void setCreate_time(int create_time) {
                this.create_time = create_time;
            }

            public int getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(int update_time) {
                this.update_time = update_time;
            }

            public int getIs_deleted() {
                return is_deleted;
            }

            public void setIs_deleted(int is_deleted) {
                this.is_deleted = is_deleted;
            }

            public int getCancel_use_time() {
                return cancel_use_time;
            }

            public void setCancel_use_time(int cancel_use_time) {
                this.cancel_use_time = cancel_use_time;
            }

            public Object getCourse_color() {
                return course_color;
            }

            public void setCourse_color(Object course_color) {
                this.course_color = course_color;
            }

            public int getLimit_number() {
                return limit_number;
            }

            public void setLimit_number(int limit_number) {
                this.limit_number = limit_number;
            }

            public int getLimit_cencel_time() {
                return limit_cencel_time;
            }

            public void setLimit_cencel_time(int limit_cencel_time) {
                this.limit_cencel_time = limit_cencel_time;
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

            public int getIs_recommend() {
                return is_recommend;
            }

            public void setIs_recommend(int is_recommend) {
                this.is_recommend = is_recommend;
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

            public int getLessonId() {
                return lessonId;
            }

            public void setLessonId(int lessonId) {
                this.lessonId = lessonId;
            }

            public int getLessonTime() {
                return lessonTime;
            }

            public void setLessonTime(int lessonTime) {
                this.lessonTime = lessonTime;
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

            public String getLessonContent() {
                return lessonContent;
            }

            public void setLessonContent(String lessonContent) {
                this.lessonContent = lessonContent;
            }

            public String getClassroomName() {
                return classroomName;
            }

            public void setClassroomName(String classroomName) {
                this.classroomName = classroomName;
            }

            public int getClassroomId() {
                return classroomId;
            }

            public void setClassroomId(int classroomId) {
                this.classroomId = classroomId;
            }

            public int getAllAppNum() {
                return allAppNum;
            }

            public void setAllAppNum(int allAppNum) {
                this.allAppNum = allAppNum;
            }

            public int getAppFromYueKeNum() {
                return appFromYueKeNum;
            }

            public void setAppFromYueKeNum(int appFromYueKeNum) {
                this.appFromYueKeNum = appFromYueKeNum;
            }

            public int getAppFromQuanYiNum() {
                return appFromQuanYiNum;
            }

            public void setAppFromQuanYiNum(int appFromQuanYiNum) {
                this.appFromQuanYiNum = appFromQuanYiNum;
            }

            public int getIsExpired() {
                return isExpired;
            }

            public void setIsExpired(int isExpired) {
                this.isExpired = isExpired;
            }

            public int getQuanYiIsFull() {
                return quanYiIsFull;
            }

            public void setQuanYiIsFull(int quanYiIsFull) {
                this.quanYiIsFull = quanYiIsFull;
            }

            public int getQuanYiCanAppNum() {
                return quanYiCanAppNum;
            }

            public void setQuanYiCanAppNum(int quanYiCanAppNum) {
                this.quanYiCanAppNum = quanYiCanAppNum;
            }

            public int getYueKeCanAppNum() {
                return yueKeCanAppNum;
            }

            public void setYueKeCanAppNum(int yueKeCanAppNum) {
                this.yueKeCanAppNum = yueKeCanAppNum;
            }

            public String getHasBook() {
                return hasBook;
            }

            public void setHasBook(String hasBook) {
                this.hasBook = hasBook;
            }

            public List<TutorListBean> getTutorList() {
                return tutorList;
            }

            public void setTutorList(List<TutorListBean> tutorList) {
                this.tutorList = tutorList;
            }

            public static class TutorListBean {
                /**
                 * id : 243
                 * name : 刘老师
                 */

                private int id;
                private String name;

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
            }
        }

        public static class ShopInfoBean {
            /**
             * id : 63684083
             * name : 广州
             * address : 123
             * logopath :
             * hotline : 123
             * merid : 0
             * payinfos_id : 33
             * mobile : 13500000000
             * is_for_platform : 0
             * chain_id : 214
             * longitude : null
             * latitude : null
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
            private Object longitude;
            private Object latitude;

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

            public Object getLongitude() {
                return longitude;
            }

            public void setLongitude(Object longitude) {
                this.longitude = longitude;
            }

            public Object getLatitude() {
                return latitude;
            }

            public void setLatitude(Object latitude) {
                this.latitude = latitude;
            }
        }

        public static class ParentShopInfoBean {
            /**
             * id : 45140653
             * name : 梵天优珈瑜伽(珠江新城店)
             * address : 珠江新城金穗路72号盈嘉花园3层(5号线猎德地铁站)
             * logopath :
             * hotline : 13200000007
             * merid : 0
             * payinfos_id : 0
             * mobile : 13200000007
             * is_for_platform : 0
             * chain_id : null
             * longitude : 113.3388899
             * latitude : 23.1244134
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
            private Object chain_id;
            private String longitude;
            private String latitude;

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
        }
    }
}
