package com.android.subuhome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
//    private var tvTest:Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

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

    }

    /**
     * 已安装的保活 apk 版本 是否大于等于ass中的apk版本
     * @return true 不需要安装 false:需要覆盖安装
     */
//    fun isInstalledKALargerNew(): Boolean {
//        val pManager: PackageManager =
//            MirrorApplication.getInstance().getApplicationContext().getPackageManager()
//        // 获取手机内所有应用
//        val paklist = pManager.getInstalledPackages(0)
//        if (paklist != null) {
//            try {
//                for (idx in paklist.indices) {
//                    val packageInfo = paklist[idx]
//                    val packageName = packageInfo.applicationInfo.packageName
//                    if (packageName == KeepAliveConstants.KA_PACKAGE_NAME) {
//                        val versionCode = packageInfo.versionCode.toString() + ""
//                        val fileVersionCode: String =
//                            KeepAliveConstants.KA_FILE_NAME.split("_").get(3).split("\\.").get(0)
//                        LogUtils.i("保活应用 : versionCode = $versionCode, fileVersionCode = $fileVersionCode")
//                        //已安装的 apk 版本大于等于ass中的apk版本
//                        if (versionCode.toLong() >= fileVersionCode.toLong()) {
//                            return true
//                        }
//                    }
//                }
//                LogUtils.i("保活提供者APK未安装 或 有高版本")
//                //未安装
//                return false
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//        return false
//    }

    /**
     * 安装保活APP
     */
//    fun installKA(assFilePath: String) { ///mnt/internal_sd/Android/data/com.haier.uhome.smartmirro/cache/apk/
//        val mSavePath: String = PathUtils.getExternalAppCachePath().toString() + "/apk/"
//        LogUtils.i("Save to : $mSavePath")
//        val dir = File(mSavePath)
//        if (!dir.isDirectory) {
//            dir.mkdir()
//        }
//        try {
//            val `is`: InputStream = MirrorApplication.getInstance().getMappContext()
//                .getResources().getAssets().open(assFilePath)
//            FileIOUtils.writeFileFromIS(
//                mSavePath + assFilePath,
//                `is`,
//                object : OnProgressUpdateListener() {
//                    fun onProgressUpdate(progress: Double) {
//                        LogUtils.d("progress:$progress") // 1M 18.073至18.092  0.02S完成
//                        //  0.0..   -  1.0...
//                        if (progress == 1.0) {
//                            ShellUtils.execCmdAsync(
//                                "pm install -r $mSavePath$assFilePath",
//                                true,
//                                object : Callback<ShellUtils.CommandResult?>() {
//                                    fun onCall(data: ShellUtils.CommandResult) {
//                                        if (data.result === 0) {
//                                            KeepAlivePresenter.getInstance()
//                                                .bindKeepAliveService(MirrorApplication.getInstance().getMappContext())
//                                        }
//                                    }
//                                })
//                        }
//                    }
//                })
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//    }
}
