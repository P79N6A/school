package com.idyoga.yoga.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
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
public class HomeSubjectItemBean implements Parcelable {

    /**
     * id : 24
     * title : 体式入门动作基础系列三
     * time : 30
     * grade : 简单
     * deplete : 30
     * description : 体式入门动作基础系列三
     * image_url : https://platform.hq-xl.com/video_image/2018/03/5ab8afa63cb16.jpg
     * is_free : 0
     * price : 0.00
     * number : null
     * efficacy : null
     * videoGroup : [{"id":15,"title":"part2-小节名儿","videoGroupAction":[{"id":29,"title":"动作三","number":30,"rest_time":30,"image_url":"https://platform.hq-xl.com/video_image/2018/03/5ab8b9b792c63.jpg","gif_url":"https://platform.hq-xl.com/video_image/2018/03/5ab8b9b792d29.gif","video":"9","content":"动作三","tutor_id":null,"tutor_name":null},{"id":28,"title":"动作2","number":30,"rest_time":30,"image_url":"https://platform.hq-xl.com/video_image/2018/03/5ab8b9967e8d8.jpg","gif_url":"https://platform.hq-xl.com/video_image/2018/03/5ab8b9967e97e.gif","video":"9","content":"动作2","tutor_id":null,"tutor_name":null},{"id":27,"title":"动作1","number":30,"rest_time":30,"image_url":"https://platform.hq-xl.com/video_image/2018/03/5ab8b941218e9.jpg","gif_url":"https://platform.hq-xl.com/video_image/2018/03/5ab8b94121995.gif","video":"5","content":"动作1","tutor_id":null,"tutor_name":null}]},{"id":14,"title":"part1-小节名儿","videoGroupAction":[{"id":26,"title":"小节名儿三","number":30,"rest_time":30,"image_url":"https://platform.hq-xl.com/video_image/2018/03/5ab8b8c84c1b1.jpg","gif_url":"https://platform.hq-xl.com/video_image/2018/03/5ab8b8c84c28b.gif","video":"6","content":"小节名儿三","tutor_id":null,"tutor_name":null},{"id":25,"title":"小节名儿二","number":30,"rest_time":30,"image_url":"https://platform.hq-xl.com/video_image/2018/03/5ab8b89658005.jpg","gif_url":"https://platform.hq-xl.com/video_image/2018/03/5ab8b896580ae.gif","video":"3","content":"小节名儿二","tutor_id":null,"tutor_name":null},{"id":24,"title":"小节明儿一","number":30,"rest_time":30,"image_url":"https://platform.hq-xl.com/video_image/2018/03/5ab8b07dd5838.jpg","gif_url":"https://platform.hq-xl.com/video_image/2018/03/5ab8b07dd58de.gif","video":"3","content":"小节明儿一","tutor_id":null,"tutor_name":null}]}]
     * equipment : [{"id":6,"image_url":"https://platform.hq-xl.com/video_image/2018/03/5ab9f3e4826fa.png","url":"https://platform.hq-xl.com/video_image/2018/03/5ab9f3cece56f.png"},{"id":7,"image_url":"https://platform.hq-xl.com/video_image/2018/03/5ab9f3e9a8738.png","url":"https://platform.hq-xl.com/video_image/2018/03/5ab9f3cece56f.png"},{"id":8,"image_url":"https://platform.hq-xl.com/video_image/2018/03/5ab9f3f004a33.png","url":"https://platform.hq-xl.com/video_image/2018/03/5ab9f3cece56f.png"},{"id":9,"image_url":"https://platform.hq-xl.com/video_image/2018/03/5ab9f3f8170c9.png","url":"https://platform.hq-xl.com/video_image/2018/03/5ab9f3cece56f.png"},{"id":10,"image_url":"https://platform.hq-xl.com/video_image/2018/03/5ab9f3fd008a6.png","url":"https://platform.hq-xl.com/video_image/2018/03/5ab9f3cece56f.png"}]
     * headPortrait : [{"image_url":"http://wx.qlogo.cn/mmopen/vi_32/vAlZclHQwGr60W82JbiadkCHia32IWy9RDY3QUBwkBCypRaiamWgIjnp1pFrwKPCWM6KOID71zykRl2nApDFF7BVg/0"},{"image_url":"http://wx.qlogo.cn/mmopen/vi_32/3Ar5tUOsYKA3aK3gVYycI4SRJJ0AwfZFsyUChmCobXcG8nFkdYFmibHJKRptWQQuh0zTLNAmzPszbyoibaXHNwgQ/0"},{"image_url":"http://wx.qlogo.cn/mmopen/vi_32/lG0e7xuEGzMJOVM6iaEAHfMAj4d9kFyO9YFNwr98yD7VBCibJAUcLydh4erdp3MRib6T0fKpdF7jlxXj5rwn76prQ/0"},{"image_url":"http://wx.qlogo.cn/mmopen/vi_32/3Ar5tUOsYKA3aK3gVYycI4SRJJ0AwfZFsyUChmCobXcG8nFkdYFmibHJKRptWQQuh0zTLNAmzPszbyoibaXHNwgQ/0"},{"image_url":"http://wx.qlogo.cn/mmopen/vi_32/lG0e7xuEGzMJOVM6iaEAHfMAj4d9kFyO9YFNwr98yD7VBCibJAUcLydh4erdp3MRib6T0fKpdF7jlxXj5rwn76prQ/0"},{"image_url":"http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLic3Iqqnfu65bgSCmeD3DCHpTGXdZTD7fve7BZg2KJZ2YJSD64KxTiaIMEfedxS0XPxTjC0UhtfibwQ/0"},{"image_url":"http://wx.qlogo.cn/mmopen/vi_32/ZyWuPiaKa3p1s9mUM3wDSlrB7Z6icnB36xvSTiaYH9nD7h6R6yYUp7TkSsz3m54oCKCcZ0f5S6rENrE6RntG9gqkw/0"},{"image_url":"http://wx.qlogo.cn/mmopen/vi_32/vAlZclHQwGr60W82JbiadkCHia32IWy9RDY3QUBwkBCypRaiamWgIjnp1pFrwKPCWM6KOID71zykRl2nApDFF7BVg/0"}]
     */

