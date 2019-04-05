package com.idyoga.yoga.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author weilgu
 * @time 2019/3/22 10:15
 * @des ${TODO}
 */

public class CoureseDetailInfo implements MultiItemEntity{


    /**
     * id : 7669
     * name : 亚健康修复课
     * time : 60
     * image : hsttps://admin.idyoga.cn/static/images/lesson/5c0f78942817e.jpg
     * introduce :
     * content : null
     * detail_image : https://admin.idyoga.cn/static/images/lesson_detail_image/5c0f78942817e.jpg
     * sex_limit : 1
     * url : https://p.idyoga.cn/mall/lesson/lessonDetail?shopId=25&lessonId=7669
     * venueServiceItem : WIFI,停车场,物品寄存,更衣室,练习道具
     * appointmentNum : null
     * isCourse : 0
     * lessonList : []
     * Banner : []
     * label : []
     */

    private int id;
    private String name;
    private int time;
    private String image;
    private String introduce;
    private String content;
    private String detail_image;
    private int sex_limit;
    private String url;
    private String venueServiceItem;
    private Object appointmentNum;
    private int isCourse;
    private List<LessonList> lessonList;
    private List<Banner> Banner;
    private List<Label> label;
    private int itemType;

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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDetail_image() {
        return detail_image;
    }

    public void setDetail_image(String detail_image) {
        this.detail_image = detail_image;
    }

    public int getSex_limit() {
        return sex_limit;
    }

    public void setSex_limit(int sex_limit) {
        this.sex_limit = sex_limit;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVenueServiceItem() {
        return venueServiceItem;
    }

    public void setVenueServiceItem(String venueServiceItem) {
        this.venueServiceItem = venueServiceItem;
    }

    public Object getAppointmentNum() {
        return appointmentNum;
    }

    public void setAppointmentNum(Object appointmentNum) {
        this.appointmentNum = appointmentNum;
    }

    public int getIsCourse() {
        return isCourse;
    }

    public void setIsCourse(int isCourse) {
        this.isCourse = isCourse;
    }

    public List<LessonList> getLessonList() {
        return lessonList;
    }

    public void setLessonList(List<LessonList> lessonList) {
        this.lessonList = lessonList;
    }

    public List<Banner> getBanner() {
        return Banner;
    }

    public void setBanner(List<Banner> Banner) {
        this.Banner = Banner;
    }

    public List<Label> getLabel() {
        return label;
    }

    public void setLabel(List<Label> label) {
        this.label = label;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType){
        this.itemType = itemType;
    }

    public static class Label implements MultiItemEntity{
        /*{
                "name": "性情调和瑜伽",----功效名
                "image_url": "https://platform.hq-xl.com/label_classify_image/2018/07/5b42d4f640d04.jpg",----------------功效图
                "description": ""----------功效介绍
            }*/
        private String name;
        private String image_url;
        private String description;
        private int itemType;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public int getItemType() {
            return itemType;
        }

        public void setItemType(int itemType) {
            this.itemType = itemType;
        }
    }

    public static class Banner implements MultiItemEntity{
        /*{
                "image_url": "http://testyogabook.hq-xl.com/shop/lesson_images/19287727/5c7e3f429b092.jpg"
            }*/
        private String image_url;
        private int itemType;
        private List<String> images;

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        @Override
        public int getItemType() {
            return itemType;
        }

        public void setItemType(int itemType) {
            this.itemType = itemType;
        }
    }

    public static class LessonList implements MultiItemEntity{
        /*
        * {
            "id": 114,
            "name": "艾扬格瑜伽",
            "image": "https://admin.idyoga.cn/static/images/lesson/5ae295516ef04.jpg",
            "introduce": "不错",
            "appointmentNum": null,
            "isCourse": 0
            }
        * */
        private int id;
        private String name;
        private String image;
        private String introduce;
        private String appointmentNum;
        private int isCourse;
        private int itemType;

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getAppointmentNum() {
            return appointmentNum;
        }

        public void setAppointmentNum(String appointmentNum) {
            this.appointmentNum = appointmentNum;
        }

        public int getIsCourse() {
            return isCourse;
        }

        public void setIsCourse(int isCourse) {
            this.isCourse = isCourse;
        }

        @Override
        public int getItemType() {
            return itemType;
        }

        public void setItemType(int itemType) {
            this.itemType = itemType;
        }
    }
}
