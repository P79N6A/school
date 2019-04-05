package com.idyoga.yoga.model;

import java.util.List;

/**
 * 文 件 名: ShopDetailBean
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/21
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class ShopMarketCourseAppointmentBean {

    /**
     * shopInfo : {"id":63684083,"name":"广州","address":"123","logopath":"http://testyogabook.hq-xl.com/static/images/logo/shop_1455076031.png","hotline":"123","merid":0,"payinfos_id":33,"mobile":"13500000000","is_for_platform":0,"chain_id":214,"longitude":"0.0000000","latitude":"0.0000000","province":1,"city":17,"area":4,"introduce":null,"is_verify":2,"shop_code":null,"secret_key":null}
     * userInfo : {"id":10771,"name":"喵星人（毛孩子2）","ui_id":12698,"mobile":"15772747952","sex":1,"nickname":"喵星人","is_deleted":0,"headpicUrl":null,"openid":null}
     * courseAppList : [{"id":18872,"shop_id":63684083,"membership_id":"5373","course_id":28778,"status":0,"user_id":10771,"sign_staff_id":null,"is_deleted":0,"create_time":1529890192,"update_time":1529890192,"sign_time":null,"channel_id":0,"channel_membership_id":null,"payment_type":0,"money":"0.00","is_closed":0,"course_order_id":null,"use_points":0,"mobile":"15772747952","username":"喵星人","avatar_url":"http://testyogabook.hq-xl.com/static/images/appuser_image/5b285e8ac3442.jpg","headpicUrl":null,"nickname":null}]
     * courseDetail : {"id":28778,"shop_id":63684083,"parent_shop_id":20881491,"lesson_id":4183,"start_time":1546128000,"end_time":1546131600,"classroom_id":195,"number":10,"create_time":1529550340,"update_time":1529550340,"is_deleted":0,"cancel_use_time":0,"course_color":null,"limit_number":0,"limit_cencel_time":0,"is_pay":2,"price":"0.00","is_recommend":0,"appointment_limit_time":0,"use_points":0,"is_auto_cancel":0,"auto_cancel_check_time":0,"auto_cancel_check_number":0,"cancel_status":0,"start":1546128000,"end":1546131600,"lessonName":"体验课","lessonId":4183,"lessonTime":60,"lessonImg":"","introduce":"1.目前以上课程的日期与时间仅做展示使用，在您预约成功后，IDYoga平台客服将在2小时内与您联系确认课程需求\r\n2.为提供更优质的服务，所有预约流程均提供客服专线答疑，如有疑问可致电400-902-0929","lessonContent":"","classroomName":"大班课室","classroomId":195,"allAppNum":1,"appFromYueKeNum":1,"appFromQuanYiNum":0,"tutorList":[{"id":240,"name":"陈老师"}],"isExpired":0,"quanYiIsFull":1,"quanYiCanAppNum":0,"yueKeCanAppNum":9}
     * usingMembership : [{"shopId":63684083,"shopName":"广州","shopLogopath":"http://testyogabook.hq-xl.com/static/images/logo/shop_1455076031.png","membership":[{"membershipId":5373,"categoryId":1,"categoryName":"期限卡","status":0,"cardName":"权益5","expireTime":1559270880,"startTime":1527734880,"hasTerm":12,"hasTime":0,"validTime":0,"price":"0.01","isChain":1},{"membershipId":5389,"categoryId":1,"categoryName":"期限卡","status":0,"cardName":"权益5","expireTime":1559270880,"startTime":1527734880,"hasTerm":12,"hasTime":0,"validTime":0,"price":"0.01","isChain":1},{"membershipId":5442,"categoryId":2,"categoryName":"次卡","status":0,"cardName":"权益20次卡","expireTime":1560418800,"startTime":1528882800,"hasTerm":2,"hasTime":20,"validTime":20,"price":"0.01","isChain":1},{"membershipId":5567,"categoryId":2,"categoryName":"次卡","status":0,"cardName":"现联公司权益卡","expireTime":1561012080,"startTime":1529476080,"hasTerm":12,"hasTime":600,"validTime":600,"price":"0.10","isChain":1}]}]
     */

    private ShopInfoBean shopInfo;
    private UserInfoBean userInfo;
    private CourseDetailBean courseDetail;
    private List<CourseAppListBean> courseAppList;
    private List<UsingMembershipBean> usingMembership;

    public ShopInfoBean getShopInfo() {
        return shopInfo;
    }

    public void setShopInfo(ShopInfoBean shopInfo) {
        this.shopInfo = shopInfo;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public CourseDetailBean getCourseDetail() {
        return courseDetail;
    }

    public void setCourseDetail(CourseDetailBean courseDetail) {
        this.courseDetail = courseDetail;
    }

    public List<CourseAppListBean> getCourseAppList() {
        return courseAppList;
    }

    public void setCourseAppList(List<CourseAppListBean> courseAppList) {
        this.courseAppList = courseAppList;
    }

    public List<UsingMembershipBean> getUsingMembership() {
        return usingMembership;
    }

    public void setUsingMembership(List<UsingMembershipBean> usingMembership) {
        this.usingMembership = usingMembership;
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
         * shop_code : null
         * secret_key : null
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
        private Object shop_code;
        private Object secret_key;

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

        public Object getShop_code() {
            return shop_code;
        }

        public void setShop_code(Object shop_code) {
            this.shop_code = shop_code;
        }

        public Object getSecret_key() {
            return secret_key;
        }

        public void setSecret_key(Object secret_key) {
            this.secret_key = secret_key;
        }
    }

    public static class UserInfoBean {
        /**
         * id : 10771
         * name : 喵星人（毛孩子2）
         * ui_id : 12698
         * mobile : 15772747952
         * sex : 1
         * nickname : 喵星人
         * is_deleted : 0
         * headpicUrl : null
         * openid : null
         */

        private int id;
        private String name;
        private int ui_id;
        private String mobile;
        private int sex;
        private String nickname;
        private int is_deleted;
        private Object headpicUrl;
        private Object openid;

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

        public int getUi_id() {
            return ui_id;
        }

        public void setUi_id(int ui_id) {
            this.ui_id = ui_id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getIs_deleted() {
            return is_deleted;
        }

        public void setIs_deleted(int is_deleted) {
            this.is_deleted = is_deleted;
        }

        public Object getHeadpicUrl() {
            return headpicUrl;
        }

        public void setHeadpicUrl(Object headpicUrl) {
            this.headpicUrl = headpicUrl;
        }

        public Object getOpenid() {
            return openid;
        }

        public void setOpenid(Object openid) {
            this.openid = openid;
        }
    }

    public static class CourseDetailBean {
        /**
         * id : 28778
         * shop_id : 63684083
         * parent_shop_id : 20881491
         * lesson_id : 4183
         * start_time : 1546128000
         * end_time : 1546131600
         * classroom_id : 195
         * number : 10
         * create_time : 1529550340
         * update_time : 1529550340
         * is_deleted : 0
         * cancel_use_time : 0
         * course_color : null
         * limit_number : 0
         * limit_cencel_time : 0
         * is_pay : 2
         * price : 0.00
         * is_recommend : 0
         * appointment_limit_time : 0
         * use_points : 0
         * is_auto_cancel : 0
         * auto_cancel_check_time : 0
         * auto_cancel_check_number : 0
         * cancel_status : 0
         * start : 1546128000
         * end : 1546131600
         * lessonName : 体验课
         * lessonId : 4183
         * lessonTime : 60
         * lessonImg :
         * introduce : 1.目前以上课程的日期与时间仅做展示使用，在您预约成功后，IDYoga平台客服将在2小时内与您联系确认课程需求
         2.为提供更优质的服务，所有预约流程均提供客服专线答疑，如有疑问可致电400-902-0929
         * lessonContent :
         * classroomName : 大班课室
         * classroomId : 195
         * allAppNum : 1
         * appFromYueKeNum : 1
         * appFromQuanYiNum : 0
         * tutorList : [{"id":240,"name":"陈老师"}]
         * isExpired : 0
         * quanYiIsFull : 1
         * quanYiCanAppNum : 0
         * yueKeCanAppNum : 9
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

        public List<TutorListBean> getTutorList() {
            return tutorList;
        }

        public void setTutorList(List<TutorListBean> tutorList) {
            this.tutorList = tutorList;
        }

        public static class TutorListBean {
            /**
             * id : 240
             * name : 陈老师
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

    public static class CourseAppListBean {
        /**
         * id : 18872
         * shop_id : 63684083
         * membership_id : 5373
         * course_id : 28778
         * status : 0
         * user_id : 10771
         * sign_staff_id : null
         * is_deleted : 0
         * create_time : 1529890192
         * update_time : 1529890192
         * sign_time : null
         * channel_id : 0
         * channel_membership_id : null
         * payment_type : 0
         * money : 0.00
         * is_closed : 0
         * course_order_id : null
         * use_points : 0
         * mobile : 15772747952
         * username : 喵星人
         * avatar_url : http://testyogabook.hq-xl.com/static/images/appuser_image/5b285e8ac3442.jpg
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

    public static class UsingMembershipBean {
        /**
         * shopId : 63684083
         * shopName : 广州
         * shopLogopath : http://testyogabook.hq-xl.com/static/images/logo/shop_1455076031.png
         * membership : [{"membershipId":5373,"categoryId":1,"categoryName":"期限卡","status":0,"cardName":"权益5","expireTime":1559270880,"startTime":1527734880,"hasTerm":12,"hasTime":0,"validTime":0,"price":"0.01","isChain":1},{"membershipId":5389,"categoryId":1,"categoryName":"期限卡","status":0,"cardName":"权益5","expireTime":1559270880,"startTime":1527734880,"hasTerm":12,"hasTime":0,"validTime":0,"price":"0.01","isChain":1},{"membershipId":5442,"categoryId":2,"categoryName":"次卡","status":0,"cardName":"权益20次卡","expireTime":1560418800,"startTime":1528882800,"hasTerm":2,"hasTime":20,"validTime":20,"price":"0.01","isChain":1},{"membershipId":5567,"categoryId":2,"categoryName":"次卡","status":0,"cardName":"现联公司权益卡","expireTime":1561012080,"startTime":1529476080,"hasTerm":12,"hasTime":600,"validTime":600,"price":"0.10","isChain":1}]
         */

        private int shopId;
        private String shopName;
        private String shopLogopath;
        private List<MembershipBean> membership;

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public String getShopLogopath() {
            return shopLogopath;
        }

        public void setShopLogopath(String shopLogopath) {
            this.shopLogopath = shopLogopath;
        }

        public List<MembershipBean> getMembership() {
            return membership;
        }

        public void setMembership(List<MembershipBean> membership) {
            this.membership = membership;
        }

        public static class MembershipBean {
            /**
             * membershipId : 5373
             * categoryId : 1
             * categoryName : 期限卡
             * status : 0
             * cardName : 权益5
             * expireTime : 1559270880
             * startTime : 1527734880
             * hasTerm : 12
             * hasTime : 0
             * validTime : 0
             * price : 0.01
             * isChain : 1
             */

            private int membershipId;
            private int categoryId;
            private String categoryName;
            private int status;
            private String cardName;
            private int expireTime;
            private int startTime;
            private int hasTerm;
            private int hasTime;
            private int validTime;
            private String price;
            private int isChain;

            public int getMembershipId() {
                return membershipId;
            }

            public void setMembershipId(int membershipId) {
                this.membershipId = membershipId;
            }

            public int getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(int categoryId) {
                this.categoryId = categoryId;
            }

            public String getCategoryName() {
                return categoryName;
            }

            public void setCategoryName(String categoryName) {
                this.categoryName = categoryName;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getCardName() {
                return cardName;
            }

            public void setCardName(String cardName) {
                this.cardName = cardName;
            }

            public int getExpireTime() {
                return expireTime;
            }

            public void setExpireTime(int expireTime) {
                this.expireTime = expireTime;
            }

            public int getStartTime() {
                return startTime;
            }

            public void setStartTime(int startTime) {
                this.startTime = startTime;
            }

            public int getHasTerm() {
                return hasTerm;
            }

            public void setHasTerm(int hasTerm) {
                this.hasTerm = hasTerm;
            }

            public int getHasTime() {
                return hasTime;
            }

            public void setHasTime(int hasTime) {
                this.hasTime = hasTime;
            }

            public int getValidTime() {
                return validTime;
            }

            public void setValidTime(int validTime) {
                this.validTime = validTime;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public int getIsChain() {
                return isChain;
            }

            public void setIsChain(int isChain) {
                this.isChain = isChain;
            }
        }
    }
}
