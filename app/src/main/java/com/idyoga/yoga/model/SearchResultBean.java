package com.idyoga.yoga.model;

import java.util.List;

/**
 * 文 件 名: TutorSelectBean
 * 创 建 人: By k
 * 创建日期: 2018/5/14 11:58
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注： 搜索列表
 */
public class SearchResultBean {

    private List<ShopListBean> shopList;
    private List<TutorListBean> tutorList;
    private List<CourseListBeanX> courseList;

    public List<ShopListBean> getShopList() {
        return shopList;
    }

    public void setShopList(List<ShopListBean> shopList) {
        this.shopList = shopList;
    }

    public List<TutorListBean> getTutorList() {
        return tutorList;
    }

    public void setTutorList(List<TutorListBean> tutorList) {
        this.tutorList = tutorList;
    }

    public List<CourseListBeanX> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<CourseListBeanX> courseList) {
        this.courseList = courseList;
    }

    public static class ShopListBean {
        /**
         * id : 63408634
         * name : 员村瑜伽馆
         * logopath : http://testyogabook.hq-xl.com/static/images/logo/shop_1649895434.png
         * address : 地址
         * compare : 0.00
         */

        private int id;
        private String name;
        private String logopath;
        private String address;
        private String compare;

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

        public String getLogopath() {
            return logopath;
        }

        public void setLogopath(String logopath) {
            this.logopath = logopath;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCompare() {
            return compare;
        }

        public void setCompare(String compare) {
            this.compare = compare;
        }
    }

    public static class TutorListBean {

        /**
         * id : 113
         * name : 方老师
         * image :
         * lessonName : 林大师蛇形瑜伽课,钢管舞瑜伽,测试课程01,林大师测试课程,老李课程
         * mainstreamName : 吠陀瑜伽,王瑜伽,吉瓦穆克提瑜伽,阴瑜伽
         * shop_id : 19287727
         * tutorLabel : [{"name":"签约导师"},{"name":"海外导师"},{"name":"金牌导师"}]
         */

        private int id;
        private String name;
        private String image;
        private String lessonName;
        private String mainstreamName;
        private int shop_id;
        private List<TutorLabelBean> tutorLabel;

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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getLessonName() {
            return lessonName;
        }

        public void setLessonName(String lessonName) {
            this.lessonName = lessonName;
        }

        public String getMainstreamName() {
            return mainstreamName;
        }

        public void setMainstreamName(String mainstreamName) {
            this.mainstreamName = mainstreamName;
        }

        public int getShop_id() {
            return shop_id;
        }

        public void setShop_id(int shop_id) {
            this.shop_id = shop_id;
        }

        public List<TutorLabelBean> getTutorLabel() {
            return tutorLabel;
        }

        public void setTutorLabel(List<TutorLabelBean> tutorLabel) {
            this.tutorLabel = tutorLabel;
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
    }

    public static class CourseListBeanX {
        /**
         * id : 71349001
         * name : 长沙芙蓉妃梵瑜伽
         * address : 湖南省长沙市芙蓉区东岸街道隆平路169号紫御园二期地上商业及地下室4层4012-4013
         * logopath :
         * compare : 157.43
         * courseList : [{"lessonName":"空中瑜伽","image":null,"start_time":1531455600,"end_time":1531459200,"tutorName":"彭桢桢老师"}]
         */

        private int id;
        private String name;
        private String address;
        private String logopath;
        private String compare;
        private List<CourseListBean> courseList;

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

        public String getCompare() {
            return compare;
        }

        public void setCompare(String compare) {
            this.compare = compare;
        }

        public List<CourseListBean> getCourseList() {
            return courseList;
        }

        public void setCourseList(List<CourseListBean> courseList) {
            this.courseList = courseList;
        }

        public static class CourseListBean {

            /**
             * id : 1250
             * lessonName : 辅助瑜伽
             * image : null
             * start_time : 1531807200
             * end_time : 1531810800
             * tutorName : 吴婷老师
             */

            private int id;
            private String lessonName;
            private String image;
            private int start_time;
            private int end_time;
            private String tutorName;

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

            public String getTutorName() {
                return tutorName;
            }

            public void setTutorName(String tutorName) {
                this.tutorName = tutorName;
            }
        }
    }
}
