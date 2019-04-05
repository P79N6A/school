package com.idyoga.yoga.model;



public class CheckUpdateBean {
    /**
     * constraint : false
     * newVersion : 1.0
     * newVersionCode : 1
     * apkSize : 321
     * newMd5 : youga_
     * downloadApkUri :
     * updateLog :
     * updateTime : 1522401562
     */

    private String constraint;
    private String newVersion;
    private String content;
    private String newVersionCode;
    private String apkSize;
    private String newMd5;
    private String downloadApkUri;
    private String updateLog;
    private int updateTime;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getConstraint() {
        return constraint;
    }

    public void setConstraint(String constraint) {
        this.constraint = constraint;
    }

    public String getNewVersion() {
        return newVersion;
    }

    public void setNewVersion(String newVersion) {
        this.newVersion = newVersion;
    }

    public String getNewVersionCode() {
        return newVersionCode;
    }

    public void setNewVersionCode(String newVersionCode) {
        this.newVersionCode = newVersionCode;
    }

    public String getApkSize() {
        return apkSize;
    }

    public void setApkSize(String apkSize) {
        this.apkSize = apkSize;
    }

    public String getNewMd5() {
        return newMd5;
    }

    public void setNewMd5(String newMd5) {
        this.newMd5 = newMd5;
    }

    public String getDownloadApkUri() {
        return downloadApkUri;
    }

    public void setDownloadApkUri(String downloadApkUri) {
        this.downloadApkUri = downloadApkUri;
    }

    public String getUpdateLog() {
        return updateLog;
    }

    public void setUpdateLog(String updateLog) {
        this.updateLog = updateLog;
    }

    public int getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(int updateTime) {
        this.updateTime = updateTime;
    }
}
