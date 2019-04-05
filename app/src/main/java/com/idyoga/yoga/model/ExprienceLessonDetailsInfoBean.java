package com.idyoga.yoga.model;

import java.util.List;

/**
 * Created by domain on 2018-03-23.
 * Email:sunlongyue@foxmail.com
 * describe:
 */

public class ExprienceLessonDetailsInfoBean {

    /**
     * code : 1
     * msg : 操作成功
     * time : 1522036742
     * data : {"courseAppList":[{"id":1574,"shop_id":94983847,"membership_id":"690","course_id":4768,"status":0,"user_id":55,"sign_staff_id":null,"is_deleted":0,"create_time":1521625888,"update_time":1521625888,"sign_time":null,"channel_id":0,"channel_membership_id":null,"payment_type":0,"money":"0.00","is_closed":0,"course_order_id":null,"mobile":"13377766381","username":"麦锦辉","avatar_url":"http://testyogabook.hq-xl.com/static/images/appuser_image/5ab85a4864ab3.png","headpicUrl":"http://thirdwx.qlogo.cn/mmopen/vi_32/jfCUvp4oicoqSY8DLUTT7jLTGqPFAEcVxZf9zVicicWkNXCcCAQytaEmdZibSpgU2dwkhvLxriaoc3wPGqMCcHcKqXA/132","nickname":"Aj23"},{"id":1669,"shop_id":94983847,"membership_id":"682","course_id":4768,"status":0,"user_id":10641,"sign_staff_id":null,"is_deleted":0,"create_time":1521771660,"update_time":1521771660,"sign_time":null,"channel_id":0,"channel_membership_id":null,"payment_type":0,"money":"0.00","is_closed":0,"course_order_id":null,"mobile":"15906557682","username":"楼","avatar_url":null,"headpicUrl":null,"nickname":null}],"courseDetail":{"id":4768,"shop_id":94983847,"parent_shop_id":1,"lesson_id":346,"start_time":1521806400,"end_time":1521808200,"classroom_id":196,"number":123,"create_time":1521624411,"update_time":1521625365,"is_deleted":0,"cancel_use_time":1,"course_color":null,"limit_number":0,"limit_cencel_time":0,"is_pay":2,"price":"0.01","is_recommend":1,"start":1521806400,"end":1521808200,"lessonName":"热瑜伽","lessonId":346,"lessonTime":30,"lessonImg":"http://testyogabook.hq-xl.com/static/images/lesson/5aa9e38d0322c.jpg","introduce":"测试热瑜伽","lessonContent":"","classroomName":"1班","classroomId":196,"allAppNum":2,"appFromYueKeNum":2,"appFromQuanYiNum":0,"tutorList":[{"id":232,"name":"张老师"}],"isExpired":1,"quanYiIsFull":1,"quanYiCanAppNum":0,"yueKeCanAppNum":121,"hasBook":"0"},"shopInfo":{"id":94983847,"name":"北京","address":"北京","logopath":"http://testyogabook.hq-xl.com/static/images/logo/shop_1833836431.png","hotline":"11111","merid":0,"payinfos_id":33,"mobile":"18800000000","is_for_platform":0,"chain_id":215,"longitude":"0.0000000","latitude":"0.0000000","province":1,"city":17,"area":4},"parentShopInfo":{"id":1,"name":"向日葵","address":"广州市天河区棠安路288号","logopath":"","hotline":"020-85412424","merid":100099,"payinfos_id":1,"mobile":"13824489830","is_for_platform":null,"chain_id":null,"longitude":"113.3833670","latitude":"23.1420700","province":1,"city":17,"area":4},"userInfo":{"isAppointment":0,"isSign":0,"channelId":0,"membershipId":0,"appointmentId":""}}
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
         * courseAppList : [{"id":1574,"shop_id":94983847,"membership_id":"690","course_id":4768,"status":0,"user_id":55,"sign_staff_id":null,"is_deleted":0,"create_time":1521625888,"update_time":1521625888,"sign_time":null,"channel_id":0,"channel_membership_id":null,"payment_type":0,"money":"0.00","is_closed":0,"course_order_id":null,"mobile":"13377766381","username":"麦锦辉","avatar_url":"http://testyogabook.hq-xl.com/static/images/appuser_image/5ab85a4864ab3.png","headpicUrl":"http://thirdwx.qlogo.cn/mmopen/vi_32/jfCUvp4oicoqSY8DLUTT7jLTGqPFAEcVxZf9zVicicWkNXCcCAQytaEmdZibSpgU2dwkhvLxriaoc3wPGqMCcHcKqXA/132","nickname":"Aj23"},{"id":1669,"shop_id":94983847,"membership_id":"682","course_id":4768,"status":0,"user_id":10641,"sign_staff_id":null,"is_deleted":0,"create_time":1521771660,"update_time":1521771660,"sign_time":null,"channel_id":0,"channel_membership_id":null,"payment_type":0,"money":"0.00","is_closed":0,"course_order_id":null,"mobile":"15906557682","username":"楼","avatar_url":null,"headpicUrl":null,"nickname":null}]
         * courseDetail : {"id":4768,"shop_id":94983847,"parent_shop_id":1,"lesson_id":346,"start_time":1521806400,"end_time":1521808200,"classroom_id":196,"number":123,"create_time":1521624411,"update_time":1521625365,"is_deleted":0,"cancel_use_time":1,"course_color":null,"limit_number":0,"limit_cencel_time":0,"is_pay":2,"price":"0.01","is_recommend":1,"start":1521806400,"end":1521808200,"lessonName":"热瑜伽","lessonId":346,"lessonTime":30,"lessonImg":"http://testyogabook.hq-xl.com/static/images/lesson/5aa9e38d0322c.jpg","introduce":"测试热瑜伽","lessonContent":"","classroomName":"1班","classroomId":196,"allAppNum":2,"appFromYueKeNum":2,"appFromQuanYiNum":0,"tutorList":[{"id":232,"name":"张老师"}],"isExpired":1,"quanYiIsFull":1,"quanYiCanAppNum":0,"yueKeCanAppNum":121,"hasBook":"0"}
         * shopInfo : {"id":94983847,"name":"北京","address":"北京","logopath":"http://testyogabook.hq-xl.com/static/images/logo/shop_1833836431.png","hotline":"11111","merid":0,"payinfos_id":33,"mobile":"18800000000","is_for_platform":0,"chain_id":215,"longitude":"0.0000000","latitude":"0.0000000","province":1,"city":17,"area":4}
         * parentShopInfo : {"id":1,"name":"向日葵","address":"广州市天河区棠安路288号","logopath":"","hotline":"020-85412424","merid":100099,"payinfos_id":1,"mobile":"13824489830","is_for_platform":null,"chain_id":null,"longitude":"113.3833670","latitude":"23.1420700","province":1,"city":17,"area":4}
         * userInfo : {"isAppointment":0,"isSign":0,"channelId":0,"membershipId":0,"appointmentId":""}
         */

