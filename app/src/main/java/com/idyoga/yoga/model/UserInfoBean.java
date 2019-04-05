package com.idyoga.yoga.model;

import java.io.Serializable;


/**
 * 作者 by K
 * 时间：on 2017/9/19 19:33
 * 邮箱 by  vip@devkit.vip
 * <p/>
 * 类用途：
 * 最后修改：
 */

public class UserInfoBean  implements Serializable {
    /**
     * id : 46244
     * username : %E9%9B%B6%E6%98%9F
     * token : 5cd0dce02c27275c7a17a79d2f2a790b
     * mobile : 15975309485
     * birthday : 0
     * mailbox :
     * sex : 0
     * avatarUrl : null
     * address :
     * nickname :
     * headimgurl :
     * type : false
     * agentId :
     * is_vip : 0
     * vip_end_time :
     * union_id
     * name
     * id_card
     * head_pic
     * isBindingWeChat
     * aboutAppointment
     */

    private int id;
    private String username;
    private String token;
    private String mobile;
    private int birthday;
    private String mailbox;
    private int sex;
    private String avatarUrl;
    private String address;
    private String nickname;
    private String headimgurl;
    private String type;
    private String agentId;
    private int is_vip;
    private String vip_end_time;
    private String union_id;

    public String getUnion_id() {
        return union_id;
    }

    public void setUnion_id(String union_id) {
        this.union_id = union_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAvatar_url() {
        return avatarUrl;
    }

    public void setAvatar_url(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public int getIs_vip() {
        return is_vip;
    }

    public void setIs_vip(int is_vip) {
        this.is_vip = is_vip;
    }

    public String getVip_end_time() {
        return vip_end_time;
    }

    public void setVip_end_time(String vip_end_time) {
        this.vip_end_time = vip_end_time;
    }


    @Override
    public String toString() {
        return "UserInfoBean{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", token='" + token + '\'' +
                ", mobile='" + mobile + '\'' +
                ", birthday=" + birthday +
                ", mailbox='" + mailbox + '\'' +
                ", sex=" + sex +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", address='" + address + '\'' +
                ", nickname='" + nickname + '\'' +
                ", headimgurl='" + headimgurl + '\'' +
                ", type='" + type + '\'' +
                ", agentId='" + agentId + '\'' +
                ", is_vip=" + is_vip +
                ", vip_end_time='" + vip_end_time + '\'' +
                '}';
    }
}