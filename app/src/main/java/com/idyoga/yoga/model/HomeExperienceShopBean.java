package com.idyoga.yoga.model;

import java.util.List;

/**
 * 文 件 名: HomeExperienceBean
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/4
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class HomeExperienceShopBean {


    /**
     * id : 63150551
     * name : 梵伊瑜伽(天河公园店)
     * address : 建中路36号裕桥商务大厦702室(科韵路)
     * logopath :
     * hotline : 13432051056
     * merid : 0
     * mobile : 13432051056
     * sms_number : 0
     * is_for_platform : 0
     * chain_id : null
     * longitude : 113.3771262
     * latitude : 23.1308669
     * province : 1
     * city : 17
     * area : 4
     * create_time : 1521107863
     * update_time : 1521797104
     * course : [{"id":17125,"start_time":1546214400,"end_time":1546218000,"start":"2018-12-31 08:00","end":"2018-12-31 09:00","number":10,"tutorName":"杨老师","tagName":"五官系列","lessonName":"视力保健","lessonImage":"http://testyogabook.hq-xl.com/static/images/lesson/5ad9a99f5875e.jpg","classroomName":"大班课室"},{"id":17185,"start_time":1546214400,"end_time":1546218000,"start":"2018-12-31 08:00","end":"2018-12-31 09:00","number":10,"tutorName":"吴老师","tagName":"女性系列","lessonName":"暖宫瑜伽","lessonImage":"http://testyogabook.hq-xl.com/static/images/lesson/5ad9ad843e0e3.jpg","classroomName":"大班课室"}]
     * shopCommentCount : 0
     * url : http://wxyoga.hq-xl.com/studio/detail?shopId=63150551&lat=23.14278&long=113.383856
     * compare : 1.49
     */

    private int id;
    private String name;
    private String address;
    private String logopath;
    private String hotline;
    private int merid;
    private String mobile;
    private int sms_number;
    private int is_for_platform;
    private Object chain_id;
    private String longitude;
    private String latitude;
    private int province;
    private int city;
    private int area;
    private int create_time;
    private int update_time;
    private int shopCommentCount;
    private String url;
    private String compare;
    private List<CourseBean> course;
    private List<LessonBean> lesson;

    public List<LessonBean> getLesson() {
        return lesson;
    }

    public void setLesson(List<LessonBean> lesson) {
        this.lesson = lesson;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogopath() {
        return logopath;
    }

    public void setLogopath(String logopath) {
        this.logopath = logopath;
    }

    public String getHotline() {
        return hotline;
    }

    public void setHotline(String hotline) {
        this.hotline = hotline;
    }

    public int getMerid() {
        return merid;
    }

    public void setMerid(int merid) {
        this.merid = merid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getSms_number() {
        return sms_number;
    }

    public void setSms_number(int sms_number) {
        this.sms_number = sms_number;
    }

    public int getIs_for_platform() {
        return is_for_platform;
    }

    public void setIs_for_platform(int is_for_platform) {
        this.is_for_platform = is_for_platform;
    }

    public Object getChain_id() {
        return chain_id;
    }

    public void setChain_id(Object chain_id) {
        this.chain_id = chain_id;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public int getProvince() {
        return province;
    }

    public void setProvince(int province) {
        this.province = province;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getCreate_time() {
        return create_time;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }

    public int getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(int update_time) {
        this.update_time = update_time;
    }

    public int getShopCommentCount() {
        return shopCommentCount;
    }

    public void setShopCommentCount(int shopCommentCount) {
        this.shopCommentCount = shopCommentCount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCompare() {
        return compare;
    }

    public void setCompare(String compare) {
        this.compare = compare;
    }

    public List<CourseBean> getCourse() {
        return course;
    }

    public void setCourse(List<CourseBean> course) {
        this.course = course;
    }

    public static class LessonBean{

        /**
         * id : 106
         * lessonName : 啊啊啊啊
         * image : null
         * time : 60
         * type : 2
         * start_time :
         * end_time :
         */

        private int id;
        private String lessonName;
        private Object image;
        private int time;
        private int type;
        private String start_time;
        private String end_time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLessonName() {
            return lessonName;
        }

        public void setLessonName(String lessonName) {
            this.lessonName = lessonName;
        }

        public Object getImage() {
            return image;
        }

        public void setImage(Object image) {
            this.image = image;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }
    }

    public static class CourseBean {

        /**
         * id : 33336
         * shop_id : 74120289
         * start_time : 1531142820
         * end_time : 1531144620
         * lessonName : 吐鲁番瑜伽
         * image : http://testyogabook.hq-xl.com/static/images/lesson/5b1b872b69d44.png
         * type : 1
         */

        private int id;
        private int shop_id;
        private int start_time;
        private int end_time;
        private String lessonName;
        private String image;
        private int type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getShop_id() {
            return shop_id;
        }

        public void setShop_id(int shop_id) {
            this.shop_id = shop_id;
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

        public String getLessonName() {
            return lessonName;
        }

        public void setLessonName(String lessonName) {
            this.lessonName = lessonName;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
