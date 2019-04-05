package com.idyoga.yoga.model;

import java.util.List;

/**
 * @author weilgu
 * @time 2019/4/2 14:13
 * @des ${TODO}
 */

public class VideoDetailBean {


    /**
     * id : 477
     * shop_id : 0
     * tutor_id : 0
     * title : 添加系列课
     * number : 0
     * price : 0.00
     * image_url : https://t.p.idyoga.cn/image/video_image/2019/03/5c90a0b9db420.jpg
     * tutor_name : 孔德腾
     * has_time : 730
     * is_free : 1
     * description : 啊哈哈哈哈差
     * divide : 0
     * like_number : 0
     * collect_number : 0
     * goods_number : VIDEO2019031915564192653776801544
     * pitch_number : 0
     * is_vip_view : 0
     * invitation_code : 9999
     * code_duration : 0
     * invitation_code_expire_time : 0
     * is_promote : 1
     * url : https%3A%2F%2Fcmzb.idyoga.cn%2Fvideo_mall%2Findex%2Fvideodetail.html%3Fvideo_id%3D477%26agent_id%3D
     * content_url : https://cmzb.idyoga.cn/video_mall/details/videoDetails?video_id=477&type=0
     * action : []
     * recommendVideo : [{"id":484,"title":"测试一下","image_url":"https://t.p.idyoga.cn/image/video_image/2019/04/5ca1ce7652e20.jpg","price":"0.00","tutor_name":"皮卡丘（测试）","study_number":0,"number":3},{"id":476,"title":"测试系列课-11.14.26","image_url":"https://t.p.idyoga.cn/image/video_image/2019/03/5c85ffba42b17.jpg","price":"0.00","tutor_name":"孔德腾","study_number":0,"number":3},{"id":473,"title":"壳壳","image_url":"https://czb.idyoga.cn/upload/images/190306/5c7f31ae55c48.png","price":"0.00","tutor_name":"Tomorrow（测试）","study_number":0,"number":1},{"id":471,"title":"瑜伽入门","image_url":"https://czb.idyoga.cn/upload/images/190304/5c7cfbc68c80b.png","price":"0.00","tutor_name":"测试","study_number":0,"number":4},{"id":461,"title":"免费啦啦啦","image_url":"https://t.p.idyoga.cn/image/video_image/2019/02/5c77ada045600.png","price":"0.00","tutor_name":"我自己啊旅途","study_number":0,"number":2}]
     * seckillActivity : {"activity_code":null,"start_time":null,"end_time":null,"seckill_price":null,"seckill_number":null,"sale_order_count":0,"sale_money_count":null}
     * groupBuyingActivity : null
     * is_pay : 0
     * is_like : 0
     * is_collect : 0
     * is_use_invitation_code : 0
     */

    private int id;
    private int shop_id;
    private int tutor_id;
    private String title;
    private int number;
    private String price;
    private String image_url;
    private String tutor_name;
    private int has_time;
    private int is_free;
    private String description;
    private int divide;
    private int like_number;
    private int collect_number;
    private String goods_number;
    private int pitch_number;
    private int is_vip_view;
    private String invitation_code;
    private int code_duration;
    private int invitation_code_expire_time;
    private int is_promote;
    private String url;
    private String content_url;
    private SeckillActivityBean seckillActivity;
    private Object groupBuyingActivity;
    private int is_pay;
    private int is_like;
    private int is_collect;
    private int is_use_invitation_code;
    private List<Action> action;
    private List<RecommendVideoBean> recommendVideo;

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

    public int getTutor_id() {
        return tutor_id;
    }

