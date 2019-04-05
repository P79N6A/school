package com.idyoga.yoga.model;

import java.util.List;

/**
 * @author weilgu
 * @time 2019/3/21 9:59
 * @des ${TODO}
 */

public class SpecialDetailData {


    /**
     * id : 5
     * title : 一睡再睡瑜伽
     * crowd : 失眠人群
     * image_url : https://t.p.idyoga.cn/video_image/2018/11/5bf68c400a518.jpg
     * shopList : [{"shopId":19287727,"shopName":"现联瑜伽馆","logopath":"http://testyogabook.hq-xl.com/static/images/shop/19287727/5c6d1b951a9dc.png","address":"广州天河","shopImage":"http://testyogabook.hq-xl.com/static/images/shop/19287727/5c6d1b951aaab.png","lessonImage":"http://testyogabook.hq-xl.com/shop/lesson_cover/19287727/5c3d84a70123e.png","studentImage":"http://testyogabook.hq-xl.com/static/images/student_mien_image/5c739b2222d50.jpeg"},{"shopId":16735424,"shopName":"拉菲瑜伽馆","logopath":"https://admin.idyoga.cn/static/images/logo/shop_base.png","address":"广州天河","shopImage":null,"lessonImage":"https://admin.idyoga.cn/static/images/lesson/5c0f78942817e.jpg","studentImage":null}]
     */

    private int id;
    private String title;
    private String crowd;
    private String image_url;
    private List<ShopListBean> shopList;

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

    public String getCrowd() {
        return crowd;
    }

    public void setCrowd(String crowd) {
        this.crowd = crowd;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public List<ShopListBean> getShopList() {
        return shopList;
    }

    public void setShopList(List<ShopListBean> shopList) {
        this.shopList = shopList;
    }

    public static class ShopListBean {
        /**
         * shopId : 19287727
         * shopName : 现联瑜伽馆
         * logopath : http://testyogabook.hq-xl.com/static/images/shop/19287727/5c6d1b951a9dc.png
         * address : 广州天河
         * shopImage : http://testyogabook.hq-xl.com/static/images/shop/19287727/5c6d1b951aaab.png
         * lessonImage : http://testyogabook.hq-xl.com/shop/lesson_cover/19287727/5c3d84a70123e.png
         * studentImage : http://testyogabook.hq-xl.com/static/images/student_mien_image/5c739b2222d50.jpeg
         */

        private int shopId;
        private String shopName;
        private String logopath;
        private String address;
        private String shopImage;
        private String lessonImage;
        private String studentImage;

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

        public String getShopImage() {
            return shopImage;
        }

        public void setShopImage(String shopImage) {
            this.shopImage = shopImage;
        }

        public String getLessonImage() {
            return lessonImage;
        }

        public void setLessonImage(String lessonImage) {
            this.lessonImage = lessonImage;
        }

        public String getStudentImage() {
            return studentImage;
        }

        public void setStudentImage(String studentImage) {
            this.studentImage = studentImage;
        }
    }
}
