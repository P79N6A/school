package com.idyoga.yoga.model;

import java.util.List;
/**
 * 文 件 名: HomeSubjectBean
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/3/26
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class CourseVideoListBean {

    /**
     * id : 15
     * title : part2-小节名儿
     * videoGroupAction : [{"id":29,"title":"动作三","number":30,"rest_time":30,"image_url":"https://platform.hq-xl.com/video_image/2018/03/5ab8b9b792c63.jpg","gif_url":"https://platform.hq-xl.com/video_image/2018/03/5ab8b9b792d29.gif","video":"9","content":"动作三"},{"id":28,"title":"动作2","number":30,"rest_time":30,"image_url":"https://platform.hq-xl.com/video_image/2018/03/5ab8b9967e8d8.jpg","gif_url":"https://platform.hq-xl.com/video_image/2018/03/5ab8b9967e97e.gif","video":"9","content":"动作2"},{"id":27,"title":"动作1","number":30,"rest_time":30,"image_url":"https://platform.hq-xl.com/video_image/2018/03/5ab8b941218e9.jpg","gif_url":"https://platform.hq-xl.com/video_image/2018/03/5ab8b94121995.gif","video":"5","content":"动作1"}]
     */

    private int id;
    private String title;
    private List<VideoGroupActionBean> videoGroupAction;

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

    public List<VideoGroupActionBean> getVideoGroupAction() {
        return videoGroupAction;
    }

    public void setVideoGroupAction(List<VideoGroupActionBean> videoGroupAction) {
        this.videoGroupAction = videoGroupAction;
    }

    public static class VideoGroupActionBean {
        /**
         * id : 29
         * title : 动作三
         * number : 30
         * rest_time : 30
         * image_url : https://platform.hq-xl.com/video_image/2018/03/5ab8b9b792c63.jpg
         * gif_url : https://platform.hq-xl.com/video_image/2018/03/5ab8b9b792d29.gif
         * video : 9
         * content : 动作三
         */

        private int id;
        private String title;
        private int number;
        private int rest_time;
        private String image_url;
        private String gif_url;
        private String video;
        private String content;

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

        public int getRest_time() {
            return rest_time;
        }

        public void setRest_time(int rest_time) {
            this.rest_time = rest_time;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getGif_url() {
            return gif_url;
        }

        public void setGif_url(String gif_url) {
            this.gif_url = gif_url;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
