package com.idyoga.yoga.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mjh on 2019/1/25.
 */

public class WxPayBean {


    /**
     * key : {"appid":"wx22af3b9685739732","noncestr":"98x4xv9xer9mf9u7dy2qovohp6mwfmbm","package":"Sign=WXPay","partnerid":"1514074051","prepayid":"wx25143707838357f7ab96d1061241439216","timestamp":1548398227,"sign":"54367A0A7BA6229CDD910A279FE5422E"}
     */
    private KeyBean key;

    public KeyBean getKey() {
        return key;
    }

    public void setKey(KeyBean key) {
        this.key = key;
    }

    public static class KeyBean {
        /**
         * appid : wx22af3b9685739732
         * noncestr : 98x4xv9xer9mf9u7dy2qovohp6mwfmbm
         * package : Sign=WXPay
         * partnerid : 1514074051
         * prepayid : wx25143707838357f7ab96d1061241439216
         * timestamp : 1548398227
         * sign : 54367A0A7BA6229CDD910A279FE5422E
         */

        private String appid;
        private String noncestr;
        @SerializedName("package")
        private String packageX;
        private String partnerid;
        private String prepayid;
        private int timestamp;
        private String sign;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        @Override
        public String toString() {
            return "KeyBean{" +
                    "appid='" + appid + '\'' +
                    ", noncestr='" + noncestr + '\'' +
                    ", packageX='" + packageX + '\'' +
                    ", partnerid='" + partnerid + '\'' +
                    ", prepayid='" + prepayid + '\'' +
                    ", timestamp=" + timestamp +
                    ", sign='" + sign + '\'' +
                    '}';
        }
    }
}
