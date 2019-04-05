package com.idyoga.yoga.model;

import java.util.List;

/**
 * 文 件 名: HomeExperienceBean
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/5/19
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class HomeExperienceBean {

    private List<BannerListBean> bannerList;
    private List<TagListBean> tagList;
    private List<RecommendCourseList> recommendCourseList;

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

    public List<RecommendCourseList> getRecommendCourseList() {
        return recommendCourseList;
    }

    public void setRecommendCourseList(List<RecommendCourseList> recommendCourseList) {
        this.recommendCourseList = recommendCourseList;
    }

    public static class BannerListBean {
        /**
         * id : 28
         * image : http://testyogabook.hq-xl.com/shop/marketBanner/5ab305b8cb54c.jpg
         * url :
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
         * id : 104
         * image_url : http://testyogabook.hq-xl.com/shop/tagImage/5ad9922033216.jpg
         * name : 医学理疗
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

    public static class RecommendCourseList {
    }

}
