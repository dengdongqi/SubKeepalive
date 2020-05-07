package com.android.subuhome

import android.app.Application

/**
 * Created by DengDongQi on 2020/3/28
 */
class MyApplication : Application(){

    companion object{
        var application:MyApplication? = null
    }

    override fun onCreate() {
        super.onCreate()
        if(application == null){
            application = this
            inits()
        }
    }

    private fun inits() {
        //Crash     发送崩溃不弹出系统提示信息
        CrashHandler.instance.init(application!!.applicationContext)
    }
}