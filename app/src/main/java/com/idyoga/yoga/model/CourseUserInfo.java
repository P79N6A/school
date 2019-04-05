package com.idyoga.yoga.model;

import java.util.List;

/**
 * @author weilgu
 * @time 2019/3/26 11:54
 * @des ${TODO}
 */

public class CourseUserInfo {


    /**
     * shopInfo : {"shopId":19287727,"shopName":"现联瑜伽馆","shopAddress":"广州天河","shopLong":"0.0000000","shopLat":"0.0000000"}
     * lessonInfo : {"lessonId":104,"lessonName":"林大师蛇形瑜伽课","lessonImage":"https://admin.idyoga.cn/static/images/lesson/5ae2956e5cb82.jpg","lessonDetailImage":null,"lessonTime":30}
     * userInfo : {"userId":46860,"userName":"13300000000","userMobile":"13300000000","platformCardList":[]}
     * isSetCourse : 0
     */

    private ShopInfoBean shopInfo;
    private LessonInfoBean lessonInfo;
    private UserInfoBean userInfo;
    private int isSetCourse;

    public ShopInfoBean getShopInfo() {
        return shopInfo;
    }

    public void setShopInfo(ShopInfoBean shopInfo) {
        this.shopInfo = shopInfo;
    }

    public LessonInfoBean getLessonInfo() {
        return lessonInfo;
    }

    public void setLessonInfo(LessonInfoBean lessonInfo) {
        this.lessonInfo = lessonInfo;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public int getIsSetCourse() {
        return isSetCourse;
    }

    public void setIsSetCourse(int isSetCourse) {
        this.isSetCourse = isSetCourse;
    }

    public static class ShopInfoBean {
        /**
         * shopId : 19287727
         * shopName : 现联瑜伽馆
         * shopAddress : 广州天河
         * shopLong : 0.0000000
         * shopLat : 0.0000000
         */

        private int shopId;
        private String shopName;
        private String shopAddress;
        private String shopLong;
        private String shopLat;

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

        public String getShopAddress() {
            return shopAddress;
        }

        public void setShopAddress(String shopAddress) {
            this.shopAddress = shopAddress;
        }

        public String getShopLong() {
            return shopLong;
        }

        public void setShopLong(String shopLong) {
            this.shopLong = shopLong;
        }

        public String getShopLat() {
            return shopLat;
        }

        public void setShopLat(String shopLat) {
            this.shopLat = shopLat;
        }
    }

    public static class LessonInfoBean {
        /**
         * lessonId : 104
         * lessonName : 林大师蛇形瑜伽课
         * lessonImage : https://admin.idyoga.cn/static/images/lesson/5ae2956e5cb82.jpg
         * lessonDetailImage : null
         * lessonTime : 30
         */

        private int lessonId;
        private String lessonName;
        private String lessonImage;
        private Object lessonDetailImage;
        private int lessonTime;

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

        public String getLessonImage() {
            return lessonImage;
        }

        public void setLessonImage(String lessonImage) {
            this.lessonImage = lessonImage;
        }

        public Object getLessonDetailImage() {
            return lessonDetailImage;
        }

        public void setLessonDetailImage(Object lessonDetailImage) {
            this.lessonDetailImage = lessonDetailImage;
        }

        public int getLessonTime() {
            return lessonTime;
        }

        public void setLessonTime(int lessonTime) {
            this.lessonTime = lessonTime;
        }
    }

    public static class UserInfoBean {
        /**
         * userId : 46860
         * userName : 13300000000
         * userMobile : 13300000000
         * platformCardList : []
         */

        private int userId;
        private String userName;
        private String userMobile;
        private List<CardBean> platformCardList;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserMobile() {
            return userMobile;
        }

        public void setUserMobile(String userMobile) {
            this.userMobile = userMobile;
        }

        public List<CardBean> getPlatformCardList() {
            return platformCardList;
        }

        public void setPlatformCardList(List<CardBean> platformCardList) {
            this.platformCardList = platformCardList;
        }

        public class CardBean {
            /*{
                "card_name": "预制年卡",
                "card_category_id": 2,
                "id": 19456,
                "order_id": 327,
                "user_id": 45475,
                "card_id": 20,
                "expire_time": 1579155200,
                "start_time": 1547619200,
                "has_term": 12,
                "has_time": 99999,
                "valid_time": 99999,
                "status": 0,
                "remarks": null,
                "is_deleted": 0,
                "create_time": 1547619200,
                "update_time": 1547619200,
                "is_chain": 1,
                "type": 2,
                "annex_num": 0,
                "valid_annex_num": 0,
                "is_freeze": 0}*/
            private String card_name;
            private int card_category_id;
            private int id;
            private int order_id;
            private int user_id;
            private int card_id;
            private long expire_time;
            private int has_term;
            private int has_time;
            private int valid_time;
            private int status;
            private Object remarks;
            private int is_deleted;
            private long create_time;
            private long update_time;
            private int is_chain;
            private int type;
            private int annex_num;
            private int valid_annex_num;
            private int is_freeze;
            private boolean isSelect;

            public boolean isSelect() {
                return isSelect;
            }

            public void setSelect(boolean select) {
                isSelect = select;
            }

            public String getCard_name() {
                return card_name;
            }

            public void setCard_name(String card_name) {
                this.card_name = card_name;
            }

            public int getCard_category_id() {
                return card_category_id;
            }

            public void setCard_category_id(int card_category_id) {
                this.card_category_id = card_category_id;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getOrder_id() {
                return order_id;
            }

            public void setOrder_id(int order_id) {
                this.order_id = order_id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public int getCard_id() {
                return card_id;
            }

            public void setCard_id(int card_id) {
                this.card_id = card_id;
            }

            public long getExpire_time() {
                return expire_time;
            }

            public void setExpire_time(long expire_time) {
                this.expire_time = expire_time;
            }

            public int getHas_term() {
                return has_term;
            }

            public void setHas_term(int has_term) {
                this.has_term = has_term;
            }

            public int getHas_time() {
                return has_time;
            }

            public void setHas_time(int has_time) {
                this.has_time = has_time;
            }

            public int getValid_time() {
                return valid_time;
            }

            public void setValid_time(int valid_time) {
                this.valid_time = valid_time;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public Object getRemarks() {
                return remarks;
            }

            public void setRemarks(Object remarks) {
                this.remarks = remarks;
            }

            public int getIs_deleted() {
                return is_deleted;
            }

            public void setIs_deleted(int is_deleted) {
                this.is_deleted = is_deleted;
            }

            public long getCreate_time() {
                return create_time;
            }

            public void setCreate_time(long create_time) {
                this.create_time = create_time;
            }

            public long getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(long update_time) {
                this.update_time = update_time;
            }

            public int getIs_chain() {
                return is_chain;
            }

            public void setIs_chain(int is_chain) {
                this.is_chain = is_chain;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getAnnex_num() {
                return annex_num;
            }

            public void setAnnex_num(int annex_num) {
                this.annex_num = annex_num;
            }

            public int getValid_annex_num() {
                return valid_annex_num;
            }

            public void setValid_annex_num(int valid_annex_num) {
                this.valid_annex_num = valid_annex_num;
            }

            public int getIs_freeze() {
                return is_freeze;
            }

            public void setIs_freeze(int is_freeze) {
                this.is_freeze = is_freeze;
            }
        }
    }
}
