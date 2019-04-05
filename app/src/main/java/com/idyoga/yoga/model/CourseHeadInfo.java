package com.idyoga.yoga.model;

import java.util.List;

/**
 * @author weilgu
 * @time 2019/3/18 10:58
 * @des 权益课 页面头部信息
 */

public class CourseHeadInfo {

    private List<BannerListBean> bannerList;
    private List<TagListBean> tagList;
    private List<HomePageData.SubjectListBean> subjectList;
    private List<AreaListBean> areaList;
    private List<LabelListBean> labelList;
    private List<EquityCourseShopListBean> shopList;

    public List<EquityCourseShopListBean> getShopList() {
        return shopList;
    }

    public void setShopList(List<EquityCourseShopListBean> shopList) {
        this.shopList = shopList;
    }

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

    public List<HomePageData.SubjectListBean> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<HomePageData.SubjectListBean> subjectList) {
        this.subjectList = subjectList;
    }

    public List<AreaListBean> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<AreaListBean> areaList) {
        this.areaList = areaList;
    }

    public List<LabelListBean> getLabelList() {
        return labelList;
    }

    public void setLabelList(List<LabelListBean> labelList) {
        this.labelList = labelList;
    }

    public static class BannerListBean {
        /**
         * id : 89
         * image_url : https://platform.hq-xl.com/city_image/2018/03/5aaf5e2f22026.png
         * url : http://mp.weixin.qq.com/s/gY_wT17L8imm6Ap3ZovDiw
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

    public static class TagListBean {
        /**
         * id : 10
         * name : 脾胃瑜伽
         * image_url : https://platform.hq-xl.com/label_classify_image/2018/07/5b47fee1840a0.png
         */

        private int id;
        private String name;
        private String image_url;

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

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }
    }

    public static class AreaListBean {
        /**
         * id : 14
         * name : 河东区
         */

        private int id;
        private String name;
        private boolean isSelect = false;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

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
    }

    public static class LabelListBean {
        /**
         * id : 30
         * name : 抑郁缓解
         * image_url : https://platform.hq-xl.com/label_classify_image/2018/07/5b47fe3864a5d.png
         * label : [{"id":67,"name":"抑郁缓解瑜伽"}]
         */

        private int id;
        private String name;
        private String image_url;
        private List<LabelBean> label;
        private boolean isSelect;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

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

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public List<LabelBean> getLabel() {
            return label;
        }

        public void setLabel(List<LabelBean> label) {
            this.label = label;
        }

        public static class LabelBean {
            /**
             * id : 67
             * name : 抑郁缓解瑜伽
             */

            private int id;
            private String name;
            private boolean isSelect;

            public boolean isSelect() {
                return isSelect;
            }

            public void setSelect(boolean select) {
                isSelect = select;
            }

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
        }
    }
}
