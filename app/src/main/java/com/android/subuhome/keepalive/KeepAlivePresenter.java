package com.android.subuhome.keepalive;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;


/**
 * Created by DengDongQi on 2020/3/28
 * 保活提供者
 */
public class KeepAlivePresenter {

    private Context mContext;

    private static KeepAlivePresenter presenter = null;

    private KeepAlivePresenter(){

    }

    public static KeepAlivePresenter getInstance(){
        if(presenter == null){
            synchronized (KeepAlivePresenter.class){
                if(presenter == null){
                    presenter =new KeepAlivePresenter();
                }
            }
        }
        return presenter;
    }


    /**
     * bind 保活提供者服务
     */
    public void bindKeepAliveService(Context context) {
        mContext = context;
        Intent intent = new Intent();
        intent.setPackage(KeepAliveConstants.KA_PACKAGE_NAME);
        intent.setAction(KeepAliveConstants.KA_SERVICE_ACTION);
        mContext.bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    /**
     * 服务连接
     */
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //LogUtils.d( "PresenterService  连接服务! ");
            try {
                // 注册死亡代理
                service.linkToDeath(mDeathRecipient, 0);
                //LogUtils.d( "PresenterService死亡代理注册成功! ");
            } catch (RemoteException e) {
                e.printStackTrace();
                //LogUtils.d( "PresenterService死亡代理注册失败! ");
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //LogUtils.d( "PresenterService 断开服务");
        }
    };

    /**
     * 死亡代理
     * 添加死亡代理在服务killed之后再次拉起
     * */
    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
        // 当绑定的service既目标IBinder的进程已经死亡后，会执行此方法
        @Override
        public void binderDied() {
            //LogUtils.d("PresenterService killed 之后再次拉起服务" );
            //先拉子App起来
            Intent intent = mContext.getPackageManager().getLaunchIntentForPackage(KeepAliveConstants.KA_PACKAGE_NAME);
            if (intent != null) {
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
            //再重新绑定service
            bindKeepAliveService(mContext);
        }
    };

    /**
     * 解绑服务
     * */
    public void unBind(){
        if(mContext != null) {
            mContext.unbindService(connection);
        }
    }

}
