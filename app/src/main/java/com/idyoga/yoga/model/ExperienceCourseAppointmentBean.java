package com.idyoga.yoga.model;

import java.util.List;

public class ExperienceCourseAppointmentBean {


    /**
     * courseDetail : {"id":33787,"start_time":1532422800,"end_time":1532424600,"cancel_use_time":0,"is_platform_course":1,"lesson_name":"林大师瑜伽课测试","lesson_id":120,"lesson_time":30,"lesson_img":"http://testyogabook.hq-xl.com/static/images/lesson/5adee594bacba.jpg","classroom_name":"4-103","classroom_id":373,"tutor_name":"林大师,方老师"}
     * usingMembership : [{"card_name":"广州100次测试卡","card_category_id":2,"id":6655,"order_id":1,"user_id":88,"card_id":1,"expire_time":1537409820,"start_time":1532053020,"has_term":2,"has_time":100,"valid_time":100,"status":0,"remarks":null,"is_deleted":0,"create_time":1532053049,"update_time":1532053049,"is_chain":1,"type":2,"annex_num":5,"valid_annex_num":5,"is_freeze":0}]
     * shopInfo : {"name":"现联瑜伽馆","address":"广州天河","mobile":"13300000000"}
     */

    private CourseDetailBean courseDetail;
    private ShopInfoBean shopInfo;
    private List<UsingMembershipBean> usingMembership;

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

    public List<UsingMembershipBean> getUsingMembership() {
        return usingMembership;
    }

    public void setUsingMembership(List<UsingMembershipBean> usingMembership) {
        this.usingMembership = usingMembership;
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
         * mobile : 13300000000
         */

        private String name;
        private String address;
        private String mobile;

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

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }

    public static class UsingMembershipBean {
        /**
         * card_name : 广州100次测试卡
         * card_category_id : 2
         * id : 6655
         * order_id : 1
         * user_id : 88
         * card_id : 1
         * expire_time : 1537409820
         * start_time : 1532053020
         * has_term : 2
         * has_time : 100
         * valid_time : 100
         * status : 0
         * remarks : null
         * is_deleted : 0
         * create_time : 1532053049
         * update_time : 1532053049
         * is_chain : 1
         * type : 2
         * annex_num : 5
         * valid_annex_num : 5
         * is_freeze : 0
         */

        private String card_name;
        private int card_category_id;
        private int id;
        private int order_id;
        private int user_id;
        private int card_id;
        private int expire_time;
        private int start_time;
        private int has_term;
        private int has_time;
        private int valid_time;
        private int status;
        private Object remarks;
        private int is_deleted;
        private int create_time;
        private int update_time;
        private int is_chain;
        private int type;
        private int annex_num;
        private int valid_annex_num;
        private int is_freeze;

        public String getCard_name() {
            return card_name;
        }

        public void setCard_name(String card_name) {
            this.card_name = card_name;
        }

        public int getCard_category_id() {
            return card_category_id;
        }

        public void setCard_category_id(int card_category_id) {
            this.card_category_id = card_category_id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getCard_id() {
            return card_id;
        }

        public void setCard_id(int card_id) {
            this.card_id = card_id;
        }

        public int getExpire_time() {
            return expire_time;
        }

        public void setExpire_time(int expire_time) {
            this.expire_time = expire_time;
        }

        public int getStart_time() {
            return start_time;
        }

        public void setStart_time(int start_time) {
            this.start_time = start_time;
        }

        public int getHas_term() {
            return has_term;
        }

        public void setHas_term(int has_term) {
            this.has_term = has_term;
        }

        public int getHas_time() {
            return has_time;
        }

        public void setHas_time(int has_time) {
            this.has_time = has_time;
        }

        public int getValid_time() {
            return valid_time;
        }

        public void setValid_time(int valid_time) {
            this.valid_time = valid_time;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Object getRemarks() {
            return remarks;
        }

        public void setRemarks(Object remarks) {
            this.remarks = remarks;
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

        public int getIs_chain() {
            return is_chain;
        }

        public void setIs_chain(int is_chain) {
            this.is_chain = is_chain;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getAnnex_num() {
            return annex_num;
        }

        public void setAnnex_num(int annex_num) {
            this.annex_num = annex_num;
        }

        public int getValid_annex_num() {
            return valid_annex_num;
        }

        public void setValid_annex_num(int valid_annex_num) {
            this.valid_annex_num = valid_annex_num;
        }

        public int getIs_freeze() {
            return is_freeze;
        }

        public void setIs_freeze(int is_freeze) {
            this.is_freeze = is_freeze;
        }
    }
}
