package com.cyanogenmod.settings.device;

import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.content.Intent;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceManager;
import android.preference.PreferenceActivity;

public class TouchKeyBacklight implements OnPreferenceClickListener {

    private PreferenceActivity mContext;

    public static final String PACKAGE_BLN = "neldar.bln.control.free";

    public boolean isPackageExists(String targetPackage){
        List<ApplicationInfo> packages;
        PackageManager pm;
            pm = mContext.getPackageManager();        
            packages = pm.getInstalledApplications(0);
            for (ApplicationInfo packageInfo : packages) {
        if(packageInfo.packageName.equals(targetPackage)) return true;
        }        
        return false;
    }

    public TouchKeyBacklight(PreferenceActivity context) {
        mContext = context;
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
	    if (isPackageExists(PACKAGE_BLN)) {
            Intent i = new Intent();
            PackageManager manager = mContext.getPackageManager();
            i = manager.getLaunchIntentForPackage(PACKAGE_BLN);
            i.addCategory(Intent.CATEGORY_LAUNCHER);
            mContext.startActivity(i);
        } else {
            Intent intent = new Intent(Intent.ACTION_VIEW); 
            intent.setData(Uri.parse("market://details?id="+PACKAGE_BLN)); 
            mContext.startActivity(intent);
        }
        return true;
    }

}

