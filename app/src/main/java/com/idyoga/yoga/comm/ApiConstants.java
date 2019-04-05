/**
 * ****************************** Copyright (c)*********************************\
 * *
 * *                 (c) Copyright 2017, DevKit.vip, china, qd. sd
 * *                          All Rights Reserved
 * *
 * *                           By(K)
 * *
 * *-----------------------------------版本信息------------------------------------
 * * 版    本: V0.1
 * *
 * *------------------------------------------------------------------------------
 * *******************************End of Head************************************\
 */
package com.idyoga.yoga.comm;


/**
 * 文 件 名: ApiConstants
 * 创 建 人: By k
 * 创建日期: 2018/3/25 16:00
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注：
 */
public class ApiConstants {
    public static final class Urls {
        public static final String BASE_URL_LOCAL = "http://api.idyoga.cn/mall";//本地
        public static final String BASE_URL_TEST = "http://api.idyoga.cn/mall/";//测试
//        public static final String BASE_URL_PRODUCTION = "https://platform.hq-xl.com/mall";//生产
        public static final String BASE_URL_PRODUCTION = "https://p.idyoga.cn/mall";//生产

        public static final String TUTOR_BASE_URL = "http://testyogabook.hq-xl.com/mall";
        //权益课 开发环境
        public static final String COURSE_BASE_URL = "http://testyogabook.hq-xl.com/";//yoga_college/";

//        public static final String TUTOR_BASE_URL = "https://p.idyoga.cn/mall";

        public static final String BASE_URL = BASE_URL_LOCAL;


        public static final String GET_CITY = BASE_URL_PRODUCTION + "/Region/appCityList";
        public static final String HOME_SUBJECT = BASE_URL_PRODUCTION + "/Yoga_college/recommendIndex";//

        public static final String HOME_SUBJECT_CLOCK = TUTOR_BASE_URL + "/Shop/userClock";//

        public static final String HOME_SUBJECT_ITEM = BASE_URL_PRODUCTION + "/Index_group/indexGroupInfo";//
        public final static String HOME_SUBJECT_GET_VIDEOID = BASE_URL_PRODUCTION + "/video/getVideoUrlByVideoId"; //专题

        public static final String HOME_SUBJECT_1 = BASE_URL + "/App_user_data/orderCenter";//

        public static final String CHECKVERSION = BASE_URL_PRODUCTION + "/Index_group/versionsUpdate";// 应用更新


        public static final String ADDRESS = TUTOR_BASE_URL + "/Region/index";

        public static final String TUTOR_LIST = TUTOR_BASE_URL + "/Shop/getTutorByMainstreamList";

        public static final String TUTOR_SELECT = TUTOR_BASE_URL + "/Shop/getMainstreamList";

        public static final String SEARCH_LIST = TUTOR_BASE_URL + "/Yoga_market/keywordSearch";
        public static final String SEARCH_TAG_LIST = TUTOR_BASE_URL + "/Shop/hotSeek";

        public static final String USER_CARD_LIST = BASE_URL_LOCAL + "/Rights_card/RightsCardList";
        public static final String USER_CARD_DETAILS = BASE_URL_LOCAL + "/Rights_card/getRightsCard";
        public static final String USER_CARD_CHILD = BASE_URL_LOCAL + "/User/getShopCardDetail";
        public static final String USER_CARD_PERMISSION = TUTOR_BASE_URL + "/platformcard/appGetPlatformCardLesson";
        public static final String USER_CARD_PERMISSION_V2 = TUTOR_BASE_URL + "/platformcard/appGetPlatformCardCity";

