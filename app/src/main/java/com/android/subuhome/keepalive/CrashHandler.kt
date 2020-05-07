package com.haier.uhome.smartmirro.setting.keepalive

import android.annotation.SuppressLint
import android.content.Context
import java.util.*


/**
 * Created by DengDongQi on 2020/3/28
 */
class CrashHandler private constructor() : Thread.UncaughtExceptionHandler {

    private var mDefaultCrashHandler: Thread.UncaughtExceptionHandler? = null

    private var mContext: Context? = null

    /**
     * 倒计时显示3
     */
    private val timer: Timer = Timer()

    /**
     * 初始化
     * @param context
     */
    fun init(context: Context) {
        //获取系统默认的异常处理器
        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler()
        //将当前实例设为系统默认的异常处理器
        Thread.setDefaultUncaughtExceptionHandler(this)
        mContext = context.applicationContext
    }

    /**
     * 这个是最关键的函数，当程序中有未被捕获的异常，系统将会自动调用#uncaughtException方法
     */
    override fun uncaughtException(thread: Thread, throwable: Throwable) {
        //打印出当前调用栈信息
        throwable.printStackTrace()
        //交给系统的UncaughtExceptionHandler处理
//        mDefaultCrashHandler!!.uncaughtException(thread, throwable)
        //Bugly上报
//        CrashReport.postCatchedException(throwable)
//        LogUtils.e("DDQ","自杀")

        //我们自己结束自己,由保活应用再次拉起
//        android.os.Process.killProcess(android.os.Process.myPid())
        //延时自杀,马上自杀会导致bugly日志上传失败,检查不出异常日志
        timer.schedule(object : TimerTask() {
            override fun run() {
                //我们自己结束自己,由保活应用再次拉起
//                LogUtils.e("DDQ", "延时自杀")
                android.os.Process.killProcess(android.os.Process.myPid())
//            exitProcess(10)
                timer.cancel()
            }
        }, 3000)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        val instance = CrashHandler()
    }
}