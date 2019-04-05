package com.idyoga.yoga.model;

/**
 * @author weilgu
 * @time 2019/3/18 17:04
 * @des ${TODO}
 */

public class TutorListBeans {
    /**
     * name : Fong ll
     * image : http://testyogabook.hq-xl.com/static/images/tutor/5bd12b8032689.png
     */
    private int tutorId;
    private String name;
    private String image;

    public int getTutorId() {
        return tutorId;
    }

    public void setTutorId(int tutorId) {
        this.tutorId = tutorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
