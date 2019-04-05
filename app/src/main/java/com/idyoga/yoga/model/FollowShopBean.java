package com.idyoga.yoga.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @author weilgu
 * @time 2019/3/27 14:01
 * @des ${TODO}
 */

public class FollowShopBean implements MultiItemEntity {

    /**
     * shopId : 11042524
     * shopName : 芊桦瑜伽馆
     * logopath : https://admin.idyoga.cn/static/images/logo/shop_base.png
     * address : 东晓南路晓港湾花园管理处会所1楼(近地铁东晓南站)
     * is_verify : 0
     * create_time : 1553665984
     */

    private int shopId;
    private String shopName;
    private String logopath;
    private String address;
    private int is_verify;
    private int create_time;
    private int itemType;
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

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

    public int getIs_verify() {
        return is_verify;
    }

    public void setIs_verify(int is_verify) {
        this.is_verify = is_verify;
    }

    public int getCreate_time() {
        return create_time;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }
}
