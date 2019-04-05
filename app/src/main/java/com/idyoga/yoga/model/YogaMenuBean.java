package com.idyoga.yoga.model;

/**
 * 文 件 名: YogaMenuBean
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/4/27
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class YogaMenuBean {

    private String id;
    private int icon;
    private String text;

    public YogaMenuBean() {}

    public YogaMenuBean(String text) {
        this.text = text;
    }

    public YogaMenuBean(int iconId, String text) {
        this.icon = iconId;
        this.text = text;
    }

    public YogaMenuBean(String id, int iconId, String text) {
        this.id = id;
        this.icon = iconId;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIcon() {
        return icon;

    }
    public void setIcon(int iconId) {
        this.icon = iconId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

