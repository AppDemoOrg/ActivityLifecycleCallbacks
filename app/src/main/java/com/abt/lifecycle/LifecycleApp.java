package com.abt.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.Stack;

/**
 * Created by huangweiqi on 05/05/2018.
 */
public class LifecycleApp extends Application {

    private static LifecycleApp mApp;
    public static Stack<ActivityDetail> mStore;
    private static final int MAX_ACTIVITY_DETAIL_NUM = 2;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        mStore = new Stack<ActivityDetail>();

        Logger.addLogAdapter(new AndroidLogAdapter());
        this.registerActivityLifecycleCallbacks(new SwitchPageCallbacks());
    }

    public static LifecycleApp getAppContext() {
        return mApp;
    }

    public static boolean toGoodDetail(String id) {
        if (mStore == null || mStore.empty()) {
            return false;
        }
        for (ActivityDetail activityDetail : mStore) {
            if (id.equalsIgnoreCase(activityDetail.getID())) {
                activityDetail.finish();
                return true;
            }
        }
        return false;
    }

    private class SwitchPageCallbacks implements ActivityLifecycleCallbacks {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            Logger.d("onActivityCreated");
            if (activity instanceof ActivityDetail) {
                if (mStore.size() >= MAX_ACTIVITY_DETAIL_NUM) {
                    ((ActivityDetail)mStore.peek()).finish();
                    // 移除栈底页面且finish，保证总页面不超过MAX页
                }
                mStore.add((ActivityDetail)activity);
            }
        }

        @Override
        public void onActivityStarted(Activity activity) {
            Logger.d("onActivityStarted");
        }

        @Override
        public void onActivityResumed(Activity activity) {
            Logger.d("onActivityResumed");
        }

        @Override
        public void onActivityPaused(Activity activity) {
            Logger.d("onActivityPaused");
        }

        @Override
        public void onActivityStopped(Activity activity) {
            Logger.d("onActivityStopped");
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            Logger.d("onActivitySaveInstanceState");
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            Logger.d("onActivityDestroyed");
            mStore.remove(activity);
        }
    }

    public ActivityDetail getCurActivity() {
        return mStore.lastElement();
    }

}
