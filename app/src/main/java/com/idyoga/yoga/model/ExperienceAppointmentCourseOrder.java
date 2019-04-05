package com.idyoga.yoga.model;

public class ExperienceAppointmentCourseOrder {


    /**
     * appointmentInfo : {"id":26048,"shop_id":19287727,"membership_id":"6657","course_id":33787,"status":0,"user_id":10771,"sign_staff_id":null,"is_deleted":0,"create_time":1532417060,"update_time":1532417060,"sign_time":null,"channel_id":0,"channel_membership_id":null,"payment_type":0,"money":"0.00","is_closed":0,"course_order_id":null,"use_points":0,"source":2,"confirm_status":0,"confirm_expire_time":0}
     * courseDetail : {"id":33787,"start_time":1532422800,"end_time":1532424600,"cancel_use_time":0,"is_platform_course":1,"lesson_name":"林大师瑜伽课测试","lesson_id":120,"lesson_time":30,"lesson_img":"http://testyogabook.hq-xl.com/static/images/lesson/5adee594bacba.jpg","classroom_name":"4-103","classroom_id":373,"tutor_name":"林大师,方老师"}
     * shopInfo : {"name":"现联瑜伽馆","address":"广州天河"}
     * qrCode : http://testyogabook.hq-xl.com/shop/sign_qrcode/199277dd13448b0babf1cc6aa657fb8b.jpg
     * now : 1532417840
     */

    private AppointmentInfoBean appointmentInfo;
    private CourseDetailBean courseDetail;
    private ShopInfoBean shopInfo;
    private String qrCode;
    private int now;

    public AppointmentInfoBean getAppointmentInfo() {
        return appointmentInfo;
    }

    public void setAppointmentInfo(AppointmentInfoBean appointmentInfo) {
        this.appointmentInfo = appointmentInfo;
    }

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

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public int getNow() {
        return now;
    }

    public void setNow(int now) {
        this.now = now;
    }

    public static class AppointmentInfoBean {
        /**
         * id : 26048
         * shop_id : 19287727
         * membership_id : 6657
         * course_id : 33787
         * status : 0
         * user_id : 10771
         * sign_staff_id : null
         * is_deleted : 0
         * create_time : 1532417060
         * update_time : 1532417060
         * sign_time : null
         * channel_id : 0
         * channel_membership_id : null
         * payment_type : 0
         * money : 0.00
         * is_closed : 0
         * course_order_id : null
         * use_points : 0
         * source : 2
         * confirm_status : 0
         * confirm_expire_time : 0
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
        private int source;
        private int confirm_status;
        private int confirm_expire_time;

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

        public int getSource() {
            return source;
        }

        public void setSource(int source) {
            this.source = source;
        }

        public int getConfirm_status() {
            return confirm_status;
        }

        public void setConfirm_status(int confirm_status) {
            this.confirm_status = confirm_status;
        }

        public int getConfirm_expire_time() {
            return confirm_expire_time;
        }

        public void setConfirm_expire_time(int confirm_expire_time) {
            this.confirm_expire_time = confirm_expire_time;
        }
    }

    public static class CourseDetailBean {
        /**
         * id : 33787
         * start_time : 1532422800
         * end_time : 1532424600
         * cancel_use_time : 0
         * is_platform_course : 1
         * lesson_name : 林大师瑜伽课测试
         * lesson_id : 120
         * lesson_time : 30
         * lesson_img : http://testyogabook.hq-xl.com/static/images/lesson/5adee594bacba.jpg
         * classroom_name : 4-103
         * classroom_id : 373
         * tutor_name : 林大师,方老师
         */

        private int id;
        private int start_time;
        private int end_time;
        private int cancel_use_time;
        private int is_platform_course;
        private String lesson_name;
        private int lesson_id;
        private int lesson_time;
        private String lesson_img;
        private String classroom_name;
        private int classroom_id;
        private String tutor_name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public int getCancel_use_time() {
            return cancel_use_time;
        }

        public void setCancel_use_time(int cancel_use_time) {
            this.cancel_use_time = cancel_use_time;
        }

        public int getIs_platform_course() {
            return is_platform_course;
        }

        public void setIs_platform_course(int is_platform_course) {
            this.is_platform_course = is_platform_course;
        }

        public String getLesson_name() {
            return lesson_name;
        }

        public void setLesson_name(String lesson_name) {
            this.lesson_name = lesson_name;
        }

        public int getLesson_id() {
            return lesson_id;
        }

        public void setLesson_id(int lesson_id) {
            this.lesson_id = lesson_id;
        }

        public int getLesson_time() {
            return lesson_time;
        }

        public void setLesson_time(int lesson_time) {
            this.lesson_time = lesson_time;
        }

        public String getLesson_img() {
            return lesson_img;
        }

        public void setLesson_img(String lesson_img) {
            this.lesson_img = lesson_img;
        }

        public String getClassroom_name() {
            return classroom_name;
        }

        public void setClassroom_name(String classroom_name) {
            this.classroom_name = classroom_name;
        }

        public int getClassroom_id() {
            return classroom_id;
        }

        public void setClassroom_id(int classroom_id) {
            this.classroom_id = classroom_id;
        }

        public String getTutor_name() {
            return tutor_name;
        }

        public void setTutor_name(String tutor_name) {
            this.tutor_name = tutor_name;
        }
    }

    public static class ShopInfoBean {
        /**
         * name : 现联瑜伽馆
         * address : 广州天河
         */

        private String name;
        private String address;

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
    }
}