        private CourseDetailBean courseDetail;
        private ShopInfoBean shopInfo;
        private ParentShopInfoBean parentShopInfo;
        private UserInfoBean userInfo;
        private List<CourseAppListBean> courseAppList;

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

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public List<CourseAppListBean> getCourseAppList() {
            return courseAppList;
        }

        public void setCourseAppList(List<CourseAppListBean> courseAppList) {
            this.courseAppList = courseAppList;
        }

        public static class CourseDetailBean {
            /**
             * id : 4768
             * shop_id : 94983847
             * parent_shop_id : 1
             * lesson_id : 346
             * start_time : 1521806400
             * end_time : 1521808200
             * classroom_id : 196
             * number : 123
             * create_time : 1521624411
             * update_time : 1521625365
             * is_deleted : 0
             * cancel_use_time : 1
             * course_color : null
             * limit_number : 0
             * limit_cencel_time : 0
             * is_pay : 2
             * price : 0.01
             * is_recommend : 1
             * start : 1521806400
             * end : 1521808200
             * lessonName : 热瑜伽
             * lessonId : 346
             * lessonTime : 30
             * lessonImg : http://testyogabook.hq-xl.com/static/images/lesson/5aa9e38d0322c.jpg
             * introduce : 测试热瑜伽
             * lessonContent :
             * classroomName : 1班
             * classroomId : 196
             * allAppNum : 2
             * appFromYueKeNum : 2
             * appFromQuanYiNum : 0
             * tutorList : [{"id":232,"name":"张老师"}]
             * isExpired : 1
             * quanYiIsFull : 1
             * quanYiCanAppNum : 0
             * yueKeCanAppNum : 121
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
            private String course_color;
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

            public String getCourse_color() {
                return course_color;
            }

            public void setCourse_color(String course_color) {
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
                 * id : 232
                 * name : 张老师
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
             * id : 94983847
             * name : 北京
             * address : 北京
             * logopath : http://testyogabook.hq-xl.com/static/images/logo/shop_1833836431.png
             * hotline : 11111
             * merid : 0
             * payinfos_id : 33
             * mobile : 18800000000
             * is_for_platform : 0
             * chain_id : 215
             * longitude : 0.0000000
             * latitude : 0.0000000
             * province : 1
             * city : 17
             * area : 4
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
        }

        public static class ParentShopInfoBean {
            /**
             * id : 1
             * name : 向日葵
             * address : 广州市天河区棠安路288号
             * logopath :
             * hotline : 020-85412424
             * merid : 100099
             * payinfos_id : 1
             * mobile : 13824489830
             * is_for_platform : null
             * chain_id : null
             * longitude : 113.3833670
             * latitude : 23.1420700
             * province : 1
             * city : 17
             * area : 4
             */

