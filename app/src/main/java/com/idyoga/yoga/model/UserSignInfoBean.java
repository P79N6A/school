package com.idyoga.yoga.model;

/**
 * 文 件 名: UserSignInfoBean
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/7/18
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class UserSignInfoBean {
    /**
     * id : 10771
     * name : 喵星人（测试）
     * mobile : 15772747952
     * sex : 1
     * head_pic : http://testyogabook.hq-xl.com/static/images/appuser_image/5b4c41aa2391a.jpg
     * token : Iwbf4no2w0AqrP7FCH1G40szWZc1yBxh
     * integralNumber : 160
     * userClockNumber : 2
     * userClockCount : 16
     * isClock : 0
     */

    private int id;
    private String name;
    private String mobile;
    private int sex;
    private String head_pic;
    private String token;
    private int integralNumber;
    private int userClockNumber;
    private int userClockCount;
    private String isClock;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getHead_pic() {
        return head_pic;
    }

    public void setHead_pic(String head_pic) {
        this.head_pic = head_pic;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getIntegralNumber() {
        return integralNumber;
    }

    public void setIntegralNumber(int integralNumber) {
        this.integralNumber = integralNumber;
    }

    public int getUserClockNumber() {
        return userClockNumber;
    }

    public void setUserClockNumber(int userClockNumber) {
        this.userClockNumber = userClockNumber;
    }

    public int getUserClockCount() {
        return userClockCount;
    }

    public void setUserClockCount(int userClockCount) {
        this.userClockCount = userClockCount;
    }

    public String getIsClock() {
        return isClock;
    }

    public void setIsClock(String isClock) {
        this.isClock = isClock;
    }
}
