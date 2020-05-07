# SubKeepalive
ROOT设备静默安装保活子应用

    // 双应用互拉保活机制
    // 原理: service.linkToDeath()  监听对方相关服务所在进程死亡事件,再重新拉活app
    // 缺点: 1.需要再安装一个子应用,增加app体积 ≈ 1 M ; 设备必须 ROOT (进行用户无感知 shell命令安装或升级;)
    //       2.启动Activity 中尽量不要出现启动类异常,否则会进入无限拉活再闪退
    //       (趁拉活至闪退间隙快速 home-设置-应用-卸载SubUhome )


    //使用:  (注意包名)
    // 1. 该项目去除keepalive包下的代码,打包生成apk
    // 2. apk放入目标项目ass文件夹下
    // 3. keepalive包赋值到目标应用
    // 4. 目标应用启用保活时

    //    if (!isInstalledKALargerNew()) {
    //        installKA(KeepAliveConstants.KA_FILE_NAME)
    //    } else {
    //        KeepAlivePresenter.getInstance().bindKeepAliveService(com.haier.uhome.smartmirro.MirrorApplication.myApp)
    //    }
    //4.解绑保活
    //KeepAlivePresenter.getInstance().unBind();
