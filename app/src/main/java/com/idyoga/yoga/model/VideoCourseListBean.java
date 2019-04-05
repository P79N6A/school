package com.idyoga.yoga.model;

import java.util.List;

/**
 * 文 件 名: VideoCourseListBean
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/5/28
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class VideoCourseListBean {


    /**
     * id : 32
     * title : 测试视频2
     * time : 12
     * grade : 0
     * deplete : 90
     * description : 减肥
     * image_url : https://platform.hq-xl.com/video_image/2018/05/5b077bfab9ab0.jpg
     * is_free : 1
     * price : 0.01
     * number : 12
     * efficacy : 减肥
     * video_label : [{"id":15,"name":"视频标签3"},{"id":14,"name":"视频标签2"},{"id":13,"name":"视频标签1"}]
     */

    private int id;
    private String title;
    private int time;
    private int grade;
    private int deplete;
    private String description;
    private String image_url;
    private int is_free;
    private String price;
    private int number;
    private String efficacy;
    private List<VideoLabelBean> video_label;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getDeplete() {
        return deplete;
    }

    public void setDeplete(int deplete) {
        this.deplete = deplete;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getIs_free() {
        return is_free;
    }

    public void setIs_free(int is_free) {
        this.is_free = is_free;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getEfficacy() {
        return efficacy;
    }

    public void setEfficacy(String efficacy) {
        this.efficacy = efficacy;
    }

    public List<VideoLabelBean> getVideo_label() {
        return video_label;
    }

    public void setVideo_label(List<VideoLabelBean> video_label) {
        this.video_label = video_label;
    }

    public static class VideoLabelBean {
        /**
         * id : 15
         * name : 视频标签3
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


