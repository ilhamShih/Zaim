package com.zaimutest777.zaim.helper;

import android.os.Build;

public class Davlik {
    public static String getdavlik(){

        String path_name = System.getProperty("java.vm.name");// Dalvik
        String path_version = System.getProperty("java.vm.version");// 2.1.0
        String path_os_name = System.getProperty("os.name");// Linux
        String path_model = Build.MODEL; //----------------------------- Android SDK built for x86
        String path_rele = Build.VERSION.RELEASE;// 9
        String path_id = Build.ID; // PSR1.180720.075

        String path_ret =path_name+"/"+path_version+"("+ path_os_name+"; "+"U; "+"Android "+path_rele+"; "+path_model+"; "+ path_id+")";

        return path_ret;

    }

}

