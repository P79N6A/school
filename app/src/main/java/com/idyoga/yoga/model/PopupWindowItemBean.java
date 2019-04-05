package com.idyoga.yoga.model;

public class PopupWindowItemBean {
    private int id;
    private String name;
    private boolean isVisibility;

    public PopupWindowItemBean(String name, boolean isVisibility) {
        this.name = name;
        this.isVisibility = isVisibility;
    }

    public PopupWindowItemBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVisibility() {
        return isVisibility;
    }

    public void setVisibility(boolean visibility) {
        isVisibility = visibility;
    }
}
