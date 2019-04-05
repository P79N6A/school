package com.idyoga.yoga.model;

import java.util.List;

/**
 * 文 件 名: ShopTutorBean
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/22
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class ShopTutorBean {

    /**
     * id : 794
     * shop_id : 19287727
     * name : 现联林导师
     * mobile : 15699899987
     * mailbox : 020@163.com
     * sex : 1
     * image :
     * introduce : 现联的瑜伽导师
     * is_deleted : 0
     * create_time : 1526352256
     * update_time : 1526352256
     * experience : null
     * shop_name : 现联瑜伽馆
     * tutorLabel : [{"name":"签约导师"},{"name":"海外导师"},{"name":"金牌导师"}]
     * lesson : [{"id":580,"lesson_id":104,"lessonName":"林大师蛇形瑜伽课"},{"id":581,"lesson_id":120,"lessonName":"林大师瑜伽课测试"},{"id":582,"lesson_id":2045,"lessonName":"林大师测试课程"}]
     * mainstream : [{"id":683,"mainstream_id":6,"mainstreamName":"吠陀瑜伽"},{"id":684,"mainstream_id":10,"mainstreamName":"奉爱瑜伽"},{"id":685,"mainstream_id":11,"mainstreamName":"王瑜伽"},{"id":686,"mainstream_id":15,"mainstreamName":"吉瓦穆克提瑜伽"},{"id":687,"mainstream_id":17,"mainstreamName":"艾扬格瑜伽"}]
     */

    private int id;
    private int shop_id;
    private String name;
    private String mobile;
    private String mailbox;
    private int sex;
    private String image;
    private String introduce;
    private int is_deleted;
    private int create_time;
    private int update_time;
    private Object experience;
    private String shop_name;
    private List<TutorLabelBean> tutorLabel;
    private List<LessonBean> lesson;
    private List<MainstreamBean> mainstream;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
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

    public int getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(int is_deleted) {
        this.is_deleted = is_deleted;
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

    public Object getExperience() {
        return experience;
    }

    public void setExperience(Object experience) {
        this.experience = experience;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public List<TutorLabelBean> getTutorLabel() {
        return tutorLabel;
    }

    public void setTutorLabel(List<TutorLabelBean> tutorLabel) {
        this.tutorLabel = tutorLabel;
    }

    public List<LessonBean> getLesson() {
        return lesson;
    }

    public void setLesson(List<LessonBean> lesson) {
        this.lesson = lesson;
    }

    public List<MainstreamBean> getMainstream() {
        return mainstream;
    }

    public void setMainstream(List<MainstreamBean> mainstream) {
        this.mainstream = mainstream;
    }

    public static class TutorLabelBean {
        /**
         * name : 签约导师
         */

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class LessonBean {
        /**
         * id : 580
         * lesson_id : 104
         * lessonName : 林大师蛇形瑜伽课
         */

        private int id;
        private int lesson_id;
        private String lessonName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLesson_id() {
            return lesson_id;
        }

        public void setLesson_id(int lesson_id) {
            this.lesson_id = lesson_id;
        }

        public String getLessonName() {
            return lessonName;
        }

        public void setLessonName(String lessonName) {
            this.lessonName = lessonName;
        }
    }

    public static class MainstreamBean {
        /**
         * id : 683
         * mainstream_id : 6
         * mainstreamName : 吠陀瑜伽
         */

        private int id;
        private int mainstream_id;
        private String mainstreamName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMainstream_id() {
            return mainstream_id;
        }

        public void setMainstream_id(int mainstream_id) {
            this.mainstream_id = mainstream_id;
        }

        public String getMainstreamName() {
            return mainstreamName;
        }

        public void setMainstreamName(String mainstreamName) {
            this.mainstreamName = mainstreamName;
        }
    }
}
