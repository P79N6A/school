package com.idyoga.yoga.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 文 件 名: VideoClassifyBean
 * 创 建 人: By k
 * 创建日期: 2018/5/28 09:40
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注：
 */
public class VideoClassifyBean implements Parcelable {

    /**
     * cate_name : 视频标签2
     * cate_id : 11
     * label : [{"id":15,"name":"视频标签3"}]
     */

    private String cate_name;
    private int cate_id;
    private List<LabelBean> label;

    public String getCate_name() {
        return cate_name;
    }

    public void setCate_name(String cate_name) {
        this.cate_name = cate_name;
    }

    public int getCate_id() {
        return cate_id;
    }

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
    }

    public List<LabelBean> getLabel() {
        return label;
    }

    public void setLabel(List<LabelBean> label) {
        this.label = label;
    }

    public static class LabelBean {
        /**
         * id : 15
         * name : 视频标签3
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.cate_name);
        dest.writeInt(this.cate_id);
        dest.writeList(this.label);
    }

    public VideoClassifyBean() {
    }

    protected VideoClassifyBean(Parcel in) {
        this.cate_name = in.readString();
        this.cate_id = in.readInt();
        this.label = new ArrayList<LabelBean>();
        in.readList(this.label, LabelBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<VideoClassifyBean> CREATOR = new Parcelable.Creator<VideoClassifyBean>() {
        @Override
        public VideoClassifyBean createFromParcel(Parcel source) {
            return new VideoClassifyBean(source);
        }

        @Override
        public VideoClassifyBean[] newArray(int size) {
            return new VideoClassifyBean[size];
        }
    };
}