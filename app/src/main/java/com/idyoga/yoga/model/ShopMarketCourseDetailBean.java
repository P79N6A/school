package com.idyoga.yoga.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 文 件 名: ShopMarketCourseBean
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/22
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class ShopMarketCourseDetailBean {

    /**
     * courseDetail : {"id":29023,"shop_id":63684083,"parent_shop_id":30687049,"lesson_id":399,"start_time":1546048800,"end_time":1546052400,"classroom_id":195,"number":10,"create_time":1529909324,"update_time":1529909324,"is_deleted":0,"cancel_use_time":0,"course_color":null,"limit_number":0,"limit_cencel_time":0,"is_pay":2,"price":"0.00","is_recommend":0,"appointment_limit_time":0,"use_points":0,"is_auto_cancel":0,"auto_cancel_check_time":0,"auto_cancel_check_number":0,"cancel_status":0,"coursesn":null,"is_platform_course":0,"platform_number":0,"start":1546048800,"end":1546052400,"lessonName":"肌肉伸展","lessonId":399,"lessonTime":60,"lessonImg":"/static/images/lesson/5ad5a39e55ecc.jpg","introduce":"1.目前以上课程的日期与时间仅做展示使用，在您预约成功后，IDYoga平台客服将在2小时内与您联系确认课程需求\r\n2.为提供更优质的服务，所有预约流程均提供客服专线答疑，如有疑问可致电400-902-0929","lessonContent":"","classroomName":"大班课室","classroomId":195,"sumNumber":10,"residueNumber":8,"tag":[{"id":117,"name":"形体肌肉系列"}],"tutor":[{"id":231,"name":"张老师","image":"","mainstream":[]}]}
     * shopInfo : {"id":63684083,"name":"广州","address":"123","logopath":"http://testyogabook.hq-xl.com/static/images/logo/shop_1455076031.png","hotline":"123","merid":0,"payinfos_id":33,"mobile":"13500000000","is_for_platform":0,"chain_id":214,"longitude":"0.0000000","latitude":"0.0000000","province":1,"city":17,"area":4,"introduce":null,"is_verify":2,"shop_code":null,"secret_key":null,"uniacid":null,"renren_shop_name":null,"is_sex":0,"is_sign":0,"compare":12387.61}
     * courseAppList : [{"username":"陈济旺","avatar_url":null,"headpicUrl":null,"nickname":null},{"username":"罗晨","avatar_url":null,"headpicUrl":null,"nickname":null}]
     */

    private CourseDetailBean courseDetail;
    private ShopInfoBean shopInfo;
    private List<CourseAppListBean> courseAppList;

    public CourseDetailBean getCourseDetail() {
        return courseDetail;
    }

    public void setCourseDetail(CourseDetailBean courseDetail) {
        this.courseDetail = courseDetail;
    }

    public ShopInfoBean getShopInfo() {
        return shopInfo;
    }

    public void setShopInfo(ShopInfoBean shopInfo) {
        this.shopInfo = shopInfo;
    }

    public List<CourseAppListBean> getCourseAppList() {
        return courseAppList;
    }

    public void setCourseAppList(List<CourseAppListBean> courseAppList) {
        this.courseAppList = courseAppList;
    }


    public static class CourseDetailBean implements Parcelable {
        /**
         * id : 29023
         * shop_id : 63684083
         * parent_shop_id : 30687049
         * lesson_id : 399
         * start_time : 1546048800
         * end_time : 1546052400
         * classroom_id : 195
         * number : 10
         * create_time : 1529909324
         * update_time : 1529909324
         * is_deleted : 0
         * cancel_use_time : 0
         * course_color : null
         * limit_number : 0
         * limit_cencel_time : 0
         * is_pay : 2
         * price : 0.00
         * is_recommend : 0
         * appointment_limit_time : 0
         * use_points : 0
         * is_auto_cancel : 0
         * auto_cancel_check_time : 0
         * auto_cancel_check_number : 0
         * cancel_status : 0
         * coursesn : null
         * is_platform_course : 0
         * platform_number : 0
         * start : 1546048800
         * end : 1546052400
         * lessonName : 肌肉伸展
         * lessonId : 399
         * lessonTime : 60
         * lessonImg : /static/images/lesson/5ad5a39e55ecc.jpg
         * introduce : 1.目前以上课程的日期与时间仅做展示使用，在您预约成功后，IDYoga平台客服将在2小时内与您联系确认课程需求
         2.为提供更优质的服务，所有预约流程均提供客服专线答疑，如有疑问可致电400-902-0929
         * lessonContent :
         * classroomName : 大班课室
         * classroomId : 195
         * sumNumber : 10
         * residueNumber : 8
         * tag : [{"id":117,"name":"形体肌肉系列"}]
         * tutor : [{"id":231,"name":"张老师","image":"","mainstream":[]}]
         */

        private int id;
        private int shop_id;
        private int parent_shop_id;
        private int lesson_id;
        private int start_time;
        private int end_time;
        private int classroom_id;
        private int number;
        private int create_time;
        private int update_time;
        private int is_deleted;
        private int cancel_use_time;
        private Object course_color;
        private int limit_number;
        private int limit_cencel_time;
        private int is_pay;
        private String price;
        private int is_recommend;
        private int appointment_limit_time;
        private int use_points;
        private int is_auto_cancel;
        private int auto_cancel_check_time;
        private int auto_cancel_check_number;
        private int cancel_status;
        private Object coursesn;
        private int is_platform_course;
        private int platform_number;
        private int start;
        private int end;
        private String lessonName;
        private int lessonId;
        private int lessonTime;
        private String lessonImg;
        private String introduce;
        private String lessonContent;
        private String classroomName;
        private int classroomId;
        private int sumNumber;
        private int residueNumber;
        private List<TagBean> tag;
        private List<TutorBean> tutor;


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

        public int getParent_shop_id() {
            return parent_shop_id;
        }

        public void setParent_shop_id(int parent_shop_id) {
            this.parent_shop_id = parent_shop_id;
        }

        public int getLesson_id() {
            return lesson_id;
        }

        public void setLesson_id(int lesson_id) {
            this.lesson_id = lesson_id;
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

        public int getClassroom_id() {
            return classroom_id;
        }

        public void setClassroom_id(int classroom_id) {
            this.classroom_id = classroom_id;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
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

        public int getIs_deleted() {
            return is_deleted;
        }

        public void setIs_deleted(int is_deleted) {
            this.is_deleted = is_deleted;
        }

        public int getCancel_use_time() {
            return cancel_use_time;
        }

        public void setCancel_use_time(int cancel_use_time) {
            this.cancel_use_time = cancel_use_time;
        }

        public Object getCourse_color() {
            return course_color;
        }

        public void setCourse_color(Object course_color) {
            this.course_color = course_color;
        }

        public int getLimit_number() {
            return limit_number;
        }

        public void setLimit_number(int limit_number) {
            this.limit_number = limit_number;
        }

        public int getLimit_cencel_time() {
            return limit_cencel_time;
        }

        public void setLimit_cencel_time(int limit_cencel_time) {
            this.limit_cencel_time = limit_cencel_time;
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

        public int getIs_recommend() {
            return is_recommend;
        }

        public void setIs_recommend(int is_recommend) {
            this.is_recommend = is_recommend;
        }

        public int getAppointment_limit_time() {
            return appointment_limit_time;
        }

        public void setAppointment_limit_time(int appointment_limit_time) {
            this.appointment_limit_time = appointment_limit_time;
        }

        public int getUse_points() {
            return use_points;
        }

        public void setUse_points(int use_points) {
            this.use_points = use_points;
        }

        public int getIs_auto_cancel() {
            return is_auto_cancel;
        }

        public void setIs_auto_cancel(int is_auto_cancel) {
            this.is_auto_cancel = is_auto_cancel;
        }

        public int getAuto_cancel_check_time() {
            return auto_cancel_check_time;
        }

        public void setAuto_cancel_check_time(int auto_cancel_check_time) {
            this.auto_cancel_check_time = auto_cancel_check_time;
        }

        public int getAuto_cancel_check_number() {
            return auto_cancel_check_number;
        }

        public void setAuto_cancel_check_number(int auto_cancel_check_number) {
            this.auto_cancel_check_number = auto_cancel_check_number;
        }

        public int getCancel_status() {
            return cancel_status;
        }

        public void setCancel_status(int cancel_status) {
            this.cancel_status = cancel_status;
        }

        public Object getCoursesn() {
            return coursesn;
        }

        public void setCoursesn(Object coursesn) {
            this.coursesn = coursesn;
        }

        public int getIs_platform_course() {
            return is_platform_course;
        }

        public void setIs_platform_course(int is_platform_course) {
            this.is_platform_course = is_platform_course;
        }

        public int getPlatform_number() {
            return platform_number;
        }

        public void setPlatform_number(int platform_number) {
            this.platform_number = platform_number;
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

        public String getLessonName() {
            return lessonName;
        }

        public void setLessonName(String lessonName) {
            this.lessonName = lessonName;
        }

        public int getLessonId() {
            return lessonId;
        }

        public void setLessonId(int lessonId) {
            this.lessonId = lessonId;
        }

        public int getLessonTime() {
            return lessonTime;
        }

        public void setLessonTime(int lessonTime) {
            this.lessonTime = lessonTime;
        }

        public String getLessonImg() {
            return lessonImg;
        }

        public void setLessonImg(String lessonImg) {
            this.lessonImg = lessonImg;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getLessonContent() {
            return lessonContent;
        }

        public void setLessonContent(String lessonContent) {
            this.lessonContent = lessonContent;
        }

        public String getClassroomName() {
            return classroomName;
        }

        public void setClassroomName(String classroomName) {
            this.classroomName = classroomName;
        }

        public int getClassroomId() {
            return classroomId;
        }

        public void setClassroomId(int classroomId) {
            this.classroomId = classroomId;
        }

        public int getSumNumber() {
            return sumNumber;
        }

        public void setSumNumber(int sumNumber) {
            this.sumNumber = sumNumber;
        }

        public int getResidueNumber() {
            return residueNumber;
        }

        public void setResidueNumber(int residueNumber) {
            this.residueNumber = residueNumber;
        }

        public List<TagBean> getTag() {
            return tag;
        }

        public void setTag(List<TagBean> tag) {
            this.tag = tag;
        }

        public List<TutorBean> getTutor() {
            return tutor;
        }

        public void setTutor(List<TutorBean> tutor) {
            this.tutor = tutor;
        }

        public static class TagBean {
            /**
             * id : 117
             * name : 形体肌肉系列
             */

            private int id;
            private String name;

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
        }

        public static class TutorBean {
            /**
             * id : 231
             * name : 张老师
             * image :
             * mainstream : []
             */

            private int id;
            private String name;
            private String image;
            private List<Mainstream> mainstream;

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

            public List<Mainstream> getMainstream() {
                return mainstream;
            }

            public void setMainstream(List<Mainstream> mainstream) {
                this.mainstream = mainstream;
            }
            public static class Mainstream{

                /**
                 * id : 1089
                 * mainstream_id : 9
                 * mainstreamName : 智瑜伽
                 */

                private int id;
                private int mainstream_id;
                private String mainstreamName;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getMainstream_id() {
                    return mainstream_id;
                }

                public void setMainstream_id(int mainstream_id) {
                    this.mainstream_id = mainstream_id;
                }

                public String getMainstreamName() {
                    return mainstreamName;
                }

                public void setMainstreamName(String mainstreamName) {
                    this.mainstreamName = mainstreamName;
                }

                @Override
                public String toString() {
                    return "Mainstream{" +
                            "id=" + id +
                            ", mainstream_id=" + mainstream_id +
                            ", mainstreamName='" + mainstreamName + '\'' +
                            '}';
                }
            }
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeInt(this.shop_id);
            dest.writeInt(this.parent_shop_id);
            dest.writeInt(this.lesson_id);
            dest.writeInt(this.start_time);
            dest.writeInt(this.end_time);
            dest.writeInt(this.classroom_id);
            dest.writeInt(this.number);
            dest.writeInt(this.create_time);
            dest.writeInt(this.update_time);
            dest.writeInt(this.is_deleted);
            dest.writeInt(this.cancel_use_time);
            dest.writeInt(this.limit_number);
            dest.writeInt(this.limit_cencel_time);
            dest.writeInt(this.is_pay);
            dest.writeString(this.price);
            dest.writeInt(this.is_recommend);
            dest.writeInt(this.appointment_limit_time);
            dest.writeInt(this.use_points);
            dest.writeInt(this.is_auto_cancel);
            dest.writeInt(this.auto_cancel_check_time);
            dest.writeInt(this.auto_cancel_check_number);
            dest.writeInt(this.cancel_status);
            dest.writeInt(this.is_platform_course);
            dest.writeInt(this.platform_number);
            dest.writeInt(this.start);
            dest.writeInt(this.end);
            dest.writeString(this.lessonName);
            dest.writeInt(this.lessonId);
            dest.writeInt(this.lessonTime);
            dest.writeString(this.lessonImg);
            dest.writeString(this.introduce);
            dest.writeString(this.lessonContent);
            dest.writeString(this.classroomName);
            dest.writeInt(this.classroomId);
            dest.writeInt(this.sumNumber);
            dest.writeInt(this.residueNumber);
            dest.writeList(this.tag);
            dest.writeList(this.tutor);
        }

        public CourseDetailBean() {
        }

        protected CourseDetailBean(Parcel in) {
            this.id = in.readInt();
            this.shop_id = in.readInt();
            this.parent_shop_id = in.readInt();
            this.lesson_id = in.readInt();
            this.start_time = in.readInt();
            this.end_time = in.readInt();
            this.classroom_id = in.readInt();
            this.number = in.readInt();
            this.create_time = in.readInt();
            this.update_time = in.readInt();
            this.is_deleted = in.readInt();
            this.cancel_use_time = in.readInt();
            this.course_color = in.readParcelable(Object.class.getClassLoader());
            this.limit_number = in.readInt();
            this.limit_cencel_time = in.readInt();
            this.is_pay = in.readInt();
            this.price = in.readString();
            this.is_recommend = in.readInt();
            this.appointment_limit_time = in.readInt();
            this.use_points = in.readInt();
            this.is_auto_cancel = in.readInt();
            this.auto_cancel_check_time = in.readInt();
            this.auto_cancel_check_number = in.readInt();
            this.cancel_status = in.readInt();
            this.coursesn = in.readParcelable(Object.class.getClassLoader());
            this.is_platform_course = in.readInt();
            this.platform_number = in.readInt();
            this.start = in.readInt();
            this.end = in.readInt();
            this.lessonName = in.readString();
            this.lessonId = in.readInt();
            this.lessonTime = in.readInt();
            this.lessonImg = in.readString();
            this.introduce = in.readString();
            this.lessonContent = in.readString();
            this.classroomName = in.readString();
            this.classroomId = in.readInt();
            this.sumNumber = in.readInt();
            this.residueNumber = in.readInt();
            this.tag = new ArrayList<TagBean>();
            in.readList(this.tag, TagBean.class.getClassLoader());
            this.tutor = new ArrayList<TutorBean>();
            in.readList(this.tutor, TutorBean.class.getClassLoader());
        }

        public static final Creator<CourseDetailBean> CREATOR = new Creator<CourseDetailBean>() {
            @Override
            public CourseDetailBean createFromParcel(Parcel source) {
                return new CourseDetailBean(source);
            }

            @Override
            public CourseDetailBean[] newArray(int size) {
                return new CourseDetailBean[size];
            }
        };
    }

    public static class ShopInfoBean implements Parcelable {
        /**
         * id : 63684083
         * name : 广州
         * address : 123
         * logopath : http://testyogabook.hq-xl.com/static/images/logo/shop_1455076031.png
         * hotline : 123
         * merid : 0
         * payinfos_id : 33
         * mobile : 13500000000
         * is_for_platform : 0
         * chain_id : 214
         * longitude : 0.0000000
         * latitude : 0.0000000
         * province : 1
         * city : 17
         * area : 4
         * introduce : null
         * is_verify : 2
         * shop_code : null
         * secret_key : null
         * uniacid : null
         * renren_shop_name : null
         * is_sex : 0
         * is_sign : 0
         * compare : 12387.61
         */


        private int id;
        private String name;
        private String address;
        private String logopath;
        private String hotline;
        private int merid;
        private int payinfos_id;
        private String mobile;
        private int is_for_platform;
        private int chain_id;
        private String longitude;
        private String latitude;
        private int province;
        private int city;
        private int area;
        private Object introduce;
        private int is_verify;
        private Object shop_code;
        private Object secret_key;
        private Object uniacid;
        private Object renren_shop_name;
        private int is_sex;
        private int is_sign;
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

        public int getMerid() {
            return merid;
        }

        public void setMerid(int merid) {
            this.merid = merid;
        }

        public int getPayinfos_id() {
            return payinfos_id;
        }

        public void setPayinfos_id(int payinfos_id) {
            this.payinfos_id = payinfos_id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getIs_for_platform() {
            return is_for_platform;
        }

        public void setIs_for_platform(int is_for_platform) {
            this.is_for_platform = is_for_platform;
        }

        public int getChain_id() {
            return chain_id;
        }

        public void setChain_id(int chain_id) {
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

        public Object getIntroduce() {
            return introduce;
        }

        public void setIntroduce(Object introduce) {
            this.introduce = introduce;
        }

        public int getIs_verify() {
            return is_verify;
        }

        public void setIs_verify(int is_verify) {
            this.is_verify = is_verify;
        }

        public Object getShop_code() {
            return shop_code;
        }

        public void setShop_code(Object shop_code) {
            this.shop_code = shop_code;
        }

        public Object getSecret_key() {
            return secret_key;
        }

        public void setSecret_key(Object secret_key) {
            this.secret_key = secret_key;
        }

        public Object getUniacid() {
            return uniacid;
        }

        public void setUniacid(Object uniacid) {
            this.uniacid = uniacid;
        }

        public Object getRenren_shop_name() {
            return renren_shop_name;
        }

        public void setRenren_shop_name(Object renren_shop_name) {
            this.renren_shop_name = renren_shop_name;
        }

        public int getIs_sex() {
            return is_sex;
        }

        public void setIs_sex(int is_sex) {
            this.is_sex = is_sex;
        }

        public int getIs_sign() {
            return is_sign;
        }

        public void setIs_sign(int is_sign) {
            this.is_sign = is_sign;
        }

        public double getCompare() {
            return compare;
        }

        public void setCompare(double compare) {
            this.compare = compare;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.name);
            dest.writeString(this.address);
            dest.writeString(this.logopath);
            dest.writeString(this.hotline);
            dest.writeInt(this.merid);
            dest.writeInt(this.payinfos_id);
            dest.writeString(this.mobile);
            dest.writeInt(this.is_for_platform);
            dest.writeInt(this.chain_id);
            dest.writeString(this.longitude);
            dest.writeString(this.latitude);
            dest.writeInt(this.province);
            dest.writeInt(this.city);
            dest.writeInt(this.area);
            dest.writeInt(this.is_verify);
            dest.writeInt(this.is_sex);
            dest.writeInt(this.is_sign);
            dest.writeDouble(this.compare);
        }

        public ShopInfoBean() {
        }

        protected ShopInfoBean(Parcel in) {
            this.id = in.readInt();
            this.name = in.readString();
            this.address = in.readString();
            this.logopath = in.readString();
            this.hotline = in.readString();
            this.merid = in.readInt();
            this.payinfos_id = in.readInt();
            this.mobile = in.readString();
            this.is_for_platform = in.readInt();
            this.chain_id = in.readInt();
            this.longitude = in.readString();
            this.latitude = in.readString();
            this.province = in.readInt();
            this.city = in.readInt();
            this.area = in.readInt();
            this.introduce = in.readParcelable(Object.class.getClassLoader());
            this.is_verify = in.readInt();
            this.shop_code = in.readParcelable(Object.class.getClassLoader());
            this.secret_key = in.readParcelable(Object.class.getClassLoader());
            this.uniacid = in.readParcelable(Object.class.getClassLoader());
            this.renren_shop_name = in.readParcelable(Object.class.getClassLoader());
            this.is_sex = in.readInt();
            this.is_sign = in.readInt();
            this.compare = in.readDouble();
        }

        public static final Creator<ShopInfoBean> CREATOR = new Creator<ShopInfoBean>() {
            @Override
            public ShopInfoBean createFromParcel(Parcel source) {
                return new ShopInfoBean(source);
            }

            @Override
            public ShopInfoBean[] newArray(int size) {
                return new ShopInfoBean[size];
            }
        };
    }

    public static class CourseAppListBean {
        /**
         * username : 陈济旺
         * avatar_url : null
         * headpicUrl : null
         * nickname : null
         */

        private String username;
        private String avatar_url;
        private String headpicUrl;
        private String nickname;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public String getHeadpicUrl() {
            return headpicUrl;
        }

        public void setHeadpicUrl(String headpicUrl) {
            this.headpicUrl = headpicUrl;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }


}
