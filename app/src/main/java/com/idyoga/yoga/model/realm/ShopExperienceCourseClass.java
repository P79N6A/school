package com.idyoga.yoga.model.realm;

import java.util.List;


/**
 * 文 件 名: ShopExperienceCourseClassTag
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/21
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class ShopExperienceCourseClass  {
    /**
     * id : 30
     * name : 抑郁缓解瑜伽
     * image_url : https://platform.hq-xl.com/label_classify_image/2018/07/5b42c34de84a7.jpg
     * label : [{"id":67,"name":"抑郁缓解瑜伽"}]
     */

    private int id;
    private String name;
    private String image_url;
    private List<ShopExperienceCourseClassTag> label;
    private boolean isVisibility;

    public ShopExperienceCourseClass() {
    }

    public ShopExperienceCourseClass(String name) {
        this.name = name;
    }

    public ShopExperienceCourseClass(int id, String name, String image_url, List<ShopExperienceCourseClassTag> label, boolean isVisibility) {
        this.id = id;
        this.name = name;
        this.image_url = image_url;
        this.label = label;
        this.isVisibility = isVisibility;
    }

    public boolean isVisibility() {
        return isVisibility;
    }

    public void setVisibility(boolean visibility) {
        isVisibility = visibility;
    }

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

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public List<ShopExperienceCourseClassTag> getLabel() {
        return label;
    }

    public void setLabel(List<ShopExperienceCourseClassTag> label) {
        this.label = label;
    }

}
