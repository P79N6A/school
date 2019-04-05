package com.idyoga.yoga.model;

/**
 * 文 件 名: ClassifyBean
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/5/19
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class ClassifyBean {


    /**
     * id : 1975
     * image_url : http://testyogabook.hq-xl.com/shop/tagImage/5afa37ccb9017.png
     * pic_url : http://testyogabook.hq-xl.com/shop/tagImage/5afa37ccb8f6a.jpg
     * name : 减肥
     * description : 减肥
     */

    private int id;
    private String image_url;
    private String pic_url;
    private String name;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
