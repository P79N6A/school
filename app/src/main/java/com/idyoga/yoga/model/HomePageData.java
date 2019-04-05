package com.idyoga.yoga.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * @author weilgu
 * @time 2019/3/20 14:52
 * @des ${TODO}
 */

public class HomePageData implements MultiItemEntity {

    /**
     * banner : [{"id":127,"image_url":"https://platform.hq-xl.com/city_image/2018/04/5ad84221e219e.jpg","url":""},{"id":126,"image_url":"https://platform.hq-xl.com/city_image/2018/04/5ad841e436c8d.jpg","url":""}]
     * jumpUrlArr : {"integralMallUrl":"https://cmzb.idyoga.cn/yoga_college_mall/index/index","signUrl":"https://cmzb.idyoga.cn/yoga_college_mall/index/index"}
     * userData : {"id":46244,"name":"左丘梦凡","mobile":"15975309485","sex":1,"head_pic":null,"token":"5cd0dce02c27275c7a17a79d2f2a790b","integralNumber":0,"userClockNumber":0,"userClockCount":0,"isClock":"0"}
     * subjectList : [{"subjectId":5,"title":"一睡再睡瑜伽","image_url":"https://t.p.idyoga.cn/video_image/2018/11/5bf68c400a518.jpg"}]
     * shopList : [{"shop_id":90728654,"name":"天盈创意园瑜伽馆","logopath":"https://admin.idyoga.cn/static/images/logo/shop_1990364977.png","address":"广州市天河区棠安路288号","is_verify":0,"sumCourse":null,"compare":"0.21","lessonImage":"https://admin.idyoga.cn/static/images/lesson/5b5572108345c.png","shopImage":null,"studentMienImage":"http://testyogabook.hq-xl.com/static/images/student_mien_image/5b8e4236894c3.jpg"},{"shop_id":29619723,"name":"魔鬼瑜伽馆","logopath":"http://testyogabook.hq-xl.com/static/images/shop/29619723/5c8716f8b3dc2.jpg","address":"广州天河","is_verify":0,"sumCourse":1,"compare":"0.31","lessonImage":"https://admin.idyoga.cn/static/images/lesson/5c871e1236033.jpg","shopImage":"http://testyogabook.hq-xl.com/static/images/shop/29619723/5c8716f8b5c09.jpg","studentMienImage":"http://testyogabook.hq-xl.com/static/images/student_mien_image/5c8715bf1393a.jpg"},{"shop_id":62857226,"name":"爱秀时代舞蹈培训（上社）","logopath":"http://testyogabook.hq-xl.com/static/images/logo/shop_1252651641.png","address":"上社棠下街道荷光路合心商务中心C栋402","is_verify":0,"sumCourse":null,"compare":"0.62","lessonImage":"http://testyogabook.hq-xl.com/static/images/lesson/5b3ae173129bc.jpg","shopImage":null,"studentMienImage":null},{"shop_id":60703077,"name":"WE dance 棠东校区","logopath":"http://testyogabook.hq-xl.com/static/images/logo/shop_1897031198.png","address":"广州市天河棠德南路64号123大厦5楼","is_verify":0,"sumCourse":36,"compare":"0.77","lessonImage":"http://testyogabook.hq-xl.com/static/images/lesson/5bfb9ba1bce22.jpg","shopImage":null,"studentMienImage":null},{"shop_id":51619262,"name":"亚码瑜伽（俊园馆）","logopath":"http://testyogabook.hq-xl.com/static/images/logo/shop_98713189.png","address":"广州市天河区中山大道枫叶路3号珠江俊园11商铺","is_verify":0,"sumCourse":null,"compare":"1.28","lessonImage":"http://testyogabook.hq-xl.com/static/images/lesson/5bfba9c5243ff.jpg","shopImage":null,"studentMienImage":null},{"shop_id":87388202,"name":"亚玛瑜伽(俊园馆)","logopath":"http://testyogabook.hq-xl.com/static/images/logo/shop_1428762062.png","address":"中山大道枫叶路3号珠江俊园11商铺(大嘴渔港斜对面)","is_verify":0,"sumCourse":null,"compare":"1.28","lessonImage":"http://testyogabook.hq-xl.com/static/images/lesson/5bfcfd7fbc54c.jpg","shopImage":null,"studentMienImage":null},{"shop_id":29171784,"name":"禅本伽舍理疗工作室","logopath":"http://testyogabook.hq-xl.com/static/images/logo/shop_1427609601.png","address":"广州市天河区中山大道西203号历德雅舍D栋910","is_verify":0,"sumCourse":null,"compare":"1.40","lessonImage":"http://testyogabook.hq-xl.com/static/images/lesson/5bfcb05e86e64.jpg","shopImage":null,"studentMienImage":null},{"shop_id":48088370,"name":"爱秀时代舞蹈培训(棠东店）","logopath":"http://testyogabook.hq-xl.com/static/images/logo/shop_1121208422.png","address":"天河区中山大道西777号骏唐广场三楼","is_verify":0,"sumCourse":null,"compare":"1.40","lessonImage":"http://testyogabook.hq-xl.com/static/images/lesson/5bfcaa4c5a1c7.jpg","shopImage":null,"studentMienImage":null},{"shop_id":54270050,"name":"爱秀时代舞蹈培训（中山大道）","logopath":"http://testyogabook.hq-xl.com/static/images/logo/shop_1383102644.png","address":"广州天河区中山大道西777号骏唐广场三楼","is_verify":0,"sumCourse":null,"compare":"1.40","lessonImage":"http://testyogabook.hq-xl.com/static/images/lesson/5bff84bc08e6e.jpg","shopImage":null,"studentMienImage":null},{"shop_id":79361897,"name":"禅本伽舍理疗瑜伽工作室","logopath":"https://admin.idyoga.cn/static/images/logo/shop_base.png","address":"中山大道西203号历德雅舍D栋910(上社BRT)","is_verify":0,"sumCourse":null,"compare":"1.40","lessonImage":"https://admin.idyoga.cn/static/images/lesson/5c0f78942817e.jpg","shopImage":null,"studentMienImage":null}]
     */