        public static final String HOME_EXPERIENCE = TUTOR_BASE_URL + "/Market/getMarketInfo";
        public static final String HOME_EXPERIENCE_V2 = TUTOR_BASE_URL + "/Yoga_market/yogaMarketHomePageInfo";
        public static final String HOME_EXPERIENCE_SHOP = TUTOR_BASE_URL + "/Market/getMarketShop";
        public static final String HOME_EXPERIENCE_COURSE = TUTOR_BASE_URL + "/Market/getMarketCourse";
        public static final String HOME_EXPERIENCE_CLASS = TUTOR_BASE_URL + "/Market/getMarketTag";
        public static final String HOME_EXPERIENCE_CLASS_V2 = TUTOR_BASE_URL + "/Yoga_market/getYogaClassifyList";
        public static final String HOME_EXPERIENCE_CLASS_LIST = TUTOR_BASE_URL + "/Market/getMarketByTag";

        public static final String SHOP_OFFLINE_COURSE_ALL = TUTOR_BASE_URL + "/Shop/shopOfflineCourseList";
        public final static String CARD_CHILD_ADD = TUTOR_BASE_URL + "/Rights_card/addAuxiliaryUserCard";
        public final static String CARD_CHILD_ADD_V2 = TUTOR_BASE_URL + "/rights_card/bandAuxiliaryCard";
        public final static String CARD_CHILD_ADD_GET_CODE = TUTOR_BASE_URL + "/App_user_data/loginSendSms";

        public final static String VIDEO_CLASSIFY = BASE_URL_PRODUCTION + "/label/getLabelList";
        public final static String VIDEO_CLASSIFY_LIST = BASE_URL_PRODUCTION + "/Videolesson/getVideoByClassify";

        public final static String USER_UPDATE_INFO = TUTOR_BASE_URL + "/App_user_data/updateUserData";

        public final static String HOME_EXPERIENCE_CLASS_SHOP = TUTOR_BASE_URL + "/Shop/getCityShopList";
        public final static String HOME_EXPERIENCE_CLASS_COURSE = TUTOR_BASE_URL + "/Market/getMarketByTag";

        public final static String VIDEO_COURSE_LIST = BASE_URL_PRODUCTION + "/Videolesson/getVideolessonIndex";


        public final static String USER_APPOINTMENT_VIDEO_COURSE = BASE_URL_PRODUCTION + "/Videolesson/userSubscriptionVideo";
        public final static String USER_CANCEL_APPOINTMENT_VIDEO_COURSE = BASE_URL_PRODUCTION + "/Videolesson/saveUserVideo";
        public final static String USER_APPOINTMENT_VIDEO_COURSE_LIST = BASE_URL_PRODUCTION + "/Videolesson/getUserSubscriptionVideoList";

        public final static String USER_CARD_CHILD_LIST = TUTOR_BASE_URL + "/Rights_card/getAuxiliaryCard";
        public final static String USER_CARD_CHILD_LIST_V2 = TUTOR_BASE_URL + "/rights_card/getMyAuxiliaryCardList";

        public final static String UPLOAD_IMG = TUTOR_BASE_URL + "/App_user_data/uploadingImage";
        public final static String USER_UPDATE_PWD = TUTOR_BASE_URL + "/App_user_data/updateUserPasswordByCode";


        public static final String USER_COURSE_DETAIL = TUTOR_BASE_URL + "/shop/courseDetail";

        public static final String USER_CANCEL_COURSE_V2_1 = TUTOR_BASE_URL + "/palt_form_course_api/cancelReservationCourse";
        public static final String USER_CANCEL_COURSE_V2_2 = TUTOR_BASE_URL + "/course_api/cancelAppointmentOrder";


        public static final String SHOP_DETAIL = BASE_URL_PRODUCTION + "/Yoga_college/shopYogaInfo";

        public static final String SHOP_MARKET_COURSE_LIST = TUTOR_BASE_URL + "/Lesson/getAllList";

        public static final String SHOP_TUTOR_LIST = TUTOR_BASE_URL + "/Shop/getShopTutor";

        public static final String SHOP_VIDEO_COURSE_LIST = TUTOR_BASE_URL + "/Yoga_college/shopVideoLessonList";


        public static final String SHOP_MARKET_COURSE_INFO = TUTOR_BASE_URL + "/Lesson/appLessonInfo";

