package com.idyoga.yoga.model.realm;

import java.util.List;


/**
 * 文 件 名: ShopExperienceCourseClassTag
 * 功能描述:
 * 创 建 人: By k
 * 邮    箱:vip@devkit.vip
 * 网    站:www.devkit.vip
 * 创建日期: 2018/6/21
 * 版   本: V 1.0
 * 代码修改:（修改人 - 修改时间）
 * 修改备注：
 */
public class ShopExperienceCourseClassTag  {
        /**
         * id : 67
         * name : 抑郁缓解瑜伽
         */

        private int id;
        private String name;
        private boolean isVisibility;

        public boolean isVisibility() {
            return isVisibility;
        }

        public void setVisibility(boolean visibility) {
            isVisibility = visibility;
        }

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
