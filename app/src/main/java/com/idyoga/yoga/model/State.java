package com.idyoga.yoga.model;

/**
 * Created by Administrator on 2019/03/24.
 *
 */

public class State {

    private boolean isClick;
    private int data;

    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click) {
        isClick = click;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
