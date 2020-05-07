package com.android.subuhome

import android.annotation.SuppressLint
import android.content.Context

/**
 * Created by DengDongQi on 2020/3/28
 */
class CrashHandler private constructor() : Thread.UncaughtExceptionHandler {

    private var mDefaultCrashHandler: Thread.UncaughtExceptionHandler? = null

    private var mContext: Context? = null

    /**
     * 初始化
     *
     * @param context
     */

    fun init(context: Context) {
        //获取系统默认的异常处理器
        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler()
        //将当前实例设为系统默认的异常处理器
        Thread.setDefaultUncaughtExceptionHandler(this)
        //获取Context，方便内部使用
        mContext = context.applicationContext
    }

    /**
     * 这个是最关键的函数，当程序中有未被捕获的异常，系统将会自动调用#uncaughtException方法
     * thread为出现未捕获异常的线程，ex为未捕获的异常，有了这个throwable，我们就可以得到异常信息
     *
     * @param thread
     * @param throwable
     */
    override fun uncaughtException(thread: Thread, throwable: Throwable) {
        //打印出当前调用栈信息
        throwable.printStackTrace()
        //我们自己结束自己
        android.os.Process.killProcess(android.os.Process.myPid())
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        val instance = CrashHandler()
    }
}