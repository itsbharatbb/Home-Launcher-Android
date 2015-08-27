package ex.home_launcher_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class MyLauncherActivity extends Activity  {
    RelativeLayout launcherLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_launcher);
        launcherLayout = (RelativeLayout) findViewById(R.id.launcherLayout);
        launcherLayout.setOnClickListener(ocl);
    }
    
    View.OnClickListener ocl = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(MyLauncherActivity.this,AllAppsActivity.class));
            overridePendingTransition(0,0);
        }
    };






}