        public static final String SHOP_MARKET_COURSE_DETAILS = TUTOR_BASE_URL + "/shop/courseDetail";

        public static final String SHOP_MARKET_COURSE_DETAILS_V2 = TUTOR_BASE_URL + "/shop/appCourseDetail";

        public static final String SHOP_MARKET_COURSE_APPOINTMENT_INFO = BASE_URL_LOCAL + "/shop/order";

        public static final String APPOINTMENT_LESSON = BASE_URL_LOCAL + "/palt_form_course_api/reservationCourse";
        public static final String APPOINTMENT_LESSON_V2 = TUTOR_BASE_URL + "/palt_form_course_api/reservationCourse";

        public static final String CONSULT_LESSON = TUTOR_BASE_URL + "/User_lesson_consult/addUserLessonConsult";

        public static final String CONSULT_LESSON_LIST = TUTOR_BASE_URL + "/User_lesson_consult/getUserLessonConsultByUserId";

        public static final String CONSULT_LESSON_DETAIL = TUTOR_BASE_URL + "/User_lesson_consult/getUserLessonConsultInfo";

        public static final String SHOP_LIST = BASE_URL_PRODUCTION + "/Yoga_college/yougaShop";


        public static final String HOME_TUTOR_LIST = BASE_URL_PRODUCTION + "/Yoga_college/yogaTutorPage";

        public static final String TUTOR_DETAIL = TUTOR_BASE_URL + "/Shop/shopTutorInfo";

        public static final String HOME_EXPERIENCE_SHOP_2 = TUTOR_BASE_URL + "/Yoga_market/yogaMarketHomePageShopList";

//        public static final String HOME_EXPERIENCE_SHOP_V2 = TUTOR_BASE_URL + "/Yoga_market/yogaMarketHomePageShopListV2";
        public static final String HOME_EXPERIENCE_SHOP_V2 = TUTOR_BASE_URL + "/Yoga_market/yogaMarketHomePageShopList";

        public static final String SHOP_EXPERIENCE_TAG = TUTOR_BASE_URL + "/Yoga_market/getLabelListByClassifyId";

        public static final String SHOP_EXPERIENCE_CLASS_TAG = TUTOR_BASE_URL + "/Yoga_market/getClassifyLabelListBycityId";
        public static final String APP_FEEDBACK = TUTOR_BASE_URL + "/App_feedback/addAppFeedback";

        public final static String VIDEO_COURSE_LIST_V2 = BASE_URL_PRODUCTION + "/Yoga_college_new/yogaVideoIndex";
        public final static String HOME_RECOMMEND=BASE_URL_PRODUCTION+"/Yoga_college_new/yogaRecommendIndex";

        public final static String USER_SIGN_INFO=TUTOR_BASE_URL+"/Yoga_recommend/yogaRecommendUserInfo";

        public final static String USER_CARD_LIST_V2= TUTOR_BASE_URL+"/rights_card/getMyPlatformCardList";

        public final static String APPOINTMENTED_COURSE_INFO= TUTOR_BASE_URL+"/palt_form_course_api/appointmentSuccess";


        /**
         * 获取权益 预约课程详情
         */
        public final static String APPOINTMENT_ORDER_DETAIL=TUTOR_BASE_URL+"/shop/getAppointmentOrder";

        /**
         *
         */
        public final static String APPOINTMENT_COURSE_INFO=TUTOR_BASE_URL+"/shop/reservationPlatformCourseOrder";


        /**
         * 获取预约列表
         */
        public final static String APPOINTMENT_GET_COURSE_LIST=TUTOR_BASE_URL+"/app_user_data/platformUserOrderCenter";