            private int id;
            private String name;
            private String address;
            private String logopath;
            private String hotline;
            private int merid;
            private int payinfos_id;
            private String mobile;
            private String is_for_platform;
            private String chain_id;
            private String longitude;
            private String latitude;
            private int province;
            private int city;
            private int area;

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

            public String getIs_for_platform() {
                return is_for_platform;
            }

            public void setIs_for_platform(String is_for_platform) {
                this.is_for_platform = is_for_platform;
            }

            public String getChain_id() {
                return chain_id;
            }

            public void setChain_id(String chain_id) {
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
        }

        public static class UserInfoBean {
            /**
             * isAppointment : 0
             * isSign : 0
             * channelId : 0
             * membershipId : 0
             * appointmentId :
             */

            private int isAppointment;
            private int isSign;
            private int channelId;
            private int membershipId;
            private String appointmentId;

            public int getIsAppointment() {
                return isAppointment;
            }

            public void setIsAppointment(int isAppointment) {
                this.isAppointment = isAppointment;
            }

            public int getIsSign() {
                return isSign;
            }

            public void setIsSign(int isSign) {
                this.isSign = isSign;
            }

            public int getChannelId() {
                return channelId;
            }

            public void setChannelId(int channelId) {
                this.channelId = channelId;
            }

            public int getMembershipId() {
                return membershipId;
            }

            public void setMembershipId(int membershipId) {
                this.membershipId = membershipId;
            }

            public String getAppointmentId() {
                return appointmentId;
            }

            public void setAppointmentId(String appointmentId) {
                this.appointmentId = appointmentId;
            }
        }

        public static class CourseAppListBean {
            /**
             * id : 1574
             * shop_id : 94983847
             * membership_id : 690
             * course_id : 4768
             * status : 0
             * user_id : 55
             * sign_staff_id : null
             * is_deleted : 0
             * create_time : 1521625888
             * update_time : 1521625888
             * sign_time : null
             * channel_id : 0
             * channel_membership_id : null
             * payment_type : 0
             * money : 0.00
             * is_closed : 0
             * course_order_id : null
             * mobile : 13377766381
             * username : 麦锦辉
             * avatar_url : http://testyogabook.hq-xl.com/static/images/appuser_image/5ab85a4864ab3.png
             * headpicUrl : http://thirdwx.qlogo.cn/mmopen/vi_32/jfCUvp4oicoqSY8DLUTT7jLTGqPFAEcVxZf9zVicicWkNXCcCAQytaEmdZibSpgU2dwkhvLxriaoc3wPGqMCcHcKqXA/132
             * nickname : Aj23
             */

            private int id;
            private int shop_id;
            private String membership_id;
            private int course_id;
            private int status;
            private int user_id;
            private String sign_staff_id;
            private int is_deleted;
            private int create_time;
            private int update_time;
            private String sign_time;
            private int channel_id;
            private String channel_membership_id;
            private int payment_type;
            private String money;
            private int is_closed;
            private String course_order_id;
            private String mobile;
            private String username;
            private String avatar_url;
            private String headpicUrl;
            private String nickname;

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

            public String getMembership_id() {
                return membership_id;
            }

            public void setMembership_id(String membership_id) {
                this.membership_id = membership_id;
            }

            public int getCourse_id() {
                return course_id;
            }

            public void setCourse_id(int course_id) {
                this.course_id = course_id;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getSign_staff_id() {
                return sign_staff_id;
            }

            public void setSign_staff_id(String sign_staff_id) {
                this.sign_staff_id = sign_staff_id;
            }

            public int getIs_deleted() {
                return is_deleted;
            }

            public void setIs_deleted(int is_deleted) {
                this.is_deleted = is_deleted;
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

            public String getSign_time() {
                return sign_time;
            }

            public void setSign_time(String sign_time) {
                this.sign_time = sign_time;
            }

            public int getChannel_id() {
                return channel_id;
            }

            public void setChannel_id(int channel_id) {
                this.channel_id = channel_id;
            }

            public String getChannel_membership_id() {
                return channel_membership_id;
            }

            public void setChannel_membership_id(String channel_membership_id) {
                this.channel_membership_id = channel_membership_id;
            }

            public int getPayment_type() {
                return payment_type;
            }

            public void setPayment_type(int payment_type) {
                this.payment_type = payment_type;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public int getIs_closed() {
                return is_closed;
            }

            public void setIs_closed(int is_closed) {
                this.is_closed = is_closed;
            }

            public String getCourse_order_id() {
                return course_order_id;
            }

            public void setCourse_order_id(String course_order_id) {
                this.course_order_id = course_order_id;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getAvatar_url() {
                return avatar_url;
            }

            public void setAvatar_url(String avatar_url) {
                this.avatar_url = avatar_url;
            }

            public String getHeadpicUrl() {
                return headpicUrl;
            }

            public void setHeadpicUrl(String headpicUrl) {
                this.headpicUrl = headpicUrl;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }
        }
    }
}
