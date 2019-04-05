package com.idyoga.yoga.model;

import java.util.List;

/**
 * @author weilgu
 * @time 2019/3/18 14:26
 * @des 店铺详情
 */

public class ShopDetailDataBean {

    /**
     * id : 60703077   //店铺ID
     * name : WE dance 棠东校区  //店铺名
     * address : 广州市天河棠德南路64号123大厦5楼  //店铺地址
     * logopath : http://testyogabook.hq-xl.com/static/images/logo/shop_1897031198.png  //店铺封面图
     * longitude : 113.3894854  //经度
     * latitude : 23.1378314    //纬度
     * is_verify : 0            //店铺是否已认证（0未认证 1正在认证 2已认证 3认证失败）
     * detail_image : http://testyogabook.hq-xl.com/static/images/shop_detail_image/96fc1a41837c1c8e1154546b95c0c18d.png  //店铺详情图
     * business_hours : null  //营业时间
     * floor_area : 0.00      //占地面积
     * capacity : 0           //可容纳人数
     * isAttention : 0        //是否已关注（0否1是）：未传userId情况下默认0
     * shopImage : null       //场馆图
     * lessonImage : http://testyogabook.hq-xl.com/static/images/lesson/5bfb9a945f37e.jpg  //课程图
     * studentImage : null    //学员风采图
     * lessonCount : 6
     *
     * //课程
     * lessonList : [{"id":6698,"name":"Urban","image":"http://testyogabook.hq-xl.com/static/images/lesson/5bfb9ba1bce22.jpg","appointmentNum":0,"isCourse":1},{"id":6699,"name":"Jazz","image":"http://testyogabook.hq-xl.com/static/images/lesson/5bfb9b996ec36.jpg","appointmentNum":0,"isCourse":1}]
     * //秒杀列表
     * seckillList : []
     * //团购列表
     * spellteamList : []
     * //店铺老师列表
     * tutorList : [{"name":"Fong ll","image":"http://testyogabook.hq-xl.com/static/images/tutor/5bd12b8032689.png"},{"name":"Amx","image":"http://testyogabook.hq-xl.com/static/images/tutor/5bd12ba9b4ca8.png"},{"name":"ViVi","image":"http://testyogabook.hq-xl.com/static/images/tutor/5bd12c0d146bd.png"},{"name":"Biga","image":"http://testyogabook.hq-xl.com/static/images/tutor/5bd12c2fec3c9.png"}]
     * //附近瑜伽馆列表
     * nearbyShopList : [{"id":87388202,"name":"亚玛瑜伽(俊园馆)","address":"中山大道枫叶路3号珠江俊园11商铺(大嘴渔港斜对面)","logopath":"http://testyogabook.hq-xl.com/static/images/logo/shop_1428762062.png","courese_start_time":null,"compare":"1.01","lessonImage":"http://testyogabook.hq-xl.com/static/images/lesson/5bfcfd7fbc54c.jpg","shopImage":null,"studentImage":null},{"id":51619262,"name":"亚码瑜伽（俊园馆）","address":"广州市天河区中山大道枫叶路3号珠江俊园11商铺","logopath":"http://testyogabook.hq-xl.com/static/images/logo/shop_98713189.png","courese_start_time":null,"compare":"1.03","lessonImage":"http://testyogabook.hq-xl.com/static/images/lesson/5bfba9c5243ff.jpg","shopImage":null,"studentImage":null},{"id":29171784,"name":"禅本伽舍理疗工作室","address":"广州市天河区中山大道西203号历德雅舍D栋910","logopath":"http://testyogabook.hq-xl.com/static/images/logo/shop_1427609601.png","courese_start_time":null,"compare":"1.09","lessonImage":"http://testyogabook.hq-xl.com/static/images/lesson/5bfcb05e86e64.jpg","shopImage":null,"studentImage":null},{"id":30687049,"name":"菲尚健身会所（天河店）","address":"广州市天河区中山大道西203号历德雅舍13楼","logopath":"http://testyogabook.hq-xl.com/static/images/logo/shop_1967333119.png","courese_start_time":null,"compare":"1.17","lessonImage":"http://testyogabook.hq-xl.com/static/images/lesson/5bfb96659b8d3.jpg","shopImage":null,"studentImage":null},{"id":89077895,"name":"广州GS瑜伽舞蹈工作室","address":"广州市天河区棠东官育路意丰商务中心8号110","logopath":"http://testyogabook.hq-xl.com/static/images/logo/shop_1751676593.png","courese_start_time":"2019-03-19 13:30:00","compare":"1.39","lessonImage":"https://admin.idyoga.cn/static/images/lesson/5ad84dadbaeca.JPG","shopImage":null,"studentImage":null}]
     */

    private int id;
    private String name;
    private String address;
    private String logopath;
    private String longitude;
    private String latitude;
    private int is_verify;
    private String detail_image;
    //Object business_hours ?????
    private Object business_hours;
    private String floor_area;
    private int capacity;
    private int isAttention;
    private String shopImage;
    private String lessonImage;
    private String studentImage;
    private int lessonCount;
    private List<LessonListBean> lessonList;
    private List<?> seckillList;
    private List<?> spellteamList;
    private List<TutorListBeans> tutorList;
    private List<NearbyShopListBean> nearbyShopList;

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

    public int getIs_verify() {
        return is_verify;
    }

    public void setIs_verify(int is_verify) {
        this.is_verify = is_verify;
    }

    public String getDetail_image() {
        return detail_image;
    }

    public void setDetail_image(String detail_image) {
        this.detail_image = detail_image;
    }

    public Object getBusiness_hours() {
        return business_hours;
    }

    public void setBusiness_hours(Object business_hours) {
        this.business_hours = business_hours;
    }

    public String getFloor_area() {
        return floor_area;
    }

    public void setFloor_area(String floor_area) {
        this.floor_area = floor_area;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getIsAttention() {
        return isAttention;
    }

    public void setIsAttention(int isAttention) {
        this.isAttention = isAttention;
    }

    public String getShopImage() {
        return shopImage;
    }

    public void setShopImage(String shopImage) {
        this.shopImage = shopImage;
    }

    public String getLessonImage() {
        return lessonImage;
    }

    public void setLessonImage(String lessonImage) {
        this.lessonImage = lessonImage;
    }

    public String getStudentImage() {
        return studentImage;
    }

    public void setStudentImage(String studentImage) {
        this.studentImage = studentImage;
    }

    public int getLessonCount() {
        return lessonCount;
    }

    public void setLessonCount(int lessonCount) {
        this.lessonCount = lessonCount;
    }

    public List<LessonListBean> getLessonList() {
        return lessonList;
    }

    public void setLessonList(List<LessonListBean> lessonList) {
        this.lessonList = lessonList;
    }

    public List<?> getSeckillList() {
        return seckillList;
    }

    public void setSeckillList(List<?> seckillList) {
        this.seckillList = seckillList;
    }

    public List<?> getSpellteamList() {
        return spellteamList;
    }

    public void setSpellteamList(List<?> spellteamList) {
        this.spellteamList = spellteamList;
    }

    public List<TutorListBeans> getTutorList() {
        return tutorList;
    }

    public void setTutorList(List<TutorListBeans> tutorList) {
        this.tutorList = tutorList;
    }

    public List<NearbyShopListBean> getNearbyShopList() {
        return nearbyShopList;
    }

    public void setNearbyShopList(List<NearbyShopListBean> nearbyShopList) {
        this.nearbyShopList = nearbyShopList;
    }
}
