package com.idyoga.yoga.model;

import java.util.List;

/**
 * 文 件 名: UserCardDetailPermission
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/5/17
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class UserCardDetailPermission  {
    private List<CardCityListBean> cardCityList;
    private List<ShopLessonListBean> shopLessonList;

    public List<CardCityListBean> getCardCityList() {
        return cardCityList;
    }

    public void setCardCityList(List<CardCityListBean> cardCityList) {
        this.cardCityList = cardCityList;
    }

    public List<ShopLessonListBean> getShopLessonList() {
        return shopLessonList;
    }

    public void setShopLessonList(List<ShopLessonListBean> shopLessonList) {
        this.shopLessonList = shopLessonList;
    }

    public static class CardCityListBean {
        /**
         * id : 1
         * card_id : 1
         * city_id : 17
         * has_time : 0
         * platform_has_time : 0
         * is_deleted : 0
         * create_time : 1532053041
         * update_time : 1532053041
         * name : 广州
         */

        private int id;
        private int card_id;
        private int city_id;
        private int has_time;
        private int platform_has_time;
        private int is_deleted;
        private int create_time;
        private int update_time;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCard_id() {
            return card_id;
        }

        public void setCard_id(int card_id) {
            this.card_id = card_id;
        }

        public int getCity_id() {
            return city_id;
        }

        public void setCity_id(int city_id) {
            this.city_id = city_id;
        }

        public int getHas_time() {
            return has_time;
        }

        public void setHas_time(int has_time) {
            this.has_time = has_time;
        }

        public int getPlatform_has_time() {
            return platform_has_time;
        }

        public void setPlatform_has_time(int platform_has_time) {
            this.platform_has_time = platform_has_time;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class ShopLessonListBean {
        /**
         * shopId : 31
         * shopName : 老麦瑜伽馆
         * shopHasTime : 0
         * shopValidTime : 0
         * lesson : [{"lessonId":84,"lessonName":"热瑜伽"},{"lessonId":86,"lessonName":"1人课"},{"lessonId":92,"lessonName":"艾扬格瑜伽"},{"lessonId":95,"lessonName":"流瑜伽"},{"lessonId":98,"lessonName":"2222"},{"lessonId":99,"lessonName":"77"},{"lessonId":106,"lessonName":"啊啊啊啊"}]
         */

        private int shopId;
        private String shopName;
        private int shopHasTime;
        private int shopValidTime;
        private List<LessonBean> lesson;

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public int getShopHasTime() {
            return shopHasTime;
        }

        public void setShopHasTime(int shopHasTime) {
            this.shopHasTime = shopHasTime;
        }

        public int getShopValidTime() {
            return shopValidTime;
        }

        public void setShopValidTime(int shopValidTime) {
            this.shopValidTime = shopValidTime;
        }

        public List<LessonBean> getLesson() {
            return lesson;
        }

        public void setLesson(List<LessonBean> lesson) {
            this.lesson = lesson;
        }

        public static class LessonBean {
            /**
             * lessonId : 84
             * lessonName : 热瑜伽
             */

            private int lessonId;
            private String lessonName;

            public int getLessonId() {
                return lessonId;
            }

            public void setLessonId(int lessonId) {
                this.lessonId = lessonId;
            }

            public String getLessonName() {
                return lessonName;
            }

            public void setLessonName(String lessonName) {
                this.lessonName = lessonName;
            }
        }
    }
}
