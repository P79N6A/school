package com.idyoga.yoga.receiver;

/**
 * @author weilgu
 * @time 2019/3/4 15:29
 * @des 监听 gps 开启 / 关闭的回调
 */

public interface IGpsChangerCallback {

    void gpsSwitchState( boolean gpsOpen );
}
