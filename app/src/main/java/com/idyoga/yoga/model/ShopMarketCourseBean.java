package com.idyoga.yoga.model;

/**
 * 文 件 名: ShopMarketCourseBean
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/22
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class ShopMarketCourseBean {


    /**
     * id : 357
     * def_yoga_lession_id : null
     * name : 长者舒缓
     * introduce : 1.目前以上课程的日期与时间仅做展示使用，在您预约成功后，IDYoga平台客服将在2小时内与您联系确认课程需求
     2.为提供更优质的服务，所有预约流程均提供客服专线答疑，如有疑问可致电400-902-0929
     * image : http://testyogabook.hq-xl.com/static/images/lesson/5ad9afdba450e.jpg
     * content :
     * shop_id : 63684083
     * status : 0
     * time : 60
     * is_deleted : 0
     * create_time : 1521095372
     * update_time : 1524727012
     */

    private int id;
    private Object def_yoga_lession_id;
    private String name;
    private String introduce;
    private String image;
    private String content;
    private int shop_id;
    private int status;
    private int time;
    private int is_deleted;
    private int create_time;
    private int update_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getDef_yoga_lession_id() {
        return def_yoga_lession_id;
    }

    public void setDef_yoga_lession_id(Object def_yoga_lession_id) {
        this.def_yoga_lession_id = def_yoga_lession_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
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
}
