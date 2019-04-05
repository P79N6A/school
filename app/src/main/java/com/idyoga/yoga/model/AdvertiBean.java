package com.idyoga.yoga.model;

/**
 * @author weilgu
 * @time 2019/4/2 9:24
 * @des ${TODO}
 */

public class AdvertiBean {


    /**
     * id : 3
     * image_url : https://t.p.idyoga.cn//image/yoga_college_adverti_image/2019/04/5ca1a9a3bfe67.jpg
     * start_time : 1554048000
     * end_time : 1554120180
     * url : https://www.baidu.com/
     */

    private int id;
    private String image_url;
    private int start_time;
    private int end_time;
    private String url;

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

    public int getStart_time() {
        return start_time;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public int getEnd_time() {
        return end_time;
    }

    public void setEnd_time(int end_time) {
        this.end_time = end_time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
