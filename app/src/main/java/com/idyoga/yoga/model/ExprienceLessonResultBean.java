package com.idyoga.yoga.model;

/**
 * Created by domain on 2018-03-26.
 * Email:sunlongyue@foxmail.com
 * describe:
 */

public class ExprienceLessonResultBean {


    /**
     * code : 1
     * msg : 课程预约成功！
     * time : 1522376981
     * data : {"appId":"2403"}
     */

    private String code;
    private String msg;
    private String time;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * appId : 2403
         */

        private String appId;

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }
    }
}
