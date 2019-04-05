package com.idyoga.yoga.model;

import java.util.List;

/**
 * 文 件 名: TeamCourseDetailBean
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/13
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class TeamCourseDetailBean {


    /**
     * courseDetail : {"id":25497,"shop_id":63684083,"parent_shop_id":61976547,"lesson_id":405,"start_time":1546128000,"end_time":1546131600,"classroom_id":195,"number":10,"create_time":1527573695,"update_time":1527573695,"is_deleted":0,"cancel_use_time":0,"course_color":null,"limit_number":0,"limit_cencel_time":0,"is_pay":2,"price":"100.00","is_recommend":0,"appointment_limit_time":0,"use_points":0,"is_auto_cancel":0,"auto_cancel_check_time":0,"auto_cancel_check_number":0,"cancel_status":0,"start":1546128000,"end":1546131600,"lessonName":"脊柱矫正","lessonId":405,"lessonTime":60,"lessonImg":"http://testyogabook.hq-xl.com/static/images/lesson/5ad9aa54d0ca9.jpg","introduce":"1.目前以上课程的日期与时间仅做展示使用，在您预约成功后，IDYoga平台客服将在2小时内与您联系确认课程需求\r\n2.为提供更优质的服务，所有预约流程均提供客服专线答疑，如有疑问可致电400-902-0929","lessonContent":"","classroomName":"大班课室","classroomId":195,"allAppNum":1,"appFromYueKeNum":1,"appFromQuanYiNum":0,"tutorList":[{"id":231,"name":"张老师","image":""}],"isExpired":0,"quanYiIsFull":1,"quanYiCanAppNum":0,"yueKeCanAppNum":9,"hasBook":"1"}
     * courseAppList : [{"id":15484,"shop_id":63684083,"membership_id":"2208","course_id":25497,"status":0,"user_id":11379,"sign_staff_id":null,"is_deleted":0,"create_time":1528530119,"update_time":1528530119,"sign_time":null,"channel_id":0,"channel_membership_id":null,"payment_type":0,"money":"0.00","is_closed":0,"course_order_id":null,"use_points":0,"mobile":"13097340262","username":"domain","avatar_url":"http://testyogabook.hq-xl.com/static/images/appuser_image/5b1f74122d8ca.jpg","headpicUrl":null,"nickname":null}]
     * shopInfo : {"id":63684083,"name":"广州","address":"123","logopath":"http://testyogabook.hq-xl.com/static/images/logo/shop_1455076031.png","hotline":"123","merid":0,"payinfos_id":33,"mobile":"13500000000","is_for_platform":0,"chain_id":214,"longitude":"0.0000000","latitude":"0.0000000","province":1,"city":17,"area":4,"introduce":null,"is_verify":2}
     * parentShopInfo : {"id":61976547,"name":"东方瑜伽","address":"五山路金山大厦旁金山轩601","logopath":"","hotline":"18520544852","merid":0,"payinfos_id":0,"mobile":"18520544852","is_for_platform":0,"chain_id":null,"longitude":"113.3529442","latitude":"23.1496590","province":1,"city":17,"area":4,"introduce":null,"is_verify":0}
     * userInfo : {"isAppointment":1,"isSign":0,"appointmentId":15484,"channelId":0,"membershipId":"2208","qrCodeUrl":"http://testyogabook.hq-xl.com/shop/sign_qrcode/9bb4d31ee19c9fbb386fe7eef0e45b9a.jpg"}
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
         * id : 25497
         * shop_id : 63684083
         * parent_shop_id : 61976547
         * lesson_id : 405
         * start_time : 1546128000
         * end_time : 1546131600
         * classroom_id : 195
         * number : 10
         * create_time : 1527573695
         * update_time : 1527573695
         * is_deleted : 0
         * cancel_use_time : 0
         * course_color : null
         * limit_number : 0
         * limit_cencel_time : 0
         * is_pay : 2
         * price : 100.00
         * is_recommend : 0
         * appointment_limit_time : 0
         * use_points : 0
         * is_auto_cancel : 0
         * auto_cancel_check_time : 0
         * auto_cancel_check_number : 0
         * cancel_status : 0
         * start : 1546128000
         * end : 1546131600
         * lessonName : 脊柱矫正
         * lessonId : 405
         * lessonTime : 60
         * lessonImg : http://testyogabook.hq-xl.com/static/images/lesson/5ad9aa54d0ca9.jpg
         * introduce : 1.目前以上课程的日期与时间仅做展示使用，在您预约成功后，IDYoga平台客服将在2小时内与您联系确认课程需求
         2.为提供更优质的服务，所有预约流程均提供客服专线答疑，如有疑问可致电400-902-0929
         * lessonContent :
         * classroomName : 大班课室
         * classroomId : 195
         * allAppNum : 1
         * appFromYueKeNum : 1
         * appFromQuanYiNum : 0
         * tutorList : [{"id":231,"name":"张老师","image":""}]
         * isExpired : 0
         * quanYiIsFull : 1
         * quanYiCanAppNum : 0
         * yueKeCanAppNum : 9
         * hasBook : 1
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
        private int appointment_limit_time;
        private int use_points;
        private int is_auto_cancel;
        private int auto_cancel_check_time;
        private int auto_cancel_check_number;
        private int cancel_status;
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

        public int getAppointment_limit_time() {
            return appointment_limit_time;
        }

        public void setAppointment_limit_time(int appointment_limit_time) {
            this.appointment_limit_time = appointment_limit_time;
        }

        public int getUse_points() {
            return use_points;
        }

        public void setUse_points(int use_points) {
            this.use_points = use_points;
        }

        public int getIs_auto_cancel() {
            return is_auto_cancel;
        }

        public void setIs_auto_cancel(int is_auto_cancel) {
            this.is_auto_cancel = is_auto_cancel;
        }

        public int getAuto_cancel_check_time() {
            return auto_cancel_check_time;
        }

        public void setAuto_cancel_check_time(int auto_cancel_check_time) {
            this.auto_cancel_check_time = auto_cancel_check_time;
        }

        public int getAuto_cancel_check_number() {
            return auto_cancel_check_number;
        }

        public void setAuto_cancel_check_number(int auto_cancel_check_number) {
            this.auto_cancel_check_number = auto_cancel_check_number;
        }

        public int getCancel_status() {
            return cancel_status;
        }

        public void setCancel_status(int cancel_status) {
            this.cancel_status = cancel_status;
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
             * id : 231
             * name : 张老师
             * image :
             */

            private int id;
            private String name;
            private String image;

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
        }
    }

    public static class ShopInfoBean {
        /**
         * id : 63684083
         * name : 广州
         * address : 123
         * logopath : http://testyogabook.hq-xl.com/static/images/logo/shop_1455076031.png
         * hotline : 123
         * merid : 0
         * payinfos_id : 33
         * mobile : 13500000000
         * is_for_platform : 0
         * chain_id : 214
         * longitude : 0.0000000
         * latitude : 0.0000000
         * province : 1
         * city : 17
         * area : 4
         * introduce : null
         * is_verify : 2
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
    }

    public static class ParentShopInfoBean {
        /**
         * id : 61976547
         * name : 东方瑜伽
         * address : 五山路金山大厦旁金山轩601
         * logopath :
         * hotline : 18520544852
         * merid : 0
         * payinfos_id : 0
         * mobile : 18520544852
         * is_for_platform : 0
         * chain_id : null
         * longitude : 113.3529442
         * latitude : 23.1496590
         * province : 1
         * city : 17
         * area : 4
         * introduce : null
         * is_verify : 0
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
        private int province;
        private int city;
        private int area;
        private Object introduce;
        private int is_verify;

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
    }

    public static class UserInfoBean {
        /**
         * isAppointment : 1
         * isSign : 0
         * appointmentId : 15484
         * channelId : 0
         * membershipId : 2208
         * qrCodeUrl : http://testyogabook.hq-xl.com/shop/sign_qrcode/9bb4d31ee19c9fbb386fe7eef0e45b9a.jpg
         */

        private int isAppointment;
        private int isSign;
        private int appointmentId;
        private int channelId;
        private String membershipId;
        private String qrCodeUrl;

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

        public int getAppointmentId() {
            return appointmentId;
        }

        public void setAppointmentId(int appointmentId) {
            this.appointmentId = appointmentId;
        }

        public int getChannelId() {
            return channelId;
        }

        public void setChannelId(int channelId) {
            this.channelId = channelId;
        }

        public String getMembershipId() {
            return membershipId;
        }

        public void setMembershipId(String membershipId) {
            this.membershipId = membershipId;
        }

        public String getQrCodeUrl() {
            return qrCodeUrl;
        }

        public void setQrCodeUrl(String qrCodeUrl) {
            this.qrCodeUrl = qrCodeUrl;
        }
    }

    public static class CourseAppListBean {
        /**
         * id : 15484
         * shop_id : 63684083
         * membership_id : 2208
         * course_id : 25497
         * status : 0
         * user_id : 11379
         * sign_staff_id : null
         * is_deleted : 0
         * create_time : 1528530119
         * update_time : 1528530119
         * sign_time : null
         * channel_id : 0
         * channel_membership_id : null
         * payment_type : 0
         * money : 0.00
         * is_closed : 0
         * course_order_id : null
         * use_points : 0
         * mobile : 13097340262
         * username : domain
         * avatar_url : http://testyogabook.hq-xl.com/static/images/appuser_image/5b1f74122d8ca.jpg
         * headpicUrl : null
         * nickname : null
         */

        private int id;
        private int shop_id;
        private String membership_id;
        private int course_id;
        private int status;
        private int user_id;
        private Object sign_staff_id;
        private int is_deleted;
        private int create_time;
        private int update_time;
        private Object sign_time;
        private int channel_id;
        private Object channel_membership_id;
        private int payment_type;
        private String money;
        private int is_closed;
        private Object course_order_id;
        private int use_points;
        private String mobile;
        private String username;
        private String avatar_url;
        private Object headpicUrl;
        private Object nickname;

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

        public Object getSign_staff_id() {
            return sign_staff_id;
        }

        public void setSign_staff_id(Object sign_staff_id) {
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

        public Object getSign_time() {
            return sign_time;
        }

        public void setSign_time(Object sign_time) {
            this.sign_time = sign_time;
        }

        public int getChannel_id() {
            return channel_id;
        }

        public void setChannel_id(int channel_id) {
            this.channel_id = channel_id;
        }

        public Object getChannel_membership_id() {
            return channel_membership_id;
        }

        public void setChannel_membership_id(Object channel_membership_id) {
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

        public Object getCourse_order_id() {
            return course_order_id;
        }

        public void setCourse_order_id(Object course_order_id) {
            this.course_order_id = course_order_id;
        }

        public int getUse_points() {
            return use_points;
        }

        public void setUse_points(int use_points) {
            this.use_points = use_points;
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

        public Object getHeadpicUrl() {
            return headpicUrl;
        }

        public void setHeadpicUrl(Object headpicUrl) {
            this.headpicUrl = headpicUrl;
        }

        public Object getNickname() {
            return nickname;
        }

        public void setNickname(Object nickname) {
            this.nickname = nickname;
        }
    }
}