    private int id;
    private String title;
    private int time;
    private String grade;
    private int deplete;
    private String description;
    private String image_url;
    private int is_free;
    private int is_order;
    private String price;
    private String number;
    private String efficacy;
    private List<VideoGroupBean> videoGroup;
    private List<EquipmentBean> equipment;
    private List<HeadPortraitBean> headPortrait;

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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
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


    public void setIs_order(int is_order) {
        this.is_order = is_order;
    }

    public int getIs_order() {
        return is_order;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEfficacy() {
        return efficacy;
    }

    public void setEfficacy(String efficacy) {
        this.efficacy = efficacy;
    }

    public List<VideoGroupBean> getVideoGroup() {
        return videoGroup;
    }

    public void setVideoGroup(List<VideoGroupBean> videoGroup) {
        this.videoGroup = videoGroup;
    }

    public List<EquipmentBean> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<EquipmentBean> equipment) {
        this.equipment = equipment;
    }

    public List<HeadPortraitBean> getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(List<HeadPortraitBean> headPortrait) {
        this.headPortrait = headPortrait;
    }

    public static class VideoGroupBean {
        /**
         * id : 15
         * title : part2-小节名儿
         * videoGroupAction : [{"id":29,"title":"动作三","number":30,"rest_time":30,"image_url":"https://platform.hq-xl.com/video_image/2018/03/5ab8b9b792c63.jpg","gif_url":"https://platform.hq-xl.com/video_image/2018/03/5ab8b9b792d29.gif","video":"9","content":"动作三","tutor_id":null,"tutor_name":null},{"id":28,"title":"动作2","number":30,"rest_time":30,"image_url":"https://platform.hq-xl.com/video_image/2018/03/5ab8b9967e8d8.jpg","gif_url":"https://platform.hq-xl.com/video_image/2018/03/5ab8b9967e97e.gif","video":"9","content":"动作2","tutor_id":null,"tutor_name":null},{"id":27,"title":"动作1","number":30,"rest_time":30,"image_url":"https://platform.hq-xl.com/video_image/2018/03/5ab8b941218e9.jpg","gif_url":"https://platform.hq-xl.com/video_image/2018/03/5ab8b94121995.gif","video":"5","content":"动作1","tutor_id":null,"tutor_name":null}]
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
             * tutor_id : null
             * tutor_name : null
             */

