package com.idyoga.yoga.comm;


import android.content.Context;

import com.idyoga.yoga.common.http.type2.HttpHelper;


/**
 * 作者 by K
 * 时间：on 2017/9/27 09:22
 * 邮箱 by  vip@devkit.vip
 * <p/>
 * 类用途：
 * 最后修改：
 */
public class SQLConfig {
    private static SQLConfig mConfig;
    private Context mContext;


    public static synchronized SQLConfig getInstance() {
        if (mConfig == null) {
            mConfig = new SQLConfig();
        }
        return mConfig;
    }

    public SQLConfig() {
        mConfig = this;
        mContext = AppContext.getInstance();
        init(mContext);
    }

    public void init(Context context) {
    }


    /**
     * 更新数据版本
     *
     * @param schemaVersion 新版本号
     * @param migration     数据迁移配置
     */
    public void updata(int schemaVersion, YogaMigration migration) {
    }

    /**
     * 删除数据库
     */
    public void delete() {
    }


}
