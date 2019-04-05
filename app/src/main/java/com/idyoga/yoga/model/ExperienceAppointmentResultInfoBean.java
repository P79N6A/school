package com.idyoga.yoga.model;

import java.util.List;

public class ExperienceAppointmentResultInfoBean {

        /**
         * courseDetail : {"lessonName":"流瑜伽","classroomName":"1班","start":1522238400,"end":1522240200,"tutorList":[{"id":232,"name":"张老师"}]}
         * shopInfo : {"id":5,"name":"IDYOGA测试店铺","address":"广州天河","logopath":"","hotline":"020","merid":1,"payinfos_id":0,"mobile":null,"is_for_platform":null,"chain_id":null,"longitude":"0.0000000","latitude":"0.0000000","province":1,"city":17,"area":4}
         */

        private CourseDetailBean courseDetail;
        private ShopInfoBean shopInfo;

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

        public static class CourseDetailBean {
            /**
             * lessonName : 流瑜伽
             * classroomName : 1班
             * start : 1522238400
             * end : 1522240200
             * tutorList : [{"id":232,"name":"张老师"}]
             */

            private String lessonName;
            private String classroomName;
            private int start;
            private int end;
            private List<TutorListBean> tutorList;

            public String getLessonName() {
                return lessonName;
            }

            public void setLessonName(String lessonName) {
                this.lessonName = lessonName;
            }

            public String getClassroomName() {
                return classroomName;
            }

            public void setClassroomName(String classroomName) {
                this.classroomName = classroomName;
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

            public List<TutorListBean> getTutorList() {
                return tutorList;
            }

            public void setTutorList(List<TutorListBean> tutorList) {
                this.tutorList = tutorList;
            }

            public static class TutorListBean {
                /**
                 * id : 232
                 * name : 张老师
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
        }

        public static class ShopInfoBean {
            /**
             * id : 5
             * name : IDYOGA测试店铺
             * address : 广州天河
             * logopath :
             * hotline : 020
             * merid : 1
             * payinfos_id : 0
             * mobile : null
             * is_for_platform : null
             * chain_id : null
             * longitude : 0.0000000
             * latitude : 0.0000000
             * province : 1
             * city : 17
             * area : 4
             */

            private int id;
            private String name;
            private String address;
            private String logopath;
            private String hotline;
            private int merid;
            private int payinfos_id;
            private String mobile;
            private Object is_for_platform;
            private Object chain_id;
            private String longitude;
            private String latitude;
            private int province;
            private int city;
            private int area;

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

            public Object getIs_for_platform() {
                return is_for_platform;
            }

            public void setIs_for_platform(Object is_for_platform) {
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
        }
}
