package ex.home_launcher_android;

import android.content.Context;
import android.content.SharedPreferences;

public class Settings  {
    public final String APP_SETTINGS="app_settings";
    public final String IS_FULL_SCREEN="is_full_screen";
    SharedPreferences prefs;
    Settings(Context context){
        prefs = context.getSharedPreferences(APP_SETTINGS,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

    }
}
