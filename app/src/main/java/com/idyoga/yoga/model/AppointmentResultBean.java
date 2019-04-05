package com.idyoga.yoga.model;

import java.util.List;

/**
 * @author weilgu
 * @time 2019/3/27 16:29
 * @des ${TODO}
 */

public class AppointmentResultBean {


    /**
     * id : 824
     * lessonName : 林大师蛇形瑜伽课
     * lessonId : 104
     * courseId : null
     * start_time : 1553731200
     * end_time : null
     * shopId : 19287727
     * shopName : 现联瑜伽馆
     * address : 广州天河
     * sign_source : null
     * sign_time : null
     * cancel_source : 0
     * create_time : 1553675280
     * update_time : 1553675280
     * state : 待确定
     * state_code : 1
     * lessonType : 2
     * qrUrl :
     * taillist : [{"content":"预约提交","time":1553675280}]
     * copywriting : {"top":{"text":["预约已提交","客服将在2小时以内与您联系","请耐心等待"],"notice":["注：春节期间由于部分瑜伽馆放假，可能出现预约不上的情况，我们将尽力调节，请谅解"]},"bottom":{"text":["1.春节期间由于部分瑜伽馆放假，可能出现预约不上的情况，我们将尽力调节，请谅解","2.订单详情可在 我的预约 处查看。","3.请于课程前10分钟到瑜伽馆前台进行签到。","4.取消预约的时间限制由瑜伽馆决定。","5.若瑜伽馆取消课程，将会及时通知您。","6.同一时间段不能预约两节课程。"],"notice":[""]}}
     */

    private int id;
    private String lessonName;
    private int lessonId;
    private Object courseId;
    private int start_time;
    private Object end_time;
    private int shopId;
    private String shopName;
    private String address;
    private Object sign_source;
    private Object sign_time;
    private int cancel_source;
    private int create_time;
    private int update_time;
    private String state;
    private String state_code;
    private int lessonType;
    private String qrUrl;
    private CopywritingBean copywriting;
    private List<TaillistBean> taillist;

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

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public Object getCourseId() {
        return courseId;
    }

    public void setCourseId(Object courseId) {
        this.courseId = courseId;
    }

    public int getStart_time() {
        return start_time;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public Object getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Object end_time) {
        this.end_time = end_time;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Object getSign_source() {
        return sign_source;
    }

    public void setSign_source(Object sign_source) {
        this.sign_source = sign_source;
    }

    public Object getSign_time() {
        return sign_time;
    }

    public void setSign_time(Object sign_time) {
        this.sign_time = sign_time;
    }

    public int getCancel_source() {
        return cancel_source;
    }

    public void setCancel_source(int cancel_source) {
        this.cancel_source = cancel_source;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState_code() {
        return state_code;
    }

    public void setState_code(String state_code) {
        this.state_code = state_code;
    }

    public int getLessonType() {
        return lessonType;
    }

    public void setLessonType(int lessonType) {
        this.lessonType = lessonType;
    }

    public String getQrUrl() {
        return qrUrl;
    }

    public void setQrUrl(String qrUrl) {
        this.qrUrl = qrUrl;
    }

    public CopywritingBean getCopywriting() {
        return copywriting;
    }

    public void setCopywriting(CopywritingBean copywriting) {
        this.copywriting = copywriting;
    }

    public List<TaillistBean> getTaillist() {
        return taillist;
    }

    public void setTaillist(List<TaillistBean> taillist) {
        this.taillist = taillist;
    }

    public static class CopywritingBean {
        /**
         * top : {"text":["预约已提交","客服将在2小时以内与您联系","请耐心等待"],"notice":["注：春节期间由于部分瑜伽馆放假，可能出现预约不上的情况，我们将尽力调节，请谅解"]}
         * bottom : {"text":["1.春节期间由于部分瑜伽馆放假，可能出现预约不上的情况，我们将尽力调节，请谅解","2.订单详情可在 我的预约 处查看。","3.请于课程前10分钟到瑜伽馆前台进行签到。","4.取消预约的时间限制由瑜伽馆决定。","5.若瑜伽馆取消课程，将会及时通知您。","6.同一时间段不能预约两节课程。"],"notice":[""]}
         */

        private TopBean top;
        private BottomBean bottom;

        public TopBean getTop() {
            return top;
        }

        public void setTop(TopBean top) {
            this.top = top;
        }

        public BottomBean getBottom() {
            return bottom;
        }

        public void setBottom(BottomBean bottom) {
            this.bottom = bottom;
        }

        public static class TopBean {
            private List<String> text;
            private List<String> notice;

            public List<String> getText() {
                return text;
            }

            public void setText(List<String> text) {
                this.text = text;
            }

            public List<String> getNotice() {
                return notice;
            }

            public void setNotice(List<String> notice) {
                this.notice = notice;
            }
        }

        public static class BottomBean {
            private List<String> text;
            private List<String> notice;

            public List<String> getText() {
                return text;
            }

            public void setText(List<String> text) {
                this.text = text;
            }

            public List<String> getNotice() {
                return notice;
            }

            public void setNotice(List<String> notice) {
                this.notice = notice;
            }
        }
    }

    public static class TaillistBean {
        /**
         * content : 预约提交
         * time : 1553675280
         */

        private String content;
        private int time;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }
    }
}
