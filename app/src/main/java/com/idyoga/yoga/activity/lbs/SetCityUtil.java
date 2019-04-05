package com.idyoga.yoga.activity.lbs;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.idyoga.yoga.model.CityBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import vip.devkit.library.ListUtil;
import vip.devkit.library.Logcat;
import vip.devkit.library.SharedPreferencesUtils;
import vip.devkit.library.StringUtil;

public class SetCityUtil {
    Context mContext;

    public static SetCityUtil init(Context context) {
        return new SetCityUtil(context);
    }

    public SetCityUtil(Context context) {
        this.mContext = context;
    }

    /**
     * @param cityBean
     */
    public void addRecentCityBean(CityBean.RecentCityBean cityBean) {
        String json = (String) SharedPreferencesUtils.getSP(mContext, "RecentCity", "");
        List<CityBean.RecentCityBean> beanList;
        if (StringUtil.isEmpty(json)) {
            beanList = new ArrayList<>();
        } else {
            beanList = JSON.parseArray(json, CityBean.RecentCityBean.class);
        }
        if (cityBean != null) {
            beanList.add(cityBean);
        }
        setRecentCityBean(removeDuplicate(beanList));
    }

    /**
     * @param beanList
     */
    public void setRecentCityBean(List<CityBean.RecentCityBean> beanList) {
        String json = JSON.toJSONString(beanList);
        SharedPreferencesUtils.setSP(mContext, "RecentCity", json);
    }

    /**
     * @return
     */
    public List<CityBean.RecentCityBean> getRecentCityBean() {
        String json = (String) SharedPreferencesUtils.getSP(mContext, "RecentCity", "");
        Logcat.i("json:" + json);
        if (StringUtil.isEmpty(json)) {
            return null;
        }
        return JSON.parseArray(json, CityBean.RecentCityBean.class);
    }


    /**
     * @param list
     * @return 去重
     */
    public static List<CityBean.RecentCityBean> removeDuplicate(List<CityBean.RecentCityBean> list) {
        for (CityBean.RecentCityBean bean : list) {
            bean.setSelect(false);
        }
        HashSet set = new HashSet(list);
        list.clear();
        removeDuplicate(set);
        list.addAll(set);
        return list;
    }

    private static Set<CityBean.RecentCityBean> removeDuplicate(Set<CityBean.RecentCityBean> set) {
        Map<String, CityBean.RecentCityBean> map = new HashMap<String, CityBean.RecentCityBean>();
        Set<CityBean.RecentCityBean> tempSet = new HashSet<CityBean.RecentCityBean>();
        for (CityBean.RecentCityBean p : set) {
            if (map.get(p.getName()) == null) {
                map.put(p.getName(), p);
            } else {
                tempSet.add(p);
            }
        }
        set.removeAll(tempSet);
        return set;
    }


    public static void setCityBean(Context context, CityBean bean) {
        String json = JSON.toJSONString(bean);
        SharedPreferencesUtils.setSP(context, "CityBean", json);
    }

    public static CityBean getCityBean(Context context) {
        String json = (String) SharedPreferencesUtils.getSP(context, "CityBean", "");
        if (!StringUtil.isEmpty(json)) {
            return JSON.parseObject(json, CityBean.class);
        }
        return null;
    }

}
