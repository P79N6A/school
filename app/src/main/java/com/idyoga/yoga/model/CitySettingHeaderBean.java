package com.idyoga.yoga.model;

import com.mcxtzhang.indexlib.IndexBar.bean.BaseIndexPinyinBean;

import java.util.List;

/**
 * 文 件 名: CitySettingHeaderBean
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/12
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class CitySettingHeaderBean extends BaseIndexPinyinBean {
    private List<CityBean> cityList;
    //悬停ItemDecoration显示的Tag
    private String suspensionTag;

    public CitySettingHeaderBean() {
    }

    public CitySettingHeaderBean(List<CityBean> cityList, String suspensionTag, String indexBarTag) {
        this.cityList = cityList;
        this.suspensionTag = suspensionTag;
        this.setBaseIndexTag(indexBarTag);
    }

    public List<CityBean> getCityList() {
        return cityList;
    }

    public CitySettingHeaderBean setCityList(List<CityBean> cityList) {
        this.cityList = cityList;
        return this;
    }

    public CitySettingHeaderBean setSuspensionTag(String suspensionTag) {
        this.suspensionTag = suspensionTag;
        return this;
    }

    @Override
    public String getTarget() {
        return null;
    }

    @Override
    public boolean isNeedToPinyin() {
        return false;
    }

    @Override
    public String getSuspensionTag() {
        return suspensionTag;
    }

    public static class CityBean {
        /**
         * id : 6
         * name : 合肥市
         * shop_id : 12362297
         */

        private int id;
        private String name;
        private int shop_id;
        private boolean isSelect =false;

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

        public int getShop_id() {
            return shop_id;
        }

        public void setShop_id(int shop_id) {
            this.shop_id = shop_id;
        }

        @Override
        public String toString() {
            return "CityBean{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", shop_id=" + shop_id +
                    ", isSelect=" + isSelect +
                    '}';
        }
    }
}