        // http://testyogabook.hq-xl.com/mall/user_unionid/checkUserBind
        //2.检查当前unionid是否已经绑定用户  http://testyogabook.hq-xl.com/mall
        public final static String CHECK_USER_UNIONID_BIND=TUTOR_BASE_URL+"/user_unionid/checkUserBind";
        //绑定用户手机号 发送验证码 //  http://testyogabook.hq-xl.com/mall/user_unionid/sendSms
        public final static String BIND_PHONE_SEND_CODE = TUTOR_BASE_URL+"/user_unionid/sendSms";
        //绑定手机号  http://testyogabook.hq-xl.com/mall/user_unionid/bindUnionid
        //http://testyogabook.hq-xl.com/mall/user_unionid/bindUnionid
//        public final static String BIND_PHONE = TUTOR_BASE_URL+"/user_unionid/bindUnionid";
//        public final static String BIND_PHONE = "http://testyogabook.hq-xl.com/mall/user_unionid/bindUnionid";
        public final static String BIND_PHONE = "http://testyogabook.hq-xl.com/mall/user_unionid/bindUnionid";

        //激活权益卡  https://t.p.idyoga.cn/yoga_college/Yoga_college/activatePlatformCard
        public final static String ACTIVA_CARD = "https://t.p.idyoga.cn/yoga_college/Yoga_college/activatePlatformCard";
        //权益课头部信息 head data http://testyogabook.hq-xl.com/yoga_college/Yoga_college/courseHeadData
        public final static String COURSE_HEAD_INFO = COURSE_BASE_URL+"yoga_college/Yoga_college/courseHeadData";
        //课程详情  http://testyogabook.hq-xl.com/yoga_college/Yoga_college/getShopDetail
        public final static String SHOP_DETAIL_INFO = COURSE_BASE_URL+"yoga_college/Yoga_college/getShopDetail";
        //权益课 瑜伽馆列表 http://testyogabook.hq-xl.com/yoga_college/Yoga_college/platformCourseShopList
        public final static String EQUITY_COURSE_SHOP_LISTS = COURSE_BASE_URL+"yoga_college/Yoga_college/platformCourseShopList";
        //权益课 头部信息 和 瑜伽馆列表 合并 http://testyogabook.hq-xl.com/yoga_college/Yoga_college/vipPlatformCourseData
        public final static String EQUITY_COURSE_PAGE_DATA = COURSE_BASE_URL+"yoga_college/Yoga_college/vipPlatformCourseData";
        //首页 http://testyogabook.hq-xl.com/yoga_college/Yoga_college/yogaCollegeData
        public final static String HOME_PAGE_DATA = COURSE_BASE_URL+"yoga_college/Yoga_college/yogaCollegeData";
        //签到 https://t.p.idyoga.cn/yoga_college/Yoga_college/userClock
        public final static String HOME_USER_CLOCK = "https://t.p.idyoga.cn/yoga_college/Yoga_college/userClock";
        //专题活动详情 http://testyogabook.hq-xl.com/yoga_college/Yoga_college/subjectDetail
        public final static String SPECIAL_DETAIL_DATA = COURSE_BASE_URL+"yoga_college/Yoga_college/subjectDetail";
        //视频首页页面 https://t.p.idyoga.cn/yoga_college/Yoga_college/videoIndex
        public final static String VIDEO_HOME_PAGE = "https://t.p.idyoga.cn/yoga_college/Yoga_college/videoIndex";
        //关注 取消 瑜伽馆 http://testyogabook.hq-xl.com/yoga_college/Yoga_college/userAttentionShop
        public final static String FOLLOW_SHOP = COURSE_BASE_URL+"yoga_college/Yoga_college/userAttentionShop";
        // 课程详情 http://testyogabook.hq-xl.com/yoga_college/Yoga_college/getLessonDetail
        public final static String COURSE_DETAIL_DATA = COURSE_BASE_URL+"yoga_college/Yoga_college/getLessonDetail";
        // 导师详情 http://testyogabook.hq-xl.com/yoga_college/Yoga_college/getTutorDetail
        public final static String TEACH_INFO_DETAIL = COURSE_BASE_URL+"yoga_college/Yoga_college/getTutorDetail";
        //瑜伽馆图片详情  http://testyogabook.hq-xl.com/yoga_college/Yoga_college/getShopImage
        public final static String SHOP_IAMGES_DATA = COURSE_BASE_URL+"yoga_college/Yoga_college/getShopImage";
        //瑜伽馆 课程表 http://testyogabook.hq-xl.com/yoga_college/Yoga_college/getCourseList
        public final static String SHOP_COURSR_LIST_DATA = COURSE_BASE_URL+"yoga_college/Yoga_college/getCourseList";
        //我的信息 http://testyogabook.hq-xl.com/yoga_college/Yoga_college/userData
        public final static String MINE_INFO_DATA = COURSE_BASE_URL+"yoga_college/Yoga_college/userData";
        //我的预约 http://testyogabook.hq-xl.com/yoga_college/Yoga_college/platformUserOrderCenter
        public final static String PLATFORM_USER_ORDER_CENTER = COURSE_BASE_URL+"yoga_college/Yoga_college/platformUserOrderCenter";
        //预约/咨询页面数据 详情http://testyogabook.hq-xl.com/mall/yoga_market/yogaCollegeAppointmentPageData
        public final static String COLLEGE_APPOINTMENT_PAGEDATA = "http://testyogabook.hq-xl.com/mall/yoga_market/yogaCollegeAppointmentPageData";
        //某节已排期的权益排课情况 课程表 http://testyogabook.hq-xl.com/mall/yoga_market/yogaCollegeSetCourseLessonList
        public final static String COLLEGE_SETCOURSE_LESSON_LIST = "http://testyogabook.hq-xl.com/mall/yoga_market/yogaCollegeSetCourseLessonList";
        //添加咨询 http://testyogabook.hq-xl.com/mall/user_lesson_consult/addUserLessonConsult
        public final static String ADD_USER_LESSON_CONSULT = "http://testyogabook.hq-xl.com/mall/user_lesson_consult/addUserLessonConsult";
        //我关注的瑜伽馆 http://testyogabook.hq-xl.com/yoga_college/Yoga_college/userAttentionShopList
        public final static String USER_ATTENTION_SHOP_LIST = COURSE_BASE_URL+"yoga_college/Yoga_college/userAttentionShopList";
        //预约详情 http://testyogabook.hq-xl.com/yoga_college/Yoga_college/makeDetail
        public final static String MAKE_DETAIL = COURSE_BASE_URL+"yoga_college/Yoga_college/makeDetail";
        //取消预约 http://testyogabook.hq-xl.com/yoga_college/Yoga_college/cancelUserLessonConsult
        public final static String CANCEL_USER_LESSON_CONSULT = COURSE_BASE_URL+"yoga_college/Yoga_college/cancelUserLessonConsult";
        //取消 待上课中已排课的课程 http://testyogabook.hq-xl.com/mall/course_api/cancelReservationCourse
        public final static String CANCEL_RESERVATION_COURSE = COURSE_BASE_URL+"mall/course_api/cancelReservationCourse";
        //广告弹窗 https://t.p.idyoga.cn/yoga_college/Yoga_college/adverti
        public final static String YOGA_ADVERTI = "https://t.p.idyoga.cn/yoga_college/Yoga_college/adverti";
        //视频详情 https://t.p.idyoga.cn/yoga_college/Yoga_college/videoDetail
        public final static String VIDEO_DETAIL = "https://t.p.idyoga.cn/yoga_college/Yoga_college/videoDetail";
        //视频链接 https://t.p.idyoga.cn/mall/Aliyun_video/liveGetReplayUrl
        public final static String VIDEO_PLAYER_PATH = "https://t.p.idyoga.cn/mall/Aliyun_video/liveGetReplayUrl";


    }

