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
public class HomeSubjectBean {

    /**
     * id : 8
     * name : 体式动作 - 入门
     * type : 1
     * video : [{"id":24,"title":"体式入门动作基础系列三","image_url":"https://platform.hq-xl.com/video_image/2018/03/5ab8afa63cb16.jpg","number":3069},{"id":23,"title":"体式入门动作基础系列二","image_url":"https://platform.hq-xl.com/video_image/2018/03/5ab8af8779428.jpg","number":5589},{"id":22,"title":"体式入门动作基础系列一","image_url":"https://platform.hq-xl.com/video_image/2018/03/5ab8af5b5d042.jpg","number":9956}]
     */

    private int id;
    private String name;
    private int type;
    private List<VideoBean> video;

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

    public List<VideoBean> getVideo() {
        return video;
    }

    public void setVideo(List<VideoBean> video) {
        this.video = video;
    }

    public static class VideoBean {
        /**
         * id : 24
         * title : 体式入门动作基础系列三
         * image_url : https://platform.hq-xl.com/video_image/2018/03/5ab8afa63cb16.jpg
         * number : 3069
         */

        private int id;
        private String title;
        private String image_url;
        private int number;

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

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }
    }
}
