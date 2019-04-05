package com.idyoga.yoga.model;

import java.util.List;

/**
 * @author weilgu
 * @time 2019/3/21 10:50
 * @des ${TODO}
 */

public class VideoHomeData {

    private List<HomePageData.BannerBean> banner;
    private List<VideoBean> video;

    public List<HomePageData.BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<HomePageData.BannerBean> banner) {
        this.banner = banner;
    }

    public List<VideoBean> getVideo() {
        return video;
    }

    public void setVideo(List<VideoBean> video) {
        this.video = video;
    }

    public static class VideoBean extends BaseCourseBean{
        /**
         * id : 65
         * name : 名师系列
         * type : 3
         * videoList : []
         */

        private int id;
        private String name;
        private int type;
        private List<VideoList> videoList;

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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public List<VideoList> getVideoList() {
            return videoList;
        }

        public void setVideoList(List<VideoList> videoList) {
            this.videoList = videoList;
        }
    }

    public static class VideoList {
        /**
         * "videoId": 473,
         * "image_url": "https://czb.idyoga.cn/upload/images/190306/5c7f31ae55c48.png",
         * "title": "壳壳"
         */
        private int videoId;
        private String image_url;
        private String title;

        public int getVideoId() {
            return videoId;
        }

        public void setVideoId(int videoId) {
            this.videoId = videoId;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
