package com.idyoga.yoga.model;

/**
 * Created by mjh on 2019/1/9.
 */

public class WxLoginResultBean {

    /**
     * access_token : 17_QN1nh93klt-rtHYUDZA3ToMpI5Sfw8E0V5i3WT_5KCmwUYkDl3v3eW_4L4FIbHBIVjJETg_7i5sPPfbYBA9t_lJpzpBZCPvIe5m-jfqSadg
     * expires_in : 7200
     * refresh_token : 17_LQ00n1A7gr_-3Kv9Ql1MP-sbWD99xjHuddRC0VDamMozRHScgXZMmgX7JkRsl7Pv1Je8b9QNvg_uVxtaGD0GY1JWjv3KHiZy91iBfRofd-o
     * openid : owWto00YxbtTwnvNlTJTucgm3ydA
     * scope : snsapi_userinfo
     * unionid : od3rD1ZZf_t3tjqgDjdlxAGmBEao
     */

    private String access_token; //access_token
    private int expires_in;  //expires_in
    private String refresh_token; //refresh_token
    private String openid; //openid
    private String scope; //scope
    private String unionid;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}
