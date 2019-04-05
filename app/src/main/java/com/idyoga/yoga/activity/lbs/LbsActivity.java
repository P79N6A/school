package com.idyoga.yoga.activity.lbs;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.bumptech.glide.Glide;
import com.idyoga.yoga.R;
import com.idyoga.yoga.base.BaseActivity;
import com.idyoga.yoga.comm.AppContext;
import com.idyoga.yoga.lbs.LbsUtil;
import com.idyoga.yoga.lbs.LocationManager;
import com.idyoga.yoga.lbs.YogaLocationListener;
import com.idyoga.yoga.model.YogaMenuBean;
import com.idyoga.yoga.utils.ToastUtil;
import com.idyoga.yoga.view.YogaContextMenu;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import vip.devkit.library.Logcat;

/**
 * 文 件 名: LbsActivity
 * 创 建 人: By k
 * 创建日期: 2018/5/7 10:11
 * 邮   箱:  vip@devkit.vip
 * 博   客: www.devkit.vip
 * 修改时间：
 * 修改备注：
 */
public class LbsActivity extends BaseActivity {
    @BindView(R.id.ll_layout_back)
    LinearLayout mLlLayoutBack;
    @BindView(R.id.ll_web_close)
    LinearLayout mLlWebClose;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.ll_layout_more)
    LinearLayout mLlLayoutMore;
    @BindView(R.id.ll_common_layout)
    LinearLayout mLlCommonLayout;
    @BindView(R.id.mv_bMap)
    MapView mMapView;

    private BaiduMap mBaiduMap;
    private LatLng mLatLng;
    private LocationManager mLocationManager;
    private MyLocationConfiguration.LocationMode mCurrentMode;
    private PlanNode stNode, enNode;
    private RoutePlanSearch mSearch;
    private String shopName = "", shopAddress = "";
    private String latitude, longitude;

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(mLlCommonLayout).init();
    }

    @Override
    protected void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            latitude = bundle.getString("latitude");
            longitude = bundle.getString("longitude");
            shopName = bundle.getString("shopName");
            shopAddress = bundle.getString("shopAddress");
            Logcat.i("latitude:" + latitude + " longitude:" + longitude + "/" + LbsUtil.isBaiduMapInstalled() + "/" + LbsUtil.isAmapMapInstalled());
            mLatLng = new LatLng(Double.valueOf(latitude), Double.valueOf(longitude));
        } else {
            mLatLng = new LatLng(23.143548, 113.3829);
        }

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_map;
    }

    @Override
    protected void initView() {

        mTvTitle.setText("地图");
        Logcat.i("mLatLng:" + mLatLng.latitude + "/" + mLatLng.longitude + "/" + mLatLng.toString());
        initMap();
        initMapView();

        mSearch = RoutePlanSearch.newInstance();
    }

    private void initMapView() {
        View view = View.inflate(this, R.layout.layout_location_view, null);
        ((TextView) view.findViewById(R.id.tv_shop_name)).setText(shopName);
        ((TextView) view.findViewById(R.id.tv_shop_address)).setText(shopAddress);
        view.findViewById(R.id.ll_nav).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMapApp(view);
            }
        });
        LatLng pt = new LatLng(mLatLng.latitude, mLatLng.longitude);
        InfoWindow mInfoWindow = new InfoWindow(view, pt, -60);
        mBaiduMap.showInfoWindow(mInfoWindow);
    }

    private void initMap() {
        mBaiduMap = mMapView.getMap();
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(mLatLng);
        mMapView.getMap().setMapStatus(mapStatusUpdate);
        mBaiduMap.setMyLocationEnabled(true);//开启图层
        mBaiduMap.setMyLocationData(mBaiduMap.getLocationData());
        mBaiduMap.setMapStatus(mapStatusUpdate);
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.ic_location_target_mark);
        OverlayOptions option = new MarkerOptions().title("目标位置").position(mLatLng).icon(bitmap);
        mBaiduMap.addOverlay(option);


        MapStatus.Builder builder1 = new MapStatus.Builder();
        builder1.overlook(0);
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder1.build()));

        mLocationManager = LocationManager.init(this, mYogaLocationListener);
        mLocationManager.onStart();
    }


    @Override
    protected void setListener() {

        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Logcat.i("LatLng：" + latLng.toString());
            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                return false;
            }
        });

        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                Logcat.i("Marker:" + marker.getTitle() + "/" + marker.getPeriod());
                return false;
            }
        });


    }

    YogaLocationListener mYogaLocationListener = new YogaLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation location) {
            super.onReceiveLocation(location);
            double latitude = location.getLatitude();    //获取纬度信息
            double longitude = location.getLongitude();    //获取经度信息
            float radius = location.getRadius();    //获取定位精度，默认值为0.0f
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
//            mBaiduMap.setMyLocationData(locData);

            Logcat.i("lo:" + location.getStreet() + "/" + location.getDistrict());
            stNode = PlanNode.withCityNameAndPlaceName("广州", location.getAddrStr());
            enNode = PlanNode.withCityNameAndPlaceName("广州", "车陂");
            mSearch.drivingSearch((new DrivingRoutePlanOption())
                    .from(stNode)
                    .to(enNode));
            BitmapDescriptor bitmap2 = BitmapDescriptorFactory.fromResource(R.drawable.ic_location_mark);
            LatLng latLng = new LatLng(latitude, longitude);
            OverlayOptions option2 = new MarkerOptions().title("当前位置").position(latLng).icon(bitmap2);
            mBaiduMap.addOverlay(option2);

            mLocationManager.onStop();
        }
    };

    @Override
    protected void onResume() {
        if (mMapView != null) {
            mMapView.setVisibility(View.VISIBLE);
            mMapView.onResume();
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        if (mMapView != null) {
            mMapView.setVisibility(View.INVISIBLE);
            mMapView.onPause();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (mMapView != null) {
            MapView.setMapCustomEnable(false);
            mMapView.onDestroy();
        }
        super.onDestroy();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.BUTTON_BACK) {
            finish();
        }
        return true;
    }


    @OnClick({R.id.ll_layout_back, R.id.ll_layout_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_layout_back:
                finish();
                break;
            case R.id.ll_layout_more:
                final List<YogaMenuBean> menuItems = new ArrayList<>();
                menuItems.add(new YogaMenuBean(R.drawable.icon_copy, "复制瑜伽馆地址"));
                menuItems.add(new YogaMenuBean(R.drawable.ic_bar_blank_share, "地图报错"));
                YogaContextMenu contextMenu = new YogaContextMenu(this)
                        .addMenuList(menuItems)
                        .dimBackground(false)
                        .setOnItemSelectListener(new YogaContextMenu.OnItemSelectListener() {
                            @Override
                            public void onItemSelect(int position) {
                                menuItemClicked(menuItems.get(position));
                            }
                        });
                contextMenu.showMenu(mLlLayoutMore);
                break;
        }
    }

    private void menuItemClicked(YogaMenuBean yogaMenuBean) {
        switch (yogaMenuBean.getText()) {
            case "复制瑜伽馆地址":
                toCopy(LbsActivity.this, shopAddress);
                ToastUtil.showToast("复制成功");
                break;
            case "地图报错":
                ToastUtil.showToast("敬请期待");
                break;
        }
    }

    /**
     * 复制字符串
     *
     * @param context
     * @param text
     */
    private void toCopy(Context context, String text) {
        ClipboardManager mClipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        mClipboardManager.setPrimaryClip(ClipData.newPlainText(null, text));
    }


    public void showMapApp(View view) {
        final List<YogaMenuBean> menuItems = new ArrayList<>();

        if (LbsUtil.isAmapMapInstalled()) {
            menuItems.add(new YogaMenuBean(R.drawable.ic_a_map, "高德地图"));
        }
        if (LbsUtil.isBaiduMapInstalled()) {
            menuItems.add(new YogaMenuBean(R.drawable.ic_baidu_map, "百度地图"));
        }
        if (LbsUtil.isTencentInstalled()) {
            menuItems.add(new YogaMenuBean(R.drawable.ic_qq_map, "腾讯地图"));
        }

        if (menuItems.size() == 0) {
            menuItems.add(new YogaMenuBean(R.drawable.ic_baidu_map, "百度地图（网页版）"));
        }

        YogaContextMenu contextMenu = new YogaContextMenu(this)
                .addMenuList(menuItems)
                .setWidth(0)
                .dimBackground(false)
                .setOnItemSelectListener(new YogaContextMenu.OnItemSelectListener() {
                    @Override
                    public void onItemSelect(int position) {
                        Logcat.i("点击了菜单：" + menuItems.get(position));
                        mapMenuClicked(menuItems.get(position));
                    }
                });
        contextMenu.showMenu(view,+150,0);
    }

    private void mapMenuClicked(YogaMenuBean yogaMenuBean) {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            latitude = bundle.getString("latitude");
            longitude = bundle.getString("longitude");
        }
        Logcat.i("latitude:" + latitude + " longitude :" + longitude);
        switch (yogaMenuBean.getText()) {
            case "高德地图":
                LbsUtil.openAmap(this, latitude, longitude,shopName,shopAddress);
                break;
            case "百度地图":
                LbsUtil.openBaiduMap(this, latitude, longitude,shopName,shopAddress);
                break;
            case "腾讯地图":
                LbsUtil.openQQMap(this, latitude, longitude,shopName,shopAddress);
                break;
            case "百度地图（网页版）":
                LbsUtil.openBaiduWebMap(this, latitude, longitude,shopName,shopAddress);
                break;
        }
    }

}
