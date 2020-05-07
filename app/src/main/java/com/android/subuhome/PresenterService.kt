package com.android.subuhome

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import androidx.annotation.Nullable

/**
 * Created by DengDongQi on 2020/3/28
 */
class PresenterService : Service() {

    //通过binder实现调用者client与Service之间的通信
    inner class MyBinder : Binder() {
        val service: PresenterService
            get() = this@PresenterService
    }



    @Nullable
    override fun onBind(intent: Intent): IBinder? {
        //绑定MainApp死亡监听
        MainDeathMonitor.bindDeathRecipientService()

        return MyBinder()
    }
}