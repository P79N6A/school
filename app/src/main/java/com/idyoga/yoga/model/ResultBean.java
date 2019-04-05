package com.idyoga.yoga.model;

import java.util.List;

/**
 * 作者 by K
 * 时间：on 2017/8/22 13:56
 * 邮箱 by  vip@devkit.vip
 * <p/>
 * 类用途：
 * 最后修改：
 */
public class ResultBean {


    /**
     * code : 1
     * msg :
     * time : 1521612223
     * data : [{"id":12,"name":"天津","is_deleted":0,"create_time":1521442237,"update_time":1521442237,"City":[{"id":22,"province_id":12,"shop_id":30035817,"name":"天津","is_deleted":0,"create_time":1521442274,"update_time":1521442274}]},{"id":11,"name":"上海","is_deleted":0,"create_time":1521085120,"update_time":1521095067,"City":[{"id":16,"province_id":11,"shop_id":44972495,"name":"上海","is_deleted":0,"create_time":1521085368,"update_time":1521095133}]},{"id":10,"name":"北京","is_deleted":0,"create_time":1521085116,"update_time":1521085116,"City":[{"id":15,"province_id":10,"shop_id":94983847,"name":"北京","is_deleted":0,"create_time":1521085134,"update_time":1521085134}]},{"id":1,"name":"广东省","is_deleted":0,"create_time":1520319878,"update_time":1520319878,"City":[{"id":17,"province_id":1,"shop_id":63684083,"name":"广州","is_deleted":0,"create_time":1521097161,"update_time":1521097161},{"id":20,"province_id":1,"shop_id":94990884,"name":"深圳","is_deleted":0,"create_time":1521185987,"update_time":1521185987},{"id":21,"province_id":1,"shop_id":19348042,"name":"珠海","is_deleted":0,"create_time":1521191346,"update_time":1521191346}]}]
     */

    private String code;
    private String msg;
    private String time;
    private String data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", time='" + time + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}