    public static final class EventTags {

        public final static int BEGIN_EVENT = 10;
        public final static int TUTOR_BEGIN_EVENT = 20;
        public final static int ADDRESS_SELECT = BEGIN_EVENT + 1;
        public final static int HOME_SUBJECT = BEGIN_EVENT + 2; //专题
        public final static int HOME_SUBJECT_CLOCK = BEGIN_EVENT + 3;
        public final static int HOME_SUBJECT_ITEM = BEGIN_EVENT + 2; //专题详情

        public final static int HOME_SUBJECT_GET_VIDEOID = BEGIN_EVENT + 3; //专题

        public final static int HOME_SUBJECT_1 = BEGIN_EVENT + 3; //测试

        public final static int TUTOR_LIST = TUTOR_BEGIN_EVENT + 1;//导师筛选
        public final static int TUTOR_SELECT = TUTOR_BEGIN_EVENT + 2;//导师筛选


        public final static int SEARCH_LIST = BEGIN_EVENT + 3 + 1;
        public final static int SEARCH_TAG_LIST = BEGIN_EVENT + 3 + 2;


        public final static int USER_CARD_LIST = BEGIN_EVENT + 4 + 1;
        public final static int USER_CARD_DETAILS = BEGIN_EVENT + 4 + 2;
        public final static int USER_CARD_PERMISSION = BEGIN_EVENT + 4 + 3;


