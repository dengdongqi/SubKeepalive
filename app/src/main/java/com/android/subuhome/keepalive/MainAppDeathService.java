package com.android.subuhome.keepalive;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * Created by DengDongQi on 2020/3/28
 *
 * 提供给别的App检测本APP的服务进程死亡事件;
 */
public class MainAppDeathService extends Service {

    //通过binder实现调用者client与Service之间的通信
    public class MyBinder extends Binder {
        public MainAppDeathService getService(){
            return MainAppDeathService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }
}
