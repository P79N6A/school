package com.idyoga.yoga.model;

/**
 * @author weilgu
 * @time 2019/3/27 10:15
 * @des ${TODO}
 */

public class BaseCourseBean {
    // 1 某节课的排课  0 瑜伽馆的课程排课
    protected int beanType;

    public int getBeanType() {
        return beanType;
    }

    public void setBeanType(int beanType) {
        this.beanType = beanType;
    }
}
