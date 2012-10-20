package com.example.autotest;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.concurrent.CopyOnWriteArrayList;

import junit.framework.Assert;

import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;


public class InvokeMenuBuilder {
    
    private static CopyOnWriteArrayList<WeakReference<Object>> mPresenters =
            new CopyOnWriteArrayList<WeakReference<Object>>();
            
    public static View getMenuView(Menu menu, int id, ViewGroup vg){
        return (View) Invoker.invoke(menu, "getMenuView", new Class[]{int.class, ViewGroup.class} , new Object[]{id, vg});
    }
    
    
    public static View getmPresenters(Menu menu, int id, ViewGroup vg){
        String fieldName = "mPresenters";
        Field fields[];
        try {
            fields = Class.forName("com.android.internal.view.menu.MenuBuilder").getDeclaredFields();
            for (int i = 0; i < fields.length; ++i) {
                if (fieldName.equals(fields[i].getName())) {
                    try {
                        fields[i].setAccessible(true);
                        Log.i("tbt", "======= accessible =======");
                        mPresenters = (CopyOnWriteArrayList<WeakReference<Object>>) fields[i].get(menu);
                        if (mPresenters.isEmpty()){
                            Log.i("tbt", "======= empty =======");
                        } else {
                            Log.i("tbt", "======= not empty =======");
                        }
                        for (WeakReference<Object> ref : mPresenters) {
                            Object presenter = ref.get();
                            if (presenter == null) {
                                Log.i("tbt", "======= presenter null =======");
                            } else {
                                Log.i("tbt", "======= presenter NOT null!!! =======");
                                Log.i("tbt", "===== " + presenter.getClass() + "=====");
                                View v = getMenuView(presenter, vg);
                                if (v != null && v.isShown()){
                                    Log.i("tbt", "******* v is shown *****");
                                    return v;
                                } else {
                                    Log.i("tbt", "$$$$$$$ !v is NOT shown !!!");
                                }
                            }
                        }
//                        return (View[]) fields[i].get(wm);
                    } catch (IllegalAccessException ex) {
                        Assert.fail("IllegalAccessException accessing " + fieldName);
                    }
                }
            }
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    private static View getMenuView(Object presenter, ViewGroup vg) {
        // TODO Auto-generated method stub
        return (View) Invoker.invoke(presenter, "getMenuView", new Class[]{ViewGroup.class} , 
                new Object[]{vg});
    }
}
