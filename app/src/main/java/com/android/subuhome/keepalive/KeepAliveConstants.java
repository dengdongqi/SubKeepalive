package com.android.subuhome.keepalive;

/**
 * <pre>
 * Created by DengDongQi on 2020/3/28
 *
 * 双应用互拉保活机制
 * 原理: service.linkToDeath()  监听对方相关服务所在进程死亡事件,再重新拉活app
 * 缺点: 1.需要再安装一个子应用,增加app体积 ≈ 1 M ; 设备必须 ROOT (进行用户无感知 shell命令安装或升级;)
 *       工厂出厂机器皆ROOT,暂无 无root情况逻辑
 *       2.注,解(PersonalActivity)中尽量不要出现启动类异常,否则会进入无限拉活再闪退
 *       (趁拉活至闪退间隙快速 home-设置-应用-卸载SubUhome )
 *
 * ADB测试:
 *      adb shell am force-stop com.android.subuhome
 *      adb shell am force-stop com.haier.uhome.smartmirro
 * </pre>
 */
public class KeepAliveConstants {

    //保活提供者app包名
    public static String KA_PACKAGE_NAME = "com.android.subuhome";

    //action
    public static String KA_SERVICE_ACTION = "com.android.subuhome.PresenterService";

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //升级保活APP 修改assets内保活app 并且需同步更新此值
    public static String KA_FILE_NAME = "subuhome_2020-03-28_1.0.4_104.apk";
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
}
