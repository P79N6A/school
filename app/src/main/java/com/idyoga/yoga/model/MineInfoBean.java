package com.idyoga.yoga.model;

/**
 * @author weilgu
 * @time 2019/3/25 14:39
 * @des ${TODO}
 */

public class MineInfoBean {

    /**
     * id : 46244
     * name : 左丘梦凡
     * mobile : 15975309485
     * sex : 1
     * mailbox :
     * id_card :
     * address : 广东 广州 天河区
     * birthday : 0
     * head_pic : null
     * isBindingWeChat : 0
     * aboutAppointment : null  //即将开始的课程
     */

    private int id;
    private String name;
    private String mobile;
    private int sex;
    private String mailbox;
    private String id_card;
    private String address;
    private int birthday;
    private String head_pic;
    private int isBindingWeChat;
    private AboutAppointment aboutAppointment;

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

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public String getHead_pic() {
        return head_pic;
    }

    public void setHead_pic(String head_pic) {
        this.head_pic = head_pic;
    }

    public int getIsBindingWeChat() {
        return isBindingWeChat;
    }

    public void setIsBindingWeChat(int isBindingWeChat) {
        this.isBindingWeChat = isBindingWeChat;
    }

    public AboutAppointment getAboutAppointment() {
        return aboutAppointment;
    }

    public void setAboutAppointment(AboutAppointment aboutAppointment) {
        this.aboutAppointment = aboutAppointment;
    }
}
