package com.idyoga.yoga.model.address;


import com.bigkoo.pickerview.model.IPickerViewData;

import java.util.List;

/**
 * 文 件 名: ProvinceBean
 * 创 建 人: By k
 * 创建日期: 2018/3/21 13:52
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注：
 */
public class AddressBean implements IPickerViewData {

    /**
     * id : 12
     * name : 天津
     * is_deleted : 0
     * create_time : 1521442237
     * update_time : 1521442237
     * City : [{"id":22,"province_id":12,"shop_id":30035817,"name":"天津","is_deleted":0,"create_time":1521442274,"update_time":1521442274}]
     */

    private int id;
    private String name;
    private int is_deleted;
    private int create_time;
    private int update_time;
    private List<CityBean> City;

    @Override
    public String getPickerViewText() {
        return name;
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

    public List<CityBean> getCity() {
        return City;
    }

    public void setCity(List<CityBean> City) {
        this.City = City;
    }

    public static class CityBean implements IPickerViewData{
        /**
         * id : 22
         * province_id : 12
         * shop_id : 30035817
         * name : 天津
         * is_deleted : 0
         * create_time : 1521442274
         * update_time : 1521442274
         */

        private int id;
        private int province_id;
        private int shop_id;
        private String name;
        private int is_deleted;
        private int create_time;
        private int update_time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getProvince_id() {
            return province_id;
        }

        public void setProvince_id(int province_id) {
            this.province_id = province_id;
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

        @Override
        public String getPickerViewText() {
            return name;
        }
    }
}
