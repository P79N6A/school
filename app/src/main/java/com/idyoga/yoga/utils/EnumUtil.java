package com.idyoga.yoga.utils;

/**
 * 文 件 名: EnumUtil
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/3/22
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class EnumUtil {
    public enum SEX {
        BOX("男", 1), GIRL("女", 2);

        private String sex;
        private int type;

        private SEX(String sex, int type) {
            this.sex = sex;
            this.type = type;
        }

        public static String getName(int type) {
            for (SEX c : SEX.values()) {
                if (c.getType() == type) {
                    return c.sex;
                }
            }
            return null;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

    public enum COURSE_STATE {
        STAYCONFIRM("待核销", 1), CONFIRM("已取消", 2), CANCEL("已核销", 3);;  ;
        private String state;
        private int type;

        COURSE_STATE(String state, int type) {
            this.state = state;
            this.type = type;
        }
        public static String getSate(int type) {
            for (COURSE_STATE s : COURSE_STATE.values()) {
                if (s.getType() == type) {
                    return s.state;
                }
            }
            return null;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }



    }
}
