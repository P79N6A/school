package com.idyoga.yoga.model;

import com.mcxtzhang.indexlib.IndexBar.bean.BaseIndexBean;
import com.mcxtzhang.indexlib.IndexBar.bean.BaseIndexPinyinBean;

import java.util.List;

import vip.devkit.library.ListUtil;

/**
 * 文 件 名: CityBean
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/12
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class CityBean  {



    private List<RecentCityBean> RecentCity;
    private List<HotCityBean> hotCity;
    private List<CityListBean> cityList;

    public List<HotCityBean> getHotCity() {
        return hotCity;
    }

    public void setHotCity(List<HotCityBean> hotCity) {
        this.hotCity = hotCity;
    }

    public List<CityListBean> getCityList() {
        return cityList;
    }

    public void setCityList(List<CityListBean> cityList) {
        this.cityList = cityList;
    }

    public List<RecentCityBean> getRecentCity() {
        return RecentCity;
    }

    public void setRecentCity(List<RecentCityBean> recentCity) {
        RecentCity = recentCity;
    }

    public static class RecentCityBean extends BaseIndexPinyinBean{
        /**
         * id : 6
         * name : 合肥市
         * shop_id : 12362297
         */

        private int id;
        private String name;
        private int shop_id;
        private boolean isSelect =false;

        public RecentCityBean() {
        }

        public RecentCityBean(int id, String name, int shop_id) {
            this.id = id;
            this.name = name;
            this.shop_id = shop_id;
        }

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
        public String getTarget() {
            return name;
        }

        @Override
        public String toString() {
            return "RecentCityBean{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", shop_id=" + shop_id +
                    ", isSelect=" + isSelect +
                    '}';
        }
    }

    public static class HotCityBean extends BaseIndexPinyinBean{
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
        public String getTarget() {
            return name;
        }
    }

    public static class CityListBean extends BaseIndexPinyinBean {
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
        public String getTarget() {
            return name;
        }

    }

    @Override
    public String toString() {
        return "CityBean{" +
                "RecentCity=" + RecentCity +
                ", hotCity=" + hotCity +
                ", cityList=" + cityList +
                '}';
    }
}
