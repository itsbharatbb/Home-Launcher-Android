package ex.home_launcher_android;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * Created by B on 27/08/2015.
 *
 */
public class ModelClass implements Serializable{
    HashMap<Integer,Drawable> drawableMap;
    HashMap<Integer,CharSequence> titleMap;
    HashMap<Integer,Intent> intentMap;
    List<ResolveInfo> li;
    Context context;
    PackageManager pm;


    public  ModelClass(Context context){
        drawableMap = new HashMap<>();
        titleMap = new HashMap<>();
        intentMap = new HashMap<>();
        pm = context.getPackageManager();
        this.context=context;
        li=pm.queryIntentActivities(new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER), 0);
        int i=0;
        for(ResolveInfo ri: li){
            drawableMap.put(i,ri.loadIcon(pm));
            titleMap.put(i,ri.loadLabel(pm));
            intentMap.put(i,new Intent().setClassName(ri.activityInfo.packageName,ri.activityInfo.name));
            i++;
        }
        this.context=null;
        pm=null;
        li=null;
    }
}