    private JumpUrlArrBean jumpUrlArr;
    private UserDataBean userData;
    private List<BannerBean> banner;
    private List<SubjectListBean> subjectList;
    private List<ShopListBean> shopList;

    public JumpUrlArrBean getJumpUrlArr() {
        return jumpUrlArr;
    }

    public void setJumpUrlArr(JumpUrlArrBean jumpUrlArr) {
        this.jumpUrlArr = jumpUrlArr;
    }

    public UserDataBean getUserData() {
        return userData;
    }

    public void setUserData(UserDataBean userData) {
        this.userData = userData;
    }

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public List<SubjectListBean> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<SubjectListBean> subjectList) {
        this.subjectList = subjectList;
    }

    public List<ShopListBean> getShopList() {
        return shopList;
    }

    public void setShopList(List<ShopListBean> shopList) {
        this.shopList = shopList;
    }

    @Override
    public int getItemType() {
        return 0;
    }

    public static class JumpUrlArrBean {
        /**
         * integralMallUrl : https://cmzb.idyoga.cn/yoga_college_mall/index/index
         * signUrl : https://cmzb.idyoga.cn/yoga_college_mall/index/index
         */

        private String integralMallUrl;
        private String signUrl;

        public String getIntegralMallUrl() {
            return integralMallUrl;
        }

        public void setIntegralMallUrl(String integralMallUrl) {
            this.integralMallUrl = integralMallUrl;
        }

        public String getSignUrl() {
            return signUrl;
        }

        public void setSignUrl(String signUrl) {
            this.signUrl = signUrl;
        }
    }

    public static class UserDataBean {
        /**
         * id : 46244
         * name : 左丘梦凡
         * mobile : 15975309485
         * sex : 1
         * head_pic : null
         * token : 5cd0dce02c27275c7a17a79d2f2a790b
         * integralNumber : 0
         * userClockNumber : 0
         * userClockCount : 0
         * isClock : 0
         */

        private int id;
        private String name;
        private String mobile;
        private int sex;
        private String head_pic;
        private String token;
        private int integralNumber;
        private int userClockNumber;
        private int userClockCount;
        private String isClock;
        private String signUrl;
        private boolean isLogin; //是否登录

        public boolean isLogin() {
            return isLogin;
        }

        public void setLogin(boolean login) {
            isLogin = login;
        }

        public String getSignUrl() {
            return signUrl;
        }

        public void setSignUrl(String signUrl) {
            this.signUrl = signUrl;
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

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getHead_pic() {
            return head_pic;
        }

        public void setHead_pic(String head_pic) {
            this.head_pic = head_pic;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getIntegralNumber() {
            return integralNumber;
        }

        public void setIntegralNumber(int integralNumber) {
            this.integralNumber = integralNumber;
        }

        public int getUserClockNumber() {
            return userClockNumber;
        }

        public void setUserClockNumber(int userClockNumber) {
            this.userClockNumber = userClockNumber;
        }

        public int getUserClockCount() {
            return userClockCount;
        }

        public void setUserClockCount(int userClockCount) {
            this.userClockCount = userClockCount;
        }

        public String getIsClock() {
            return isClock;
        }

        public void setIsClock(String isClock) {
            this.isClock = isClock;
        }
    }

    public static class BannerBean extends BaseCourseBean{
        /**
         * id : 127
         * image_url : https://platform.hq-xl.com/city_image/2018/04/5ad84221e219e.jpg
         * url :
         */

        private int id;
        private String image_url;
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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class SubjectListBean {
        /**
         * subjectId : 5
         * title : 一睡再睡瑜伽
         * image_url : https://t.p.idyoga.cn/video_image/2018/11/5bf68c400a518.jpg
         */

        private int subjectId;
        private String title;
        private String image_url;
        private boolean isLabel;

        public boolean isLabel() {
            return isLabel;
        }

        public void setLabel(boolean label) {
            isLabel = label;
        }

        public int getSubjectId() {
            return subjectId;
        }

        public void setSubjectId(int subjectId) {
            this.subjectId = subjectId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }
    }

    public static class ShopListBean {
        /**
         * shop_id : 90728654
         * name : 天盈创意园瑜伽馆
         * logopath : https://admin.idyoga.cn/static/images/logo/shop_1990364977.png
         * address : 广州市天河区棠安路288号
         * is_verify : 0
         * sumCourse : null
         * compare : 0.21
         * lessonImage : https://admin.idyoga.cn/static/images/lesson/5b5572108345c.png
         * shopImage : null
         * studentMienImage : http://testyogabook.hq-xl.com/static/images/student_mien_image/5b8e4236894c3.jpg
         */

        private int shop_id;
        private String name;
        private String logopath;
        private String address;
        private int is_verify;
        private Object sumCourse;
        private String compare;
        private String lessonImage;
        private String shopImage;
        private String studentMienImage;

        public int getShop_id() {
            return shop_id;
        }

        public void setShop_id(int shop_id) {
            this.shop_id = shop_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogopath() {
            return logopath;
        }

        public void setLogopath(String logopath) {
            this.logopath = logopath;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getIs_verify() {
            return is_verify;
        }

        public void setIs_verify(int is_verify) {
            this.is_verify = is_verify;
        }

        public Object getSumCourse() {
            return sumCourse;
        }

        public void setSumCourse(Object sumCourse) {
            this.sumCourse = sumCourse;
        }

        public String getCompare() {
            return compare;
        }

        public void setCompare(String compare) {
            this.compare = compare;
        }

        public String getLessonImage() {
            return lessonImage;
        }

        public void setLessonImage(String lessonImage) {
            this.lessonImage = lessonImage;
        }

        public String getShopImage() {
            return shopImage;
        }

        public void setShopImage(String shopImage) {
            this.shopImage = shopImage;
        }

        public String getStudentMienImage() {
            return studentMienImage;
        }

        public void setStudentMienImage(String studentMienImage) {
            this.studentMienImage = studentMienImage;
        }
    }
}
