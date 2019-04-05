package com.idyoga.yoga.model;

import java.util.List;
/**
 * 文 件 名: UserCourseBean
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/3/23
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class UserCourseDetailBean {

    /**
     * id : 2979
     * shop_id : 94983847
     * parent_shop_id : 31
     * lesson_id : 346
     * start_time : 1521554400
     * end_time : 1521556200
     * classroom_id : 196
     * number : 111
     * create_time : 1521083406
     * update_time : 1521441722
     * is_deleted : 0
     * cancel_use_time : 1
     * course_color : null
     * limit_number : 0
     * limit_cencel_time : 0
     * is_pay : 2
     * price : 0.01
     * is_recommend : 1
     * start : 1521554400
     * end : 1521556200
     * lessonName : 热瑜伽
     * lessonId : 346
     * lessonTime : 30
     * lessonImg : http://testyogabook.hq-xl.com/static/images/lesson/5aa9e38d0322c.jpg
     * introduce : 测试热瑜伽
     * lessonContent :
     * classroomName : 1班
     * classroomId : 196
     * allAppNum : 3
     * appFromYueKeNum : 3
     * appFromQuanYiNum : 0
     * tutorList : [{"id":232,"name":"张老师"}]
     * isExpired : 1
     * quanYiIsFull : 1
     * quanYiCanAppNum : 0
     * yueKeCanAppNum : 108
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