            private int id;
            private String title;
            private int number;
            private int rest_time;
            private String image_url;
            private String gif_url;
            private String video;
            private String content;
            private Object tutor_id;
            private Object tutor_name;

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

            public Object getTutor_id() {
                return tutor_id;
            }

            public void setTutor_id(Object tutor_id) {
                this.tutor_id = tutor_id;
            }

            public Object getTutor_name() {
                return tutor_name;
            }

            public void setTutor_name(Object tutor_name) {
                this.tutor_name = tutor_name;
            }
        }
    }

    public static class EquipmentBean {
        /**
         * id : 6
         * image_url : https://platform.hq-xl.com/video_image/2018/03/5ab9f3e4826fa.png
         * url : https://platform.hq-xl.com/video_image/2018/03/5ab9f3cece56f.png
         */

        private int id;
        private String image_url;
        private String url;

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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class HeadPortraitBean {
        /**
         * image_url : http://wx.qlogo.cn/mmopen/vi_32/vAlZclHQwGr60W82JbiadkCHia32IWy9RDY3QUBwkBCypRaiamWgIjnp1pFrwKPCWM6KOID71zykRl2nApDFF7BVg/0
         */

        private String image_url;

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }
    }

    @Override
    public String toString() {
        return "HomeSubjectItemBean{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", time=" + time +
                ", grade='" + grade + '\'' +
                ", deplete=" + deplete +
                ", description='" + description + '\'' +
                ", image_url='" + image_url + '\'' +
                ", is_free=" + is_free +
                ", price='" + price + '\'' +
                ", number=" + number +
                ", efficacy=" + efficacy +
                ", videoGroup=" + videoGroup +
                ", equipment=" + equipment +
                ", headPortrait=" + headPortrait +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeInt(this.time);
        dest.writeString(this.grade);
        dest.writeInt(this.deplete);
        dest.writeString(this.description);
        dest.writeString(this.image_url);
        dest.writeInt(this.is_free);
        dest.writeString(this.price);
        dest.writeString(this.number);
        dest.writeString(this.efficacy);
        dest.writeList(this.videoGroup);
        dest.writeList(this.equipment);
        dest.writeList(this.headPortrait);
    }

    public HomeSubjectItemBean() {
    }

    protected HomeSubjectItemBean(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.time = in.readInt();
        this.grade = in.readString();
        this.deplete = in.readInt();
        this.description = in.readString();
        this.image_url = in.readString();
        this.is_free = in.readInt();
        this.price = in.readString();
        this.number = in.readString();
        this.efficacy = in.readString();
        this.videoGroup = new ArrayList<VideoGroupBean>();
        in.readList(this.videoGroup, VideoGroupBean.class.getClassLoader());
        this.equipment = new ArrayList<EquipmentBean>();
        in.readList(this.equipment, EquipmentBean.class.getClassLoader());
        this.headPortrait = new ArrayList<HeadPortraitBean>();
        in.readList(this.headPortrait, HeadPortraitBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<HomeSubjectItemBean> CREATOR = new Parcelable.Creator<HomeSubjectItemBean>() {
        @Override
        public HomeSubjectItemBean createFromParcel(Parcel source) {
            return new HomeSubjectItemBean(source);
        }

        @Override
        public HomeSubjectItemBean[] newArray(int size) {
            return new HomeSubjectItemBean[size];
        }
    };
}