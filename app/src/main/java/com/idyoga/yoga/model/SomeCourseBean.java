package com.idyoga.yoga.model;

import java.util.List;

/**
 * @author weilgu
 * @time 2019/3/27 10:10
 * @des ${TODO}
 */

public class SomeCourseBean extends BaseCourseBean{

    private int id;
    private long start_time;
    private long end_time;
    private int lesson_id;
    private String lesson_name;
    private String lesson_image;
    private int last_number;
    private List<Tutor> tutor;
    private boolean isSelect;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getStart_time() {
        return start_time;
    }

    public void setStart_time(long start_time) {
        this.start_time = start_time;
    }

    public long getEnd_time() {
        return end_time;
    }

    public void setEnd_time(long end_time) {
        this.end_time = end_time;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
    }

    public String getLesson_name() {
        return lesson_name;
    }

    public void setLesson_name(String lesson_name) {
        this.lesson_name = lesson_name;
    }

    public String getLesson_image() {
        return lesson_image;
    }

    public void setLesson_image(String lesson_image) {
        this.lesson_image = lesson_image;
    }

    public int getLast_number() {
        return last_number;
    }

    public void setLast_number(int lesson_number) {
        this.last_number = lesson_number;
    }

    public List<Tutor> getTutor() {
        return tutor;
    }

    public void setTutor(List<Tutor> tutor) {
        this.tutor = tutor;
    }

    public static class Tutor{
        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
