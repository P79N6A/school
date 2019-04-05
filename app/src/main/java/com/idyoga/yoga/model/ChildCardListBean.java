package com.idyoga.yoga.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * 文 件 名: ChildCardListBean
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/12
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class ChildCardListBean implements MultiItemEntity{


    @Override
    public int getItemType() {
        return 0;
    }


    /**
     * annexNum : 5
     * validAnnexNum : 4
     * cardList : [{"id":5373,"username":"喵星人（毛孩子2）","mobile":"15772747952","start_time":1527734880,"expire_time":1559270880,"has_time":0,"valid_time":0,"is_chain":1,"status":0,"annex_num":0,"valid_annex_num":0,"card_name":"权益5","card_num":"YOGA20180612094628381072","card_category_id":1,"card_category":"期限卡","shop_id":63684083,"content":"132"}]
     */

    private int annexNum;
    private int validAnnexNum;

    private List<CardListBean> cardList;

    public int getAnnexNum() {
        return annexNum;
    }

    public void setAnnexNum(int annexNum) {
        this.annexNum = annexNum;
    }

    public int getValidAnnexNum() {
        return validAnnexNum;
    }

    public void setValidAnnexNum(int validAnnexNum) {
        this.validAnnexNum = validAnnexNum;
    }

    public List<CardListBean> getCardList() {
        return cardList;
    }

    public void setCardList(List<CardListBean> cardList) {
        this.cardList = cardList;
    }

    public static class CardListBean {

        public CardListBean() {
        }

        public CardListBean(boolean isAdd) {
            this.isAdd = isAdd;
        }


        /**
         * id : 5373
         * username : 喵星人（毛孩子2）
         * mobile : 15772747952
         * start_time : 1527734880
         * expire_time : 1559270880
         * has_time : 0
         * valid_time : 0
         * is_chain : 1
         * status : 0
         * annex_num : 0
         * valid_annex_num : 0
         * card_name : 权益5
         * card_num : YOGA20180612094628381072
         * card_category_id : 1
         * card_category : 期限卡
         * shop_id : 63684083
         * content : 132
         */

        private int id;
        private String username;
        private String mobile;
        private int start_time;
        private int expire_time;
        private int has_time;
        private int valid_time;
        private int is_chain;
        private int status;
        private int annex_num;
        private int valid_annex_num;
        private String card_name;
        private String card_num;
        private int card_category_id;
        private String card_category;
        private int shop_id;
        private String content;
        private boolean isAdd;//用于判断是否可添加

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getStart_time() {
            return start_time;
        }

        public void setStart_time(int start_time) {
            this.start_time = start_time;
        }

        public int getExpire_time() {
            return expire_time;
        }

        public void setExpire_time(int expire_time) {
            this.expire_time = expire_time;
        }

        public int getHas_time() {
            return has_time;
        }

        public void setHas_time(int has_time) {
            this.has_time = has_time;
        }

        public int getValid_time() {
            return valid_time;
        }

        public void setValid_time(int valid_time) {
            this.valid_time = valid_time;
        }

        public int getIs_chain() {
            return is_chain;
        }

        public void setIs_chain(int is_chain) {
            this.is_chain = is_chain;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getAnnex_num() {
            return annex_num;
        }

        public void setAnnex_num(int annex_num) {
            this.annex_num = annex_num;
        }

        public int getValid_annex_num() {
            return valid_annex_num;
        }

        public void setValid_annex_num(int valid_annex_num) {
            this.valid_annex_num = valid_annex_num;
        }

        public String getCard_name() {
            return card_name;
        }

        public void setCard_name(String card_name) {
            this.card_name = card_name;
        }

        public String getCard_num() {
            return card_num;
        }

        public void setCard_num(String card_num) {
            this.card_num = card_num;
        }

        public int getCard_category_id() {
            return card_category_id;
        }

        public void setCard_category_id(int card_category_id) {
            this.card_category_id = card_category_id;
        }

        public String getCard_category() {
            return card_category;
        }

        public void setCard_category(String card_category) {
            this.card_category = card_category;
        }

        public int getShop_id() {
            return shop_id;
        }

        public void setShop_id(int shop_id) {
            this.shop_id = shop_id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public boolean isAdd() {
            return isAdd;
        }

        public void setAdd(boolean add) {
            isAdd = add;
        }
    }
}
