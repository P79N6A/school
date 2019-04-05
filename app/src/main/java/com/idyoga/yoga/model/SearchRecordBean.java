package com.idyoga.yoga.model;

import java.util.List;

/**
 * 文 件 名: SearchRecordBean
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/5/15
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class SearchRecordBean {


    private List<HotSeekBean> hotSeek;
    private List<HistorySeekBean> historySeek;

    public List<HotSeekBean> getHotSeek() {
        return hotSeek;
    }

    public void setHotSeek(List<HotSeekBean> hotSeek) {
        this.hotSeek = hotSeek;
    }

    public List<HistorySeekBean> getHistorySeek() {
        return historySeek;
    }

    public void setHistorySeek(List<HistorySeekBean> historySeek) {
        this.historySeek = historySeek;
    }

    public static class HotSeekBean {
        /**
         * name : 现联
         */

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class HistorySeekBean {
        /**
         * name : 现联
         */

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    public static class SearchTag {
        String name;

        public SearchTag() {
        }

        public SearchTag(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        @Override
        public boolean equals(Object obj) {
            if(!(obj instanceof SearchTag)) {
                return false;
            }else {
                SearchTag user=(SearchTag) obj;
                if(name==user.name &&
                        name!=null && user.name!=null &&
                        name.equals(user.name)) {
                    return true;
                }else {
                    return false;
                }
            }
        }

        @Override
        public int hashCode() {
            return 1;
        }
    }



    public static class TagBean {
        String tagTitle;
        List<String> mList;

        public TagBean() {
        }

        public TagBean(String tagTitle, List<String> list) {
            this.tagTitle = tagTitle;
            mList = list;
        }

        public String getTagTitle() {
            return tagTitle;
        }

        public void setTagTitle(String tagTitle) {
            this.tagTitle = tagTitle;
        }

        public List<String> getList() {
            return mList;
        }

        public void setList(List<String> list) {
            mList = list;
        }
    }

}
