package com.idyoga.yoga.model;

import java.util.List;

/**
 * 文 件 名: TutorSelectBean
 * 创 建 人: By k
 * 创建日期: 2018/5/14 11:58
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注： 老师列表
 */
public class TutorListBean {


    private List<TutorBean> tutor;
    private List<?> recommendTutor;

    public List<TutorBean> getTutor() {
        return tutor;
    }

    public void setTutor(List<TutorBean> tutor) {
        this.tutor = tutor;
    }

    public List<?> getRecommendTutor() {
        return recommendTutor;
    }

    public void setRecommendTutor(List<?> recommendTutor) {
        this.recommendTutor = recommendTutor;
    }

    public static class TutorBean {
        /**
         * id : 108
         * name : 林大师
         * mobile : 15622605889
         * mailbox : 020@163.com
         * sex : 1
         * introduce :
         * image :
         * tutorLabel : [{"name":"签约导师"},{"name":"海外导师"},{"name":"金牌导师"}]
         * lesson : [{"id":569,"lesson_id":104,"lessonName":"林大师蛇形瑜伽课"},{"id":570,"lesson_id":120,"lessonName":"林大师瑜伽课测试"},{"id":571,"lesson_id":2045,"lessonName":"林大师测试课程"}]
         * mainstream : [{"id":650,"mainstream_id":6,"mainstreamName":"吠陀瑜伽"},{"id":651,"mainstream_id":11,"mainstreamName":"王瑜伽"},{"id":652,"mainstream_id":15,"mainstreamName":"吉瓦穆克提瑜伽"}]
         */

        private int id;
        private String name;
        private String mobile;
        private String mailbox;
        private int sex;
        private String introduce;
        private String image;
        private List<TutorLabelBean> tutorLabel;
        private List<LessonBean> lesson;
        private List<MainstreamBean> mainstream;

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

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public List<TutorLabelBean> getTutorLabel() {
            return tutorLabel;
        }

        public void setTutorLabel(List<TutorLabelBean> tutorLabel) {
            this.tutorLabel = tutorLabel;
        }

        public List<LessonBean> getLesson() {
            return lesson;
        }

        public void setLesson(List<LessonBean> lesson) {
            this.lesson = lesson;
        }

        public List<MainstreamBean> getMainstream() {
            return mainstream;
        }

        public void setMainstream(List<MainstreamBean> mainstream) {
            this.mainstream = mainstream;
        }

        public static class TutorLabelBean {
            /**
             * name : 签约导师
             */

            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class LessonBean {
            /**
             * id : 569
             * lesson_id : 104
             * lessonName : 林大师蛇形瑜伽课
             */

            private int id;
            private int lesson_id;
            private String lessonName;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getLesson_id() {
                return lesson_id;
            }

            public void setLesson_id(int lesson_id) {
                this.lesson_id = lesson_id;
            }

            public String getLessonName() {
                return lessonName;
            }

            public void setLessonName(String lessonName) {
                this.lessonName = lessonName;
            }
        }

        public static class MainstreamBean {
            /**
             * id : 650
             * mainstream_id : 6
             * mainstreamName : 吠陀瑜伽
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
        }
    }
}
