package com.idyoga.yoga.model;

import java.util.List;

/**
 * 文 件 名: VideoCourseV2Bean
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/5/28
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class VideoCourseV2Bean {

    private List<BannerListBean> BannerList;
    private List<VideoListBean> VideoList;

    public List<BannerListBean> getBannerList() {
        return BannerList;
    }

    public void setBannerList(List<BannerListBean> BannerList) {
        this.BannerList = BannerList;
    }

    public List<VideoListBean> getVideoList() {
        return VideoList;
    }

    public void setVideoList(List<VideoListBean> VideoList) {
        this.VideoList = VideoList;
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
    }

    public static class VideoListBean {
        /**
         * id : 20
         * name : 名馆系列
         * type : 1
         * videoLseeon : [{"id":46,"title":"体式入门动作基础系列十三","number":233,"image_url":"https://platform.hq-xl.com/video_image/2018/07/5b4d4cb921dd4.jpg","tutor_name":"陈老师","is_free":0},{"id":23,"title":"体式入门动作基础系列二","number":100,"image_url":"https://platform.hq-xl.com/video_image/2018/03/5ab8af8779428.jpg","tutor_name":null,"is_free":0},{"id":22,"title":"体式入门动作基础系列一","number":100,"image_url":"https://platform.hq-xl.com/video_image/2018/03/5ab8af5b5d042.jpg","tutor_name":null,"is_free":0}]
         */

        private int id;
        private String name;
        private int type;
        private List<VideoLseeonBean> videoLseeon;

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

        public static class VideoLseeonBean {
            /**
             * id : 46
             * title : 体式入门动作基础系列十三
             * number : 233
             * image_url : https://platform.hq-xl.com/video_image/2018/07/5b4d4cb921dd4.jpg
             * tutor_name : 陈老师
             * is_free : 0
             */

            private int id;
            private String title;
            private int number;
            private String image_url;
            private String tutor_name;
            private int is_free;

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
        }
    }
}
