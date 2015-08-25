package ex.home_launcher_android;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;


public class IconCachingClass {
    Context context;
    PackageManager pm;
    List<ResolveInfo> appsList; //Contains ResolviInfos of all apps based on Intent

    IconCachingClass(Context context){
        this.context=context;
        pm=context.getPackageManager();
        appsList = pm.queryIntentActivities(new Intent(Intent.ACTION_MAIN)
                .addCategory(Intent.CATEGORY_LAUNCHER),0);
    }

    void cacheIcons() {

        Drawable drawableIcon;
        Bitmap icon;
        File file;
        ResolveInfo ri;
        for (int i = 0; i < appsList.size(); i++) {
            ri = appsList.get(i);
            drawableIcon = ri.loadIcon(pm);
            icon=drawableToBitmap(drawableIcon);
            try {
                file=new File(context.getFilesDir(),ri.activityInfo.packageName);
                FileOutputStream fos = new FileOutputStream(file);
                icon.compress(Bitmap.CompressFormat.PNG,100,fos);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap;
        Canvas canvas;




        if (drawable.getIntrinsicHeight() >0 || drawable.getIntrinsicWidth()>0){
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }
        else
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel


        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }


}
