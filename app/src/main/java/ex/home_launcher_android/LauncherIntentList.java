package ex.home_launcher_android;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.HashMap;
import java.util.List;

/**
 *Created by B on 27/08/2015.
 */
public class LauncherIntentList {
    Context context;
    HashMap<Integer,Intent> hashMap;
    List<ResolveInfo> l;
    PackageManager pm;

    LauncherIntentList(Context context){
        this.context=context;
        hashMap = new HashMap<>();
        pm=context.getPackageManager();
        l=pm.queryIntentActivities(new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER), 0);
        for (int i = 0; i < l.size(); i++) {
            hashMap.put(i, new Intent().setClassName(l.get(i).
                    activityInfo.packageName, l.get(i).activityInfo.name));
        }
    }

    HashMap<Integer,Intent> getIntentList(){
        return hashMap;
    }
}
