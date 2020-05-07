package com.android.subuhome

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.os.RemoteException
import android.util.Log

/**
 * Created by DengDongQi on 2020/3/28
 */
class MainDeathMonitor {

    companion object{
        val TAG = "DDQ_LOG"
        
        var serviceConnection : ServiceConnection? = null
        /**
         * bind服务
         */
        fun bindDeathRecipientService() {
            val intent = Intent()
            intent.setPackage(Constants.PACKAGE_NAME)
            intent.action = Constants.SERVICE_NAME
            MyApplication.application!!.bindService(intent, connection, Context.BIND_AUTO_CREATE)
            serviceConnection = connection
        }

        /**
         * 服务连接
         */
        private val connection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName, service: IBinder) {
                Log.i(TAG,"MainAppDeathService  连接服务! ")
                try {
                    // 注册死亡代理
                    service.linkToDeath(mDeathRecipient, 0)
                    Log.i(TAG, "MainAppDeathService死亡代理注册成功! ")
                } catch (e: RemoteException) {
                    e.printStackTrace()
                    Log.i(TAG, "MainAppDeathService死亡代理注册失败! ")
                }

            }

            override fun onServiceDisconnected(name: ComponentName) {
                Log.i(TAG, "MainAppDeathService 断开服务")
            }
        }

        /**
         * 死亡代理
         * 服务随时都可能被kill，如果此时再次调用服务端的接口，会引起deadObject异常的发生
         * 添加死亡代理在服务killed之后再次拉起
         */
        private val mDeathRecipient = object : IBinder.DeathRecipient {
            // 当绑定的service异常断开连接后，会自动执行此方法
            override fun binderDied() {
                Log.i(TAG, "MainAppDeathService si翘翘了,要保活的APP进程挂了,咋办大哥!")
                //拉起海尔应用
                if(checkAppInstalled(MyApplication.application!!.applicationContext,Constants.PACKAGE_NAME)) {
                    val mIntent = Intent()
                    mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    val comp =
                        ComponentName(Constants.PACKAGE_NAME, Constants.START_NAME)
                    mIntent.component = comp
                    mIntent.action = "android.intent.action.VIEW"
                    MyApplication.application!!.startActivity(mIntent)
                }
            }
        }

        /**
         * 检查app是否安装
         *
         * */
        fun checkAppInstalled(context: Context, pkgName: String?): Boolean {
            if (pkgName == null || pkgName.isEmpty()) {
                return false
            }
            val packageManager = context.packageManager
            val info = packageManager.getInstalledPackages(0)
            if (info.isEmpty()) return false
            for (i in info.indices) {
                if (pkgName == info[i].packageName) {
                    return true
                }
            }
            return false
        }

    }
}