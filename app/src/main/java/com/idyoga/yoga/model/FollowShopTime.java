package com.idyoga.yoga.model;

/**
 * @author weilgu
 * @time 2019/3/7 16:38
 * @des 关注瑜伽馆的关注时间
 */

public class FollowShopTime {

    private long time;
    private String followTime;

    public String getFollowTime() {
        return followTime;
    }

    public void setFollowTime(String followTime) {
        this.followTime = followTime;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
