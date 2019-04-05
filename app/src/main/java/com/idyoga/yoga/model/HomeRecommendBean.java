package com.idyoga.yoga.model;

import java.util.List;

public class HomeRecommendBean {


    private List<BannerListBean> BannerList;
    private List<LabelBean> label;
    private List<VideoListBean> VideoList;

    public List<BannerListBean> getBannerList() {
        return BannerList;
    }

    public void setBannerList(List<BannerListBean> BannerList) {
        this.BannerList = BannerList;
    }

    public List<LabelBean> getLabel() {
        return label;
    }

    public void setLabel(List<LabelBean> label) {
        this.label = label;
    }

    public List<VideoListBean> getVideoList() {
        return VideoList;
    }

    public void setVideoList(List<VideoListBean> VideoList) {
        this.VideoList = VideoList;
    }

    @Override
    public String toString() {
        return "HomeRecommendBean{" +
                "BannerList=" + BannerList +
                ", label=" + label +
                ", VideoList=" + VideoList +
                '}';
    }

    public static class BannerListBean {
        /**
         * id : 25
         * image : https://platform.hq-xl.com/banner_thumb/2018/06/5b1ba2dceb23f.jpg
         * url : www.baidu.com
         */

        private int id;
        private String image;
        private String url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "BannerListBean{" +
                    "id=" + id +
                    ", image='" + image + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    public static class LabelBean {
        /**
         * id : 1
         * image_url : https://platform.hq-xl.com/banner_thumb/2018/05/5afa8d9470a0d.jpg
         * url :
         * name : 快速问诊
         */

        private int id;
        private String image_url;
        private String name;
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "LabelBean{" +
                    "id=" + id +
                    ", image_url='" + image_url + '\'' +
                    ", name='" + name + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    public static class VideoListBean {

        /**
         * id : 23
         * name : 系列视频
         * type : 6
         * videoLseeon : [{"id":51,"title":"体式入门动作基础系列十七","number":300,"image_url":"https://platform.hq-xl.com/video_image/2018/07/5b4d4dafe2dbf.jpg","tutor_name":"陈老师","is_free":0},{"id":50,"title":"体式入门动作基础系列十六","number":666,"image_url":"https://platform.hq-xl.com/video_image/2018/07/5b4d4d7362cc1.jpg","tutor_name":"陈老师","is_free":0},{"id":49,"title":"体式入门动作基础系列十五","number":233,"image_url":"https://platform.hq-xl.com/video_image/2018/07/5b4d4d47b434e.jpg","tutor_name":"陈老师","is_free":0},{"id":48,"title":"体式入门动作基础系列十四","number":300,"image_url":"https://platform.hq-xl.com/video_image/2018/07/5b4d4cfe780ed.jpg","tutor_name":"陈老师","is_free":0},{"id":47,"title":"体式入门动作基础系列十三","number":666,"image_url":"https://platform.hq-xl.com/video_image/2018/07/5b4d4cded9b74.jpg","tutor_name":"陈老师","is_free":0}]
         */

        private int id;
        private String name;
        private int type;
        private List<VideoLseeonBean> videoLseeon;
        private boolean isLabel;

        public boolean isLabel() {
            return isLabel;
        }

        public void setLabel(boolean label) {
            isLabel = label;
        }

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

        public List<VideoLseeonBean> getVideoLseeon() {
            return videoLseeon;
        }

        public void setVideoLseeon(List<VideoLseeonBean> videoLseeon) {
            this.videoLseeon = videoLseeon;
        }

        @Override
        public String toString() {
            return "VideoListBean{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", type=" + type +
                    ", videoLseeon=" + videoLseeon +
                    '}';
        }

        public static class VideoLseeonBean {

            /**
             * id : 49
             * title : 体式入门动作基础系列十五
             * number : 233
             * image_url : https://platform.hq-xl.com/video_image/2018/07/5b4d4d47b434e.jpg
             * tutor_name : 陈老师
             * is_free : 0
             * time : 1
             */

            private int id;
            private String title;
            private int number;
            private String image_url;
            private String tutor_name;
            private int is_free;
            private int time;

            private int itemType;


            public int getItemType() {
                return itemType;
            }

            public void setItemType(int itemType) {
                this.itemType = itemType;
            }

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

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public String getTutor_name() {
                return tutor_name;
            }

            public void setTutor_name(String tutor_name) {
                this.tutor_name = tutor_name;
            }

            public int getIs_free() {
                return is_free;
            }

            public void setIs_free(int is_free) {
                this.is_free = is_free;
            }

            public int getTime() {
                return time;
            }

            public void setTime(int time) {
                this.time = time;
            }

            @Override
            public String toString() {
                return "VideoLseeonBean{" +
                        "id=" + id +
                        ", title='" + title + '\'' +
                        ", number=" + number +
                        ", image_url='" + image_url + '\'' +
                        ", tutor_name='" + tutor_name + '\'' +
                        ", is_free=" + is_free +
                        ", time=" + time +
                        ", itemType=" + itemType +
                        '}';
            }
        }
    }
}
