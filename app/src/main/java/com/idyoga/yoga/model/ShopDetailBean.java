package com.idyoga.yoga.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 文 件 名: ShopDetailBean
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/21
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class ShopDetailBean {

    /**
     * shopInfo : {"id":19287727,"name":"现联瑜伽馆","address":"天河","logopath":"http://testyogabook.hq-xl.com/static/images/logo/shop_1825848429.png","hotline":"123","mobile":"13300000000","longitude":"0.0000000","latitude":"0.0000000","payinfos_id":33,"province":1,"city":17,"area":4,"introduce":null,"is_verify":2,"compare":15509.23}
     * tutor : [{"id":105,"name":"林某","mobile":"15666669891","mailbox":"","sex":1,"image":"","introduce":""},{"id":108,"name":"林大师","mobile":"15622605889","mailbox":"020@163.com","sex":1,"image":"","introduce":""},{"id":113,"name":"方老师","mobile":"13377700001","mailbox":"020@163.com","sex":2,"image":"","introduce":"方老师"},{"id":116,"name":"领导企鹅","mobile":"13602868750","mailbox":"332817520@qq.com","sex":2,"image":"","introduce":"<p>领导企鹅<\/p>"},{"id":117,"name":"领导2","mobile":"13602868751","mailbox":"332817520@qq.com","sex":2,"image":"","introduce":""}]
     * recommendCourseList : [{"id":28488,"parent_shop_id":19287727,"shop_id":69633860,"shopName":"现联瑜伽馆","shopAddress":"天河","longitude":"0.0000000","latitude":"0.0000000","allAppNum":1,"is_pay":2,"price":"0.01","start":1529571600,"end":1529573400,"number":10,"tutorName":"李丽丽","tagName":null,"lessonName":"热瑜伽","lessonImage":"http://testyogabook.hq-xl.com/static/images/lesson/5a6eba643876b.jpg","classroomName":"瑜伽专业活动室"}]
     * hotCourseList : [{"id":28179,"start":"2018-06-27 14:30","end":"2018-06-27 15:10","start_time":1530081000,"end_time":1530083400,"price":"50.00","is_pay":2,"number":20,"tutorName":"杨猪猪","lessonName":"奇葩课","lessonImage":"http://testyogabook.hq-xl.com/static/images/lesson/5b2082d739409.jpg","classroomName":"邹家豪宅"},{"id":28180,"start":"2018-06-25 20:00","end":"2018-06-25 20:30","start_time":1529928000,"end_time":1529929800,"price":"0.00","is_pay":0,"number":10,"tutorName":"方老师","lessonName":"林大师蛇形瑜伽课","lessonImage":"https://admin.idyoga.cn/static/images/lesson/5ae2956e5cb82.jpg","classroomName":"4-201"},{"id":28181,"start":"2018-06-26 20:00","end":"2018-06-26 20:30","start_time":1530014400,"end_time":1530016200,"price":"0.00","is_pay":0,"number":10,"tutorName":"方老师","lessonName":"林大师蛇形瑜伽课","lessonImage":"https://admin.idyoga.cn/static/images/lesson/5ae2956e5cb82.jpg","classroomName":"4-201"},{"id":28182,"start":"2018-07-04 14:30","end":"2018-07-04 15:10","start_time":1530685800,"end_time":1530688200,"price":"50.00","is_pay":2,"number":20,"tutorName":"杨猪猪","lessonName":"奇葩课","lessonImage":"http://testyogabook.hq-xl.com/static/images/lesson/5b2082d739409.jpg","classroomName":"邹家豪宅"},{"id":28183,"start":"2018-07-02 20:00","end":"2018-07-02 20:30","start_time":1530532800,"end_time":1530534600,"price":"0.00","is_pay":0,"number":10,"tutorName":"方老师","lessonName":"林大师蛇形瑜伽课","lessonImage":"https://admin.idyoga.cn/static/images/lesson/5ae2956e5cb82.jpg","classroomName":"4-201"},{"id":28184,"start":"2018-07-03 20:00","end":"2018-07-03 20:30","start_time":1530619200,"end_time":1530621000,"price":"0.00","is_pay":0,"number":10,"tutorName":"方老师","lessonName":"林大师蛇形瑜伽课","lessonImage":"https://admin.idyoga.cn/static/images/lesson/5ae2956e5cb82.jpg","classroomName":"4-201"},{"id":28496,"start":"2018-06-22 20:00","end":"2018-06-22 20:30","start_time":1529668800,"end_time":1529670600,"price":"0.00","is_pay":0,"number":10,"tutorName":"方老师","lessonName":"林大师蛇形瑜伽课","lessonImage":"https://admin.idyoga.cn/static/images/lesson/5ae2956e5cb82.jpg","classroomName":"4-201"},{"id":28497,"start":"2018-06-23 20:00","end":"2018-06-23 20:30","start_time":1529755200,"end_time":1529757000,"price":"0.00","is_pay":0,"number":10,"tutorName":"方老师","lessonName":"林大师蛇形瑜伽课","lessonImage":"https://admin.idyoga.cn/static/images/lesson/5ae2956e5cb82.jpg","classroomName":"4-201"}]
     * shopCardList : [{"id":1160,"name":"小灰卡","category_id":1,"description":"小灰卡","price":"1.00","has_time":0,"has_term":1,"status":0,"home_status":0,"is_chain":1,"type":2,"annex_num":5,"is_deleted":0,"content":"小灰卡","use_shop_list":"现联瑜伽馆"},{"id":1153,"name":"林林卡","category_id":3,"description":"小灰灰卡","price":"30.00","has_time":30,"has_term":3,"status":0,"home_status":0,"is_chain":1,"type":2,"annex_num":5,"is_deleted":0,"content":"小灰灰卡","use_shop_list":"现联瑜伽馆,现联连锁店1"}]
     * shopComment : [{"userName":"小灰灰","head_pic":null,"lessonName":"测试课程","description":"课程很好","create_time":"1526875311","tutorName":"林某,林大师,方老师"},{"userName":"姜念","head_pic":null,"lessonName":"测试课程","description":"课程很好","create_time":"1526882465","tutorName":"林某,林大师,方老师"}]
     * chainShopList : [{"id":19287727,"long":"0.0000000","lat":"0.0000000","name":"现联瑜伽馆","logopath":"http://testyogabook.hq-xl.com/static/images/logo/shop_1825848429.png","address":"天河"},{"id":19641715,"long":"0.0000000","lat":"0.0000000","name":"现联连锁店1","logopath":"","address":"2042"},{"id":56833611,"long":"0.0000000","lat":"0.0000000","name":"jfdskfa","logopath":"","address":"dsa"},{"id":65280494,"long":"0.0000000","lat":"0.0000000","name":"afjsdkj","logopath":"https://admin.idyoga.cn/static/images/logo/shop_872606201.png","address":"fjask"},{"id":46349756,"long":"0.0000000","lat":"0.0000000","name":"震撼瑜伽馆","logopath":"http://testyogabook.hq-xl.com/static/images/logo/shop_1041615837.png","address":"侠飞路666号"}]
     * video : [{"id":22,"title":"体式入门动作基础系列一","time":30,"grade":2,"deplete":30,"description":"体式入门动作基础系列一","image_url":"https://platform.hq-xl.com/video_image/2018/03/5ab8af5b5d042.jpg","is_free":0,"price":"0.00","number":100,"efficacy":"修身","tutor":"林大师","videoLabel":[{"name":"限时免费"},{"name":"海外导师"},{"name":"强身健体"}]},{"id":23,"title":"体式入门动作基础系列二","time":30,"grade":2,"deplete":30,"description":"体式入门动作基础系列二","image_url":"https://platform.hq-xl.com/video_image/2018/03/5ab8af8779428.jpg","is_free":0,"price":"0.00","number":100,"efficacy":"修身","tutor":"林大师","videoLabel":[{"name":"限时免费"},{"name":"海外导师"},{"name":"强身健体"}]}]
     */

    private ShopInfoBean shopInfo;
    private List<TutorBean> tutor;
    private List<LessonListBean> mLessonList;
    private List<RecommendCourseListBean> recommendCourseList;
    private List<HotCourseListBean> hotCourseList;
    private List<ShopCardListBean> shopCardList;
    private List<ShopCommentBean> shopComment;
    private List<ChainShopListBean> chainShopList;
    private List<VideoBean> video;

    public ShopInfoBean getShopInfo() {
        return shopInfo;
    }

    public void setShopInfo(ShopInfoBean shopInfo) {
        this.shopInfo = shopInfo;
    }

    public List<TutorBean> getTutor() {
        return tutor;
    }

    public void setTutor(List<TutorBean> tutor) {
        this.tutor = tutor;
    }

    public List<RecommendCourseListBean> getRecommendCourseList() {
        return recommendCourseList;
    }

    public void setRecommendCourseList(List<RecommendCourseListBean> recommendCourseList) {
        this.recommendCourseList = recommendCourseList;
    }

    public List<HotCourseListBean> getHotCourseList() {
        return hotCourseList;
    }

    public void setHotCourseList(List<HotCourseListBean> hotCourseList) {
        this.hotCourseList = hotCourseList;
    }

    public List<ShopCardListBean> getShopCardList() {
        return shopCardList;
    }

    public void setShopCardList(List<ShopCardListBean> shopCardList) {
        this.shopCardList = shopCardList;
    }

    public List<ShopCommentBean> getShopComment() {
        return shopComment;
    }

    public void setShopComment(List<ShopCommentBean> shopComment) {
        this.shopComment = shopComment;
    }

    public List<ChainShopListBean> getChainShopList() {
        return chainShopList;
    }

    public void setChainShopList(List<ChainShopListBean> chainShopList) {
        this.chainShopList = chainShopList;
    }

    public List<LessonListBean> getLessonList() {
        return mLessonList;
    }

    public void setLessonList(List<LessonListBean> lessonList) {
        mLessonList = lessonList;
    }

    public List<VideoBean> getVideo() {
        return video;
    }

    public void setVideo(List<VideoBean> video) {
        this.video = video;
    }

    public static class LessonListBean{

        /**
         * id : 4219
         * name : 小小奇葩课_111
         * image : http://testyogabook.hq-xl.com/shop/lesson_cover/19287727/5b2cc3392be1d.jpg
         * time : 40
         */

        private int id;
        private String name;
        private String image;
        private int time;

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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }
    }
    public static class ShopInfoBean {
        /**
         * id : 19287727
         * name : 现联瑜伽馆
         * address : 天河
         * logopath : http://testyogabook.hq-xl.com/static/images/logo/shop_1825848429.png
         * hotline : 123
         * mobile : 13300000000
         * longitude : 0.0000000
         * latitude : 0.0000000
         * payinfos_id : 33
         * province : 1
         * city : 17
         * area : 4
         * introduce : null
         * is_verify : 2
         * compare : 15509.23
         */

        private int id;
        private String name;
        private String address;
        private String logopath;
        private String hotline;
        private String mobile;
        private String longitude;
        private String latitude;
        private int payinfos_id;
        private int province;
        private int city;
        private int area;
        private String introduce;
        private int is_verify;
        private double compare;

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

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
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

        public int getPayinfos_id() {
            return payinfos_id;
        }

        public void setPayinfos_id(int payinfos_id) {
            this.payinfos_id = payinfos_id;
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

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public int getIs_verify() {
            return is_verify;
        }

        public void setIs_verify(int is_verify) {
            this.is_verify = is_verify;
        }

        public double getCompare() {
            return compare;
        }

        public void setCompare(double compare) {
            this.compare = compare;
        }

        @Override
        public String toString() {
            return "ShopInfoBean{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    ", logopath='" + logopath + '\'' +
                    ", hotline='" + hotline + '\'' +
                    ", mobile='" + mobile + '\'' +
                    ", longitude='" + longitude + '\'' +
                    ", latitude='" + latitude + '\'' +
                    ", payinfos_id=" + payinfos_id +
                    ", province=" + province +
                    ", city=" + city +
                    ", area=" + area +
                    ", introduce=" + introduce +
                    ", is_verify=" + is_verify +
                    ", compare=" + compare +
                    '}';
        }
    }

    public static class TutorBean {
        /**
         * id : 105
         * name : 林某
         * mobile : 15666669891
         * mailbox :
         * sex : 1
         * image :
         * introduce :
         */

        private int id;
        private String name;
        private String mobile;
        private String mailbox;
        private int sex;
        private String image;
        private String introduce;

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

        public String getMailbox() {
            return mailbox;
        }

        public void setMailbox(String mailbox) {
            this.mailbox = mailbox;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }
    }

    public static class RecommendCourseListBean {
        /**
         * id : 28488
         * parent_shop_id : 19287727
         * shop_id : 69633860
         * shopName : 现联瑜伽馆
         * shopAddress : 天河
         * longitude : 0.0000000
         * latitude : 0.0000000
         * allAppNum : 1
         * is_pay : 2
         * price : 0.01
         * start : 1529571600
         * end : 1529573400
         * number : 10
         * tutorName : 李丽丽
         * tagName : null
         * lessonName : 热瑜伽
         * lessonImage : http://testyogabook.hq-xl.com/static/images/lesson/5a6eba643876b.jpg
         * classroomName : 瑜伽专业活动室
         */

        private int id;
        private int parent_shop_id;
        private int shop_id;
        private String shopName;
        private String shopAddress;
        private String longitude;
        private String latitude;
        private int allAppNum;
        private int is_pay;
        private String price;
        private int start;
        private int end;
        private int number;
        private String tutorName;
        private Object tagName;
        private String lessonName;
        private String lessonImage;
        private String classroomName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getParent_shop_id() {
            return parent_shop_id;
        }

        public void setParent_shop_id(int parent_shop_id) {
            this.parent_shop_id = parent_shop_id;
        }

        public int getShop_id() {
            return shop_id;
        }

        public void setShop_id(int shop_id) {
            this.shop_id = shop_id;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public String getShopAddress() {
            return shopAddress;
        }

        public void setShopAddress(String shopAddress) {
            this.shopAddress = shopAddress;
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

        public int getAllAppNum() {
            return allAppNum;
        }

        public void setAllAppNum(int allAppNum) {
            this.allAppNum = allAppNum;
        }

        public int getIs_pay() {
            return is_pay;
        }

        public void setIs_pay(int is_pay) {
            this.is_pay = is_pay;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getTutorName() {
            return tutorName;
        }

        public void setTutorName(String tutorName) {
            this.tutorName = tutorName;
        }

        public Object getTagName() {
            return tagName;
        }

        public void setTagName(Object tagName) {
            this.tagName = tagName;
        }

        public String getLessonName() {
            return lessonName;
        }

        public void setLessonName(String lessonName) {
            this.lessonName = lessonName;
        }

        public String getLessonImage() {
            return lessonImage;
        }

        public void setLessonImage(String lessonImage) {
            this.lessonImage = lessonImage;
        }

        public String getClassroomName() {
            return classroomName;
        }

        public void setClassroomName(String classroomName) {
            this.classroomName = classroomName;
        }
    }

    public static class HotCourseListBean {
        /**
         * id : 28179
         * start : 2018-06-27 14:30
         * end : 2018-06-27 15:10
         * start_time : 1530081000
         * end_time : 1530083400
         * price : 50.00
         * is_pay : 2
         * number : 20
         * tutorName : 杨猪猪
         * lessonName : 奇葩课
         * lessonImage : http://testyogabook.hq-xl.com/static/images/lesson/5b2082d739409.jpg
         * classroomName : 邹家豪宅
         */

        private int id;
        private String start;
        private String end;
        private int start_time;
        private int end_time;
        private String price;
        private int is_pay;
        private int number;
        private String tutorName;
        private String lessonName;
        private String lessonImage;
        private String classroomName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
        }

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getIs_pay() {
            return is_pay;
        }

        public void setIs_pay(int is_pay) {
            this.is_pay = is_pay;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getTutorName() {
            return tutorName;
        }

        public void setTutorName(String tutorName) {
            this.tutorName = tutorName;
        }

        public String getLessonName() {
            return lessonName;
        }

        public void setLessonName(String lessonName) {
            this.lessonName = lessonName;
        }

        public String getLessonImage() {
            return lessonImage;
        }

        public void setLessonImage(String lessonImage) {
            this.lessonImage = lessonImage;
        }

        public String getClassroomName() {
            return classroomName;
        }

        public void setClassroomName(String classroomName) {
            this.classroomName = classroomName;
        }
    }

    public static class ShopCardListBean {
        /**
         * id : 1160
         * name : 小灰卡
         * category_id : 1
         * description : 小灰卡
         * price : 1.00
         * has_time : 0
         * has_term : 1
         * status : 0
         * home_status : 0
         * is_chain : 1
         * type : 2
         * annex_num : 5
         * is_deleted : 0
         * content : 小灰卡
         * use_shop_list : 现联瑜伽馆
         */

        private int id;
        private String name;
        private int category_id;
        private String description;
        private String price;
        private int has_time;
        private int has_term;
        private int status;
        private int home_status;
        private int is_chain;
        private int type;
        private int annex_num;
        private int is_deleted;
        private String content;
        private String use_shop_list;

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

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getHas_time() {
            return has_time;
        }

        public void setHas_time(int has_time) {
            this.has_time = has_time;
        }

        public int getHas_term() {
            return has_term;
        }

        public void setHas_term(int has_term) {
            this.has_term = has_term;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getHome_status() {
            return home_status;
        }

        public void setHome_status(int home_status) {
            this.home_status = home_status;
        }

        public int getIs_chain() {
            return is_chain;
        }

        public void setIs_chain(int is_chain) {
            this.is_chain = is_chain;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getAnnex_num() {
            return annex_num;
        }

        public void setAnnex_num(int annex_num) {
            this.annex_num = annex_num;
        }

        public int getIs_deleted() {
            return is_deleted;
        }

        public void setIs_deleted(int is_deleted) {
            this.is_deleted = is_deleted;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUse_shop_list() {
            return use_shop_list;
        }

        public void setUse_shop_list(String use_shop_list) {
            this.use_shop_list = use_shop_list;
        }
    }

    public static class ShopCommentBean {
        /**
         * userName : 小灰灰
         * head_pic : null
         * lessonName : 测试课程
         * description : 课程很好
         * create_time : 1526875311
         * tutorName : 林某,林大师,方老师
         */

        private String userName;
        private Object head_pic;
        private String lessonName;
        private String description;
        private String create_time;
        private String tutorName;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Object getHead_pic() {
            return head_pic;
        }

        public void setHead_pic(Object head_pic) {
            this.head_pic = head_pic;
        }

        public String getLessonName() {
            return lessonName;
        }

        public void setLessonName(String lessonName) {
            this.lessonName = lessonName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getTutorName() {
            return tutorName;
        }

        public void setTutorName(String tutorName) {
            this.tutorName = tutorName;
        }
    }

    public static class ChainShopListBean {
        /**
         * id : 19287727
         * long : 0.0000000
         * lat : 0.0000000
         * name : 现联瑜伽馆
         * logopath : http://testyogabook.hq-xl.com/static/images/logo/shop_1825848429.png
         * address : 天河
         */

        private int id;
        @SerializedName("long")
        private String longX;
        private String lat;
        private String name;
        private String logopath;
        private String address;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLongX() {
            return longX;
        }

        public void setLongX(String longX) {
            this.longX = longX;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
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
    }

    public static class VideoBean {
        /**
         * id : 22
         * title : 体式入门动作基础系列一
         * time : 30
         * grade : 2
         * deplete : 30
         * description : 体式入门动作基础系列一
         * image_url : https://platform.hq-xl.com/video_image/2018/03/5ab8af5b5d042.jpg
         * is_free : 0
         * price : 0.00
         * number : 100
         * efficacy : 修身
         * tutor : 林大师
         * videoLabel : [{"name":"限时免费"},{"name":"海外导师"},{"name":"强身健体"}]
         */

        private int id;
        private String title;
        private int time;
        private int grade;
        private int deplete;
        private String description;
        private String image_url;
        private int is_free;
        private String price;
        private int number;
        private String efficacy;
        private String tutor;
        private List<VideoLabelBean> videoLabel;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public int getGrade() {
            return grade;
        }

        public void setGrade(int grade) {
            this.grade = grade;
        }

        public int getDeplete() {
            return deplete;
        }

        public void setDeplete(int deplete) {
            this.deplete = deplete;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public int getIs_free() {
            return is_free;
        }

        public void setIs_free(int is_free) {
            this.is_free = is_free;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getEfficacy() {
            return efficacy;
        }

        public void setEfficacy(String efficacy) {
            this.efficacy = efficacy;
        }

        public String getTutor() {
            return tutor;
        }

        public void setTutor(String tutor) {
            this.tutor = tutor;
        }

        public List<VideoLabelBean> getVideoLabel() {
            return videoLabel;
        }

        public void setVideoLabel(List<VideoLabelBean> videoLabel) {
            this.videoLabel = videoLabel;
        }

        public static class VideoLabelBean {
            /**
             * name : 限时免费
             */

            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
