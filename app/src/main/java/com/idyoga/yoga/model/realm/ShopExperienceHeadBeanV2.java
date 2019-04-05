package com.idyoga.yoga.model.realm;

import com.idyoga.yoga.model.realm.ShopExperienceBanner;
import com.idyoga.yoga.model.realm.ShopExperienceTag;

import java.util.List;


/**
 * 文 件 名: HomeExperienceBean
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/5/19
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class ShopExperienceHeadBeanV2 {


    /**
     * type : 1
     * bannerList : [{"id":127,"image_url":"https://platform.hq-xl.com/city_image/2018/04/5ad84221e219e.jpg","url":""},{"id":126,"image_url":"https://platform.hq-xl.com/city_image/2018/04/5ad841e436c8d.jpg","url":""}]
     * tagList : [{"id":30,"name":"抑郁缓解瑜伽","image_url":"https://platform.hq-xl.com/label_classify_image/2018/07/5b471bfc0893d.png"},{"id":29,"name":"食疗瑜伽","image_url":"https://platform.hq-xl.com/label_classify_image/2018/07/5b471c3207fa1.png"},{"id":28,"name":"心智瑜伽","image_url":"https://platform.hq-xl.com/label_classify_image/2018/07/5b471c3e33d75.png"},{"id":27,"name":"睡眠瑜伽","image_url":"https://platform.hq-xl.com/label_classify_image/2018/07/5b471c47223ea.png"},{"id":26,"name":"头痛焦虑瑜伽","image_url":"https://platform.hq-xl.com/label_classify_image/2018/07/5b471c4d3870e.png"},{"id":25,"name":"长者保健瑜伽","image_url":"https://platform.hq-xl.com/label_classify_image/2018/07/5b471c55003bf.png"},{"id":24,"name":"亲子瑜伽","image_url":"https://platform.hq-xl.com/label_classify_image/2018/07/5b471c5ae5882.png"}]
     */

    private int type;
    private List<ShopExperienceBanner> bannerList;
    private List<ShopExperienceTag> tagList;


    public ShopExperienceHeadBeanV2() {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<ShopExperienceBanner> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<ShopExperienceBanner> bannerList) {
        this.bannerList = bannerList;
    }

    public List<ShopExperienceTag> getTagList() {
        return tagList;
    }

    public void setTagList(List<ShopExperienceTag> tagList) {
        this.tagList = tagList;
    }

}
