package com.idyoga.yoga.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 文 件 名: UserVipCardBean
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/3/22
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class UserVipCardBean implements Parcelable {

    /**
     * card_name : 广州深圳通用20次卡
     * shop_id : 63684083
     * id : 2203
     * order_id : 3321
     * user_id : 11379
     * card_id : 924
     * expire_time : 1527593760
     * start_time : 1522323360
     * has_term : 2
     * has_time : 20
     * valid_time : 17
     * status : 0
     * remarks : null
     * is_deleted : 0
     * create_time : 1522323406
     * update_time : 1522376980
     * is_chain : 1
     * type : 2
     * annex_num : 20
     * valid_annex_num : 20
     * is_freeze : 0
     */

    private String card_name;
    private int shop_id;
    private int id;
    private int order_id;
    private int user_id;
    private int card_id;
    private int expire_time;
    private int start_time;
    private int has_term;
    private int has_time;
    private int valid_time;
    private int status;
    private String remarks;
    private int is_deleted;
    private int create_time;
    private int update_time;
    private int is_chain;
    private int type;
    private int annex_num;
    private int valid_annex_num;
    private int is_freeze;

    public String getCard_name() {
        return card_name;
    }

    public void setCard_name(String card_name) {
        this.card_name = card_name;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
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

    public int getExpire_time() {
        return expire_time;
    }

    public void setExpire_time(int expire_time) {
        this.expire_time = expire_time;
    }

    public int getStart_time() {
        return start_time;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.card_name);
        dest.writeInt(this.shop_id);
        dest.writeInt(this.id);
        dest.writeInt(this.order_id);
        dest.writeInt(this.user_id);
        dest.writeInt(this.card_id);
        dest.writeInt(this.expire_time);
        dest.writeInt(this.start_time);
        dest.writeInt(this.has_term);
        dest.writeInt(this.has_time);
        dest.writeInt(this.valid_time);
        dest.writeInt(this.status);
        dest.writeString(this.remarks);
        dest.writeInt(this.is_deleted);
        dest.writeInt(this.create_time);
        dest.writeInt(this.update_time);
        dest.writeInt(this.is_chain);
        dest.writeInt(this.type);
        dest.writeInt(this.annex_num);
        dest.writeInt(this.valid_annex_num);
        dest.writeInt(this.is_freeze);
    }

    public UserVipCardBean() {
    }

    protected UserVipCardBean(Parcel in) {
        this.card_name = in.readString();
        this.shop_id = in.readInt();
        this.id = in.readInt();
        this.order_id = in.readInt();
        this.user_id = in.readInt();
        this.card_id = in.readInt();
        this.expire_time = in.readInt();
        this.start_time = in.readInt();
        this.has_term = in.readInt();
        this.has_time = in.readInt();
        this.valid_time = in.readInt();
        this.status = in.readInt();
        this.remarks = in.readString();
        this.is_deleted = in.readInt();
        this.create_time = in.readInt();
        this.update_time = in.readInt();
        this.is_chain = in.readInt();
        this.type = in.readInt();
        this.annex_num = in.readInt();
        this.valid_annex_num = in.readInt();
        this.is_freeze = in.readInt();
    }

    public static final Creator<UserVipCardBean> CREATOR = new Creator<UserVipCardBean>() {
        @Override
        public UserVipCardBean createFromParcel(Parcel source) {
            return new UserVipCardBean(source);
        }

        @Override
        public UserVipCardBean[] newArray(int size) {
            return new UserVipCardBean[size];
        }
    };
}