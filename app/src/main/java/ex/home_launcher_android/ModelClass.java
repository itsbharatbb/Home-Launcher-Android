package ex.home_launcher_android;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by B on 27/08/2015.
 *
 */
public class ModelClass implements Serializable{
    Map<Intent,Drawable> drawableMap;
    Map<Intent,CharSequence> titleMap;
    Map<Integer,Intent> intentMap;
    List<ResolveInfo> li;
    Context context;
    PackageManager pm;
    Gson gson;



    public  ModelClass(Context context) {
        drawableMap = new HashMap<>();
        titleMap = new HashMap<>();
        intentMap = new HashMap<>();
        pm = context.getPackageManager();
        this.context = context;
        li = pm.queryIntentActivities(new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER), 0);
        gson = new Gson();
        createMaps();

    }

        void createMaps(){
            int i = 0;
            for (ResolveInfo ri : li) {
                intentMap.put(i, new Intent().setClassName(ri.activityInfo.packageName, ri.activityInfo.name));
                titleMap.put(intentMap.get(i), ri.loadLabel(pm));
                drawableMap.put(intentMap.get(i), ri.loadIcon(pm));
                i++;
            }

            SharedPreferences pref =context.getSharedPreferences("SETTINGS",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor= pref.edit();
            editor.putString("intentMap", gson.toJson(intentMap));
            editor.putString("titleMap", gson.toJson(titleMap));
            editor.commit();

           try {
               File file = new File(context.getFilesDir(),"drawableMap.ser");
               FileOutputStream fos = new FileOutputStream(file);
               ObjectOutputStream oos = new ObjectOutputStream(fos);
               oos.writeObject(drawableMap);
               oos.close();
               fos.close();
            }catch (Exception e){
               e.printStackTrace();
           }

            this.context=null;
            pm=null;
            li=null;
    }


    Map<Integer,Intent> getIntentMap(){
        return  intentMap;
    }

    public Map<Intent, Drawable> getDrawableMap() {
        return drawableMap;
    }

    public Map<Intent, CharSequence> getTitleMap() {
        return titleMap;
    }







}
