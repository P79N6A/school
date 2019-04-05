package com.idyoga.yoga.model;

/**
 * @author weilgu
 * @time 2019/4/3 17:47
 * @des ${TODO}
 */

public class ShopTag {

    private String tagName;
    private boolean isSelect;
    private int tag ;

    public ShopTag(String tagName, boolean isSelect,int tag) {
        this.tagName = tagName;
        this.isSelect = isSelect;
        this.tag = tag;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }
}
