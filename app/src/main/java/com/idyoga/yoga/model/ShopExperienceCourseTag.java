package com.idyoga.yoga.model;


/**
 * 文 件 名: ShopExperienceCourseTag
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/21
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class ShopExperienceCourseTag {

    /**
     * id : 67
     * name : 抑郁缓解瑜伽
     */

    private int id;
    private String name;
    public ShopExperienceCourseTag() {
    }

    public ShopExperienceCourseTag(int id, String name) {
        this.id = id;
        this.name = name;
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
}
