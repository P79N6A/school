package com.idyoga.yoga.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

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
public class UserCourseBean implements Parcelable, Comparable<UserCourseBean> {

    /**
     * appointmentId : 25969
     * status : 0
     * confirmStatus : 1
     * courseId : 33815
     * start : 1534247100
     * end : 1534251600
     * classroomName : 天府路店
     * lessonName : 纤体瑜伽
     * lessonImg : http://testyogabook.hq-xl.com/static/images/lesson/5b45a672e7730.jpg
     * shopName : 喜玛瑜伽生活馆
     * shopAddress : 广州市天河区天府路华建大厦5楼空中花园
     * shop_id : 61849626
     * tutorName : 贤贤
     */

    /*{
        "id": 112882,
        "time": 1552372200,
        "lessonName": "力量与柔软",
        "lessonImg": "http://testyogabook.hq-xl.com/static/images/lesson/5c6f59b0826c4.jpg",
        "shop_id": 45134348,
        "shopName": "鹭鹭瑜伽文化健康管理中心",
        "state": "",
        "lessonType": 1
    }*/
    private int id;
    private long time;
    private String state;
    private int lessonType;

    private int appointmentId;
    private int status;
    private int confirmStatus;
    private int courseId;
    private long start;
    private long end;
    private String classroomName;
    private String lessonName;
    private String lessonImg;
    private String shopName;
    private String shopAddress;
    private int shop_id;
    private String tutorName;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getLessonType() {
        return lessonType;
    }

    public void setLessonType(int lessonType) {
        this.lessonType = lessonType;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getConfirmStatus() {
        return confirmStatus;
    }

    public void setConfirmStatus(int confirmStatus) {
        this.confirmStatus = confirmStatus;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public long getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
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

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.appointmentId);
        dest.writeInt(this.status);
        dest.writeInt(this.confirmStatus);
        dest.writeInt(this.courseId);
        dest.writeLong(this.start);
        dest.writeLong(this.end);
        dest.writeString(this.classroomName);
        dest.writeString(this.lessonName);
        dest.writeString(this.lessonImg);
        dest.writeString(this.shopName);
        dest.writeString(this.shopAddress);
        dest.writeInt(this.shop_id);
        dest.writeString(this.tutorName);
    }

    public UserCourseBean() {
    }

    protected UserCourseBean(Parcel in) {
        this.appointmentId = in.readInt();
        this.status = in.readInt();
        this.confirmStatus = in.readInt();
        this.courseId = in.readInt();
        this.start = in.readInt();
        this.end = in.readInt();
        this.classroomName = in.readString();
        this.lessonName = in.readString();
        this.lessonImg = in.readString();
        this.shopName = in.readString();
        this.shopAddress = in.readString();
        this.shop_id = in.readInt();
        this.tutorName = in.readString();
    }

    public static final Creator<UserCourseBean> CREATOR = new Creator<UserCourseBean>() {
        @Override
        public UserCourseBean createFromParcel(Parcel source) {
            return new UserCourseBean(source);
        }

        @Override
        public UserCourseBean[] newArray(int size) {
            return new UserCourseBean[size];
        }
    };

    @Override
    public int compareTo(@NonNull UserCourseBean bean) {
        return 0;
    }
}