        public final static int HOME_EXPERIENCE = BEGIN_EVENT + 2 + 1;
        public final static int HOME_EXPERIENCE_SHOP = BEGIN_EVENT + 2 + 2;
        public final static int HOME_EXPERIENCE_TAG = BEGIN_EVENT + 2 + 4;
        public final static int HOME_EXPERIENCE_COURSE = BEGIN_EVENT + 2 + 3;
        public final static int HOME_EXPERIENCE_CLASS = BEGIN_EVENT + 2 + 2;
        public final static int HOME_EXPERIENCE_CLASS_LIST = BEGIN_EVENT + 2 + 3;


        public final static int SHOP_OFFLINE_COURSE_ALL = BEGIN_EVENT + 5 + 1;

        public final static int CARD_CHILD_ADD = BEGIN_EVENT + 5 + 2;
        public final static int CARD_CHILD_ADD_GET_CODE = BEGIN_EVENT + 5 + 3;

        public final static int VIDEO_CLASSIFY = BEGIN_EVENT + 5 + 4;
        public final static int VIDEO_CLASSIFY_LIST = BEGIN_EVENT + 5 + 5;

        public final static int USER_UPDATE_INFO = BEGIN_EVENT + 5 + 6;

        public final static int HOME_EXPERIENCE_CLASS_SHOP = BEGIN_EVENT + 5 + 7;
        public final static int HOME_EXPERIENCE_CLASS_COURSE = BEGIN_EVENT + 5 + 8;

        public final static int VIDEO_COURSE_LIST = BEGIN_EVENT + 5 + 9;

        public final static int USER_APPOINTMENT_VIDEO_COURSE = BEGIN_EVENT + 5 + 10;
        public final static int USER_CANCEL_APPOINTMENT_VIDEO_COURSE = BEGIN_EVENT + 5 + 11;
        public final static int USER_APPOINTMENT_VIDEO_COURSE_LIST = BEGIN_EVENT + 5 + 12;
        public final static int USER_CARD_CHILD_LIST = BEGIN_EVENT + 5 + 13;
        public final static int USER_UPDATE_PWD = BEGIN_EVENT + 5 + 15;

        public final static int USER_SIGN_INFO =BEGIN_EVENT+5+16;



    }

    public static final class Html5Urls {

        public final static String HOME_YOGA_EXPERIENCE = "";
        public final static String HOME_YOGA_GYM = "http://wxyoga.hq-xl.com/studio/index";
        public final static String HOME_YOGA_TUTOR = "http://wxyoga.hq-xl.com/tutor/index";
        public final static String COURSE_ALL = "http://wxyoga.hq-xl.com/course/vd";
        public final static String HOME_YOGA_OFFLINE = "http://wxyoga.hq-xl.com/course/ul";
        public final static String HOME_YOGA_VIDEO = "http://wxyoga.hq-xl.com/course/vd";


        /**
         * 关于我们
         */
        public final static String SETTING_ABOUT_APP = "https://platform.hq-xl.com/mall/About_us/aboutUs";
    }
}

