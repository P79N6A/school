package com.idyoga.yoga.comm;

/**
 * 作者 by K
 * 时间：on 2018/3/21 09:47
 * 邮箱 by  vip@devkit.vip
 * <p/>
 * 类用途：
 * 最后修改：
 */
public class NetWorkConstant {
    public static final String BASE_URL = "http://api.idyoga.cn/mall";

    public static final String BASE_URL_1 = "https://platform.hq-xl.com/mall";
    public static final String BASE_URL_2 = "http://testyogabook.hq-xl.com/mall";
    public static final String BASE_COURSE_URL = "http://api.idyoga.cn/";
    public static final String BASE_COURSE_URL_1 = "http://api.idyoga.cn/";
//    public static final String BASE_SHOP_PALY = "https://t.p.idyoga.cn/mall/";
    public static final String BASE_SHOP_PALY = "https://p.idyoga.cn/mall/";

    //http://testyogabook.hq-xl.com/mall/User/getUserInfoById
    public static final String GET_ADDRESS = BASE_URL_1 + "/Region/index";
    public static final String USER_LONIN_PWD = BASE_URL + "/App_user_data/login";
    public static final String USER_LONIN_CODE = BASE_URL + "/App_user_data/SmsloginUser";
    //http://testyogabook.hq-xl.com/mall/App_user_data/yogaCollegeAddUser
//    public static final String USER_REISTER = BASE_URL + "/App_user_data/AddAppUser";
    //注册
    public static final String USER_REISTER = BASE_URL_2 + "/App_user_data/yogaCollegeAddUser";
    public static final String USER_UPDATE_PWD = BASE_URL + " /App_user_data/updateUserPassword";
    public static final String GET_SMS_FOR_LONGIN = BASE_URL + "/App_user_data/loginSendSms";
    public static final String GET_SMS_FOR_REGISTER = BASE_URL + "/App_user_data/SendSms";
    public static final String USER_CARD_LIST = BASE_URL + "/Rights_card/RightsCardList";
    public static final String USER_CARD_DETAIL = BASE_URL + "/Rights_card/getRightsCard";
    public static final String USER_COURSE_LIST = BASE_URL_2 + "/App_user_data/orderCenter";
    public static final String USER_COURSE_LIST_DETAIL = BASE_URL + "/shop/courseDetail";
    /**
     * 首页体验区
     */
    public static final String HOMEEXPRIENCE = BASE_COURSE_URL +"mall/market/WXIndex";
    /*
    * 类别课程列表*/
    public static final String GET_TYPE_COURSE = BASE_COURSE_URL +"mall/course_api/WXGetMarketList";

    /*课程的详情*/
    public static final String GET_COURSE_DETAILS = BASE_COURSE_URL +"mall/shop/courseDetail";

    /*预约时显示信息*/
    public static final String GET_APPOINTMENT_INFO = BASE_COURSE_URL +"mall/shop/order";

    /*预约课程*/
    public static final String APPOINTMENT_LESSSON = BASE_COURSE_URL +"mall/palt_form_course_api/reservationCourse";

    /*得到预约后的信息*/
    public static final String APPOINTMENTED_INFO = BASE_COURSE_URL +"mall/palt_form_course_api/appointmentSuccess";

    /*瑜伽学院-积分商城微信支付接口 https://t.p.idyoga.cn/mall/Integral_mall/appPayGoodsOrder*/
    public static final String SHOP_PLAY_URL = BASE_SHOP_PALY +"Integral_mall/appPayGoodsOrder";


}