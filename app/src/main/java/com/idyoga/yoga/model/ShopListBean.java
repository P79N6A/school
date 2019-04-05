package com.idyoga.yoga.model;

import java.util.List;

/**
 * 文 件 名: ShopListBean
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/22
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class ShopListBean {

    private List<BannerListBean> bannerList;
    private List<TagListBean> tagList;
    private List<ShopBean> shop;

    public List<BannerListBean> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<BannerListBean> bannerList) {
        this.bannerList = bannerList;
    }

    public List<TagListBean> getTagList() {
        return tagList;
    }

    public void setTagList(List<TagListBean> tagList) {
        this.tagList = tagList;
    }

    public List<ShopBean> getShop() {
        return shop;
    }

    public void setShop(List<ShopBean> shop) {
        this.shop = shop;
    }

    public static class BannerListBean {
        /**
         * id : 20
         * image : https://platform.hq-xl.com/banner_thumb/2018/06/5b1a1d47c94ef.jpg
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

    public static class TagListBean {
        /**
         * id : 8
         * image_url : https://platform.hq-xl.com/classify_image/2018/05/5afa80ae4ef05.jpg
         * name : 分类3
         */

        private int id;
        private String image_url;
        private String name;

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
    }

    public static class ShopBean {
        /**
         * id : 89236670
         * name : 美道瑜伽会馆(骏景花园馆)
         * address : 骏景花园南苑骏景路113号骏灏轩D座804(棠东BRT车站、棠下BRT车站)
         * logopath :
         * hotline : 18122340332
         * mobile : 18122340332
         * courese_start_time : null
         * compare : 无
         * shopCommentCount : 0
         * url : http://wxyoga.hq-xl.com/studio/detail?shopId=89236670&lat=&long=
         * course : [{"id":28776,"shop_id":63684083,"start_time":1546135200,"end_time":1546138800,"lessonName":"体验课","type":1}]
         * lesson : [{"id":3267,"lessonName":"强化训练课","image":"https://admin.idyoga.cn/static/images/lesson/5ae490507d677.jpg","time":120,"type":2,"start_time":"","end_time":""},{"id":3266,"lessonName":"全方位身体评估","image":"https://admin.idyoga.cn/static/images/lesson/5ae44896638e8.jpg","time":120,"type":2,"start_time":"","end_time":""},{"id":731,"lessonName":"空中瑜伽私教","image":"https://admin.idyoga.cn/static/images/lesson/5acd8155bc07f.jpg","time":90,"type":2,"start_time":"","end_time":""}]
         */

        private int id;
        private String name;
        private String address;
        private String logopath;
        private String hotline;
        private String mobile;
        private Object courese_start_time;
        private String compare;
        private String shopCommentCount;
        private String url;
        private List<CourseBean> course;
        private List<LessonBean> lesson;

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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLogopath() {
            return logopath;
        }

        public void setLogopath(String logopath) {
            this.logopath = logopath;
        }

        public String getHotline() {
            return hotline;
        }

        public void setHotline(String hotline) {
            this.hotline = hotline;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public Object getCourese_start_time() {
            return courese_start_time;
        }

        public void setCourese_start_time(Object courese_start_time) {
            this.courese_start_time = courese_start_time;
        }

        public String getCompare() {
            return compare;
        }

        public void setCompare(String compare) {
            this.compare = compare;
        }

        public String getShopCommentCount() {
            return shopCommentCount;
        }

        public void setShopCommentCount(String shopCommentCount) {
            this.shopCommentCount = shopCommentCount;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<CourseBean> getCourse() {
            return course;
        }

        public void setCourse(List<CourseBean> course) {
            this.course = course;
        }

        public List<LessonBean> getLesson() {
            return lesson;
        }

        public void setLesson(List<LessonBean> lesson) {
            this.lesson = lesson;
        }

        public static class CourseBean {
            /**
             * id : 28776
             * shop_id : 63684083
             * start_time : 1546135200
             * end_time : 1546138800
             * lessonName : 体验课
             * type : 1
             */

            private int id;
            private int shop_id;
            private int start_time;
            private int end_time;
            private String lessonName;
            private int type;

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

            public int getStart_time() {
                return start_time;
            }

            public void setStart_time(int start_time) {
                this.start_time = start_time;
            }

            public int getEnd_time() {
                return end_time;
            }

            public void setEnd_time(int end_time) {
                this.end_time = end_time;
            }

            public String getLessonName() {
                return lessonName;
            }

            public void setLessonName(String lessonName) {
                this.lessonName = lessonName;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }

        public static class LessonBean {
            /**
             * id : 3267
             * lessonName : 强化训练课
             * image : https://admin.idyoga.cn/static/images/lesson/5ae490507d677.jpg
             * time : 120
             * type : 2
             * start_time :
             * end_time :
             */

            private int id;
            private String lessonName;
            private String image;
            private int time;
            private int type;
            private String start_time;
            private String end_time;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLessonName() {
                return lessonName;
            }

            public void setLessonName(String lessonName) {
                this.lessonName = lessonName;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public int getTime() {
                return time;
            }

            public void setTime(int time) {
                this.time = time;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }
        }
    }
}
