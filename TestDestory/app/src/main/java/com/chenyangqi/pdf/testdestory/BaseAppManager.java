package com.chenyangqi.pdf.testdestory;


import android.app.Activity;

import androidx.annotation.NonNull;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class BaseAppManager {

    private static final String TAG = BaseAppManager.class.getSimpleName();

    private static BaseAppManager instance = null;
    private static List<Activity> mActivities = new LinkedList<Activity>();

    private BaseAppManager() {

    }

    public static BaseAppManager getInstance() {
        if (null == instance) {
            synchronized (BaseAppManager.class) {
                if (null == instance) {
                    instance = new BaseAppManager();
                }
            }
        }
        return instance;
    }

    public int size() {
        return mActivities.size();
    }

    public synchronized Activity getForwardActivity() {
        return size() > 0 ? mActivities.get(size() - 1) : null;
    }

    public synchronized void addActivity(Activity activity) {
        mActivities.add(activity);
    }

    public synchronized void removeActivity(Activity activity) {
        if (mActivities.contains(activity)) {
            mActivities.remove(activity);
        }
    }

    /**
     * 根据类移除Activity
     * @param act
     */
    public synchronized void removeActivity(Class<?> act) {
        Iterator<Activity> iterator = mActivities.listIterator();
        while (iterator.hasNext()) {
            Activity activity = iterator.next();
            if (activity.getClass().equals(act)) {
                iterator.remove();
                activity.finish();
                break;
            }
        }
    }

    public synchronized void clear() {
        for (int i = mActivities.size() - 1; i > -1; i--) {
            Activity activity = mActivities.get(i);
            removeActivity(activity);
            activity.finish();
            i = mActivities.size();
        }
    }

    public synchronized void clearToTop() {
        for (int i = mActivities.size() - 2; i > -1; i--) {
            Activity activity = mActivities.get(i);
            removeActivity(activity);
            activity.finish();
            i = mActivities.size() - 1;
        }
    }

    public synchronized void clearOneToTop() {
        if (size() <= 0) return;

        Activity act = mActivities.remove(mActivities.size() - 1);
        act.finish();
    }

    public boolean isTaskTop(Activity activity) {
        if (mActivities.size() <= 0 || !activity.getClass().getName().equals(mActivities.get(mActivities.size() - 1).getClass().getName())) {
            return false;
        } else {
            return true;
        }
    }

    public synchronized boolean isContain(String activityName) {
        if (mActivities.size() <= 0) {
            return false;
        }

        for (Activity activity : mActivities) {
            if (activity.getClass().getSimpleName().equals(activityName)) {
                return true;
            }
        }

        return false;
    }

    public static @NonNull List<Activity> getActivities() {
        return mActivities;
    }
}