    public void setTutor_id(int tutor_id) {
        this.tutor_id = tutor_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getTutor_name() {
        return tutor_name;
    }

    public void setTutor_name(String tutor_name) {
        this.tutor_name = tutor_name;
    }

    public int getHas_time() {
        return has_time;
    }

    public void setHas_time(int has_time) {
        this.has_time = has_time;
    }

    public int getIs_free() {
        return is_free;
    }

    public void setIs_free(int is_free) {
        this.is_free = is_free;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDivide() {
        return divide;
    }

    public void setDivide(int divide) {
        this.divide = divide;
    }

    public int getLike_number() {
        return like_number;
    }

    public void setLike_number(int like_number) {
        this.like_number = like_number;
    }

    public int getCollect_number() {
        return collect_number;
    }

    public void setCollect_number(int collect_number) {
        this.collect_number = collect_number;
    }

    public String getGoods_number() {
        return goods_number;
    }

    public void setGoods_number(String goods_number) {
        this.goods_number = goods_number;
    }

    public int getPitch_number() {
        return pitch_number;
    }

    public void setPitch_number(int pitch_number) {
        this.pitch_number = pitch_number;
    }

    public int getIs_vip_view() {
        return is_vip_view;
    }

    public void setIs_vip_view(int is_vip_view) {
        this.is_vip_view = is_vip_view;
    }

    public String getInvitation_code() {
        return invitation_code;
    }

    public void setInvitation_code(String invitation_code) {
        this.invitation_code = invitation_code;
    }

    public int getCode_duration() {
        return code_duration;
    }

    public void setCode_duration(int code_duration) {
        this.code_duration = code_duration;
    }

    public int getInvitation_code_expire_time() {
        return invitation_code_expire_time;
    }

    public void setInvitation_code_expire_time(int invitation_code_expire_time) {
        this.invitation_code_expire_time = invitation_code_expire_time;
    }

    public int getIs_promote() {
        return is_promote;
    }

    public void setIs_promote(int is_promote) {
        this.is_promote = is_promote;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent_url() {
        return content_url;
    }

    public void setContent_url(String content_url) {
        this.content_url = content_url;
    }

    public SeckillActivityBean getSeckillActivity() {
        return seckillActivity;
    }

    public void setSeckillActivity(SeckillActivityBean seckillActivity) {
        this.seckillActivity = seckillActivity;
    }

    public Object getGroupBuyingActivity() {
        return groupBuyingActivity;
    }

    public void setGroupBuyingActivity(Object groupBuyingActivity) {
        this.groupBuyingActivity = groupBuyingActivity;
    }

    public int getIs_pay() {
        return is_pay;
    }

    public void setIs_pay(int is_pay) {
        this.is_pay = is_pay;
    }

    public int getIs_like() {
        return is_like;
    }

    public void setIs_like(int is_like) {
        this.is_like = is_like;
    }

    public int getIs_collect() {
        return is_collect;
    }

    public void setIs_collect(int is_collect) {
        this.is_collect = is_collect;
    }

    public int getIs_use_invitation_code() {
        return is_use_invitation_code;
    }

    public void setIs_use_invitation_code(int is_use_invitation_code) {
        this.is_use_invitation_code = is_use_invitation_code;
    }

    public List<Action> getAction() {
        return action;
    }

    public void setAction(List<Action> action) {
        this.action = action;
    }

    public List<RecommendVideoBean> getRecommendVideo() {
        return recommendVideo;
    }

    public void setRecommendVideo(List<RecommendVideoBean> recommendVideo) {
        this.recommendVideo = recommendVideo;
    }

    public static class Action{


        /**
         * id : 708
         * title : 测试文件上传：06546
         * time : null
         * image_url : https://t.p.idyoga.cn/image/video_image/2019/03/5c861e53f3487.jpg
         * video : b9cf1aaeec27416783968672f861d9b2
         * type : 1
         * goods_id : null
         * goods_number : null
         * number : 0
         */

        private int id;
        private String title;
        private Object time;
        private String image_url;
        private String video;
        private int type;
        private Object goods_id;
        private Object goods_number;
        private int number;

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

        public Object getTime() {
            return time;
        }

        public void setTime(Object time) {
            this.time = time;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public Object getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(Object goods_id) {
            this.goods_id = goods_id;
        }

        public Object getGoods_number() {
            return goods_number;
        }

        public void setGoods_number(Object goods_number) {
            this.goods_number = goods_number;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }
    }

    public static class SeckillActivityBean {
        /**
         * activity_code : null
         * start_time : null
         * end_time : null
         * seckill_price : null
         * seckill_number : null
         * sale_order_count : 0
         * sale_money_count : null
         */

        private Object activity_code;
        private Object start_time;
        private Object end_time;
        private Object seckill_price;
        private Object seckill_number;
        private int sale_order_count;
        private Object sale_money_count;

        public Object getActivity_code() {
            return activity_code;
        }

        public void setActivity_code(Object activity_code) {
            this.activity_code = activity_code;
        }

        public Object getStart_time() {
            return start_time;
        }

        public void setStart_time(Object start_time) {
            this.start_time = start_time;
        }

        public Object getEnd_time() {
            return end_time;
        }

        public void setEnd_time(Object end_time) {
            this.end_time = end_time;
        }

        public Object getSeckill_price() {
            return seckill_price;
        }

        public void setSeckill_price(Object seckill_price) {
            this.seckill_price = seckill_price;
        }

        public Object getSeckill_number() {
            return seckill_number;
        }

        public void setSeckill_number(Object seckill_number) {
            this.seckill_number = seckill_number;
        }

        public int getSale_order_count() {
            return sale_order_count;
        }

        public void setSale_order_count(int sale_order_count) {
            this.sale_order_count = sale_order_count;
        }

        public Object getSale_money_count() {
            return sale_money_count;
        }

        public void setSale_money_count(Object sale_money_count) {
            this.sale_money_count = sale_money_count;
        }
    }

    public static class RecommendVideoBean {
        /**
         * id : 484
         * title : 测试一下
         * image_url : https://t.p.idyoga.cn/image/video_image/2019/04/5ca1ce7652e20.jpg
         * price : 0.00
         * tutor_name : 皮卡丘（测试）
         * study_number : 0
         * number : 3
         */

        private int id;
        private String title;
        private String image_url;
        private String price;
        private String tutor_name;
        private int study_number;
        private int number;

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

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getTutor_name() {
            return tutor_name;
        }

        public void setTutor_name(String tutor_name) {
            this.tutor_name = tutor_name;
        }

        public int getStudy_number() {
            return study_number;
        }

        public void setStudy_number(int study_number) {
            this.study_number = study_number;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }
    }
}
